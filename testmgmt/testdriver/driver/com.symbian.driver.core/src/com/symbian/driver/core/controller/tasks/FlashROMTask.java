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

package com.symbian.driver.core.controller.tasks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.FlashROM;
import com.symbian.driver.Task;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.pluginProxies.FlashROMProxy;
import com.symbian.driver.core.processors.PreProcessor;

public class FlashROMTask implements IVisitor {

	private final FlashROM iFlashROM ; 
	protected static final Logger LOGGER = Logger.getLogger(FlashROMTask.class.getName());

	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();
	
	
	public FlashROMTask(FlashROM flashROM) {
		super();
		iFlashROM = flashROM;
	}


	public Map<? extends Exception, ESeverity> execute(Task task, PreProcessor symbianDevice) {
		String lRomPath = ModelUtils.subsituteVariables(iFlashROM.getPCPath());
		File lRomFile = new File(lRomPath);
		if (!lRomFile.isFile()) {
			LOGGER.log(Level.SEVERE, "Rom File does not exist.");
			iExceptions.put(new IOException("Rom File does not exist."), ESeverity.ERROR);
		} else {
			try {
				FlashROMProxy.getInstance().FlashRom(lRomFile.getAbsolutePath());
			} catch (Exception lException) {
				iExceptions.put(lException, ESeverity.ERROR);
			}
		}
		return iExceptions;
	}

}
