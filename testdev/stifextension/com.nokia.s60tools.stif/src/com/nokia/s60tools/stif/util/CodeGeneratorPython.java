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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.core.runtime.IPath;

import com.nokia.s60tools.stif.Constants;

/**
 * Code generator for python test modules. Used by Test Case Wizard
 *
 */
public class CodeGeneratorPython extends CodeGenerator {

	String outputCfgName;
	String newCfgSource;
	
	/**
	 * @param methods
	 * @param methodParams
	 * @param moduleType
	 * @param outputCppFilePath
	 * @param outputIncFilePath
	 * @param className
	 */
	public CodeGeneratorPython(IMethodDeclaration[] methods,
			Collection<String[][]> methodParams, int moduleType,
			String outputCppFilePath, String outputIncFilePath, String className, Vector<String> SDKList,
			String configFileName) {
		super(methods, methodParams, moduleType, outputCppFilePath,
				outputIncFilePath, className, SDKList);
		this.outputCfgName = configFileName;
	}
	
	/**
	 * Start code generating process
	 */
	public void run() {
		super.run();
		IPath projFilePath = FileAccessUtils.getMmpFileLoc();
		IPath groupProjPath = projFilePath.append("group");
		
		String inputCfgFilePath = groupProjPath + File.separator + className + "_script.py";
		newCfgSource = generateNewCfg(inputCfgFilePath, methods, className, methodParams);
	}
	
	/**
	 * Saves new generated content to files
	 * @return true if save succeeded, false otherwise
	 */
	public boolean saveContents() {
		super.saveContents();
		boolean retVal = false;
		//gets all TestFramework.ini locations definied in bld.inf and modifies all
		Iterator it = FileAccessUtils.getConfigFilePathts(outputCfgName, SDKList).iterator(); 
		while(it.hasNext())
		{
			IPath path = (IPath) it.next();
			File iniFile = new File( path.toOSString() );
			try {
				if(!iniFile.exists())
				{
					iniFile.createNewFile();
				}
				FileAccessUtils.setContents(iniFile, newCfgSource);
				retVal = true;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}			
		}
		retVal = true;
		return retVal;
	}

	/**
	 * Generates and returns the functions stub codes as String in collection
	 * 
	 * @param methods IMethodDeclaration array of methods to be tested
	 * @param methodParams Collection of String[][] arrays that holds the method parameter names and values for the corresponding methods in IMethodDeclaration array 
	 * @param moduleType The type of STIF test module
	 * @param className Name of the class where testfunctions are added
	 * @param pathToHeaderFile Path to header file
	 * @return function stub code
	 */
	protected Collection<String> getFunctions(IMethodDeclaration[] methods, 
			Collection<String[][]> methodParams, int moduleType, String className,
			String pathToHeaderFile) {
		
		Collection<String> functions = new ArrayList<String>();
		
		String template = null;
		
			try {
				InputStream stream = this.getClass().getResourceAsStream("/templates/python_function.stif");
				BufferedReader input = new BufferedReader(new InputStreamReader(stream));
				template = FileAccessUtils.getContents(input);
			} catch (Exception e) {
				//catch if template files are not found for some reason
				e.printStackTrace();
			}
			
		for (int i = 0; i < methods.length; i++) {
			String functionCode = template;
			IMethodDeclaration method = methods[i];
			String[][] params = ((ArrayList<String[][]>)methodParams).get(i);
			functionCode = functionCode.replace("?member_function", 
					newDecoratedMethodsName[i]);
			functionCode = functionCode.replace("?classname", className);
			String code = super.getParameterInits(method, params);
			code += "    " + super.getFunctionCall(method, params);
			functions.add(functionCode);
		}
		
		return functions;
	}

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
	protected String generateNewCpp(String outputCppFilePath, Collection<String> includes, 
			Collection<String> functions, IMethodDeclaration[] methods, String className) {
		//get cppfile contents to string
		String cppFile = FileAccessUtils.getContents(new File(outputCppFilePath));
		
		//place new functions to the string (end of file)
		Iterator<String> iter = functions.iterator();
		String testFunc = "";
		while (iter.hasNext()) {
			testFunc += iter.next() + System.getProperty("line.separator");
		}
		if (!testFunc.equals("")) {
			cppFile = cppFile.replace(Constants.PYTHON_CPP_FILE_INPUT_START, Constants.PYTHON_CPP_FILE_INPUT_START
					 + "\r\n" + testFunc);
		}
		
		//place new ENTRY statements to the string (start if File)
		String caseEntries = "";
		for (int i = 0; i < methods.length; i++) {
			String newMethodName = newDecoratedMethodsName[i];
			caseEntries += "{\"" + newMethodName + "\"," + newMethodName + "," +
			"METH_VARARGS},\r\n";
		}
//		Check if this source file already contains appended section
		if(!cppFile.contains( caseEntries ))
		{
			cppFile = cppFile.replace(Constants.PYTHON_INC_FILE_ENTRY, caseEntries + Constants.PYTHON_INC_FILE_ENTRY);
		}
			
		return cppFile;
	}

