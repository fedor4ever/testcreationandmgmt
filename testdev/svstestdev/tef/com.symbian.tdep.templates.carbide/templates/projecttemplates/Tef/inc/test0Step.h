/**
 * @file test0Step.h
 * @internalTechnology
 *
 * $(copyright)
 */
#if (!defined __TEST0_STEP_H__)
#define __TEST0_STEP_H__
#include <TestExecuteStepBase.h>
#include "$(baseName)SuiteStepBase.h"

class Ctest0Step : public C$(baseName)SuiteStepBase
	{
public:
	Ctest0Step();
	~Ctest0Step();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepL();
	virtual TVerdict doTestStepPostambleL();

// Please add/modify your class members here:
private:
	};

_LIT(Ktest0Step,"test0Step");

#endif
