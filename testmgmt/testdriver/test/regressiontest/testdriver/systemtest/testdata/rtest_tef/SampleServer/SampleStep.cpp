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


#include "SampleStep.h"
#include <testexecutelog.h>
#include <e32utrace.h>

CSampleStep1::~CSampleStep1()
/**
 * Destructor
 */
	{
	}

CSampleStep1::CSampleStep1()
/**
 * Constructor
 */
	{
	// Call base class method to set up the human readable name for logging
	SetTestStepName(KSampleStep1);
	}

TVerdict CSampleStep1::doTestStepPreambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EPass);
	return TestStepResult();
	}

TVerdict CSampleStep1::doTestStepL()
/**
 * @return - TVerdict code
 * Override of base class pure virtual
 * Demonstrates reading configuration parameters fom an ini file section
 */
	{
	// Use logging macro
	// Could use Flogger().Write.. directly
	INFO_PRINTF1(_L("Step1: Should pass1."));

	//test code to add entry to ulogger
	TBool includeContextID = ETrue;
        TBool includePc = EFalse;
        TUint32 traceData = 0;
        TPrimaryFilter filter1 = 253;

        TUTrace tracer(filter1, KBinary, includeContextID, includePc);
        tracer.Printf(_L("My trace point."));
        tracer.Trace(traceData);

	SetTestStepResult(EPass);
	return TestStepResult();
	}

TVerdict CSampleStep1::doTestStepPostambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EPass);
	return TestStepResult();
	}

///////

CSampleStep2::~CSampleStep2()
/**
 * Destructor
 */
	{
	}

CSampleStep2::CSampleStep2(CSampleServer& aParent) : iParent(aParent)
/**
 * Constructor
 */
	{
	// Call base class method to set up the human readable name for logging
	SetTestStepName(KSampleStep2);
	}

TVerdict CSampleStep2::doTestStepPreambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EFail);
	return TestStepResult();
	}

TVerdict CSampleStep2::doTestStepL()
/**
 * @return - TVerdict code
 * Override of base class pure virtual
 * Demonstrates reading configuration parameters fom an ini file section
 */
	{
	INFO_PRINTF1(_L("Step2: Should fail."));
	SetTestStepResult(EFail);
	return TestStepResult();
	}

TVerdict CSampleStep2::doTestStepPostambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EFail);
	return TestStepResult();
	}
///////

CSampleStep3::~CSampleStep3()
/**
 * Destructor
 */
	{
	}

CSampleStep3::CSampleStep3(CSampleServer& aParent) : iParent(aParent)
/**
 * Constructor
 */
	{
	// Call base class method to set up the human readable name for logging
	SetTestStepName(KSampleStep3);
	}

TVerdict CSampleStep3::doTestStepPreambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EInconclusive);
	return TestStepResult();
	}

TVerdict CSampleStep3::doTestStepL()
/**
 * @return - TVerdict code
 * Override of base class pure virtual
 * Demonstrates reading configuration parameters fom an ini file section
 */
	{
	INFO_PRINTF1(_L("Step3: Should be inconclusive."));
	SetTestStepResult(EInconclusive);
	return TestStepResult();
	}

TVerdict CSampleStep3::doTestStepPostambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EInconclusive);
	return TestStepResult();
	}
	
///////

CSampleStep4::~CSampleStep4()
/**
 * Destructor
 */
	{
	}

CSampleStep4::CSampleStep4(CSampleServer& aParent) : iParent(aParent)
/**
 * Constructor
 */
	{
	// Call base class method to set up the human readable name for logging
	SetTestStepName(KSampleStep4);
	}

TVerdict CSampleStep4::doTestStepPreambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(ETestSuiteError);
	return TestStepResult();
	}

TVerdict CSampleStep4::doTestStepL()
/**
 * @return - TVerdict code
 * Override of base class pure virtual
 * Demonstrates reading configuration parameters fom an ini file section
 */
	{
	INFO_PRINTF1(_L("Step4: Should give a test suite error."));
	SetTestStepResult(ETestSuiteError);
	return TestStepResult();
	}

TVerdict CSampleStep4::doTestStepPostambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(ETestSuiteError);
	return TestStepResult();
	}

///////

CSampleStep5::~CSampleStep5()
/**
 * Destructor
 */
	{
	}

CSampleStep5::CSampleStep5(CSampleServer& aParent) : iParent(aParent)
/**
 * Constructor
 */
	{
	// Call base class method to set up the human readable name for logging
	SetTestStepName(KSampleStep5);
	}

TVerdict CSampleStep5::doTestStepPreambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EAbort);
	return TestStepResult();
	}

TVerdict CSampleStep5::doTestStepL()
/**
 * @return - TVerdict code
 * Override of base class pure virtual
 * Demonstrates reading configuration parameters fom an ini file section
 */
	{
	INFO_PRINTF1(_L("Step5: Should abort."));
	SetTestStepResult(EAbort);
	return TestStepResult();
	}

