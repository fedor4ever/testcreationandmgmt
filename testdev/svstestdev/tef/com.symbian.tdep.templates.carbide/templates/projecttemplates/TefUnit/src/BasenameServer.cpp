/**
 * @file $(baseName)server.cpp
 * @internalTechnology
 * 
 * $(copyright)
 *
 * Example file/test code to demonstrate how to develop a TestExecute Server
 * Developers should take this project as a template and substitute their own
 *
 * for (WINS && !EKA2) versions will be xxxServer.Dll and require a thread to be started
 * in the process of the client. The client initialises the server by calling the
 * one and only ordinal.
 */

#include "$(baseName)server.h"

_LIT(KServerName,"$(baseName)");
C$(baseNameCapital)Server* C$(baseNameCapital)Server::NewL()
/**
 * @return - Instance of the test server
 * Same code for Secure and non-secure variants
 * Called inside the MainL() function to create and start the
 * C$(baseNameCapital)Server derived server.
 */
	{
	C$(baseNameCapital)Server * server = new (ELeave) C$(baseNameCapital)Server();
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
	C$(baseNameCapital)Server* server = NULL;
	// Create the C$(baseNameCapital)Server derived server
	TRAPD(err,server = C$(baseNameCapital)Server::NewL());
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



GLDEF_C const TTestName ServerName()
/**
 * ServerName
 *
 * @return - The TEF server name
 */
	{
	TTestName serverName(KServerName);
	return serverName;
	}



GLDEF_C CTestSuite* CreateTestSuiteL()
/**
 * CreateTestSuiteL
 *
 *
 * @return - The top level suite
 */
	{

$(serverSuiteSwitch)

	}



//GLDEF_C CTestStep* CreateTEFTestStep(const TdesC& aStepName, CTEFUnitServer& aServer)
/**
 * @return - A CTestStep derived instance
 * Secure and non-secure variants
 * Implementation of CTestServer pure virtual
 * Use with the TEFUnit server
 */
//	{
	
	// Initialise test step object to NULL if no TEF steps are assigned
//	CTestStep* testStep = NULL;
	
//	if(aStepName == K$(baseNameCapital)TestStep)
//      	{
//      	testStep = new C$(baseNameCapital)TestStep();
//      	}
	
//	return testStep;
//	}



CTestStep* C$(baseNameCapital)Server::CreateTestStep(const TDesC& aStepName)
/**
 * @return - A CTestStep derived instance
 * Secure and non-secure variants
 * Implementation of CTestServer pure virtual
 * Use with TEF Step Server
 */
	{
	CTestStep* $(baseNameCapital)Step = NULL;
	
	if(aStepName == K$(baseNameCapital)TestStep)
      	{
      	$(baseNameCapital)Step = new C$(baseNameCapital)TestStep();
      	}

	return $(baseNameCapital)Step;
	}
