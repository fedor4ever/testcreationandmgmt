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

package com.symbian.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author EngineeringTools
 *
 */
public class XSLTUtils {
	
	/** Generic Logger. */
	private static final Logger LOGGER = Logger.getLogger(XSLTUtils.class.getName());
	
	/**
	 * @param aXmlInputFile 
	 * @param aOuptutPath
	 * @param aOutputName 
	 * @param aXslt 
	 * @param aClass 
	 * 
	 * @throws IOException
	 */
	public static void transformXml(final File aXmlInputFile, final File aOuptutPath, final String aOutputName, final String aXslt, final Class aClass) throws IOException {
		// Transform the XML to other formats via XSLT
		File lOutputFile = new File(aOuptutPath, aOutputName);
		File lXsltExtracted = JarUtils.extractResource(aClass, aXslt);
		
		transformXml(aXmlInputFile, lOutputFile, lXsltExtracted);
	}

	/**
	 * @param aXmlInputFile
	 * @param aOuputFile 
	 * @param aXslt
	 * @param aOutputStream
	 */
	public static void transformXml(File aXmlInputFile, File aOuputFile, File aXslt) {
		try {
			OutputStream lOutputStream = new FileOutputStream(aOuputFile);
			
			TransformerFactory lTransformerFactor = TransformerFactory.newInstance();
			Transformer lTransformer = lTransformerFactor.newTransformer(new StreamSource(aXslt));
			lTransformer.transform(new StreamSource(aXmlInputFile), new StreamResult(lOutputStream));
			lOutputStream.close();
			
			LOGGER.finer("Created results page at: " + aOuputFile.getCanonicalPath());
			
		} catch (TransformerConfigurationException lTransformerConfigurationException) {
			LOGGER.log(Level.SEVERE, "Could not transform the XML due to Transformer Configuration problem", lTransformerConfigurationException);
		} catch (TransformerException lTransformerException) {
			LOGGER.log(Level.SEVERE, "Could not transform the XML ", lTransformerException);
		} catch (IOException lException) {
			LOGGER.log(Level.SEVERE, "IO Error while transforming the XML: " + lException.getMessage(), lException);
		}
	}
}
