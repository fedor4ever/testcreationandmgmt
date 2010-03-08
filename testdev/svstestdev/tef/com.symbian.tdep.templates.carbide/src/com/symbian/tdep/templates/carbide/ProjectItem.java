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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.dom.ast.DOMException;
import org.eclipse.cdt.core.dom.ast.IBinding;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPClassType;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.index.IIndexBinding;
import org.eclipse.cdt.core.index.IIndexName;
import org.eclipse.cdt.core.index.IndexFilter;
import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.IStructure;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.core.parser.ast.ASTAccessVisibility;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;

public class ProjectItem implements ITestItem, Comparable<ITestItem> {

	private final static Image PROJECT_IMAGE = TefTemplatesCarbidePlugin
			.imageDescriptorFromPlugin("com.symbian.tdep.templates.carbide",
					"icons/project.gif").createImage();

	private String iName;

	private String iLib;

	private HashMap<String, Map<String, IMethodDeclaration>> iFileLineMethodMap = new HashMap<String,Map<String,IMethodDeclaration>>();
	
	private Set<ClassItem> iChildItemSet = new TreeSet<ClassItem>();

	// Constructor
	public ProjectItem(IProject aProject) {
		iName = aProject.getName();
		iChildItemSet = getClasses(aProject);
	}

	// Constructor
	public ProjectItem(String name) {
		iName = name;
	}

	// Constructor
	public ProjectItem(IProject aHostProject, Path aFilePath) {
		iName = aFilePath.lastSegment();
		iChildItemSet = getClassesFromHeader(aHostProject, aFilePath);
	}

	// Get name
	public String getName() {
		return iName;
	}

	// Set name
	public void setName(String name) {
		iName = name;
	}

	// Get parent
	public ProjectItem getParent() {
		return null;
	}

	// Get children
	public Set<ClassItem> getChildren() {
		return iChildItemSet;
	}

	// Add child
	public boolean addChild(ClassItem aClass) {
		return iChildItemSet.add(aClass);
	}

	// Remove child
	public boolean removeChild(ClassItem aClass) {
		return iChildItemSet.remove(aClass);
	}

	// Get image
	public Image getImage() {
		return PROJECT_IMAGE;
	}

	// Get whether valid
	public boolean isValid() {
		if (iChildItemSet.size() > 0) {
			for (ClassItem item : iChildItemSet) {
				if (!item.isValid()) {
					return false;
				}
			}
			return true;
		} else {// no child
			return false;
		}
	}

	// Get whether selected
	public boolean isSelected() {
		boolean lSelected = false;
		for (ClassItem lClass : iChildItemSet) {
			if (lClass.isSelected()) {
				lSelected = true;
				break;
			}
		}
		return lSelected;
	}

	// Set whether selected
	public void setSelected(boolean aSelected) {
		for (ClassItem lClass : iChildItemSet) {
			lClass.setSelected(aSelected);
		}
	}

	// Get classes
	private Set<ClassItem> getClassesFromHeader(IProject aHostProject, Path aFilePath) {
		final Set<ClassItem> lClasses = new TreeSet<ClassItem>();
		try {

			ICProject cproject = CoreModel.getDefault().create(aHostProject);

			ITranslationUnit tu = CCorePlugin.getDefault().getCoreModel()
					.createTranslationUnitFrom(cproject, aFilePath);

			if (tu != null) {
				ICElement[] elements = tu.getChildren();
				for (ICElement element : elements) {
					if (element instanceof IStructure) {
						IMethodDeclaration[] methods = ((IStructure) element)
								.getMethods();
						if (methods.length == 0)
							continue;
							ClassItem lItem = new ClassItem(
									(IStructure) element,
									this, aFilePath.toString());
							if (lItem.getChildren().size() > 0) {
								lClasses.add(lItem);
							}
					}
				}
			}

		} catch (CModelException lCModelException) {
			IStatus lStatus = new Status(IStatus.ERROR,
					GenerateUnitFromPage.class.getName(), IStatus.ERROR,
					"CModel exception while inspecting header file: " + aFilePath.toString(),
					lCModelException);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		} catch (Exception exception) {
			IStatus lStatus = new Status(IStatus.WARNING,
					GenerateUnitFromPage.class.getName(), IStatus.ERROR,
					"Exception while inspecting header file: " + aFilePath.toString(), exception);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}
		return lClasses;
	}
	
