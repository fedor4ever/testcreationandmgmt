/*
 * Copyright (c) 2010 Nokia Corporation and/or its subsidiary(-ies).
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

package com.nokia.testfw.codegen.ui.wizard;

import org.eclipse.jface.wizard.IWizardPage;

public class SUTNewTestWizard extends AbstractTemplateWizard {

	public static final String SYMBIAN_UNIT_TEST = "SymbianUnitTest";

	private ChooseProjectPage iChooseProjectPage;
	private ChooseObjectivePage iChooseObjectivePage;
	private LocationAndPropertyPage iLocationAndPropertyPage;
	private TestCasePreviewPage iTestCasePreviewPage;

	private boolean iShowChooseProjectPage = true;

	public SUTNewTestWizard() {
		super(SYMBIAN_UNIT_TEST);
		iShowNewProjectPage = false;
	}

	public void addPages() {
		super.addPages();
		if (iShowChooseProjectPage) {
			iChooseProjectPage = new ChooseProjectPage();
			addPage(iChooseProjectPage);
		}

		iChooseObjectivePage = new ChooseObjectivePage();
		addPage(iChooseObjectivePage);

		iLocationAndPropertyPage = new LocationAndPropertyPage();
		addPage(iLocationAndPropertyPage);

		if (iShowChooseProjectPage) {
			iLocationAndPropertyPage.setShowPreviewButton(false);
		} else {
			iTestCasePreviewPage = new TestCasePreviewPage();
			addPage(iTestCasePreviewPage);
		}
	}

	@Override
	protected boolean initPagesConditon() {
		return !iShowChooseProjectPage;
	}

	/**
	 * @return the iShowChooseProjectPage
	 */
	public boolean isShowChooseProjectPage() {
		return iShowChooseProjectPage;
	}

	/**
	 * @param iShowChooseProjectPage
	 *            the iShowChooseProjectPage to set
	 */
	public void setShowChooseProjectPage(boolean showChooseProjectPage) {
		this.iShowChooseProjectPage = showChooseProjectPage;
	}

	/**
	 * Overridden to check if preview page is the next page and is it requested
	 * to be shown. If user has not selected it to be shown, returns null as
	 * next page.
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page instanceof LocationAndPropertyPage) {
			if (!iLocationAndPropertyPage.isShowPreviewChanges()) {
				return null;
			}
		}
		return super.getNextPage(page);
	}
}
