/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of "Eclipse Public License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.eclipse.org/legal/epl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description: 
*
*/


#if (!defined __ERROR_STEP_H__)
#define __ERROR_STEP_H__
#include <TestExecuteStepBase.h>
#include "Te_TestServerSuiteStepBase.h"


class CErrorStep : public CTe_TestServerSuiteStepBase
	{
public:
	CErrorStep(CTe_TestServerSuite* aServer);
	~CErrorStep();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepL();
	virtual TVerdict doTestStepPostambleL();

// Please add/modify your class members here:
private:
	CTe_TestServerSuite* iServer;
	};

_LIT(KErrorStep,"ErrorStep");

#endif
