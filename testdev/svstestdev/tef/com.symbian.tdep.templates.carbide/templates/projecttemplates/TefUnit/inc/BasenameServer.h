/**
 * @file $(baseNameLower)server.h
 * @internalTechnology
 *
 * $(copyright)
 *
 */

#if (!defined __$(baseNameUpper)_SERVER_H__)
#define __$(baseNameUpper)_SERVER_H__
#include <testexecuteserverbase.h>
#include "$(baseNameLower)teststep.h"
$(serverIncludes)

class C$(baseNameCapital)Server : public CTestServer
	{
public:
	static C$(baseNameCapital)Server* NewL();
	// Base class pure virtual override
	virtual CTestStep* CreateTestStep(const TDesC& aStepName);

//TODO:Add/modify your class members
private:
	};

#endif
