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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.templates.Template;

import com.nokia.carbide.template.engine.ITemplate;
import com.nokia.carbide.templatewizard.process.IParameter;
import com.symbian.tdep.templates.carbide.util.TemplateUtil;

/**
 * A process to customize some template parameters before creating Test Block
 * project from template.
 * 
 * @author Development Tools
 */
public class FillTestProjectTefBlockIntegration extends CommonProcess {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nokia.carbide.templatewizard.process.AbstractProjectProcess#init(com.nokia.carbide.template.engine.ITemplate,
	 *      java.util.List)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void init(ITemplate aTemplate, List<IParameter> aParemeters)
			throws CoreException {
		super.init(aTemplate, aParemeters);

		final ProjectItem lTestProject = (ProjectItem) iTemplateValues
				.get(SetTestableItemsPage.PROJECT);

		// create some new template parameters which need customized process

		// The content buffer of parameter "mmpSourcePath"
		final StringBuffer lMppSourcePathBuffer = new StringBuffer();
		// The content buffer of parameter "mmpSource"
		final StringBuffer lMppSourceBuffer = new StringBuffer();
		// The content buffer of parameter "mmpUserInclude"
		final StringBuffer lMppUserIncludeBuffer = new StringBuffer();
		// The content buffer of parameter "controllerIncludes"
		final StringBuffer lControllerIncludesBuffer = new StringBuffer();
		// The content buffer of parameter "wrapperLIT"
		final StringBuffer lWrapperLITBuffer = new StringBuffer();
		// The content buffer of parameter "wrapperCreating"
		final StringBuffer lWrapperCreatingBuffer = new StringBuffer();

		// This project
		final IProject lProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(getProjectName());
		// Project location
		final File lProjectDir = lProject.getLocation().toFile();
		// Project source location
		final File lSourceFullDir = new File(lProjectDir, iSourceDir);
		// Project include location
		final File lIncFullDir = new File(lProjectDir, iIncDir);

		IPath lGroupPath = new Path(iGroupDir);
		IPath lSourcePath = new Path(iSourceDir);
		IPath lUserInclude = new Path(iIncDir);

		// Target Project
		iTargetProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
				getProjectName());

		// Data directory path
		final IPath lDataPath = iTargetProject.getLocation().append(iDataDir);
		// Script directory path
		final IPath lScriptPath = iTargetProject.getLocation().append(
				iScriptDir);

		// Script file
		final File lScriptFile = new File(lScriptPath.toOSString(), iBassName
				+ ".script");
		// Data file
		final File lDataFile = new File(lDataPath.toOSString(), iBassName
				+ ".ini");

		PrintWriter lScriptFileWriter = null;
		PrintWriter lDataFileWriter = null;
		try {
			// Write script file header
			lScriptFileWriter = new PrintWriter(new BufferedWriter(
					new FileWriter(lScriptFile)));
			lScriptFileWriter.println("// Sample " + iBassName + ".script");
			lScriptFileWriter.println("// "
					+ (String) iTemplateValues.get(COPYRIGHT));
			lScriptFileWriter.println();
			lScriptFileWriter.println("LOAD_SERVER " + iBassName);
			lScriptFileWriter.println();

			lScriptFileWriter
					.println("// Please add or make change of you test cases, here is sample only:");
			lScriptFileWriter.println("START_TESTCASE TEST-SAMPLE");

			// Write data file header
			lDataFileWriter = new PrintWriter(new BufferedWriter(
					new FileWriter(lDataFile)));
			lDataFileWriter.println("// Sample " + iBassName + ".ini");
			lDataFileWriter.println("// "
					+ (String) iTemplateValues.get(COPYRIGHT));
			lDataFileWriter.println();
		} catch (IOException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					FillTestProjectTefBlockIntegration.class.getName(),
					IStatus.ERROR,
					"IOException while writing script or ini file.", e);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}

		lMppSourcePathBuffer.append(genRelativePath(lGroupPath, lSourcePath));
		lMppUserIncludeBuffer.append(genRelativePath(lGroupPath, lUserInclude));

		for (ITestItem lTestClass : lTestProject.getChildren()) {

			String lTestClassWrapper = TemplateUtil.getClassWrapperName(lTestClass.getName());
			
			// Write Wrapper Class Code
			createSourceFile(lSourceFullDir, (ClassItem) lTestClass, lTestClassWrapper);
			createHeaderFile(lIncFullDir, (ClassItem) lTestClass, lTestClassWrapper);
			
			if (lScriptFileWriter != null && lDataFileWriter != null) {
				writeScriptAndIniFile((ClassItem) lTestClass, iBassName, lScriptFileWriter, lDataFileWriter);
			}

			// MMP
			lMppSourceBuffer.append("SOURCE\t\t\t\t" + lTestClassWrapper + ".cpp\r\n");

			// Block Controller
			lControllerIncludesBuffer.append("#include \"" + lTestClassWrapper + ".h\"\r\n");
			// _LIT(KWrapperName, "WrapperName");
			lWrapperLITBuffer.append("_LIT(K" + lTestClassWrapper + ", \"" + lTestClassWrapper + "\");\r\n");
			if (lWrapperCreatingBuffer.length() == 0) {
				lWrapperCreatingBuffer.append("\tif (K" + lTestClassWrapper + "() == aData)\r\n\t\t{\r\n");
			} else {
				lWrapperCreatingBuffer.append("\telse if (K" + lTestClassWrapper + "() == aData)\r\n\t\t{\r\n");
			}
			lWrapperCreatingBuffer.append("\t\twrapper = " + lTestClassWrapper + "::NewL();\r\n\t\t}\r\n");
		}

		// MMP
		iTemplateValues.put("mmpSourcePath", lMppSourcePathBuffer.toString()
				.trim());
		iTemplateValues.put("mmpSource", lMppSourceBuffer.toString());
		iTemplateValues.put("mmpUserInclude", lMppUserIncludeBuffer.toString()
				.trim());
		iTemplateValues.put("mmpSystemInclude", "");
		iTemplateValues.put("mmpLibrary", "");

		// BlockController
		iTemplateValues.put("controllerIncludes", lControllerIncludesBuffer
				.toString());
		iTemplateValues.put("wrapperLIT", lWrapperLITBuffer.toString());
		iTemplateValues.put("wrapperCreating", lWrapperCreatingBuffer
				.toString());

		// iby and inf
		iTemplateValues.put("dataEpocLoc", toEpocLoc((String) iTemplateValues
				.get("dataExportLoc")));
		iTemplateValues.put("scriptEpocLoc", toEpocLoc((String) iTemplateValues
				.get("scriptExportLoc")));

		// Close ini and script files.
		if (lScriptFileWriter != null) {
			lScriptFileWriter.println("END_TESTCASE TEST-SAMPLE");
			lScriptFileWriter.close();
		}
		if (lDataFileWriter != null) {
			lDataFileWriter.close();
		}

		// Create BasenameBlockController.h, BasenameBlockServer.h. 
		createTestBlockFiles(lIncFullDir, lTestProject, TemplateUtil.NAME_BLOCK_CONTROLLER_H);
		createTestBlockFiles(lIncFullDir, lTestProject, TemplateUtil.NAME_BLOCK_SERVER_H);
		
		// Create BasenameBlockControler.cpp, BasenameBlockServer.cpp. 
		createTestBlockFiles(lSourceFullDir, lTestProject, TemplateUtil.NAME_BLOCK_CONTROLLER_CPP);
		createTestBlockFiles(lSourceFullDir, lTestProject, TemplateUtil.NAME_BLOCK_SERVER_CPP);

		// Refresh Workspace
		lProject.refreshLocal(IResource.DEPTH_INFINITE,
				new NullProgressMonitor());
	}

	private void writeScriptAndIniFile(ClassItem aClassItem,
			String aTargetName, PrintWriter scriptFileWriter,
			PrintWriter dataFileWriter) {
		String lWrapperName = TemplateUtil.getClassWrapperName(aClassItem.getName());
		// START_TEST_BLOCK 100 $(baseName) $(dataExportLoc)\$(baseName).ini
		scriptFileWriter.println("\tSTART_TEST_BLOCK 100 " + aTargetName + " "
				+ iDataExportLoc + "\\" + aTargetName + ".ini");
		// CREATE_OBJECT <object type> <object name section>
		scriptFileWriter.println("\t\tCREATE_OBJECT " + lWrapperName + " "
				+ lWrapperName + "Section");

		// [WrapperNameSection]
		// name = WrapperNameInstanceName
		dataFileWriter.println("[" + lWrapperName + "Section]");
		dataFileWriter.println("name = " + lWrapperName + "InstanceName");

		boolean lHasAsync = false;
		for (MethodItem lMethod : aClassItem.getChildren()) {
			if (lMethod.isSelected()) {
				scriptFileWriter.print("\t\tCOMMAND " + lWrapperName
						+ "Section " + lMethod.getNormalisedName());
				if (lMethod.hasParameter()) {
					scriptFileWriter.println(" " + lWrapperName + "Section");
				} else {
					scriptFileWriter.println();
				}
				if (lMethod.isAsync()) {
					lHasAsync = true;
				}
				List<String[]> lParameterList = lMethod.getParameters();
				for (int i = 0; i < lParameterList.size(); i++) {
					String lName = lParameterList.get(i)[MethodItem.NAME];
					dataFileWriter.println(lMethod.getName() + "_" + lName
							+ " = // Please set value");
				}
			}
		}
		// Asynchronous
		if (lHasAsync) {
			scriptFileWriter.println("\t\tOUTSTANDING");
		}
		// END_TEST_BLOCK
		scriptFileWriter.println("\tEND_TEST_BLOCK");
		dataFileWriter.println();
	}

	/**
	 * Create source files of TEFUnit project
	 * 
	 * @param aSourceDirectory
	 * @param lClassEntry
	 * @param lCppName
	 * @throws CoreException
	 */
	private void createSourceFile(final File aSourceDirectory,
			ClassItem aClassWrapper, String aClassItem) throws CoreException {
		String lCppName = aClassItem + ".cpp";
		PrintWriter lClassCppWriter = null;
		try {

			File lClassCppCode = new File(aSourceDirectory, lCppName);
			lClassCppWriter = new PrintWriter(new BufferedWriter(
					new FileWriter(lClassCppCode)));
			// Copyright
			lClassCppWriter.println("/**");
			lClassCppWriter.println(" * @file " + lCppName);
			lClassCppWriter.println(" * @internalTechnology");
			lClassCppWriter.println(" * @author " + (String) iTemplateValues.get(AUTHOR));
			lClassCppWriter.println(" *");
			lClassCppWriter.println(" * "
					+ (String) iTemplateValues.get(COPYRIGHT));
			lClassCppWriter.println(" *");
			lClassCppWriter.println(" */");
			lClassCppWriter.println();

			lClassCppWriter.println("#include \"" + aClassItem + ".h\"");

			lClassCppWriter.println();
			lClassCppWriter
					.println("// Commands – the definition of names of supported command");

			for (MethodItem lMethod : aClassWrapper.getChildren()) {
				lClassCppWriter.println("_LIT(K" + lMethod.getName() + ", \""
						+ lMethod.getName() + "\");");
			}
			lClassCppWriter.println();

			// Constructor
			lClassCppWriter.println(aClassItem + "::" + aClassItem
					+ "():iObject(NULL)");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// Destructor
			lClassCppWriter.println(aClassItem + "::~" + aClassItem + "()");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\tdelete iObject;");
			lClassCppWriter.println("\tiObject = NULL;");
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// NewL
			lClassCppWriter.println(aClassItem + "*" + aClassItem + "::"
					+ "NewL()");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\t" + aClassItem + "* ret = new (ELeave) "
					+ aClassItem + "();");
			lClassCppWriter.println("\tCleanupStack::PushL(ret);");
			lClassCppWriter.println("\tret->ConstructL();");
			lClassCppWriter.println("\tCleanupStack::Pop(ret);");
			lClassCppWriter.println("\treturn ret;");
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// ConstructL
			lClassCppWriter.println("void " + aClassItem + "::"
					+ "ConstructL()");
			lClassCppWriter.println("\t{");
			lClassCppWriter
					.println("\tiActiveCallback = CActiveCallback::NewL(*this);");
			lClassCppWriter.println("\t// TODO : Initialize wrppped object");
			lClassCppWriter.println();
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// RunL
			lClassCppWriter.println("void " + aClassItem
					+ "::RunL(CActive* aActive, TInt aIndex)");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\tTInt err = aActive->iStatus.Int();");
			lClassCppWriter.println("\tSetAsyncError(aIndex, err);");
			lClassCppWriter.println("\tDecOutstanding();");
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// DoCommandL
			lClassCppWriter
					.println("TBool "
							+ aClassItem
							+ "::DoCommandL(const TTEFFunction& aCommand, const TTEFSectionName& aSection, const TInt aAsyncErrorIndex)");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\tTBool ret = ETrue;");
			lClassCppWriter.println();

			lClassCppWriter.print("\t");
			for (MethodItem lMethod : aClassWrapper.getChildren()) {
				MethodItem lMethodItem = (MethodItem) lMethod;
				lClassCppWriter.println("if (K" + lMethodItem.getName()
						+ "() == aCommand)");
				lClassCppWriter.println("\t\t{");
				lClassCppWriter.println("\t\t// Testing "
						+ aClassWrapper.getName() + "::"
						+ lMethodItem.getName().replace(" (", "("));

				lClassCppWriter.print("\t\t" + TemplateUtil.getMethodWrapperName(lMethodItem.getName()) + "(");
				if (lMethodItem.hasParameter() && lMethodItem.isAsync()) {
					lClassCppWriter.print("aSection, aAsyncErrorIndex");
				} else {
					if (lMethodItem.hasParameter()) {
						lClassCppWriter.print("aSection");
					}
					if (lMethodItem.isAsync()) {
						lClassCppWriter.print("aAsyncErrorIndex");
					}
				}
				lClassCppWriter.println(");");

				lClassCppWriter.println("\t\t}");
				lClassCppWriter.print("\telse ");
			}
			lClassCppWriter.println();
			lClassCppWriter.println("\t\t{");
			lClassCppWriter.println("\t\tret = EFalse;");
			lClassCppWriter.println("\t\t}");
			lClassCppWriter.println("\treturn ret;");
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// DoCmdXXXX
			for (MethodItem lMethod : aClassWrapper.getChildren()) {
				MethodItem lMethodItem = (MethodItem) lMethod;
				lClassCppWriter.println("// Testing " + aClassWrapper.getName()
						+ "::" + lMethodItem.getName().replace(" (", "("));
				lClassCppWriter.print("void " + aClassItem + "::"
						+ TemplateUtil.getMethodWrapperName(lMethodItem.getName()) + "(");
				if (lMethodItem.hasParameter() && lMethodItem.isAsync()) {
					lClassCppWriter
							.print("const TTEFSectionName& aSection, const TInt aAsyncErrorIndex");
				} else {
					if (lMethodItem.hasParameter()) {
						lClassCppWriter
								.print("const TTEFSectionName& aSection");
					}
					if (lMethodItem.isAsync()) {
						lClassCppWriter.print("const TInt aAsyncErrorIndex");
					}
				}
				lClassCppWriter.println(")");
				lClassCppWriter.println("\t{");
				if (lMethod.isAsync()) {
					lClassCppWriter
							.println("\t// This is an asynchronous method under testing.");
					lClassCppWriter.println();
				}
				lClassCppWriter.println("\tINFO_PRINTF1(_L(\"Running "
						+ lMethodItem.getName() + "()\"));");
				lClassCppWriter.println("\t// TODO : Add a method test here");
				lClassCppWriter.println("\t// iObject->"
						+ lMethodItem.getName().replace(" (", "("));
				lClassCppWriter.println();
				if (lMethod.isAsync()) {
					lClassCppWriter
							.println("\tiActiveCallback->Activate(aAsyncErrorIndex);");
					lClassCppWriter.println("\tIncOutstanding();");
					lClassCppWriter.println();
				}
				lClassCppWriter.println("\t// TODO : Please make changes for checking your test result");
				lClassCppWriter.println("\tSetBlockResult(EFail);");
				lClassCppWriter.println("\t}");
				lClassCppWriter.println();
			}

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
	 * 
	 * @param aIncDirectory
	 * @param lClassEntry
	 * @param lClassItem
	 * @param lHeaderName
	 * @throws CoreException
	 */
	private void createHeaderFile(final File aIncDirectory,
			ClassItem aClassWrapper, String lClassItem) throws CoreException {
		String lHeaderName = lClassItem + ".h";
		PrintWriter lClassHeaderWriter = null;
		try {

			File lClassHeaderCode = new File(aIncDirectory, lHeaderName);
			lClassHeaderWriter = new PrintWriter(new BufferedWriter(
					new FileWriter(lClassHeaderCode)));
			// Copyright
			lClassHeaderWriter.println("/**");
			lClassHeaderWriter.println(" * @file " + lHeaderName);
			lClassHeaderWriter.println(" * @internalTechnology");
			lClassHeaderWriter.println(" * @author " + (String) iTemplateValues.get(AUTHOR));
			lClassHeaderWriter.println(" *");
			lClassHeaderWriter.println(" * "
					+ (String) iTemplateValues.get(COPYRIGHT));
			lClassHeaderWriter.println(" *");
			lClassHeaderWriter.println(" */");
			lClassHeaderWriter.println();

			lClassHeaderWriter.println("#ifndef __" + lClassItem.toUpperCase()
					+ "_H__");
			lClassHeaderWriter.println("#define __" + lClassItem.toUpperCase()
					+ "_H__");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("#include <DataWrapper.h>");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("class " + lClassItem
					+ " : public CDataWrapper");
			lClassHeaderWriter.println("\t{");
			lClassHeaderWriter.println("public:");
			lClassHeaderWriter.println("\t// Default Constructor");
			lClassHeaderWriter.println("\t" + lClassItem + "();");
			lClassHeaderWriter.println("\t// Default Destructor");
			lClassHeaderWriter.println("\t~" + lClassItem + "();");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("\t// Two-Phase Constructor");
			lClassHeaderWriter.println("\tstatic " + lClassItem + "* NewL();");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("\t// Command Dispatcher");
			lClassHeaderWriter
					.println("\tvirtual TBool DoCommandL(const TTEFFunction& aCommand, const TTEFSectionName& aSection, const TInt aAsyncErrorIndex);");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("\t// Getter for the wrapped object");
			lClassHeaderWriter
					.println("\tvirtual TAny* GetObject() { return iObject; }");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("\t// Setter for the wrapped object");
			lClassHeaderWriter
					.println("\tinline virtual void SetObjectL(TAny* aObject)");
			lClassHeaderWriter.println("\t\t{");
			lClassHeaderWriter.println("\t\tdelete iObject;");
			lClassHeaderWriter.println("\t\tiObject = NULL;");
			lClassHeaderWriter.println("\t\tiObject = aObject;");
			lClassHeaderWriter.println("\t\t}");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("\t// Asynchronous Run");
			lClassHeaderWriter
					.println("\tvoid RunL(CActive* aActive, TInt aIndex);");
			lClassHeaderWriter.println();

			lClassHeaderWriter.println("protected:");
			lClassHeaderWriter.println("\tvoid ConstructL();");
			lClassHeaderWriter.println();

			lClassHeaderWriter.println("private:");

			for (MethodItem lMethod : aClassWrapper.getChildren()) {
				MethodItem lMethodItem = (MethodItem) lMethod;
				lClassHeaderWriter.println("\t// Testing "
						+ aClassWrapper.getName() + "::"
						+ lMethodItem.getName().replace(" (", "("));

				lClassHeaderWriter.print("\tinline void "
						+ TemplateUtil.getMethodWrapperName(lMethodItem.getName()) + "(");
				if (lMethodItem.hasParameter() && lMethodItem.isAsync()) {
					lClassHeaderWriter
							.print("const TTEFSectionName& aSection, const TInt aAsyncErrorIndex");
				} else {
					if (lMethodItem.hasParameter()) {
						lClassHeaderWriter
								.print("const TTEFSectionName& aSection");
					}
					if (lMethodItem.isAsync()) {
						lClassHeaderWriter.print("const TInt aAsyncErrorIndex");
					}
				}
				lClassHeaderWriter.println(");");

				lClassHeaderWriter.println();
			}

			lClassHeaderWriter.println();
			lClassHeaderWriter.println("private:");
			lClassHeaderWriter.println("\tCActiveCallback* iActiveCallback;");
			lClassHeaderWriter.println("\tTAny* iObject;");
			lClassHeaderWriter.println("\t};");
			lClassHeaderWriter.println("#endif // __"
					+ lClassItem.toUpperCase() + "_H__");

		} catch (IOException lIOException) {
			fail("Could not create source code: " + lHeaderName, lIOException);
		} finally {
			if (lClassHeaderWriter != null) {
				lClassHeaderWriter.flush();
				lClassHeaderWriter.close();
			}
		}
	}
	
	/**
	 * create TestBlock Headers and Sources from Template.
	 * 
	 * @param aDirectory
	 * @param aClassWrapper
	 * @param lClassItem
	 * @param lTemplateName
	 * @throws CoreException
	 */
	private void createTestBlockFiles(final File aDirectory, ProjectItem projectItem, String lTemplateName) throws CoreException {
		
		Template template = TemplateUtil.findTemplate(lTemplateName);

		String name = replaceWithVariables(template.getDescription());
		String content = replaceWithVariables(template.getPattern());
		
		content = replaceWithIncludes(content, projectItem);

		File file = new File(aDirectory, name);
		PrintWriter writer = null;
		try {
			file.getParentFile().mkdirs();
			writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			writer.append(content);
		} catch (IOException lIOException) {
			fail("Could not create code: " + file.getName(), lIOException);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
	
	/**
	 * Replace patterns with environment variables. 
	 * 
	 * @param pattern
	 * @param aClassWrapper
	 * @return
	 */
	private String replaceWithVariables(String pattern){
		return pattern
			.replace("${baseName}", (String) iTemplateValues.get(BASE_NAME))
			.replace("${baseNameUpper}", (String) iTemplateValues.get("baseNameUpper"))
			.replace("${baseNameLower}", (String) iTemplateValues.get("baseNameLower"))
			.replace("${user}", System.getenv("USERNAME"))
			.replace("${author}", (String) iTemplateValues.get("author"))
			.replace("${copyright}",(String) iTemplateValues.get(COPYRIGHT));
	}
	
	private String replaceWithIncludes(String pattern, ProjectItem project){
		
		StringBuffer include = new StringBuffer();
		StringBuffer _lit = new StringBuffer();
		StringBuffer creating = new StringBuffer();
		
		int i = 0;
		for(ClassItem aClassItem : project.getChildren()){
			
			if(i != 0){
				include.append("\r\n");
				_lit.append("\r\n");
				creating.append("\telse ");
			}
			
			String name = aClassItem.getName();
			String wrapperName = TemplateUtil.getClassWrapperName(name);
			
			include.append("#include \"" + wrapperName + ".h\"");
			_lit.append("_LIT(K" + wrapperName + ", \"" + wrapperName + "\");");
			creating.append("if (K" + wrapperName + "() == aData)\r\n\t\t{\r\n\t\twrapper = " + wrapperName + "::NewL();\r\n\t\t}\r\n");
			
			i++;
		}
		
		return pattern
			.replace("${controllerIncludes}", include)
			.replace("${wrapperLIT}", _lit)
			.replace("${wrapperCreating}", "\t" + creating);
	}
}
