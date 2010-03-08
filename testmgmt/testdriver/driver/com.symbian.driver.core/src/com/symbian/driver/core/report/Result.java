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

package com.symbian.driver.core.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.Driver;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.RetrieveFromSymbian;
import com.symbian.driver.Rtest;
import com.symbian.driver.StartTrace;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.core.CrashException;
import com.symbian.driver.core.controller.Visitor;
import com.symbian.driver.core.controller.event.IVisitorEventListener;
import com.symbian.driver.core.controller.event.TaskFinishedEvent;
import com.symbian.driver.core.controller.event.TaskStartedEvent;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IVisitor.ESeverity;
import com.symbian.driver.report.BaseReport;
import com.symbian.driver.report.DocumentRoot;
import com.symbian.driver.report.ExceptionReport;
import com.symbian.driver.report.GenericReport;
import com.symbian.driver.report.GenericResult;
import com.symbian.driver.report.Report;
import com.symbian.driver.report.ReportFactory;
import com.symbian.driver.report.ReportInfo;
import com.symbian.driver.report.util.ReportResourceFactoryImpl;
import com.symbian.driver.util.DriverSwitch;
import com.symbian.utils.XSLTUtils;

/**
 * @author EngineeringTools
 *
 */
public class Result {
	
	// Enumerations on type of result file.
	/** PC enumeration. */
	public static final int PC = 0;
	
	/** Symbian enumeration. */
	public static final int SYMBIAN = 1;
	
	// Generic settings constants
	
	/** Generic Logger. */
	private static final Logger LOGGER = Logger.getLogger(Result.class.getName());
	
	/** Generic settings/configuration. */
	private static final ReportFactory REPORT_FACTORY = ReportFactory.eINSTANCE;
	
	private static final String TRACE_PREFIX = "_swtrace.utf";

	// Result file
	/** Resource for Result file. */
	private static Resource sResource;
	
	/** EMF Report object for Result file. */
	private static Report sReport;
	
	/** Path to output results to. */
	private static File sOutputPath;
	
	/** path to the xml file */
	private static File iReportXml;
	
	/** report name */
	private static String iReportName;
	
	/** Execute Finished event. */
	private TaskFinishedEvent iTaskFinishedEvent = null;
	
	/**
	 * Constructor for Result. This attaches a listener to the Visitor.
	 * 
	 * @param aVisitor The visitor calling the Result Parser. This is what will be used to attach the listener.
	 * @param aTask The root task.
	 */
	
	public Result() {
		
		// Setup the Report EMF framework
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("report", new ReportResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(DriverPackage.eNS_URI, DriverPackage.eINSTANCE);
		
		// Add Resource
		try {
			sResource = resourceSet.createResource(URI.createFileURI(ModelUtils.getBaseDirectory(null, TDConfig.getInstance().getPreferenceInteger(TDConfig.RUN_NUMBER)) + "testdriver.report"));
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Could not get the configuration", lParseException);
		}
		sReport = REPORT_FACTORY.createReport();
		
		// Add Document Root
		DocumentRoot lDocumentRoot = REPORT_FACTORY.createDocumentRoot();
		lDocumentRoot.setReport(sReport);
		sResource.getContents().add(lDocumentRoot);
		
	}
	
	
	/**  
	 * 
	 * @param aVisitor
	 * @param aTask
	 * @param aIsPcVisitor
	 * @return 
	 */
	public boolean initResult(final Visitor aVisitor, final Task aTask, boolean aIsPcVisitor) {
		
		boolean lReturn = true;
		// Setup listener to the Visitor
		aVisitor.addVisitorListener(new IVisitorEventListener() {
			
			private Stack<Long> iTimerStack = new Stack<Long>();

			public void taskFinished(TaskFinishedEvent aVisitorEvent) {
				iTaskFinishedEvent = aVisitorEvent;
				
				long lDuration = 0;
				if (!iTimerStack.isEmpty()) {
					lDuration = iTimerStack.pop().longValue();
				}
				
				getReport(lDuration);
			}

			public void taskStarted(TaskStartedEvent aVisitorEvent) {
				iTimerStack.push(new Long(System.currentTimeMillis()));
			}
		});
		
		try {
			TDConfig CONFIG = TDConfig.getInstance();
			//set XML output location
			sOutputPath = new File((aIsPcVisitor ? CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT) : CONFIG.getPreferenceFile(TDConfig.RESULT_ROOT)),
					ModelUtils.getBaseDirectory((aIsPcVisitor ? -1 : CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER))));

			
			iReportName = (aIsPcVisitor ? "build" : "run" + CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER))
			+ "_" + CONFIG.getPreference(TDConfig.BUILD_NUMBER);
			iReportXml = new File(sOutputPath, iReportName + ".xml");
						
