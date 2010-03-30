/*
 * Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies).
 * All rights reserved.
 * This component and the accompanying materials are made available
 * under the terms of the License "Symbian Foundation License v1.0"
 * which accompanies this distribution, and is available
 * at the URL "http://www.symbianfoundation.org/legal/epl-v10.html".
 *
 * Initial Contributors:
 * Nokia Corporation - initial contribution.
 *
 * Contributors:
 *
 * Description:
 *
 */

#include "ut_CChild.h"
#include "ut_CTest1.h"
#include <symbianunittestsuite.h>

// Exactly one exported function returning
// the pointer to the suite of tests for the SymbianUnit framework.
//
EXPORT_C MSymbianUnitTestInterface* CreateTestL()
{
	CSymbianUnitTestSuite* testSuite =
	CSymbianUnitTestSuite::NewLC( _L("ut_testThread") );

    testSuite->AddL( UT_CChild::NewLC() );
    testSuite->AddL( UT_CTest1::NewLC() );
	CleanupStack::Pop();

	CleanupStack::Pop( testSuite );
	return testSuite;
}
