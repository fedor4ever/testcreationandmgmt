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

package com.nokia.s60tools.stif;

/**
 * Constants
 *
 */
public class Constants {

	/**  Normal type of module	 */
	public final static int MODULE_TYPE_NORMAL = 1;
	/**  Hardcoded type of module	 */
	public final static int MODULE_TYPE_HARDCODED = 2;
	/**  Testscripter type of module	 */
	public final static int MODULE_TYPE_TESTCLASS = 3;
	/**  Kerneltest type of module	 */
	public final static int MODULE_TYPE_KERNELTEST = 4;
	/**  Capsmodifier type of module	 */
	public final static int MODULE_TYPE_CAPSMODIFIER = 5;
	/**  Pythonscripter type of module	 */
	public final static int MODULE_TYPE_PYTHONTEST = 6;
	/**  STIFUnit type of module	 */
	public final static int MODULE_TYPE_STIFUNIT = 7;

	/** Tag that is used in testscripter header file helping to find start of method
	 *  declarations section
	*/
	public final static String TESTCLASS_INC_FILE_INPUT_START = "* Test methods are listed below.";
	/** Tag that is used in testscripter header file helping to find end of method
	 *  declarations section
	*/
	public final static String TESTCLASS_INC_FILE_INPUT_END = "//[TestMethods] - Do not remove";
	
	public final static String TESTCLASS_SRC_FILE_INPUT = "// [test cases entries] - Do not remove";
	public final static String TESTCLASS_INI_FILE_ENTRY = "ModuleName= TestScripter\r\n";
	
	
	public final static String HARDCODED_INC_FILE_INPUT_START = "* Actual Hardcoded test case functions are listed below.";
	public final static String HARDCODED_INC_FILE_INPUT_END = "//ADD NEW METHOD DEC HERE";
	
	public final static String PYTHON_INC_FILE_INPUT_START = "// Method table to map Python script names to correct C++ wrappers";
	public final static String PYTHON_INC_FILE_INPUT_END = "{0, 0}";
	public final static String PYTHON_INC_FILE_ENTRY = "//{\"Template\",      Template,     METH_VARARGS},";
	
	public final static String PYTHON_CFG_FILE_INPUT_START = "# test cases start here";
	public final static String PYTHON_CFG_FILE_INPUT_END = "# test example add function";
	
	public final static String PYTHON_CPP_FILE_INPUT_START = "// ADD YOUR OWN FUNTIONS HERE";
	public final static String PYTHON_CPP_FILE_INPUT_END = "// PYTHON AND SYMBIAN RELATED METHODS";
	
	public final static String PYTHON_SCRIPTER_INI_FILE_ENTRY = "ModuleName= PythonScripter\r\n";
	
	
}
