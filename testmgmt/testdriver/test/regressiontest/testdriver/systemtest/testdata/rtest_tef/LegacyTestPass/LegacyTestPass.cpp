/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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



#include <e32test.h>
//#include <e32Cons.h>
#include <f32file.h>


_LIT(KTestString1,"This is a short line of Text");
_LIT(KTestString2,"This is a slightly longer line of Text");

LOCAL_D RTest test(_L("LegacyTestPass"));
_LIT(KLogFilePath,"c:\\testdriver\\logs\\");

//RunTest1
//Simple use of RTest to test logging
void RunTest1()
	{
	TBuf16<28> lStr1(KTestString1);
	TBuf16<40> lStr2(KTestString2);
	
	test.Title();
	test.Start(_L("TestDriverLoggering Test"));

	test.Printf(lStr1);
	test.Next(lStr2);
	test.End();
	test.Close();
	}
	
//creates results directory in case this rtest is run without initlogger
void CreateResultsDirL()
	{
	RFs fsSession; // session to write to
	User::LeaveIfError(fsSession.Connect()); // connect session
	fsSession.MkDirAll(KLogFilePath);
	fsSession.Close();
	}
	
	
LOCAL_C void MainL()
	{
	_LIT(KTitle,"TestExecute Standalone Logger Test Code");
	CConsoleBase* console = Console::NewL(KTitle,TSize(KConsFullScreen,KConsFullScreen));
	CleanupStack::PushL(console);
	console->Printf(_L("Running test1\n"));
	//CreateResultsDirL();
	RunTest1();
	CleanupStack::PopAndDestroy(); // close console
	}

// Entry point for all Epoc32 executables
// See PSP Chapter 2 Getting Started
GLDEF_C TInt E32Main()
	{
	// Heap balance checking
	// See PSP Chapter 6 Error Handling
	CTrapCleanup* cleanup = CTrapCleanup::New();
	if(cleanup == NULL)
		{
		return KErrNoMemory;
		}
	TRAPD(err,MainL());
	_LIT(KPanic,"A.B");
	__ASSERT_ALWAYS(!err, User::Panic(KPanic,err));
	delete cleanup;
	return KErrNone;
    }
