/**
 * $(copyright)
 *
 * @file $(baseName)SuiteServer.cpp
 * @internalTechnology
 *
 * Example file/test code to demonstrate how to develop a TestExecute Server
 * Developers should take this project as a template and substitute their own
 *
 * for (WINS && !EKA2) versions will be xxxServer.Dll and require a thread to be started
 * in the process of the client. The client initialises the server by calling the
 * one and only ordinal.
 */

#include "$(baseName)SuiteServer.h"
#include "test0Step.h"
#include "test1Step.h"
#include "test2Step.h"

_LIT(KServerName,"$(baseName)Suite");
C$(baseName)Suite* C$(baseName)Suite::NewL()
/**
 * @return - Instance of the test server
 * Same code for Secure and non-secure variants
 * Called inside the MainL() function to create and start the
 * CTestServer derived server.
 */
	{
	C$(baseName)Suite * server = new (ELeave) C$(baseName)Suite();
	CleanupStack::PushL(server);

	server->ConstructL(KServerName);
	CleanupStack::Pop(server);
	return server;
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
	C$(baseName)Suite* server = NULL;
	// Create the CTestServer derived server
	TRAPD(err,server = C$(baseName)Suite::NewL());
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


CTestStep* C$(baseName)Suite::CreateTestStep(const TDesC& aStepName)
/**
 * @return - A CTestStep derived instance
 * Secure and non-secure variants
 * Implementation of CTestServer pure virtual
 */
	{
	CTestStep* testStep = NULL;
              if(aStepName == Ktest0Step)
                            testStep = new Ctest0Step();
              else if(aStepName == Ktest1Step)
                            testStep = new Ctest1Step();
              else if(aStepName == Ktest2Step)
                            testStep = new Ctest2Step();

	return testStep;
	}
