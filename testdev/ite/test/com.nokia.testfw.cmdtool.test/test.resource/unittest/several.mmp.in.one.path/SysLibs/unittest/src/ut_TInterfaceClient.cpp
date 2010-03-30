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

#include "ut_TInterfaceClient.h"
#include "InterfaceClient.h"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_TInterfaceClient* UT_TInterfaceClient::NewL()
    {
    UT_TInterfaceClient* self = UT_TInterfaceClient::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_TInterfaceClient* UT_TInterfaceClient::NewLC()
    {
    UT_TInterfaceClient* self = new( ELeave )UT_TInterfaceClient();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_TInterfaceClient::UT_TInterfaceClient()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_TInterfaceClient::ConstructL()
    {
    BASE_CONSTRUCT
        ADD_SUT( UT_GetByDiscoveryL )
        ADD_SUT( UT_GetBySpecificationL )
        ADD_SUT( UT_GetDefaultL )
    
    // Setup and teardown functions can be changed for each test function
    // Usually this is not needed, but this is possible.
    // ADD_SUT_WITH_SETUP_AND_TEARDOWN( SetupL, UT_YouFunc, Teardown )
    }

// ---------------------------------------------------------------------------
// Destructor
// ---------------------------------------------------------------------------
//
UT_TInterfaceClient::~UT_TInterfaceClient()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_TInterfaceClient::SetupL()
    {
    //iTInterfaceClient = TInterfaceClient::NewL(); ??
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_TInterfaceClient::Teardown()
    {
    delete iTInterfaceClient;
    iTInterfaceClient = NULL;
    }


// -----------------------------------------------------------------------------
// test TInterfaceClient::GetByDiscoveryL ()
// -----------------------------------------------------------------------------
//
void UT_TInterfaceClient::UT_GetByDiscoveryL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test TInterfaceClient::GetBySpecificationL ()
// -----------------------------------------------------------------------------
//
void UT_TInterfaceClient::UT_GetBySpecificationL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

// -----------------------------------------------------------------------------
// test TInterfaceClient::GetDefaultL ()
// -----------------------------------------------------------------------------
//
void UT_TInterfaceClient::UT_GetDefaultL()
    {
	SUT_ASSERT_LEAVE_WITH(_L("test case not implemented"), KErrNotReady);
    }

