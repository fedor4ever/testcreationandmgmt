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


package com.symbian.driver.impl;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.DocumentRoot;
import com.symbian.driver.Driver;
import com.symbian.driver.DriverFactory;
import com.symbian.driver.DriverInfo;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.ExecuteOnPC;
import com.symbian.driver.ExecuteOnSymbian;
import com.symbian.driver.FlashROM;
import com.symbian.driver.Info;
import com.symbian.driver.OperatorType;
import com.symbian.driver.Phase;
import com.symbian.driver.Reference;
import com.symbian.driver.RetrieveFromSymbian;
import com.symbian.driver.Rtest;
import com.symbian.driver.StartTrace;
import com.symbian.driver.StatCommand;
import com.symbian.driver.StopTrace;
import com.symbian.driver.Task;
import com.symbian.driver.TestCase;
import com.symbian.driver.TestCasesList;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.TransferToSymbian;

import com.symbian.driver.util.DriverValidator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DriverPackageImpl extends EPackageImpl implements DriverPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass buildEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cmdPCEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cmdSymbianEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass driverEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass driverInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executeOnPCEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executeOnSymbianEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flashROMEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass retrieveFromSymbianEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rtestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCasesListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testExecuteScriptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transferEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transferToSymbianEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stopTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum operatorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum phaseEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum statCommandEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType componentNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType operatorTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType phaseObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType statCommandObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType symbianPathEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType targetTypeEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.symbian.driver.DriverPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DriverPackageImpl() {
		super(eNS_URI, DriverFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DriverPackage init() {
		if (isInited) return (DriverPackage)EPackage.Registry.INSTANCE.getEPackage(DriverPackage.eNS_URI);

		// Obtain or create and register package
		DriverPackageImpl theDriverPackage = (DriverPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof DriverPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new DriverPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theDriverPackage.createPackageContents();

		// Initialize created meta-data
		theDriverPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theDriverPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return DriverValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theDriverPackage.freeze();

		return theDriverPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBuild() {
		return buildEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBuild_ComponentName() {
		return (EAttribute)buildEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBuild_TestBuild() {
		return (EAttribute)buildEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBuild_URI() {
		return (EAttribute)buildEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCmdPC() {
		return cmdPCEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCmdPC_Value() {
		return (EAttribute)cmdPCEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCmdPC_Phase() {
		return (EAttribute)cmdPCEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCmdPC_Sync() {
		return (EAttribute)cmdPCEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCmdPC_URI() {
		return (EAttribute)cmdPCEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCmdSymbian() {
		return cmdSymbianEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCmdSymbian_Argument() {
		return (EAttribute)cmdSymbianEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCmdSymbian_Output() {
		return (EAttribute)cmdSymbianEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCmdSymbian_StatCommand() {
		return (EAttribute)cmdSymbianEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCmdSymbian_Sync() {
		return (EAttribute)cmdSymbianEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Driver() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDriver() {
		return driverEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDriver_DriverInfo() {
		return (EReference)driverEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDriver_Task() {
		return (EReference)driverEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDriverInfo() {
		return driverInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDriverInfo_Info() {
		return (EReference)driverInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecuteOnPC() {
		return executeOnPCEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecuteOnPC_Group() {
		return (EAttribute)executeOnPCEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecuteOnPC_Cmd() {
		return (EReference)executeOnPCEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecuteOnPC_Build() {
		return (EReference)executeOnPCEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecuteOnSymbian() {
		return executeOnSymbianEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecuteOnSymbian_Group() {
		return (EAttribute)executeOnSymbianEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecuteOnSymbian_Cmd() {
		return (EReference)executeOnSymbianEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecuteOnSymbian_TestExecuteScript() {
		return (EReference)executeOnSymbianEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecuteOnSymbian_Rtest() {
		return (EReference)executeOnSymbianEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFlashROM() {
		return flashROMEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlashROM_PCPath() {
		return (EAttribute)flashROMEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfo() {
		return infoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_Value() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_Key() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReference() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReference_Uri() {
		return (EReference)referenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRetrieveFromSymbian() {
		return retrieveFromSymbianEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRetrieveFromSymbian_Group() {
		return (EAttribute)retrieveFromSymbianEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRetrieveFromSymbian_Transfer() {
		return (EReference)retrieveFromSymbianEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRtest() {
		return rtestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRtest_ResultFile() {
		return (EAttribute)rtestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRtest_SymbianPath() {
		return (EAttribute)rtestEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTask() {
		return taskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Group() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_ExecuteOnPC() {
		return (EReference)taskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_TransferToSymbian() {
		return (EReference)taskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_ExecuteOnSymbian() {
		return (EReference)taskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_RetrieveFromSymbian() {
		return (EReference)taskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_Reference() {
		return (EReference)taskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_Task() {
		return (EReference)taskEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_Flashrom() {
		return (EReference)taskEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Name() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_PreRebootDevice() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Timeout() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_StartTrace() {
		return (EReference)taskEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_StopTrace() {
		return (EReference)taskEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCase() {
		return testCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_Target() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCasesList() {
		return testCasesListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCasesList_TestCase() {
		return (EReference)testCasesListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCasesList_Operator() {
		return (EAttribute)testCasesListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestExecuteScript() {
		return testExecuteScriptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestExecuteScript_TestCasesList() {
		return (EReference)testExecuteScriptEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestExecuteScript_PCPath() {
		return (EAttribute)testExecuteScriptEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestExecuteScript_SymbianPath() {
		return (EAttribute)testExecuteScriptEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransfer() {
		return transferEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransfer_Move() {
		return (EAttribute)transferEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransfer_PCPath() {
		return (EAttribute)transferEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransfer_SymbianPath() {
		return (EAttribute)transferEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransferToSymbian() {
		return transferToSymbianEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransferToSymbian_Group() {
		return (EAttribute)transferToSymbianEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransferToSymbian_Transfer() {
		return (EReference)transferToSymbianEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStartTrace() {
		return startTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartTrace_EnablePrimaryFilters() {
		return (EAttribute)startTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartTrace_EnableSecondaryFilters() {
		return (EAttribute)startTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartTrace_DisablePrimaryFilters() {
		return (EAttribute)startTraceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartTrace_DisableSecondaryFilters() {
		return (EAttribute)startTraceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartTrace_ConfigFilePath() {
		return (EAttribute)startTraceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStopTrace() {
		return stopTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOperatorType() {
		return operatorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPhase() {
		return phaseEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStatCommand() {
		return statCommandEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getComponentNameType() {
		return componentNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOperatorTypeObject() {
		return operatorTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPhaseObject() {
		return phaseObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getStatCommandObject() {
		return statCommandObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSymbianPath() {
		return symbianPathEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTargetType() {
		return targetTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DriverFactory getDriverFactory() {
		return (DriverFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		buildEClass = createEClass(BUILD);
		createEAttribute(buildEClass, BUILD__COMPONENT_NAME);
		createEAttribute(buildEClass, BUILD__TEST_BUILD);
		createEAttribute(buildEClass, BUILD__URI);

		cmdPCEClass = createEClass(CMD_PC);
		createEAttribute(cmdPCEClass, CMD_PC__VALUE);
		createEAttribute(cmdPCEClass, CMD_PC__PHASE);
		createEAttribute(cmdPCEClass, CMD_PC__SYNC);
		createEAttribute(cmdPCEClass, CMD_PC__URI);

		cmdSymbianEClass = createEClass(CMD_SYMBIAN);
		createEAttribute(cmdSymbianEClass, CMD_SYMBIAN__ARGUMENT);
		createEAttribute(cmdSymbianEClass, CMD_SYMBIAN__OUTPUT);
		createEAttribute(cmdSymbianEClass, CMD_SYMBIAN__STAT_COMMAND);
		createEAttribute(cmdSymbianEClass, CMD_SYMBIAN__SYNC);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DRIVER);

		driverEClass = createEClass(DRIVER);
		createEReference(driverEClass, DRIVER__DRIVER_INFO);
		createEReference(driverEClass, DRIVER__TASK);

		driverInfoEClass = createEClass(DRIVER_INFO);
		createEReference(driverInfoEClass, DRIVER_INFO__INFO);

		executeOnPCEClass = createEClass(EXECUTE_ON_PC);
		createEAttribute(executeOnPCEClass, EXECUTE_ON_PC__GROUP);
		createEReference(executeOnPCEClass, EXECUTE_ON_PC__CMD);
		createEReference(executeOnPCEClass, EXECUTE_ON_PC__BUILD);

		executeOnSymbianEClass = createEClass(EXECUTE_ON_SYMBIAN);
		createEAttribute(executeOnSymbianEClass, EXECUTE_ON_SYMBIAN__GROUP);
		createEReference(executeOnSymbianEClass, EXECUTE_ON_SYMBIAN__CMD);
		createEReference(executeOnSymbianEClass, EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT);
		createEReference(executeOnSymbianEClass, EXECUTE_ON_SYMBIAN__RTEST);

		flashROMEClass = createEClass(FLASH_ROM);
		createEAttribute(flashROMEClass, FLASH_ROM__PC_PATH);

		infoEClass = createEClass(INFO);
		createEAttribute(infoEClass, INFO__VALUE);
		createEAttribute(infoEClass, INFO__KEY);

		referenceEClass = createEClass(REFERENCE);
		createEReference(referenceEClass, REFERENCE__URI);

		retrieveFromSymbianEClass = createEClass(RETRIEVE_FROM_SYMBIAN);
		createEAttribute(retrieveFromSymbianEClass, RETRIEVE_FROM_SYMBIAN__GROUP);
		createEReference(retrieveFromSymbianEClass, RETRIEVE_FROM_SYMBIAN__TRANSFER);

		rtestEClass = createEClass(RTEST);
		createEAttribute(rtestEClass, RTEST__RESULT_FILE);
		createEAttribute(rtestEClass, RTEST__SYMBIAN_PATH);

		taskEClass = createEClass(TASK);
		createEAttribute(taskEClass, TASK__GROUP);
		createEReference(taskEClass, TASK__EXECUTE_ON_PC);
		createEReference(taskEClass, TASK__TRANSFER_TO_SYMBIAN);
		createEReference(taskEClass, TASK__EXECUTE_ON_SYMBIAN);
		createEReference(taskEClass, TASK__RETRIEVE_FROM_SYMBIAN);
		createEReference(taskEClass, TASK__REFERENCE);
		createEReference(taskEClass, TASK__TASK);
		createEReference(taskEClass, TASK__FLASHROM);
		createEReference(taskEClass, TASK__START_TRACE);
		createEReference(taskEClass, TASK__STOP_TRACE);
		createEAttribute(taskEClass, TASK__NAME);
		createEAttribute(taskEClass, TASK__PRE_REBOOT_DEVICE);
		createEAttribute(taskEClass, TASK__TIMEOUT);

		testCaseEClass = createEClass(TEST_CASE);
		createEAttribute(testCaseEClass, TEST_CASE__TARGET);

		testCasesListEClass = createEClass(TEST_CASES_LIST);
		createEReference(testCasesListEClass, TEST_CASES_LIST__TEST_CASE);
		createEAttribute(testCasesListEClass, TEST_CASES_LIST__OPERATOR);

		testExecuteScriptEClass = createEClass(TEST_EXECUTE_SCRIPT);
		createEReference(testExecuteScriptEClass, TEST_EXECUTE_SCRIPT__TEST_CASES_LIST);
		createEAttribute(testExecuteScriptEClass, TEST_EXECUTE_SCRIPT__PC_PATH);
		createEAttribute(testExecuteScriptEClass, TEST_EXECUTE_SCRIPT__SYMBIAN_PATH);

		transferEClass = createEClass(TRANSFER);
		createEAttribute(transferEClass, TRANSFER__MOVE);
		createEAttribute(transferEClass, TRANSFER__PC_PATH);
		createEAttribute(transferEClass, TRANSFER__SYMBIAN_PATH);

		transferToSymbianEClass = createEClass(TRANSFER_TO_SYMBIAN);
		createEAttribute(transferToSymbianEClass, TRANSFER_TO_SYMBIAN__GROUP);
		createEReference(transferToSymbianEClass, TRANSFER_TO_SYMBIAN__TRANSFER);

		startTraceEClass = createEClass(START_TRACE);
		createEAttribute(startTraceEClass, START_TRACE__ENABLE_PRIMARY_FILTERS);
		createEAttribute(startTraceEClass, START_TRACE__ENABLE_SECONDARY_FILTERS);
		createEAttribute(startTraceEClass, START_TRACE__DISABLE_PRIMARY_FILTERS);
		createEAttribute(startTraceEClass, START_TRACE__DISABLE_SECONDARY_FILTERS);
		createEAttribute(startTraceEClass, START_TRACE__CONFIG_FILE_PATH);

		stopTraceEClass = createEClass(STOP_TRACE);

		// Create enums
		operatorTypeEEnum = createEEnum(OPERATOR_TYPE);
		phaseEEnum = createEEnum(PHASE);
		statCommandEEnum = createEEnum(STAT_COMMAND);

		// Create data types
		componentNameTypeEDataType = createEDataType(COMPONENT_NAME_TYPE);
		operatorTypeObjectEDataType = createEDataType(OPERATOR_TYPE_OBJECT);
		phaseObjectEDataType = createEDataType(PHASE_OBJECT);
		statCommandObjectEDataType = createEDataType(STAT_COMMAND_OBJECT);
		symbianPathEDataType = createEDataType(SYMBIAN_PATH);
		targetTypeEDataType = createEDataType(TARGET_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(buildEClass, Build.class, "Build", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuild_ComponentName(), this.getComponentNameType(), "componentName", null, 0, -1, Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_TestBuild(), theXMLTypePackage.getBoolean(), "testBuild", "false", 0, 1, Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_URI(), theXMLTypePackage.getAnyURI(), "uRI", "file://c:/", 0, 1, Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cmdPCEClass, CmdPC.class, "CmdPC", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCmdPC_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, CmdPC.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCmdPC_Phase(), this.getPhase(), "phase", "build", 0, 1, CmdPC.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCmdPC_Sync(), theXMLTypePackage.getBoolean(), "sync", "true", 0, 1, CmdPC.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCmdPC_URI(), theXMLTypePackage.getAnyURI(), "uRI", "file://c:/", 0, 1, CmdPC.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cmdSymbianEClass, CmdSymbian.class, "CmdSymbian", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCmdSymbian_Argument(), theXMLTypePackage.getString(), "argument", null, 0, 2, CmdSymbian.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCmdSymbian_Output(), theXMLTypePackage.getAnyURI(), "output", null, 0, 1, CmdSymbian.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCmdSymbian_StatCommand(), this.getStatCommand(), "statCommand", "createFolder", 1, 1, CmdSymbian.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCmdSymbian_Sync(), theXMLTypePackage.getBoolean(), "sync", "true", 0, 1, CmdSymbian.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Driver(), this.getDriver(), null, "driver", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(driverEClass, Driver.class, "Driver", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDriver_DriverInfo(), this.getDriverInfo(), null, "driverInfo", null, 0, 1, Driver.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDriver_Task(), this.getTask(), null, "task", null, 1, 1, Driver.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(driverInfoEClass, DriverInfo.class, "DriverInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDriverInfo_Info(), this.getInfo(), null, "info", null, 1, -1, DriverInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executeOnPCEClass, ExecuteOnPC.class, "ExecuteOnPC", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecuteOnPC_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ExecuteOnPC.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecuteOnPC_Cmd(), this.getCmdPC(), null, "cmd", null, 0, -1, ExecuteOnPC.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getExecuteOnPC_Build(), this.getBuild(), null, "build", null, 0, -1, ExecuteOnPC.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(executeOnSymbianEClass, ExecuteOnSymbian.class, "ExecuteOnSymbian", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecuteOnSymbian_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ExecuteOnSymbian.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecuteOnSymbian_Cmd(), this.getCmdSymbian(), null, "cmd", null, 0, -1, ExecuteOnSymbian.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getExecuteOnSymbian_TestExecuteScript(), this.getTestExecuteScript(), null, "testExecuteScript", null, 0, -1, ExecuteOnSymbian.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getExecuteOnSymbian_Rtest(), this.getRtest(), null, "rtest", null, 0, -1, ExecuteOnSymbian.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(flashROMEClass, FlashROM.class, "FlashROM", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFlashROM_PCPath(), theXMLTypePackage.getAnyURI(), "pCPath", null, 1, 1, FlashROM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(infoEClass, Info.class, "Info", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInfo_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, Info.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfo_Key(), theXMLTypePackage.getString(), "key", null, 0, 1, Info.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceEClass, Reference.class, "Reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReference_Uri(), this.getTask(), null, "uri", null, 0, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(retrieveFromSymbianEClass, RetrieveFromSymbian.class, "RetrieveFromSymbian", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRetrieveFromSymbian_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, RetrieveFromSymbian.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRetrieveFromSymbian_Transfer(), this.getTransfer(), null, "transfer", null, 1, -1, RetrieveFromSymbian.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(rtestEClass, Rtest.class, "Rtest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRtest_ResultFile(), this.getSymbianPath(), "resultFile", null, 0, 1, Rtest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRtest_SymbianPath(), theXMLTypePackage.getString(), "symbianPath", null, 1, 1, Rtest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskEClass, Task.class, "Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTask_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTask_ExecuteOnPC(), this.getExecuteOnPC(), null, "executeOnPC", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTask_TransferToSymbian(), this.getTransferToSymbian(), null, "transferToSymbian", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTask_ExecuteOnSymbian(), this.getExecuteOnSymbian(), null, "executeOnSymbian", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTask_RetrieveFromSymbian(), this.getRetrieveFromSymbian(), null, "retrieveFromSymbian", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTask_Reference(), this.getReference(), null, "reference", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTask_Task(), this.getTask(), null, "task", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTask_Flashrom(), this.getFlashROM(), null, "flashrom", null, 0, -1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTask_StartTrace(), this.getStartTrace(), null, "startTrace", null, 0, 1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTask_StopTrace(), this.getStopTrace(), null, "stopTrace", null, 0, 1, Task.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_Name(), theXMLTypePackage.getID(), "name", null, 1, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_PreRebootDevice(), theXMLTypePackage.getBoolean(), "preRebootDevice", null, 0, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_Timeout(), theXMLTypePackage.getInt(), "timeout", null, 0, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCaseEClass, TestCase.class, "TestCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCase_Target(), this.getTargetType(), "target", null, 1, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCasesListEClass, TestCasesList.class, "TestCasesList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCasesList_TestCase(), this.getTestCase(), null, "testCase", null, 1, -1, TestCasesList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCasesList_Operator(), this.getOperatorType(), "operator", "include", 1, 1, TestCasesList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testExecuteScriptEClass, TestExecuteScript.class, "TestExecuteScript", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestExecuteScript_TestCasesList(), this.getTestCasesList(), null, "testCasesList", null, 0, 1, TestExecuteScript.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestExecuteScript_PCPath(), theXMLTypePackage.getAnyURI(), "pCPath", null, 1, 1, TestExecuteScript.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestExecuteScript_SymbianPath(), this.getSymbianPath(), "symbianPath", null, 1, 1, TestExecuteScript.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transferEClass, Transfer.class, "Transfer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransfer_Move(), theXMLTypePackage.getBoolean(), "move", "false", 0, 1, Transfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransfer_PCPath(), theXMLTypePackage.getAnyURI(), "pCPath", null, 1, 1, Transfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransfer_SymbianPath(), this.getSymbianPath(), "symbianPath", null, 1, 1, Transfer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transferToSymbianEClass, TransferToSymbian.class, "TransferToSymbian", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransferToSymbian_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TransferToSymbian.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransferToSymbian_Transfer(), this.getTransfer(), null, "transfer", null, 1, -1, TransferToSymbian.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(startTraceEClass, StartTrace.class, "StartTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStartTrace_EnablePrimaryFilters(), theXMLTypePackage.getString(), "enablePrimaryFilters", null, 0, 1, StartTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartTrace_EnableSecondaryFilters(), theXMLTypePackage.getString(), "enableSecondaryFilters", null, 0, 1, StartTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartTrace_DisablePrimaryFilters(), theXMLTypePackage.getString(), "disablePrimaryFilters", null, 0, 1, StartTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartTrace_DisableSecondaryFilters(), theXMLTypePackage.getString(), "disableSecondaryFilters", null, 0, 1, StartTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartTrace_ConfigFilePath(), theXMLTypePackage.getString(), "configFilePath", null, 0, 1, StartTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stopTraceEClass, StopTrace.class, "StopTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(operatorTypeEEnum, OperatorType.class, "OperatorType");
		addEEnumLiteral(operatorTypeEEnum, OperatorType.INCLUDE);
		addEEnumLiteral(operatorTypeEEnum, OperatorType.EXCLUDE);

		initEEnum(phaseEEnum, Phase.class, "Phase");
		addEEnumLiteral(phaseEEnum, Phase.BUILD);
		addEEnumLiteral(phaseEEnum, Phase.RUN);
		addEEnumLiteral(phaseEEnum, Phase.BOTH);

		initEEnum(statCommandEEnum, StatCommand.class, "StatCommand");
		addEEnumLiteral(statCommandEEnum, StatCommand.CREATE_FOLDER);
		addEEnumLiteral(statCommandEEnum, StatCommand.REMOVE_FOLDER);
		addEEnumLiteral(statCommandEEnum, StatCommand.LIST_DRIVES);
		addEEnumLiteral(statCommandEEnum, StatCommand.LIST_FILES);
		addEEnumLiteral(statCommandEEnum, StatCommand.GET_SCREEN_CAPTURE);
		addEEnumLiteral(statCommandEEnum, StatCommand.DELETE);
		addEEnumLiteral(statCommandEEnum, StatCommand.RUN);
		addEEnumLiteral(statCommandEEnum, StatCommand.START_LOGGING);
		addEEnumLiteral(statCommandEEnum, StatCommand.STOP_LOGGING);

		// Initialize data types
		initEDataType(componentNameTypeEDataType, String.class, "ComponentNameType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(operatorTypeObjectEDataType, OperatorType.class, "OperatorTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(phaseObjectEDataType, Phase.class, "PhaseObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(statCommandObjectEDataType, StatCommand.class, "StatCommandObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(symbianPathEDataType, String.class, "SymbianPath", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(targetTypeEDataType, String.class, "TargetType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";			
		addAnnotation
		  (buildEClass, 
		   source, 
		   new String[] {
			 "name", "build",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getBuild_ComponentName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "componentName"
		   });			
		addAnnotation
		  (getBuild_TestBuild(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "testBuild"
		   });			
		addAnnotation
		  (getBuild_URI(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "URI"
		   });			
		addAnnotation
		  (cmdPCEClass, 
		   source, 
		   new String[] {
			 "name", "cmdPC",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getCmdPC_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getCmdPC_Phase(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "phase"
		   });			
		addAnnotation
		  (getCmdPC_Sync(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "sync"
		   });			
		addAnnotation
		  (getCmdPC_URI(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "URI"
		   });			
		addAnnotation
		  (cmdSymbianEClass, 
		   source, 
		   new String[] {
			 "name", "cmdSymbian",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getCmdSymbian_Argument(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "argument"
		   });			
		addAnnotation
		  (getCmdSymbian_Output(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "output"
		   });			
		addAnnotation
		  (getCmdSymbian_StatCommand(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "statCommand"
		   });			
		addAnnotation
		  (getCmdSymbian_Sync(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "sync"
		   });			
		addAnnotation
		  (componentNameTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "componentName_._type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
			 "pattern", "[^\\\\%20/%20:%20\\*%20\\?%20\\.]*"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_Driver(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "driver",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (driverEClass, 
		   source, 
		   new String[] {
			 "name", "driver",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDriver_DriverInfo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "driverInfo"
		   });		
		addAnnotation
		  (getDriver_Task(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task"
		   });			
		addAnnotation
		  (driverInfoEClass, 
		   source, 
		   new String[] {
			 "name", "driverInfo",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDriverInfo_Info(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "info"
		   });			
		addAnnotation
		  (executeOnPCEClass, 
		   source, 
		   new String[] {
			 "name", "executeOnPC",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExecuteOnPC_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getExecuteOnPC_Cmd(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "cmd",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getExecuteOnPC_Build(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "build",
			 "group", "#group:0"
		   });			
		addAnnotation
		  (executeOnSymbianEClass, 
		   source, 
		   new String[] {
			 "name", "executeOnSymbian",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExecuteOnSymbian_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getExecuteOnSymbian_Cmd(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "cmd",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getExecuteOnSymbian_TestExecuteScript(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "testExecuteScript",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getExecuteOnSymbian_Rtest(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "rtest",
			 "group", "#group:0"
		   });			
		addAnnotation
		  (flashROMEClass, 
		   source, 
		   new String[] {
			 "name", "flashROM",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getFlashROM_PCPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "PCPath"
		   });			
		addAnnotation
		  (infoEClass, 
		   source, 
		   new String[] {
			 "name", "info",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getInfo_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getInfo_Key(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "key"
		   });		
		addAnnotation
		  (operatorTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "operator_._type"
		   });		
		addAnnotation
		  (operatorTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "operator_._type:Object",
			 "baseType", "operator_._type"
		   });		
		addAnnotation
		  (phaseEEnum, 
		   source, 
		   new String[] {
			 "name", "phase_._type"
		   });		
		addAnnotation
		  (phaseObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "phase_._type:Object",
			 "baseType", "phase_._type"
		   });			
		addAnnotation
		  (referenceEClass, 
		   source, 
		   new String[] {
			 "name", "reference",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getReference_Uri(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "uri"
		   });			
		addAnnotation
		  (retrieveFromSymbianEClass, 
		   source, 
		   new String[] {
			 "name", "retrieveFromSymbian",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRetrieveFromSymbian_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getRetrieveFromSymbian_Transfer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transfer",
			 "group", "#group:0"
		   });			
		addAnnotation
		  (rtestEClass, 
		   source, 
		   new String[] {
			 "name", "rtest",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getRtest_ResultFile(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "ResultFile"
		   });		
		addAnnotation
		  (getRtest_SymbianPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "SymbianPath"
		   });			
		addAnnotation
		  (statCommandEEnum, 
		   source, 
		   new String[] {
			 "name", "statCommand"
		   });		
		addAnnotation
		  (statCommandObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "statCommand:Object",
			 "baseType", "statCommand"
		   });			
		addAnnotation
		  (symbianPathEDataType, 
		   source, 
		   new String[] {
			 "name", "SymbianPath",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
			 "pattern", "[a-zA-Z$]:\\\\([^\\\\%20/%20:%20\\*%20\\?]+\\\\)*[^\\\\%20/%20:%20\\?]+"
		   });		
		addAnnotation
		  (targetTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "target_._type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
			 "pattern", "(\\S+\\.tcs)|([^%20:\\.]+)(\\:[^%20:\\.]+)?"
		   });			
		addAnnotation
		  (taskEClass, 
		   source, 
		   new String[] {
			 "name", "task",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTask_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTask_ExecuteOnPC(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "executeOnPC",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTask_TransferToSymbian(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transferToSymbian",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTask_ExecuteOnSymbian(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "executeOnSymbian",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTask_RetrieveFromSymbian(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "retrieveFromSymbian",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTask_Reference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "reference",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTask_Task(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTask_Flashrom(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "flashrom",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTask_StartTrace(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "startTrace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTask_StopTrace(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "stopTrace",
			 "group", "#group:0"
		   });			
		addAnnotation
		  (getTask_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTask_PreRebootDevice(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "preRebootDevice"
		   });			
		addAnnotation
		  (getTask_Timeout(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "timeout"
		   });		
		addAnnotation
		  (testCaseEClass, 
		   source, 
		   new String[] {
			 "name", "testCase",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTestCase_Target(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "target"
		   });		
		addAnnotation
		  (testCasesListEClass, 
		   source, 
		   new String[] {
			 "name", "testCasesList",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTestCasesList_TestCase(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "testCase"
		   });		
		addAnnotation
		  (getTestCasesList_Operator(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "operator"
		   });			
		addAnnotation
		  (testExecuteScriptEClass, 
		   source, 
		   new String[] {
			 "name", "testExecuteScript",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTestExecuteScript_TestCasesList(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "testCasesList"
		   });		
		addAnnotation
		  (getTestExecuteScript_PCPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "PCPath"
		   });		
		addAnnotation
		  (getTestExecuteScript_SymbianPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "SymbianPath"
		   });			
		addAnnotation
		  (transferEClass, 
		   source, 
		   new String[] {
			 "name", "transfer",
			 "kind", "empty"
		   });			
		addAnnotation
		  (getTransfer_Move(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "move"
		   });			
		addAnnotation
		  (getTransfer_PCPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "PCPath"
		   });			
		addAnnotation
		  (getTransfer_SymbianPath(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "SymbianPath"
		   });			
		addAnnotation
		  (transferToSymbianEClass, 
		   source, 
		   new String[] {
			 "name", "transferToSymbian",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTransferToSymbian_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTransferToSymbian_Transfer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transfer",
			 "group", "#group:0"
		   });			
		addAnnotation
		  (startTraceEClass, 
		   source, 
		   new String[] {
			 "name", "startTrace",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getStartTrace_EnablePrimaryFilters(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "enablePrimaryFilters"
		   });		
		addAnnotation
		  (getStartTrace_EnableSecondaryFilters(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "enableSecondaryFilters"
		   });		
		addAnnotation
		  (getStartTrace_DisablePrimaryFilters(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "disablePrimaryFilters"
		   });		
		addAnnotation
		  (getStartTrace_DisableSecondaryFilters(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "disableSecondaryFilters"
		   });		
		addAnnotation
		  (getStartTrace_ConfigFilePath(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "configFilePath"
		   });			
		addAnnotation
		  (stopTraceEClass, 
		   source, 
		   new String[] {
			 "name", "stopTrace",
			 "kind", "empty"
		   });
	}

} //DriverPackageImpl
