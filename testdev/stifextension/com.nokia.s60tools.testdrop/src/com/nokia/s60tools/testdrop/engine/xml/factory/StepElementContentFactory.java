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


package com.nokia.s60tools.testdrop.engine.xml.factory;

import com.nokia.s60tools.testdrop.engine.xml.element.CaseElement;
import com.nokia.s60tools.testdrop.engine.xml.element.ParamElement;
import com.nokia.s60tools.testdrop.engine.xml.element.StepElement;
import com.nokia.s60tools.testdrop.engine.xml.element.TargetElement;
import com.nokia.s60tools.testdrop.resources.Messages;

import org.w3c.dom.Element;

/**
 * Factory class for constructs step element content
 */
public class StepElementContentFactory {

	public static final String TYPE_MODULE_VALUE = "binary"; 
	public static final String TYPE_SCRIPT_VALUE = "data"; 
	public static final String TEST_SCRIPTER_MODULE_ATTRIBUTE_VALUE = "TestScripter"; 
	public static final String TEST_COMBINER_MODULE_ATTRIBUTE_VALUE = "TestCombiner"; 
	private static final String TESTCASE_FILE_ATTRIBUTE = "testcase-file"; 
	private static final String FILTER_ATTRIBUTE = "filter"; 
	private static final String FILTER_ATTRIBUTE_VALUE = "*"; 
	private static final String TIMEOUT_ATTRIBUTE = "timeout"; 
	private static final String TIMEOUT_ATTRIBUTE_VALUE = "300"; 
	private final String INSTALL = "install"; 
	private final String RUN = "run-cases"; 
	private final String TYPE_ATTRIBUTE = "type"; 
	private final String SRC_ATTRIBUTE = "src"; 
	private final String DST_ATTRIBUTE = "dst"; 
	private final String COMPONENT_PATH_ATTRIBUTE = "component-path"; 
	private final String MODULE_ATTRIBUTE = "module"; 
	private final String DST_MODULE_VALUE = "c:\\sys\\bin\\"; 
	private final String DST_SCRIPT_VALUE = "c:\\TestFramework\\"; 
	private final String SCRIPT_FILE_EXTENSION = ".cfg"; 
	private final String INSTALL_MODULE_NAME = Messages
			.getString("StepElementContentFactory.installTestModule"); 
	private final String INSTALL_SCRIPT_NAME = Messages
			.getString("StepElementContentFactory.installTestscript"); 
	private final String RUN_NAME = Messages
			.getString("StepElementContentFactory.runTestCases"); 
	private final String COMPONENT_PATH = "ATS3\\components"; 
	private StepElement stepElement;
	private String method;
	private String type;
	private String src;
	private String config;
	private CaseElement caseElement;
	private TargetElement targetElement;

	/**
	 * Constructs class with mandatory parameter for constructs step element
	 * content
	 * 
	 * @param method
	 *            command element type
	 * @param type
	 *            attribute value for type element
	 * @param src
	 *            attribute value for src element
	 * @param caseElement
	 *            for constructs step element
	 * @param targetElement
	 *            for constructs step element
	 */
	public StepElementContentFactory(String method, String type, String src, String config,
			CaseElement caseElement, TargetElement targetElement) {
		this.method = method;
		this.type = type;
		this.src = src;
		this.config = config;
		this.caseElement = caseElement;
		this.targetElement = targetElement;
		if (method.equals(INSTALL) && type.equals(TYPE_MODULE_VALUE)) {
			createModuleInstallStep(INSTALL_MODULE_NAME);
		} else if (method.equals(INSTALL) && type.equals(TYPE_SCRIPT_VALUE)) {
			createModuleInstallStep(INSTALL_SCRIPT_NAME);
		} else if (method.equals(RUN)) {
			createRunStep(type);
		}
	}

	/**
	 * Creates step element content for install step
	 * 
	 * @param name
	 *            step element name
	 */
	private void createModuleInstallStep(String name) {
		stepElement = new StepElement(name, method, caseElement, targetElement);
		Element paramsElement = stepElement.getParamsElement();
		new ParamElement(TYPE_ATTRIBUTE, type, paramsElement);
		new ParamElement(SRC_ATTRIBUTE, src, paramsElement);
		if (src.indexOf(SCRIPT_FILE_EXTENSION) != -1) {
			new ParamElement(DST_ATTRIBUTE, DST_SCRIPT_VALUE + src,
					paramsElement);
		} else {
			new ParamElement(DST_ATTRIBUTE, DST_MODULE_VALUE + src,
					paramsElement);
		}

		new ParamElement(COMPONENT_PATH_ATTRIBUTE, COMPONENT_PATH,
				paramsElement);
	}

	/**
	 * Create step element content for run step
	 */
	private void createRunStep(String moduleName) {
		stepElement = new StepElement(RUN_NAME, method, caseElement,
				targetElement);
		Element paramsElement = stepElement.getParamsElement();
		new ParamElement(MODULE_ATTRIBUTE, moduleName,
				paramsElement);
		if (moduleName.equals("TestScripter") || moduleName.equals("TestCombiner")) {
			new ParamElement(TESTCASE_FILE_ATTRIBUTE, DST_SCRIPT_VALUE + src,
					paramsElement);
		}
		if (config != null) {
			new ParamElement(TESTCASE_FILE_ATTRIBUTE, DST_SCRIPT_VALUE + config, 
					paramsElement);
		}
		new ParamElement(FILTER_ATTRIBUTE, FILTER_ATTRIBUTE_VALUE,
				paramsElement);
		new ParamElement(TIMEOUT_ATTRIBUTE, TIMEOUT_ATTRIBUTE_VALUE,
				paramsElement);
	}
}
