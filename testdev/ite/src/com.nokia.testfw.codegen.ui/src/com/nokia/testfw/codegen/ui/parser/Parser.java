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
package com.nokia.testfw.codegen.ui.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.IStructure;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.core.parser.ast.ASTAccessVisibility;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;

import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.parser.model.UIClassNode;
import com.nokia.testfw.codegen.ui.parser.model.UIMethodNode;
import com.nokia.testfw.codegen.ui.parser.model.UIProjectNode;

public class Parser {

	public static int PUBLIC = 1;
	public static int PROTECTED = 2;
	public static int PRIVATE = 4;
	public static int FRIEND = 8;

	public static UIProjectNode parseProject(IProject aProject,
			String lTemplateName, int accessVisibility) {
		UIProjectNode lProjectNode = null;

		try {
			Set<String> testFolders = ProjectInfoHelper.getTestFolders(
					aProject, lTemplateName);

			ICProject lCProject = CoreModel.getDefault().create(aProject);
			lProjectNode = new UIProjectNode(lCProject);

			List<ITranslationUnit> headers = new ArrayList<ITranslationUnit>();
			lCProject.accept(new TranslationUnitCollector(null, headers,
					new NullProgressMonitor()));
			for (ITranslationUnit header : headers) {
				if (!matchTestFolder(header.getResource()
						.getProjectRelativePath().toString(), testFolders)) {
					parseTranslationUnit(header, lProjectNode, accessVisibility);
				}
			}

			collectBuildInfo(aProject, lProjectNode);
		} catch (CoreException lCoreException) {
			IStatus lStatus = new Status(IStatus.ERROR, Parser.class.getName(),
					IStatus.ERROR, "Core exception while parsing project",
					lCoreException);
			CodegenUIPlugin.getDefault().getLog().log(lStatus);
		}
		return lProjectNode;
	}

	private static boolean matchTestFolder(final String path,
			final Set<String> testFolders) {
		for (String testfolder : testFolders) {
			if (path.startsWith(testfolder)) {
				return true;
			}
		}
		return false;
	}

	private static void collectBuildInfo(IProject aProject,
			UIProjectNode aProjectNode) {
		Map<String, Set<String>> lBuildInfoMap = ProjectInfoHelper
				.getBuildInfoMap(aProject);
		aProjectNode.setUserIncludes(lBuildInfoMap
				.get(ProjectInfoHelper.USERINCLUDE));
		aProjectNode.setSystemIncludes(lBuildInfoMap
				.get(ProjectInfoHelper.SYSTEMINCLUDE));
		aProjectNode.setLibrarys(lBuildInfoMap.get(ProjectInfoHelper.LIBRARY));
	}

	private static void parseTranslationUnit(final ITranslationUnit tu,
			UIProjectNode aProjectNode, int accessVisibility)
			throws CModelException {
		if (tu != null) {
			ICElement[] elements = tu.getChildren();
			for (ICElement element : elements) {
				if (element instanceof IStructure) {
					UIClassNode lClassNode = parseClass((IStructure) element,
							aProjectNode, accessVisibility);
					if (lClassNode.getChildren().size() > 0) {
						aProjectNode.addChild(lClassNode);
					}
				}
			}
		}
	}

	public static UIProjectNode parseTranslationUnit(ITranslationUnit[] tus,
			int accessVisibility) throws CModelException {
		UIProjectNode lProjectNode = new UIProjectNode(tus[0].getCProject());
		for (ITranslationUnit tu : tus) {
			parseTranslationUnit(tu, lProjectNode, accessVisibility);
		}
		collectBuildInfo(tus[0].getCProject().getProject(), lProjectNode);
		return lProjectNode;
	}

	public static UIProjectNode parseTranslationUnit(ITranslationUnit tu,
			int accessVisibility) throws CModelException {
		UIProjectNode lProjectNode = new UIProjectNode(tu.getCProject());
		parseTranslationUnit(tu, lProjectNode, accessVisibility);
		return lProjectNode;
	}

