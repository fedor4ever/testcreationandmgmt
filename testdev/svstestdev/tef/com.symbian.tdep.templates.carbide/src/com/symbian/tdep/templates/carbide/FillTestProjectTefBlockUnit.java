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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.templates.Template;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.carbide.cpp.internal.project.ui.ProjectUIPlugin;
import com.nokia.carbide.template.engine.ITemplate;
import com.nokia.carbide.templatewizard.process.IParameter;
import com.symbian.tdep.templates.carbide.util.TemplateUtil;

/**
 * A process to customize some template parameters before creating Test Block
 * project from template.
 * 
 * @author Development Tools
 */
public class FillTestProjectTefBlockUnit extends CommonProcess {

	// The name of template parameter
	private static final String ABORT = "Abort";

	// The name of template parameter
	private static final String OVERWRITE = "Overwrite";

	// The content buffer of parameter "mmpSystemInclude"
	private StringBuffer iMppSystemIncludeBuffer = null;

	// The content buffer of parameter "mmpLibrary"
	private StringBuffer iMppLibraryBuffer = null;

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

		if (iTemplateValues.get(ABORT) != null) {
			return;
		}

		// Disable Add Files Listener, revert in RestartResourceListener
		TefTemplatesCarbidePlugin.setAddFilesToProjectOption(ProjectUIPlugin
				.getAddFilesToProjectOption());
		ProjectUIPlugin.setAddFilesToProjectOption(2);

		final Set<ProjectItem> lProjects = (Set<ProjectItem>) iTemplateValues
				.get(GetTestableItemsPage.PROJECTS);

		// create some new template parameters which need customized process
		// The content buffer of parameter "mmpSourcePath"
		final StringBuffer lMppSourcePathBuffer = new StringBuffer();
		// The content buffer of parameter "mmpUserInclude"
		final StringBuffer lMppUserIncludeBuffer = new StringBuffer();
		// The content buffer of parameter "mmpSource"
		final StringBuffer lMppSourceBuffer = new StringBuffer();
		// The content buffer of parameter "mmpSource"
		final StringBuffer lMppTestedSourceBuffer = new StringBuffer();
		// The content buffer of parameter "controllerIncludes"
		final StringBuffer lControllerIncludesBuffer = new StringBuffer();
		// The content buffer of parameter "wrapperLIT"
		final StringBuffer lWrapperLITBuffer = new StringBuffer();
		// The content buffer of parameter "wrapperCreating"
		final StringBuffer lWrapperCreatingBuffer = new StringBuffer();

