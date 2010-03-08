/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of "Eclipse Public License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.eclipse.org/legal/epl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description:
*
*/


package com.nokia.s60tools.stif.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.IInclude;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import com.nokia.s60tools.stif.Constants;


/**
 * Abstract code generator
 * 
 */
abstract public class CodeGenerator {
	
	IMethodDeclaration[] methods;
	Collection<String[][]> methodParams;
	int moduleType;
	String outputCppFilePath;
	String outputIncFilePath;
	String outputIniFilePath;
	String className;
	String oldCppSource;
	String newCppSource;
	String oldIncSource;
	String newIncSource;
	String oldInISource;
	String newInISource;
	String[] newDecoratedMethodsName;
	Collection<String> declarations;
	Vector<String> SDKList;
	
	/**
	 * Constructor 
	 * 
	 * @param methods IMethodDeclarations of the functions to test in a array
	 * @param methodParams Parameters for the methods in same order as the methods
	 * @param moduleType Output module type
	 * @param outputCppFilePath Output source file path
	 * @param outputIncFilePath Output header file path
	 * @param className name of test class
	 */
	public CodeGenerator(IMethodDeclaration[] methods, Collection<String[][]> methodParams, 
			int moduleType, String outputCppFilePath, String outputIncFilePath, String className,
			Vector<String> SDKList) {
		this.methods = methods;
		this.methodParams = methodParams;
		this.moduleType = moduleType;
		this.outputCppFilePath = outputCppFilePath;
		this.outputIncFilePath = outputIncFilePath;
		this.SDKList = SDKList;
		this.outputIniFilePath = FileAccessUtils.getTestFrameworkIniPathts(SDKList).get(0).toOSString(); 
		this.className = className;
		
		if(moduleType == Constants.MODULE_TYPE_PYTHONTEST)
			this.newDecoratedMethodsName = getDecoratedMethods(methods, outputCppFilePath );
		else
			this.newDecoratedMethodsName = getDecoratedMethods(methods, outputIncFilePath );
	}
	
	/**
	 * Start code generating process
	 */
	public void run() {
		Collection<String> includes = getIncludes(methods, outputCppFilePath);
		Collection<String> functions = getFunctions(methods, methodParams, moduleType, className, outputIncFilePath);
		declarations = getMethodDeclarations(methods);
		oldCppSource = FileAccessUtils.getContents(new File(outputCppFilePath));
		oldIncSource = FileAccessUtils.getContents(new File(outputIncFilePath));
		oldInISource = FileAccessUtils.getContents(new File(outputIniFilePath));
		newCppSource = generateNewCpp(outputCppFilePath, includes, functions, methods, className);
		newInISource = generateNewIni(outputIniFilePath, className);
		newIncSource = generateNewInc(outputIncFilePath, declarations);
	}
	
