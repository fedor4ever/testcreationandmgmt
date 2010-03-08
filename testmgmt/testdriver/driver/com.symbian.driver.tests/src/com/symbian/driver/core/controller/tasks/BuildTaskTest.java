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

package com.symbian.driver.core.controller.tasks;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;
import org.jmock.Mock;
import org.jmock.builder.IdentityBuilder;
import org.jmock.builder.NameMatchBuilder;
import org.jmock.cglib.MockObjectTestCase;
import org.jmock.core.stub.StubSequence;

import com.symbian.driver.core.controller.impl.PCVisitor;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.environment.TimeOut;
import com.symbian.driver.engine.util.ExecuteFactory;
import com.symbian.driver.mockstubs.TDConfigMock;
import com.symbian.driver.plugins.build.BuildTask;

public class BuildTaskTest extends MockObjectTestCase {

	private Mock iExecuteFactoryMock;
	private Mock iIExecuteOnHostMock;
	private Mock iTDConfig;
	
	private File iCWD = new File("./src/com/symbian/driver/core/controller/tasks");
	private File iBldInf = new File(iCWD, "bld.inf");
	private File iAbldBat = new File(iCWD, "abld.bat");
	
	protected void setUp() throws Exception {
		super.setUp();
		
		iExecuteFactoryMock = mock(ExecuteFactory.class, "ExecuteFactory");
		iIExecuteOnHostMock = mock(IExecuteOnHost.class, "ExecuteOnHost");
		iTDConfig = mock(TDConfig.class, "TDConfig");
		
		if (iBldInf.isFile() && !iBldInf.delete()) {
			fail();
		}
		
		if (iAbldBat.isFile() && !iAbldBat.delete()) {
			fail();
		}
		
		if (!iBldInf.createNewFile()) {
			fail();
		}
		
		if (!iAbldBat.createNewFile()) {
			fail();
		}
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		
		if (iBldInf.isFile() && !iBldInf.delete()) {
			fail();
		}
		
		if (iAbldBat.isFile() && !iAbldBat.delete()) {
			fail();
		}
	}
	
	private void createDoBeforeBuildMock(boolean aBldmake, boolean aClean, boolean aRBuild) {
		iExecuteFactoryMock = mock(ExecuteFactory.class, "ExecuteFactory");
		iIExecuteOnHostMock = mock(IExecuteOnHost.class, "ExecuteOnHost");
		iTDConfig = mock(TDConfig.class, "TDConfig");
		
		int lNumberOfCalls = 0;
		if (aBldmake) {
			if (!aRBuild && aClean) {
				lNumberOfCalls = 3;
			} else {
				lNumberOfCalls = 1;
			}
		} else if (!aRBuild && aClean) {
			lNumberOfCalls = 1;
		}
		
		iIExecuteOnHostMock
			.expects(exactly(lNumberOfCalls))
			.method("getOutput");
	
		iIExecuteOnHostMock
			.expects(exactly(lNumberOfCalls))
			.method("doTask")
			.with(eq(true), eq(TimeOut.NO_TIMEOUT));
		
		iExecuteFactoryMock
			.expects(exactly(lNumberOfCalls))
			.method("createExecuteOnHost")
			.with(isA(File.class), isA(String.class))
			.will(returnValue(iIExecuteOnHostMock.proxy()));
		
		TDConfigMock lTDConfigMock = new TDConfigMock();
		
		lTDConfigMock
			.setClean(true)
			.setBldMake(true)
			.setPlatform("armv5")
			.setVariant("udeb");
		
		lTDConfigMock.createTDMockGetters(iTDConfig);
		
		iTDConfig
			.stubs()
			.method("isPreference")
			.with(eq(TDConfig.BLDMAKE))
			.will(returnValue(aBldmake));
		
		iTDConfig
			.stubs()
			.method("isPreference")
			.with(eq(TDConfig.CLEAN))
			.will(returnValue(aClean));
		
		
		iTDConfig
			.stubs()
			.method("getPreference")
			.with(eq(TDConfig.PLATFORM))
			.will(returnValue("armv5"));
		
		iTDConfig
			.stubs()
			.method("getPreference")
			.with(eq(TDConfig.VARIANT))
			.will(returnValue("urel"));
		
		PCVisitor.setRBuild(aRBuild);
	}
	