		// Target Project
		iTargetProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
				getProjectName());
		final IPath lProjectPath = iTargetProject.getLocation();

		ICarbideProjectInfo icarbideprojectinfo = CarbideBuilderPlugin
				.getBuildManager().getProjectInfo(iTargetProject);

		final IPath iSDKIncPath = icarbideprojectinfo.getDefaultConfiguration()
				.getSDK().getIncludePath();

		final IPath lTestIncludePath = lProjectPath.append(iTestIncDir);
		final IPath lTestSourcePath = lProjectPath.append(iTestSourceDir);
		final IPath lTestDataPath = lProjectPath.append(iTestDataDir);
		final IPath lTestScriptPath = lProjectPath.append(iTestScriptDir);
		final IPath lTestGroupPath = new Path(iTestGroupDir);

		final File lScriptFile = new File(lTestScriptPath.toOSString(),
				iTargetName + ".script");
		final File lDataFile = new File(lTestDataPath.toOSString(), iTargetName
				+ ".ini");

		PrintWriter lScriptFileWriter = null;
		PrintWriter lDataFileWriter = null;

		// Script
		if (!lScriptFile.exists() || iTemplateValues.get(OVERWRITE) != null) {
			try {
				lScriptFileWriter = new PrintWriter(new BufferedWriter(
						new FileWriter(lScriptFile)));
				lScriptFileWriter.println("// Sample " + iTargetName
						+ ".script");
				lScriptFileWriter.println("// "
						+ (String) iTemplateValues.get(COPYRIGHT));
				lScriptFileWriter.println();
				lScriptFileWriter.println("LOAD_SERVER " + iTargetName);
				lScriptFileWriter.println();

				lScriptFileWriter
						.println("// Please add or make change of you test cases, here is sample only:");
				lScriptFileWriter.println("START_TESTCASE TEST-SAMPLE");
			} catch (IOException e) {
				IStatus lStatus = new Status(IStatus.ERROR,
						FillTestProjectTefBlockUnit.class.getName(),
						IStatus.ERROR,
						"IOException while writing script file.", e);
				TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
			}
		}

		// Ini
		if (!lDataFile.exists() || iTemplateValues.get(OVERWRITE) != null) {
			try {
				lDataFileWriter = new PrintWriter(new BufferedWriter(
						new FileWriter(lDataFile)));
				lDataFileWriter.println("// Sample " + iTargetName + ".ini");
				lDataFileWriter.println("// "
						+ (String) iTemplateValues.get(COPYRIGHT));
				lDataFileWriter.println();
			} catch (IOException e) {
				IStatus lStatus = new Status(IStatus.ERROR,
						FillTestProjectTefBlockUnit.class.getName(),
						IStatus.ERROR, "IOException while writing ini file.", e);
				TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
			}
		}

		ICarbideProjectInfo carbideprojectinfo = CarbideBuilderPlugin
				.getBuildManager().getProjectInfo(iTargetProject);
		IPath lBldInfPath = carbideprojectinfo.getAbsoluteBldInfPath();

		Set<String> lUserIncludeSet = new HashSet<String>();
		Set<String> lSystemIncludeSet = new HashSet<String>();
		Set<String> lLibrarySet = new HashSet<String>();

		if (lBldInfPath.toFile().exists()) {
			// Get Test mmp relative path
			String lTestMmp = genRelativePath(lBldInfPath.setDevice(null)
					.removeFirstSegments(
							lBldInfPath.setDevice(null).matchingFirstSegments(
									lProjectPath.setDevice(null)))
					.removeLastSegments(1), lTestGroupPath.append(iTargetName
					+ ".mmp"));
			// Update Bld.inf
			Utility.addNewTestMMP(lBldInfPath.toFile(), lTestMmp);

			// Generate Relative Source Path
			lMppSourcePathBuffer.append(genRelativePath(lTestGroupPath,
					new Path(iTestSourceDir)));

			// collect includes and librarys information
			List<IPath> lMMPFiles = Utility.getMMPFiles(lBldInfPath);
			for (IPath mmpFile : lMMPFiles) {
				Map map = Utility.catchIncludeFromMMP(mmpFile, iTargetProject);
				lUserIncludeSet.addAll((Collection<? extends String>) map
						.get(Utility.MMP_USERINCLUDE));
				lSystemIncludeSet.addAll((Collection<? extends String>) map
						.get(Utility.MMP_SYSTEMINCLUDE));
				lLibrarySet.addAll((Collection<? extends String>) map
						.get(Utility.MMP_LIBRARY));
			}
		}

		Map<String, Set<String>> lTestedSourceMap = new HashMap<String, Set<String>>();
		for (ProjectItem lProjectItem : lProjects) {
			if (lProjectItem.isSelected()) {
				if (lProjectItem.getLib() != null) {
					lLibrarySet.add(lProjectItem.getLib());
				}
				for (ClassItem lClassItem : lProjectItem.getChildren()) {
					if (lClassItem.isSelected()) {
						String lTestClassWrapper = TemplateUtil.getClassWrapperName(lClassItem.getName());

						// Write Wrapper Class Code
						createSourceFile(lTestSourcePath, lClassItem, lTestClassWrapper);
						createHeaderFile(lTestIncludePath, lClassItem, lTestClassWrapper);
						writeScriptAndIniFile(lClassItem, iTargetName, lScriptFileWriter, lDataFileWriter);
						
						// MMP
						lMppSourceBuffer.append("SOURCE\t\t\t\t"
								+ lTestClassWrapper + ".cpp\r\n");
						// lMppTestedSourceBuffer
						IPath[] lImplFiles = lClassItem.getImplLocation();
						for (IPath lPath : lImplFiles) {
							if (lPath.matchingFirstSegments(iSDKIncPath) == iSDKIncPath
									.segmentCount()) {
								continue;
							}
							String lFileName = lPath.lastSegment();
							lPath = lPath.removeLastSegments(1).setDevice(null);
							lPath = lPath.removeFirstSegments(lPath
									.matchingFirstSegments(lProjectPath
											.setDevice(null)));
							String lDir = genRelativePath(lTestGroupPath, lPath).trim();
							Set<String> lSourceSet = lTestedSourceMap.get(lDir);
							if (lSourceSet == null) {
								lSourceSet = new HashSet<String>();
							}
							lSourceSet.add(lFileName);
							lTestedSourceMap.put(lDir, lSourceSet);
						}

						// Block Controller
						lControllerIncludesBuffer.append("#include \""
								+ lTestClassWrapper + ".h\"\r\n");

						// _LIT(KWrapperName, "WrapperName");
						lWrapperLITBuffer.append("_LIT(K" + lTestClassWrapper
								+ ", \"" + lTestClassWrapper + "\");\r\n");
						if (lWrapperCreatingBuffer.length() == 0) {
							lWrapperCreatingBuffer.append("\tif (K"
									+ lTestClassWrapper
									+ "() == aData)\r\n\t\t{\r\n");
						} else {
							lWrapperCreatingBuffer.append("\telse if (K"
									+ lTestClassWrapper
									+ "() == aData)\r\n\t\t{\r\n");
						}
						lWrapperCreatingBuffer.append("\t\twrapper = "
								+ lTestClassWrapper + "::NewL();\r\n\t\t}\r\n");
					}
				}
				

				// Create BasenameBlockController.h, BasenameBlockServer.h. 
				createTestBlockFiles(new File(lTestIncludePath.toOSString()), lProjectItem, TemplateUtil.NAME_BLOCK_CONTROLLER_H);
				createTestBlockFiles(new File(lTestIncludePath.toOSString()), lProjectItem, TemplateUtil.NAME_BLOCK_SERVER_H);
				
				// Create BasenameBlockControler.cpp, BasenameBlockServer.cpp. 
				createTestBlockFiles(new File(lTestSourcePath.toOSString()), lProjectItem, TemplateUtil.NAME_BLOCK_CONTROLLER_CPP);
				createTestBlockFiles(new File(lTestSourcePath.toOSString()), lProjectItem, TemplateUtil.NAME_BLOCK_SERVER_CPP);
			}
		}

		// Collect user include
		Set<String> lNewUserIncludeSet = new HashSet<String>();
		lUserIncludeSet.add(iTestIncDir);
		for (String lUserInclude : lUserIncludeSet) {
			IPath lInclude = new Path(lUserInclude);
			String lRelativeIncludePath = genRelativePath(lTestGroupPath,
					lInclude).trim();
			lNewUserIncludeSet.add(lRelativeIncludePath);
			lMppUserIncludeBuffer.append(lRelativeIncludePath + " ");
		}

		// Collect system include
		for (String lSystemInclude : lSystemIncludeSet) {
			if (!lSystemInclude.equals("\\epoc32\\include")
					&& !lSystemInclude.equals("\\epoc32\\include\\test")) {
				addToSystemInclude(lSystemInclude);
			}
		}

		// Collect tested code
		for (String lDir : lTestedSourceMap.keySet()) {
			if (lNewUserIncludeSet.contains(lDir)) {
				HashSet<String> lSourceFile = new HashSet();
				for (String lFile : lTestedSourceMap.get(lDir)) {
					if (lFile.trim().endsWith(".inl") || lFile.trim().endsWith(".h")) {
						continue;
					}else{
						lSourceFile.add(lFile);
					}
				}
				if (lSourceFile.size() > 0) {
					lMppTestedSourceBuffer.append("\r\nSOURCEPATH			" + lDir);
					for (String lFile : lSourceFile) {
						lMppTestedSourceBuffer.append("\r\nSOURCE				" + lFile);
					}
				}
			}else{
				lMppTestedSourceBuffer.append("\r\nSOURCEPATH			" + lDir);
				for (String lFile : lTestedSourceMap.get(lDir)) {
					lMppTestedSourceBuffer.append("\r\nSOURCE				" + lFile);
				}
			}
		}

		// Collect library
		for (String lLibrary : lLibrarySet) {
			addToLib(lLibrary.trim());
		}

		// MMP
		iTemplateValues.put("mmpSourcePath", lMppSourcePathBuffer.toString()
				.trim());
		iTemplateValues.put("mmpSource", lMppSourceBuffer.toString().trim()
				+ "\r\n" + lMppTestedSourceBuffer.toString().trim());
		iTemplateValues.put("mmpUserInclude", lMppUserIncludeBuffer.toString()
				.trim());
		if (iMppSystemIncludeBuffer != null) {
			iTemplateValues.put("mmpSystemInclude", iMppSystemIncludeBuffer
					.toString().trim());
		} else {
			iTemplateValues.put("mmpSystemInclude", "");
		}
		if (iMppLibraryBuffer != null) {
			iTemplateValues.put("mmpLibrary", iMppLibraryBuffer.toString()
					.trim());
		} else {
			iTemplateValues.put("mmpLibrary", "");
		}
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

		// close ini and script files.
		if (lScriptFileWriter != null) {
			lScriptFileWriter.println("END_TESTCASE TEST-SAMPLE");
			lScriptFileWriter.close();
		}
		if (lDataFileWriter != null) {
			lDataFileWriter.close();
		}

		// Refresh Workspace
		iTargetProject.refreshLocal(IResource.DEPTH_INFINITE,
				new NullProgressMonitor());
	}

	// Filter Adding
	private boolean addToSystemInclude(String include) {
		if (!include.equals("\\epoc32\\include")
				&& !include.equals("\\epoc32\\include\\test")) {
			if (iMppSystemIncludeBuffer == null) {
				iMppSystemIncludeBuffer = new StringBuffer();
				iMppSystemIncludeBuffer.append("SYSTEMINCLUDE		" + include);
			} else {
				iMppSystemIncludeBuffer.append(" " + include);
			}
			return true;
		} else {
			return false;
		}
	}

	// Filter Adding
	private boolean addToLib(String lib) {
		if (!lib.equals("euser.lib") && !lib.equals("testexecuteutils.lib")
				&& !lib.equals("testexecutelogclient.lib")) {
			if (iMppLibraryBuffer == null) {
				iMppLibraryBuffer = new StringBuffer();
				iMppLibraryBuffer.append("LIBRARY				" + lib);
			} else {
				iMppLibraryBuffer.append(" " + lib);
			}
			return true;
		} else {
			return false;
		}
	}

	private void writeScriptAndIniFile(ClassItem aClassItem,
			String aTargetName, PrintWriter scriptFileWriter,
			PrintWriter dataFileWriter) {
		String lWrapperName = TemplateUtil.getClassWrapperName(aClassItem.getName());
		// START_TEST_BLOCK 100 $(targetName) $(dataExportLoc)\$(targetName).ini
		scriptFileWriter.println("\tSTART_TEST_BLOCK 100 " + aTargetName + " "
				+ iDataExportLoc + "\\" + aTargetName + ".ini");
		// CREATE_OBJECT <object type> <object name section>
		scriptFileWriter.println("\t\tCREATE_OBJECT " + lWrapperName + " "
				+ lWrapperName + "Section");

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
		if (lHasAsync) {
			scriptFileWriter.println("\t\tOUTSTANDING");
		}
		// END_TEST_BLOCK
		scriptFileWriter.println("\tEND_TEST_BLOCK");
		dataFileWriter.println();
	}

	/**
	 * Create source files of TEF Block project
	 * 
	 * @param aSourceDirectory
	 * @param lClassEntry
	 * @param lCppName
	 * @throws CoreException
	 */
	private void createSourceFile(final IPath aSourcePath,
			ClassItem aClassItem, String aTestClass) throws CoreException {
		String lCppName = aTestClass + ".cpp";
		PrintWriter lClassCppWriter = null;
		try {

			File lClassCppCode = new File(aSourcePath.toOSString(), lCppName);
			if (lClassCppCode.exists()
					&& iTemplateValues.get(OVERWRITE) == null) {
				return;
			}
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

			lClassCppWriter.println("#include \"" + aTestClass + ".h\"");

			lClassCppWriter.println();
			lClassCppWriter
					.println("// Commands – the definition of names of supported command");

			List<MethodItem> lMethodItemList = new ArrayList<MethodItem>();
			for (MethodItem lMethod : aClassItem.getChildren()) {
				if (lMethod.isSelected()) {
					lMethodItemList.add(lMethod);
				}
			}

			for (MethodItem lMethod : lMethodItemList) {
				lClassCppWriter.println("_LIT(K" + lMethod.getNormalisedName()
						+ ", \"" + lMethod.getNormalisedName() + "\");");
			}
			lClassCppWriter.println();

			// Constructor
			lClassCppWriter.println(aTestClass + "::" + aTestClass
					+ "():iObject(NULL)");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// Destructor
			lClassCppWriter.println(aTestClass + "::~" + aTestClass + "()");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\tdelete iObject;");
			lClassCppWriter.println("\tiObject = NULL;");
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// NewL
			lClassCppWriter.println(aTestClass + "*" + aTestClass + "::"
					+ "NewL()");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\t" + aTestClass + "* ret = new (ELeave) "
					+ aTestClass + "();");
			lClassCppWriter.println("\tCleanupStack::PushL(ret);");
			lClassCppWriter.println("\tret->ConstructL();");
			lClassCppWriter.println("\tCleanupStack::Pop(ret);");
			lClassCppWriter.println("\treturn ret;");
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// ConstructL
			lClassCppWriter.println("void " + aTestClass + "::"
					+ "ConstructL()");
			lClassCppWriter.println("\t{");
			lClassCppWriter
					.println("\tiActiveCallback = CActiveCallback::NewL(*this);");
			lClassCppWriter.println("\t // TODO : Initialize wrppped object");
			lClassCppWriter.println();
			lClassCppWriter.println("\t}");
			lClassCppWriter.println();

			// RunL
			lClassCppWriter.println("void " + aTestClass
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
							+ aTestClass
							+ "::DoCommandL(const TTEFFunction& aCommand, const TTEFSectionName& aSection, const TInt aAsyncErrorIndex)");
			lClassCppWriter.println("\t{");
			lClassCppWriter.println("\tTBool ret = ETrue;");
			lClassCppWriter.println();

			lClassCppWriter.print("\t");
			for (MethodItem lMethod : lMethodItemList) {
				lClassCppWriter.println("if (K" + lMethod.getNormalisedName()
						+ "() == aCommand)");
				lClassCppWriter.println("\t\t{");
				lClassCppWriter.println("\t\t// Testing "
						+ aClassItem.getName() + "::"
						+ lMethod.getFullName().replace(" (", "("));

				String lMethodCall = "\t\t" + TemplateUtil.getMethodWrapperName(lMethod.getNormalisedName());
				if (lMethod.hasParameter() && lMethod.isAsync()) {
					lMethodCall += "(aSection, aAsyncErrorIndex);";
				} else {
					if (lMethod.hasParameter()) {
						lMethodCall += "(aSection);";
					} else if (lMethod.isAsync()) {
						lMethodCall += "(aAsyncErrorIndex);";
					} else {
						lMethodCall += "();";
					}
				}
				lClassCppWriter.println(lMethodCall);

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

			for (MethodItem lMethod : lMethodItemList) {
				lClassCppWriter.println("// Testing " + aClassItem.getName()
						+ "::" + lMethod.getFullName().replace(" (", "("));

				String lMethodDeclaration = "void " + aTestClass + "::"
						+ TemplateUtil.getMethodWrapperName(lMethod.getNormalisedName());

				if (lMethod.hasParameter() && lMethod.isAsync()) {
					lMethodDeclaration += "(const TDesC& aSection, const TInt aAsyncErrorIndex)";
				} else {
					if (lMethod.hasParameter()) {
						lMethodDeclaration += "(const TDesC& aSection)";
					} else if (lMethod.isAsync()) {
						lMethodDeclaration += "(const TInt aAsyncErrorIndex)";
					} else {
						lMethodDeclaration += "()";
					}
				}
				lClassCppWriter.println(lMethodDeclaration);
				lClassCppWriter.println("\t{");
				if (lMethod.isAsync()) {
					lClassCppWriter
							.println("\t// This is an asynchronous method under testing.");
					lClassCppWriter.println();
				}
				lClassCppWriter.println("\tINFO_PRINTF1(_L(\"Running "
						+ lMethodDeclaration.replace("void ", "") + "\"));");
				lClassCppWriter.println();

				String parameters = "";

				if (lMethod.hasParameter()) {
					lClassCppWriter
							.println("\t// Initialize parameters from config");
					List<String[]> lParameterList = lMethod.getParameters();
					for (int i = 0; i < lParameterList.size(); i++) {
						String lType = lParameterList.get(i)[MethodItem.TYPE];
						String lName = lParameterList.get(i)[MethodItem.NAME];
						if ("".equals(lName) || lName.startsWith("/")) {
							if (lParameterList.size() > 1) {
								lName = "aArg" + Integer.toString(i + 1);
							} else {
								lName = "aArg";
							}
						}
						if (lType.startsWith("const "))
							lType = lType.replace("const ", "");
						if (lType.endsWith(" const"))
							lType = lType.replace(" const", "");

						String lVarType = lType;
						boolean isPointer = false;
						if (lVarType.endsWith("*")) {
							lVarType = lVarType.substring(0,
									lVarType.length() - 1);
							isPointer = true;
						} else if (lVarType.endsWith("&")) {
							lVarType = lVarType.substring(0,
									lVarType.length() - 1);
						}

						if ("TRequestStatus&".equals(lType)) {
							if (parameters == "") {
								parameters = "iActiveCallback->iStatus";
							} else {
								parameters += ", iActiveCallback->iStatus";
							}
							continue;
						} else {
							String lParameter;
							if (isPointer) {
								lParameter = lName;
							} else {
								lParameter = "*" + lName;
							}
							if (parameters == "") {
								parameters = lParameter;
							} else {
								parameters += ", " + lParameter;
							}
						}
						String lVarName = lName;
						if (lVarName.matches("^a[A-Z].*")) {
							lVarName = lVarName.substring(1);
						}
						lClassCppWriter.println("\tTPtrC l" + lVarName + ";");
						lClassCppWriter
								.println("\tif (!GetStringFromConfig(aSection, _L(\""
										+ lMethod.getName()
										+ "_"
										+ lName
										+ "\"), l" + lVarName + "))");
						lClassCppWriter.println("\t\t{");
						lClassCppWriter
								.println("\t\tERR_PRINTF1(_L(\"No parameter "
										+ lMethod.getName() + "_" + lName
										+ "\"));");
						lClassCppWriter.println("\t\tSetBlockResult(EFail);");
						lClassCppWriter.println("\t\t}");

						lClassCppWriter.println("\t" + lVarType + "* " + lName
								+ "; // TODO : Initialize by yourself");
						lClassCppWriter.println();
					}

				}

				String lLineHead = "\t";
				if (!lMethod.isHasImpl()
						&& ((ProjectItem) aClassItem.getParent()).getLib() == null) {
					lLineHead = "\t// TODO : Can not find method implementation, comment out the calling \r\n\t// ";
				}
				if (lMethod.getName().equals(aClassItem.getName())) {
					lClassCppWriter.println("\t// Call Constructor");
					lClassCppWriter.println(lLineHead + aClassItem.getName()
							+ "* lInstance = new " + lMethod.getName() + "("
							+ parameters + ");");
				} else {
					lClassCppWriter.println("\t// Call " + aClassItem.getName()
							+ "::" + lMethod.getFullName().replace(" (", "("));
					lClassCppWriter.println(lLineHead + "iObject->"
							+ lMethod.getName() + "(" + parameters + ");");
				}

				lClassCppWriter.println();
				lClassCppWriter.println("\t// TODO : Please make changes for checking your test result");
				lClassCppWriter.println("\tSetBlockResult(EFail);");
				if (lMethod.isAsync()) {
					lClassCppWriter
							.println("\tiActiveCallback->Activate(aAsyncErrorIndex);");
					lClassCppWriter.println("\tIncOutstanding();");
				}
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
	 * Create header files of TEF Block project
	 * 
	 * @param aIncDirectory
	 * @param lClassEntry
	 * @param lTestClass
	 * @param lHeaderName
	 * @throws CoreException
	 */
	private void createHeaderFile(final IPath aIncPath, ClassItem aClassItem,
			String lTestClass) throws CoreException {
		String lHeaderName = lTestClass + ".h";
		PrintWriter lClassHeaderWriter = null;

		try {

			File lClassHeaderCode = new File(aIncPath.toOSString(), lHeaderName);
			if (lClassHeaderCode.exists()
					&& iTemplateValues.get(OVERWRITE) == null) {
				return;
			}
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

			lClassHeaderWriter.println("#ifndef __" + lTestClass.toUpperCase()
					+ "_H__");
			lClassHeaderWriter.println("#define __" + lTestClass.toUpperCase()
					+ "_H__");
			lClassHeaderWriter.println();

			// include
			lClassHeaderWriter.println("#include <DataWrapper.h>");
			String location = aClassItem.getLocation();
			IPath lHeaderPath = new Path(location);
			if (location.startsWith(iTargetProject.getLocation().toOSString())) {
				lClassHeaderWriter.println("#include \""
						+ lHeaderPath.lastSegment() + "\"");
			} else {
				lClassHeaderWriter.println("#include <"
						+ lHeaderPath.lastSegment() + ">");
				lHeaderPath = lHeaderPath.setDevice(null);
				while (lHeaderPath.segmentCount() > 2) {
					if (lHeaderPath.segment(0).equals("epoc32")) {
						lHeaderPath = lHeaderPath.removeLastSegments(1);
						String path = lHeaderPath.toOSString();
						if (!path.startsWith(File.separator)) {
							path = File.separator + path;
						}
						addToSystemInclude(path);
						break;
					} else {
						lHeaderPath = lHeaderPath.removeFirstSegments(1);
					}
				}
			}

			lClassHeaderWriter.println();
			lClassHeaderWriter.println("class " + lTestClass
					+ " : public CDataWrapper");
			lClassHeaderWriter.println("\t{");
			lClassHeaderWriter.println("public:");
			lClassHeaderWriter.println("\t// Default Constructor");
			lClassHeaderWriter.println("\t" + lTestClass + "();");
			lClassHeaderWriter.println("\t// Default Destructor");
			lClassHeaderWriter.println("\t~" + lTestClass + "();");
			lClassHeaderWriter.println();
			lClassHeaderWriter.println("\t// Two-Phase Constructor");
			lClassHeaderWriter.println("\tstatic " + lTestClass + "* NewL();");
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
			lClassHeaderWriter.println("\t\tiObject = static_cast<"
					+ aClassItem.getName() + "*> (aObject);");
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

			for (MethodItem lMethodItem : aClassItem.getChildren()) {
				if (lMethodItem.isSelected()) {
					lClassHeaderWriter.println("\t// Testing "
							+ aClassItem.getName() + "::"
							+ lMethodItem.getFullName().replace(" (", "("));

					if (lMethodItem.hasParameter() && lMethodItem.isAsync()) {
						lClassHeaderWriter
								.println("\tinline void "
										+ TemplateUtil.getMethodWrapperName(lMethodItem.getNormalisedName())
										+ "(const TDesC& aSection, const TInt aAsyncErrorIndex);");
					} else {
						if (lMethodItem.hasParameter()) {
							lClassHeaderWriter.println("\tinline void "
									+ TemplateUtil.getMethodWrapperName(lMethodItem.getNormalisedName())
									+ "(const TDesC& aSection);");
						} else if (lMethodItem.isAsync()) {
							lClassHeaderWriter.println("\tinline void "
									+ TemplateUtil.getMethodWrapperName(lMethodItem.getNormalisedName())
									+ "(const TInt aAsyncErrorIndex);");
						} else {
							lClassHeaderWriter.println("\tinline void "
									+ TemplateUtil.getMethodWrapperName(lMethodItem.getNormalisedName()) + "();");
						}
					}
					lClassHeaderWriter.println();
				}
			}

			lClassHeaderWriter.println("private:");
			lClassHeaderWriter.println("\tCActiveCallback* iActiveCallback;");
			lClassHeaderWriter.println("\t" + aClassItem.getName()
					+ "* iObject;");
			lClassHeaderWriter.println("\t};");
			lClassHeaderWriter.println("#endif // __"
					+ lTestClass.toUpperCase() + "_H__");

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
	private void createTestBlockFiles(final File aDirectory, ProjectItem project, String lTemplateName) throws CoreException {
		
		Template template = TemplateUtil.findTemplate(lTemplateName);

		String name = replaceWithVariables(template.getDescription());
		String content = replaceWithVariables(template.getPattern());
		
		content = replaceWithIncludes(content, project);

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
			.replace("${baseName}", (String) iTemplateValues.get(TARGET_NAME))
			.replace("${baseNameUpper}", ((String) iTemplateValues.get(TARGET_NAME)).toUpperCase())
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
