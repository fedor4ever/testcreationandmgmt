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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.dom.ast.DOMException;
import org.eclipse.cdt.core.dom.ast.IBinding;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPClassType;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPMethod;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.index.IIndexBinding;
import org.eclipse.cdt.core.index.IIndexName;
import org.eclipse.cdt.core.index.IndexFilter;
import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;

/**
 * A Wrapper class to wrap a Carbide project, for TEFUnit wizard to provide a 
 * project->class->method selection UI to users. 
 * 
 * @author Development Tools
 */
@SuppressWarnings("unchecked")
public class ProjectWrapper implements Wrapper<Wrapper> {

	private final IProject iProject;
	
	private final String iProjectName;
	
	private final List<ClassWrapper> iClassWrapperList;
	
	private final static Image PROJECT_IMAGE = TefTemplatesCarbidePlugin.imageDescriptorFromPlugin(
			"com.symbian.tdep.templates.carbide",
			"icons/project.gif").createImage();
	
	/**
	 * 
	 */
	public ProjectWrapper(IProject aProject) {
		iProject = aProject;
		iProjectName = aProject.getName();
		iClassWrapperList = getClassesMethods();
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getName()
	 */
	public String getName() {
		return iProjectName;
	}
	
	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getChildren()
	 */
	public List<ClassWrapper> getChildren() {
		return iClassWrapperList;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getParent()
	 */
	public Wrapper getParent() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#isSelected()
	 */
	public boolean isSelected() {
		boolean lSelected = false;
		for (Wrapper lWrapper : iClassWrapperList) {
			if (lWrapper.isSelected()) {
				lSelected = true;
				break;
			}
		}
		return lSelected;
	}
	
	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#setSelected(boolean)
	 */
	public void setSelected(boolean aSelected) {
		for (Wrapper lWrapper : iClassWrapperList) {
			lWrapper.setSelected(aSelected);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getImage()
	 */
	public Image getImage() {
		return PROJECT_IMAGE;
	}

	/**
	 * @return the project wrapped by this class
	 */
	public IProject getProject() {
		return this.iProject;
	}
	
	/* (non-Javadoc)
	 * To fetch all wrapped classes of this project 
	 */
	private List<ClassWrapper> getClassesMethods() {
		final List<ClassWrapper> lClassesMethods = new ArrayList<ClassWrapper>();
		
		try {
			final ICProject lCProject = CoreModel.getDefault().create(iProject);			
			final IIndex lIndex = CCorePlugin.getIndexManager().getIndex(lCProject);
			
			try {
				lIndex.acquireReadLock();
				
				IndexFilter lFilter = new IndexFilter() {
					
					/* (non-Javadoc)
					 * @see org.eclipse.cdt.core.index.IndexFilter#acceptBinding(org.eclipse.cdt.core.dom.ast.IBinding)
					 */
					@Override
					public boolean acceptBinding(IBinding aBinding) {
						if (aBinding instanceof ICPPClassType) {
							
							try {
								ICPPClassType lClass = (ICPPClassType) aBinding;
								
								if (lClass.getKey() == ICPPClassType.k_class) {
									IIndexName[] lIndexDefinitions = lIndex.findDefinitions(lClass);
									
									if (lIndexDefinitions != null) {
										for (IIndexName lDefinition : lIndexDefinitions) {
											String lFullPath = lDefinition.getFileLocation().getFileName();
											
											if (lFullPath != null) {
												ICElement lElement = null;
												try {
													lElement = lCProject.findElement(new Path(lFullPath));
												} catch (CModelException lCModelException) {
													return false;
												}
												
												if (lElement != null) {
													IResource lResource = lElement.getResource();
													
													if (lResource != null) {
														return lResource.getProject().equals(iProject);
													}
												}
											}
										}
									}
								}
								
							} catch (DOMException lDOMException) {
								IStatus lStatus = new Status(
										IStatus.ERROR,
										ProjectWrapper.class.getName(),
										IStatus.ERROR,
										"DOM exception while inspecting binding: " + aBinding.getName(),
										lDOMException);
								TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
							} catch (CoreException lCoreException) {
								IStatus lStatus = new Status(
										IStatus.WARNING,
										ProjectWrapper.class.getName(),
										IStatus.ERROR,
										"Core exception while inspecting binding: " + aBinding.getName(),
										lCoreException);
								TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
							}
						}
						
						return false;
					}
					
				};
				
				IIndexBinding[] lFindBindings = lIndex.findBindings(Pattern.compile(".*"), true, lFilter, new NullProgressMonitor());
				for (IIndexBinding lBinding : lFindBindings) {
					ICPPClassType lClassType = ((ICPPClassType) lBinding);
					ICPPMethod[] lMethodsList = lClassType.getDeclaredMethods();
					List<ICPPMethod> lMethods = new ArrayList<ICPPMethod>();
					
					for (ICPPMethod lMethod : lMethodsList) {
						if (!lMethod.isInline()
								&& !lMethod.isVirtual()
								&& lMethod.getVisibility() == ICPPMethod.v_public) {
							lMethods.add(lMethod);
						}
					}
					
					lClassesMethods.add(new ClassWrapper(lClassType, lMethods, this));
				}
				
			} catch (InterruptedException lInterruptedException) {
				IStatus lStatus = new Status(
						IStatus.ERROR,
						ProjectWrapper.class.getName(),
						IStatus.ERROR,
						"Interuppted exception while searching for classes in project",
						lInterruptedException);
				TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
			} catch (DOMException lDOMException) {
				IStatus lStatus = new Status(
						IStatus.ERROR,
						ProjectWrapper.class.getName(),
						IStatus.ERROR,
						"DOM exception while searching for classes in project",
						lDOMException);
				TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
			} finally {
				lIndex.releaseReadLock();
			}
			
		} catch (CoreException lCoreException) {
			IStatus lStatus = new Status(
					IStatus.ERROR,
					ProjectWrapper.class.getName(),
					IStatus.ERROR,
					"Core exception while searching for classes in project",
					lCoreException);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}
		
		return lClassesMethods;
	}

}
