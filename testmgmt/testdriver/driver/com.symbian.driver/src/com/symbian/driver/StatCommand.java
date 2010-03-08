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


package com.symbian.driver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Stat Command</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 				The possible STAT commands. STAT is the protocol used between the PC and the Symbian device (hardware or emulator). Please
 * 				see the STAT documentation for further details.
 * 			
 * <!-- end-model-doc -->
 * @see com.symbian.driver.DriverPackage#getStatCommand()
 * @model extendedMetaData="name='statCommand'"
 * @generated
 */
public enum StatCommand implements Enumerator
{
	/**
	 * The '<em><b>Create Folder</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_FOLDER_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE_FOLDER(0, "createFolder", "createFolder"),
	/**
	 * The '<em><b>Remove Folder</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMOVE_FOLDER_VALUE
	 * @generated
	 * @ordered
	 */
	REMOVE_FOLDER(1, "removeFolder", "removeFolder"),
	/**
	 * The '<em><b>List Drives</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIST_DRIVES_VALUE
	 * @generated
	 * @ordered
	 */
	LIST_DRIVES(2, "listDrives", "listDrives"),
	/**
	 * The '<em><b>List Files</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIST_FILES_VALUE
	 * @generated
	 * @ordered
	 */
	LIST_FILES(3, "listFiles", "listFiles"),
	/**
	 * The '<em><b>Get Screen Capture</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GET_SCREEN_CAPTURE_VALUE
	 * @generated
	 * @ordered
	 */
	GET_SCREEN_CAPTURE(4, "getScreenCapture", "getScreenCapture"),
	/**
	 * The '<em><b>Delete</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE_VALUE
	 * @generated
	 * @ordered
	 */
	DELETE(5, "delete", "delete"),
	/**
	 * The '<em><b>Run</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUN_VALUE
	 * @generated
	 * @ordered
	 */
	RUN(6, "run", "run"),
	/**
	 * The '<em><b>Start Logging</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #START_LOGGING_VALUE
	 * @generated
	 * @ordered
	 */
	START_LOGGING(7, "startLogging", "startLogging"),
	/**
	 * The '<em><b>Stop Logging</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STOP_LOGGING_VALUE
	 * @generated
	 * @ordered
	 */
	STOP_LOGGING(8, "stopLogging", "stopLogging");
	/**
	 * The '<em><b>Create Folder</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Create Folder</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CREATE_FOLDER
	 * @model name="createFolder"
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_FOLDER_VALUE = 0;

	/**
	 * The '<em><b>Remove Folder</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Remove Folder</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMOVE_FOLDER
	 * @model name="removeFolder"
	 * @generated
	 * @ordered
	 */
	public static final int REMOVE_FOLDER_VALUE = 1;

	/**
	 * The '<em><b>List Drives</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>List Drives</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LIST_DRIVES
	 * @model name="listDrives"
	 * @generated
	 * @ordered
	 */
	public static final int LIST_DRIVES_VALUE = 2;

	/**
	 * The '<em><b>List Files</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>List Files</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LIST_FILES
	 * @model name="listFiles"
	 * @generated
	 * @ordered
	 */
	public static final int LIST_FILES_VALUE = 3;

	/**
	 * The '<em><b>Get Screen Capture</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Get Screen Capture</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GET_SCREEN_CAPTURE
	 * @model name="getScreenCapture"
	 * @generated
	 * @ordered
	 */
	public static final int GET_SCREEN_CAPTURE_VALUE = 4;

	/**
	 * The '<em><b>Delete</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Delete</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETE
	 * @model name="delete"
	 * @generated
	 * @ordered
	 */
	public static final int DELETE_VALUE = 5;

	/**
	 * The '<em><b>Run</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Run</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RUN
	 * @model name="run"
	 * @generated
	 * @ordered
	 */
	public static final int RUN_VALUE = 6;

	/**
	 * The '<em><b>Start Logging</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Start Logging</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #START_LOGGING
	 * @model name="startLogging"
	 * @generated
	 * @ordered
	 */
	public static final int START_LOGGING_VALUE = 7;

	/**
	 * The '<em><b>Stop Logging</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stop Logging</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STOP_LOGGING
	 * @model name="stopLogging"
	 * @generated
	 * @ordered
	 */
	public static final int STOP_LOGGING_VALUE = 8;

	/**
	 * An array of all the '<em><b>Stat Command</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final StatCommand[] VALUES_ARRAY =
		new StatCommand[] {
			CREATE_FOLDER,
			REMOVE_FOLDER,
			LIST_DRIVES,
			LIST_FILES,
			GET_SCREEN_CAPTURE,
			DELETE,
			RUN,
			START_LOGGING,
			STOP_LOGGING,
		};

	/**
	 * A public read-only list of all the '<em><b>Stat Command</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<StatCommand> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Stat Command</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatCommand get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StatCommand result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Stat Command</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatCommand getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StatCommand result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Stat Command</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatCommand get(int value) {
		switch (value) {
			case CREATE_FOLDER_VALUE: return CREATE_FOLDER;
			case REMOVE_FOLDER_VALUE: return REMOVE_FOLDER;
			case LIST_DRIVES_VALUE: return LIST_DRIVES;
			case LIST_FILES_VALUE: return LIST_FILES;
			case GET_SCREEN_CAPTURE_VALUE: return GET_SCREEN_CAPTURE;
			case DELETE_VALUE: return DELETE;
			case RUN_VALUE: return RUN;
			case START_LOGGING_VALUE: return START_LOGGING;
			case STOP_LOGGING_VALUE: return STOP_LOGGING;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private StatCommand(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
}
