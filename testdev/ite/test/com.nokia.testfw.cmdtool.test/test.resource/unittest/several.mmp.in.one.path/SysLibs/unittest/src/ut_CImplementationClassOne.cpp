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

#include "ut_CImplementationClassOne.h"
#include "CImplementationClassOne.h"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_CImplementationClassOne* UT_CImplementationClassOne::NewL()
    {
    UT_CImplementationClassOne* self = UT_CImplementationClassOne::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_CImplementationClassOne* UT_CImplementationClassOne::NewLC()
    {
    UT_CImplementationClassOne* self = new( ELeave )UT_CImplementationClassOne();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_CImplementationClassOne::UT_CImplementationClassOne()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_CImplementationClassOne::ConstructL()
    {
    BASE_CONSTRUCT
        ADD_SUT( UT_Destructor_CImplementationClassOne )
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
UT_CImplementationClassOne::~UT_CImplementationClassOne()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassOne::SetupL()
    {
    //iCImplementationClassOne = CImplementationClassOne::NewL(); ??
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassOne::Teardown()
    {
    delete iCImplementationClassOne;
    iCImplementationClassOne = NULL;
    }


// -----------------------------------------------------------------------------
// test CImplementationClassOne::~CImplementationClassOne ()
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassOne::UT_Destructor_CImplementationClassOne()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CImplementationClassOne::DoMethodL ()
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassOne::UT_DoMethodL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CImplementationClassOne::NewL ()
// -----------------------------------------------------------------------------
//
void UT_CImplementationClassOne::UT_NewL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

