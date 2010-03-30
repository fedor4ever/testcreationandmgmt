/*
* Copyright (c) 2010 Nokia Corporation and/or its subsidiary(-ies).
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

// [Test Class Headers] Begin - Do not remove
//!!//!![Repeat Section Begin]::// [Test Class Headers]
//!!foreach( $class_object in ${project_object.Children})
#include "ut_${class_object.Name}.h"
//!!end
//!!//!![Repeat Section End]::// [Test Class Headers]
// [Test Class Headers] End - Do not remove
#include <symbianunittestsuite.h>

// Exactly one exported function returning
// the pointer to the suite of tests for the SymbianUnit framework.
//
EXPORT_C MSymbianUnitTestInterface* CreateTestL()
{
	CSymbianUnitTestSuite* testSuite =
	CSymbianUnitTestSuite::NewLC( _L("ut_${project_name}") );

	// [Test Class Entries] Begin - Do not remove
//!!//!![Repeat Section Begin]::// [Test Class Entries]
//!!foreach( $class_object in ${project_object.Children})
    testSuite->AddL( UT_${class_object.Name}::NewLC() );
    CleanupStack::Pop();//UT_${class_object.Name}::NewLC()

//!!end
//!!//!![Repeat Section End]::// [Test Class Entries]
    // [Test Class Entries] End - Do not remove

    CleanupStack::Pop( testSuite );
	return testSuite;
}