TVerdict CSampleStep5::doTestStepPostambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EAbort);
	return TestStepResult();
	}


///////

CSampleStep6::~CSampleStep6()
/**
 * Destructor
 */
	{
	}

CSampleStep6::CSampleStep6(CSampleServer& aParent) : iParent(aParent)
/**
 * Constructor
 */
	{
	// Call base class method to set up the human readable name for logging
	SetTestStepName(KSampleStep6);
	}

TVerdict CSampleStep6::doTestStepPreambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	SetTestStepResult(EPass);
	return TestStepResult();
	}

TVerdict CSampleStep6::doTestStepL()
/**
 * @return - TVerdict code
 * Override of base class pure virtual
 * Demonstrates reading configuration parameters fom an ini file section
 */
	{
	INFO_PRINTF1(_L("Step6: Should pass if dependencies are found."));
	SetTestStepResult(EPass);
	return TestStepResult(); 
	}

TVerdict CSampleStep6::doTestStepPostambleL()
/**
 * @return - TVerdict code
 * Override of base class virtual
 */
	{
	User::LeaveIfError(iParent.Fs().Connect());
	
	//check for dependencies
	TUint lAttr;
	TInt lErr;

#if (defined __WINS__)
	///////////////////////
	_LIT(KMsgStart,"**Test Running on emulator");
	INFO_PRINTF1(KMsgStart);
		
	_LIT(KF1,"c:\\abc.txt");
	lErr=iParent.Fs().Att(KF1,lAttr);
	if(lErr!=KErrNone)
		{
		_LIT(KMsg,"Error: Unable to find dependency: c:\\abc.txt");
		INFO_PRINTF1(KMsg);
		SetTestStepResult(EFail);
		return TestStepResult();
		}
		
	///////////////////////
	_LIT(KF2,"c:\\abcd.txt");
	lErr=iParent.Fs().Att(KF2,lAttr);
	if(lErr!=KErrNone)
		{
		_LIT(KMsg,"Error: Unable to find dependency: c:\\abcd.txt");
		INFO_PRINTF1(KMsg);
		SetTestStepResult(EFail);
		return TestStepResult();
		}
	///////////////////////
	_LIT(KF3,"z:\\system\\libs\\TestBuildDep.dll");
	lErr=iParent.Fs().Att(KF3,lAttr);
	if(lErr!=KErrNone)
		{
		_LIT(KMsg,"Error: Unable to find dependency: z:\\system\\libs\\TestBuildDep.dll");
		INFO_PRINTF1(KMsg);
		SetTestStepResult(EFail);
		return TestStepResult();
		}
	///////////////////////
	_LIT(KF4,"z:\\system\\libs\\TestBuildDep2.dll");
	lErr=iParent.Fs().Att(KF4,lAttr);
	if(lErr!=KErrNone)
		{
		_LIT(KMsg,"Error: Unable to find dependency: z:\\system\\libs\\TestBuildDep2.dll");
		INFO_PRINTF1(KMsg);
		SetTestStepResult(EFail);
		return TestStepResult();
		}
	///////////////////////	
	SetTestStepResult(EPass);
	return TestStepResult();
#else
	_LIT(KMsgStart2,"**Test Running on the device");
	INFO_PRINTF1(KMsgStart2);
		
		
	_LIT(KF1,"c:\\abc.txt");
	lErr=iParent.Fs().Att(KF1,lAttr);
	if(lErr!=KErrNone)
		{
		_LIT(KMsg,"Error: Unable to find dependency: c:\\abc.txt");
		INFO_PRINTF1(KMsg);
		SetTestStepResult(EFail);
		return TestStepResult();
		}
		
	///////////////////////
	_LIT(KF2,"c:\\abcd.txt");
	lErr=iParent.Fs().Att(KF2,lAttr);
	if(lErr!=KErrNone)
		{
		_LIT(KMsg,"Error: Unable to find dependency: c:\\abcd.txt");
		INFO_PRINTF1(KMsg);
		SetTestStepResult(EFail);
		return TestStepResult();
		}
	///////////////////////
	_LIT(KF3,"c:\\system\\libs\\TestBuildDep.dll");
	lErr=iParent.Fs().Att(KF3,lAttr);
	if(lErr!=KErrNone)
		{
		_LIT(KMsg,"Error: Unable to find dependency: c:\\system\\libs\\TestBuildDep.dll");
		INFO_PRINTF1(KMsg);
		SetTestStepResult(EFail);
		return TestStepResult();
		}
	///////////////////////
	_LIT(KF4,"c:\\system\\libs\\TestBuildDep2.dll");
	lErr=iParent.Fs().Att(KF4,lAttr);
	if(lErr!=KErrNone)
		{
		_LIT(KMsg,"Error: Unable to find dependency: c:\\system\\libs\\TestBuildDep2.dll");
		INFO_PRINTF1(KMsg);
		SetTestStepResult(EFail);
		return TestStepResult();
		}
	///////////////////////	
	SetTestStepResult(EPass);
	return TestStepResult();
	
#endif

	}
