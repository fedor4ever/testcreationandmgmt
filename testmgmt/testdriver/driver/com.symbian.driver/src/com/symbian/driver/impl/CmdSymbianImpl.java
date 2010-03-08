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

import com.symbian.driver.CmdSymbian;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.StatCommand;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cmd Symbian</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.CmdSymbianImpl#getArgument <em>Argument</em>}</li>
 *   <li>{@link com.symbian.driver.impl.CmdSymbianImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link com.symbian.driver.impl.CmdSymbianImpl#getStatCommand <em>Stat Command</em>}</li>
 *   <li>{@link com.symbian.driver.impl.CmdSymbianImpl#isSync <em>Sync</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CmdSymbianImpl extends EObjectImpl implements CmdSymbian {
	/**
	 * The cached value of the '{@link #getArgument() <em>Argument</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArgument()
	 * @generated
	 * @ordered
	 */
	protected EList<String> argument;

	/**
	 * The default value of the '{@link #getOutput() <em>Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutput()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutput() <em>Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutput()
	 * @generated
	 * @ordered
	 */
	protected String output = OUTPUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatCommand() <em>Stat Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatCommand()
	 * @generated
	 * @ordered
	 */
	protected static final StatCommand STAT_COMMAND_EDEFAULT = StatCommand.CREATE_FOLDER;

	/**
	 * The cached value of the '{@link #getStatCommand() <em>Stat Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatCommand()
	 * @generated
	 * @ordered
	 */
	protected StatCommand statCommand = STAT_COMMAND_EDEFAULT;

	/**
	 * This is true if the Stat Command attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean statCommandESet;

	/**
	 * The default value of the '{@link #isSync() <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSync()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNC_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSync() <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSync()
	 * @generated
	 * @ordered
	 */
	protected boolean sync = SYNC_EDEFAULT;

	/**
	 * This is true if the Sync attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean syncESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CmdSymbianImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.CMD_SYMBIAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getArgument() {
		if (argument == null) {
			argument = new EDataTypeEList<String>(String.class, this, DriverPackage.CMD_SYMBIAN__ARGUMENT);
		}
		return argument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutput(String newOutput) {
		String oldOutput = output;
		output = newOutput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.CMD_SYMBIAN__OUTPUT, oldOutput, output));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatCommand getStatCommand() {
		return statCommand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatCommand(StatCommand newStatCommand) {
		StatCommand oldStatCommand = statCommand;
		statCommand = newStatCommand == null ? STAT_COMMAND_EDEFAULT : newStatCommand;
		boolean oldStatCommandESet = statCommandESet;
		statCommandESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.CMD_SYMBIAN__STAT_COMMAND, oldStatCommand, statCommand, !oldStatCommandESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetStatCommand() {
		StatCommand oldStatCommand = statCommand;
		boolean oldStatCommandESet = statCommandESet;
		statCommand = STAT_COMMAND_EDEFAULT;
		statCommandESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.CMD_SYMBIAN__STAT_COMMAND, oldStatCommand, STAT_COMMAND_EDEFAULT, oldStatCommandESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetStatCommand() {
		return statCommandESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSync() {
		return sync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSync(boolean newSync) {
		boolean oldSync = sync;
		sync = newSync;
		boolean oldSyncESet = syncESet;
		syncESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.CMD_SYMBIAN__SYNC, oldSync, sync, !oldSyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSync() {
		boolean oldSync = sync;
		boolean oldSyncESet = syncESet;
		sync = SYNC_EDEFAULT;
		syncESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.CMD_SYMBIAN__SYNC, oldSync, SYNC_EDEFAULT, oldSyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSync() {
		return syncESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DriverPackage.CMD_SYMBIAN__ARGUMENT:
				return getArgument();
			case DriverPackage.CMD_SYMBIAN__OUTPUT:
				return getOutput();
			case DriverPackage.CMD_SYMBIAN__STAT_COMMAND:
				return getStatCommand();
			case DriverPackage.CMD_SYMBIAN__SYNC:
				return isSync() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DriverPackage.CMD_SYMBIAN__ARGUMENT:
				getArgument().clear();
				getArgument().addAll((Collection<? extends String>)newValue);
				return;
			case DriverPackage.CMD_SYMBIAN__OUTPUT:
				setOutput((String)newValue);
				return;
			case DriverPackage.CMD_SYMBIAN__STAT_COMMAND:
				setStatCommand((StatCommand)newValue);
				return;
			case DriverPackage.CMD_SYMBIAN__SYNC:
				setSync(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DriverPackage.CMD_SYMBIAN__ARGUMENT:
				getArgument().clear();
				return;
			case DriverPackage.CMD_SYMBIAN__OUTPUT:
				setOutput(OUTPUT_EDEFAULT);
				return;
			case DriverPackage.CMD_SYMBIAN__STAT_COMMAND:
				unsetStatCommand();
				return;
			case DriverPackage.CMD_SYMBIAN__SYNC:
				unsetSync();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DriverPackage.CMD_SYMBIAN__ARGUMENT:
				return argument != null && !argument.isEmpty();
			case DriverPackage.CMD_SYMBIAN__OUTPUT:
				return OUTPUT_EDEFAULT == null ? output != null : !OUTPUT_EDEFAULT.equals(output);
			case DriverPackage.CMD_SYMBIAN__STAT_COMMAND:
				return isSetStatCommand();
			case DriverPackage.CMD_SYMBIAN__SYNC:
				return isSetSync();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (argument: ");
		result.append(argument);
		result.append(", output: ");
		result.append(output);
		result.append(", statCommand: ");
		if (statCommandESet) result.append(statCommand); else result.append("<unset>");
		result.append(", sync: ");
		if (syncESet) result.append(sync); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //CmdSymbianImpl