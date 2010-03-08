/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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


package com.nokia.s60tools.testdrop.ui.dialogs.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Combo;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.EpocEngineHelper;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.carbide.cpp.sdk.core.ISDKManager;
import com.nokia.carbide.cpp.sdk.core.ISymbianSDK;
import com.nokia.carbide.cpp.sdk.core.SDKCorePlugin;
import com.nokia.s60tools.testdrop.engine.connection.HttpConnection;
import com.nokia.s60tools.testdrop.engine.xml.TargetDeviceXMLReader;
import com.nokia.s60tools.testdrop.engine.xml.value.ConnectionPropertyValue;
import com.nokia.s60tools.testdrop.engine.xml.value.HardwarePropertyValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.ui.dialogs.model.DialogModel;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * 
 * Content factory class for dialog
 * 
 */
public class DialogContentFactory {

	private final String MMP_LIBRARY = "LIBRARY"; 
	private final String COMMENTED_OUT = "//"; 
	private final String[] STIF_LIBRARIES = { "stiftestinterface.lib", 
			"stiftestengine.lib", "StifKernelTestClassBase.lib" };  
	private final String STIF_HARNESS = "STIF"; 
	
	public final static String EMULATOR_DEVICE_POSTFIX = " (emulator)";

	/**
	 * Constructor
	 */
	public DialogContentFactory() {

	}

	/**
	 * Gets and sets target devices to the combo control
	 * 
	 * @throws Exception,
	 *             IOException
	 */
	public void setTargetDevicesToCombo(Combo combo, DialogModel dialogModel,
			int cache, boolean addHardwareDevices) throws Exception, IOException {

		if (addHardwareDevices) {
			ConnectionPropertyValue connectionPropertyValue = StartUp
					.getConnectionProperties();
			String host = connectionPropertyValue.getHost();
			boolean needToUpdate = false;
	
			if (cache == 0) {
				needToUpdate = true;
			} else {
				cache = cache * 60000;
				if (StartUp.lastLoadTime == 0
						|| ((StartUp.lastLoadTime + cache) < System
								.currentTimeMillis())) {
					needToUpdate = true;
				}
			}
			
			if (dialogModel.getTargetDeviceList() == null || needToUpdate) {
				try {
					dialogModel.setTargetDeviceList(getTargetDevices(dialogModel, cache));
				} catch (Exception ex) {
					LogExceptionHandler.log(ex.getMessage());
				}
			}
			
			if (dialogModel.getTargetDeviceList() != null
					&& !dialogModel.getTargetDeviceList().isEmpty()) {

				Iterator<TargetDeviceValue> iterator = dialogModel
						.getTargetDeviceList().iterator();
				while (iterator.hasNext()) {
					TargetDeviceValue targetDeviceValue = (TargetDeviceValue) iterator
							.next();

					String hostname = makeHostPartForComboControl(targetDeviceValue
							.getProperties());
					if (host != null) {
						combo.add(targetDeviceValue.getAlias() + hostname);
					}

				}
			}
		}
		
		List<ISymbianSDK> targetEmulatorDevicesList = getTargetEmulatorDevices();
		if (dialogModel.getTargetEmulatorDeviceList() == null) {
			dialogModel.setTargetEmulatorDeviceList(targetEmulatorDevicesList);
		}
		
		if (dialogModel.getTargetEmulatorDeviceList() != null
				&& !dialogModel.getTargetEmulatorDeviceList().isEmpty()) {
			Iterator<ISymbianSDK> iterator = dialogModel.getTargetEmulatorDeviceList().iterator();
			while(iterator.hasNext()) {
				ISymbianSDK symbianSDK = iterator.next();
				combo.add(symbianSDK.getUniqueId() + EMULATOR_DEVICE_POSTFIX);
			}
		}
			
		if ((dialogModel.getTargetDeviceList() != null 
				&& dialogModel.getTargetDeviceList().isEmpty())
			&& (dialogModel.getTargetEmulatorDeviceList() != null
				&& dialogModel.getTargetEmulatorDeviceList().isEmpty())
			|| dialogModel.getTargetDeviceList() == null && dialogModel.getTargetEmulatorDeviceList() == null) {
			// Not found target devices
			throw new Exception(
					Messages
							.getString("DialogContentFactory.didNotFindTargetDeviceListException")); 
		 }
	}