			setReportHeader(aTask, aIsPcVisitor);
			
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Could not get the configuration", lParseException);
			lReturn = false;
		} catch (SecurityException lSecurityException) {
			lReturn = false;
			LOGGER.log(Level.SEVERE, "Sercurity exception while adding logging handler", lSecurityException);
		}
		return lReturn;
	}
	
	/**
	 * Transform the XML result file by XSLT to HTML and TestLog
	 * 
	 * @param aIsPcVisitor If the visitor is a PC Visitor or not.
	 */
	public void doXSLT(boolean aIsPcVisitor) {
		try {
			if (!iReportXml.exists()) {
				return;
			}
			XSLTUtils.transformXml(iReportXml, sOutputPath, iReportName + ".html", "/resource/xslt/oldTDReport.xsl", Result.class);
			if (!aIsPcVisitor) {
				XSLTUtils.transformXml(iReportXml, sOutputPath, "testLog.txt", "/resource/xslt/testLog.xsl", Result.class);
			}
		} catch (IOException lIOException) {
			lIOException.printStackTrace();
			LOGGER.log(Level.SEVERE, "Recieved an IO error while creating the HTML result file.", lIOException);
		}
	}

	/**
	 * @param aDuration
	 */
	private void getReport(final long aDuration) {
			BaseReport aReport = null;
			
			// Run appropriate runner
			if (iTaskFinishedEvent.getEObject() instanceof CmdPC) {
				aReport = caseCmd(true);
			} else if (iTaskFinishedEvent.getEObject() instanceof CmdSymbian) {
				aReport = caseCmd(false);
			} else if (iTaskFinishedEvent.getEObject() instanceof Build) {
				aReport = caseBuild();
			} else if (iTaskFinishedEvent.getEObject() instanceof TestExecuteScript || iTaskFinishedEvent.getEObject() instanceof Rtest) {
				aReport = caseTest();
			} else if (iTaskFinishedEvent.getEObject() instanceof Transfer) {
				aReport = caseTransfer();
			} else if (iTaskFinishedEvent.getEObject() instanceof Task) {
				aReport = caseTask();
			} //else { //if ( ! (iTaskFinishedEvent.getEObject() instanceof ExecuteOnSymbian)) {
			//	LOGGER.warning("Could not add the event to the results report: " + iTaskFinishedEvent.getEObject().getClass().getName());
			//}
			
			if (aReport != null) {
				
				setDuration(aReport, System.currentTimeMillis() - aDuration);
				setException(aReport);
				aReport.setTask(setName(iTaskFinishedEvent.getEObject()));
				sReport.getAReport().add(aReport);
				// Write to output
				writeReportXml(iReportXml);
			}
	}
	
	/**
	 * @return A Report corresponding to a generic task.
	 */
	private GenericReport caseTask() {
		GenericReport lGenericReport = ReportFactory.eINSTANCE.createGenericReport();
		lGenericReport.setName(iTaskFinishedEvent.isPCVisitor() ? "Repository Creation" : "Install/Uninstall of Repository");
        
		String traceFilePath = getTraceFile();
		if (traceFilePath != null) {
		    lGenericReport.setTrace(traceFilePath);
		    //set the hasTrace flag to true.
		    //without this flag, the xlst won't create trace column at all
			sReport.getReportInfo().getInfo().put("hasTrace", "true");
		}
		if (iTaskFinishedEvent.isWarning()) {
			lGenericReport.setResult(GenericResult.WARNING_LITERAL);
		} else if (iTaskFinishedEvent.getExceptions() != null && !iTaskFinishedEvent.getExceptions().isEmpty()) {
			lGenericReport.setResult(GenericResult.ERROR_LITERAL);
		} else {
			lGenericReport.setResult(GenericResult.PASS_LITERAL);
		}
		
		return lGenericReport;
	}

	/**
	 * try to get the trace data of the task
	 * 
	 * @return null if no trace data
	 */
	private String getTraceFile() {
		
		//check if the task has starttrace subtask
		EObject task = iTaskFinishedEvent.getEObject();
		boolean hasStartTrace = false;
		for (EObject child : task.eContents()) {
			if (child instanceof StartTrace) {
				hasStartTrace = true;
				break;
			}
		}
        if (!hasStartTrace) {
        	return null;
        }
		String taskName = setName(iTaskFinishedEvent.getEObject());
		if (taskName.indexOf(".") > 0) {
			taskName = taskName.substring(taskName.indexOf(".") + 1);
		}
		String fileName = taskName + TRACE_PREFIX;
		
		File traceFile = new File(sOutputPath, fileName);
		LOGGER.log(Level.INFO, "trace file url:" + traceFile.getAbsolutePath());
		//until here, we can't check the exists of trace file. since they will be copied later
		try {
		    return traceFile.toURL().toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @throws  
	 * @throws IOException 
	 * 
	 */
	private BaseReport caseTest() {
		// Parse the results
		if (!iTaskFinishedEvent.isPCVisitor()) {
			// Setup Parser
			ResultParser lResultParser = null;
			BaseReport lTestReport = null;
			
			if (iTaskFinishedEvent.getEObject() instanceof TestExecuteScript) {
				lTestReport = ReportFactory.eINSTANCE.createTefReport();
				lResultParser = new TefResultParser(iTaskFinishedEvent.getEObject(), lTestReport);
			} else {
				lTestReport = ReportFactory.eINSTANCE.createGenericReport();
				lResultParser = new RTestResultParser(iTaskFinishedEvent.getEObject(), lTestReport);
			}
			
			try {	
				// Parse Results
				lResultParser.parseResults(sOutputPath.toString().length());
				
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "Could not parse Result file due to an IO Exception.", lIOException);
				lResultParser.createEmptyReport();
			} catch (ParseException lParseException) {
				LOGGER.log(Level.SEVERE, "Could not parse Result file due to an Configuration Exception.", lParseException);
				lResultParser.createEmptyReport();
			} finally {
				// Calculate summary
				lResultParser.createSummary(sReport.getReportInfo().getInfo());
			}
			
			return lTestReport;
		}
		
		return null;
	}

	/**
	 */
	private GenericReport caseTransfer() {
		// For transferToSymbian do nothing: covered in Execute
		
		// For retrieveFromSymbian
		if (iTaskFinishedEvent.getEObject().eContainer() instanceof RetrieveFromSymbian && !iTaskFinishedEvent.isPCVisitor()) {
			GenericReport lGenericReport = ReportFactory.eINSTANCE.createGenericReport();
			lGenericReport.setName("Retrieve of File:" + ((Transfer) iTaskFinishedEvent.getEObject()).getSymbianPath());
			
			if (iTaskFinishedEvent.isWarning()) {
				lGenericReport.setResult(GenericResult.WARNING_LITERAL);
			} else if (iTaskFinishedEvent.getExceptions() != null && !iTaskFinishedEvent.getExceptions().isEmpty()) {
				lGenericReport.setResult(GenericResult.ERROR_LITERAL);
			} else {
				lGenericReport.setResult(GenericResult.PASS_LITERAL);
			}
			
			return lGenericReport;
		}
		
		return null;
	}

	/**
	 */
	private GenericReport caseBuild() {
		if (iTaskFinishedEvent.isPCVisitor()) {
			GenericReport lBuildReport = ReportFactory.eINSTANCE.createGenericReport();
			Build lBuild = (Build) iTaskFinishedEvent.getEObject();

			lBuildReport.setName(lBuild.getURI());
			
			if (iTaskFinishedEvent.isWarning()) {
				lBuildReport.setResult(GenericResult.WARNING_LITERAL);
			} else if (iTaskFinishedEvent.getExceptions() != null && !iTaskFinishedEvent.getExceptions().isEmpty()) {
				lBuildReport.setResult(GenericResult.ERROR_LITERAL);
			} else {
				lBuildReport.setResult(GenericResult.PASS_LITERAL);
			}
			
			return lBuildReport;
		}
		
		return null;
	}

	/**
	 */
	private GenericReport caseCmd(final boolean aIsPC) {
		GenericReport lGenericReport = ReportFactory.eINSTANCE.createGenericReport();
		
		if (iTaskFinishedEvent.isWarning()) {
			lGenericReport.setResult(GenericResult.WARNING_LITERAL);
		} else if (iTaskFinishedEvent.getExceptions() != null && !iTaskFinishedEvent.getExceptions().isEmpty()) {
			lGenericReport.setResult(GenericResult.ERROR_LITERAL);
		} else {
			lGenericReport.setResult(GenericResult.PASS_LITERAL);
		}
		
		if (aIsPC) {
			lGenericReport.setName(((CmdPC) iTaskFinishedEvent.getEObject()).getValue());
		} else {
			lGenericReport.setName(((CmdSymbian) iTaskFinishedEvent.getEObject()).getStatCommand().getLiteral());
		}
		
		return lGenericReport;
	}

	/**
	 * @param aTask
	 * @throws ParseException
	 */
	private void setReportHeader(final Task aTask, boolean aIsPCVititor) throws ParseException {
		// Calculate number of Tests
		TDConfig CONFIG = TDConfig.getInstance();
		DriverSwitch lCountSwitch = new DriverSwitch() {
			public Object caseTestExecuteScript(TestExecuteScript aObject) {
				return aObject;
			}
			
			public Object caseRtest(Rtest aObject) {
				return aObject;
			}
		};
		
		int lCountTest = 0;
		for (Iterator lCountIter = aTask.eAllContents(); lCountIter.hasNext();) {
			if (lCountSwitch.doSwitch((EObject) lCountIter.next()) != null) {
				lCountTest++;
			}
		}
		
		// Add General Information
		ReportInfo lReportInfo = REPORT_FACTORY.createReportInfo();
		lReportInfo.getInfo().put("platform", CONFIG.getPreference(TDConfig.PLATFORM));
		lReportInfo.getInfo().put("build", CONFIG.getPreference(TDConfig.VARIANT));
		lReportInfo.getInfo().put("runNumber", Integer.toString(CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER)));
		lReportInfo.getInfo().put("transport", CONFIG.getPreference(TDConfig.TRANSPORT));
		lReportInfo.getInfo().put("date", new Date().toString());
		lReportInfo.getInfo().put("rootAddress", CONFIG.getPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS).toString());
		lReportInfo.getInfo().put("buildNumber", CONFIG.getPreference(TDConfig.BUILD_NUMBER));
		lReportInfo.getInfo().put("testCount", new Integer(lCountTest).toString());
		
		//new
		lReportInfo.getInfo().put("Command", aIsPCVititor ? "Build" : "Run");
		lReportInfo.getInfo().put("ReportXMLLocation", iReportXml.getAbsoluteFile().toString());
	
		sReport.setReportInfo(lReportInfo);
	}

	/**
	 * @param aTaskName
	 */
	private void setDuration(final BaseReport aBaseReport, final long aDuration) {
		final int lMillisecInSec = 1000;
		final int lSecInHour = 3600;
		final int lSecInMinute = 60;
		
		long lDurationInSeconds = aDuration / lMillisecInSec;
	
		long lHours = lDurationInSeconds / lSecInHour;
		lDurationInSeconds = lDurationInSeconds - (lHours * lSecInHour);
		long lMinutes = lDurationInSeconds / lSecInMinute;
		lDurationInSeconds = lDurationInSeconds - (lMinutes * lSecInMinute);
		long lSeconds = lDurationInSeconds;
		
		String lDurationLiteral = lHours + ":" + lMinutes + ":" + lSeconds;
		LOGGER.fine("Duration of test (h:m:s) = " + lDurationLiteral);
		
		aBaseReport.setDuration(lDurationLiteral);
	}

	/**
	 * @return The name of the currents object container.
	 */
	private String setName(final EObject aEObject) {
		if (aEObject == null) {
			return "";
		}
		EObject lParentObject = aEObject.eContainer();
		String lObjectName = sResource.getURIFragment(aEObject);
		
		if (aEObject instanceof Task) {
			if (lParentObject instanceof Driver) {
				return lObjectName;
			} else if (lParentObject instanceof Task) {
				return setName(lParentObject).concat("." + lObjectName);
			}
		}
		
		return setName(lParentObject);
	}

	/**
	 */
	private void setException(final BaseReport aBaseReport) {
		Map<? extends Exception, ESeverity> lExceptions = iTaskFinishedEvent.getExceptions();
		if (lExceptions != null && !lExceptions.isEmpty()) {
			EList lExceptionList = aBaseReport.getExecptionReport();
			
			for (Exception lException : lExceptions.keySet()) {
				//handle the crash exception
				if (lException instanceof CrashException) {
					CrashException crashExp = (CrashException)lException;
					aBaseReport.setCrash(true);
					if (crashExp.getCoreDumpUrl() != null) {
						aBaseReport.setCoredump(crashExp.getCoreDumpUrl().toString());
						sReport.getReportInfo().getInfo().put("hasCoreDump", "true");
					}
					continue;
				}
				
				ExceptionReport lExceptionReport = REPORT_FACTORY.createExceptionReport();
				lExceptionReport.setMessage(lException.getMessage());
				
				StringBuffer lStringBuffer = new StringBuffer();
				StackTraceElement[] lStackTrace = lException.getStackTrace();
				for (int i = 0; i < lStackTrace.length; i++) {
					lStringBuffer.append(lStackTrace[i].getFileName() + "#" + lStackTrace[i].getMethodName() + " (" + lStackTrace[i].getLineNumber() + ")\n");
				}
				lExceptionReport.setStackTrace(lStringBuffer.toString());
				
				lExceptionList.add(lExceptionReport);
				
				if (lException instanceof TimeLimitExceededException) {
					aBaseReport.setTimeout(true);
				} else {
					aBaseReport.setTimeout(false);
				}
			}
		} else {
			aBaseReport.setTimeout(false);
		}
	}

	/**
	 * @param aXml
	 */
	private void writeReportXml(final File aXml) {
		try {
			if (!aXml.getParentFile().isDirectory() && !aXml.getParentFile().mkdirs()) {
				throw new IOException();
			}
			
			Map<String, String> lSaveOptions = new HashMap<String, String>();
			lSaveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
			
			sResource.save(new FileOutputStream(aXml), lSaveOptions);
			
			LOGGER.finer("Created XML results page at: " + aXml.getCanonicalPath());
			
		} catch (FileNotFoundException lFileNotFoundException) {
			LOGGER.log(Level.WARNING, "Could not save the report to: " + aXml, lFileNotFoundException);
		} catch (IOException lException) {
			LOGGER.log(Level.WARNING, "Could not save the report to: " + aXml, lException);
		}
	}

	public Report getReport() {
		return sReport;
	}
}
