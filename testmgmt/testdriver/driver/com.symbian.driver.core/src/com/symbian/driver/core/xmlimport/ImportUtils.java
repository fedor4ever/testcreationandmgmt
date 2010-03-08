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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdom.input.SAXBuilder;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.JarUtils;

/**
 * @author EngineeringTools
 *
 */
public class ImportUtils {
		
	/** The logger for the Visitor class. */
	protected final static Logger LOGGER = Logger.getLogger(FrameworkGenerator.class.getName());
	
	/**
	 * Get the next XML file.
	 * 
	 * @param aXmlFilePath
	 *            The path to the next XML file.
	 * @param aFileName
	 *            The actual file name for the next XML file.
	 * @param aIsServer
	 *            <code>true</code> if the next XML file is a server,
	 *            <code>false</code> otherwise.
	 * @return The path to the next XML file.
	 */
	protected static String getNextFile(String aXmlFilePath, String aFileName, boolean aIsServer) {
		// retrieve the actual file path of the sub-item (".xml" or ".xml~")
		
		String lNextXmlFile = aXmlFilePath.replaceAll("\\.xml"
				+ (aXmlFilePath.endsWith(Tags.ALTERNATIVE_XML_FILE) ? Tags.ALTERNATIVE_XML_FILE : ""), "")
				+ (aIsServer ? (File.separator + Tags.TEF_SERVERS_DIR_NAME) : "")
				+ File.separator
				+ aFileName
				+ ".xml";
		
		LOGGER.finest("Parsing: " + lNextXmlFile);
		
		return lNextXmlFile;
	}

