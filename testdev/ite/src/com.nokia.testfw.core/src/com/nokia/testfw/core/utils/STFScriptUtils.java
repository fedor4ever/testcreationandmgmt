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

package com.nokia.testfw.core.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author xiaoma
 * 
 */
public class STFScriptUtils {
	private static final String TITLE_KEYWORD = "title ";

	public static ArrayList<String> getTestCasesFromScript(String filePath) {
		try {
			return getTestCasesFromScript(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<String>(0);
		}
	}

	/*
	 * get list of test cases names from the STF script file
	 * 
	 * @param filePath, the full path of the script file
	 * 
	 * @return ArrayList<String>, the list of test cases. size is 0 if no test
	 * cases found.
	 */
	public static ArrayList<String> getTestCasesFromScript(
			InputStream fileStream) {
		ArrayList<String> testCases = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(fileStream));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (isCommentLine(line)) {
					continue;
				}
				if (!line.startsWith(TITLE_KEYWORD)) {
					continue;
				}
				testCases.add(line.substring(TITLE_KEYWORD.length()));
			}
			br.close();
			br = null;
		} catch (Exception e) {
			e.printStackTrace();
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
				}
			}
		}

		return testCases;
	}

	private static boolean isCommentLine(String line) {
		if (line.startsWith("//")) {
			return true;
		}
		return false;
	}
}
