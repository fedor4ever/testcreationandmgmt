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


package com.symbian.driver.report;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Generic Result</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.report.ReportPackage#getGenericResult()
 * @model extendedMetaData="name='result_._type'"
 * @generated
 */
public final class GenericResult extends AbstractEnumerator {
	/**
	 * The '<em><b>Error</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Error</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR_LITERAL
	 * @model name="error"
	 * @generated
	 * @ordered
	 */
	public static final int ERROR = 0;

	/**
	 * The '<em><b>Warning</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Warning</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WARNING_LITERAL
	 * @model name="warning"
	 * @generated
	 * @ordered
	 */
	public static final int WARNING = 1;

	/**
	 * The '<em><b>Pass</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Pass</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PASS_LITERAL
	 * @model name="pass"
	 * @generated
	 * @ordered
	 */
	public static final int PASS = 2;

	/**
	 * The '<em><b>Error</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR
	 * @generated
	 * @ordered
	 */
	public static final GenericResult ERROR_LITERAL = new GenericResult(ERROR, "error", "error");

	/**
	 * The '<em><b>Warning</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WARNING
	 * @generated
	 * @ordered
	 */
	public static final GenericResult WARNING_LITERAL = new GenericResult(WARNING, "warning", "warning");

	/**
	 * The '<em><b>Pass</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PASS
	 * @generated
	 * @ordered
	 */
	public static final GenericResult PASS_LITERAL = new GenericResult(PASS, "pass", "pass");

	/**
	 * An array of all the '<em><b>Generic Result</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final GenericResult[] VALUES_ARRAY =
		new GenericResult[] {
			ERROR_LITERAL,
			WARNING_LITERAL,
			PASS_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Generic Result</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Generic Result</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenericResult get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GenericResult result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Generic Result</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenericResult getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GenericResult result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Generic Result</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenericResult get(int value) {
		switch (value) {
			case ERROR: return ERROR_LITERAL;
			case WARNING: return WARNING_LITERAL;
			case PASS: return PASS_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private GenericResult(int value, String name, String literal) {
		super(value, name, literal);
	}

} //GenericResult
