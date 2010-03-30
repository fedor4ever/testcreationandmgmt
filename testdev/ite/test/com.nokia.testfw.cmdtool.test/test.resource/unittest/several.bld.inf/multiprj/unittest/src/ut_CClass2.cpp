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

#include "ut_CClass2.h"
#include "class2.h"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_CClass2* UT_CClass2::NewL()
    {
    UT_CClass2* self = UT_CClass2::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_CClass2* UT_CClass2::NewLC()
    {
    UT_CClass2* self = new( ELeave )UT_CClass2();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_CClass2::UT_CClass2()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_CClass2::ConstructL()
    {
    BASE_CONSTRUCT
        ADD_SUT( UT_Constructor_CClass2 )
        ADD_SUT( UT_Destructor_CClass2 )
        ADD_SUT( UT_PubFoo1 )
        ADD_SUT( UT_PubFoo2 )
        ADD_SUT( UT_staFoo4 )
    
    // Setup and teardown functions can be changed for each test function
    // Usually this is not needed, but this is possible.
    // ADD_SUT_WITH_SETUP_AND_TEARDOWN( SetupL, UT_YouFunc, Teardown )
    }

// ---------------------------------------------------------------------------
// Destructor
// ---------------------------------------------------------------------------
//
UT_CClass2::~UT_CClass2()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_CClass2::SetupL()
    {
    //iCClass2 = CClass2::NewL(); ??
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_CClass2::Teardown()
    {
    delete iCClass2;
    iCClass2 = NULL;
    }


// -----------------------------------------------------------------------------
// test CClass2::CClass2 ()
// -----------------------------------------------------------------------------
//
void UT_CClass2::UT_Constructor_CClass2()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CClass2::~CClass2 ()
// -----------------------------------------------------------------------------
//
void UT_CClass2::UT_Destructor_CClass2()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CClass2::PubFoo1 ()
// -----------------------------------------------------------------------------
//
void UT_CClass2::UT_PubFoo1()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CClass2::PubFoo2 ()
// -----------------------------------------------------------------------------
//
void UT_CClass2::UT_PubFoo2()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CClass2::staFoo4 ()
// -----------------------------------------------------------------------------
//
void UT_CClass2::UT_staFoo4()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

