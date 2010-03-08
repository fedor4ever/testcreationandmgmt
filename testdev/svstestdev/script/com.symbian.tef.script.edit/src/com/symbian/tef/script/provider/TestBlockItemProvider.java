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


package com.symbian.tef.script.provider;


import com.symbian.tef.script.ScriptFactory;
import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.TestBlock;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

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
 * This is the item provider adapter for a {@link com.symbian.tef.script.TestBlock} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TestBlockItemProvider
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
	public TestBlockItemProvider(AdapterFactory adapterFactory) {
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

			addHeapPropertyDescriptor(object);
			addTimeoutPropertyDescriptor(object);
			addServerPropertyDescriptor(object);
			addIniFilePropertyDescriptor(object);
			addPanicCodePropertyDescriptor(object);
			addPanicStringPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Heap feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHeapPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TestBlock_Heap_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TestBlock_Heap_feature", "_UI_TestBlock_type"),
				 ScriptPackage.Literals.TEST_BLOCK__HEAP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
				 getString("_UI_TestBlock_timeout_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TestBlock_timeout_feature", "_UI_TestBlock_type"),
				 ScriptPackage.Literals.TEST_BLOCK__TIMEOUT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Server feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addServerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TestBlock_server_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TestBlock_server_feature", "_UI_TestBlock_type"),
				 ScriptPackage.Literals.TEST_BLOCK__SERVER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Panic Code feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPanicCodePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TestBlock_PanicCode_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TestBlock_PanicCode_feature", "_UI_TestBlock_type"),
				 ScriptPackage.Literals.TEST_BLOCK__PANIC_CODE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Panic String feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPanicStringPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TestBlock_PanicString_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TestBlock_PanicString_feature", "_UI_TestBlock_type"),
				 ScriptPackage.Literals.TEST_BLOCK__PANIC_STRING,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Ini File feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIniFilePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TestBlock_iniFile_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TestBlock_iniFile_feature", "_UI_TestBlock_type"),
				 ScriptPackage.Literals.TEST_BLOCK__INI_FILE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ScriptPackage.Literals.CONTAINER__TEF);
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
	 * This returns TestBlock.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TestBlock"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((TestBlock)object).getIniFile();
		return label == null || label.length() == 0 ?
			getString("_UI_TestBlock_type") :
			getString("_UI_TestBlock_type") + " " + label;
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

		switch (notification.getFeatureID(TestBlock.class)) {
			case ScriptPackage.TEST_BLOCK__HEAP:
			case ScriptPackage.TEST_BLOCK__TIMEOUT:
			case ScriptPackage.TEST_BLOCK__SERVER:
			case ScriptPackage.TEST_BLOCK__INI_FILE:
			case ScriptPackage.TEST_BLOCK__PANIC_CODE:
			case ScriptPackage.TEST_BLOCK__PANIC_STRING:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ScriptPackage.TEST_BLOCK__TEF:
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
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createTefComment()));

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createCreateObject()));

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createRestoreObject()));

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createCommand()));

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createStore()));

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createOutstanding()));
		
		newChildDescriptors.add
		(createChildParameter
			(ScriptPackage.Literals.CONTAINER__TEF,
			 ScriptFactory.eINSTANCE.createDelay()));

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createAsyncDelay()));

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createSharedActiveScheduler()));

		newChildDescriptors.add
			(createChildParameter
				(ScriptPackage.Literals.CONTAINER__TEF,
				 ScriptFactory.eINSTANCE.createStoreActiveScheduler()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return TefEditPlugin.INSTANCE;
	}

}
