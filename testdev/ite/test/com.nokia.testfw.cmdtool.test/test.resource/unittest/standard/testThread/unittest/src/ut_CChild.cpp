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
#include "child.h"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_CChild* UT_CChild::NewL()
    {
    UT_CChild* self = UT_CChild::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_CChild* UT_CChild::NewLC()
    {
    UT_CChild* self = new( ELeave )UT_CChild();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_CChild::UT_CChild()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_CChild::ConstructL()
    {
    BASE_CONSTRUCT
        ADD_SUT( UT_Add )
        ADD_SUT( UT_Constructor_CChild )
        ADD_SUT( UT_Del )
        ADD_SUT( UT_Destructor_CChild )
    
    // Setup and teardown functions can be changed for each test function
    // Usually this is not needed, but this is possible.
    // ADD_SUT_WITH_SETUP_AND_TEARDOWN( SetupL, UT_YouFunc, Teardown )
    }

// ---------------------------------------------------------------------------
// Destructor
// ---------------------------------------------------------------------------
//
UT_CChild::~UT_CChild()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_CChild::SetupL()
    {
    //iCChild = CChild::NewL(); ??
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_CChild::Teardown()
    {
    delete iCChild;
    iCChild = NULL;
    }


// -----------------------------------------------------------------------------
// test CChild::Add ()
// -----------------------------------------------------------------------------
//
void UT_CChild::UT_Add()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CChild::CChild ()
// -----------------------------------------------------------------------------
//
void UT_CChild::UT_Constructor_CChild()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CChild::Del ()
// -----------------------------------------------------------------------------
//
void UT_CChild::UT_Del()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CChild::~CChild ()
// -----------------------------------------------------------------------------
//
void UT_CChild::UT_Destructor_CChild()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

