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
 * A representation of the literals of the enumeration '<em><b>Tef Test Case Result</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.report.ReportPackage#getTefTestCaseResult()
 * @model extendedMetaData="name='result_._1_._type'"
 * @generated
 */
public final class TefTestCaseResult extends AbstractEnumerator {
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
	public static final int PASS = 0;

	/**
	 * The '<em><b>Fail</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Fail</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FAIL_LITERAL
	 * @model name="fail"
	 * @generated
	 * @ordered
	 */
	public static final int FAIL = 1;

	/**
	 * The '<em><b>Inconclusive</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Inconclusive</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INCONCLUSIVE_LITERAL
	 * @model name="inconclusive"
	 * @generated
	 * @ordered
	 */
	public static final int INCONCLUSIVE = 2;

	/**
	 * The '<em><b>Skipped Selectively</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Skipped Selectively</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SKIPPED_SELECTIVELY_LITERAL
	 * @model name="skippedSelectively" literal="skipped_selectively"
	 * @generated
	 * @ordered
	 */
	public static final int SKIPPED_SELECTIVELY = 3;

	/**
	 * The '<em><b>Pass</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PASS
	 * @generated
	 * @ordered
	 */
	public static final TefTestCaseResult PASS_LITERAL = new TefTestCaseResult(PASS, "pass", "pass");

	/**
	 * The '<em><b>Fail</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAIL
	 * @generated
	 * @ordered
	 */
	public static final TefTestCaseResult FAIL_LITERAL = new TefTestCaseResult(FAIL, "fail", "fail");

	/**
	 * The '<em><b>Inconclusive</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCONCLUSIVE
	 * @generated
	 * @ordered
	 */
	public static final TefTestCaseResult INCONCLUSIVE_LITERAL = new TefTestCaseResult(INCONCLUSIVE, "inconclusive", "inconclusive");

	/**
	 * The '<em><b>Skipped Selectively</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SKIPPED_SELECTIVELY
	 * @generated
	 * @ordered
	 */
	public static final TefTestCaseResult SKIPPED_SELECTIVELY_LITERAL = new TefTestCaseResult(SKIPPED_SELECTIVELY, "skippedSelectively", "skipped_selectively");

	/**
	 * An array of all the '<em><b>Tef Test Case Result</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TefTestCaseResult[] VALUES_ARRAY =
		new TefTestCaseResult[] {
			PASS_LITERAL,
			FAIL_LITERAL,
			INCONCLUSIVE_LITERAL,
			SKIPPED_SELECTIVELY_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Tef Test Case Result</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Tef Test Case Result</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TefTestCaseResult get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TefTestCaseResult result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Tef Test Case Result</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TefTestCaseResult getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TefTestCaseResult result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Tef Test Case Result</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TefTestCaseResult get(int value) {
		switch (value) {
			case PASS: return PASS_LITERAL;
			case FAIL: return FAIL_LITERAL;
			case INCONCLUSIVE: return INCONCLUSIVE_LITERAL;
			case SKIPPED_SELECTIVELY: return SKIPPED_SELECTIVELY_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TefTestCaseResult(int value, String name, String literal) {
		super(value, name, literal);
	}

} //TefTestCaseResult
