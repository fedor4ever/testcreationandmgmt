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




package com.symbian.driver.report.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.symbian.driver.report.Report;
import com.symbian.driver.report.ReportInfo;
import com.symbian.driver.report.ReportPackage;
import com.symbian.driver.report.impl.DocumentRootImpl;
import com.symbian.driver.report.util.ReportResourceFactoryImpl;
import com.symbian.driver.report.views.ShowView;
import com.symbian.driver.report.views.TestViewer;

/**
 * 
 * @author HocineA ShowInTDResultsView : contributes to the popupMenus so that
 *         users can re-open TestDriver XML results files in {@link TestViewer}
 * 
 */
public class ShowInTDResultsView implements IObjectActionDelegate {

	ISelection lSelection;

	/**
	 * Constructor.
	 */
	public ShowInTDResultsView() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart) Saves
	 *      the selection when the user selects a file
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		lSelection = targetPart.getSite().getSelectionProvider().getSelection();
	}

	/**
	 * @see IActionDelegate#run(IAction) Make all the checks that the users
	 *      wants to show the right file in {@link TestViewer} and show it.
	 */
	public void run(IAction action) {
		// check the selection type
		if (lSelection instanceof IStructuredSelection) {
			IStructuredSelection lSel = (IStructuredSelection) lSelection;
			Object lObject = lSel.getFirstElement();
			// check it's a file selection
			if (lObject instanceof IFile) {
				IFile lFile = (IFile) lObject;
				String lExtension = lFile.getFileExtension();
				// check it's an XML file
				if (lExtension != null && lExtension.equals("xml")) {
					// Create a resource set to hold the resources.
					//
					ResourceSet resourceSet = new ResourceSetImpl();

					// Register the appropriate resource factory to handle all
					// file extentions.
					//
					resourceSet.getResourceFactoryRegistry()
							.getExtensionToFactoryMap()
							.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
									new ReportResourceFactoryImpl());

					// Register the package to ensure it is available during
					// loading.
					//
					resourceSet.getPackageRegistry().put(ReportPackage.eNS_URI,
							ReportPackage.eINSTANCE);

					URI lPath = URI.createFileURI(lFile.getLocation()
							.toString());
					Resource lResource = resourceSet.getResource(lPath, true);
					EList lContent = lResource.getContents();
					if (lContent.size() != 0) {
						DocumentRootImpl lRoot = (DocumentRootImpl) lContent
								.get(0);
						Report lReport = lRoot.getReport();
						if (lReport != null) {
							//Replace the xml location
							ReportInfo lRI = lReport.getReportInfo();
							EMap lInfo = lRI.getInfo();
							lInfo.put("ReportXMLLocation", lFile.getLocation().toFile().toString());
							
							// load the results view
							ShowView lViewer = new ShowView();
							lViewer.showTestRunnerViewPartInActivePage();
							TestViewer.getInstance().setInput(lRoot.getReport());
							
						}
					}

				}
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {

	}

}
