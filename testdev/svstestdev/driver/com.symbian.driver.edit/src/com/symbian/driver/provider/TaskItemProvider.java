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


package com.symbian.driver.provider;


import com.symbian.driver.DriverFactory;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.Task;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.FeatureMapUtil;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.symbian.driver.Task} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TaskItemProvider
	extends ItemProviderAdapter
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addPreRebootDevicePropertyDescriptor(object);
			addTimeoutPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_name_feature", "_UI_Task_type"),
				 DriverPackage.Literals.TASK__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Pre Reboot Device feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPreRebootDevicePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_preRebootDevice_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_preRebootDevice_feature", "_UI_Task_type"),
				 DriverPackage.Literals.TASK__PRE_REBOOT_DEVICE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Timeout feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTimeoutPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_timeout_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_timeout_feature", "_UI_Task_type"),
				 DriverPackage.Literals.TASK__TIMEOUT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(DriverPackage.Literals.TASK__GROUP);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Task.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Task"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Task)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Task_type") :
			getString("_UI_Task_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Task.class)) {
			case DriverPackage.TASK__NAME:
			case DriverPackage.TASK__PRE_REBOOT_DEVICE:
			case DriverPackage.TASK__TIMEOUT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case DriverPackage.TASK__GROUP:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(DriverPackage.Literals.TASK__GROUP,
				 FeatureMapUtil.createEntry
					(DriverPackage.Literals.TASK__EXECUTE_ON_PC,
					 DriverFactory.eINSTANCE.createExecuteOnPC())));

		newChildDescriptors.add
			(createChildParameter
				(DriverPackage.Literals.TASK__GROUP,
				 FeatureMapUtil.createEntry
					(DriverPackage.Literals.TASK__TRANSFER_TO_SYMBIAN,
					 DriverFactory.eINSTANCE.createTransferToSymbian())));

		newChildDescriptors.add
			(createChildParameter
				(DriverPackage.Literals.TASK__GROUP,
				 FeatureMapUtil.createEntry
					(DriverPackage.Literals.TASK__EXECUTE_ON_SYMBIAN,
					 DriverFactory.eINSTANCE.createExecuteOnSymbian())));

		newChildDescriptors.add
			(createChildParameter
				(DriverPackage.Literals.TASK__GROUP,
				 FeatureMapUtil.createEntry
					(DriverPackage.Literals.TASK__RETRIEVE_FROM_SYMBIAN,
					 DriverFactory.eINSTANCE.createRetrieveFromSymbian())));

		newChildDescriptors.add
			(createChildParameter
				(DriverPackage.Literals.TASK__GROUP,
				 FeatureMapUtil.createEntry
					(DriverPackage.Literals.TASK__REFERENCE,
					 DriverFactory.eINSTANCE.createReference())));

		newChildDescriptors.add
			(createChildParameter
				(DriverPackage.Literals.TASK__GROUP,
				 FeatureMapUtil.createEntry
					(DriverPackage.Literals.TASK__TASK,
					 DriverFactory.eINSTANCE.createTask())));

		newChildDescriptors.add
			(createChildParameter
				(DriverPackage.Literals.TASK__GROUP,
				 FeatureMapUtil.createEntry
					(DriverPackage.Literals.TASK__FLASHROM,
					 DriverFactory.eINSTANCE.createFlashROM())));

		newChildDescriptors.add
			(createChildParameter
				(DriverPackage.Literals.TASK__GROUP,
				 FeatureMapUtil.createEntry
					(DriverPackage.Literals.TASK__START_TRACE,
					 DriverFactory.eINSTANCE.createStartTrace())));
		
		newChildDescriptors.add
		(createChildParameter
			(DriverPackage.Literals.TASK__GROUP,
			 FeatureMapUtil.createEntry
				(DriverPackage.Literals.TASK__STOP_TRACE,
				 DriverFactory.eINSTANCE.createStopTrace())));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return DriverEditPlugin.INSTANCE;
	}

}
