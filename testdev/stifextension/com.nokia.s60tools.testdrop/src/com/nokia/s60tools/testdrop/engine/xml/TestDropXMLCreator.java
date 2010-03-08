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


package com.nokia.s60tools.testdrop.engine.xml;

import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob.ModuleType;
import com.nokia.s60tools.testdrop.engine.xml.element.ActionParamElement;
import com.nokia.s60tools.testdrop.engine.xml.element.BaseElement;
import com.nokia.s60tools.testdrop.engine.xml.element.CaseElement;
import com.nokia.s60tools.testdrop.engine.xml.element.FlashElement;
import com.nokia.s60tools.testdrop.engine.xml.element.PostActionElement;
import com.nokia.s60tools.testdrop.engine.xml.element.SessionElement;
import com.nokia.s60tools.testdrop.engine.xml.element.SetElement;
import com.nokia.s60tools.testdrop.engine.xml.element.TargetElement;
import com.nokia.s60tools.testdrop.engine.xml.element.TestPlanElement;
import com.nokia.s60tools.testdrop.engine.xml.element.TestRunElement;
import com.nokia.s60tools.testdrop.engine.xml.factory.StepElementContentFactory;
import com.nokia.s60tools.testdrop.engine.xml.factory.TargetElementFactory;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TestContentValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TestResultPropertyValue;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.runtime.Path;
import org.w3c.dom.Element;

/**
 * Creates test drop XML file
 * 
 */
public class TestDropXMLCreator {

	private final String PLAN_NAME = Messages
			.getString("TestDropXMLCreator.testPlanName"); 
	private final String SESSION_NAME_SUFFIX = " Session"; 
	private final String SET_NAME_SUFFIX = " Set"; 
	private final String CASE_NAME_SUFFIX = " Case"; 
	private final String HARNESS_STIF = "STIF"; 
	private final String IMAGE_SEPARATOR = ";"; 
	private final String XML_FILE_EXTENSION = ".xml"; 
	private final String FILE_STORE_ACTION = "FileStoreAction"; 
	private String fileName;
	private TestRunElement testRunElement;
	private TestPlanElement testPlanElement;
	private TargetElementFactory targetElementFactory;

	/**
	 * Constructors
	 * 
	 * @param fileName
	 *            XML file name
	 */
	public TestDropXMLCreator(String fileName) throws IllegalArgumentException {
		this.fileName = fileName;
		checkValidity();
	}

	/**
	 * Check filename validity
	 * 
	 * @throws IllegalArgumentException,
	 *             if arguments either null or empty except test type.
	 */
	private void checkValidity() throws IllegalArgumentException {
		if (fileName.indexOf(XML_FILE_EXTENSION) == -1) {
			throw new IllegalArgumentException(Messages
					.getString("TestDropXMLCreator.argumentCannotBeNull")); 
		}

	}

	/**
	 * Creates STIF test
	 * 
	 * @param caseElement
	 *            for creating step element
	 * @param testContent
	 *            for creating step content
	 */
	private void createStifTest(CaseElement caseElement,
			TestContentValue testContent) {
		if (testContent.getTestType() != ModuleType.TESTCOMBINER) {
			createInstallStepElement(StepElementContentFactory.TYPE_MODULE_VALUE,
					testContent.getTestBinarySrcPath().lastSegment(), caseElement,
					null);
		}
		else {
			for (int i = 0; testContent.getFilesToIncludeNames() != null
					&& i < testContent.getFilesToIncludeNames().length; i++) {
				Path file = new Path(testContent.getFilesToIncludeNames()[i]);
				if (file.getFileExtension().equals("dll")) {
					createInstallStepElement(StepElementContentFactory.TYPE_MODULE_VALUE,
							file.lastSegment(), caseElement, null);
				}
				else {
					createInstallStepElement(StepElementContentFactory.TYPE_SCRIPT_VALUE,
							file.lastSegment(), caseElement, null);
				}
			}
		}
		
		if (testContent.getTestType() == ModuleType.TESTCLASS
			|| testContent.getTestType() == ModuleType.TESTCOMBINER
			|| (testContent.getTestType() == ModuleType.NORMAL && testContent.getTestScriptSrcPath() != null)) {
			createInstallStepElement(StepElementContentFactory.TYPE_SCRIPT_VALUE,
					testContent.getTestScriptSrcPath().lastSegment(), caseElement,
					null);
		}
		
		if (testContent.getTestType() == ModuleType.TESTCLASS) {
			createRunTestScripterScriptStepElement(testContent.getTestScriptSrcPath().lastSegment(),
					caseElement, null);
		}
		else if (testContent.getTestType() == ModuleType.TESTCOMBINER) {
			createRunTestCombinerScriptStepElement(testContent.getTestScriptSrcPath().lastSegment(),
					caseElement, null);
		}
		else {
			String configPath = null;
			if (testContent.getTestScriptSrcPath() != null) {
				configPath = testContent.getTestScriptSrcPath().lastSegment();
			}
			createRunModuleStepElement(testContent.getTestBinarySrcPath().lastSegment(),
				configPath, caseElement, null);
		}
	}

