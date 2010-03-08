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


package com.nokia.s60tools.testdrop.engine.xml.factory;

import com.nokia.s60tools.testdrop.engine.xml.element.DeviceElement;
import com.nokia.s60tools.testdrop.engine.xml.element.PropertyElement;
import com.nokia.s60tools.testdrop.engine.xml.element.TargetElement;
import com.nokia.s60tools.testdrop.engine.xml.value.HardwarePropertyValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.resources.Messages;

import java.util.Iterator;
import java.util.List;

/**
 * Factory class for constructs target element content
 * 
 */
public class TargetElementFactory {

	private final String NONE_VALUE = "none"; 
	private final String PROPERTY_HOST = "HOST"; 
	private final String PROPERTY_HARNESS = "HARNESS"; 
	private final String PROPERTY_NAME = "NAME"; 

	private TargetElement targetRunElement;
	private TargetElement targetPlanElement;

	/**
	 * Constructs class with mandatory parameter for constructs target element
	 * 
	 */
	public TargetElementFactory() {

	}

	/**
	 * creates target element from target device data
	 * 
	 * @param targetDeviceValues
	 *            contain target device
	 */
	public void createTargets(List<TargetDeviceValue> targetDeviceValues)
			throws IllegalArgumentException, NullPointerException {
		if (targetDeviceValues == null) {
			throw new IllegalArgumentException(
					Messages
							.getString("TargetElementFactory.listOfTheTargetDeviceValueCannotBeNullException")); 
		}
		Iterator<TargetDeviceValue> iterator = targetDeviceValues.iterator();
		targetRunElement = new TargetElement();
		targetPlanElement = new TargetElement();
		while (iterator.hasNext()) {
			boolean hostFound = false;
			boolean harnessFound = false;
			TargetDeviceValue targetDeviceValue = (TargetDeviceValue) iterator
					.next();
			if (targetDeviceValue.getAlias() == null
					|| targetDeviceValue.getRank() == null
					|| targetDeviceValue.getProperties() == null) {
				dispose();
				throw new NullPointerException(
						Messages
								.getString("TargetElementFactory.aliasRankOrPropertiesCannotBeNull")); 
			}
			DeviceElement deviceRunElement = new DeviceElement(NONE_VALUE,
					targetDeviceValue.getAlias(), targetRunElement);
			new DeviceElement(targetDeviceValue.getRank(), targetDeviceValue
					.getAlias(), targetPlanElement);

			List<HardwarePropertyValue> hardwarePropertyValues = targetDeviceValue
					.getProperties();
			Iterator<HardwarePropertyValue> propertiesIterator = hardwarePropertyValues
					.iterator();
			while (propertiesIterator.hasNext()) {
				HardwarePropertyValue hardwarePropertyValue = (HardwarePropertyValue) propertiesIterator
						.next();

				if (hardwarePropertyValue.getName().equals(PROPERTY_HOST)
						|| hardwarePropertyValue.getName().equals(
								PROPERTY_HARNESS)) {
					new PropertyElement(hardwarePropertyValue.getName(),
							hardwarePropertyValue.getValue(), deviceRunElement);
					if (hardwarePropertyValue.getName().equals(PROPERTY_HOST)) {
						hostFound = true;
					} else if (hardwarePropertyValue.getName().equals(
							PROPERTY_HARNESS)) {
						harnessFound = true;
					}
				}
				if (hostFound && harnessFound) {
					break;
				}
			}

			if (!hostFound || !harnessFound) {
				dispose();
				throw new NullPointerException(
						Messages
								.getString("TargetElementFactory.NotFoundExpectedPropertiesException")); 
			}

			// finally adds its name in property element
			new PropertyElement(PROPERTY_NAME, targetDeviceValue.getAlias(),
					deviceRunElement);

		}
	}

	/**
	 * Returns target element depending on which part is asked
	 * 
	 * @param planTarget
	 *            true value returns test plan area's target element and false
	 *            value returns test run area's target element
	 * @return returns target element that is depend on planTarget value
	 */
	public TargetElement getTarget(boolean planTarget) {
		if (planTarget) {
			return targetPlanElement;
		} else {
			return targetRunElement;
		}
	}

	/**
	 * Dispose method
	 */
	public void dispose() {
		if (targetPlanElement != null) {
			targetPlanElement.dispose();
			targetPlanElement = null;
		}
		if (targetRunElement != null) {
			targetRunElement.dispose();
			targetRunElement = null;
		}
	}
}
