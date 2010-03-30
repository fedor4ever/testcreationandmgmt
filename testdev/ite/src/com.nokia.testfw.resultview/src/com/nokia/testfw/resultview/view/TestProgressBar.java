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
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.nokia.testfw.core.model.result.TestRunResult;

/**
 * A progress bar with a red/green indication for success or failure.
 */
public class TestProgressBar extends Canvas {
	private static final int DEFAULT_WIDTH = 160;
	private static final int DEFAULT_HEIGHT = 16;

	int totalCount = 0;
	int currentCount = 0;
	boolean error = false;
	boolean stopped = false;

	int fColorBarWidth = 0;
	private Color fOKColor;
	private Color fFailureColor;
	private Color fStoppedColor;

	public TestProgressBar(Composite parent) {
		super(parent, SWT.NONE);

		addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				fColorBarWidth = scale(currentCount);
				redraw();
			}
		});
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				paint(e);
			}
		});
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				fFailureColor.dispose();
				fOKColor.dispose();
				fStoppedColor.dispose();
			}
		});

		Display display = parent.getDisplay();
		fFailureColor = new Color(display, 159, 63, 63);
		fOKColor = new Color(display, 95, 191, 95);
		fStoppedColor = new Color(display, 120, 120, 120);
	}

	public void setTestCounts(int counts) {
		totalCount = counts;
		fColorBarWidth = scale(currentCount);
		paintStep(1, fColorBarWidth);
	}

	public void addTestCount() {
		setTestCounts(totalCount + 1);
	}

	public void reset() {
		error = false;
		stopped = false;
		currentCount = 0;
		fColorBarWidth = 0;
		totalCount = 0;
		redraw();
	}

	private void paintStep(int startX, int endX) {
		GC gc = new GC(this);
		setStatusColor(gc);
		Rectangle rect = getClientArea();
		startX = Math.max(1, startX);
		gc.fillRectangle(startX, 1, endX - startX, rect.height - 2);
		gc.dispose();
	}

	private void setStatusColor(GC gc) {
		if (stopped) {
			gc.setBackground(fStoppedColor);
		} else if (error) {
			gc.setBackground(fFailureColor);
		} else {
			gc.setBackground(fOKColor);
		}
	}

	public void stopTest() {
		stopped = true;
		redraw();
	}

	private int scale(int value) {
		if (totalCount > 0) {
			Rectangle r = getClientArea();
			if (r.width != 0) {
				return Math.max(0, value * (r.width - 2) / totalCount);
			}
		}

		return value;
	}

	private void drawBevelRect(GC gc, int x, int y, int w, int h,
			Color topleft, Color bottomright) {
		gc.setForeground(topleft);
		gc.drawLine(x, y, x + w - 1, y);
		gc.drawLine(x, y, x, y + h - 1);

		gc.setForeground(bottomright);
		gc.drawLine(x + w, y, x + w, y + h);
		gc.drawLine(x, y + h, x + w, y + h);
	}

	private void paint(PaintEvent event) {
		GC gc = event.gc;
		Display disp = getDisplay();

		Rectangle rect = getClientArea();
		gc.fillRectangle(rect);
		drawBevelRect(gc, rect.x, rect.y, rect.width - 1, rect.height - 1, disp
				.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW), disp
				.getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		setStatusColor(gc);
		fColorBarWidth = Math.min(rect.width - 2, fColorBarWidth);
		gc.fillRectangle(1, 1, fColorBarWidth, rect.height - 2);
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		checkWidget();
		Point size = new Point(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		if (wHint != SWT.DEFAULT) {
			size.x = wHint;
		}
		if (hHint != SWT.DEFAULT) {
			size.y = hHint;
		}

		return size;
	}

	public void step(boolean passed) {
		currentCount++;
		int x = fColorBarWidth;

		fColorBarWidth = scale(currentCount);
		if (!error && !passed) {
			error = true;
			x = 1;
		}
		if (currentCount == totalCount) {
			fColorBarWidth = getClientArea().width - 1;
		}
		paintStep(x, fColorBarWidth);
	}

	/**
	 * initialize the progress bar with a test result
	 * 
	 * @param result
	 * @see TestRunResult
	 */
	public void init(TestRunResult result) {
		totalCount = result.getTestCount();
		currentCount = result.getPassedTestCount()
				+ result.getfailedTestCount() + result.getSkippedTestCount();
		error = result.getfailedTestCount() > 0 ? true : false;
		redraw();
	}

}
