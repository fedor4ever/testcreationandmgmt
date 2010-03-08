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


import java.io.File;
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

import com.symbian.driver.report.BaseReport;
import com.symbian.driver.report.DocumentRoot;
import com.symbian.driver.report.GenericReport;
import com.symbian.driver.report.Report;
import com.symbian.driver.report.ReportFactory;
import com.symbian.driver.report.ReportPackage;
import com.symbian.driver.report.TefReport;
import com.symbian.driver.report.TefTestCaseSummary;
import com.symbian.driver.report.TefTestRunWsProgramSummary;

/**
 * This is the item provider adapter for a {@link com.symbian.driver.report.BaseReport} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
/**
 * @author HocineA
 *
 */
public class BaseReportItemProvider
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
	public BaseReportItemProvider(AdapterFactory adapterFactory) {
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

			addDurationPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addTaskPropertyDescriptor(object);
			addTimeoutPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Duration feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDurationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BaseReport_duration_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BaseReport_duration_feature", "_UI_BaseReport_type"),
				 ReportPackage.Literals.BASE_REPORT__DURATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
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
				 getString("_UI_BaseReport_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BaseReport_name_feature", "_UI_BaseReport_type"),
				 ReportPackage.Literals.BASE_REPORT__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Task feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTaskPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BaseReport_task_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BaseReport_task_feature", "_UI_BaseReport_type"),
				 ReportPackage.Literals.BASE_REPORT__TASK,
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
				 getString("_UI_BaseReport_timeout_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BaseReport_timeout_feature", "_UI_BaseReport_type"),
				 ReportPackage.Literals.BASE_REPORT__TIMEOUT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
			childrenFeatures.add(ReportPackage.Literals.BASE_REPORT__EXECPTION_REPORT);
		}
		return childrenFeatures;
	}

	/**
	 * This returns BaseReport.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/BaseReport"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((BaseReport)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_BaseReport_type") :
			getString("_UI_BaseReport_type") + " " + label;
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

		switch (notification.getFeatureID(BaseReport.class)) {
			case ReportPackage.BASE_REPORT__DURATION:
			case ReportPackage.BASE_REPORT__NAME:
			case ReportPackage.BASE_REPORT__TASK:
			case ReportPackage.BASE_REPORT__TIMEOUT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ReportPackage.BASE_REPORT__EXECPTION_REPORT:
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
				(ReportPackage.Literals.BASE_REPORT__EXECPTION_REPORT,
				 ReportFactory.eINSTANCE.createExceptionReport()));
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

		if (object instanceof TefReport) {
			switch (columnIndex) {
			case 6:				
				TefReport lReport = (TefReport) object;			    
				return new File(lReport.getLog());				
			default:
				break;
			}
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	public String getColumnText(Object object, int columnIndex) {

			String lReturn = "";
			if (object instanceof GenericReport) {
				GenericReport lReport = (GenericReport) object;
			switch (columnIndex) {
			case 0:
				//task name
				lReturn = lReport.getTask();
				break;
			case 1:
				//operation
				DocumentRoot lRoot = (DocumentRoot) lReport.eResource().getContents().get(0);
				String lCommand = (String) lRoot.getReport().getReportInfo().getInfo().get("Command");
				lReturn = lCommand + " : " +lReport.getName();
				break;
			case 2: 
				//duration
				lReturn = lReport.getDuration();
				break;
			case 3:
				//timeout
				lReturn = lReport.isTimeout() ? "Yes" : "No";
				break;
			case 4:
				//result
				lReturn = lReport.getResult().getLiteral();
				break;
			case 5:
				//log
				lReturn = lReport.getLog();
				break;

			default:
				break;
			}
			} else if (object instanceof TefReport) {
				TefReport lReport = (TefReport)object;
				switch (columnIndex) {
				case 0:
					//task name
					lReturn = lReport.getTask();
					break;
				case 1:
					//operation
					DocumentRoot lRoot = (DocumentRoot) lReport.eResource().getContents().get(0);
					String lCommand = (String) lRoot.getReport().getReportInfo().getInfo().get("Command");
					lReturn = lCommand + " : " +lReport.getName();
					break;
				case 2: 
					//duration
					lReturn = lReport.getDuration();
					break;
				case 3:
					//timeout
					lReturn = lReport.isTimeout() ? "Yes" : "No";
					break;
				case 4:
					//TEF result
					TefTestCaseSummary lTefSummary = lReport.getTefTestCaseSummary();
					if (lTefSummary != null) {
					ITableItemLabelProvider lItemLabelProvider = (ITableItemLabelProvider) adapterFactory.adapt(lTefSummary, IItemLabelProvider.class);
					//see TefTestCaseSummaryItemProvider
					lReturn = lItemLabelProvider.getColumnText(lTefSummary,4);
					}
					break;
				case 5:
					//TEF RunWsProgram	
					TefTestRunWsProgramSummary lTefRunWsSummary = lReport.getTefTestRunWsProgramSummary();
					if (lTefRunWsSummary != null) {
						ITableItemLabelProvider lItemLabelProvider = (ITableItemLabelProvider) adapterFactory.adapt(lTefRunWsSummary, IItemLabelProvider.class);
						//see TefTestRunWsProgramSummaryItemProvider
						lReturn = lItemLabelProvider.getColumnText(lTefRunWsSummary,4);
					}
					
					break;
//				case 6:
//					lReturn = lReport.getLog();
//					if (lReturn == null) {
//						lReturn = "";
//					}
				default:
					break;
				}
				
			} 
			return lReturn;
	}

}