	/**
	 * Subsitute the variables for the input paths
	 * 
	 * @param aHostPath
	 * @return The string corresponding to the path with the variables.
	 */
	protected static String createValidPath(String aHostPath) {
		try {
			String lSubstHostPath = ModelUtils.subsituteVariables(aHostPath);
			Map<String, String> lRoots = new HashMap<String, String>();
			TDConfig CONFIG=TDConfig.getInstance();
			lRoots.put("${xmlroot}", CONFIG.getPreferenceFile(TDConfig.XML_ROOT).getCanonicalPath());
			lRoots.put("${sourceroot}", CONFIG.getPreferenceFile(TDConfig.SOURCE_ROOT).getCanonicalPath());
			lRoots.put("${epocroot}", CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT).getCanonicalPath());
			
			for(String lRoot : lRoots.keySet()) {
				if (aHostPath.startsWith(lRoot)) {
					if (!new File(lSubstHostPath).exists()) {
						LOGGER.fine("Could not find file: \"" + aHostPath + "\".");
					}
					return aHostPath;
				} else if (new File(lRoots.get(lRoot), lSubstHostPath).exists()) {
					if (aHostPath.startsWith("\\") || aHostPath.startsWith("/")){
						aHostPath = aHostPath.substring(1);
					}
					return lRoot + File.separator + aHostPath;
				}
			}
			
			LOGGER.fine("Could not find file: \"" + aHostPath + "\" at locations: " + lRoots.values().toString());
			return aHostPath;
	
		} catch (Exception lException) {
			LOGGER.log(Level.WARNING, lException.getMessage(), lException);
			return aHostPath;
		}
	}

	/**
	 * Resolves the DTD's of the files.
	 * 
	 * @param aBuilder
	 *            The SAX builder.
	 */
	protected static void dtdResolver(final SAXBuilder aBuilder) {
		aBuilder.setEntityResolver(new EntityResolver() {
			public InputSource resolveEntity(final String aPublicId, final String aSystemId) throws IOException {
				//use slash "/" or back-slash "\" as delimiters
				String[] lSystemIdArray = aSystemId.split("/|\\\\");
				String lSystemId = lSystemIdArray[lSystemIdArray.length - 1];
				
				//legacy V1 xml file might refer to "CommandLineTest.dtd", 
				//should use "commandLineTest.dtd" in case-sensitive environment, like Java
				lSystemId = lSystemId.replace("CommandLineTest", "commandLineTest");
				File lDtd = JarUtils.extractResourceOnce(FrameworkGenerator.class, "/resource/td1dtd/" + lSystemId);
	
				return new InputSource(lDtd.getCanonicalPath());
			}
		});
	}

	/**
	 * Fixes incorrect XML files with common known problems.
	 * 
	 * @param aXMLFilePath
	 *            of the XML file to check
	 * @return either the path of the XML file if it was valid or the path of a
	 *         valid one
	 * @throws IOException
	 *             If there is an I/O errors occurs with the XML files.
	 */
	protected static String fixXmlFile(final String aXMLFilePath) throws IOException {
	
		boolean lIsAlternativeFileCreated = false;
		boolean lNewDocType = false;
		String lNewXMLFilePath = aXMLFilePath + Tags.ALTERNATIVE_XML_FILE;
		String lBegin = ".*<";
		String lEnd = ".*>.*";
		String lOpen = "\n<";
		String lClose = ">";
	
		BufferedReader lReader = new BufferedReader(new FileReader(new File(aXMLFilePath)));
	
		File lOutputFile = new File(lNewXMLFilePath);
		BufferedWriter lWriter = new BufferedWriter(new FileWriter(lOutputFile));
	
		String lLine;
		while ((lLine = lReader.readLine()) != null) {
			if (lLine.matches(".*" + Tags.DOCTYPE_SYMBIAN + ".*")) {
				lWriter.close();
				lReader.close();
				lOutputFile.delete();
				return aXMLFilePath;
			}
	
			// Fix DTD's
			String lNewLine = lLine;
			if (lLine.matches(lBegin + "!DOCTYPE" + lEnd)) {
				lNewLine = lLine.replaceAll(".*<!DOCTYPE.*", "");
			} else if (lLine.matches(lBegin + Tags.TEST_SUITE + lEnd) && (!lNewDocType)) {
				lNewLine = Tags.DOCTYPE_TESTSUITE + lOpen + Tags.TEST_SUITE + lClose;
				lNewDocType = true;
			} else if (lLine.matches(lBegin + Tags.TEST_EXECUTE_TEST + lEnd) && (!lNewDocType)) {
				lNewLine = Tags.DOCTYPE_TESTEXECUTETEST + lOpen + Tags.TEST_EXECUTE_TEST + lClose;
				lNewDocType = true;
			} else if (lLine.matches(lBegin + Tags.TEST_EXECUTE_SERVER + lEnd) && (!lNewDocType)) {
				lNewLine = Tags.DOCTYPE_TESTEXECUTESERVER + lOpen + Tags.TEST_EXECUTE_SERVER + lClose;
				lNewDocType = true;
			} else if (lLine.matches(lBegin + Tags.R_TEST + "( .+)*>.*") && (!lNewDocType)) {
				lNewLine = Tags.DOCTYPE_RTEST + lOpen + Tags.R_TEST + lClose;
				lNewDocType = true;
			} else if (lLine.matches(lBegin + Tags.R_TEST_ROM + lEnd) && (!lNewDocType)) {
				lNewLine = Tags.DOCTYPE_RTESTROM + lOpen + Tags.R_TEST_ROM + lClose;
				lNewDocType = true;
			} else if (lLine.matches(lBegin + Tags.COMMAND_LINE_TEST + lEnd) && (!lNewDocType)) {
				lNewLine = Tags.DOCTYPE_COMMANDLINETEST + lOpen + Tags.COMMAND_LINE_TEST + lClose;
				lNewDocType = true;
			} else if (lLine.matches(".*<CommandLineTest.*>.*") && (!lNewDocType)) {
				lNewLine = Tags.DOCTYPE_COMMANDLINETEST + lOpen + Tags.COMMAND_LINE_TEST + lClose;
			} else if (lLine.matches(".*</CommandLineTest.*>.*")) {
				lNewLine = lOpen + "/" + Tags.COMMAND_LINE_TEST + lClose;
				lNewDocType = true;
			}
	
			if (!lIsAlternativeFileCreated) {
				lIsAlternativeFileCreated = !lNewLine.equals(lLine);
			}
	
			lWriter.write(lNewLine);
			lWriter.newLine();
		}
		lWriter.close();
		lReader.close();
		if (!lIsAlternativeFileCreated) {
			lOutputFile.delete();
		}
	
		return lIsAlternativeFileCreated ? lNewXMLFilePath : aXMLFilePath;
	}

}
