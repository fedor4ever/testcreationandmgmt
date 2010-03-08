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



/**
 *
 * add define for ulogger
 *
 * */
#if (!defined _SYMBIAN_TRACE_ENABLE)
#define _SYMBINA_TRACE_ENABLE
#endif

#if (!defined __SAMPLE_STEP_H__)
#define __SAMPLE_STEP_H__
#include <TestExecuteStepBase.h>
#include "SampleServer.h"

// __EDIT_ME__ - Create your own test step definitions
class CSampleStep1 : public CTestStep
	{
public:
	CSampleStep1();
	~CSampleStep1();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepPostambleL();
	virtual TVerdict doTestStepL();
private:
	};

class CSampleStep2 : public CTestStep
	{
public:
	CSampleStep2(CSampleServer& aParent);
	~CSampleStep2();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepPostambleL();
	virtual TVerdict doTestStepL();
private:
	CSampleServer& iParent;
	};

class CSampleStep3 : public CTestStep
	{
public:
	CSampleStep3(CSampleServer& aParent);
	~CSampleStep3();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepPostambleL();
	virtual TVerdict doTestStepL();
private:
	CSampleServer& iParent;
	};
	
class CSampleStep4 : public CTestStep
	{
public:
	CSampleStep4(CSampleServer& aParent);
	~CSampleStep4();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepPostambleL();
	virtual TVerdict doTestStepL();
private:
	CSampleServer& iParent;
	};
	
class CSampleStep5 : public CTestStep
	{
public:
	CSampleStep5(CSampleServer& aParent);
	~CSampleStep5();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepPostambleL();
	virtual TVerdict doTestStepL();
private:
	CSampleServer& iParent;
	};

class CSampleStep6 : public CTestStep
	{
public:
	CSampleStep6(CSampleServer& aParent);
	~CSampleStep6();
	virtual TVerdict doTestStepPreambleL();
	virtual TVerdict doTestStepPostambleL();
	virtual TVerdict doTestStepL();
private:
	CSampleServer& iParent;
	};


_LIT(KSampleStep1,"SampleStep1");
_LIT(KSampleStep2,"SampleStep2");
_LIT(KSampleStep3,"SampleStep3");
_LIT(KSampleStep4,"SampleStep4");
_LIT(KSampleStep5,"SampleStep5");
_LIT(KSampleStep6,"SampleStep6");

#endif
