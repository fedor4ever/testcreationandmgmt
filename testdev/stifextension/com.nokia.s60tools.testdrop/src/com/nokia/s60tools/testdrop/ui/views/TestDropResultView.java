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


package com.nokia.s60tools.testdrop.ui.views;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.nokia.s60tools.testdrop.engine.value.TestResultValue;
import com.nokia.s60tools.testdrop.resources.TestDropHelpContextIDs;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.ui.preferences.TestDropPreferences;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * View for newest test drop results
 * 
 */
public class TestDropResultView extends ViewPart {
	
	private static final String VIEW_ID = "com.nokia.s60tools.testdrop.ui.views.TestDropResultView"; 

	private final int MAX_RESULTS_COUNT = 10;

	private List<TestResultValue> testResults;
	private int lastIndex;
	private Table table;
	private int currentlySelectedResults = -1;
	private TableColumn resultsColumn;
	private int currentTestResultCount;
	
	private final int resultWidth = 220;
	private final int resultHeight = 60;
	
	private Display display = Display.getCurrent();
	private Color red = new Color(display, 255, 144, 144);
	private Color green = new Color(display, 144, 255, 144);
	private Color black = new Color(display, 0, 0, 0);
	private Color white = new Color(display, 255, 255, 255);

	/**
	 * Constructor
	 * 
	 */
	public TestDropResultView() {

	}

	/**
	 * Shows detailed results
	 * 
	 * @param selectedIndex
	 * 			Index of the selected results
	 */
	private void showDetailedResult(int selectedIndex) {
		if (selectedIndex == -1) {
			return;
		}
		Iterator<TestResultValue> iterator = testResults.iterator();
		while (iterator.hasNext()) {
			TestResultValue testResultValue = iterator.next();
			if (testResultValue.isSelected(selectedIndex)) {
				StartUp.showResultInWindow(testResultValue);
				break;
			}
		}
	}

	/**
	 * Adds test result item to table
	 * 
	 * @param testResultValue
	 * 				adds test result value to the result view
	 */
	private void addResultItemToTable(TestResultValue testResultValue) {
		TableItem item = new TableItem(table, SWT.NONE);
		int index = lastIndex;
		String nameText = testResultValue.getTestNameAndTestId();
		String passrateText = Messages.getString("TestDropResultView.Passrate") + testResultValue.getPassrateString();
		String endTimeText = Messages.getString("TestDropResultView.EndTime") + testResultValue.getEndTime();
		float passrate = testResultValue.getPassrate();

		Display display = Display.getCurrent();

	    Image image = new Image(display, resultWidth, resultHeight);
	    GC gc = new GC(image);
	    
	    gc.setBackground(red);
	    gc.fillRectangle(0, 0, resultWidth, resultHeight);
	    
	    gc.setBackground(green);
	    gc.fillRectangle(0, 0, (int)(passrate * 2.2), resultHeight);
	    
	    gc.setForeground(black);
	    gc.setFont(new Font(display, "arial", 10, SWT.BOLD));
	    gc.drawString(nameText, 2, 2, true);
	    gc.drawString(passrateText, 2, 22, true);
	    gc.drawString(endTimeText, 2, 42, true);
	    
	    drawCheckbox(gc);
	    
	    gc.dispose();
	    
	    item.setImage(0, image);

		testResultValue.addSelectionIndex(index);
		lastIndex++;
	}
	
	/**
	 * Draws an empty checkbox
	 * 
	 * @param gc
	 */
	private void drawCheckbox(GC gc) {		
		gc.setBackground(white);
	    gc.fillRectangle(185, 18, 20, 20);
	    
	    gc.setForeground(black);
	    gc.setLineWidth(2);
	    gc.drawRectangle(185, 18, 20, 20);
	}
	