	private static UIClassNode parseClass(IStructure classDec,
			UIProjectNode parent, int accessVisibility) throws CModelException {
		UIClassNode lClassNode = new UIClassNode(classDec, parent);
		lClassNode.setDeclLocation(classDec.getTranslationUnit().getLocation()
				.toOSString());
		IMethodDeclaration[] lMethodsList = classDec.getMethods();
		for (IMethodDeclaration lMethod : lMethodsList) {
			if (lMethod.getVisibility() == ASTAccessVisibility.PUBLIC) {
				if ((accessVisibility & PUBLIC) == 0) {
					continue;
				}
			} else if (lMethod.getVisibility() == ASTAccessVisibility.PROTECTED) {
				if ((accessVisibility & PROTECTED) == 0) {
					continue;
				}
			} else if (lMethod.getVisibility() == ASTAccessVisibility.PRIVATE) {
				if ((accessVisibility & PRIVATE) == 0) {
					continue;
				}
			}
			if (lMethod.isFriend()) {
				if ((accessVisibility & FRIEND) == 0) {
					continue;
				}
			}
			UIMethodNode lMethodItem = parseMethod(lMethod, lClassNode);
			lClassNode.addChild(lMethodItem);
		}
		if (lClassNode.getChildren().size() > 0) {
			parent.addChild(lClassNode);
		}
		return lClassNode;
	}

	public static UIProjectNode parseClass(IStructure[] classDecs,
			int accessVisibility) throws CModelException {
		UIProjectNode lProjectNode = new UIProjectNode(classDecs[0]
				.getCProject());
		for (IStructure classDec : classDecs) {
			parseClass(classDec, lProjectNode, accessVisibility);
		}
		collectBuildInfo(classDecs[0].getCProject().getProject(), lProjectNode);
		return lProjectNode;
	}

	private static UIMethodNode parseMethod(IMethodDeclaration methodDec,
			UIClassNode parent) throws CModelException {
		UIMethodNode lMethodNode = new UIMethodNode(methodDec, parent);

		if (methodDec.getVisibility() == ASTAccessVisibility.PUBLIC) {
			lMethodNode.setVisibility(0);
		} else if (methodDec.getVisibility() == ASTAccessVisibility.PROTECTED) {
			lMethodNode.setVisibility(1);
		} else {// private
			lMethodNode.setVisibility(2);
		}
		lMethodNode.setConstructor(methodDec.isConstructor());
		lMethodNode.setDestructor(methodDec.isDestructor());
		lMethodNode.setInline(methodDec.isInline());
		lMethodNode.setOperator(methodDec.isOperator());
		lMethodNode.setPureVirtual(methodDec.isPureVirtual());
		lMethodNode.setStatic(methodDec.isStatic());
		lMethodNode.setVirtual(methodDec.isVirtual());
		lMethodNode.setConst(methodDec.isConst());

		String[] lParameterTypes = methodDec.getParameterTypes();

		for (int i = 0; i < lParameterTypes.length; i++) {
			String type = lParameterTypes[i];
			if ("TRequestStatus&".equals(type)) {
				lMethodNode.setAsync(true);
			}
			type = type.split("=")[0];
			lMethodNode.addParameters(type, "aParam" + (i + 1));
		}
		return lMethodNode;
	}

	public static UIProjectNode parseMethod(IMethodDeclaration[] methodDecs)
			throws CModelException {
		UIProjectNode lProjectNode = new UIProjectNode(methodDecs[0]
				.getCProject());
		Map<IStructure, UIClassNode> classDecMap = new HashMap<IStructure, UIClassNode>();
		for (IMethodDeclaration methodDec : methodDecs) {
			IStructure classDec = (IStructure) methodDec.getParent();
			UIClassNode lClassNode = (UIClassNode) classDecMap.get(classDec);
			if (lClassNode == null) {
				lClassNode = new UIClassNode(classDec, lProjectNode);
				lClassNode.setDeclLocation(classDec.getTranslationUnit()
						.getLocation().toOSString());
				lProjectNode.addChild(lClassNode);
				classDecMap.put(classDec, lClassNode);
			}
			UIMethodNode lMethodItem = parseMethod(methodDec, lClassNode);
			lClassNode.addChild(lMethodItem);
		}
		collectBuildInfo(methodDecs[0].getCProject().getProject(), lProjectNode);
		return lProjectNode;
	}
}
