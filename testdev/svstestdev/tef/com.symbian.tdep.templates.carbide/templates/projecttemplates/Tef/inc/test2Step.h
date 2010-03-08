/**
 * @file test2Step.h
 * @internalTechnology
 *
 * $(copyright)
 */
#if (!defined __TEST2_STEP_H__)
#define __TEST2_STEP_H__
#include <TestExecuteStepBase.h>
#include "$(baseName)SuiteStepBase.h"

class Ctest2Step : public C$(baseName)SuiteStepBase
	{
public:
	Ctest2Step();
	~Ctest2Step();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepL();
	virtual TVerdict doTestStepPostambleL();

// Please add/modify your class members here:
private:
	};

_LIT(Ktest2Step,"test2Step");

#endif
