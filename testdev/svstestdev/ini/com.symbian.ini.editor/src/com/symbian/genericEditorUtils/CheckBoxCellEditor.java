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

import java.text.MessageFormat;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class CheckBoxCellEditor extends CellEditor {

	private Button iCheckButton;
	protected Boolean selection;

	public CheckBoxCellEditor(Composite parent) {
		super(parent);
	}
	
    @Override
    public void deactivate() {
    }

	@Override
	protected Control createControl(Composite parent) {
		iCheckButton = new Button(parent, SWT.CHECK);
		
		iCheckButton.addKeyListener(new KeyAdapter() {
            // hook key pressed - see PR 14201  
            public void keyPressed(KeyEvent e) {
                keyReleaseOccured(e);
            }
        });

        iCheckButton.addSelectionListener(new SelectionAdapter() {
            public void widgetDefaultSelected(SelectionEvent event) {
                applyEditorValue();
            }

            public void widgetSelected(SelectionEvent event) {
                selection = iCheckButton.getSelection();
            }
        });

        iCheckButton.addTraverseListener(new TraverseListener() {
            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_ESCAPE
                        || e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                }
            }
        });

        iCheckButton.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                CheckBoxCellEditor.this.focusLost();
            }
        });
		
		return iCheckButton;
	}

	@Override
	protected Object doGetValue() {
		return selection;
	}

	@Override
	protected void doSetFocus() {
		if (iCheckButton != null) {
			iCheckButton.setFocus();
		}
	}

	@Override
	protected void doSetValue(Object value) {
		if (value instanceof Boolean) {
			iCheckButton.setSelection((Boolean) value);
		}
	}
	
    /**
     * Applies the currently selected value and deactiavates the cell editor
     */
    private void applyEditorValue() {
        //	must set the selection before getting value
        selection = iCheckButton.getSelection();
        Object newValue = doGetValue();
        markDirty();
        boolean isValid = isCorrect(newValue);
        setValueValid(isValid);
        
        if (!isValid) {
        	setErrorMessage(MessageFormat.format(getErrorMessage(),
        			new Object[] { iCheckButton.getSelection() }));
        }

        fireApplyEditorValue();
    }

}
