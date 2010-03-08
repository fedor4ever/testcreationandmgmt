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


/**
 * 
 */
package com.symbian.genericEditorUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class PropertyDescriptorDisabledDeactivate extends PropertyDescriptor {
	PropertyDescriptorDisabledDeactivate(Object aObject,
			IItemPropertyDescriptor aDescriptor) {
		super(aObject, aDescriptor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CellEditor createPropertyEditor(Composite aComposite) {
		if (!itemPropertyDescriptor.canSetProperty(object)) {
			return null;
		}

		CellEditor result = null;

		Object genericFeature = itemPropertyDescriptor.getFeature(object);
		if (genericFeature instanceof EReference[]) {
			result = new ExtendedComboBoxCellEditor(aComposite, new ArrayList(
					itemPropertyDescriptor.getChoiceOfValues(object)),
					getEditLabelProvider(), itemPropertyDescriptor
							.isSortChoices(object)) {

				@Override
				public void deactivate() {
					// Do nothing
				}

			};
		} else if (genericFeature instanceof EStructuralFeature) {
			final EStructuralFeature feature = (EStructuralFeature) genericFeature;
			final EClassifier eType = feature.getEType();
			final Collection choiceOfValues = itemPropertyDescriptor
					.getChoiceOfValues(object);
			if (choiceOfValues != null) {
				if (itemPropertyDescriptor.isMany(object)) {
					boolean valid = true;
					for (Iterator i = choiceOfValues.iterator(); i.hasNext();) {
						Object choice = i.next();
						if (!eType.isInstance(choice)) {
							valid = false;
							break;
						}
					}

					if (valid) {
						result = new ExtendedDialogCellEditor(aComposite,
								getEditLabelProvider()) {

							@Override
							protected Object openDialogBox(
									Control cellEditorWindow) {
								FeatureEditorDialog dialog = new FeatureEditorDialog(
										cellEditorWindow.getShell(),
										getEditLabelProvider(),
										object,
										feature.getEType(),
										(List) ((IItemPropertySource) itemPropertyDescriptor
												.getPropertyValue(object))
												.getEditableValue(object),
										getDisplayName(), new ArrayList(
												choiceOfValues), false,
										itemPropertyDescriptor
												.isSortChoices(object));
								dialog.open();
								return dialog.getResult();
							}

							@Override
							public void deactivate() {
								//setFocus();
							}

						};
					}
				}

				if (result == null) {
					result = new ExtendedComboBoxCellEditor(aComposite,
							new ArrayList(choiceOfValues),
							getEditLabelProvider(), itemPropertyDescriptor
									.isSortChoices(object)) {

						@Override
						protected Control createControl(Composite aParent) {
							Control lControl = super.createControl(aParent);
							((CCombo) lControl).setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
							return lControl;
						}
						
						@Override
						public void deactivate() {
							// Do nothing
						}

					};
				}
			} else if (eType instanceof EDataType) {
				EDataType eDataType = (EDataType) eType;
				if (eDataType.isSerializable()) {
					if (itemPropertyDescriptor.isMany(object)) {
						result = new ExtendedDialogCellEditor(aComposite,
								getEditLabelProvider()) {

							@Override
							protected Object openDialogBox(
									Control cellEditorWindow) {
								FeatureEditorDialog dialog = new FeatureEditorDialog(
										cellEditorWindow.getShell(),
										getEditLabelProvider(),
										object,
										feature.getEType(),
										(List) ((IItemPropertySource) itemPropertyDescriptor
												.getPropertyValue(object))
												.getEditableValue(object),
										getDisplayName(), null,
										itemPropertyDescriptor
												.isMultiLine(object), false);
								dialog.open();
								return dialog.getResult();
							}

							@Override
							public void deactivate() {
								// setFocus();
							}

						};
					} else if (eDataType.getInstanceClass() == Boolean.class
							|| eDataType.getInstanceClass() == Boolean.TYPE) {
						result = new CheckBoxCellEditor(aComposite);
					} else {
						result = createEDataTypeCellEditor(eDataType, aComposite);
					}
				}
			}
		}

		return result;
	}

	@Override
	protected CellEditor createEDataTypeCellEditor(final EDataType aDataType,
			Composite aComposite) {
		if (itemPropertyDescriptor.isMultiLine(object)) {
			return new EDataTypeCellEditor(aDataType, aComposite) {
				
				@Override
				public int getStyle() {
					return SWT.MULTI | SWT.WRAP | SWT.V_SCROLL;
				}
				
				@Override
				protected Control createControl(Composite aParent) {
					Text lText = (Text) super.createControl(aParent);
					GridData lGridData = new GridData(GridData.FILL, GridData.FILL, true, true);
					lGridData.heightHint = 100;
					lText.setLayoutData(lGridData);
					return lText;
				}
				
				
				
				
			};
		}
		return new EDataTypeCellEditor(aDataType, aComposite) {
			
			@Override
			protected Control createControl(Composite aParent) {
				Control lControl = super.createControl(aParent);
				lControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				return lControl;
			}
			
			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.CellEditor#deactivate()
			 */
			@Override
			public void deactivate() {
				// Do nothing
			}
			
		};
	}

}