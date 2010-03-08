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



package com.symbian.driver.core;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.OperatorType;
import com.symbian.driver.RetrieveFromSymbian;
import com.symbian.driver.StatCommand;
import com.symbian.driver.Task;
import com.symbian.driver.TestCase;
import com.symbian.driver.TestCasesList;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.TransferToSymbian;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.util.DriverValidator;

/**
 * @author EngineeringTools
 * 
 */
public class ExtendedDriverValidator {

	/** Logger for this class. */
	final public static Logger LOGGER = Logger.getLogger(ExtendedDriverValidator.class.getName());


	/**
	 * @param aResource
	 */
	public static void validator(Resource aResource) {
		getValidator();

		for (Iterator lResDiag = aResource.getContents().iterator(); lResDiag.hasNext();) {
			Diagnostic lDiagnostic = Diagnostician.INSTANCE.validate((EObject) lResDiag.next());
			if (lDiagnostic.getSeverity() != Diagnostic.OK) {
				ExtendedDriverValidator.printDiagnostic(lDiagnostic);
			}
		}
	}

	/**
	 * Validates the EMF model with runtime information.
	 */
	public static void getValidator() {
		EValidator.Registry.INSTANCE.put(DriverPackage.eINSTANCE, new DriverValidator() {

			// //////////////////////
			// Switch CmdPC
			public boolean validateCmdPC(CmdPC aCmdPC, DiagnosticChain aDiagnostics, Map aContext) {
				return super.validateCmdPC(aCmdPC, aDiagnostics, aContext);
			}

			// //////////////////////
			// Switch Build
			public boolean validateBuild(Build aBuild, DiagnosticChain aDiagnostics, Map aContext) {
				boolean lValidate = super.validateBuild(aBuild, aDiagnostics, aContext);
				if (lValidate) {
					try {
						File lGroupDir = ModelUtils.checkPCPath(aBuild.getURI(), false)[0];
						lValidate = lGroupDir.isDirectory()
								&& new File(lGroupDir.getCanonicalPath() + File.separator + "bld.inf").isFile();
					} catch (IOException lIOException) {
						lValidate = false;
					}

					if (!lValidate && aDiagnostics != null) {
						aDiagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, DriverValidator.DIAGNOSTIC_SOURCE, 0,
								EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] {
										"Build Group Directory: " + aBuild.getURI(),
										EObjectValidator.getObjectLabel(aBuild, aContext) }), new Object[] { aBuild }));
					}
				}
				return lValidate;
			}

			// //////////////////////
			// Switch CmdSymbian
			public boolean validateCmdSymbian(CmdSymbian aCmdSymbian, DiagnosticChain aDiagnostics, Map aContext) {
				boolean lValidate = super.validateCmdSymbian(aCmdSymbian, aDiagnostics, aContext);

				if (lValidate) {
					StatCommand lStatCommandLiteral = aCmdSymbian.getStatCommand();
					EList lArguments = aCmdSymbian.getArgument();

					if (lStatCommandLiteral == StatCommand.CREATE_FOLDER) {
						lValidate = lArguments.size() == 1
								&& validatePattern(DriverPackage.eINSTANCE.getSymbianPath(), lArguments.get(0),
										SYMBIAN_PATH__PATTERN__VALUES, aDiagnostics, aContext);
					} else if (lStatCommandLiteral == StatCommand.DELETE) {
						lValidate = lArguments.size() == 1
								&& validatePattern(DriverPackage.eINSTANCE.getSymbianPath(), lArguments.get(0),
										SYMBIAN_PATH__PATTERN__VALUES, aDiagnostics, aContext);
					} else if (lStatCommandLiteral == StatCommand.GET_SCREEN_CAPTURE) {
						lValidate = lArguments.size() == 0;
					} else if (lStatCommandLiteral == StatCommand.LIST_DRIVES) {
						lValidate = lArguments.size() == 0
								&& validatePattern(DriverPackage.eINSTANCE.getSymbianPath(), lArguments.get(0),
										SYMBIAN_PATH__PATTERN__VALUES, aDiagnostics, aContext);
					} else if (lStatCommandLiteral == StatCommand.LIST_FILES) {
						lValidate = lArguments.size() == 1
								&& validatePattern(DriverPackage.eINSTANCE.getSymbianPath(), lArguments.get(0),
										SYMBIAN_PATH__PATTERN__VALUES, aDiagnostics, aContext);
					} else if (lStatCommandLiteral == StatCommand.REMOVE_FOLDER) {
						lValidate = lArguments.size() == 1
								&& validatePattern(DriverPackage.eINSTANCE.getSymbianPath(), lArguments.get(0),
										SYMBIAN_PATH__PATTERN__VALUES, aDiagnostics, aContext);
					} else if (lStatCommandLiteral == StatCommand.RUN) {
						lValidate = lArguments.size() >= 1
								|| lArguments.size() <= 2
								&& validatePattern(DriverPackage.eINSTANCE.getSymbianPath(), lArguments.get(0),
										SYMBIAN_PATH__PATTERN__VALUES, aDiagnostics, aContext);
					} else if (lStatCommandLiteral == StatCommand.START_LOGGING) {
						lValidate = lArguments.size() == 1
								&& validatePattern(DriverPackage.eINSTANCE.getSymbianPath(), lArguments.get(0),
										SYMBIAN_PATH__PATTERN__VALUES, aDiagnostics, aContext);
					} else if (lStatCommandLiteral == StatCommand.STOP_LOGGING) {
						lValidate = lArguments.size() == 0;
					} else {
						lValidate = false;
					}

					if (!lValidate && aDiagnostics != null) {
						aDiagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, DriverValidator.DIAGNOSTIC_SOURCE, 0,
								EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] {
										"Symbian command argument",
										EObjectValidator.getObjectLabel(aCmdSymbian, aContext) }),
								new Object[] { aCmdSymbian }));
					}
				}
				return lValidate;
			}

			// //////////////////////
			// Switch TestExecuteScript
			public boolean validateTestExecuteScript(TestExecuteScript aTestExecuteScript,
					DiagnosticChain aDiagnostics, Map aContext) {
				boolean lValidate = super.validateTestExecuteScript(aTestExecuteScript, aDiagnostics, aContext);

				if (lValidate) {
					StringBuffer lErrorMessage = new StringBuffer();
					try {
						File[] lScripts = ModelUtils.checkPCPath(aTestExecuteScript.getPCPath(), true);

						for (int lFileIter = 0; lFileIter < lScripts.length; lFileIter++) {
							lValidate = lScripts[lFileIter].isFile();
							if (!lValidate) {
								break;
							}
						}

						TestCasesList lTestCasesList = aTestExecuteScript.getTestCasesList();
						if (lTestCasesList != null) {
							// validate operator is 'include' or 'exclude'
							OperatorType lOperator = lTestCasesList.getOperator();
							if (!OperatorType.VALUES.contains(lOperator)) {
								// the value is not 'include' or 'exclude'
								lValidate = false;
								lErrorMessage.append(". The target attribute " + lOperator
										+ " should be one of 'include/'exclude'");
							} else {

								// validate testcases
								EList lTestCases = lTestCasesList.getTestCase();

								// check if it's a cfg file
								TestCase lTestCase = null;
								String lTarget = null;
								Iterator lIter = lTestCases.iterator();
								while (lIter.hasNext()) {
									lTestCase = (TestCase) lIter.next();
									lTarget = lTestCase.getTarget();
									if (ModelUtils.isTestCasesFile(lTarget)) {										
										try {
											ModelUtils.checkTCFile(lTarget, lScripts[0]);
										} catch (IOException lIOException) {
											lValidate = false;
											lErrorMessage.append(". The Test cases file " + lTarget
													+ " is not valid. " + lIOException.getMessage());
										}
									}
								}
							}
						}
					} catch (IOException lIOException) {
						lErrorMessage.append(". The value: " + aTestExecuteScript.getPCPath() + ", caused an IO error: "
								+ lIOException.getMessage());
						lValidate = false;
					}

					if (!lValidate && aDiagnostics != null) {
						aDiagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, DriverValidator.DIAGNOSTIC_SOURCE, 0,
								EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] {
										"TestExecute script " + aTestExecuteScript.getPCPath()
												+ lErrorMessage.toString(),
										EObjectValidator.getObjectLabel(aTestExecuteScript, aContext) }),
								new Object[] { aTestExecuteScript }));
					}
				}

				return lValidate;
			}

			// //////////////////////
			// Switch Transfer
			public boolean validateTransfer(Transfer aTransfer, DiagnosticChain aDiagnostics, Map aContext) {
				boolean lValidate = super.validateTransfer(aTransfer, aDiagnostics, aContext);

				if (lValidate) {

					StringBuffer lErrorMessage = new StringBuffer();

					if (aTransfer.eContainer() instanceof TransferToSymbian) {
						// Check for Eclipsing Errors
						Task lTask = ModelUtils.isFileEclipsing(aTransfer.getSymbianPath().substring(3,
								aTransfer.getSymbianPath().length()).toLowerCase(), (Task) aTransfer.eContainer()
								.eContainer());

						if (lTask != null) {
							lValidate = false;
							lErrorMessage.append("The file " + aTransfer.getSymbianPath()
									+ " has already been added to the driver file in parent node " + lTask.getName());
						}

						
						
						// Check if the file exists
						File[] lTransferFiles = null;
						
						try {
							
							if (aTransfer.getPCPath().indexOf("*") >= 0) {
								lTransferFiles = ModelUtils.checkPCPath(aTransfer.getPCPath(), true);
							} else {
								lTransferFiles = ModelUtils.checkPCPath(aTransfer.getPCPath(), false);
							}
							
						} catch (IOException lIOException) {
							lValidate = false;
							lErrorMessage.append("The file: " + aTransfer.getPCPath() + ", caused an IO error: "
									+ lIOException.getMessage());
						}
						
						if (lTransferFiles != null && lTransferFiles.length > 0) {
							for (int lFileIter = 0; lFileIter < lTransferFiles.length; lFileIter++) {
								if (!lTransferFiles[lFileIter].isFile()) {
									lValidate = false;
									lErrorMessage.append("The file " + lTransferFiles[lFileIter] + " must be valid.");
									break;
								}
							}
						}

					} else if (aTransfer.eContainer() instanceof RetrieveFromSymbian) {
						String lPCPathLiteral = aTransfer.getPCPath().toString();
						if (lPCPathLiteral.indexOf("*") >= 0) {
							lValidate = false;
							lErrorMessage.append("The PCPath " + lPCPathLiteral + " cannot contain a wildcard.");
						}
					}

					if (!lValidate && aDiagnostics != null) {
						aDiagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, DriverValidator.DIAGNOSTIC_SOURCE, 0,
								EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic",
										new Object[] { lErrorMessage.toString(),
												EObjectValidator.getObjectLabel(aTransfer, aContext) }),
								new Object[] { aTransfer }));
					}
				}

				return lValidate;
			}
		});
	}

	/**
	 * Prints diagnostics with indentation.
	 * 
	 * @param diagnostic
	 *            the diagnostic to print.
	 * @generated
	 */
	public static void printDiagnostic(final Diagnostic diagnostic) {

		switch (diagnostic.getSeverity()) {
		case Diagnostic.INFO:
			LOGGER.info(diagnostic.getMessage());
			break;
		case Diagnostic.ERROR:
			LOGGER.warning(diagnostic.getMessage());
			break;
		case Diagnostic.WARNING:
			LOGGER.warning(diagnostic.getMessage());
			break;
		case Diagnostic.OK:
			LOGGER.fine(diagnostic.getMessage());
			break;
		}

		for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext();) {
			printDiagnostic((Diagnostic) i.next());
		}
	}

}
