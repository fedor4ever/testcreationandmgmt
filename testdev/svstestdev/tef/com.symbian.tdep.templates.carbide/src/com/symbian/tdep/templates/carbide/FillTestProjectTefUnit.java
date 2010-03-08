/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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



package com.symbian.tdep.templates.carbide;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Plugin;

import com.nokia.carbide.template.engine.ITemplate;
import com.nokia.carbide.templatewizard.process.AbstractProjectProcess;
import com.nokia.carbide.templatewizard.process.IParameter;

/**
 * A process to customize some template parameters before creating 
 * TEFUnit project from template.  
 * 
 * @author Development Tools
 */
public class FillTestProjectTefUnit extends AbstractProjectProcess {

	// The name of template parameter
	private static final String INC_DIR = "incDir";

	// The name of template parameter
	private static final String SOURCE_DIR = "sourceDir";

	// The default prefix of generated files
	private static final String TEST = "Test_";

	/* (non-Javadoc)
	 * @see com.nokia.carbide.templatewizard.process.AbstractProcess#getPlugin()
	 */
	@Override
	protected Plugin getPlugin() {
		return TefTemplatesCarbidePlugin.getDefault();
	}
	
	
	/* (non-Javadoc)
	 * @see com.nokia.carbide.templatewizard.process.AbstractProjectProcess#init(com.nokia.carbide.template.engine.ITemplate, java.util.List)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void init(ITemplate aTemplate, List<IParameter> aParemeters) throws CoreException {
		super.init(aTemplate, aParemeters);
		
		// Get Properties
		final Map<String, Object> lTemplateValues = aTemplate.getTemplateValues();
		final Set<ProjectWrapper> lProjects = (Set<ProjectWrapper>) lTemplateValues.get(GetTestableItemsPage.PROJECTS);
		final String lSourceDir = (String) lTemplateValues.get(SOURCE_DIR);
		final String lIncDir = (String) lTemplateValues.get(INC_DIR);
		
		// create some new template parameters which need customized process 
		
		// The content buffer of parameter "mmpSource"
		final StringBuffer lMppSourceBuffer = new StringBuffer();
		// The content buffer of parameter "serverIncludes"
		final StringBuffer lServerIncludesBuffer = new StringBuffer();
		// The content buffer of parameter "serverSuiteSwitch"
		final StringBuffer lServerSuiteBuffer = new StringBuffer();
		// The content buffer of parameter "serverSuiteSwitch"
		final StringBuffer lServerSuitePackageBuffer = new StringBuffer();
		// The content buffer of parameter "scriptTestCases"
		final StringBuffer lScriptBuffer = new StringBuffer();

		final IProject lProject = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
		final File lProjectDir = lProject.getLocation().toFile();
		final File lSourceFullDir = new File(lProjectDir, lSourceDir);
		final File lIncFullDir = new File(lProjectDir, lIncDir);
		
		// Write TEF C++ Source Code
		lServerSuitePackageBuffer.append("\tSTART_SUITE;\r\n");
		
		for(ProjectWrapper lProjectWrapper : lProjects) {
			if (lProjectWrapper.isSelected()) {
				
				final String projName = lProjectWrapper.getName(); 
				final File lSourceDirUnitTest = new File(lSourceFullDir, projName);
				final File lIncDirUnitTest = new File(lIncFullDir, projName);
				
				if (!lSourceDirUnitTest.mkdirs()) {
					// Error
				}
				if (!lIncDirUnitTest.mkdirs()) {
					// Error
				}
				
				lMppSourceBuffer.append("\r\nSOURCEPATH\t\t\t../" + lSourceDir + "/" + projName + "\r\n");
				lServerSuitePackageBuffer.append("\tADD_TEST_SUITE(" + projName + ");\r\n");
				lServerSuiteBuffer.append("\tSTART_SUITE;\r\n");
				
				for (ClassWrapper lClassWrapper : lProjectWrapper.getChildren()) {
					if (lClassWrapper.isSelected()) {
						String lTestClass = TEST + lClassWrapper.getName();
						
						// Write TEF Code
						createSourceFile(lSourceDirUnitTest, lClassWrapper, lTestClass);
						createHeaderFile(lIncDirUnitTest, lClassWrapper, lTestClass);
						
						// MMP
						lMppSourceBuffer.append("SOURCE\t\t\t\t" + (lTestClass +"TestSuite").toLowerCase()+ ".cpp\r\n");
						
						// Server
						lServerIncludesBuffer.append("#include \"" + projName + "/" + lTestClass.toLowerCase() + "testsuite.h\"\r\n");
						lServerSuiteBuffer.append("\tADD_TEST_SUITE(C" + lTestClass +"TestSuite"+ ");\r\n");
						
						// Script
						lScriptBuffer.append("RUN_TEST_STEP 100 " + "TestServer" + " TEFUnit." + lProjectWrapper.getName() + ".C" + lTestClass + ".*\r\n");
					}
				}
				

				lServerSuiteBuffer.append("\tEND_SUITE;\r\n\r\n");
			}
		}
		
		// MMP
		lTemplateValues.put("mmpSource", lMppSourceBuffer.toString());
		
		// Driver
		lTemplateValues.put("driverProjectUnderTest", "Blah");
		
		// Server
		lServerSuitePackageBuffer.append("\tEND_SUITE;\r\n\r\n");
		lTemplateValues.put("serverIncludes", lServerIncludesBuffer.toString());
		lTemplateValues.put("serverSuiteSwitch", /*lServerSuitePackageBuffer.toString() +*/ lServerSuiteBuffer.toString());
		
		// Script
		lTemplateValues.put("scriptTestCases", lScriptBuffer.toString());
		
		lTemplateValues.put("dataEpocLoc", toEpocLoc((String) lTemplateValues.get("dataExportLoc"))); 
		lTemplateValues.put("scriptEpocLoc", toEpocLoc((String) lTemplateValues.get("scriptExportLoc"))); 
		
		// Misc
		String baseNameCapital = (String)lTemplateValues.get("baseName");
		baseNameCapital = baseNameCapital.substring(0, 1).toUpperCase().concat(baseNameCapital.substring(1));
		lTemplateValues.put("baseNameCapital",baseNameCapital);
		// Refresh Workspace
		lProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
	}


	/**
	 * Create source files of TEFUnit project
	 * @param aSourceDirectory
	 * @param lClassEntry
	 * @param lCppName
	 * @throws CoreException
	 */
	private void createSourceFile(final File aSourceDirectory, 
			ClassWrapper aClassWrapper, String aTestClass) throws CoreException {
		String lCppName = aTestClass.toLowerCase()+ "testsuite.cpp";
		String projName = aClassWrapper.getParent().getName();
		PrintWriter lClassCppWriter = null;
		try {
			
			File lClassCppCode = new File(aSourceDirectory, lCppName);
			lClassCppWriter = new PrintWriter(new BufferedWriter(new FileWriter(lClassCppCode)));
			
			lClassCppWriter.println("#include \"" + projName + "/" + (aTestClass + "TestSuite.h\"").toLowerCase());
			
			lClassCppWriter.println();
			lClassCppWriter.println("void C" + aTestClass + "TestSuite::SetupL()");
			lClassCppWriter.println("/**");
			lClassCppWriter.println(" * SetupL");
			lClassCppWriter.println(" *");
			lClassCppWriter.println(" */");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\t//TODO:Add some setup code here");
			lClassCppWriter.println("\t}");
			
			lClassCppWriter.println();
			lClassCppWriter.println("void C" + aTestClass + "TestSuite::TearDownL()");
			lClassCppWriter.println("/**");
			lClassCppWriter.println(" * TearDownL");
			lClassCppWriter.println(" *");
			lClassCppWriter.println(" */");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\t//TODO:Add some teardown code here");
			lClassCppWriter.println("\t}");
			
			for (MethodWrapper lMethod : aClassWrapper.getChildren()) {
				if (lMethod.isSelected()) {
					String lName = lMethod.getNormalisedName();
					lClassCppWriter.println();
					lClassCppWriter.println("void C" + aTestClass + "TestSuite::" + TEST + lName + "()");
					lClassCppWriter.println("/**");
					lClassCppWriter.println(" * " + aTestClass);
					lClassCppWriter.println(" *");
					lClassCppWriter.println(" */");
					lClassCppWriter.println("\t{");
					lClassCppWriter.println("\t//TODO:Add a unit test here");
					lClassCppWriter.println("\tINFO_PRINTF1(_L(\"Running Suite:" + aTestClass + ", Test: " + lName + " \"));");
					lClassCppWriter.println("\tASSERT_TRUE(false);");
					lClassCppWriter.println("\t}");
				}
			}
			
			lClassCppWriter.println();
			lClassCppWriter.println("CTestSuite* C" + aTestClass + "TestSuite::CreateSuiteL( const TDesC& aName )");
			lClassCppWriter.println("\t{");
			
			lClassCppWriter.println("\tSUB_SUITE;");
			for (MethodWrapper lMethod : aClassWrapper.getChildren()) {
				if (lMethod.isSelected()) {
					lClassCppWriter.println("\tADD_TEST_STEP(" + TEST + lMethod.getNormalisedName() + ");");
				}
			}
			lClassCppWriter.println("\tEND_SUITE;");
			lClassCppWriter.println("\t}");
			
		} catch (IOException lIOException) {
			fail("Could not create source code: " + lCppName, lIOException);
		} finally {
			if (lClassCppWriter != null) {
				lClassCppWriter.flush();
				lClassCppWriter.close();
			}
		}
	}


	/**
	 * Create header files of TEFUnit project
	 * @param aIncDirectory
	 * @param lClassEntry
	 * @param lTestClass
	 * @param lHeaderName
	 * @throws CoreException
	 */
	private void createHeaderFile(final File aIncDirectory, ClassWrapper aClassWrapper, 
			String lTestClass) throws CoreException {
		String lHeaderName = lTestClass + "testsuite.h";
		lHeaderName = lHeaderName.toLowerCase();
		PrintWriter lClassHeaderWriter = null;
		try {
			
			File lClassHeaderCode = new File(aIncDirectory, lHeaderName);
			lClassHeaderWriter = new PrintWriter(new BufferedWriter(new FileWriter(lClassHeaderCode)));
			
			lClassHeaderWriter.println("#ifndef __" + lTestClass.toUpperCase() + "TEST_SUITE__");
			lClassHeaderWriter.println("#define __" + lTestClass.toUpperCase() + "TEST_SUITE__");
			lClassHeaderWriter.println("//Only one TEFUnit header file will ever be required");
			lClassHeaderWriter.println("#include <tefunit.h>");
//			lClassHeaderWriter.println("#include \"" + aClassWrapper.getHeaderName() + "\"");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("class C" + lTestClass + "TestSuite : public CTestFixture");
			lClassHeaderWriter.println("\t{");
			lClassHeaderWriter.println("public:");
			lClassHeaderWriter.println("\t//TODO:SetUp and TearDown code");
			lClassHeaderWriter.println("\tvirtual void SetupL();");
			lClassHeaderWriter.println("\tvirtual void TearDownL();");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("\t//Tests can be as many as you require");
			
			for (MethodWrapper lMethodWrapper : aClassWrapper.getChildren()) {
				if (lMethodWrapper.isSelected()) {
					lClassHeaderWriter.println("\tvoid " + TEST + lMethodWrapper.getNormalisedName() + "();");
				}
			}
			
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("\t//Create a suite of all the tests");
			lClassHeaderWriter.println("\tstatic CTestSuite* CreateSuiteL(const TDesC& aName);");
			lClassHeaderWriter.println("\t};");
			lClassHeaderWriter.println("#endif // __" + lTestClass.toUpperCase() + "TEST_SUITE__");
			
		} catch (IOException lIOException) {
			fail("Could not create source code: " + lHeaderName, lIOException);
		} finally {
			if (lClassHeaderWriter != null) {
				lClassHeaderWriter.flush();
				lClassHeaderWriter.close();
			}
		}
	}

	/*(non-Javadoc)
	 * Convert absolute path on device to the path relative to EPOCROOT
	 * @param loc the absolute path on device
	 * @return
	 */
	private static final String toEpocLoc(String loc) {
		// ^c\:\\([0-9a-zA-Z]+\\)*[0-9a-zA-Z]+$
		String ret = loc.substring(2);
		return ret;
	}

}
