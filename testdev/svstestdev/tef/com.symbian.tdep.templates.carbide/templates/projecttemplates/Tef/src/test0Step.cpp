/**
 * @file test0Step.cpp
 * @internalTechnology
 *
 * $(copyright)
 * Example CTestStep derived implementation
 */
#include "test0Step.h"
#include "$(baseName)SuiteDefs.h"

Ctest0Step::~Ctest0Step()
/**
 * Destructor
 */
	{
	}

Ctest0Step::Ctest0Step()
/**
 * Constructor
 */
	{
	// **MUST** call SetTestStepName in the constructor as the controlling
	// framework uses the test step name immediately following construction to set
	// up the step's unique logging ID.
	SetTestStepName(Ktest0Step);
	}

TVerdict Ctest0Step::doTestStepPreambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	INFO_PRINTF1(_L("Please delete this line or modify it. I am in Test Step Preamble in Class Ctest0Step"));
	// uncomment the following 3 lines if you have common pre setting to all the test steps in there
	// C$(baseName)SuiteStepBase::doTestStepPreambleL();
	// if (TestStepResult()!=EPass)
	//    return   TestStepResult();
	// process some pre setting to this test step then set SetTestStepResult to EFail or Epass.
	SetTestStepResult(EPass);
	return TestStepResult();
	}


TVerdict Ctest0Step::doTestStepL()
/**
 * @return - TVerdict code
 * Override of base class pure virtual
 * Our implementation only gets called if the base class doTestStepPreambleL() did
 * not leave. That being the case, the current test result value will be EPass.
 */
	{
	  if (TestStepResult()==EPass)
		{

		//  ************** Delete the Block, the block start ****************
		INFO_PRINTF1(_L("Please modify me. I am in Ctest0Step::doTestStepL() in the file test0Step.cpp"));  //Block start
		TPtrC TheString;
		TBool TheBool;
		TInt TheInt;
		if(!GetStringFromConfig(ConfigSection(),K$(baseNameUpper)String, TheString) ||
			!GetBoolFromConfig(ConfigSection(),K$(baseNameUpper)Bool,TheBool) ||
			!GetIntFromConfig(ConfigSection(),K$(baseNameUpper)Int,TheInt)
			)
			{
			// Leave if there's any error.
			User::Leave(KErrNotFound);
			}
		else
			{
			INFO_PRINTF4(_L("The test step is %S, The Bool is %d, The int-value is %d"), &TheString, TheBool,TheInt); // Block end
			}

		//  **************   Block end ****************

		SetTestStepResult(EPass);
		}
	  return TestStepResult();
	}



TVerdict Ctest0Step::doTestStepPostambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	INFO_PRINTF1(_L("Please delete this line or modify it. I am in Test Step Postamble in Class Ctest0Step"));
	// process something post setting to the test step
	// uncomment the following line if you have common post setting to all the test steps in there
	// C$(baseName)SuiteStepBase::doTestStepPostambleL();
	// uncomment the following line if you have post process or remove the following line if no post process
	// SetTestStepResult(EPass);		// or EFail
	return TestStepResult();
	}
