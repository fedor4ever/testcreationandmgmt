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


package com.symbian.tef.script;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Utility Command</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.symbian.tef.script.ScriptPackage#getUtilityCommand()
 * @model
 * @generated
 */
public enum UtilityCommand implements Enumerator
{
	/**
	 * The '<em><b>Make Read Write</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MAKE_READ_WRITE_VALUE
	 * @generated
	 * @ordered
	 */
	MAKE_READ_WRITE(0, "MakeReadWrite", "MakeReadWrite"),
	/**
	 * The '<em><b>Delete File</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE_FILE_VALUE
	 * @generated
	 * @ordered
	 */
	DELETE_FILE(1, "DeleteFile", "DeleteFile"),
	/**
	 * The '<em><b>Delete Directory</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE_DIRECTORY_VALUE
	 * @generated
	 * @ordered
	 */
	DELETE_DIRECTORY(2, "DeleteDirectory", "DeleteDirectory"),
	/**
	 * The '<em><b>Copy File</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COPY_FILE_VALUE
	 * @generated
	 * @ordered
	 */
	COPY_FILE(3, "CopyFile", "CopyFile"),
	/**
	 * The '<em><b>Mk Dir</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MK_DIR_VALUE
	 * @generated
	 * @ordered
	 */
	MK_DIR(4, "MkDir", "MkDir");
	/**
	 * The '<em><b>Make Read Write</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Make Read Write</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MAKE_READ_WRITE
	 * @model name="MakeReadWrite"
	 * @generated
	 * @ordered
	 */
	public static final int MAKE_READ_WRITE_VALUE = 0;

	/**
	 * The '<em><b>Delete File</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Delete File</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETE_FILE
	 * @model name="DeleteFile"
	 * @generated
	 * @ordered
	 */
	public static final int DELETE_FILE_VALUE = 1;

	/**
	 * The '<em><b>Delete Directory</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Delete Directory</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETE_DIRECTORY
	 * @model name="DeleteDirectory"
	 * @generated
	 * @ordered
	 */
	public static final int DELETE_DIRECTORY_VALUE = 2;

	/**
	 * The '<em><b>Copy File</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Copy File</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COPY_FILE
	 * @model name="CopyFile"
	 * @generated
	 * @ordered
	 */
	public static final int COPY_FILE_VALUE = 3;

	/**
	 * The '<em><b>Mk Dir</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Mk Dir</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MK_DIR
	 * @model name="MkDir"
	 * @generated
	 * @ordered
	 */
	public static final int MK_DIR_VALUE = 4;

	/**
	 * An array of all the '<em><b>Utility Command</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final UtilityCommand[] VALUES_ARRAY =
		new UtilityCommand[] {
			MAKE_READ_WRITE,
			DELETE_FILE,
			DELETE_DIRECTORY,
			COPY_FILE,
			MK_DIR,
		};

	/**
	 * A public read-only list of all the '<em><b>Utility Command</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<UtilityCommand> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Utility Command</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UtilityCommand get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UtilityCommand result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Utility Command</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UtilityCommand getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UtilityCommand result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Utility Command</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UtilityCommand get(int value) {
		switch (value) {
			case MAKE_READ_WRITE_VALUE: return MAKE_READ_WRITE;
			case DELETE_FILE_VALUE: return DELETE_FILE;
			case DELETE_DIRECTORY_VALUE: return DELETE_DIRECTORY;
			case COPY_FILE_VALUE: return COPY_FILE;
			case MK_DIR_VALUE: return MK_DIR;
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
	private UtilityCommand(int value, String name, String literal) {
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