	private void runTestDoBeforeBuild() {
		
		BuildTask lBuildTask = new BuildTask(
				(ExecuteFactory) iExecuteFactoryMock.proxy(),
				(TDConfig) iTDConfig.proxy());
		
		try {
			lBuildTask.doBeforeBuild(iCWD, true);
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			lTimeLimitExceededException.printStackTrace();
			fail();
		} catch (IOException lIOException) {
			lIOException.printStackTrace();
			fail();
		} catch (ParseException lParseException) {
			lParseException.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Testing doBeforeBuild with BldMakeOn
	 */
	public void testDoBeforeBuild_Build_Clean_RBuild() {
		createDoBeforeBuildMock(true, true, true);
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild_Build_Clean() {
		createDoBeforeBuildMock(true, true, false);
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild_Build_RBuild() {
		createDoBeforeBuildMock(true, false, true);
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild_Build() {
		createDoBeforeBuildMock(true, false, false);
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild_Clean_RBuild() {
		createDoBeforeBuildMock(false, true, true);
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild_Clean() {
		createDoBeforeBuildMock(false, true, false);
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild_RBuild() {
		createDoBeforeBuildMock(false, false, true);
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild_Clean_NoAbldBat() {
		if (!iAbldBat.delete()) {
			fail();
		}
		
		PCVisitor.setRBuild(false);
		
		iIExecuteOnHostMock
			.expects(never())
			.method("getOutput");
	
		iIExecuteOnHostMock
			.expects(never())
			.method("doTask")
			.with(eq(true), eq(TimeOut.NO_TIMEOUT));
		
		iExecuteFactoryMock
			.expects(never())
			.method("createExecuteOnHost")
			.with(isA(File.class), isA(String.class))
			.will(returnValue(iIExecuteOnHostMock.proxy()));
		
		iTDConfig
			.expects(once())
			.method("isPreference")
			.with(eq(TDConfig.BLDMAKE))
			.will(returnValue(false));
		
		iTDConfig
			.expects(never())
			.method("isPreference")
			.with(eq(TDConfig.CLEAN))
			.will(returnValue(true));
		
		iTDConfig
			.expects(never())
			.method("getPreference")
			.with(eq(TDConfig.PLATFORM))
			.will(returnValue("armv5"));
		
		iTDConfig
			.expects(never())
			.method("getPreference")
			.with(eq(TDConfig.VARIANT))
			.will(returnValue("urel"));
		
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild() {
		createDoBeforeBuildMock(false, false, false);
		runTestDoBeforeBuild();
	}
	
	public void testDoBeforeBuild_NoBldInf() {
		if (!iBldInf.delete()) {
			fail();
		}
		
		BuildTask lBuildTask = new BuildTask(
				(ExecuteFactory) iExecuteFactoryMock.proxy(),
				(TDConfig) iTDConfig.proxy());
		
		try {
			lBuildTask.doBeforeBuild(iCWD, true);
			fail();
		} catch (IOException lIOException) {
			// PASS
		} catch (Exception lException) {
			lException.printStackTrace();
			fail();
		}
	}
		
	public void testDoBuild_RBuild_NoAbldBat() {
		if (!iAbldBat.delete()) {
			fail();
		}
		
		String lPlatform = "armv5";
		String lVariant = "udeb";
		
		iTDConfig
			.expects(once())
			.method("getPreference")
			.with(eq(TDConfig.PLATFORM))
			.will(returnValue(lPlatform));
		

		iTDConfig
			.expects(once())
			.method("getPreference")
			.with(eq(TDConfig.VARIANT))
			.will(returnValue(lVariant));
		
		iExecuteFactoryMock
			.expects(exactly(2))
			.method("createExecuteOnHost")
			.with(isA(File.class), isA(String.class))
			.will(returnValue(iIExecuteOnHostMock.proxy()));
		
		iIExecuteOnHostMock
			.expects(exactly(2))
			.method("doTask")
			.with(eq(true), ANYTHING);
		
		String[] lFiles = {"testexecute.exe", "testexecute.dll", "file.map", "noensense.blah"};
		String lReturnGetOutput = "";
		for (int i = 0; i < lFiles.length; i++) {
			lReturnGetOutput += "epoc32\\RELEASE\\" + lPlatform.toUpperCase() + "\\"
				+ lVariant.toUpperCase() + "\\" + lFiles[i] + "\n";
		}
		
		iIExecuteOnHostMock
			.expects(exactly(2))
			.method("getOutput")
			.will(returnValue(lReturnGetOutput));
		
		BuildTask lBuildTask = new BuildTask(
				(ExecuteFactory) iExecuteFactoryMock.proxy(),
				(TDConfig) iTDConfig.proxy());

		try {
			
			Vector lTransferVector = lBuildTask.doBuild(iCWD, true, "mmp");
			
			if (lTransferVector == null 
					|| lTransferVector.size() != 1
					|| ((File) lTransferVector.get(0)).getAbsolutePath().indexOf(lFiles[0]) == -1) {
				fail();
			}
			
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			lTimeLimitExceededException.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testHasWarning() {
		fail("Not yet implemented");
	}

	public void testClearOutputBuffer() {
		fail("Not yet implemented");
	}

}
