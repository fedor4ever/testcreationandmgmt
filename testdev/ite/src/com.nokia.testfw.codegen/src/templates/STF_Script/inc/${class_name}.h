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
 * Description: STF testclass declaration
 *
 */
#ifndef ${class_object.NameUpperCase}_H
#define ${class_object.NameUpperCase}_H
//  INCLUDES
#include <StifLogger.h>
#include <TestScripterInternal.h>
#include <StifTestModule.h>
#include <TestclassAssert.h>
// MACROS
#define TEST_CLASS_VERSION_MAJOR 0
#define TEST_CLASS_VERSION_MINOR 0
#define TEST_CLASS_VERSION_BUILD 0
// Logging path
_LIT( K${class_name}LogPath, "\\logs\\testframework\\\\${class_name}\\" );
// Log file
_LIT( K${class_name}LogFile, "${class_name}.txt" );
_LIT( K${class_name}LogFileWithTitle, "${class_name}_[%S].txt" );

// FORWARD DECLARATIONS
class C${class_name};

// CLASS DECLARATION

/**
 *  C${class_name} test class for STF Test Framework TestScripter.
 */
NONSHARABLE_CLASS(C${class_name}) : public CScriptBase
    {
public:
    // Constructors and destructor

    /**
     * Two-phased constructor.
     */
    static C${class_name}* NewL(CTestModuleIf& aTestModuleIf);

    /**
     * Destructor.
     */
    virtual ~C${class_name}();

public:
    // Functions from base classes

    /**
     * From CScriptBase Runs a script line.
     * @param aItem Script line containing method name and parameters
     * @return Symbian OS error code
     */
    virtual TInt RunMethodL(CStifItemParser& aItem);

private:

    /**
     * C++ default constructor.
     */
    C${class_name}(CTestModuleIf& aTestModuleIf);

    /**
     * By default Symbian 2nd phase constructor is private.
     */
    void ConstructL();

    /**
     * Frees all resources allocated from test methods.
     */
    void Delete();

    /**
     * Method used to log version of test class
     */
    void SendTestClassVersion();

    /**
     * Test methods are listed below.
     */

    // [Test Case Declarations] Begin - Do not remove
//!!//!![Repeat Section Begin]::// [Test Case Declarations]
//!!foreach( ${method_object} in ${class_object.Children})
    TInt ${method_object.NormalisedName}${RepeatCounter}(CStifItemParser& aItem);
//!!end
//!!//!![Repeat Section End]::// [Test Case Declarations]
    // [Test Case Declarations] End - Do not remove
    };

#endif      // ${class_object.NameUpperCase}_H
