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


package com.symbian.driver.util;

import com.symbian.driver.*;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.DriverPackage
 * @generated
 */
public class DriverValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final DriverValidator INSTANCE = new DriverValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.symbian.driver";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DriverValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return DriverPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case DriverPackage.BUILD:
				return validateBuild((Build)value, diagnostics, context);
			case DriverPackage.CMD_PC:
				return validateCmdPC((CmdPC)value, diagnostics, context);
			case DriverPackage.CMD_SYMBIAN:
				return validateCmdSymbian((CmdSymbian)value, diagnostics, context);
			case DriverPackage.DOCUMENT_ROOT:
				return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
			case DriverPackage.DRIVER:
				return validateDriver((Driver)value, diagnostics, context);
			case DriverPackage.DRIVER_INFO:
				return validateDriverInfo((DriverInfo)value, diagnostics, context);
			case DriverPackage.EXECUTE_ON_PC:
				return validateExecuteOnPC((ExecuteOnPC)value, diagnostics, context);
			case DriverPackage.EXECUTE_ON_SYMBIAN:
				return validateExecuteOnSymbian((ExecuteOnSymbian)value, diagnostics, context);
			case DriverPackage.FLASH_ROM:
				return validateFlashROM((FlashROM)value, diagnostics, context);
			case DriverPackage.INFO:
				return validateInfo((Info)value, diagnostics, context);
			case DriverPackage.REFERENCE:
				return validateReference((Reference)value, diagnostics, context);
			case DriverPackage.RETRIEVE_FROM_SYMBIAN:
				return validateRetrieveFromSymbian((RetrieveFromSymbian)value, diagnostics, context);
			case DriverPackage.RTEST:
				return validateRtest((Rtest)value, diagnostics, context);
			case DriverPackage.TASK:
				return validateTask((Task)value, diagnostics, context);
			case DriverPackage.TEST_CASE:
				return validateTestCase((TestCase)value, diagnostics, context);
			case DriverPackage.TEST_CASES_LIST:
				return validateTestCasesList((TestCasesList)value, diagnostics, context);
			case DriverPackage.TEST_EXECUTE_SCRIPT:
				return validateTestExecuteScript((TestExecuteScript)value, diagnostics, context);
			case DriverPackage.TRANSFER:
				return validateTransfer((Transfer)value, diagnostics, context);
			case DriverPackage.TRANSFER_TO_SYMBIAN:
				return validateTransferToSymbian((TransferToSymbian)value, diagnostics, context);
			case DriverPackage.START_TRACE:
				return validateStartTrace((StartTrace)value, diagnostics, context);
			case DriverPackage.STOP_TRACE:
				return validateStopTrace((StopTrace)value, diagnostics, context);
			case DriverPackage.OPERATOR_TYPE:
				return validateOperatorType((OperatorType)value, diagnostics, context);
			case DriverPackage.PHASE:
				return validatePhase((Phase)value, diagnostics, context);
			case DriverPackage.STAT_COMMAND:
				return validateStatCommand((StatCommand)value, diagnostics, context);
			case DriverPackage.COMPONENT_NAME_TYPE:
				return validateComponentNameType((String)value, diagnostics, context);
			case DriverPackage.OPERATOR_TYPE_OBJECT:
				return validateOperatorTypeObject((OperatorType)value, diagnostics, context);
			case DriverPackage.PHASE_OBJECT:
				return validatePhaseObject((Phase)value, diagnostics, context);
			case DriverPackage.STAT_COMMAND_OBJECT:
				return validateStatCommandObject((StatCommand)value, diagnostics, context);
			case DriverPackage.SYMBIAN_PATH:
				return validateSymbianPath((String)value, diagnostics, context);
			case DriverPackage.TARGET_TYPE:
				return validateTargetType((String)value, diagnostics, context);
			default: 
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBuild(Build build, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(build, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCmdPC(CmdPC cmdPC, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(cmdPC, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCmdSymbian(CmdSymbian cmdSymbian, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(cmdSymbian, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDriver(Driver driver, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(driver, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDriverInfo(DriverInfo driverInfo, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(driverInfo, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExecuteOnPC(ExecuteOnPC executeOnPC, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(executeOnPC, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExecuteOnSymbian(ExecuteOnSymbian executeOnSymbian, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(executeOnSymbian, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlashROM(FlashROM flashROM, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(flashROM, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInfo(Info info, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(info, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReference(Reference reference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(reference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRetrieveFromSymbian(RetrieveFromSymbian retrieveFromSymbian, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(retrieveFromSymbian, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRtest(Rtest rtest, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(rtest, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTask(Task task, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(task, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestCase(TestCase testCase, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(testCase, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestCasesList(TestCasesList testCasesList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(testCasesList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestExecuteScript(TestExecuteScript testExecuteScript, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(testExecuteScript, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransfer(Transfer transfer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(transfer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransferToSymbian(TransferToSymbian transferToSymbian, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(transferToSymbian, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStartTrace(StartTrace startTrace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(startTrace, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStopTrace(StopTrace stopTrace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stopTrace, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperatorType(OperatorType operatorType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhase(Phase phase, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStatCommand(StatCommand statCommand, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentNameType(String componentNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateComponentNameType_Pattern(componentNameType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateComponentNameType_Pattern
	 */
	public static final PatternMatcher [][] COMPONENT_NAME_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("[^\\\\ / : \\* \\? \\.]*")
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Component Name Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentNameType_Pattern(String componentNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(DriverPackage.Literals.COMPONENT_NAME_TYPE, componentNameType, COMPONENT_NAME_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperatorTypeObject(OperatorType operatorTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhaseObject(Phase phaseObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStatCommandObject(StatCommand statCommandObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSymbianPath(String symbianPath, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateSymbianPath_Pattern(symbianPath, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateSymbianPath_Pattern
	 */
	public static final PatternMatcher [][] SYMBIAN_PATH__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("[a-zA-Z$]:\\\\([^\\\\ / : \\* \\?]+\\\\)*[^\\\\ / : \\?]+")
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Symbian Path</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSymbianPath_Pattern(String symbianPath, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(DriverPackage.Literals.SYMBIAN_PATH, symbianPath, SYMBIAN_PATH__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTargetType(String targetType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateTargetType_Pattern(targetType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateTargetType_Pattern
	 */
	public static final  PatternMatcher [][] TARGET_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("(\\S+\\.tcs)|([^ :\\.]+)(\\:[^ :\\.]+)?")
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Target Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTargetType_Pattern(String targetType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(DriverPackage.Literals.TARGET_TYPE, targetType, TARGET_TYPE__PATTERN__VALUES, diagnostics, context);
	}

} //DriverValidator
