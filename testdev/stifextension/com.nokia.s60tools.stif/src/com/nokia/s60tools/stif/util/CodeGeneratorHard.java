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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.cdt.core.model.IMethodDeclaration;

import com.nokia.s60tools.stif.Constants;

/**
 * Code generator for hardcoded modules. Used by Test Case Wizard.
 *
 */
public class CodeGeneratorHard extends CodeGenerator {

	/**
	 * @param methods
	 * @param methodParams
	 * @param moduleType
	 * @param outputCppFilePath
	 * @param outputIncFilePath
	 * @param className
	 */
	public CodeGeneratorHard(IMethodDeclaration[] methods,
			Collection<String[][]> methodParams, int moduleType,
			String outputCppFilePath, String outputIncFilePath, String className, 
			Vector<String> SDKList) {
		super(methods, methodParams, moduleType, outputCppFilePath,
				outputIncFilePath, className, SDKList);
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
	protected Collection<String> getFunctions(IMethodDeclaration[] methods, 
			Collection<String[][]> methodParams, int moduleType, String className,
			String headerFilePath) {
		
		Collection<String> functions = new ArrayList<String>();
		
		String template = null;
		
		try {
				InputStream stream = this.getClass().getResourceAsStream("/templates/hardcoded_function.stif");
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
			functionCode = functionCode.replace("?type", "TInt");
			functionCode = functionCode.replace("?args", "TTestResult& aResult");
			functionCode = functionCode.replace("?member_function", newDecoratedMethodsName[i]);
			functionCode = functionCode.replace("?classname", className);
			String code = super.getParameterInits(method, params);
			code += "    " + super.getFunctionCall(method, params);
			functionCode = functionCode.replace("?code", code);
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
		
		//place new includes to the string
		Iterator<String> iter = includes.iterator();
		String header = "";
		while (iter.hasNext()) {
			header += "#include " + iter.next();
		}
		if (!header.equals("")) {
			cppFile = cppFile.replace("[INCLUDE FILES] - do not remove", "[INCLUDE FILES] - do not remove" + System.getProperty("line.separator") + header);
		}
		
		//place new functions to the string (end of file)
		iter = functions.iterator();
		String testFunc = "";
		while (iter.hasNext()) {
			testFunc += iter.next() + System.getProperty("line.separator");
		}
		if (!testFunc.equals("")) {
			cppFile = cppFile.replace("//  [End of File] - do not remove", testFunc + "//  [End of File] - do not remove");
		}
		
		//put new test cases to the KCases array (in hardcoded module type)
		String caseEntries = "";
		for (int i = 0; i < methods.length; i++) {
			caseEntries += "        FUNCENTRY( " + className + "::" 
						+ newDecoratedMethodsName[i] + " )," 
						+ System.getProperty("line.separator");
		}
		if (!caseEntries.equals("")) {
			cppFile = cppFile.replace("[test cases entries] - do not remove", 
				"[test cases entries] - do not remove" + 
				System.getProperty("line.separator") + caseEntries);
		}
		
		return cppFile;
	}

	/**
	 * Adds needed entries to heder file and returns the new source as string. 
	 * The file remains untouched. 
	 * 
	 * @param outputIncFilePath Path to the header file
	 * @param declarations method declarations
	 * @return new header file content as String
	 */
	protected String generateNewInc(String outputIncFilePath, Collection<String> declarations) {
		//get header file contents to string
		String incFile = FileAccessUtils.getContents(new File(outputIncFilePath));
		
		//place new method declarations to the string
		Iterator<String> iter = declarations.iterator();
		String methodDeclaration = "";
		while (iter.hasNext()) {
			methodDeclaration += "        " + iter.next();
		}
		
		incFile = incFile.replace("[test case declarations] - do not remove\r\n        */", 
				"[test case declarations] - do not remove\r\n        */" 
				+ System.getProperty("line.separator") + methodDeclaration);
		
		return incFile;
	}

	/**
	 * Adds needed entries to TestFramework.ini file and returns the new content
	 * of TestFramework.ini as string. The file remains untouched. 
	 * 
	 * @param outputIncFilePath Path of TestFaramework.ini file 
	 * @param className Name of test method class
	 * @return new TestFramework.ini file content as String
	 */
	protected String generateNewIni(String outputIniFilePath, String className)
	{
		String iniFile = FileAccessUtils.getContents(new File(outputIniFilePath));
		
		int startModule = iniFile.indexOf( "[New_Module]" );
		int endModule = iniFile.indexOf( "[End_Module]" );
		
		String classNameWithoutC = className.substring(1, className.length() );
		
		String newModule = "";
		newModule += "[New_Module]\r\n";
		newModule += "ModuleName= " + classNameWithoutC + "\r\n";
		newModule += "[End_Module]\r\n";
		// Check if both tags [New_module] and [End Module] exist
		if((startModule != -1) && (endModule != -1))
		{
			String firstModule = iniFile.substring(startModule, endModule + 12);
			//New module content is added here
			if(!iniFile.contains(newModule))
			{
				iniFile = iniFile.replace(firstModule, firstModule + "\r\n" + newModule);
			}
		}
		//If not add new module section to the end of testframework.ini file
		else
		{
			iniFile = iniFile + newModule;
		}
		
		return iniFile;
	}
	
	/**
	 * Generates and returns the method declarations for header file for the 
	 * test functions as String in collection
	 * 
	 * @param methods IMethodDeclaration array of methods to be tested
	 * @param moduleType The type of STIF test module
	 * @return
	 */
	protected Collection<String> getMethodDeclarations(IMethodDeclaration[] methods) 
	{
		Collection<String> declarations = new ArrayList<String>();
		
		for (int i = 0; i < methods.length; i++) {
			String declaration = "TInt " + newDecoratedMethodsName[i] + "(";
			declaration += "TTestResult& aResult";
			declaration += ");" + System.getProperty("line.separator");
			declarations.add(declaration);
		}
		return declarations;
	}
	
	/**
	 * Returns new number for tested method.
	 * Searches for occurences of already existing function names and returns number increased by 1
	 * The format of case numeration is nn for example 01 02 11 etc.
	 *  
	 * @param functionName String name of function to be tesed and decorated by this method
	 * @param headerFilePath IPath header file location, that will be used for tracking of previous test cases occurences
	 * @return new test method name number as a String. 
	 */
	public String getNameWithNumberForTestCase(String functionName, String headerFilePath)
	{
		Integer lastNumberOfCase = 0;
		@SuppressWarnings("unused")
		String  lastStrNumberOfCase = "";
		@SuppressWarnings("unused")
		String lastOccurencedTestedMethod = "";
		File headerFile = new File( headerFilePath );
		if ( !headerFile.exists() )
			return null;
		String contOfHeaderFile = FileAccessUtils.getContents(headerFile);
		
		//Look for occurence of the part of header files where all declaration are written
		int startIndex = contOfHeaderFile.indexOf( Constants.HARDCODED_INC_FILE_INPUT_START);
		int endIndex = contOfHeaderFile.indexOf( Constants.HARDCODED_INC_FILE_INPUT_END);
		String funNameWithCase =  functionName + "Case";
		int funNameWithCaseLength = funNameWithCase.length();
		int lastOccurence = contOfHeaderFile.indexOf( funNameWithCase );
		
		while( (lastOccurence < endIndex) && (lastOccurence > startIndex) )
		{
			//get two next chars after FunCase that represent testcase number
			String numberOfCase = contOfHeaderFile.substring(lastOccurence + funNameWithCaseLength, 
					lastOccurence + funNameWithCaseLength + 2 );
			lastOccurence = contOfHeaderFile.indexOf( funNameWithCase, lastOccurence + funNameWithCaseLength + 2 );
			
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
}
