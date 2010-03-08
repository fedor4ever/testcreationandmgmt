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

package com.symbian.driver.mockstubs;

import java.io.File;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

import com.symbian.driver.core.environment.TDConfig;

public class TDConfigMock extends MockObjectTestCase {
	
	private boolean iBldMake = true;
	private int iBuildNumber = 1;
	private boolean iClean = true;
	private String iClient = "";
	private String iEntryPointAddress = "";
	private File iEpocRoot = new File("");
	private int iJobId = 6;
	private File iJobsFolder = new File("");
	private String iKernel = "EKA2";
	private String iMode = "both";
	private String iPlatform = "armv5";
	private boolean iPlatSec = true;
	private String iRDebug = "com1";
	private File iRepositoryRoot = new File("");
	private File iResultRoot = new File("");
	private int iRom = 15;
	private int iRunNumber = 16;
	private int iServer = 17;
	private int iServerName = 18;
	private int iService = 19;
	private int iSysBin = 20;
	private File iSourceRoot = new File("");
	private int iTargetTest = 22;
	private int iTestExecute = 23;
	private int iTestExecuteDependencies = 24;
	private int iTestPackage = 25;
	private int iTransport = 26;
	private int iUCCAddress = 27;
	private String iVariant = "";
	private int iWinTap = 29;
	private File iWorkingPath = new File("");
	private File iXmlRoot = new File("");
	private String iCommdb = "";
	private int iUidFirst = 33;
	private int iUidLast = 34;
	private File iKey = new File("");
	private File iCert = new File("");
	
	
	
