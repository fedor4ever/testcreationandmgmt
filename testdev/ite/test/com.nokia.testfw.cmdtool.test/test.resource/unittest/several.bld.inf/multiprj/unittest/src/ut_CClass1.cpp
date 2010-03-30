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

#include "ut_CClass1.h"
#include "class1.h"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_CClass1* UT_CClass1::NewL()
    {
    UT_CClass1* self = UT_CClass1::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_CClass1* UT_CClass1::NewLC()
    {
    UT_CClass1* self = new( ELeave )UT_CClass1();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_CClass1::UT_CClass1()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_CClass1::ConstructL()
    {
    BASE_CONSTRUCT
        ADD_SUT( UT_Constructor_CClass1 )
        ADD_SUT( UT_Destructor_CClass1 )
        ADD_SUT( UT_PubFoo1 )
    
    // Setup and teardown functions can be changed for each test function
    // Usually this is not needed, but this is possible.
    // ADD_SUT_WITH_SETUP_AND_TEARDOWN( SetupL, UT_YouFunc, Teardown )
    }

// ---------------------------------------------------------------------------
// Destructor
// ---------------------------------------------------------------------------
//
UT_CClass1::~UT_CClass1()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_CClass1::SetupL()
    {
    //iCClass1 = CClass1::NewL(); ??
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_CClass1::Teardown()
    {
    delete iCClass1;
    iCClass1 = NULL;
    }


// -----------------------------------------------------------------------------
// test CClass1::CClass1 ()
// -----------------------------------------------------------------------------
//
void UT_CClass1::UT_Constructor_CClass1()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CClass1::~CClass1 ()
// -----------------------------------------------------------------------------
//
void UT_CClass1::UT_Destructor_CClass1()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CClass1::PubFoo1 ()
// -----------------------------------------------------------------------------
//
void UT_CClass1::UT_PubFoo1()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