	/**
	 * Saves new generated content to files
	 * @return true if save succeeded, false otherwise
	 */
	public boolean saveContents() {
		boolean retVal = false;
		File cppFile = new File(outputCppFilePath);
		File incFile = new File(outputIncFilePath);
	
		try {
			FileAccessUtils.setContents(cppFile, newCppSource);
			FileAccessUtils.setContents(incFile, newIncSource);
			retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		Iterator it = FileAccessUtils.getTestFrameworkIniPathts(SDKList).iterator();
		
		//gets all TestFramework.ini locations definied in bld.inf and modifies all
		while(it.hasNext())
		{
			IPath path = (IPath) it.next();
			File iniFile = new File( path.toOSString() );
			try {
				FileAccessUtils.setContents(iniFile, newInISource);
				retVal = true;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return retVal;
	}
	
	/**
	 * Returns the needed header files to be included for the selected methods in a String collection 
	 * containing elements like "TestClass.h" or <SystemClass.h>
	 * 
	 * @param methods IMethodDeclaration array of the methods to be tested
	 * @param outputCppFilePath Path to the cpp file where test cases are added
	 * @return include files of the selected methods 
	 */
	private Collection<String> getIncludes(IMethodDeclaration[] methods, String outputCppFilePath) {
		Collection<String> includes = new ArrayList<String>();
		for (int i = 0; i < methods.length; i++) {
			String includeFile = methods[i].getTranslationUnit().getElementName();
			boolean systemInclude = false;
			if (includeFile.indexOf("/") != -1) {
				includeFile = includeFile.substring(includeFile.lastIndexOf("/") + 1 );
				systemInclude = true;
			} 
			if (!hasInclude(includeFile, outputCppFilePath)) {
				if (systemInclude) {
					includeFile = "<" + includeFile + ">";
				} else {
					includeFile = "\"" + includeFile + "\"";
				}
				if (!includes.contains(includeFile)) {
					includes.add(includeFile);
				}
			}
		}
		return includes;
	}
	
	/**
	 * Checks if the cpp file allready has header file included
	 * 
	 * @param includeFile header file to include
	 * @param cppFilePath path to the cpp file where to check for includes
	 * @return true if header is allready found in cpp file, false otherwise
	 */
	private boolean hasInclude(String includeFile, String cppFilePath) {
		if(cppFilePath.indexOf(includeFile) > -1)
			return true;
		try {
			ITranslationUnit cppfile = (ITranslationUnit)CoreModel.getDefault().create(new Path(cppFilePath));
			IInclude[] includes = cppfile.getIncludes();
			for (int i = 0; i < includes.length; i++) {
				if(includes[i].getElementName().equals(includeFile)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Generates and returns the method declarations for header file for the 
	 * test functions as String in collection
	 * 
	 * @param methods IMethodDeclaration array of methods to be tested
	 * @param moduleType The type of STIF test module
	 * @return strings containing method declarations
	 */
	abstract protected Collection<String> getMethodDeclarations(IMethodDeclaration[] methods);
	
	/**
	 * Generates function call and sets parameters to it
	 * 
	 * @param method IMethodDeclaration from what the call will be generated
	 * @param methodParams String[][] array that holds method parameter names and values
	 * @return
	 */
	protected String getFunctionCall(IMethodDeclaration method, String[][] methodParams) {
		String retVal = method.getElementName() + "(";
		for(int i = 0; i < methodParams.length; i++) {
			retVal += methodParams[i][0];
			if (i + 1 < methodParams.length) {
				retVal += ",";
			}
		} 
		return retVal += ");" + System.getProperty("line.separator");
	}
	
	/**
	 * Generates code to initialize parameters
	 * 
	 * @param method IMethodDeclaration whose parameters will be initialized
	 * @param methodParams String[][] array that holds method parameter names and values
	 * @return
	 */
	protected String getParameterInits(IMethodDeclaration method, String[][] methodParams) {
		String retVal = "";
		for(int i = 0; i < methodParams.length; i++) {
			if ((method.getParameterTypes()[i]).indexOf('=') != -1) {
				// If parameter has default initializing, CDT doesn't parse parametertype correctly
				// in this case, use the default initializing.
				retVal += method.getParameterTypes()[i] + ";" + System.getProperty("line.separator"); 
			} else {
				String paramValue = "";
				if (methodParams[i][1].equals("")) {
					paramValue = "NULL";
				} else {
					paramValue = methodParams[i][1];
				}
				retVal += method.getParameterTypes()[i] + " " + methodParams[i][0] 
				           + " = " + paramValue + ";" + System.getProperty("line.separator");
			}
		}
		return retVal;
	}
	
	/**
	 * Returns values of given method parameters
	 * 
	 * @param method IMethodDeclaration whose parameters will be initialized
	 * @param methodParams String[][] array that holds method parameter names and values
	 * @param delimiter char a character that will separate parameters
	 * @param defaultValue - will be different for C++ and for Python
	 * @return valeus of give method parameteres as one properly formated String
	 */
	protected String getParameterValues(IMethodDeclaration method, String[][] methodParams, char delimiter,
			String defaultValue) 
	{
		String retVal = "";
		for(int i = 0; i < methodParams.length; i++) {
			if ((method.getParameterTypes()[i]).indexOf('=') != -1) {
				// If parameter has default initializing, CDT doesn't parse parametertype correctly
				// in this case, use the default initializing.
				retVal += method.getParameterTypes()[i] + ";" + System.getProperty("line.separator"); 
			} else {
				String paramValue = "";
				if (methodParams[i][1].equals("")) {
					paramValue = defaultValue;
				} else {
					paramValue = methodParams[i][1];
				}
				//don't add delimiter after last value
				if(i+1 < methodParams.length)
					retVal += paramValue + delimiter + " ";
				else
					retVal += paramValue + " ";
			}
		}
		return retVal;
	}
	
	/**
	 * Returns new decorated name of test method with appropriate number
	 * 
	 * @param methodToBeDecorated String name of method choosen by End User that needs to be tested
	 * @param methods String[] array of already decorated methods
	 * @return new decorated test method name
	 */
	public String getNewNumerOfMethodInList( String methodToBeDecorated, String[] methods )
	{
		//set to 01. If this value will be returned this means that this value wasn't in this list
		String strMaxNumber = "00";
		Integer maxNumber = 0;
		for(int i=0;i<methods.length;i++)
		{
			//check if the current elemnt is not an empty one
			if( methods[i] == null )
				continue;
			String methodName = methods[i];
			if( methodName.contains(methodToBeDecorated+"Case") )
			{
				//get number in 2 digits form
				String newNumber = methodName.substring(methodName.length() - 7, methodName.length() -5);
				Integer numericalNumberOfCase; 
				try { 
						numericalNumberOfCase = Integer.parseInt(newNumber);
						if(numericalNumberOfCase > maxNumber)
						{
							maxNumber = numericalNumberOfCase;
						}
					} 
				catch(NumberFormatException e) {
					e.printStackTrace();
				}						
			}
		}
		maxNumber += 1;
		strMaxNumber = maxNumber.toString();
		//if written only with 1 digit then add leading 0
		if( strMaxNumber.length() == 1)
		{
			strMaxNumber = '0' + strMaxNumber;
		}
		return strMaxNumber;
	}
	
	/**
	 * Creates a table of decorated methods names from method names choosen by End User
	 * 
	 * @param methods IMethodDeclaration[] array of method declarations that were choosen for 
	 * test case creation by End User
	 * @param pathToHeaderFile String path to file that contains method declarations
	 * @return new decorated test method name's table
	 */
	//Allocates memory for returned table
	public String[] getDecoratedMethods( IMethodDeclaration[] methods, String pathToHeaderFile )
	{
		String[] decoratedMethods = new String[methods.length];
		for(int i=0; i<methods.length; i++)
		{
			String bareMethodName = methods[i].getElementName();
			if(bareMethodName.indexOf("::") > -1){
				bareMethodName = bareMethodName.substring(bareMethodName.lastIndexOf("::") + 2, bareMethodName.length());
			}
			//First look for the occurence of the method in newly created table
			//It could be that the biggest numer was already found in the file and it makes
			//no sense to search in it again. Better to check the new list and increase the number
			//that was found out there
			String decName = getNewNumerOfMethodInList( bareMethodName, decoratedMethods);
			/*If number equals 01 or null this means that the function was not searched in the file
			 * and now it needs to be searched in the header file
			*/
			if( (decName.equals("00")) || (decName.equals("01")) || (decName == null) )
			{
				decName = getNameWithNumberForTestCase( bareMethodName, pathToHeaderFile );
			}
			String methodCaseName = bareMethodName + "Case" + decName + "TestL";
			decoratedMethods[i] = methodCaseName;								
		}
		return decoratedMethods;
	}
	
	/**
	 * Creates a new name for test method with apporpriate number in format 
	 * <testMethodName>Case<Numer>Test for example for TurnCameraOn should result
	 * in first test case creation as "TurnCameraOnCase01Test"
	 * Looks for occurences of method to be tested in header file
	 * 
	 * @param functionName String name of the test function to be found in header file 
	 * test case creation by End User
	 * @param pathToHeaderFile String path to file that contains method declarations
	 * @return new decorated test method name
	 */
	abstract public String getNameWithNumberForTestCase(String functionName, String headerFilePath);
	
	/**
	 * Returns new cpp source in String
	 * @return
	 */
	public String getNewCppSource() {
		return newCppSource;
	}
	
	/**
	 * Returns new header source in String
	 * @return
	 */
	public String getNewIncSource() {
		return newIncSource;
	}
	
	/**
	 * Returns new TestFramework.ini file in String
	 * @return
	 */
	public String getNewIniSource() {
		return newInISource;
	}

	/**
	 * Returns old cpp source in String
	 * @return
	 */
	public String getOldCppSource() {
		return oldCppSource;
	}

	/**
	 * Returns old header source in String
	 * @return
	 */
	public String getOldIncSource() {
		return oldIncSource;
	}
	
	/**
	 * Returns old TestFramework.ini file in String
	 * @return
	 */
	public String getOldIniSource() {
		return oldInISource;
	}
	
	/**
	 * Returns path to the cpp file in String
	 * @return
	 */
	public String getOutputCppFilePath() {
		return outputCppFilePath;
	}
	
	/**
	 * Returns path to the header file in String
	 * @return
	 */
	public String getOutputIncFilePath() {
		return outputIncFilePath;
	}
	
	/**
	 * Returns path to the TestFramework.ini file in String
	 * @return
	 */
	public String getOutputInIFilePath() {
		return outputIniFilePath;
	}
	
	/**
	 * Generates and returns the functions stub codes as String in collection
	 * 
	 * @param methods IMethodDeclaration array of methods to be tested
	 * @param methodParams Collection of String[][] arrays that holds the method parameter names and values for the corresponding methods in IMethodDeclaration array 
	 * @param moduleType The type of STIF test module
	 * @param className Name of the class where testfunctions are added
	 * @param headerFile Name of header file out of which funtions should be taken
	 * @return function stub code
	 */
	abstract protected Collection<String> getFunctions(IMethodDeclaration[] methods, 
			Collection<String[][]> methodParams, int moduleType, String className,
			String headerFile); 
	
	/**
	 * Adds needed header includes, new test functions and test cases to STIF cases table
	 * to the cppFile source and returns the new source as string. The file remains untouched. 
	 * 
	 * @param outputCppFilePath Path to the cpp file
	 * @param includes header files to include in String collection
	 * @param functions new test functions to add to cpp source
	 * @param methods IMethodDeclaration array of the methods that are tested (from what the functions are generated)
	 * @param className Classname where functions are added
	 * @return new cpp source as String
	 */
	abstract protected String generateNewCpp(String outputCppFilePath, Collection<String> includes, 
			Collection<String> functions, IMethodDeclaration[] methods, String className);
	
	/**
	 * Adds needed entries to heder file and returns the new source as string. 
	 * The file remains untouched. 
	 * 
	 * @param outputIncFilePath Path to the header file
	 * @param declarations method declarations
	 * @return new header file content as String
	 */
	abstract protected String generateNewInc(String outputIncFilePath, Collection<String> declarations);
	
	/**
	 * Adds needed entries to TestFramework.ini file and returns the new content
	 * of TestFramework.ini as string. The file remains untouched. 
	 * 
	 * @param outputIncFilePath Path of TestFaramework.ini file 
	 * @param className Name of test method class
	 * @return new TestFramework.ini file content as String
	 */
	abstract protected String generateNewIni(String outputIniFilePath, String className);
}
