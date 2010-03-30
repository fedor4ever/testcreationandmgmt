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

package com.nokia.testfw.codegen.ui.preferences;

import java.io.File;
import java.util.Random;

import org.eclipse.jface.preference.IPreferenceStore;

import com.nokia.testfw.codegen.ui.CodegenUIPlugin;

public class PreferenceUtil {
	private static IPreferenceStore preferenceStore = CodegenUIPlugin
			.getDefault().getPreferenceStore();
	private static Random random = new Random();
	private static int minValue;
	private static int maxValue;
	private static final int HEX_PREFIX_LEN = "0x".length();

	public static String getTestFolderName(String base) {
		String folder = CodegenUIPlugin.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.TEST_FOLDER_NAME).trim();
		if (base == null) {
			return folder;
		} else {
			return base + File.separator + folder;
		}
	}

	private static void updateMaxMinValue() {
		String hexMin = preferenceStore
				.getString(PreferenceConstants.UID3_MIN_VALUE);
		minValue = Integer.decode(hexMin).intValue();
		String hexMax = preferenceStore
				.getString(PreferenceConstants.UID3_MAX_VALUE);
		maxValue = Integer.decode(hexMax).intValue();
	}

	public static String getRandomAppUID() {
		updateMaxMinValue();
		int value = Math.abs(random.nextInt());
		value = value % (maxValue - minValue) + minValue;
		return createCanonicalHexString(value);
	}

	public static String createCanonicalHexString(int value) {
		char ZEROS[] = { '0', '0', '0', '0', '0', '0', '0', '0' };
		String unprocessedHex = Long.toHexString(value).toUpperCase();
		StringBuffer buf = new StringBuffer(10);
		buf.append("0x");
		int numSigDigits = unprocessedHex.length();
		int numLeadingZeros = 8 - numSigDigits;
		if (numLeadingZeros > 0)
			buf.append(ZEROS, 0, numLeadingZeros);
		buf.append(unprocessedHex);
		return buf.toString();
	}

	public static boolean validateAppUIDValue(int value) {
		updateMaxMinValue();
		return value >= minValue && value <= maxValue;
	}

	public static boolean validateAppUIDText(String text) {
		if(text.length()<2){
			return false;
		}
		if (!text.substring(0, HEX_PREFIX_LEN).equalsIgnoreCase("0x"))
			return false;
		String valueText = text.substring(HEX_PREFIX_LEN);
		int value = -1;
		try {
			value = Integer.parseInt(valueText, 16);
		} catch (NumberFormatException _ex) {
		}
		return PreferenceUtil.validateAppUIDValue(value);
	}

}
