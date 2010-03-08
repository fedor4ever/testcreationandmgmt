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


package com.nokia.s60tools.testdrop.engine.xml;

import com.nokia.s60tools.testdrop.engine.xml.value.HardwarePropertyValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Reads target device in xml format
 * 
 */
public class TargetDeviceXMLReader {

	private final String DRIVER_ELEMENT = "driver"; 
	private final String PROPERTY_ELEMENT = "property"; 
	private final String PROPERTY_NAME_ATTRIBUTE = "NAME"; 
	private final String PROPERTY_VALUE_ATTRIBUTE = "VALUE"; 
	private final String DEVICE_NAME_ATTRIBUTE = "NAME"; 
	private final String PROPERTY_HARNESS = "HARNESS"; 
	private Document dom;

	/**
	 * Constructors
	 */
	public TargetDeviceXMLReader() {

	}

	/**
	 * Resolves target devices in xml format
	 * 
	 * @param input
	 *            xml data stream
	 * @param supportedHarness
	 *            filtering parameter for harness
	 * @return returns target device list if all goes OK, otherwise returns null
	 * @throws NullPointerException
	 *             if document is null
	 */
	public List<TargetDeviceValue> getTargetDevices(InputStream input,
			String supportedHarness) throws NullPointerException, Exception {

		// initialize dom instance
		initialize(input);

		if (dom == null) {
			throw new NullPointerException(
					Messages
							.getString("TargetDeviceXMLReader.documentCannotBeNullException")); 
		}

		ArrayList<TargetDeviceValue> targetDevices = new ArrayList<TargetDeviceValue>();
		NodeList devicesList = dom.getElementsByTagName(DRIVER_ELEMENT);
		int count = devicesList.getLength();
		for (int i = 0; i < count; i++) {
			Node deviceNode = devicesList.item(i);
			if (deviceNode.getNodeType() == Node.ELEMENT_NODE) {
				String alias = null;
				Element deviceElement = (Element) deviceNode;
				NodeList propertiesList = deviceElement.getChildNodes();
				int propertiesCount = propertiesList.getLength();
				ArrayList<HardwarePropertyValue> properties = new ArrayList<HardwarePropertyValue>();
				for (int j = 0; j < propertiesCount; j++) {
					Node nodeProperty = propertiesList.item(j);
					if (nodeProperty.getNodeType() == Node.ELEMENT_NODE) {
						Element elementProperty = (Element) nodeProperty;
						if (elementProperty.getNodeName().equals(
								PROPERTY_ELEMENT)) {
							HardwarePropertyValue hardwarePropertyValue = new HardwarePropertyValue(
									elementProperty
											.getAttribute(PROPERTY_NAME_ATTRIBUTE),
									elementProperty
											.getAttribute(PROPERTY_VALUE_ATTRIBUTE));
							if (hardwarePropertyValue.getName().equals(
									DEVICE_NAME_ATTRIBUTE)) {
								alias = hardwarePropertyValue.getValue();
							} else {
								properties.add(hardwarePropertyValue);
							}
						}
					}
				}
				if (alias != null) {
					TargetDeviceValue targetDeviceValue = new TargetDeviceValue(
							null, alias, properties, null, null);
					String harness = targetDeviceValue
							.getValueFromPropertyList(PROPERTY_HARNESS);
					if (harness == null) {
						targetDevices.add(targetDeviceValue);
					} else {
						if (supportedHarness == null) {
							targetDevices.add(targetDeviceValue);
						} else if (supportedHarness.equals(harness)) {
							targetDevices.add(targetDeviceValue);
						}
					}
				}
			}
		}
		if (targetDevices.isEmpty()) {
			return null;
		} else {
			return targetDevices;
		}
	}

	/**
	 * Initializes dom instance
	 * 
	 * @param input
	 *            xml data stream
	 * @throws Exception
	 *             if initialize fails
	 */
	private void initialize(InputStream input) throws Exception {
		if (input == null) {
			throw new NullPointerException(
					Messages
							.getString("TargetDeviceXMLReader.inputCannotBeNullException")); 
		}
		Exception exception = null;
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			dom = docBuilder.parse(input);
			dom.getDocumentElement().normalize();

		} catch (SAXException ex) {
			exception = ex;
			LogExceptionHandler.log(ex.getMessage());
		} catch (IOException ex) {
			exception = ex;
			LogExceptionHandler.log(ex.getMessage());
		} catch (ParserConfigurationException ex) {
			exception = ex;
			LogExceptionHandler.log(ex.getMessage());
		} finally {
			if (exception != null) {
				throw new Exception(exception.getMessage());
			}
		}
	}
}
