/**
 * @file $(baseName)stepbase.h
 * @internalTechnology
 *
 * $(copyright)
 *
 */

#if (!defined __$(baseNameUpper)_STEP_BASE__)
#define __$(baseNameUpper)_STEP_BASE__
#include <testexecutestepbase.h>
// Please add your include here if you have 

/****************************************************************************
* The reason to have a new step base is that it is very much possible
* that the all individual test steps have project related common variables 
* and members 
* and this is the place to define these common variable and members.
* 
****************************************************************************/
class C$(baseNameCapital)StepBase : public CTestStep
	{
public:
	virtual ~C$(baseNameCapital)StepBase();
	C$(baseNameCapital)StepBase();
	virtual TVerdict doTestStepPreambleL(); 
	virtual TVerdict doTestStepPostambleL();

//TODO:Add your class members which will be common to all individual test steps:
protected:
	HBufC8*		iReadData;
	HBufC8*		iWriteData;
	};

#endif
