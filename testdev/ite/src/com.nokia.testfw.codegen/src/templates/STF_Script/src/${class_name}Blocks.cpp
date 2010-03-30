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
 * Description: This file contains testclass implementation.
 *
 */

// [Include Files] Begin - Do not remove
#include <e32svr.h>
#include <StifParser.h>
#include <StifTestInterface.h>

//!!//!![Repeat Section Begin]::// [Include Files]
#include "${class_name}.h"
//!!foreach( ${header_file} in ${class_object.IncludeHeaders})
#include <${header_file}>
//!!end
//!!//!![Repeat Section End]::// [Include Files]
// [Include Files] End - Do not remove

// ============================ MEMBER FUNCTIONS ===============================

// -----------------------------------------------------------------------------
// C${class_name}::Delete
// Delete here all resources allocated and opened from test methods.
// Called from destructor.
// -----------------------------------------------------------------------------
//
void C${class_name}::Delete()
    {

    }

// -----------------------------------------------------------------------------
// C${class_name}::RunMethodL
// Run specified method. Contains also table of test mothods and their names.
// -----------------------------------------------------------------------------
//
TInt C${class_name}::RunMethodL(CStifItemParser& aItem)
    {

    static TStifFunctionInfo const KFunctions[] =
        {
        // Copy this line for every implemented function.
            // First string is the function name used in TestScripter script file.
            // Second is the actual implementation member function.

            // [Test Case Entries] Begin - Do not remove
//!!//!![Repeat Section Begin]::// [Test Case Entries]
//!!foreach( ${method_object} in ${class_object.Children})
            ENTRY( "${class_object.Name}_${method_object.NormalisedName}${RepeatCounter}", C${class_object.Name}::${method_object.NormalisedName}${RepeatCounter} ),
//!!end
//!!//!![Repeat Section End]::// [Test Case Entries]
            // [Test Case Entries] End - Do not remove
        };

    const TInt count = sizeof(KFunctions) / sizeof(TStifFunctionInfo);

    return RunInternalL(KFunctions, count, aItem);

    }

// [Test Case Implement] Begin - Do not remove
//!!//!![Repeat Section Begin]::// [Test Case Implement]
//!!foreach( ${method_object} in ${class_object.Children})
TInt C${class_name}::${method_object.NormalisedName}${RepeatCounter}(CStifItemParser& aItem)
    {
    STIF_ASSERT_LEAVES(_L("This test case is unimplemented."));
    return KErrNone;
    }

//!!end
//!!//!![Repeat Section End]::// [Test Case Implement]
// [Test Case Implement] End - Do not remove
