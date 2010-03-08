/**
 * @file $(baseName)SuiteStepBase.h
 * @internalTechnology
 *
 * $(copyright)
 * 
 */

#if (!defined __$(baseNameUpper)_STEP_BASE__)
#define __$(baseNameUpper)_STEP_BASE__
#include <TestExecuteStepBase.h>
// Please add your include here if you have 

/****************************************************************************
* The reason to have a new step base is that it is very much possible
* that the all individual test steps have project related common variables 
* and members 
* and this is the place to define these common variable and members.
* 
****************************************************************************/
class C$(baseName)SuiteStepBase : public CTestStep
	{
public:
	virtual ~C$(baseName)SuiteStepBase();
	C$(baseName)SuiteStepBase();
	virtual TVerdict doTestStepPreambleL(); 
	virtual TVerdict doTestStepPostambleL();

//Please add your class members which will be common to all individual test steps:
protected:
	HBufC8*		iReadData;
	HBufC8*		iWriteData;
	};

#endif
