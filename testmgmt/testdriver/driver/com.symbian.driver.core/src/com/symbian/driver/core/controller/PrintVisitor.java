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

package com.symbian.driver.core.controller;

import java.util.Map;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.Rtest;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.TransferToSymbian;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.processors.PreProcessor;
import com.symbian.driver.util.DriverSwitch;

/**
 * Prints out to STDOUT the EMF Tree.
 * 
 * @author EngineeringTools
 */
public class PrintVisitor extends Visitor {
	
	/** Indent for output. */
	private String lIndent = "";
	
	private Task iPreviousTask = null;
	
	public boolean start(Task aTask) {
		if (super.start(aTask, PrintSwitch, null, false)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Walks through the EMF tree and prints out all the tree attributes and nodes.
	 * 
	 * {@inheritDoc}
	 * 
	 * @see com.symbian.driver.core.controller.Visitor#emfSwitch(com.symbian.driver.engine.ExecuteTransferSet)
	 */
	protected final DriverSwitch PrintSwitch = new DriverSwitch() {
		
		@Override
		public Object caseTransfer(final Transfer aTransfer) {
			return new IVisitor() {

				public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					
					if (aTransfer.eContainer() instanceof TransferToSymbian) {
						System.out.println(lIndent + "- ExecuteTransfer file to Symbian:"
								+ "\n" + lIndent + "\tPC Path: " + aTransfer.getPCPath()
								+ "\n" + lIndent + "\tSymbian Path: " + aTransfer.getSymbianPath());
					} else {
						System.out.println(lIndent + "- Retrieve file from symbian:"
								+ "\n" + lIndent + "\tSymbian Path: " + aTransfer.getSymbianPath()
								+ "\n" + lIndent + "\tPC Path: " + aTransfer.getPCPath());
					}
					
					return null;
				}
				
			};
		}

		@Override
		public Object caseCmdPC(final CmdPC aCmdPC) {
			return new IVisitor() {

				public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					System.out.println(lIndent + "- Run the command:"
							+ "\n" + lIndent + "\tCommand: " + (aCmdPC.getURI() == null ? "" : aCmdPC.getURI().toString() + "\\") + aCmdPC.getValue());
					return null;
				}
				
			};
		}

		@Override
		public Object caseCmdSymbian(final CmdSymbian aCmdSymbian) {
			return new IVisitor() {

				public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					System.out.println(lIndent + "- Run the command:"
							+ "\n" + lIndent + "\tCommand: " + aCmdSymbian.getStatCommand() + " " + aCmdSymbian.getArgument().toString());
					return null;
				}
				
			};
		}

		@Override
		public Object caseBuild(final Build aBuild) {
			return new IVisitor() {

				public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					System.out.println(lIndent + "- Build the symbian executable:"
							+ "\n" + lIndent + "\tbld.inf group directory: "  + (aBuild.getURI() == null ? "" : aBuild.getURI().toString())
							+ "\n" + lIndent + "\ttest build: " + (aBuild.isTestBuild() ? "true" : "false"));
					return null;
				}
				
			};
		}

		@Override
		public Object caseTestExecuteScript(final TestExecuteScript aTestExecuteScript) {
			return new IVisitor() {

				public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					System.out.println(lIndent + "- Test the TEF Script:"
							+ "\n" + lIndent + "\tPC path: " + aTestExecuteScript.getPCPath()
							+ "\n" + lIndent + "\tSymbian path: " + aTestExecuteScript.getSymbianPath());
					return null;
				}
				
			};
		}
		
		@Override
		public Object caseRtest(final Rtest aRtest) {
			return new IVisitor() {

				public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					System.out.println(lIndent + "- RTest:"
							+ "\n" + lIndent + "\tSymbian Result File: " + aRtest.getResultFile()
							+ "\n" + lIndent + "\tSymbian Path: " + aRtest.getSymbianPath());
					return null;
				}
				
			};
		}
		
		@Override
		public Object caseTask(Task aObject) {
			return new IVisitor() {

				public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					if (iPreviousTask != null) {
						int lPreviousLevel = iPreviousTask.getLevel();
						
						if (lPreviousLevel < aTask.getLevel()) {
							LOGGER.fine("Going up one level in the task hierachy.");
							
							lIndent += "|\t";
						} else if (lPreviousLevel == aTask.getLevel()) {
							LOGGER.fine("Staying level in the task hierachy.");
							// Do nothing
						} else if (lPreviousLevel > aTask.getLevel()) {
							LOGGER.fine("Going down one level in the task hierachy.");
							lIndent = lIndent.replaceFirst("\\|\\t", "");
						}
					}
					
					iPreviousTask = aTask;
					
					System.out.println("\n" + lIndent + "----------------------------------------------------------\n" + lIndent + "Execute "
							+ aTask.getName() + (aTask.isSetTimeout() ? " (Timeout: " + aTask.getTimeout() + ")" : ""));
					return null;
				}
				
			};
		}
	};

}
