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

#include "ut_CExampleResolver.h"
#include "ExampleResolver.h"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_CExampleResolver* UT_CExampleResolver::NewL()
    {
    UT_CExampleResolver* self = UT_CExampleResolver::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_CExampleResolver* UT_CExampleResolver::NewLC()
    {
    UT_CExampleResolver* self = new( ELeave )UT_CExampleResolver();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_CExampleResolver::UT_CExampleResolver()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_CExampleResolver::ConstructL()
    {
    BASE_CONSTRUCT
        ADD_SUT( UT_Destructor_CExampleResolver )
        ADD_SUT( UT_IdentifyImplementationL )
        ADD_SUT( UT_ListAllL )
        ADD_SUT( UT_NewL )
    
    // Setup and teardown functions can be changed for each test function
    // Usually this is not needed, but this is possible.
    // ADD_SUT_WITH_SETUP_AND_TEARDOWN( SetupL, UT_YouFunc, Teardown )
    }

// ---------------------------------------------------------------------------
// Destructor
// ---------------------------------------------------------------------------
//
UT_CExampleResolver::~UT_CExampleResolver()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_CExampleResolver::SetupL()
    {
    //iCExampleResolver = CExampleResolver::NewL(); ??
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_CExampleResolver::Teardown()
    {
    delete iCExampleResolver;
    iCExampleResolver = NULL;
    }


// -----------------------------------------------------------------------------
// test CExampleResolver::~CExampleResolver ()
// -----------------------------------------------------------------------------
//
void UT_CExampleResolver::UT_Destructor_CExampleResolver()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CExampleResolver::IdentifyImplementationL ()
// -----------------------------------------------------------------------------
//
void UT_CExampleResolver::UT_IdentifyImplementationL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CExampleResolver::ListAllL ()
// -----------------------------------------------------------------------------
//
void UT_CExampleResolver::UT_ListAllL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test CExampleResolver::NewL ()
// -----------------------------------------------------------------------------
//
void UT_CExampleResolver::UT_NewL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

