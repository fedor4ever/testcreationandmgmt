/**
 * @file $(baseName)teststep.h
 * @internalTechnology
 *
 * $(copyright)
 *
 */
#if (!defined __$(baseNameUpper)_TEST_STEP_H__)
#define __$(baseNameUpper)_TEST_STEP_H__
#include <testexecutestepbase.h>
#include "$(baseName)stepbase.h"

class C$(baseNameCapital)TestStep : public C$(baseNameCapital)StepBase
	{
public:
	C$(baseNameCapital)TestStep();
	~C$(baseNameCapital)TestStep();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepL();
	virtual TVerdict doTestStepPostambleL();

//TODO:add/modify your class members here:
private:
	};

_LIT(K$(baseNameCapital)TestStep,"$(baseNameCapital)TestStep");

#endif
