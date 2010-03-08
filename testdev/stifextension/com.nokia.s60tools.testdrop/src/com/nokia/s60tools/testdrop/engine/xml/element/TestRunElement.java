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


package com.nokia.s60tools.testdrop.engine.xml.element;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.runtime.Path;
import org.w3c.dom.Element;

import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob.ModuleType;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TestContentValue;
import com.nokia.s60tools.testdrop.resources.Messages;

/**
 * Provides test run element that is main element.
 */
public class TestRunElement extends BaseElement {

	private static final String TEST_RUN_ELEMENT = "test"; 
	private final String ID_ELEMENT = "id"; 
	private final String NAME_ELEMENT = "name"; 
	private final String FILES_ELEMENT = "files"; 
	private final String DOUBLDE_BACK_SLASH = "\\"; 
	private final String GENERAL_DIRECTORY = "general";
	private String name;
	private TargetElement targetElement;

	/**
	 * Constructor with mandatory parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param targetElement
	 *            target element part for the element
	 */
	public TestRunElement(String name, TargetElement targetElement) {
		super(TEST_RUN_ELEMENT);
		this.name = name;
		this.targetElement = targetElement;
		createElement();

	}

	/**
	 * Sets element content from the class data
	 */
	protected void createElement() {
		dom.appendChild(getElement());

		Element idElement = dom.createElement(ID_ELEMENT);
		idElement.setTextContent(String.valueOf(testRunId));
		getElement().appendChild(idElement);

		Element nameElement = dom.createElement(NAME_ELEMENT);
		nameElement.setTextContent(name);
		getElement().appendChild(nameElement);

		getElement().appendChild(targetElement.getElement());
	}

	/**
	 * creates files element and its children
	 * 
	 * @param testContent
	 *            contain data for constructs file element
	 */
	public void createFilesElement(TestContentValue testContent) {
		Element filesElement = dom.createElement(FILES_ELEMENT);

		if (testContent == null) {
			throw new IllegalArgumentException(
					Messages
							.getString("TestRunElement.testContentCannotBeNullException")); 
		}

		if (testContent.getTestType() != ModuleType.TESTCOMBINER) {
			new FileElement(testContent.getComponentPath()
					+ testContent.getTestBinaryVariant() + DOUBLDE_BACK_SLASH
					+ testContent.getTestBinarySrcPath().lastSegment(),
					filesElement);
		}
		else {
			for (int i = 0; testContent.getFilesToIncludeNames() != null
							&& i < testContent.getFilesToIncludeNames().length; i++) {
				Path filePath = new Path(testContent.getFilesToIncludeNames()[i]);
				if (filePath.getFileExtension().equals("dll")) {
					new FileElement(testContent.getComponentPath()
							+ testContent.getTestBinaryVariant() + DOUBLDE_BACK_SLASH
							+ filePath.lastSegment(), filesElement);
				}
				else {
					new FileElement(testContent.getComponentPath()
							+ GENERAL_DIRECTORY + DOUBLDE_BACK_SLASH
							+ filePath.lastSegment(), filesElement);
				}
			}
		}

		if (testContent.getTestType() == ModuleType.TESTCLASS
				|| testContent.getTestType() == ModuleType.TESTCOMBINER
				|| (testContent.getTestType() == ModuleType.NORMAL 
						&& testContent.getTestScriptSrcPath() != null)) {
			new FileElement(testContent.getScriptPathInsideDrop()
					+ testContent.getTestScriptSrcPath().lastSegment(),
					filesElement);
		}
		getElement().appendChild(filesElement);

		Iterator<TargetDeviceValue> tartgetIterator = testContent.getTargets()
				.iterator();
		while (tartgetIterator.hasNext()) {
			TargetDeviceValue targetDeviceValue = (TargetDeviceValue) tartgetIterator
					.next();
			if (targetDeviceValue.getImagesInsideDrop() != null) {
				Iterator<File> fileIterator = targetDeviceValue
						.getImagesInsideDrop().iterator();
				while (fileIterator.hasNext()) {
					File file = (File) fileIterator.next();
					new FileElement(file.toString(), filesElement);
				}
			}
		}
	}
}
