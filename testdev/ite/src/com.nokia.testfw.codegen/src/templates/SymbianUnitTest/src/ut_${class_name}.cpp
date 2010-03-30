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

#include "ut_${class_name}.h"
#include "${class_object.HeaderFileName}"
#include <symbianunittestmacros.h>

// ---------------------------------------------------------------------------
// NewL
// ---------------------------------------------------------------------------
//
UT_${class_name}* UT_${class_name}::NewL()
    {
    UT_${class_name}* self = UT_${class_name}::NewLC();
    CleanupStack::Pop( self );
    return self;
    }

// ---------------------------------------------------------------------------
// NewLC
// ---------------------------------------------------------------------------
//
UT_${class_name}* UT_${class_name}::NewLC()
    {
    UT_${class_name}* self = new( ELeave )UT_${class_name}();
    CleanupStack::PushL( self );
    self->ConstructL();
    return self;
    }

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
//
UT_${class_name}::UT_${class_name}()
    {
    }

// ---------------------------------------------------------------------------
// ConstructL
// ---------------------------------------------------------------------------
//
void UT_${class_name}::ConstructL()
    {
    BASE_CONSTRUCT
    DEFINE_TEST_CLASS(UT_${class_name})
    // [Test Case Entries] Begin - Do not remove
//!!//!![Repeat Section Begin]::// [Test Case Entries]
//!!foreach( $method_object in $class_object.Children)
    ADD_SUT( UT_${method_object.NormalisedName}${RepeatCounter} )
//!!end
//!!//!![Repeat Section End]::// [Test Case Entries]
    // [Test Case Entries] End - Do not remove

    // Setup and teardown functions can be changed for each test function
    // Usually this is not needed, but this is possible.
    // ADD_SUT_WITH_SETUP_AND_TEARDOWN( SetupL, UT_YouFunc, Teardown )
    }

// ---------------------------------------------------------------------------
// Destructor
// ---------------------------------------------------------------------------
//
UT_${class_name}::~UT_${class_name}()
    {
    }

// -----------------------------------------------------------------------------
// SetupL
// -----------------------------------------------------------------------------
//
void UT_${class_name}::SetupL()
    {
    //i${class_name} = ${class_name}::NewL();
    }

// -----------------------------------------------------------------------------
// Teardown
// -----------------------------------------------------------------------------
//
void UT_${class_name}::Teardown()
    {
    //delete i${class_name};
    //i${class_name} = NULL;
    }

// [Test Case Implementations] Begin - Do not remove
//!!//!![Repeat Section Begin]::// [Test Case Implementations]
//!!foreach( $method_object in $class_object.Children)
// -----------------------------------------------------------------------------
// test ${class_name}::${method_object.FullName}
// -----------------------------------------------------------------------------
//
void UT_${class_name}::UT_${method_object.NormalisedName}${RepeatCounter}()
    {
	//i${class_name}->${method_object.FullName}
	SUT_ASSERT_LEAVE_WITH(_L("This test case is unimplemented."), KErrNotReady);
    }

//!!end
//!!//!![Repeat Section End]::// [Test Case Implementations]
// [Test Case Implementations] End - Do not remove
