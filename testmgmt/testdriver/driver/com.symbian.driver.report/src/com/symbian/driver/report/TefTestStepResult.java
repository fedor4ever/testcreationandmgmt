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
 * A representation of the literals of the enumeration '<em><b>Tef Test Step Result</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.report.ReportPackage#getTefTestStepResult()
 * @model extendedMetaData="name='result_._3_._type'"
 * @generated
 */
public final class TefTestStepResult extends AbstractEnumerator {
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
	 * The '<em><b>Abort</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Abort</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ABORT_LITERAL
	 * @model name="abort"
	 * @generated
	 * @ordered
	 */
	public static final int ABORT = 3;

	/**
	 * The '<em><b>Panic</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Panic</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PANIC_LITERAL
	 * @model name="panic"
	 * @generated
	 * @ordered
	 */
	public static final int PANIC = 4;

	/**
	 * The '<em><b>Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_LITERAL
	 * @model name="unknown"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN = 5;

	/**
	 * The '<em><b>Unexecuted</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unexecuted</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNEXECUTED_LITERAL
	 * @model name="unexecuted"
	 * @generated
	 * @ordered
	 */
	public static final int UNEXECUTED = 6;

	/**
	 * The '<em><b>Test Suite Error</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Test Suite Error</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEST_SUITE_ERROR_LITERAL
	 * @model name="testSuiteError"
	 * @generated
	 * @ordered
	 */
	public static final int TEST_SUITE_ERROR = 7;

	/**
	 * The '<em><b>Pass</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PASS
	 * @generated
	 * @ordered
	 */
	public static final TefTestStepResult PASS_LITERAL = new TefTestStepResult(PASS, "pass", "pass");

	/**
	 * The '<em><b>Fail</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAIL
	 * @generated
	 * @ordered
	 */
	public static final TefTestStepResult FAIL_LITERAL = new TefTestStepResult(FAIL, "fail", "fail");

	/**
	 * The '<em><b>Inconclusive</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCONCLUSIVE
	 * @generated
	 * @ordered
	 */
	public static final TefTestStepResult INCONCLUSIVE_LITERAL = new TefTestStepResult(INCONCLUSIVE, "inconclusive", "inconclusive");

	/**
	 * The '<em><b>Abort</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ABORT
	 * @generated
	 * @ordered
	 */
	public static final TefTestStepResult ABORT_LITERAL = new TefTestStepResult(ABORT, "abort", "abort");

	/**
	 * The '<em><b>Panic</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PANIC
	 * @generated
	 * @ordered
	 */
	public static final TefTestStepResult PANIC_LITERAL = new TefTestStepResult(PANIC, "panic", "panic");

	/**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @generated
	 * @ordered
	 */
	public static final TefTestStepResult UNKNOWN_LITERAL = new TefTestStepResult(UNKNOWN, "unknown", "unknown");

	/**
	 * The '<em><b>Unexecuted</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNEXECUTED
	 * @generated
	 * @ordered
	 */
	public static final TefTestStepResult UNEXECUTED_LITERAL = new TefTestStepResult(UNEXECUTED, "unexecuted", "unexecuted");

	/**
	 * The '<em><b>Test Suite Error</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEST_SUITE_ERROR
	 * @generated
	 * @ordered
	 */
	public static final TefTestStepResult TEST_SUITE_ERROR_LITERAL = new TefTestStepResult(TEST_SUITE_ERROR, "testSuiteError", "testSuiteError");

	/**
	 * An array of all the '<em><b>Tef Test Step Result</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TefTestStepResult[] VALUES_ARRAY =
		new TefTestStepResult[] {
			PASS_LITERAL,
			FAIL_LITERAL,
			INCONCLUSIVE_LITERAL,
			ABORT_LITERAL,
			PANIC_LITERAL,
			UNKNOWN_LITERAL,
			UNEXECUTED_LITERAL,
			TEST_SUITE_ERROR_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Tef Test Step Result</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Tef Test Step Result</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TefTestStepResult get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TefTestStepResult result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Tef Test Step Result</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TefTestStepResult getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TefTestStepResult result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Tef Test Step Result</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TefTestStepResult get(int value) {
		switch (value) {
			case PASS: return PASS_LITERAL;
			case FAIL: return FAIL_LITERAL;
			case INCONCLUSIVE: return INCONCLUSIVE_LITERAL;
			case ABORT: return ABORT_LITERAL;
			case PANIC: return PANIC_LITERAL;
			case UNKNOWN: return UNKNOWN_LITERAL;
			case UNEXECUTED: return UNEXECUTED_LITERAL;
			case TEST_SUITE_ERROR: return TEST_SUITE_ERROR_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TefTestStepResult(int value, String name, String literal) {
		super(value, name, literal);
	}

} //TefTestStepResult