	/**
	 * Draws a check in an empty checkbox
	 * 
	 * @param gc
	 */
	private void drawCheck(GC gc) {
		gc.setForeground(black);
		gc.setLineWidth(3);
	    gc.drawPolyline(new int[] {188, 25, 193, 34, 202, 20});
	}
	
	/**
	 * Handles the event of selecting an item in the result view.
	 * 
	 * @param index
	 * 			index of the selected item
	 */
	private void selectItem(int index) {
		if (currentlySelectedResults >= 0) {
			TableItem currentlySelectedItem = table.getItem(currentlySelectedResults);
			Image itemsImage = new Image(Display.getCurrent(), currentlySelectedItem.getImage(), SWT.IMAGE_COPY);
			GC gc = new GC(itemsImage);
			drawCheckbox(gc);
			gc.dispose();
			currentlySelectedItem.setImage(itemsImage);
		}
		
		TableItem newSelectedItem = table.getItem(index);
		Image itemsImage = new Image(Display.getCurrent(), newSelectedItem.getImage(), SWT.IMAGE_COPY);
		GC gc = new GC(itemsImage);
		drawCheck(gc);
		gc.dispose();
		newSelectedItem.setImage(itemsImage);
		
		showDetailedResult(index);
		currentlySelectedResults = index;
	}

	/**
	 * Updates view by removing existing items and adding them again
	 * 
	 */
	public void updateView() {
		testResults = StartUp.getTestResults();
		String showMode = StartUp.getTestResultViewShowMode();
		if (isNeedToUpdate()) {
			table.removeAll();
			lastIndex = 0;
			if (testResults != null) {
				Iterator<TestResultValue> iterator = testResults.iterator();
				int count = 0;
				while (iterator.hasNext()) {
					TestResultValue testResultValue = iterator.next();
					if (showMode
							.equals(TestDropPreferences.SHOW_TEST_RESULT_ONLY_FAILED)) {
						if (testResultValue.getPassrate() == 100.0) {
							continue;
						}
					}

					addResultItemToTable(testResultValue);
					count++;
					if (!iterator.hasNext() || count == MAX_RESULTS_COUNT) { //the most recent results should be instantly displayed
						selectItem(0);
						break;
					}
				}
			}
		}
	}

	/**
	 * Checks if there are new results available
	 * 
	 * @return true if new results are available otherwise false
	 */
	private boolean isNeedToUpdate() {
		boolean ret = false;
		if (currentTestResultCount < testResults.size()) {
			ret = true;
			currentTestResultCount = testResults.size();
		}
		return ret;
	}

	/**
	 * Creates view
	 * 
	 * @param parent
	 *            parent composite
	 */
	public void createPartControl(Composite parent) {
		testResults = StartUp.getTestResults();
		
		GridLayout gridLayout = new GridLayout(1, false);
		parent.setLayout(gridLayout);
		
		Button clearResultsButton = new Button(parent, SWT.PUSH);
		clearResultsButton.setText("Remove results");
		clearResultsButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		clearResultsButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				StartUp.clearAllResults();
				table.removeAll();
				currentTestResultCount = 0;
				currentlySelectedResults = -1;
			}
		});

		table = new Table(parent, SWT.NONE);
		table.setLinesVisible(false);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		resultsColumn = new TableColumn(table, SWT.CENTER);
		resultsColumn.setWidth(resultWidth);

		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				selectItem(table.getSelectionIndex());
			}
		});
		table.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent arg0) {
				//this is a walk-around for situation when an item is chosen and then the white (empty)
				//area of table is clicked. In such situation item disappeared until table lost
				//the focus. This solution lets all items be displayed.
				table.deselectAll();
			}
		});
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(table,
				TestDropHelpContextIDs.TESTDROP_RESULTS_VIEW);
		updateView();
	}

	/**
	 * Sets focus to view
	 * 
	 */
	public void setFocus() {
	}

	/**
	 * Returns view id
	 * 
	 * @return view id
	 */
	public static String getViewId() {
		return VIEW_ID;
	}

}
