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

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.cdt.core.dom.ast.DOMException;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPClassType;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPMethod;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.index.IIndexName;
import org.eclipse.cdt.core.index.IndexLocationFactory;
import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.IStructure;
import org.eclipse.cdt.core.parser.ast.ASTAccessVisibility;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPMethod;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.texteditor.IDocumentProvider;

import com.symbian.tdep.templates.carbide.util.TemplateUtil;

public class ClassItem implements ITestItem, Comparable<ITestItem> {
	// Icon of ClassItem
	private final static Image CLASS_IMAGE = TefTemplatesCarbidePlugin
			.imageDescriptorFromPlugin("com.symbian.tdep.templates.carbide",
					"icons/class.gif").createImage();
	// Name of this item
	private String iName;
	// Parent item of this item
	private ITestItem iParent;
	// Children of this item
	private Set<MethodItem> iChildItemSet = new TreeSet<MethodItem>();
	// Class location
	private String iLocation;
	// Class implementation locations
	private Set<IPath> iImplLocationSet = new HashSet<IPath>();

	// Constructor
	public ClassItem(ICPPClassType aClassType, ITestItem parent, IIndex aIndex)
			throws DOMException {
		iName = aClassType.getName();
		iParent = parent;
		iChildItemSet = getMethodes(aClassType, aIndex);

		IIndexName[] lIndexDefinitions = null;
		try {
			lIndexDefinitions = aIndex.findDefinitions(aClassType);
		} catch (CoreException ignore) {
		}
		if (lIndexDefinitions != null) {
			for (IIndexName lDefinition : lIndexDefinitions) {
				iLocation = new Path(lDefinition.getFileLocation()
						.getFileName()).toOSString();
				break;
			}
		}

	}

	// Constructor
	public ClassItem(IStructure element, ITestItem parent, String location)
			throws DOMException, CModelException {
		iName = element.getElementName();
		iParent = parent;
		iChildItemSet = getMethodes(element);
		iLocation = location;
	}

	// Constructor
	public ClassItem(String name, ITestItem parent) {
		iName = name;
		iParent = parent;
	}

	// Get name
	public String getName() {
		return iName;
	}

	// Set name
	public void setName(String name) {
		iName = name;
	}

	// Get parent item
	public ITestItem getParent() {
		return iParent;
	}

	// Set parent item
	public void setParent(ITestItem parent) {
		iParent = parent;
	}

	// Get children item
	public Set<MethodItem> getChildren() {
		return iChildItemSet;
	}

	// Add child item
	public boolean addChild(MethodItem aMethod) {
		return iChildItemSet.add(aMethod);
	}

	// Remove child item
	public boolean removeChild(MethodItem aMethod) {
		return iChildItemSet.remove(aMethod);
	}

	// Get image
	public Image getImage() {
		return CLASS_IMAGE;
	}

	// Validate integrality
	public boolean isValid() {
		if (iChildItemSet.size() > 0) {
			for (ITestItem item : iChildItemSet) {
				if (!item.isValid()) {
					return false;
				}
			}
			return true;
		} else {// no child
			return false;
		}
	}

	// Get methods
	private Set<MethodItem> getMethodes(IStructure element) throws CModelException {
		final Set<MethodItem> lMethods = new TreeSet<MethodItem>();
		IMethodDeclaration[] methods = element.getMethods();
		for (IMethodDeclaration method : methods) {
			if (method.getVisibility() != ASTAccessVisibility.PUBLIC)
				continue;
			if (method.isPureVirtual())
				continue;
			MethodItem lMethodItem = new MethodItem(method, this);
			lMethods.add(lMethodItem);
		}
		return lMethods;
	}

	// Get methods
	private Set<MethodItem> getMethodes(ICPPClassType aClassType, IIndex aIndex)
			throws DOMException {
		final Set<MethodItem> lMethods = new TreeSet<MethodItem>();
		ICPPMethod[] lMethodsList = aClassType.getDeclaredMethods();
		for (ICPPMethod lMethod : lMethodsList) {
			if (lMethod instanceof CPPMethod) {
				// Pure Virtual
				if (((CPPMethod) lMethod).getDefinition() != null) {
					if (((CPPMethod) lMethod).getDefinition() instanceof ICPPASTFunctionDeclarator) {
						if (((ICPPASTFunctionDeclarator) ((CPPMethod) lMethod)
								.getDefinition()).isPureVirtual()) {
							continue;
						}
					}
				}
				if (((CPPMethod) lMethod).getDeclarations() != null) {
					if (((CPPMethod) lMethod).getDeclarations()[0] instanceof ICPPASTFunctionDeclarator) {
						if (((ICPPASTFunctionDeclarator) ((CPPMethod) lMethod)
								.getDeclarations()[0]).isPureVirtual()) {
							continue;
						}
					}
				}
			}
			if (lMethod.getVisibility() == ICPPMethod.v_public) {
				try {
					IIndexName[] defs = aIndex.findDeclarations(lMethod);
					if (!(defs.length > 0)) {
						defs = aIndex.findDefinitions(lMethod);
					}
					if (defs.length > 0) {
						IIndexName name = defs[0];
						IPath path = IndexLocationFactory.getPath(name
								.getFile().getLocation());
						IFile file = ResourcesPlugin.getWorkspace().getRoot()
								.getFile(path);
						IDocumentProvider provider = new TextFileDocumentProvider();
						provider.connect(file);
						IDocument lDocument = provider.getDocument(file);
						String line = Integer.toString(lDocument.getLineOfOffset(name
								.getNodeOffset()) + 1);
						IMethodDeclaration method = ((ProjectItem)iParent).getMethodDeclaration(file.getRawLocation().toOSString(), line);
						MethodItem lMethodItem;
						if (method != null) {
							lMethodItem = new MethodItem(method, this);
						} else {
							lMethodItem = new MethodItem(lMethod, this);
						}
						if (aIndex != null) {
							// Get method location
							IIndexName[] lIndexDefinitions = null;
							try {
								lIndexDefinitions = aIndex.findDefinitions(lMethod);
							} catch (CoreException ignore) {
							}
							if (lIndexDefinitions != null
									&& lIndexDefinitions.length > 0) {
								lMethodItem.setHasImpl(true);
								for (IIndexName lDefinition : lIndexDefinitions) {
									iImplLocationSet.add(new Path(lDefinition
											.getFileLocation().getFileName()));
								}
							}
						}
						lMethods.add(lMethodItem);
						provider.disconnect(file);
					}
				} catch (Exception ignore) {
				}
			}
		}

		return lMethods;
	}

	// Return whether be selected
	public boolean isSelected() {
		boolean lSelected = false;
		for (MethodItem lMethod : iChildItemSet) {
			if (lMethod.isSelected()) {
				lSelected = true;
				break;
			}
		}
		return lSelected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.tef.templates.carbide.Wrapper#setSelected(boolean)
	 */
	public void setSelected(boolean aSelected) {
		for (MethodItem lMethod : iChildItemSet) {
			lMethod.setSelected(aSelected);
		}
	}

	// Get name of test class
	public String getTestName() {
		return TemplateUtil.getClassWrapperName(iName);
	}

	// Get location
	public String getLocation() {
		return iLocation;
	}

	// Get implementation location
	public IPath[] getImplLocation() {
		return iImplLocationSet.toArray(new IPath[0]);
	}

	public int compareTo(ITestItem o) {
		return iName.compareToIgnoreCase(o.getName());
	}
}
