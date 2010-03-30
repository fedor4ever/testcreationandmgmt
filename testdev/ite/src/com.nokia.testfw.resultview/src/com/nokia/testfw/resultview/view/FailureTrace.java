/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies).
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
package com.nokia.testfw.resultview.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.nokia.testfw.core.model.result.TestResult;

/**
 * A pane that shows a stack trace of a failed test.
 */
public class FailureTrace {
	private Table iTable;
	private TestResult iResult;

	public FailureTrace(Composite parent) {

		iTable = new Table(parent, SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
	}

	/**
	 * Returns the composite used to present the trace
	 * 
	 * @return The composite
	 */
	public Table getTable() {
		return iTable;
	}

	public void setTestResult(TestResult result) {
		iResult = result;
		updateTable();
	}

	public TestResult getTestResult() {
		return iResult;
	}

	/**
	 * Refresh the table from the trace.
	 */
	public boolean update(TestResult result) {
		if (iResult != result)
			return false;
		updateTable();
		return true;
	}

	private void updateTable() {
		iTable.removeAll();
		if (iResult == null) {
			return;
		}
		String trace = iResult.getMessage();
		if (trace == null || trace.trim().equals("")) { //$NON-NLS-1$
			return;
		}
		trace = trace.trim();
		StringBuilder location = new StringBuilder();
		if (iResult.getFile() != null) {
			location.append(iResult.getFile());
			if (iResult.getLine() > -1) {
				location.append("(").append(iResult.getLine()).append(")");
			}
		}
		iTable.setRedraw(false);
		if (trace.length() > 0) {
			TableItem item = new TableItem(iTable, SWT.None);
			item.setText("Reason:");
			item = new TableItem(iTable, SWT.None);
			item.setForeground(getShell().getDisplay().getSystemColor(
					SWT.COLOR_RED));
			item.setText("        " + trace);
		}
		if (location.length() > 0) {
			TableItem item = new TableItem(iTable, SWT.None);
			item.setText("Location:");
			item = new TableItem(iTable, SWT.None);
			item.setForeground(getShell().getDisplay().getSystemColor(
					SWT.COLOR_BLUE));
			item.setText("        " + location.toString());
		}
		iTable.setRedraw(true);
	}

	public Shell getShell() {
		return iTable.getShell();
	}
}
