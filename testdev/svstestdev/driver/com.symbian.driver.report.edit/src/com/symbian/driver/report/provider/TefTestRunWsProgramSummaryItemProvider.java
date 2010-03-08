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




package com.symbian.driver.report.provider;


import com.symbian.driver.report.ReportFactory;
import com.symbian.driver.report.ReportPackage;
import com.symbian.driver.report.TefTestCaseSummary;
import com.symbian.driver.report.TefTestRunWsProgram;
import com.symbian.driver.report.TefTestRunWsProgramSummary;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.symbian.driver.report.TefTestRunWsProgramSummary} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TefTestRunWsProgramSummaryItemProvider
	extends ItemProviderAdapter
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource,
		ITableItemLabelProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestRunWsProgramSummaryItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addAbortPropertyDescriptor(object);
			addCountPropertyDescriptor(object);
			addFailPropertyDescriptor(object);
			addInconclusivePropertyDescriptor(object);
			addPanicPropertyDescriptor(object);
			addPassPropertyDescriptor(object);
			addUnexecutedPropertyDescriptor(object);
			addUnknownPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Abort feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAbortPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TefTestRunWsProgramSummary_abort_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TefTestRunWsProgramSummary_abort_feature", "_UI_TefTestRunWsProgramSummary_type"),
				 ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Count feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TefTestRunWsProgramSummary_count_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TefTestRunWsProgramSummary_count_feature", "_UI_TefTestRunWsProgramSummary_type"),
				 ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Fail feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFailPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TefTestRunWsProgramSummary_fail_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TefTestRunWsProgramSummary_fail_feature", "_UI_TefTestRunWsProgramSummary_type"),
				 ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Inconclusive feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInconclusivePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TefTestRunWsProgramSummary_inconclusive_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TefTestRunWsProgramSummary_inconclusive_feature", "_UI_TefTestRunWsProgramSummary_type"),
				 ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Panic feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPanicPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TefTestRunWsProgramSummary_panic_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TefTestRunWsProgramSummary_panic_feature", "_UI_TefTestRunWsProgramSummary_type"),
				 ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Pass feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TefTestRunWsProgramSummary_pass_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TefTestRunWsProgramSummary_pass_feature", "_UI_TefTestRunWsProgramSummary_type"),
				 ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Unexecuted feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUnexecutedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TefTestRunWsProgramSummary_unexecuted_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TefTestRunWsProgramSummary_unexecuted_feature", "_UI_TefTestRunWsProgramSummary_type"),
				 ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Unknown feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUnknownPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TefTestRunWsProgramSummary_unknown_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TefTestRunWsProgramSummary_unknown_feature", "_UI_TefTestRunWsProgramSummary_type"),
				 ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN,
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
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE);
		}
		return childrenFeatures;
	}

	/**
	 * This returns TefTestRunWsProgramSummary.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TefTestRunWsProgramSummary"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		TefTestRunWsProgramSummary tefTestRunWsProgramSummary = (TefTestRunWsProgramSummary)object;
		return getString("_UI_TefTestRunWsProgramSummary_type") + " " + tefTestRunWsProgramSummary.getAbort();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(TefTestRunWsProgramSummary.class)) {
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT:
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT:
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL:
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE:
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC:
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS:
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED:
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE,
				 ReportFactory.eINSTANCE.createTefTestRunWsProgram()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return ResultEditPlugin.INSTANCE;
	}

	/**
	 * @generated NOT
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		return null;
	}

	/**
	 * @generated NOT
	 */
	public String getColumnText(Object object, int columnIndex) {
		//all what is requested for the moment is Pass/Fail
		if (object instanceof TefTestRunWsProgramSummary) {
			TefTestRunWsProgramSummary lTefTestCaseSummary = (TefTestRunWsProgramSummary) object;
			if (lTefTestCaseSummary.getFail() != 0 || lTefTestCaseSummary.getPanic() != 0) {
				return "fail";
			} else {
				return "pass";
			}
		}
		return "";		
	}

}
