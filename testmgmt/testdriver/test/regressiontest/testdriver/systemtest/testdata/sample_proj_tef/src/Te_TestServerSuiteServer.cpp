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



#include "Te_TestServerSuiteServer.h"
#include "ErrorStep.h"
#include "PanicStep.h"
#include "ValidStep.h"

_LIT(KServerName,"Te_TestServerSuite");
CTe_TestServerSuite* CTe_TestServerSuite::NewL()
	{
	CTe_TestServerSuite* server = new (ELeave) CTe_TestServerSuite();
	server->ConstructL(KServerName);
	server->iScheduler = new (ELeave) CActiveScheduler;
	return server;
	}

CTe_TestServerSuite::~CTe_TestServerSuite()
	{
	delete iScheduler;
	}

// Secure variants much simpler
// For EKA2, just an E32Main and a MainL()
LOCAL_C void MainL()
/**
 * Secure variant
 * Much simpler, uses the new Rendezvous() call to sync with the client
 */
	{
	// Leave the hooks in for platform security
#if (defined __DATA_CAGING__)
	RProcess().DataCaging(RProcess::EDataCagingOn);
	RProcess().DataCaging(RProcess::ESecureApiOn);
#endif
	CActiveScheduler* sched=NULL;
	sched=new(ELeave) CActiveScheduler;
	CActiveScheduler::Install(sched);
	CTe_TestServerSuite* server = NULL;
	// Create the CTestServer derived server
	TRAPD(err,server = CTe_TestServerSuite::NewL());
	if(!err)
		{
		// Sync with the client and enter the active scheduler
		RProcess::Rendezvous(KErrNone);
		sched->Start();
		}
	delete server;
	delete sched;
	}



GLDEF_C TInt E32Main()
/**
 * @return - Standard Epoc error code on process exit
 * Secure variant only
 * Process entry point. Called by client using RProcess API
 */
	{
	__UHEAP_MARK;
	CTrapCleanup* cleanup = CTrapCleanup::New();
	if(cleanup == NULL)
		{
		return KErrNoMemory;
		}
	TRAPD(err,MainL());
	delete cleanup;
	__UHEAP_MARKEND;
	return err;
    }


CTestStep* CTe_TestServerSuite::CreateTestStep(const TDesC& aStepName)
/**
 * @return - A CTestStep derived instance
 * Secure and non-secure variants
 * Implementation of CTestServer pure virtual
 */
	{
	CTestStep* testStep = NULL;
              if(aStepName == KErrorStep)
                            testStep = new CErrorStep(this);
              else if(aStepName == KPanicStep)
                            testStep = new CPanicStep(/*this*/);
              else if(aStepName == KValidStep)
                            testStep = new CValidStep(this);

	return testStep;
	}