	/**
	 * Adds needed entries to TestFramework.ini for PythonScripter testmodule
	 * and returns the new content of TestFramework.ini as string. 
	 * The file remains untouched. 
	 * 
	 * @param outputIncFilePath Path of TestFaramework.ini file 
	 * @param className Name of test method class
	 * @return new TestFramework.ini file content as String
	 */
	protected String generateNewIni(String outputIniFilePath, String className)
	{
		String iniFile = FileAccessUtils.getContents(new File(outputIniFilePath));
		String newModule = "";
		//First look for existing pythonwrapper entries
		int startModule = iniFile.indexOf( Constants.PYTHON_SCRIPTER_INI_FILE_ENTRY );
		if(startModule!=-1)
		{
			newModule += "TestCaseFile= C:\\TestFramework\\" + outputCfgName + "\r\n";
			if(!iniFile.contains( newModule ))
			{
				iniFile = iniFile.replace(Constants.PYTHON_SCRIPTER_INI_FILE_ENTRY, 
						Constants.PYTHON_SCRIPTER_INI_FILE_ENTRY + newModule);
			}
			else {}
				//do nothing
		}
		else
		{
//			New module content is added here
			newModule += "\r\n[New_Module]\r\n"; 
			newModule += "ModuleName= PythonScripter\r\n";
			newModule += "TestCaseFile= C:\\TestFramework\\" + outputCfgName + "\r\n";
			newModule += "[End_Module]\r\n\r\n";
			startModule = iniFile.indexOf( "[New_Module]" );
			int endModule = iniFile.indexOf( "[End_Module]" );
			//Check if both tags [New_module] and [End Module] exist
				if((startModule != -1) && (endModule != -1))
				{
					String firstModule = iniFile.substring(startModule, endModule + 12);
					//Check if such module already exists or not, and if not add it to TestFramework.ini
					if(!iniFile.contains( newModule ))
					{
						iniFile = iniFile.replace(firstModule, firstModule + "\r\n" + newModule);
					}
					else {}
					//do nothing
				}
				//If not add new module section to the end of testframework.ini file
				else
				{
					iniFile = iniFile + newModule;
				}
				
		}
		return iniFile;
	}
	