	/**
	 * Creates install step element
	 * 
	 * @param type
	 *            which type src file is e.g. binary, data
	 * @param src
	 *            src file
	 * @param caseElement
	 *            for creating step element
	 * @param targetElement
	 *            for creating step element
	 */
	private void createInstallStepElement(String type, String src,
			CaseElement caseElement, TargetElement targetElement) {
		new StepElementContentFactory("install", type, src, null, caseElement, 
				targetElement);
	}

	/**
	 * Creates run step element for test class test
	 * 
	 * @param testScriptName
	 *            test script file name
	 * @param caseElement
	 *            for creating step element
	 * @param targetElement
	 *            for creating step element
	 */
	private void createRunTestScripterScriptStepElement(String testScriptName,
			CaseElement caseElement, TargetElement targetElement) {
		new StepElementContentFactory(
				"run-cases", 
				StepElementContentFactory.TEST_SCRIPTER_MODULE_ATTRIBUTE_VALUE,
				testScriptName, null, caseElement, targetElement);
	}
	
	/**
	 * Creates run step element for test combiner test
	 * 
	 * @param testScriptName
	 * 				name of script file
	 * @param caseElement
	 * 				case element
	 * @param targetElement
	 * 				target element
	 */
	private void createRunTestCombinerScriptStepElement(String testScriptName,
			CaseElement caseElement, TargetElement targetElement) {
		new StepElementContentFactory(
				"run-cases", 
				StepElementContentFactory.TEST_COMBINER_MODULE_ATTRIBUTE_VALUE,
				testScriptName, null, caseElement, targetElement);
	}
	
	/**
	 * Creates run step element for hardcoded/normal/stifunit test modules 
	 * 
	 * @param testModuleName
	 * 				name of module
	 * @param configFile
	 * 				name of config file (for normal test module)
	 * @param caseElement
	 * 				case element
	 * @param targetElement
	 * 				target element
	 */
	private void createRunModuleStepElement(String testModuleName,
			String configFile, CaseElement caseElement, TargetElement targetElement) {
		new StepElementContentFactory(
				"run-cases", 
				testModuleName,
				null, configFile, caseElement, targetElement);
	}

	/**
	 * Creates flash element
	 * 
	 * @param testContent
	 *            for creating flash element content
	 * @param parent
	 *            flash element's parent element
	 */
	private void createFlashElement(TestContentValue testContent, Element parent) {
		Iterator<TargetDeviceValue> iteratorTarget = testContent.getTargets()
				.iterator();
		while (iteratorTarget.hasNext()) {
			TargetDeviceValue targetDeviceValue = (TargetDeviceValue) iteratorTarget
					.next();
			if (targetDeviceValue.getImagesInsideDrop() == null) {
				continue;
			}
			Iterator<File> files = targetDeviceValue.getImagesInsideDrop()
					.iterator();
			StringBuffer images = new StringBuffer();

			while (files.hasNext()) {
				File image = (File) files.next();
				images.append(image.toString());
				images.append(IMAGE_SEPARATOR);
			}
			if (images.length() > 0) {
				images.delete(images.length() - 1, images.length());
				new FlashElement(targetDeviceValue.getAlias(), images
						.toString(), parent);
			}

		}
	}

