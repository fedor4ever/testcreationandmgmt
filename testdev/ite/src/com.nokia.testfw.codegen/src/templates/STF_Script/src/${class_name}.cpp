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

// INCLUDE FILES
#include <StifTestInterface.h>
#include "${class_name}.h"
#include <SettingServerClient.h>

// ============================ MEMBER FUNCTIONS ===============================

// -----------------------------------------------------------------------------
// C${class_name}::C${class_name}
// C++ default constructor can NOT contain any code, that
// might leave.
// -----------------------------------------------------------------------------
//
C${class_name}::C${class_name}(CTestModuleIf& aTestModuleIf) :
    CScriptBase(aTestModuleIf)
    {
    }

// -----------------------------------------------------------------------------
// C${class_name}::ConstructL
// Symbian 2nd phase constructor can leave.
// -----------------------------------------------------------------------------
//
void C${class_name}::ConstructL()
    {
    //Read logger settings to check whether test case name is to be
    //appended to log file name.
    RSettingServer settingServer;
    TInt ret = settingServer.Connect();
    if (ret != KErrNone)
        {
        User::Leave(ret);
        }
    // Struct to StifLogger settigs.
    TLoggerSettings loggerSettings;
    // Parse StifLogger defaults from STIF initialization file.
    ret = settingServer.GetLoggerSettings(loggerSettings);
    if (ret != KErrNone)
        {
        User::Leave(ret);
        }
    // Close Setting server session
    settingServer.Close();

    TFileName logFileName;

    if (loggerSettings.iAddTestCaseTitle)
        {
        TName title;
        TestModuleIf().GetTestCaseTitleL(title);
        logFileName.Format(K${class_name}LogFileWithTitle, &title);
        }
    else
        {
        logFileName.Copy(K${class_name}LogFile);
        }

    iLog = CStifLogger::NewL(K${class_name}LogPath, logFileName, CStifLogger::ETxt,
            CStifLogger::EFile, EFalse);

    SendTestClassVersion();
    }

// -----------------------------------------------------------------------------
// C${class_name}::NewL
// Two-phased constructor.
// -----------------------------------------------------------------------------
//
C${class_name}* C${class_name}::NewL(CTestModuleIf& aTestModuleIf)
    {
    C${class_name}* self = new (ELeave) C${class_name}(aTestModuleIf);

    CleanupStack::PushL(self);
    self->ConstructL();
    CleanupStack::Pop();

    return self;

    }

// Destructor
C${class_name}::~C${class_name}()
    {

    // Delete resources allocated from test methods
    Delete();

    // Delete logger
    delete iLog;

    }

//-----------------------------------------------------------------------------
// C${class_name}::SendTestClassVersion
// Method used to send version of test class
//-----------------------------------------------------------------------------
//
void C${class_name}::SendTestClassVersion()
    {
    TVersion moduleVersion;
    moduleVersion.iMajor = TEST_CLASS_VERSION_MAJOR;
    moduleVersion.iMinor = TEST_CLASS_VERSION_MINOR;
    moduleVersion.iBuild = TEST_CLASS_VERSION_BUILD;

    TFileName moduleName;
    moduleName = _L("${class_name}.dll");

    TBool newVersionOfMethod = ETrue;
    TestModuleIf().SendTestModuleVersion(moduleVersion, moduleName,
            newVersionOfMethod);
    }

// ========================== OTHER EXPORTED FUNCTIONS =========================

// -----------------------------------------------------------------------------
// LibEntryL is a polymorphic Dll entry point.
// Returns: CScriptBase: New CScriptBase derived object
// -----------------------------------------------------------------------------
//
EXPORT_C CScriptBase* LibEntryL(CTestModuleIf& aTestModuleIf) // Backpointer to STIF Test Framework
    {

    return (CScriptBase*) C${class_name}::NewL(aTestModuleIf);

    }
