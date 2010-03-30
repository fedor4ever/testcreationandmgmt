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

#include "ut_CTest1.h"
#include "test1.h"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_CTest1* UT_CTest1::NewL()
    {
    UT_CTest1* self = UT_CTest1::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_CTest1* UT_CTest1::NewLC()
    {
    UT_CTest1* self = new( ELeave )UT_CTest1();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_CTest1::UT_CTest1()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_CTest1::ConstructL()
    {
    BASE_CONSTRUCT
        ADD_SUT( UT_Constructor_CTest1 )
        ADD_SUT( UT_Destructor_CTest1 )
        ADD_SUT( UT_Start )
        ADD_SUT( UT_threadFunc )
    
    // Setup and teardown functions can be changed for each test function
    // Usually this is not needed, but this is possible.
    // ADD_SUT_WITH_SETUP_AND_TEARDOWN( SetupL, UT_YouFunc, Teardown )
    }

// ---------------------------------------------------------------------------
// Destructor
// ---------------------------------------------------------------------------
//
UT_CTest1::~UT_CTest1()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_CTest1::SetupL()
    {
    //iCTest1 = CTest1::NewL(); ??
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_CTest1::Teardown()
    {
    delete iCTest1;
    iCTest1 = NULL;
    }


// -----------------------------------------------------------------------------
// test CTest1::CTest1 ()
// -----------------------------------------------------------------------------
//
void UT_CTest1::UT_Constructor_CTest1()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CTest1::~CTest1 ()
// -----------------------------------------------------------------------------
//
void UT_CTest1::UT_Destructor_CTest1()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CTest1::Start ()
// -----------------------------------------------------------------------------
//
void UT_CTest1::UT_Start()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CTest1::threadFunc ()
// -----------------------------------------------------------------------------
//
void UT_CTest1::UT_threadFunc()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

