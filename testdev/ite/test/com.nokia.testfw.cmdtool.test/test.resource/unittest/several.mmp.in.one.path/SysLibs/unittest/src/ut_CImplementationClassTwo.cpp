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

#include "ut_CImplementationClassTwo.h"
#include "CImplementationClassTwo.h"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_CImplementationClassTwo* UT_CImplementationClassTwo::NewL()
    {
    UT_CImplementationClassTwo* self = UT_CImplementationClassTwo::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_CImplementationClassTwo* UT_CImplementationClassTwo::NewLC()
    {
    UT_CImplementationClassTwo* self = new( ELeave )UT_CImplementationClassTwo();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_CImplementationClassTwo::UT_CImplementationClassTwo()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_CImplementationClassTwo::ConstructL()
    {
    BASE_CONSTRUCT
        ADD_SUT( UT_Destructor_CImplementationClassTwo )
        ADD_SUT( UT_DoMethodL )
        ADD_SUT( UT_NewL )
    
    // Setup and teardown functions can be changed for each test function
    // Usually this is not needed, but this is possible.
    // ADD_SUT_WITH_SETUP_AND_TEARDOWN( SetupL, UT_YouFunc, Teardown )
    }

// ---------------------------------------------------------------------------
// Destructor
// ---------------------------------------------------------------------------
//
UT_CImplementationClassTwo::~UT_CImplementationClassTwo()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassTwo::SetupL()
    {
    //iCImplementationClassTwo = CImplementationClassTwo::NewL(); ??
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassTwo::Teardown()
    {
    delete iCImplementationClassTwo;
    iCImplementationClassTwo = NULL;
    }


// -----------------------------------------------------------------------------
// test CImplementationClassTwo::~CImplementationClassTwo ()
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassTwo::UT_Destructor_CImplementationClassTwo()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CImplementationClassTwo::DoMethodL ()
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassTwo::UT_DoMethodL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CImplementationClassTwo::NewL ()
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassTwo::UT_NewL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