	/**
	 * Saves XML data structure to a file
	 * 
	 * @throws IOException
	 */
	private void saveToFile() throws IOException {

		OutputFormat format = new OutputFormat();
		format.setIndenting(true);

		XMLSerializer serializer = new XMLSerializer(new FileOutputStream(
				new File(fileName)), format);

		serializer.serialize(testRunElement.getDom());

	}

	/**
	 * Creates test drop
	 * 
	 * @param testContent
	 *            contain data for constructs XML -file
	 * @throws IOException
	 */
	public void createTestDrop(TestContentValue testContent)
			throws IllegalArgumentException, IOException, NullPointerException {
		if (testContent == null) {
			throw new IllegalArgumentException(Messages
					.getString("TestDropXMLCreator.testContentCannotBeNull")); 
		}
		if (testRunElement == null) {
			BaseElement.initializeDom();
		}

		if (targetElementFactory == null) {
			targetElementFactory = new TargetElementFactory();
		}

		if (targetElementFactory.getTarget(false) == null
				|| targetElementFactory.getTarget(true) == null) {
			targetElementFactory.createTargets(testContent.getTargets());
		}
		if (testRunElement == null) {
			String testDropName;
			if (testContent.getTestType() != ModuleType.TESTCOMBINER) {
				testDropName = testContent.getProjectName();
			}
			else {
				testDropName = "testcombiner(" + testContent.getTestScriptSrcPath().lastSegment() 
					+ ") ";
			}
			testRunElement = new TestRunElement(testDropName,
					targetElementFactory.getTarget(false));
		}

		if (testPlanElement == null) {
			testPlanElement = new TestPlanElement(PLAN_NAME, testContent
					.getHarness(), null, testRunElement);
		}
		SessionElement sessionElement = new SessionElement(testContent
				.getProjectName()
				+ SESSION_NAME_SUFFIX, testPlanElement, null);
		SetElement setElement = new SetElement(testContent.getProjectName()
				+ SET_NAME_SUFFIX, sessionElement, targetElementFactory
				.getTarget(true));
		createFlashElement(testContent, setElement.getElement());
		CaseElement caseElement = new CaseElement(testContent.getProjectName()
				+ CASE_NAME_SUFFIX, setElement, null);

		if ((testContent.getTestType() == ModuleType.TESTCLASS 
				|| testContent.getTestType() == ModuleType.HARDCODED
				|| testContent.getTestType() == ModuleType.STIFUNIT
				|| testContent.getTestType() == ModuleType.NORMAL
				|| testContent.getTestType() == ModuleType.TESTCOMBINER)
				&& testContent.getHarness().equals(HARNESS_STIF)) {
			createStifTest(caseElement, testContent);
		} else {
			dispose();
			throw new IllegalArgumentException(Messages
					.getString("TestDropXMLCreator.unsupportedType")); 
		}

		TestResultPropertyValue testResultPropertyValue = testContent
				.getTestResultPropertyValue();
		if (testResultPropertyValue != null
				&& testResultPropertyValue.getTestResulPath() != null) {
			createFileActionParameter(testResultPropertyValue
					.getTestResulPath(), testResultPropertyValue
					.getReportType());
		}
		testRunElement.createFilesElement(testContent);

		saveToFile();
	}

	/**
	 * Creates file action parameter for test XML file
	 * 
	 * @param path
	 *            place where results will be found after test ran
	 * @param reportType
	 *            report type
	 */
	public void createFileActionParameter(File path, String reportType) {
		PostActionElement postActionElement = new PostActionElement(
				FILE_STORE_ACTION);
		postActionElement.addParam(new ActionParamElement("to-folder", path 
				.toString()).getElement());
		postActionElement.addParam(new ActionParamElement("report-type", 
				reportType).getElement());
		postActionElement.addParam(new ActionParamElement("report-name", 
				"§RUN_NAME§[§RUN_START_DATE§ §RUN_START_TIME§](§RUN_ID§).html") 
				.getElement());

		testRunElement.getElement().appendChild(postActionElement.getElement());
	}

	/**
	 * Dispose method
	 */
	public void dispose() {
		if (testPlanElement != null) {
			testPlanElement.dispose();
			testPlanElement = null;
		}
		if (testRunElement != null) {
			testRunElement.dispose();
			testRunElement = null;
		}
		if (targetElementFactory != null) {
			targetElementFactory.dispose();
			targetElementFactory = null;
		}
	}
}