	/**
	 * Adds needed test cases entries to .py file for PythonScripter testmodule
	 * and returns the new content of it as string. 
	 * 
	 * @param inputCfgFilePath Path of .py file 
	 * @param methods array with method delcarations that will be used for
	 * .py file creation
	 * @param className Name of the test class
	 * @param methodParams collection of methodParams with types
	 * @return new .py file content as String
	 */
	protected String generateNewCfg(String inputCfgFilePath, IMethodDeclaration[] methods, 
			String className, Collection<String[][]> methodParams)
	{
		File cfgFile = new File(inputCfgFilePath);
		if(!cfgFile.exists())
		{
			try {
				cfgFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String pyScriptContent = FileAccessUtils.getContents(cfgFile);
		
		
		for (int i = 0; i < methods.length; i++) {
			IMethodDeclaration method = methods[i];
			String testMethodName = newDecoratedMethodsName[i];
			//Remove leading C
			String classNameWithoutLeadC = className;
			if(className.charAt(0) == 'C');
			{
				classNameWithoutLeadC = classNameWithoutLeadC.substring(1, classNameWithoutLeadC.length());
			}
			String[][] params = ((ArrayList<String[][]>)methodParams).get(i);
			String parametersLine = getParameterValues(method, params, ',' , "0");
			String testCase = "";
			//put title of the script with 2 tabs spaces
			testCase+= "\r\n" + "def case_" + testMethodName + "():\r\n";
			testCase+= "\t" + "\"" + testMethodName + "\"" +  "\r\n";
			testCase+= "\t" + className + "." + testMethodName+"(" +
				parametersLine + ")" + "\r\n";
			testCase+= "\treturn(0,\"All correct\")\r\n";
			//check if such test case do not already exist in this file
			if(!pyScriptContent.contains( testCase) )
				pyScriptContent += testCase;
		}		
		
		pyScriptContent.replace(Constants.PYTHON_CFG_FILE_INPUT_END, Constants.PYTHON_CFG_FILE_INPUT_END + "\r\n" +
				pyScriptContent);
		return pyScriptContent;
	}
	
	/**
	 * Generates and returns the method declarations for header file for the 
	 * test functions as String in collection
	 * 
	 * @param methods IMethodDeclaration array of methods to be tested
	 * @return
	 */
	protected Collection<String> getMethodDeclarations(IMethodDeclaration[] methods) 
	{
		return new ArrayList<String>();
	}
	
	/**
	 * Returns new number for tested method.
	 * Searches for occurences of already existing function names and returns number increased by 1
	 * The format of case numeration is nn for example 01 02 11 etc.
	 *  
	 * @return new test method name number as a String. 
	 * @param functionName String name of function to be tesed and decorated by this method
	 * @param outputFilePath IPath header file location, that will be used for tracking of previous test cases occurences
	 */
	public String getNameWithNumberForTestCase(String functionName, String outputFilePath)
	{
		Integer lastNumberOfCase = 0;
		@SuppressWarnings("unused")
		String  lastStrNumberOfCase = "";
		@SuppressWarnings("unused")
		String lastOccurencedTestedMethod = "";
		File outputFile = new File( outputFilePath );
		if ( !outputFile.exists() )
			return null;
		String contOfSourceFile = FileAccessUtils.getContents(outputFile);
		
		//Look for occurence of the part of header files where all declaration are written
		int startIndex = contOfSourceFile.indexOf( Constants.PYTHON_INC_FILE_INPUT_START);
		int endIndex = contOfSourceFile.indexOf( Constants.PYTHON_INC_FILE_INPUT_END);
		//without additonal '"' it recognises function twice 
		String funNameWithCase =  "\"" + functionName + "Case";
		int funNameWithCaseLength = funNameWithCase.length();
		int lastOccurence = contOfSourceFile.indexOf( funNameWithCase );
		
		while( (lastOccurence < endIndex) && (lastOccurence > startIndex) )
		{
			//get two next chars after FunCase that represent testcase number
			String numberOfCase = contOfSourceFile.substring(lastOccurence + funNameWithCaseLength, 
					lastOccurence + funNameWithCaseLength + 2 );
			lastOccurence = contOfSourceFile.indexOf( funNameWithCase, lastOccurence + funNameWithCaseLength + 2 );
			
			Integer numericalNumberOfCase; 
			try { 
					numericalNumberOfCase = Integer.parseInt(numberOfCase);
					if(numericalNumberOfCase > lastNumberOfCase)
					{
						lastNumberOfCase = numericalNumberOfCase;
						lastStrNumberOfCase = numberOfCase;
					}
				} 
			catch(NumberFormatException e) {
				e.printStackTrace();
			}						
		}
		Integer newNumber = lastNumberOfCase + 1;
		String newStringNumberOfCase = newNumber.toString();
		//if written only with 1 digit then add leading 0
		if(newStringNumberOfCase.length() == 1)
		{
			newStringNumberOfCase = "0" + newStringNumberOfCase;
		}
		lastOccurencedTestedMethod = functionName + "Case" + newStringNumberOfCase;
		return newStringNumberOfCase;
	}

	//This method does nothing for python Scripter
	protected String generateNewInc(String outputIncFilePath, Collection<String> declarations) 
	{
		//This method does nothing for python Scripter
		String empty = "";
		return empty;
	}
}