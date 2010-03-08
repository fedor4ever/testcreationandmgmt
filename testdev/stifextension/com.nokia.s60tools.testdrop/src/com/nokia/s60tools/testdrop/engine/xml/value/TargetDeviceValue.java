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


package com.nokia.s60tools.testdrop.engine.xml.value;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Contains target device values
 * 
 */
public class TargetDeviceValue {

	private String rank;
	private String alias;
	private List<HardwarePropertyValue> properties;
	private List<File> images;
	private List<File> imagesInsideDrop;

	/**
	 * Constructs class with mandatory parameter
	 * 
	 * @param rank
	 *            value which can be either master, slave or none
	 * @param alias
	 *            name for devices
	 * @param properties
	 *            devices property values
	 * @param images
	 *            list of target device's flash images
	 * 
	 */
	public TargetDeviceValue(String rank, String alias,
			List<HardwarePropertyValue> properties, List<File> images,
			List<File> imagesInsideDrop) {
		this.rank = rank;
		this.alias = alias;
		this.properties = properties;
		this.images = images;
		this.imagesInsideDrop = imagesInsideDrop;
	}

	/**
	 * Returns rank value
	 * 
	 * @return rank value
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * Sets a new rank value
	 * 
	 * @param rank
	 *            a new rank value
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * Returns alias value
	 * 
	 * @return alias value
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets a new alias value
	 * 
	 * @param alias
	 *            new alias value
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Returns list of properties
	 * 
	 * @return list of properties
	 */
	public List<HardwarePropertyValue> getProperties() {
		return properties;
	}

	/**
	 * Sets new list of properties
	 * 
	 * @param properties
	 *            new list of properties
	 */
	public void setProperties(List<HardwarePropertyValue> properties) {
		this.properties = properties;
	}

	/**
	 * Returns list of images
	 * 
	 * @return list of images
	 */
	public List<File> getImages() {
		return images;
	}

	/**
	 * Sets new list of images
	 * 
	 * @param properties
	 *            new list of images
	 */
	public void setImages(List<File> images) {
		this.images = images;
	}

	/**
	 * Returns list of images
	 * 
	 * @return list of images
	 */
	public List<File> getImagesInsideDrop() {
		return imagesInsideDrop;
	}

	/**
	 * Sets new list of images
	 * 
	 * @param properties
	 *            new list of images
	 */
	public void setImagesInsideDrop(List<File> imagesInsideDrop) {
		this.imagesInsideDrop = imagesInsideDrop;
	}

	/**
	 * Returns name's value
	 * 
	 * @param name
	 *            its value will return
	 * @return name's value if is found otherwise null
	 */
	public String getValueFromPropertyList(String name) {
		Iterator<HardwarePropertyValue> iterator = properties.iterator();
		while (iterator.hasNext()) {
			HardwarePropertyValue hardwarePropertyValue = (HardwarePropertyValue) iterator
					.next();
			if (hardwarePropertyValue.getName().equals(name)) {
				return hardwarePropertyValue.getValue();
			}
		}
		return null;
	}
}