	public TDConfigMock createTDMockGetters(Mock aMock) {
		
		aMock
			.stubs()
			.method("isPreference")
			.with(eq(TDConfig.BLDMAKE))
			.will(returnValue(iBldMake));
		
		aMock
			.stubs()
			.method("getPreferenceInteger")
			.with(eq(TDConfig.BUILD_NUMBER))
			.will(returnValue(iBuildNumber));
		
		aMock
			.stubs()
			.method("isPreference")
			.with(eq(TDConfig.CLEAN))
			.will(returnValue(iClean));
		
		aMock
			.stubs()
			.method("getPreference")
			.with(eq(TDConfig.CLIENT))
			.will(returnValue(iClient));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.ENTRY_POINT_ADDRESS))
			.will(returnValue(iEntryPointAddress));

		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.EPOC_ROOT))
			.will(returnValue(iEpocRoot));

		aMock
			.stubs()
			.method("getPreferenceInteger")
			.with(eq(TDConfig.JOB_ID))
			.will(returnValue(iJobId));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.JOBS_FOLDER))
			.will(returnValue(iJobsFolder));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.KERNEL))
			.will(returnValue(iKernel));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.MODE))
			.will(returnValue(iMode));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.PLATFORM))
			.will(returnValue(iPlatform));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.PLATSEC))
			.will(returnValue(iPlatSec));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.RDEBUG))
			.will(returnValue(iRDebug));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.REPOSITORY_ROOT))
			.will(returnValue(iRepositoryRoot));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.RESULT_ROOT))
			.will(returnValue(iResultRoot));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.ROM))
			.will(returnValue(iRom));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.RUN_NUMBER))
			.will(returnValue(iRunNumber));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SERVER))
			.will(returnValue(iServer));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SERVER_NAME))
			.will(returnValue(iServerName));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SERVICE))
			.will(returnValue(iService));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SYS_BIN))
			.will(returnValue(iSysBin));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SOURCE_ROOT))
			.will(returnValue(iSourceRoot));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TARGET_TEST))
			.will(returnValue(iTargetTest));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TEST_EXECUTE))
			.will(returnValue(iTestExecute));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TEST_EXECUTE_DEPENDENCIES))
			.will(returnValue(iTestExecuteDependencies));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TEST_PACKAGE))
			.will(returnValue(iTestPackage));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TRANSPORT))
			.will(returnValue(iTransport));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.UCC_IP_ADDRESS))
			.will(returnValue(iUCCAddress));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.VARIANT))
			.will(returnValue(iVariant));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.WINTAP))
			.will(returnValue(iWinTap));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.WORKING_PATH))
			.will(returnValue(iWorkingPath));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.XML_ROOT))
			.will(returnValue(iXmlRoot));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.COMMDB))
			.will(returnValue(iCommdb));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.UIDFIRST))
			.will(returnValue(iUidFirst));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.UIDLAST))
			.will(returnValue(iUidLast));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.KEY))
			.will(returnValue(iKey));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.CERT))
			.will(returnValue(iCert));
		
		return this;
	}

	
	public TDConfigMock createTDMockSetters(Mock aMock) {
		
		aMock
			.stubs()
			.method("setPreferenceBoolean")
			.with(eq(TDConfig.BLDMAKE), isA(boolean.class));
		
		aMock
			.stubs()
			.method("getPreferenceInteger")
			.with(eq(TDConfig.BUILD_NUMBER))
			.will(returnValue(iBuildNumber));
		
		aMock
			.stubs()
			.method("isPreference")
			.with(eq(TDConfig.CLEAN))
			.will(returnValue(iClean));
		
		aMock
			.stubs()
			.method("getPreference")
			.with(eq(TDConfig.CLIENT))
			.will(returnValue(iClient));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.ENTRY_POINT_ADDRESS))
			.will(returnValue(iEntryPointAddress));

		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.EPOC_ROOT))
			.will(returnValue(iEpocRoot));

		aMock
			.stubs()
			.method("getPreferenceInteger")
			.with(eq(TDConfig.JOB_ID))
			.will(returnValue(iJobId));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.JOBS_FOLDER))
			.will(returnValue(iJobsFolder));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.KERNEL))
			.will(returnValue(iKernel));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.MODE))
			.will(returnValue(iMode));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.PLATFORM))
			.will(returnValue(iPlatform));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.PLATSEC))
			.will(returnValue(iPlatSec));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.RDEBUG))
			.will(returnValue(iRDebug));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.REPOSITORY_ROOT))
			.will(returnValue(iRepositoryRoot));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.RESULT_ROOT))
			.will(returnValue(iResultRoot));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.ROM))
			.will(returnValue(iRom));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.RUN_NUMBER))
			.will(returnValue(iRunNumber));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SERVER))
			.will(returnValue(iServer));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SERVER_NAME))
			.will(returnValue(iServerName));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SERVICE))
			.will(returnValue(iService));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SYS_BIN))
			.will(returnValue(iSysBin));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.SOURCE_ROOT))
			.will(returnValue(iSourceRoot));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TARGET_TEST))
			.will(returnValue(iTargetTest));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TEST_EXECUTE))
			.will(returnValue(iTestExecute));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TEST_EXECUTE_DEPENDENCIES))
			.will(returnValue(iTestExecuteDependencies));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TEST_PACKAGE))
			.will(returnValue(iTestPackage));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.TRANSPORT))
			.will(returnValue(iTransport));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.UCC_IP_ADDRESS))
			.will(returnValue(iUCCAddress));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.VARIANT))
			.will(returnValue(iVariant));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.WINTAP))
			.will(returnValue(iWinTap));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.WORKING_PATH))
			.will(returnValue(iWorkingPath));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.XML_ROOT))
			.will(returnValue(iXmlRoot));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.COMMDB))
			.will(returnValue(iCommdb));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.UIDFIRST))
			.will(returnValue(iUidFirst));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.UIDLAST))
			.will(returnValue(iUidLast));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.KEY))
			.will(returnValue(iKey));
		
		aMock
			.stubs()
			.method("getPreferenceFile")
			.with(eq(TDConfig.CERT))
			.will(returnValue(iCert));
		
		return this;
	}



	/**
	 * @param aBldMake the bldMake to set
	 * @return 
	 */
	public TDConfigMock setBldMake(boolean aBldMake) {
		iBldMake = aBldMake;
		
		return this;
	}



	/**
	 * @param aBuildNumber the buildNumber to set
	 * @return 
	 */
	public TDConfigMock setBuildNumber(int aBuildNumber) {
		iBuildNumber = aBuildNumber;
		
		return this;
	}



	/**
	 * @param aCert the cert to set
	 * @return 
	 */
	public TDConfigMock setCert(File aCert) {
		iCert = aCert;
		
		return this;
	}



	/**
	 * @param aClean the clean to set
	 * @return 
	 */
	public TDConfigMock setClean(boolean aClean) {
		iClean = aClean;
		
		return this;
	}



	/**
	 * @param aClient the client to set
	 * @return 
	 */
	public TDConfigMock setClient(String aClient) {
		iClient = aClient;
		
		return this;
	}



	/**
	 * @param aCommdb the commdb to set
	 * @return 
	 */
	public TDConfigMock setCommdb(String aCommdb) {
		iCommdb = aCommdb;
		
		return this;
	}



	/**
	 * @param aEntryPointAddress the entryPointAddress to set
	 * @return 
	 */
	public TDConfigMock setEntryPointAddress(String aEntryPointAddress) {
		iEntryPointAddress = aEntryPointAddress;
		
		return this;
	}



	/**
	 * @param aEpocRoot the epocRoot to set
	 * @return 
	 */
	public TDConfigMock setEpocRoot(File aEpocRoot) {
		iEpocRoot = aEpocRoot;
		
		return this;
	}



	/**
	 * @param aJobId the jobId to set
	 * @return 
	 */
	public TDConfigMock setJobId(int aJobId) {
		iJobId = aJobId;
		
		return this;
	}



	/**
	 * @param aJobsFolder the jobsFolder to set
	 * @return 
	 */
	public TDConfigMock setJobsFolder(File aJobsFolder) {
		iJobsFolder = aJobsFolder;
		
		return this;
	}



	/**
	 * @param aKernel the kernel to set
	 * @return 
	 */
	public TDConfigMock setKernel(String aKernel) {
		iKernel = aKernel;
		
		return this;
	}



	/**
	 * @param aKey the key to set
	 * @return 
	 */
	public TDConfigMock setKey(File aKey) {
		iKey = aKey;
		
		return this;
	}



	/**
	 * @param aMode the mode to set
	 * @return 
	 */
	public TDConfigMock setMode(String aMode) {
		iMode = aMode;
		
		return this;
	}



	/**
	 * @param aPlatform the platform to set
	 * @return 
	 */
	public TDConfigMock setPlatform(String aPlatform) {
		iPlatform = aPlatform;
		
		return this;
	}



	/**
	 * @param aPlatSec the platSec to set
	 * @return 
	 */
	public TDConfigMock setPlatSec(boolean aPlatSec) {
		iPlatSec = aPlatSec;
		
		return this;
	}



	/**
	 * @param aDebug the rDebug to set
	 * @return 
	 */
	public TDConfigMock setRDebug(String aDebug) {
		iRDebug = aDebug;
		
		return this;
	}



	/**
	 * @param aRepositoryRoot the repositoryRoot to set
	 * @return 
	 */
	public TDConfigMock setRepositoryRoot(File aRepositoryRoot) {
		iRepositoryRoot = aRepositoryRoot;
		
		return this;
	}



	/**
	 * @param aResultRoot the resultRoot to set
	 * @return 
	 */
	public TDConfigMock setResultRoot(File aResultRoot) {
		iResultRoot = aResultRoot;
		
		return this;
	}



	/**
	 * @param aRom the rom to set
	 * @return 
	 */
	public TDConfigMock setRom(int aRom) {
		iRom = aRom;
		
		return this;
	}



	/**
	 * @param aRunNumber the runNumber to set
	 * @return 
	 */
	public TDConfigMock setRunNumber(int aRunNumber) {
		iRunNumber = aRunNumber;
		
		return this;
	}



	/**
	 * @param aServer the server to set
	 * @return 
	 */
	public TDConfigMock setServer(int aServer) {
		iServer = aServer;
		
		return this;
	}



	/**
	 * @param aServerName the serverName to set
	 * @return 
	 */
	public TDConfigMock setServerName(int aServerName) {
		iServerName = aServerName;
		
		return this;
	}



	/**
	 * @param aService the service to set
	 * @return 
	 */
	public TDConfigMock setService(int aService) {
		iService = aService;
		
		return this;
	}



	/**
	 * @param aSourceRoot the sourceRoot to set
	 * @return 
	 */
	public TDConfigMock setSourceRoot(File aSourceRoot) {
		iSourceRoot = aSourceRoot;
		
		return this;
	}



	/**
	 * @param aSysBin the sysBin to set
	 * @return 
	 */
	public TDConfigMock setSysBin(int aSysBin) {
		iSysBin = aSysBin;
		
		return this;
	}



	/**
	 * @param aTargetTest the targetTest to set
	 * @return 
	 */
	public TDConfigMock setTargetTest(int aTargetTest) {
		iTargetTest = aTargetTest;
		
		return this;
	}



	/**
	 * @param aTestExecute the testExecute to set
	 * @return 
	 */
	public TDConfigMock setTestExecute(int aTestExecute) {
		iTestExecute = aTestExecute;
		
		return this;
	}



	/**
	 * @param aTestExecuteDependencies the testExecuteDependencies to set
	 * @return 
	 */
	public TDConfigMock setTestExecuteDependencies(int aTestExecuteDependencies) {
		iTestExecuteDependencies = aTestExecuteDependencies;
		
		return this;
	}



	/**
	 * @param aTestPackage the testPackage to set
	 * @return 
	 */
	public TDConfigMock setTestPackage(int aTestPackage) {
		iTestPackage = aTestPackage;
		
		return this;
	}



	/**
	 * @param aTransport the transport to set
	 * @return 
	 */
	public TDConfigMock setTransport(int aTransport) {
		iTransport = aTransport;
		
		return this;
	}



	/**
	 * @param aAddress the uCCAddress to set
	 * @return 
	 */
	public TDConfigMock setUCCAddress(int aAddress) {
		iUCCAddress = aAddress;
		
		return this;
	}



	/**
	 * @param aUidFirst the uidFirst to set
	 * @return 
	 */
	public TDConfigMock setUidFirst(int aUidFirst) {
		iUidFirst = aUidFirst;
		
		return this;
	}



	/**
	 * @param aUidLast the uidLast to set
	 * @return 
	 */
	public TDConfigMock setUidLast(int aUidLast) {
		iUidLast = aUidLast;
		
		return this;
	}



	/**
	 * @param aVariant the variant to set
	 * @return 
	 */
	public TDConfigMock setVariant(String aVariant) {
		iVariant = aVariant;
		
		return this;
	}



	/**
	 * @param aWinTap the winTap to set
	 * @return 
	 */
	public TDConfigMock setWinTap(int aWinTap) {
		iWinTap = aWinTap;
		
		return this;
	}



	/**
	 * @param aWorkingPath the workingPath to set
	 * @return 
	 */
	public TDConfigMock setWorkingPath(File aWorkingPath) {
		iWorkingPath = aWorkingPath;
		
		return this;
	}



	/**
	 * @param aXmlRoot the xmlRoot to set
	 * @return 
	 */
	public TDConfigMock setXmlRoot(File aXmlRoot) {
		iXmlRoot = aXmlRoot;
		
		return this;
	}
	
}