	// Get classes
	private Set<ClassItem> getClasses(final IProject aProject) {
		final Set<ClassItem> lClasses = new TreeSet<ClassItem>();

		try {
			final ICProject lCProject = CoreModel.getDefault().create(aProject);
			final IIndex lIndex = CCorePlugin.getIndexManager().getIndex(
					lCProject);

			try {
				lIndex.acquireReadLock();

				IndexFilter lFilter = new IndexFilter() {
					public boolean acceptBinding(IBinding aBinding) {
						if (aBinding instanceof ICPPClassType) {

							try {
								ICPPClassType lClass = (ICPPClassType) aBinding;

								if (lClass.getKey() == ICPPClassType.k_class) {
									IIndexName[] lIndexDefinitions = lIndex
											.findDefinitions(lClass);

									if (lIndexDefinitions != null) {
										for (IIndexName lDefinition : lIndexDefinitions) {
											String lFullPath = lDefinition
													.getFileLocation()
													.getFileName();

											if (lFullPath != null) {
												ICElement lElement = null;
												try {
													lElement = lCProject
															.findElement(new Path(
																	lFullPath));
												} catch (CModelException lCModelException) {
													return false;
												}

												if (lElement != null) {
													IResource lResource = lElement
															.getResource();

													if (lResource != null
															&& lResource
																	.getProject()
																	.equals(
																			aProject)) {
														if (iFileLineMethodMap
																.get(lFullPath) == null) {
															iFileLineMethodMap
																	.put(
																			new Path(lFullPath).toOSString(),
																			new HashMap<String, IMethodDeclaration>());
														}
														return true;
													}
													return false;
												}
											}
										}
									}
								}

							} catch (DOMException lDOMException) {
								IStatus lStatus = new Status(IStatus.ERROR,
										ProjectItem.class.getName(),
										IStatus.ERROR,
										"DOM exception while inspecting binding: "
												+ aBinding.getName(),
										lDOMException);
								TefTemplatesCarbidePlugin.getDefault().getLog()
										.log(lStatus);
							} catch (CoreException lCoreException) {
								IStatus lStatus = new Status(IStatus.WARNING,
										ProjectItem.class.getName(),
										IStatus.ERROR,
										"Core exception while inspecting binding: "
												+ aBinding.getName(),
										lCoreException);
								TefTemplatesCarbidePlugin.getDefault().getLog()
										.log(lStatus);
							}
						}

						return false;
					}

				};

				IIndexBinding[] lFindBindings = lIndex.findBindings(Pattern
						.compile(".*"), true, lFilter,
						new NullProgressMonitor());
				for(String path : iFileLineMethodMap.keySet()){
					IFile file = ResourcesPlugin.getWorkspace().getRoot()
							.getFile(new Path(path));
					ITranslationUnit tu = CCorePlugin.getDefault().getCoreModel()
							.createTranslationUnitFrom(lCProject,
									new Path(path));
					if (tu != null) {
						ICElement[] elements = tu.getChildren();
						Map lLineMethodMap = iFileLineMethodMap.get(path);
						for (ICElement element : elements) {
							if (element instanceof IStructure) {
								IMethodDeclaration[] methods = ((IStructure) element)
										.getMethods();
								if (methods.length == 0)
									continue;
								for (IMethodDeclaration method : methods) {
									if (method.getVisibility() != ASTAccessVisibility.PUBLIC)
										continue;
									lLineMethodMap.put(Integer.toString(method.getSourceRange().getStartLine()), method);
								}
							}
						}
					}
				}
				for (IIndexBinding lBinding : lFindBindings) {
					ICPPClassType lClassType = ((ICPPClassType) lBinding);
					ClassItem lItem = new ClassItem(lClassType, this, lIndex);
					if (lItem.getChildren().size() > 0) {
						lClasses.add(lItem);
					}
				}

			} catch (InterruptedException lInterruptedException) {
				IStatus lStatus = new Status(
						IStatus.ERROR,
						ProjectItem.class.getName(),
						IStatus.ERROR,
						"Interuppted exception while searching for classes in project",
						lInterruptedException);
				TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
			} catch (DOMException lDOMException) {
				IStatus lStatus = new Status(IStatus.ERROR, ProjectItem.class
						.getName(), IStatus.ERROR,
						"DOM exception while searching for classes in project",
						lDOMException);
				TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
			} finally {
				lIndex.releaseReadLock();
			}

		} catch (CoreException lCoreException) {
			IStatus lStatus = new Status(IStatus.ERROR, ProjectItem.class
					.getName(), IStatus.ERROR,
					"Core exception while searching for classes in project",
					lCoreException);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}
		
		return lClasses;
	}

	// Get name of test method
	public String getTestName() {
		return iName;
	}

	// Get lib
	public String getLib() {
		return iLib;
	}

	// Set lib
	public void setLib(String lib) {
		iLib = lib;
	}

	// For TreeSet
	public int compareTo(ITestItem o) {
		return iName.compareToIgnoreCase(o.getName());
	}
	
	public IMethodDeclaration getMethodDeclaration(String file, String line) {
		IMethodDeclaration result = null;
		Map lLineMethodMap = iFileLineMethodMap.get(file);
		if (file != null) {
			result = (IMethodDeclaration) lLineMethodMap.get(line);
		}
		return result;
	}
}
