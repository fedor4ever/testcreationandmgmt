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

package com.symbian.driver.core.cmdline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * @author EngineeringTools
 *
 */
public class ImportCmdLine extends CmdLine {
		
    /**
     * Standard constructor.
     */
    public ImportCmdLine() {
        super("import", new CmdOperational() {
        	
        	public Object run(CmdLine aCmd) throws ParseException {
        		String lImportDir = aCmd.getCommandLine().getOptionValue("x");
        		
    			File lEpocRoot = null;
        		if (lImportDir != null) {
					lEpocRoot = new File(lImportDir);
        		} else {
        			lEpocRoot = TDConfig.getInstance().getPreferenceFile(TDConfig.XML_ROOT);
        		}
        		
        		
    			if (lEpocRoot != null && lEpocRoot.isDirectory()) {
	        		try {
						ResourceLoader.loadOldXml(lEpocRoot, null);
						return aCmd;
					} catch (FileNotFoundException lFileNotFoundException) {
						LOGGER.log(Level.SEVERE, "Could not find an XML files in the XML root.", lFileNotFoundException);
					} catch (IOException lIOException) {
						LOGGER.log(Level.SEVERE, "IO Exception with the XML root..", lIOException);
					}
    			} else {
    				LOGGER.log(Level.SEVERE, "Could not find an XML files in the XML root: " + lEpocRoot);
    			}
				
				return null;
        	}
        	
        }, "Imports the xml files from TDv1 format to TDv2 .driver format");
        
		addSwitch("x", true, "Location of where TestDriver v1 saved its XML files. (Variable: ${xmlroot}, Preference xmlRoot)", false, true);
    }

}
