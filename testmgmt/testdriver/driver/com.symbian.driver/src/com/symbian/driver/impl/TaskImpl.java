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

import java.io.IOException;
import java.util.Collection;
import java.util.Stack;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.symbian.driver.DriverPackage;
import com.symbian.driver.ExecuteOnPC;
import com.symbian.driver.ExecuteOnSymbian;
import com.symbian.driver.FlashROM;
import com.symbian.driver.Reference;
import com.symbian.driver.RetrieveFromSymbian;
import com.symbian.driver.StartTrace;
import com.symbian.driver.StopTrace;
import com.symbian.driver.Task;
import com.symbian.driver.TransferToSymbian;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getExecuteOnPC <em>Execute On PC</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getTransferToSymbian <em>Transfer To Symbian</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getExecuteOnSymbian <em>Execute On Symbian</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getRetrieveFromSymbian <em>Retrieve From Symbian</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getTask <em>Task</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getFlashrom <em>Flashrom</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getStartTrace <em>Start Trace</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getStopTrace <em>Stop Trace</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#isPreRebootDevice <em>Pre Reboot Device</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TaskImpl#getTimeout <em>Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskImpl extends EObjectImpl implements Task {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isPreRebootDevice() <em>Pre Reboot Device</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreRebootDevice()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRE_REBOOT_DEVICE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPreRebootDevice() <em>Pre Reboot Device</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreRebootDevice()
	 * @generated
	 * @ordered
	 */
	protected boolean preRebootDevice = PRE_REBOOT_DEVICE_EDEFAULT;

	/**
	 * This is true if the Pre Reboot Device attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean preRebootDeviceESet;

	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMEOUT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected int timeout = TIMEOUT_EDEFAULT;

	/**
	 * This is true if the Timeout attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean timeoutESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, DriverPackage.TASK__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecuteOnPC> getExecuteOnPC() {
		return getGroup().list(DriverPackage.Literals.TASK__EXECUTE_ON_PC);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransferToSymbian> getTransferToSymbian() {
		return getGroup().list(DriverPackage.Literals.TASK__TRANSFER_TO_SYMBIAN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecuteOnSymbian> getExecuteOnSymbian() {
		return getGroup().list(DriverPackage.Literals.TASK__EXECUTE_ON_SYMBIAN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RetrieveFromSymbian> getRetrieveFromSymbian() {
		return getGroup().list(DriverPackage.Literals.TASK__RETRIEVE_FROM_SYMBIAN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Reference> getReference() {
		return getGroup().list(DriverPackage.Literals.TASK__REFERENCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Task> getTask() {
		return getGroup().list(DriverPackage.Literals.TASK__TASK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FlashROM> getFlashrom() {
		return getGroup().list(DriverPackage.Literals.TASK__FLASHROM);
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TASK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPreRebootDevice() {
		return preRebootDevice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreRebootDevice(boolean newPreRebootDevice) {
		boolean oldPreRebootDevice = preRebootDevice;
		preRebootDevice = newPreRebootDevice;
		boolean oldPreRebootDeviceESet = preRebootDeviceESet;
		preRebootDeviceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TASK__PRE_REBOOT_DEVICE, oldPreRebootDevice, preRebootDevice, !oldPreRebootDeviceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPreRebootDevice() {
		boolean oldPreRebootDevice = preRebootDevice;
		boolean oldPreRebootDeviceESet = preRebootDeviceESet;
		preRebootDevice = PRE_REBOOT_DEVICE_EDEFAULT;
		preRebootDeviceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.TASK__PRE_REBOOT_DEVICE, oldPreRebootDevice, PRE_REBOOT_DEVICE_EDEFAULT, oldPreRebootDeviceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPreRebootDevice() {
		return preRebootDeviceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeout(int newTimeout) {
		int oldTimeout = timeout;
		timeout = newTimeout;
		boolean oldTimeoutESet = timeoutESet;
		timeoutESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TASK__TIMEOUT, oldTimeout, timeout, !oldTimeoutESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTimeout() {
		int oldTimeout = timeout;
		boolean oldTimeoutESet = timeoutESet;
		timeout = TIMEOUT_EDEFAULT;
		timeoutESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.TASK__TIMEOUT, oldTimeout, TIMEOUT_EDEFAULT, oldTimeoutESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTimeout() {
		return timeoutESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartTrace getStartTrace() {
		return (StartTrace)getGroup().get(DriverPackage.Literals.TASK__START_TRACE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartTrace(StartTrace newStartTrace, NotificationChain msgs) {
		return ((FeatureMap.Internal)getGroup()).basicAdd(DriverPackage.Literals.TASK__START_TRACE, newStartTrace, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartTrace(StartTrace newStartTrace) {
		((FeatureMap.Internal)getGroup()).set(DriverPackage.Literals.TASK__START_TRACE, newStartTrace);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StopTrace getStopTrace() {
		return (StopTrace)getGroup().get(DriverPackage.Literals.TASK__STOP_TRACE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStopTrace(StopTrace newStopTrace, NotificationChain msgs) {
		return ((FeatureMap.Internal)getGroup()).basicAdd(DriverPackage.Literals.TASK__STOP_TRACE, newStopTrace, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStopTrace(StopTrace newStopTrace) {
		((FeatureMap.Internal)getGroup()).set(DriverPackage.Literals.TASK__STOP_TRACE, newStopTrace);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DriverPackage.TASK__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case DriverPackage.TASK__EXECUTE_ON_PC:
				return ((InternalEList<?>)getExecuteOnPC()).basicRemove(otherEnd, msgs);
			case DriverPackage.TASK__TRANSFER_TO_SYMBIAN:
				return ((InternalEList<?>)getTransferToSymbian()).basicRemove(otherEnd, msgs);
			case DriverPackage.TASK__EXECUTE_ON_SYMBIAN:
				return ((InternalEList<?>)getExecuteOnSymbian()).basicRemove(otherEnd, msgs);
			case DriverPackage.TASK__RETRIEVE_FROM_SYMBIAN:
				return ((InternalEList<?>)getRetrieveFromSymbian()).basicRemove(otherEnd, msgs);
			case DriverPackage.TASK__REFERENCE:
				return ((InternalEList<?>)getReference()).basicRemove(otherEnd, msgs);
			case DriverPackage.TASK__TASK:
				return ((InternalEList<?>)getTask()).basicRemove(otherEnd, msgs);
			case DriverPackage.TASK__FLASHROM:
				return ((InternalEList<?>)getFlashrom()).basicRemove(otherEnd, msgs);
			case DriverPackage.TASK__START_TRACE:
				return basicSetStartTrace(null, msgs);
			case DriverPackage.TASK__STOP_TRACE:
				return basicSetStopTrace(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DriverPackage.TASK__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case DriverPackage.TASK__EXECUTE_ON_PC:
				return getExecuteOnPC();
			case DriverPackage.TASK__TRANSFER_TO_SYMBIAN:
				return getTransferToSymbian();
			case DriverPackage.TASK__EXECUTE_ON_SYMBIAN:
				return getExecuteOnSymbian();
			case DriverPackage.TASK__RETRIEVE_FROM_SYMBIAN:
				return getRetrieveFromSymbian();
			case DriverPackage.TASK__REFERENCE:
				return getReference();
			case DriverPackage.TASK__TASK:
				return getTask();
			case DriverPackage.TASK__FLASHROM:
				return getFlashrom();
			case DriverPackage.TASK__START_TRACE:
				return getStartTrace();
			case DriverPackage.TASK__STOP_TRACE:
				return getStopTrace();
			case DriverPackage.TASK__NAME:
				return getName();
			case DriverPackage.TASK__PRE_REBOOT_DEVICE:
				return isPreRebootDevice() ? Boolean.TRUE : Boolean.FALSE;
			case DriverPackage.TASK__TIMEOUT:
				return new Integer(getTimeout());
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
			case DriverPackage.TASK__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case DriverPackage.TASK__EXECUTE_ON_PC:
				getExecuteOnPC().clear();
				getExecuteOnPC().addAll((Collection<? extends ExecuteOnPC>)newValue);
				return;
			case DriverPackage.TASK__TRANSFER_TO_SYMBIAN:
				getTransferToSymbian().clear();
				getTransferToSymbian().addAll((Collection<? extends TransferToSymbian>)newValue);
				return;
			case DriverPackage.TASK__EXECUTE_ON_SYMBIAN:
				getExecuteOnSymbian().clear();
				getExecuteOnSymbian().addAll((Collection<? extends ExecuteOnSymbian>)newValue);
				return;
			case DriverPackage.TASK__RETRIEVE_FROM_SYMBIAN:
				getRetrieveFromSymbian().clear();
				getRetrieveFromSymbian().addAll((Collection<? extends RetrieveFromSymbian>)newValue);
				return;
			case DriverPackage.TASK__REFERENCE:
				getReference().clear();
				getReference().addAll((Collection<? extends Reference>)newValue);
				return;
			case DriverPackage.TASK__TASK:
				getTask().clear();
				getTask().addAll((Collection<? extends Task>)newValue);
				return;
			case DriverPackage.TASK__FLASHROM:
				getFlashrom().clear();
				getFlashrom().addAll((Collection<? extends FlashROM>)newValue);
				return;
			case DriverPackage.TASK__START_TRACE:
				setStartTrace((StartTrace)newValue);
				return;
			case DriverPackage.TASK__STOP_TRACE:
				setStopTrace((StopTrace)newValue);
				return;
			case DriverPackage.TASK__NAME:
				setName((String)newValue);
				return;
			case DriverPackage.TASK__PRE_REBOOT_DEVICE:
				setPreRebootDevice(((Boolean)newValue).booleanValue());
				return;
			case DriverPackage.TASK__TIMEOUT:
				setTimeout(((Integer)newValue).intValue());
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
			case DriverPackage.TASK__GROUP:
				getGroup().clear();
				return;
			case DriverPackage.TASK__EXECUTE_ON_PC:
				getExecuteOnPC().clear();
				return;
			case DriverPackage.TASK__TRANSFER_TO_SYMBIAN:
				getTransferToSymbian().clear();
				return;
			case DriverPackage.TASK__EXECUTE_ON_SYMBIAN:
				getExecuteOnSymbian().clear();
				return;
			case DriverPackage.TASK__RETRIEVE_FROM_SYMBIAN:
				getRetrieveFromSymbian().clear();
				return;
			case DriverPackage.TASK__REFERENCE:
				getReference().clear();
				return;
			case DriverPackage.TASK__TASK:
				getTask().clear();
				return;
			case DriverPackage.TASK__FLASHROM:
				getFlashrom().clear();
				return;
			case DriverPackage.TASK__START_TRACE:
				setStartTrace((StartTrace)null);
				return;
			case DriverPackage.TASK__STOP_TRACE:
				setStopTrace((StopTrace)null);
				return;
			case DriverPackage.TASK__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DriverPackage.TASK__PRE_REBOOT_DEVICE:
				unsetPreRebootDevice();
				return;
			case DriverPackage.TASK__TIMEOUT:
				unsetTimeout();
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
			case DriverPackage.TASK__GROUP:
				return group != null && !group.isEmpty();
			case DriverPackage.TASK__EXECUTE_ON_PC:
				return !getExecuteOnPC().isEmpty();
			case DriverPackage.TASK__TRANSFER_TO_SYMBIAN:
				return !getTransferToSymbian().isEmpty();
			case DriverPackage.TASK__EXECUTE_ON_SYMBIAN:
				return !getExecuteOnSymbian().isEmpty();
			case DriverPackage.TASK__RETRIEVE_FROM_SYMBIAN:
				return !getRetrieveFromSymbian().isEmpty();
			case DriverPackage.TASK__REFERENCE:
				return !getReference().isEmpty();
			case DriverPackage.TASK__TASK:
				return !getTask().isEmpty();
			case DriverPackage.TASK__FLASHROM:
				return !getFlashrom().isEmpty();
			case DriverPackage.TASK__START_TRACE:
				return getStartTrace() != null;
			case DriverPackage.TASK__STOP_TRACE:
				return getStopTrace() != null;
			case DriverPackage.TASK__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DriverPackage.TASK__PRE_REBOOT_DEVICE:
				return isSetPreRebootDevice();
			case DriverPackage.TASK__TIMEOUT:
				return isSetTimeout();
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
		result.append(" (group: ");
		result.append(group);
		result.append(", name: ");
		result.append(name);
		result.append(", preRebootDevice: ");
		if (preRebootDeviceESet) result.append(preRebootDevice); else result.append("<unset>");
		result.append(", timeout: ");
		if (timeoutESet) result.append(timeout); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The level of the current task. Starting at 0.
	 * @generated NOT
	 */
	public int getLevel() {
		return getLevel(this);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The level of the current task. Starting at 0.
	 * @generated NOT
	 */
	private int getLevel(EObject aEObject) {
		if (aEObject.eContainer() instanceof Task) {
			return getLevel(aEObject.eContainer()) + 1;
		}
		
		return 0;
	}
	
	/**
	 * Attains the address of the current task in the EMF tree.
	 * 
	 * @param aTask
	 *            The task to get the full path/address for.
	 * @return The string corresponding to the full path/address.
	 * @generated NOT
	 */
	public String getAddress() {
		String lCurrentAddress = "";

		Task lAddress = this;

		while (lAddress != null) {
			lCurrentAddress = lAddress.getName() + "/" + lCurrentAddress;

			if (lAddress.eContainer() instanceof Task) {
				lAddress = (Task) lAddress.eContainer();
			} else {
				lAddress = null;
			}

		}

		return lCurrentAddress;
	}
	
	private Object iTransferSet = null;
	
	public void setTransferSet(Object aTransferSet) throws IOException {
		iTransferSet = aTransferSet;
	}

	public Object getTransferSet() {
		if (iTransferSet == null) {
			throw new NullPointerException("Transfer Set hasn't been set for Task: " + getName());
		}
		
		return iTransferSet;
	}
	
	Stack<Object> iExecuteSet = new Stack<Object>();

	public Stack<Object> getExecuteSet() {
		return iExecuteSet;
	}

} //TaskImpl