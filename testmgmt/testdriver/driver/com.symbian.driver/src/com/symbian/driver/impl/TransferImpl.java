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

import com.symbian.driver.DriverPackage;
import com.symbian.driver.Transfer;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transfer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.TransferImpl#isMove <em>Move</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TransferImpl#getPCPath <em>PC Path</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TransferImpl#getSymbianPath <em>Symbian Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransferImpl extends EObjectImpl implements Transfer {
	/**
	 * The default value of the '{@link #isMove() <em>Move</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMove()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MOVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMove() <em>Move</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMove()
	 * @generated
	 * @ordered
	 */
	protected boolean move = MOVE_EDEFAULT;

	/**
	 * This is true if the Move attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean moveESet;

	/**
	 * The default value of the '{@link #getPCPath() <em>PC Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPCPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PC_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPCPath() <em>PC Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPCPath()
	 * @generated
	 * @ordered
	 */
	protected String pCPath = PC_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getSymbianPath() <em>Symbian Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbianPath()
	 * @generated
	 * @ordered
	 */
	protected static final String SYMBIAN_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSymbianPath() <em>Symbian Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbianPath()
	 * @generated
	 * @ordered
	 */
	protected String symbianPath = SYMBIAN_PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransferImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.TRANSFER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMove() {
		return move;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMove(boolean newMove) {
		boolean oldMove = move;
		move = newMove;
		boolean oldMoveESet = moveESet;
		moveESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TRANSFER__MOVE, oldMove, move, !oldMoveESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMove() {
		boolean oldMove = move;
		boolean oldMoveESet = moveESet;
		move = MOVE_EDEFAULT;
		moveESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.TRANSFER__MOVE, oldMove, MOVE_EDEFAULT, oldMoveESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMove() {
		return moveESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPCPath() {
		return pCPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPCPath(String newPCPath) {
		String oldPCPath = pCPath;
		pCPath = newPCPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TRANSFER__PC_PATH, oldPCPath, pCPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSymbianPath() {
		return symbianPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSymbianPath(String newSymbianPath) {
		String oldSymbianPath = symbianPath;
		symbianPath = newSymbianPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TRANSFER__SYMBIAN_PATH, oldSymbianPath, symbianPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DriverPackage.TRANSFER__MOVE:
				return isMove() ? Boolean.TRUE : Boolean.FALSE;
			case DriverPackage.TRANSFER__PC_PATH:
				return getPCPath();
			case DriverPackage.TRANSFER__SYMBIAN_PATH:
				return getSymbianPath();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DriverPackage.TRANSFER__MOVE:
				setMove(((Boolean)newValue).booleanValue());
				return;
			case DriverPackage.TRANSFER__PC_PATH:
				setPCPath((String)newValue);
				return;
			case DriverPackage.TRANSFER__SYMBIAN_PATH:
				setSymbianPath((String)newValue);
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
			case DriverPackage.TRANSFER__MOVE:
				unsetMove();
				return;
			case DriverPackage.TRANSFER__PC_PATH:
				setPCPath(PC_PATH_EDEFAULT);
				return;
			case DriverPackage.TRANSFER__SYMBIAN_PATH:
				setSymbianPath(SYMBIAN_PATH_EDEFAULT);
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
			case DriverPackage.TRANSFER__MOVE:
				return isSetMove();
			case DriverPackage.TRANSFER__PC_PATH:
				return PC_PATH_EDEFAULT == null ? pCPath != null : !PC_PATH_EDEFAULT.equals(pCPath);
			case DriverPackage.TRANSFER__SYMBIAN_PATH:
				return SYMBIAN_PATH_EDEFAULT == null ? symbianPath != null : !SYMBIAN_PATH_EDEFAULT.equals(symbianPath);
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
		result.append(" (move: ");
		if (moveESet) result.append(move); else result.append("<unset>");
		result.append(", pCPath: ");
		result.append(pCPath);
		result.append(", symbianPath: ");
		result.append(symbianPath);
		result.append(')');
		return result.toString();
	}

} //TransferImpl