	/**
	 * Gets target devices
	 * 
	 * @param dialogModel
	 *            dialog model
	 * @param cache
	 *            cache time
	 * @return list of target devices
	 * @throws Exception
	 *             if cannot resolve test harness type
	 */
	public List<TargetDeviceValue> getTargetDevices(DialogModel dialogModel,
			int cache) throws Exception {
		HttpConnection connection = StartUp.getHttpConnection(null);
		InputStream input = null;
		try {
			input = connection.getTargetDevicesFromServer();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		String harness = null;
		if (dialogModel.getSelectedProject() != null) {
			harness = resolveProjectHarnessType(dialogModel);
			if (harness == null) {
				throw new Exception(
						Messages.getString("DialogContentFactory.cannotResolveHarnessTypeException")); 
			}
		}

		TargetDeviceXMLReader targetDeviceXMLReader = new TargetDeviceXMLReader();

		if (cache != 0) {
			StartUp.lastLoadTime = System.currentTimeMillis();
		}

		return targetDeviceXMLReader.getTargetDevices(input, harness);

	}
	
	/**
	 * Gets target emulator device list
	 * 
	 * @return list of target devices
	 */
	public List<ISymbianSDK> getTargetEmulatorDevices() {
		ISDKManager manager = SDKCorePlugin.getSDKManager();
		return manager.getSDKList();
	}

	/**
	 * Adds host name part into the combo control
	 * 
	 * @param hardwarePropertyValues
	 *            list of device properties
	 * @return host name part for the combo control if combo control host name
	 *         part is done, otherwise returns null
	 */
	public String makeHostPartForComboControl(
			List<HardwarePropertyValue> hardwarePropertyValues) {

		Iterator<HardwarePropertyValue> hardwareIterator = hardwarePropertyValues
				.iterator();
		String host = null;
		while (hardwareIterator.hasNext()) {
			HardwarePropertyValue hardwarePropertyValue = (HardwarePropertyValue) hardwareIterator
					.next();
			if (hardwarePropertyValue.getName().equals("HOST")) { 
				host = hardwarePropertyValue.getValue();
			}
		}
		if (host != null) {
			return " (" + host + ")";  
		} else
			return null;

	}

	/**
	 * Resolves selected project harness type
	 * 
	 * @return harness type of the selected project if is found otherwise null
	 */
	private String resolveProjectHarnessType(DialogModel dialogModel) {

		ICarbideProjectInfo cpi = CarbideBuilderPlugin.getBuildManager()
				.getProjectInfo(dialogModel.getSelectedProject());

		Iterator<IPath> iterator = EpocEngineHelper.getMMPFilesForProject(cpi)
				.iterator();
		boolean stif_test = false;
		while (iterator.hasNext()) {
			IPath mmpPath = (IPath) iterator.next();
			BufferedReader bufReader;
			try {
				bufReader = new BufferedReader(new FileReader(new File(mmpPath
						.toString())));
				String line = null;
				ArrayList<String> libraryLines = new ArrayList<String>();
				while ((line = bufReader.readLine()) != null) {
					int index = line.indexOf(MMP_LIBRARY);
					if (index != -1) {
						int commentedOutIndex = line.indexOf(COMMENTED_OUT);
						if (commentedOutIndex == -1
								|| commentedOutIndex > index) {
							libraryLines.add(line);
						}
					}
				}
				if (!libraryLines.isEmpty()) {
					Iterator<String> libraryIterator = libraryLines.iterator();
					while (libraryIterator.hasNext()) {
						String libraryLine = (String) libraryIterator.next();

						for (int i = 0; i < STIF_LIBRARIES.length; i++) {
							if (libraryLine.indexOf(STIF_LIBRARIES[i]) != -1) {
								stif_test = true;
								break;
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				LogExceptionHandler.log(e.getMessage());

			} catch (IOException e) {
				LogExceptionHandler.log(e.getMessage());
			}
		}

		if (stif_test) {
			return STIF_HARNESS;
		}
		return null;
	}
}
