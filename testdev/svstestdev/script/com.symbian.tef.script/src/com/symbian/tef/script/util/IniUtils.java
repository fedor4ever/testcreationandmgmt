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


package com.symbian.tef.script.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.symbian.ini.IniPackage;
import com.symbian.ini.Section;
import com.symbian.ini.util.IniResourceFactoryImpl;
import com.symbian.tef.script.RunScript;
import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.SectionPesistance;

/**
 * @author EngineeringTools
 *
 */
@SuppressWarnings("unchecked")
public class IniUtils {
	
	private static final IWorkspaceRoot WORKSPACE_ROOT = ResourcesPlugin.getWorkspace().getRoot();
	private static final ArrayList<File> INI_FILES = scanDirectory(WORKSPACE_ROOT, "ini");
	private static final ArrayList<File> SCRIPT_FILES = scanDirectory(WORKSPACE_ROOT, "script");
	private static ResourceSet iResourceSet = null;;

	/**
	 * @param aIni
	 * @param aSection
	 * @param aSectionPersistance
	 */
	public static final void getSection(final String aIni, final String aSection,
			final SectionPesistance aSectionPersistance, final Resource aResourceSet) {
		setResourceSet(aResourceSet.getResourceSet());
		
		aSectionPersistance.setIniPersistance(aIni);
		aSectionPersistance.setSectionPersistance(aSection);
		
		
		Resource lResource = loadIni(aIni, aResourceSet);
		if (lResource != null) {
			EObject lEObject = lResource.getEObject(aSection);
			if (lEObject != null) {
				aSectionPersistance.setSection((Section) lEObject);
			} else {
			
				for (Iterator lIterator = aResourceSet.getAllContents(); lIterator.hasNext();) {
					Object lObject = lIterator.next();
					
					if (lObject instanceof Section) {
						Section lSectionObject = (Section) lObject;
						
						if (lSectionObject != null
								&& lSectionObject.getName() != null
								&& lSectionObject.getName().equalsIgnoreCase(aSection)) {
							aSectionPersistance.setSection(lSectionObject);
						}
					}
				}
			}
		}
	}

	public static final Resource loadIni(final String aIni, final Resource aResourceSet) {
		setResourceSet(aResourceSet.getResourceSet());
		//load ini file
		final String[] lIniSplit = aIni.split("\\\\|/");
		final String lIniFileName = lIniSplit[lIniSplit.length - 1];
		File lIniFoundFile = null;
		
		for (File lIniFile : INI_FILES) {
			if (lIniFile.getName().equalsIgnoreCase(lIniFileName)) {
				lIniFoundFile = lIniFile;
				break;
			}
		}
	    return getResource(lIniFoundFile, true);
	}
	
	/**
	 * @param aIni
	 */
	public static final Section getSection(final String aIni, String aSection, final Resource aResourceSet) {
		try {
			EObject lEObject = aResourceSet.getEObject(aSection);
			if (lEObject == null) {
				Resource lResource = loadIni(aIni, aResourceSet);
				//find section
				lEObject = lResource.getEObject(aSection);
			}
			if (lEObject != null) {
				return (Section) lEObject;
			}
		} catch (Exception ex) { }
		return null;
		
	}
	
	
	/**
	 * @param aScript
	 * @param aResource 
	 * @return
	 */
	public static void getScript(final String aScript, final RunScript aRunScript, ScriptResourceImpl aResource) {
		setResourceSet(aResource.getResourceSet());
		
		aRunScript.setScriptPersistance(aScript);
		
		final String[] lScriptSplit = aScript.split("\\\\|/");
		final String lScriptFileName = lScriptSplit[lScriptSplit.length - 1];
		File lScriptFoundFile = null;
		
		for (File lScriptFile : SCRIPT_FILES) {
			if (lScriptFile.getName().equalsIgnoreCase(lScriptFileName)) {
				lScriptFoundFile = lScriptFile;
				break;
			}
		}
		
		Resource lResource = getResource(lScriptFoundFile, false);
		
		if (lResource != null) {
			aRunScript.setScript(lResource);
		}
	}

	/**
	 * @param aFile
	 */
	public static Resource getResource(File aFile, boolean aIni) {
		if (aFile != null
				&& aFile.getName().toLowerCase().endsWith(aIni ? ".ini" : ".script")
				&& iResourceSet != null) {
			URI lUri = aFile.isFile() 
				? URI.createFileURI(aFile.getAbsolutePath())
				: URI.createURI(aFile.toString());
				
			Resource lEMFResource = iResourceSet.getResource(lUri, true);
			lEMFResource.getURI();
			
			return lEMFResource;
		}
		
		return null;
	}

	public static ArrayList<File> scanDirectory(IResource aResource, final String aFindString) {
    	final ArrayList<File> lFileList = new ArrayList<File>();
    	
        try {
        	
        	
			aResource.accept(new IResourceVisitor() {

				public boolean visit(IResource resource) throws CoreException {
					if (resource.getFileExtension() != null 
							&& resource.getFileExtension().toLowerCase().contains(aFindString.toLowerCase())) {
						lFileList.add(resource.getLocation().toFile());
					}
					
					return true;
				}
				
			});
		} catch (CoreException e) {
			//
		}
		
		return lFileList;
    }

	public static ArrayList<File> getIniFiles() {
		return INI_FILES;
	}

	public static ArrayList<File> getScriptFiles() {
		return SCRIPT_FILES;
	}
	
	public static void setResourceSet(final ResourceSet aResourceSet) {
		if (iResourceSet == null) {
			aResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
					"*." + IniPackage.eNAME,
					new IniResourceFactoryImpl());
			
			aResourceSet.getPackageRegistry().put(IniPackage.eNS_URI,
					IniPackage.eINSTANCE);
			
			aResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
					"*." + ScriptPackage.eNAME,
					new ScriptResourceFactoryImpl());
			
			aResourceSet.getPackageRegistry().put(ScriptPackage.eNS_URI,
					ScriptPackage.eINSTANCE);
			
			iResourceSet = aResourceSet;
		}
	}

//	public static ResourceSet getResourceSetIni() {
//		return RESOURCE_SET_INI;
//	}
}
