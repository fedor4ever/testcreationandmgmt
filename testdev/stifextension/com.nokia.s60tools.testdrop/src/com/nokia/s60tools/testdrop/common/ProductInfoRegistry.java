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


package com.nokia.s60tools.testdrop.common;

/**
 * This class stores product information such as product name, version, console
 * view name etc. The idea is to have the product information in one place.
 */
public class ProductInfoRegistry {

	private static final String PRODUCT_NAME = "TestDrop";
	private static final String PRODUCT_VERSION = "1.0";
	private static final String CONSOLE_WINDOW_NAME = PRODUCT_NAME + " " 
			+ "Console"; 
	private static final String IMAGES_DIRECTORY = "icons"; 
	private static final String BINARIES_RELATIVE_PATH = "os\\win32\\x86"; 

	/**
	 * @return Returns the CONSOLE_WINDOW_NAME.
	 */
	public static String getConsoleWindowName() {
		return CONSOLE_WINDOW_NAME;
	}

	/**
	 * @return Returns the PRODUCT_NAME.
	 */
	public static String getProductName() {
		return PRODUCT_NAME;
	}

	/**
	 * @return Returns the PRODUCT_VERSION.
	 */
	public static String getProductVersion() {
		return PRODUCT_VERSION;
	}

	/**
	 * @return Returns the IMAGES_DIRECTORY.
	 */
	public static String getImagesDirectoryName() {
		return IMAGES_DIRECTORY;
	}

	/**
	 * @return Returns the BINARIES_RELATIVE_PATH.
	 */
	public static String getWin32BinariesRelativePath() {
		return BINARIES_RELATIVE_PATH;
	}

}
