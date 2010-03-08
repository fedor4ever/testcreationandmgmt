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

package com.symbian.driver.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.symbian.driver.DocumentRoot;
import com.symbian.driver.Driver;
import com.symbian.driver.DriverFactory;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.Task;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.xmlimport.FrameworkGenerator;
import com.symbian.driver.util.DriverResourceConvertor;
import com.symbian.driver.util.DriverResourceFactoryImpl;
import com.symbian.driver.util.DriverResourceImpl;
import com.symbian.utils.Utils;

/**
 * @author EngineeringTools
 * 
 */
public class ResourceLoader {

	/** Logger for this class. */
	final public static Logger LOGGER = Logger.getLogger(ResourceLoader.class.getName());
	
	private static ResourceSet iResourceSet = null;

	/**
	 * Registers the EMF resource set to prepare the EMF Driver tree.
	 */
	static {
		// Register the resource set
		iResourceSet = new ResourceSetImpl();
		iResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("driver", new DriverResourceFactoryImpl());
		iResourceSet.getPackageRegistry().put(DriverPackage.eNS_URI, DriverPackage.eINSTANCE);
	}

	/**
	 * Loads any type of TestDriver file including v1 and v2.
	 * 
	 * @return The EMF task that is loaded.
	 * @throws ParseException If a configuration error has occured.
	 */
	public static Task load() throws ParseException {
		Task lTask = null;
		TDConfig CONFIG=TDConfig.getInstance();
		URI lAddressURI = CONFIG.getPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS);

		try {

			// ONLY a.driver exits

			if (lAddressURI.fileExtension() == null) {
				// CASE: -s b.c.d OR -s a.b.c.d

				File lXmlRoot = CONFIG.getPreferenceFile(TDConfig.XML_ROOT);
				String lAddressString = lAddressURI.toString();
				int lFirstDotInAddress = (lAddressString.indexOf('.') > 0) ? lAddressString.indexOf('.')
						: lAddressString.length();
				File lPotentialDriverFile = new File(lXmlRoot.getCanonicalPath(), lAddressString.substring(1,
						lFirstDotInAddress).concat(".driver"));
				File lDriverFiles[] = null; // only get the File is the first IF
											// fails.

				if (lPotentialDriverFile.isFile()) {
					// CASE: -s a.b.c.d then a.driver exists

					URI lDriverURI = URI.createFileURI(lPotentialDriverFile.getCanonicalPath());
					lDriverURI = lDriverURI.appendFragment(lAddressString.substring(1));

					LOGGER.info("Using new XML: " + lPotentialDriverFile + " from XMLRoot " + lXmlRoot
							+ " instead of the old XMLRoot.");
					CONFIG.setPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS, lDriverURI);
					lTask = load(lDriverURI);

				} else if (lXmlRoot.isDirectory() && (lDriverFiles = Utils.getFilesWithExtension(lXmlRoot, ".driver")).length == 1) {
					// CASE: -s b.c.d and b.driver doesn't exist but a.driver
					// does exist

					File lDriverFile = lDriverFiles[0];
					String lDriverName = lDriverFile.getName();

					URI lDriverURI = URI.createFileURI(lDriverFile.getCanonicalPath());
					lDriverURI = lDriverURI.appendFragment(lDriverName.substring(0, lDriverName.indexOf('.') + 1)
							+ lAddressString.substring(1));

					lTask = load(lDriverURI);

				} else if (lXmlRoot.isDirectory() && Utils.getFilesWithExtension(lXmlRoot, ".xml").length > 0) {
					// CASE: -s b.c.d or a.b.c.d but no .driver file

					lTask = loadOldXml(lXmlRoot, lAddressURI);

				} else {
					throw new IOException("Could not find a .driver file nor .xml files in: "
							+ lXmlRoot.getCanonicalPath());
				}
			} else if (lAddressURI.fileExtension().equalsIgnoreCase("driver")) {
				// CASE: -s file:/a.driver

				lTask = load(lAddressURI);
			} else {
				// CASE: doesn't end with .driver but with another extension
				throw new IOException("Please use the extension .driver");
			}

