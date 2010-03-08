/**
 * @file $(baseName)SuiteStepBase.cpp
 * @internalTechnology
 *
 * $(copyright)
 */

#include "$(baseName)SuiteStepBase.h"
#include "$(baseName)SuiteDefs.h"

// Device driver constants

TVerdict C$(baseName)SuiteStepBase::doTestStepPreambleL()
/**
 * @return - TVerdict
 * Implementation of CTestStep base class virtual
 * It is used for doing all initialisation common to derived classes in here.
 * Make it being able to leave if there are any errors here as there's no point in
 * trying to run a test step if anything fails.
 * The leave will be picked up by the framework.
 */
	{

	// process some common pre setting to test steps then set SetTestStepResult to EFail or Epass.
	INFO_PRINTF1(_L("Please delete this line or modify me!! I am in doTestStepPreambleL() of the class C$(baseName)SuiteStepBase!"));
	SetTestStepResult(EPass);
	return TestStepResult();
	}

TVerdict C$(baseName)SuiteStepBase::doTestStepPostambleL()
/**
 * @return - TVerdict
 * Implementation of CTestStep base class virtual
 * It is used for doing all after test treatment common to derived classes in here.
 * Make it being able to leave
 * The leave will be picked up by the framework.
 */
	{

	// process some common post setting to test steps then set SetTestStepResult to EFail or Epass.
	INFO_PRINTF1(_L("Please delete this line or modify me!! I am in doTestStepPostambleL() of the class C$(baseName)SuiteStepBase!"));
	//SetTestStepResult(EPass);  // or EFail
	return TestStepResult();
	}

C$(baseName)SuiteStepBase::~C$(baseName)SuiteStepBase()
	{
	}

C$(baseName)SuiteStepBase::C$(baseName)SuiteStepBase()
	{
	}
