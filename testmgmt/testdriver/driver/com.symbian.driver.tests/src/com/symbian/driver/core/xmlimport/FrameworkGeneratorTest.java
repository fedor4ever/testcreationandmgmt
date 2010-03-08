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

package com.symbian.driver.core.xmlimport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.symbian.driver.DocumentRoot;
import com.symbian.driver.Driver;
import com.symbian.driver.DriverFactory;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.Task;
import com.symbian.driver.core.xmlimport.FrameworkGenerator;
import com.symbian.driver.util.DriverResourceFactoryImpl;

/**
 * @author Enginneering Tools
 *
 */
public class FrameworkGeneratorTest extends TestCase {
	
	public final static URI DRIVER = URI.createURI("file://./My.driver");
	
	public final static File XML_ROOT = new File("H:/epoc32/TestDriver/PlatTest");
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/** Test build emf model. */
	public final void testBuildEmfModel() {
		// Create a resource set to hold the resources.
		//
		ResourceSet resourceSet = new ResourceSetImpl();

		// Register the appropriate resource factory to handle all file extentions.
		//
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new DriverResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(DriverPackage.eNS_URI, DriverPackage.eINSTANCE);
		
		Resource lResource = resourceSet.createResource(DRIVER);
		DocumentRoot lDocumentRoot = DriverFactory.eINSTANCE.createDocumentRoot();
		Driver lDriver = DriverFactory.eINSTANCE.createDriver();
		
		try {
			FrameworkGenerator lFrameworkGenerator = new FrameworkGenerator(XML_ROOT);
			
			Task lRoot = lFrameworkGenerator.buildEmfModel();
			
			lDocumentRoot.setDriver(lDriver);
			lDriver.setTask(lRoot);
		} catch (IOException e) {
			fail();
		}
		
		lResource.getContents().add(lDocumentRoot);
		try {
			lResource.save(new FileOutputStream(new File("out.xml")), null);
		} catch (IOException lException) {
			fail();
		}
	}
}