			return lTask;
		} catch (FileNotFoundException lFileNotFoundException) {
			LOGGER
					.log(Level.SEVERE, "Could not find the file corresponding to: " + lAddressURI,
							lFileNotFoundException);
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Could not open the file corresponding to: " + lAddressURI, lIOException);
		}

		return null;
	}

	/**
	 * Loads an EMF task related to the input URI.
	 * 
	 * @param aUri A URI pointing to a task to load.
	 * @return The task corresponding to the URI specificied.
	 * @throws IOException If the URI is invalid.
	 */
	public static Task load(URI aUri) throws IOException {
		LOGGER.info("Using TestDriver v2 XML at: " + aUri ); 
		
		aUri = URI.createURI(ModelUtils.subsituteVariables(aUri.toString()));
		
		// Register the DriverPackage for standalone app
		DriverPackage lDriverPackage = DriverPackage.eINSTANCE;
		DriverResourceImpl lResource = null;
		try {
			
			lResource = (DriverResourceImpl) iResourceSet.getResource(aUri.trimFragment(), true);
			if (aUri.hasFragment()) {
				return lResource.getTask(aUri.fragment());
			}
			
			DocumentRoot lDocRoot = (DocumentRoot) lResource.getContents().get(0);
			if (lDocRoot != null) {
				return lDocRoot.getDriver().getTask();
			}
			
			throw new IOException("The driver file " + aUri + " seems to be empty");
			
		} catch (WrappedException lWrappedExcetion ) {
			
			LOGGER.info("Could not load the resource " + aUri + ", try to convert...");
			
			try {
				DriverResourceConvertor.convertResourceForLoader(aUri);
				
				LOGGER.info("Resource converted: " + aUri.devicePath());
				
				lResource = (DriverResourceImpl) iResourceSet.createResource(aUri);
				FileInputStream ins = null;
				try{
					lResource.load((ins = new FileInputStream(aUri.devicePath())), lResource.getDefaultLoadOptions());
				}finally{
					if(ins != null)
						ins.close();
				}
		
				if (aUri.hasFragment()) {
					return lResource.getTask(aUri.fragment());
				}
				
				DocumentRoot lDocRoot = (DocumentRoot) lResource.getContents().get(0);
				if (lDocRoot != null) {
					return lDocRoot.getDriver().getTask();
				}
				
				throw new IOException("The driver file " + aUri + " seems to be empty");
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Could not load the resource " + aUri, e);
			}
		}
		return null;
	}

	/**
	 * Loads TDv1 XML files.
	 * 
	 * @param aXmlRoot The location of the XML root for TDv1.
	 * @param aRootAddress The URI to the Execute to load.
	 * @return The task corresponding to the URI specfied in the XML Root directory.
	 * @throws ParseException If there was a problem with getting the configuration.
	 * @throws FileNotFoundException IF the XML root directory is invalid.
	 * @throws IOException If the URI or XML root directory is invalid.
	 */
	public static Task loadOldXml(File aXmlRoot, URI aRootAddress) throws ParseException, FileNotFoundException, IOException {
		LOGGER.info("Importing Old TestDriver v1 XML at: " + aXmlRoot.getAbsolutePath() ); 
		
		// Create Document Root
		DocumentRoot lDocumentRoot = DriverFactory.eINSTANCE.createDocumentRoot();
		// Register the factory
		Driver lDriver = DriverFactory.eINSTANCE.createDriver();
		// Import Old XML to new XML
		lDriver.setTask(new FrameworkGenerator(aXmlRoot).buildEmfModel());
		lDocumentRoot.setDriver(lDriver);

		File lSaveFile = null;
		if (aXmlRoot.isDirectory()) {
			lSaveFile = new File(aXmlRoot.getCanonicalPath() + File.separator + aXmlRoot.getName() + ".driver");
		} else if (aXmlRoot.isFile()) {
			lSaveFile = new File(aXmlRoot.toString().replaceAll("\\.xml", ".driver"));
		}

		DriverResourceImpl lResource = (DriverResourceImpl) iResourceSet.createResource(URI.createFileURI(lSaveFile.getAbsolutePath()));
		lResource.getContents().add(lDocumentRoot);

		Map lSaveOptions = new HashMap();
		lSaveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
		
		lResource.save(new FileOutputStream(lSaveFile), lSaveOptions);
		LOGGER.info("Created TestDriver v2 XML file at: " + lSaveFile.getCanonicalPath());

		if (aRootAddress != null) {
			String lTempFragement = aRootAddress.toString();
			aRootAddress = URI.createFileURI(lSaveFile.getCanonicalPath());
			aRootAddress = aRootAddress.appendFragment(lTempFragement.charAt(0) == '#' ? lTempFragement.substring(1) : lTempFragement);

			TDConfig.getInstance().setPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS, aRootAddress);
			
			ExtendedDriverValidator.validator(lResource);
			
			return lResource.getTask(aRootAddress.fragment());
		}
		
		return ((DocumentRoot) lResource.getContents().get(0)).getDriver().getTask();
	}

	/**
	 * @return the resourceSet
	 */
	public static ResourceSet getResourceSet() {
		return iResourceSet;
	}
	
	public static ResourceSet resetResourceSet() {
		iResourceSet = new ResourceSetImpl();
		iResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("driver", new DriverResourceFactoryImpl());
		iResourceSet.getPackageRegistry().put(DriverPackage.eNS_URI, DriverPackage.eINSTANCE);
		return iResourceSet;

	}
}
