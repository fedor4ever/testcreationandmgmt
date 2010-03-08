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



#include "StaticTestDLL.h"
#include <e32uid.h>

// construct/destruct

EXPORT_C CMessengerTest* CMessengerTest::NewLC(CConsoleBase& aConsole, const TDesC& aString)
	{
	CMessengerTest* self=new (ELeave) CMessengerTest(aConsole);
	CleanupStack::PushL(self);
	self->ConstructL(aString);
	return self;
	}

// destruct - virtual, so no export
CMessengerTest::~CMessengerTest() 
	{
	delete iString;
	}

// useful functions

EXPORT_C void CMessengerTest::ShowMessage()
	{
	_LIT(KFormat1,"%S\n");
	iConsole.Printf(KFormat1, iString); // notify completion
	}

// constructor support
// don't export these, because used only by functions in this DLL, eg our NewLC()

CMessengerTest::CMessengerTest(CConsoleBase& aConsole) // first-phase C++ constructor
	: iConsole(aConsole)
	{
	}

void CMessengerTest::ConstructL(const TDesC& aString) // second-phase constructor
	{
	iString=aString.AllocL(); // copy given string into own descriptor
    }


#ifndef EKA2
GLDEF_C TInt E32Dll(TDllReason /*aReason*/)
// DLL entry point
	{
	return(KErrNone);
	}
#endif
