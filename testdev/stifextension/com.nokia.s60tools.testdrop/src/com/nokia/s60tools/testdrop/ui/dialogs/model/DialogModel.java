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


package com.nokia.s60tools.testdrop.ui.dialogs.model;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.nokia.carbide.cpp.sdk.core.ISymbianSDK;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;

/**
 * Contains data(model) for constructing dialogs
 * 
 */
public class DialogModel {

	private List<TargetDeviceValue> targetDeviceList;
	private List<ISymbianSDK> targetEmulatorDeviceList;
	private int selectedMasterDeviceIndex;
	private IProject selectedProject = null;
	private IFile[] selectedCfgFiles = null;

	/**
	 * Constructor
	 * 
	 * @param targetDeviceList
	 *            list of target devices
	 * @param tagetEmulatorDeviceList
	 * 			  list of target emulator devices	 
	 * @param selectedMasterDeviceIndex
	 *            selected index in master device combo control
	 * @param selectedProject
	 *            selected project
	 */
	public DialogModel(List<TargetDeviceValue> targetDeviceList,
			List<ISymbianSDK> targetEmulatorDeviceList,
			int selectedMasterDeviceIndex) {
		this.setTargetDeviceList(targetDeviceList);
		this.setTargetEmulatorDeviceList(targetEmulatorDeviceList);
		this.selectedMasterDeviceIndex = selectedMasterDeviceIndex;
	}

	/**
	 * Sets a new selected master device index
	 * 
	 * @param selectedMasterDeviceIndex
	 *            a new selected master device index
	 */
	public void setSelectedMasterDeviceIndex(int selectedMasterDeviceIndex) {
		this.selectedMasterDeviceIndex = selectedMasterDeviceIndex;
	}

	/**
	 * Returns selected master device index
	 * 
	 * @return selected master device index
	 */
	public int getSelectedMasterDeviceIndex() {
		return selectedMasterDeviceIndex;
	}

	/**
	 * Returns selected master device
	 * 
	 * @return selected master device
	 */
	public TargetDeviceValue getSelectedMasterDevice() {
		if (targetDeviceList != null) {
			return targetDeviceList.get(selectedMasterDeviceIndex);
		} else {
			return null;
		}
	}

	/**
	 * Sets a new selected project
	 * 
	 * @param selectedProject
	 *            a new selected project
	 */
	public void setSelectedProject(IProject selectedProject) {
		this.selectedProject = selectedProject;
		this.selectedCfgFiles = null;
	}
	
	/**
	 * Sets selected cfg files
	 * 
	 * @param selectedCfgFiles
	 * 			Array of selected files
	 */
	public void setSelectedCfgFiles(IFile[] selectedCfgFiles) {
		this.selectedCfgFiles = selectedCfgFiles;
		this.selectedProject = null;
	}

	/**
	 * Returns selected project
	 * 
	 * @return selected project
	 */
	public IProject getSelectedProject() {
		return selectedProject;
	}
	
	/**
	 * Returns selected cfg files
	 * 
	 * @return
	 * 		Array of selected cfg files
	 */
	public IFile[] getSelectedCfgFiles() {
		return selectedCfgFiles;
	}

	/**
	 * Sets a new target device list
	 * 
	 * @param targetDeviceList a new target device list
	 */
	public void setTargetDeviceList(List<TargetDeviceValue> targetDeviceList) {
		this.targetDeviceList = targetDeviceList;
	}
	
	/**
	 * Sets a new target emulator device list
	 * 
	 * @param targetEmulatorDeviceList
	 * 							a new target emulator device list  
	 */
	public void setTargetEmulatorDeviceList(List<ISymbianSDK> targetEmulatorDeviceList) {
		this.targetEmulatorDeviceList = targetEmulatorDeviceList;
	}

	/**
	 * Returns target device list
	 * 
	 * @return target device list
	 */
	public List<TargetDeviceValue> getTargetDeviceList() {
		return targetDeviceList;
	}
	
	/**
	 * Returns target emulator device list
	 * 
	 * @return target emulator device list
	 */
	public List<ISymbianSDK> getTargetEmulatorDeviceList() {
		return targetEmulatorDeviceList;
	}
}
