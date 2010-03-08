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


package com.symbian.genericEditorUtils;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


public class GenericPropertyDescriptor {
	
	/**
	 * The value of this entry is defined as the the first object in its value
	 * array or, if that object is an <code>IPropertySource</code>, the value
	 * it returns when sent <code>getEditableValue</code>
	 */
	private Object iEditValue = null;

	/** The EMF object to set. */
	private EObject iEObject = null;
	
	/** The Item Property desciptor for the EMF object. */
	private IItemPropertyDescriptor iItemDiscriptor = null;
	
	/** The Property desciptor for the EMF object. */
	private IPropertyDescriptor iDescriptor = null;
	
	/** The widget to edit the property. */
	private CellEditor iEditor = null;
	
	/** The error text. */
	private String iErrorText = null;
	
	/** A custom Cell Editor. */
	private CellEditor iCustomEditor;

	/**
	 * Create the CellEditorListener for this entry. It listens for value
	 * changes in the CellEditor, and cancel and finish requests.
	 */
	private ICellEditorListener cellEditorListener = new ICellEditorListener() {
		public void editorValueChanged(boolean oldValidState,
				boolean newValidState) {
			if (!newValidState) {
				// currently not valid so show an error message
				setErrorText(iEditor.getErrorMessage());
			} else {
				// currently valid
				setErrorText(null);
			}
		}

		public void cancelEditor() {
			setErrorText(null);
		}

		public void applyEditorValue() {
			GenericPropertyDescriptor.this.applyEditorValue();
		}
	};
	
	public GenericPropertyDescriptor(IEmfFormEditor aEditor, ItemProviderAdapter aProviderAdapter, EStructuralFeature aFeature) {
		iEObject = (EObject) aProviderAdapter.getEditableValue(aFeature);
		iItemDiscriptor = aProviderAdapter.getPropertyDescriptor(iEObject, aFeature);
		iDescriptor = new PropertyDescriptorDisabledDeactivate(iEObject, iItemDiscriptor);
		
		ItemPropertyDescriptor.PropertyValueWrapper lPropertyValue = (ItemPropertyDescriptor.PropertyValueWrapper) iItemDiscriptor.getPropertyValue(iEObject);
		if (lPropertyValue != null) {
			iEditValue = lPropertyValue.getEditableValue(iEObject);
		}
	}

	public GenericPropertyDescriptor(IEmfFormEditor aEditor, EObject aEObject, EStructuralFeature aFeature) {		
		iEObject = aEObject;
		
		List<IItemPropertyDescriptor> lPropertyDescriptors = new AdapterFactoryItemDelegator(aEditor.getAdapterFactory()).getPropertyDescriptors(aEObject);
		for (IItemPropertyDescriptor lDescriptor : lPropertyDescriptors) {
			if (lDescriptor.getFeature(aEObject) == aFeature) {
				iItemDiscriptor = lDescriptor;
				break;
			}
		}
		
		iDescriptor = new PropertyDescriptorDisabledDeactivate(aEObject, iItemDiscriptor);
		
		ItemPropertyDescriptor.PropertyValueWrapper lPropertyValue = (ItemPropertyDescriptor.PropertyValueWrapper) iItemDiscriptor.getPropertyValue(aEObject);
		if (lPropertyValue != null) {
			iEditValue = lPropertyValue.getEditableValue(aEObject);
		}
	}
	
	public void setCustomPropertyDiscriptor(PropertyDescriptor aPropertyDescriptor) {
		iDescriptor = aPropertyDescriptor;
	}

	public CellEditor getEditor(Composite parent) {
		if (iEditor == null) {
			
			if (iCustomEditor != null) {
				iEditor = iCustomEditor;
			} else {
				iEditor = iDescriptor.createPropertyEditor(parent);
			}
		}
		
		if (iEditor != null) {
			final Control lControl = iEditor.getControl();
			if (lControl instanceof Composite) {
				Composite lComposite = ((Composite) lControl);
				lComposite.setLayout(new GridLayout(2, false));
				Control[] lChildren = lComposite.getChildren();
				
				for (int lIter = lChildren.length - 1; lIter >= 0; lIter--) {
					if (lChildren[lIter] instanceof Label) {
						lChildren[lIter].setLayoutData(new GridData(GridData.FILL_BOTH));
					}
				}	
			}
			
			iEditor.addListener(cellEditorListener);
			iEditor.setValue(iEditValue);
			setErrorText(iEditor.getErrorMessage());
			lControl.setVisible(true);
		}
		
		return iEditor;
	}
	
	public IItemPropertyDescriptor getItemDiscriptor() {
		return iItemDiscriptor;
	}
	
	public void dispose() {
		if (iEditor != null) {
			iEditor.dispose();
			iEditor = null;
		}
	}

	/**
	 * Set the error text. This should be set to null when the current value is
	 * valid, otherwise it should be set to a error string
	 */
	private void setErrorText(String newErrorText) {
		iErrorText = newErrorText;
		// inform listeners
//		fireErrorMessageChanged();
	}
	
	/*
	 * (non-Javadoc) Method declared on IPropertySheetEntry.
	 */
	private void applyEditorValue() {
		if (iEditor == null) {
			return;
		}

		// Check if editor has a valid value
		if (!iEditor.isValueValid()) {
			setErrorText(iEditor.getErrorMessage());
			return;
		}

		setErrorText(null);

		// See if the value changed and if so update
		Object newValue = iEditor.getValue();
		boolean changed = false;
		if (iEditValue == null) {
			if (newValue != null) {
				changed = true;
			}
		} else if (!iEditValue.equals(newValue)) {
			changed = true;
		}
		
		if (newValue instanceof String && ((String) newValue).equalsIgnoreCase("")) {
			changed = false;
		}
		
		// Set the editor value
		if (changed && iItemDiscriptor.canSetProperty(iEObject)) {
			iItemDiscriptor.setPropertyValue(iEObject, newValue);
		}
	}
	
}
