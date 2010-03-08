/**
 * @file test1Step.h
 * @internalTechnology
 *
 * $(copyright) 
 */
#if (!defined __TEST1_STEP_H__)
#define __TEST1_STEP_H__
#include <TestExecuteStepBase.h>
#include "$(baseName)SuiteStepBase.h"

class Ctest1Step : public C$(baseName)SuiteStepBase
	{
public:
	Ctest1Step();
	~Ctest1Step();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepL();
	virtual TVerdict doTestStepPostambleL();

// Please add/modify your class members here:
private:
	};

_LIT(Ktest1Step,"test1Step");

#endif
