/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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

// INCLUDE FILES
#include <e32math.h>
#include <e32svr.h>
#include <StifTestModule.h>
#include "Test.h"
#include "StifTestEventInterface.h"

// EXTERNAL DATA STRUCTURES
//extern  ?external_data;

// EXTERNAL FUNCTION PROTOTYPES  
//extern ?external_function( ?arg_type,?arg_type );

// CONSTANTS
//const ?type ?constant_var = ?constant;

// MACROS
_LIT (KTooLongDesc, "CTestModuleTest::123456789012345678901234567890");
_LIT (KTooLongText, "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");		// 90
_LIT (KAlmostTooLongText, "123456789012345678901234567890123456789012345678901234567890123456789012345678%d");	// 78+2
_LIT (KDesc, "TestModule");

_LIT (KStifMacro_1, "macro testing");

// LOCAL CONSTANTS AND MACROS
//const ?type ?constant_var = ?constant;
//#define ?macro_name ?macro_def

// MODULE DATA STRUCTURES
//enum ?declaration
//typedef ?declaration

// LOCAL FUNCTION PROTOTYPES
//?type ?function_name( ?arg_type, ?arg_type );

// FORWARD DECLARATIONS
//class ?FORWARD_CLASSNAME;

// ==================== LOCAL FUNCTIONS =======================================


/*
-------------------------------------------------------------------------------

    Function: <function name>

    Description: <one line description>

    <Description of the functionality 
    description continues and...
    continues>

    Parameters:	<arg1>: <in/out/inout>: <accepted values>: <Description>
    
    Return Values: <value_1: Description
		            value_n: Description line 1
			                 description line 2>

    Errors/Exceptions: <description how errors and exceptions are handled>

    Status: Draft

-------------------------------------------------------------------------------
*/

/*
<type function_name(
    <arg_type arg,>  
    <arg_type arg >)  	
    {
    // <comment>
    <code> 
    
    // <comment>
    <code>
    }
*/


/*
-------------------------------------------------------------------------------

    DESCRIPTION

    <This module contains the implementation of xxx class 
	member functions...>

-------------------------------------------------------------------------------
*/

// ================= MEMBER FUNCTIONS =========================================

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: CTestModuleTest

    Description: Default constructor

    C++ default constructor can NOT contain any code, that
    might leave.
    
    Parameters:	None

    Return Values: None

    Errors/Exceptions: None

    Status: Draft
	
-------------------------------------------------------------------------------
*/

CTestModuleTest::CTestModuleTest()

    {
    return;
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: ConstructL

    Description: Symbian OS second phase constructor

    Symbian OS default constructor can leave.

    Parameters:	None

    Return Values: None

    Errors/Exceptions: <description how errors and exceptions are handled>

    Status: Draft
	
-------------------------------------------------------------------------------
*/
void CTestModuleTest::ConstructL()
    {
	
    return;
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: NewL

    Description: Two-phased constructor.
        
    Parameters:	None

    Return Values: CTestModuleTest*: new object

    Errors/Exceptions: <description how errors and exceptions are handled>

    Status: Draft
	
-------------------------------------------------------------------------------
*/

CTestModuleTest* CTestModuleTest::NewL()
    {
    CTestModuleTest* self = new (ELeave) CTestModuleTest();

    CleanupStack::PushL( self );
    self->ConstructL();
    CleanupStack::Pop();

    return self;
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: ~CTestModuleTest

    Description: Destructor
    
    Parameters:	None

    Return Values: None

    Errors/Exceptions: None

    Status: Draft
	
-------------------------------------------------------------------------------
*/    

CTestModuleTest::~CTestModuleTest()
    {
    
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: Init

    Description: Initialization function
    
    Init is used to initialize the Test Module. 
        
    Parameters:	TName& aIniFile: in: <accepted values>:
                    Configuration file for the test module
    
    Return Values: KErrnone: No error
                    <value_1: Description
		            value_n: Description line 1
			                 description line 2>

    Errors/Exceptions: <description how errors and exceptions are handled>

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TInt CTestModuleTest::InitL( TFileName& /* aIniFile */,
						    TBool /* aFirstTime*/ )
    {
	    

//    TestModuleIf().Printf( 0, _L("CTestModuleTest::Init"), _L("Initializing"));
    
    
//    TestModuleIf().Printf( 0, _L("CTestModuleTest::Init"), _L("Done"));
    return KErrNone;
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: GetTestCases

    Description: Get test cases.

    GetTestCases is used to inquired test cases from the Test Module.

    Parameters:	const TName& aConfigFile: in: <accepted values>: 
                    Config file name descriptor
                CArrayPtrFlat<TTestCaseInfo>& aTestCases: out: 
                    <accepted values>: Array of TestCases 
    
    Return Values: KErrNone: No error
                   <value_1: Description
		            value_n: Description line 1
			                 description line 2>

    Errors/Exceptions: <description how errors and exceptions are handled>

    Status: Draft
	
-------------------------------------------------------------------------------
*/
        
TInt CTestModuleTest::GetTestCasesL( const TFileName& /* aConfigFile */, 
                                   RPointerArray<TTestCaseInfo>& aTestCases )
    {
	
	for ( TUint i = 0; i <KLastCase; i++)
		{
		TTestCaseInfo* tc = new ( ELeave ) TTestCaseInfo();    
		CleanupStack::PushL( tc );
		tc->iCaseNumber = i;
		
		tc->iTitle.Copy( CaseName(i) );
		aTestCases.Append( tc );
		CleanupStack::Pop( tc );
		}
  
    return KErrNone;
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: RunTestCase

    Description: Run a specified testcase.

    RunTestCase is used to run an individual test case specified 
    by aTestCase. Test cases that can be run are requested from 
    Test Module by GetTestCases method before calling RunTestCase.
    This method is synchronous and test case result is returned as 
    function return value (KErrNone = Passed).
  
    Parameters:	const TInt aCaseNumber: in: <accepted values>: Testcase number 
                const TDesC8& aConfig: in: <accepted values>: Configuration
    
    Return Values: KErrNone: Passed.
                    <value_1: Description
		            value_n: Description line 1
			                 description line 2>

    Errors/Exceptions: <description how errors and exceptions are handled>

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TInt CTestModuleTest::RunTestCaseL( const TInt aCaseNumber, 
                                   const TFileName& aConfig,
								   TTestResult& aResult )
    {
    TInt execStatus = KErrNone;	

    TestModuleIf().Printf( 0, KDesc, _L("Enter"));

    TestModuleIf().Printf( 0, KDesc, 
            _L("Case number %i, aConfig: %S"), aCaseNumber, &aConfig );
	
	TEventIf event;            

    switch( aCaseNumber )
        {

		// A Passing case
		case KTestModuleCasePass:
		aResult.iResult = KErrNone;		
		break;
		// A failing case

		case KTestModuleCaseFail:
		aResult.iResult = KErrGeneral;		
		break;

		case KTestModuleOneLinePrint:        
        TestModuleIf().Printf( 0, KDesc,  _L("Simple print"));                
        aResult.iResult = KErrNone;        
        break;

		case KTestModuleSimplePrintParsing:        
        TestModuleIf().Printf( 0, KDesc,  _L("Simple print %d"), 0xdeaddead);                
        aResult.iResult = KErrNone;        
        break;

		case KTestModuleNegativePriorityPrint:        
        TestModuleIf().Printf( -1, KDesc,  _L("Simple print "));
        aResult.iResult = KErrNone;        
        break;

		case KTestModuleTooLongDescription:        
        TestModuleIf().Printf( 0, KTooLongDesc,  _L(""));
        aResult.iResult = KErrNone;        
        break;

		case KTestModuleTooLongText:        
        TestModuleIf().Printf( 0, KDesc,  KTooLongText);
        aResult.iResult = KErrNone;        
        break;

		case KTestModuleTooLongDescAndText:        
        TestModuleIf().Printf( 0, KTooLongDesc, KTooLongText);
        aResult.iResult = KErrNone;        
        break;

		case KTestModuleEmptyDescription:        
        TestModuleIf().Printf( 0, _L(""), _L("Something"));
        aResult.iResult = KErrNone;        
        break;

		case KTestmoduleEmptyText:        
        TestModuleIf().Printf( 0, KDesc, _L(""));
        aResult.iResult = KErrNone;        
        break;

		case KTestModuleEmptyDescriptionTwice:
        TestModuleIf().Printf( 0, _L(""), _L("Something"));
		TestModuleIf().Printf( 0, _L(""), _L("Something else"));
        aResult.iResult = KErrNone;        
        break;

		case KTestModulePrintParseOverflow:
		TestModuleIf().Printf( 0, KDesc, KAlmostTooLongText, 0xdeaddead );
		aResult.iResult = KErrNone;
		break;

		case KPrintQueueOverflow:
			{
			for (int i = 0; i < 100000;i++)
			{
			TestModuleIf().Printf( 0, KDesc, _L("") );
			}
		aResult.iResult = KErrNone;
		break;
			}

		case KPrintQueueOverflowWithLeave:
			{
			for (int i = 0; i < 100000;i++)
			{
			TestModuleIf().Printf( 0, KDesc, _L("") );
			}
			User::Leave(-1);
			aResult.iResult = KErrNone;
			break;
			}

		case KPrintQueueOverflowWithKill:
			{
			for (int i = 0; i < 100000;i++)
			{
			TestModuleIf().Printf( 0, KDesc, _L("") );
			}			       
			RThread t;
			t.Kill(-1);            
			aResult.iResult = KErrNone;
			break;
			}


        case KTestModulePrinting:
            {
            /* Simple print and wait loop */
            for( TInt i=0; i<10; i++)
                {
                TestModuleIf().Printf( 0, KDesc, 
                    _L("Complete in %i secs"), 10-i);
                User::After( 1000000 );
                }
            }
			aResult.iResult = KErrNone;
            break;

        case KTestModuleSleep:
            /* simple 10 sec sleeping */
            TestModuleIf().Printf( 0, KDesc, 
                _L("Sleep 10 secs"));
            User::After( 10000000 );
            break;
        case KTestModuleStateEventWait:
            {
            TestModuleIf().Printf( 0, KDesc, 
                _L("Request event"));
            event.Set( TEventIf::EReqEvent, 
                _L("TestModuleState1"), TEventIf::EState );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            TestModuleIf().Printf( 0, KDesc, 
                _L("Wait event"));
            event.SetType( TEventIf::EWaitEvent );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            TestModuleIf().Printf( 0, KDesc, 
                _L("Got event"));
            User::After( 3000000 );
            TestModuleIf().Printf( 0, KDesc, 
                _L("Release event"));
            event.SetType( TEventIf::ERelEvent );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            }
			aResult.iResult = KErrNone;
            break;
        case KTestModuleStateEventSetAndUnset:
            {
            TestModuleIf().Printf( 0, KDesc, 
                _L("Set event"));
            event.Set( TEventIf::ESetEvent, 
                _L("TestModuleState1"), TEventIf::EState );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            User::After( 4000000 );
            TestModuleIf().Printf( 0, KDesc, 
                _L("Unset event"));
            event.SetName( _L("TestModuleState1") );
            event.SetType( TEventIf::EUnsetEvent );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            User::After( 200000 );
            }
			aResult.iResult = KErrNone;
            break;
        case KTestModuleEventWait:
            {
            TestModuleIf().Printf( 0, KDesc, 
                _L("Request event"));
            event.Set( TEventIf::EReqEvent, _L("TestModuleIndication1") );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            TInt i = 1;
            FOREVER
                {
                TestModuleIf().Printf( 0, KDesc, 
                    _L("Wait event %i"), i);
                event.SetType( TEventIf::EWaitEvent );
                User::LeaveIfError( TestModuleIf().Event( event ) );
                TestModuleIf().Printf( 1, KDesc, 
                    _L("Got event %i"), i++);
                }
            TestModuleIf().Printf( 0, KDesc, 
                _L("Release event"));
            event.SetType( TEventIf::ERelEvent );
            User::LeaveIfError( TestModuleIf().Event( event ));
            }
			aResult.iResult = KErrNone;
            break;
        case KTestModuleEventSet:
            {
            event.Set( TEventIf::ESetEvent, _L("TestModuleIndication1") );
            for(TInt i = 0; i <  20; i++) 
                {
                TestModuleIf().Printf( 0, KDesc, 
                _L("Set event %i"), i+1);
                User::LeaveIfError( TestModuleIf().Event( event ) );
                User::After( 1000000 );
                }
            }
            break;

        case KTestModuleCrash:
            {
				// Do something illegal
          		TUint*p = (TUint*) 0xdeadbeef;
				*p = 1;
            }
            break;

		case KTestModuleKill:
            User::After( 1000000 );
            {
			RThread t;
			t.Kill(-1);
            }
            break;

		case KTestModuleException:
            User::After( 1000000 );
			{
			User().RaiseException( (TExcType) KExceptionAbort);
			}
			break;

		case KTestModuleLeave:
            User::After( 1000000 );
			{
				User::Leave(-1);
			}
			break;

		case KTestModulePanic:
            User::After( 1000000 );
			{
				User::Panic( _L("TestPanic"), -1);
			}
			break;

		case KCaseWithALongName:
				aResult.iResult = KErrNone;		
				break;

        case KTestModuleTwoStateEventSetAndUnset:
            {
            TestModuleIf().Printf( 0, KDesc, 
                _L("Set event"));
            event.Set( TEventIf::ESetEvent, 
                _L("TestModuleState1"), TEventIf::EState );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            event.SetName( _L("TestModuleState2") );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            User::After( 4000000 );
            TestModuleIf().Printf( 0, KDesc, 
                _L("Unset event"));
            event.SetType( TEventIf::EUnsetEvent );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            event.SetName( _L("TestModuleState1") );
            User::LeaveIfError( TestModuleIf().Event( event ) );
            User::After( 200000 );
            }
			aResult.iResult = KErrNone;
            break;
        case KTestModuleTwoEventSet:
            {
            event.Set( TEventIf::ESetEvent, _L("TestModuleIndication1") );
            TEventIf event2( TEventIf::ESetEvent, _L("TestModuleIndication2") );
            for(TInt i = 0; i <  20; i++) 
                {
                TestModuleIf().Printf( 0, KDesc, 
                _L("Set events %i"), i+1);
                User::LeaveIfError( TestModuleIf().Event( event ) );
                User::LeaveIfError( TestModuleIf().Event( event2 ) );
                User::After( 1000000 );
                }
            }
            break;

        case KCaseMemLeak:
            {
            HBufC* tmp = NULL;
            tmp = HBufC::NewL( 1 );
            }
            break;
        case KCaseMemLeakAllowed:
            {
            TestModuleIf().SetBehavior( CTestModuleIf::ETestLeaksMem );
            HBufC* tmp = NULL;
            tmp = HBufC::NewL( 1 );
            }
            break;
            
        case KCaseTestMaxResultDes:
            {
            aResult.iResult = KErrNone;	
			aResult.iResultDes = (_L( "Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text 128  XXX") );
			break;
            }            
        case KCaseTestResultSetting:
            {
            aResult.SetResult( KErrNone, _L( "aResult.SetResult passed") );
			break;
            }            
        case KCaseTestResultSettingFail:
            {
            aResult.SetResult( KErrGeneral, _L( "aResult.SetResult failed(OK)") );
			break;
            }
        case KCaseTestResultSettingWithMaxLength:
            {
            aResult.SetResult( KErrNone, _L( "Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text 128  XXX") );
			break;
            }
        case KCaseTestResultSettingWithTooLength:
            {
            aResult.SetResult( KErrNone, _L( "Long text Long text Long text Long text Long text Long text Long >> Description is over 128 so cutting this description XXXXXXXXThis not seen anymore") );
			break;
            }
        case KStifMacroTesting:
            {
            // Definitions
            TBool bool_false(EFalse);

            TBuf<50> met_buf;
            met_buf.Copy( _L( "macro testing" ) );

            TBuf<20> buf1;
            TBuf<20> buf2;
            buf1.Copy( _L( "Test 1" ) );
            buf2.Copy( _L( "Test 2" ) );

            // ---- STIF macro testing ----

            // Error codes
            TL( MacroTestInt_1() == KErrNone );
            TL( MacroTestInt_2() == KErrGeneral );

            // Comparison
            TL( 8 >= 6 );
            TL( 6 <= 8 );
            TL( 6 == 6 );

            // Boolean
            TL( EFalse == bool_false );
            //TL( MacroTestBool_1() == ETrue ); // Cannot compare to ETrue(1) ETrue is all other but not EFalse(0)
            TL( MacroTestBool_1() );
            TL( MacroTestBool_2() == EFalse );
            TL( MacroTestBool_1() != EFalse );
            TL( EFalse == MacroTestBool_2() );
            TL( EFalse != MacroTestBool_1() );

            // String
            TL( _L( "a" ) == _L( "a" ) );
            TL( _L( "a" ) != _L( "b" ) );
            TL( KStifMacro_1() == KStifMacro_1() );
            TL( KStifMacro_1() != _L( "abc123" ) );
            
            
            //TL( KStifMacro_1() == MacroTestString_1() );
            //TL( MacroTestString_1() == KStifMacro_1() );
            TL( met_buf == MacroTestString_1() );
            TL( MacroTestString_1() == met_buf );

            TL( KStifMacro_1() == KStifMacro_1() );

            TL( buf1 != buf2 );
            buf2.Copy( _L( "Test 1" ) );
            TL( buf1 == buf2 );


// TAL ->
            buf1.Copy( _L( "Test 1" ) );
            buf2.Copy( _L( "Test 2" ) );

           // Error codes
            TAL( MacroTestInt_1() == KErrNone );
            TAL( MacroTestInt_2() == KErrGeneral );

            // Comparison
            TAL( 8 >= 6 );
            TAL( 6 <= 8 );
            TAL( 6 == 6 );

            // Boolean
            TAL( EFalse == bool_false );
            //TL( MacroTestBool_1() == ETrue ); // Cannot compare to ETrue(1) ETrue is all other but not EFalse(0)
            TAL( MacroTestBool_1() );
            TAL( MacroTestBool_2() == EFalse );
            TAL( MacroTestBool_1() != EFalse );
            TAL( EFalse == MacroTestBool_2() );
            TAL( EFalse != MacroTestBool_1() );

            // String
            TAL( _L( "a" ) == _L( "a" ) );
            TAL( _L( "a" ) != _L( "b" ) );
            TAL( KStifMacro_1() == KStifMacro_1() );
            TAL( KStifMacro_1() != _L( "abc123" ) );
            
            
            //TL( KStifMacro_1() == MacroTestString_1() );
            //TL( MacroTestString_1() == KStifMacro_1() );
            TAL( met_buf == MacroTestString_1() );
            TAL( MacroTestString_1() == met_buf );

            TAL( KStifMacro_1() == KStifMacro_1() );

            TAL( buf1 != buf2 );
            buf2.Copy( _L( "Test 1" ) );
            TAL( buf1 == buf2 );

			break;
            }
        case KStifMacroTesting_INT:
            {
            T1L( MacroTestInt_1(), KErrNone );
            T1L( MacroTestInt_2(), KErrGeneral );

            T2L( MacroTestInt_1(), KErrNone, KErrNone );
            T2L( MacroTestInt_1(), KErrNone, KErrNotFound );
            T2L( MacroTestInt_1(), KErrNotFound, KErrNone );
            T2L( MacroTestInt_2(), KErrGeneral, KErrGeneral );
            T2L( MacroTestInt_2(), KErrGeneral, KErrAlreadyExists );
            T2L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral );

            T3L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone );
            T3L( MacroTestInt_1(), KErrNone, KErrNone, KErrNotFound );
            T3L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrNotFound );
            T3L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone );
            T3L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone );
            T3L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNotFound );
            T3L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral );
            T3L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrAlreadyExists );
            T3L( MacroTestInt_2(), KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );
            T3L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral );
            T3L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral );
            T3L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrAlreadyExists );

            T4L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNone );
            T4L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNotFound );
            T4L( MacroTestInt_1(), KErrNone, KErrNone, KErrNotFound , KErrNotFound );
            T4L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrNotFound, KErrNotFound );
            T4L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNone );
            T4L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone, KErrNone );
            T4L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNotFound, KErrNone );
            T4L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNotFound );
            T4L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone, KErrNotFound );
            T4L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNotFound, KErrNotFound );
            T4L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral );
            T4L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrAlreadyExists );
            T4L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );
            T4L( MacroTestInt_2(), KErrGeneral, KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists );
            T4L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrGeneral );
            T4L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrGeneral );
            T4L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrGeneral );
            T4L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrAlreadyExists );
            T4L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrAlreadyExists );
            T4L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );

            T5L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNone, KErrNone );
            T5L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNone, KErrNotFound );
            T5L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNotFound , KErrNotFound );
            T5L( MacroTestInt_1(), KErrNone, KErrNone, KErrNotFound, KErrNotFound, KErrNotFound );
            T5L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrNotFound, KErrNotFound, KErrNotFound );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNone, KErrNone );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone, KErrNone, KErrNone );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNotFound, KErrNone, KErrNone );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNotFound, KErrNotFound, KErrNone );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNone, KErrNotFound );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone, KErrNone, KErrNotFound );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNotFound, KErrNone, KErrNotFound );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNotFound, KErrNotFound );
            T5L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNotFound, KErrNotFound, KErrNotFound );
            T5L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral );
            T5L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral, KErrAlreadyExists );
            T5L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );
            T5L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists );
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral );
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrGeneral );
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrGeneral );
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrGeneral );
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrGeneral, KErrAlreadyExists );
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrAlreadyExists );            
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrAlreadyExists );
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );
            T5L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists );

// TAL ->
            TA1L( MacroTestInt_1(), KErrNone );
            TA1L( MacroTestInt_2(), KErrGeneral );

            TA2L( MacroTestInt_1(), KErrNone, KErrNone );
            TA2L( MacroTestInt_1(), KErrNone, KErrNotFound );
            TA2L( MacroTestInt_1(), KErrNotFound, KErrNone );
            TA2L( MacroTestInt_2(), KErrGeneral, KErrGeneral );
            TA2L( MacroTestInt_2(), KErrGeneral, KErrAlreadyExists );
            TA2L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral );

            TA3L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone );
            TA3L( MacroTestInt_1(), KErrNone, KErrNone, KErrNotFound );
            TA3L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrNotFound );
            TA3L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone );
            TA3L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone );
            TA3L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNotFound );
            TA3L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral );
            TA3L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrAlreadyExists );
            TA3L( MacroTestInt_2(), KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );
            TA3L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral );
            TA3L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral );
            TA3L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrAlreadyExists );

            TA4L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNone );
            TA4L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNotFound );
            TA4L( MacroTestInt_1(), KErrNone, KErrNone, KErrNotFound , KErrNotFound );
            TA4L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrNotFound, KErrNotFound );
            TA4L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNone );
            TA4L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone, KErrNone );
            TA4L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNotFound, KErrNone );
            TA4L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNotFound );
            TA4L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone, KErrNotFound );
            TA4L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNotFound, KErrNotFound );
            TA4L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral );
            TA4L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrAlreadyExists );
            TA4L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );
            TA4L( MacroTestInt_2(), KErrGeneral, KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists );
            TA4L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrGeneral );
            TA4L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrGeneral );
            TA4L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrGeneral );
            TA4L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrAlreadyExists );
            TA4L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrAlreadyExists );
            TA4L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );

            TA5L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNone, KErrNone );
            TA5L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNone, KErrNotFound );
            TA5L( MacroTestInt_1(), KErrNone, KErrNone, KErrNone, KErrNotFound , KErrNotFound );
            TA5L( MacroTestInt_1(), KErrNone, KErrNone, KErrNotFound, KErrNotFound, KErrNotFound );
            TA5L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrNotFound, KErrNotFound, KErrNotFound );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNone, KErrNone );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone, KErrNone, KErrNone );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNotFound, KErrNone, KErrNone );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNotFound, KErrNotFound, KErrNone );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNone, KErrNotFound );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNone, KErrNone, KErrNotFound );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNotFound, KErrNotFound, KErrNone, KErrNotFound );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNone, KErrNotFound, KErrNotFound );
            TA5L( MacroTestInt_1(), KErrNotFound, KErrNone, KErrNotFound, KErrNotFound, KErrNotFound );
            TA5L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral );
            TA5L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral, KErrAlreadyExists );
            TA5L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );
            TA5L( MacroTestInt_2(), KErrGeneral, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists );
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrGeneral, KErrGeneral );
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrGeneral );
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrGeneral );
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrGeneral );
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrGeneral, KErrAlreadyExists );
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrAlreadyExists );            
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists, KErrGeneral, KErrAlreadyExists );
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists );
            TA5L( MacroTestInt_2(), KErrAlreadyExists, KErrGeneral, KErrAlreadyExists, KErrAlreadyExists, KErrAlreadyExists );

			break;
            }
        case KStifMacroTesting_TL_fail_1:
            {
            TL( MacroTestInt_1() == KErrNone ); // Pass
            TL( MacroTestInt_2() == KErrNone ); // Fail(Leave)
			break;
            }
        case KStifMacroTesting_T1L_fail_1:
            {
            T1L( MacroTestInt_1(), KErrNone );      // Pass
            T1L( MacroTestInt_1(),  KErrNotFound ); // Fail(Leave)
			break;
            }
        case KStifMacroTesting_T2L_fail_1:
            {
            T2L( MacroTestInt_1(), KErrNone, KErrNotFound );     // Pass
            T2L( MacroTestInt_1(), KErrNotFound, KErrNotFound ); // Fail(Leave)
			break;
            }
        case KStifMacroTesting_T3L_fail_1:
            {
            T3L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrTotalLossOfPrecision ); // Pass
            T3L( MacroTestInt_1(), KErrNotFound, KErrDied, KErrTotalLossOfPrecision ); // Fail(Leave)
			break;
            }
        case KStifMacroTesting_T3L_fail_2:
            {
            T3L( MacroTestInt_2(), KErrGeneral, KErrNotFound, KErrTotalLossOfPrecision ); // Pass
            T3L( MacroTestInt_2(), KErrNotFound, KErrDied, KErrTotalLossOfPrecision ); // Fail(Leave)
			break;
            }
        case KStifMacroTesting_T4L_fail_1:
            {
            T4L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrTotalLossOfPrecision, KErrDisMounted ); // Pass
            T4L( MacroTestInt_1(), KErrNotFound, KErrDied, KErrTotalLossOfPrecision, KErrDisMounted ); // Fail(Leave)
			break;
            }
        case KStifMacroTesting_T4L_fail_2:
            {
            T4L( MacroTestInt_2(), KErrGeneral, KErrNotFound, KErrTotalLossOfPrecision, KErrDisMounted ); // Pass
            T4L( MacroTestInt_2(), KErrNotFound, KErrDied, KErrTotalLossOfPrecision, KErrDisMounted ); // Fail(Leave)
			break;
            }
        case KStifMacroTesting_T5L_fail_1:
            {
            T5L( MacroTestInt_1(), KErrNone, KErrNotFound, KErrTotalLossOfPrecision, KErrDisMounted, KErrCommsLineFail ); // Pass
            T5L( MacroTestInt_1(), KErrNotFound, KErrDied, KErrTotalLossOfPrecision, KErrDisMounted, KErrCommsLineFail ); // Fail(Leave)
			break;
            }
        case KStifMacroTesting_T5L_fail_2:
            {
            T5L( MacroTestInt_2(), KErrGeneral, KErrNotFound, KErrTotalLossOfPrecision, KErrDisMounted, KErrCommsLineFail ); // Pass
            T5L( MacroTestInt_2(), KErrNotFound, KErrDied, KErrTotalLossOfPrecision, KErrDisMounted, KErrCommsLineFail ); // Fail(Leave)
			break;
            }
        case KCaseVerifyThatTestCasePassesAfterMacro1:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }  
        case KCaseVerifyThatTestCasePassesAfterMacro2:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }
        case KCaseVerifyThatTestCasePassesAfterMacro3:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }
        case KCaseVerifyThatTestCasePassesAfterMacro4:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }
        case KCaseVerifyThatTestCasePassesAfterMacro5:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }
        case KCaseVerifyThatTestCasePassesAfterMacro6:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }
        case KCaseVerifyThatTestCasePassesAfterMacro7:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }
        case KCaseVerifyThatTestCasePassesAfterMacro8:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }
        case KCaseVerifyThatTestCasePassesAfterMacro9:
            {
            aResult.SetResult( KErrNone, _L( "Test case passed after macros") );
			break;
            }
        case KTestSetExitReasonWithKillingTestThread:
            {
            // Test case kills current thread, but we're expecting it to happen
            // with SetExitReason, so test case should go to passed category

            TestModuleIf().SetExitReason( CTestModuleIf::EException, -222 );
   
            RThread myself;
            myself.Kill(-222);
            
            //Thread can also be killed calling User::Exit(-222);
            // Should have already killed test thread and never come here..

            // Sets test case result and description
            aResult.SetResult( KErrGeneral, _L("KillThreadTest failed - didn't panic!!!") );
   
            // Test case execution failed!!!
            execStatus = KErrGeneral;
            break;        
            }
       case KSleepOneMinute: // This is changed to 30s
            {
            TTime time;
            time.HomeTime();  

            TTimeIntervalSeconds timeToAdd = 30;                      

            time += timeToAdd;
            User::At(time);

            aResult.SetResult( KErrNone, _L("Sleep one minute passed!") );    
            break;      
            }
        case KFailAlways:
            {
            User::After(2000000);
            
            aResult.SetResult( -222, _L("FailsAlways failed") );
            break;
            }

        case KStifMacroTesting_General_1:
            {
            T5L( MacroTestInt_General( -222 ), KErrNone, KErrNotFound, KErrTotalLossOfPrecision, KErrDisMounted, KErrCommsLineFail ); // fail
            break;
            }
        case KStifTAL_TA5LMacrosWithAllowresults:
            {
            TestModuleIf().SetAllowResult( KErrNone );
            TestModuleIf().SetAllowResult( -222 );
            TA5L( MacroTestInt_General( -222 ), KErrNone, KErrNotFound, KErrTotalLossOfPrecision, KErrDisMounted, KErrCommsLineFail ); // fail
            TestModuleIf().ResetAllowResult();
            aResult.SetResult( -KErrNone, _L("KStifTAL_TA5LMacrosWithAllowresults") );
            break;
            }

        case KTestModuleCaseNonExisting:
            User::After( 1000000 );
			// Fall 
           
        default:
            TestModuleIf().Printf( 0, KDesc, 
                _L("Case number %i, default behaviour"), aCaseNumber);
            User::After( 10000000 );
            execStatus = KErrNotFound;
			aResult.iResult = KErrNotFound;
            break;
        }
        
    return execStatus;
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: ThreadStatus

    Description: Print thread status 
  
    Parameters:	None

    Return Values: None

    Errors/Exceptions: <description how errors and exceptions are handled>

    Status: Draft
	
-------------------------------------------------------------------------------
*/

void CTestModuleTest::ThreadStatus()
    {
    RThread rt;
//    TTimeIntervalMicroSeconds cpuTime;
//    TInt heap, stack;
    TThreadId id = rt.Id();
    
//	TProcessMemoryInfo info;
//    RProcess().GetMemoryInfo( info );
TestModuleIf().Printf( 0, _L("CTestModuleTest::RunTestCase"), 
        _L("Thread: %i"), 
        id );
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: 

    Description: 
  
    Parameters:	

    Return Values: 

    Errors/Exceptions: 

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TInt CTestModuleTest::MacroTestInt_1()
    {
    TInt ret( KErrNone );
    return ret;

    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: 

    Description: 
  
    Parameters:	

    Return Values: 

    Errors/Exceptions: 

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TInt CTestModuleTest::MacroTestInt_2()
    {
    TInt ret( KErrGeneral );
    return ret;
    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: 

    Description: 
  
    Parameters:	

    Return Values: 

    Errors/Exceptions: 

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TInt CTestModuleTest::MacroTestInt_General( TInt aReturn )
    {
    // This rdebug added to be sure that TL macro will called only once this
    // method per check.
    RDebug::Print( _L( "######## --- MacroTestInt_General with code[%d]" ), aReturn );

    return aReturn;

    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: 

    Description: 
  
    Parameters:	

    Return Values: 

    Errors/Exceptions: 

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TBuf<50> CTestModuleTest::MacroTestString_1()
    {
    TBuf<50> buf;
    buf.Copy( _L( "macro testing" ) );
    return buf;

    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: 

    Description: 
  
    Parameters:	

    Return Values: 

    Errors/Exceptions: 

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TBool CTestModuleTest::MacroTestBool_1()
    {
    TBool bool_true(ETrue);    
    return bool_true;

    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: 

    Description: 
  
    Parameters:	

    Return Values: 

    Errors/Exceptions: 

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TBool CTestModuleTest::MacroTestBool_2()
    {
    TBool bool_false(EFalse);
    return bool_false;

    }

/*
-------------------------------------------------------------------------------

    Class: CTestModuleTest

    Method: TALMacrosWithAllowresults

    Description: 
  
    Parameters:	

    Return Values: 

    Errors/Exceptions: 

    Status: Draft
	
-------------------------------------------------------------------------------
*/

TInt TALMacrosWithAllowresults()
    {
    return KErrNone;

    }

// ================= OTHER EXPORTED FUNCTIONS =================================

/*
-------------------------------------------------------------------------------
   
    Function: LibEntryL

    Description: Polymorphic Dll Entry Point

    <in a MARM implementation, the entry point is 
    called when a thread is attached to or detached from the DLL>

    Parameters:	<arg1>: <in/out/inout>: <accepted values>: <description>
    
    Return Values: <value_1: Description
		            value_n: Description line 1
			                 description line 2>

    Errors/Exceptions: <description how errors and exceptions are handled>

    Status: Draft

-------------------------------------------------------------------------------
*/

EXPORT_C CTestModuleTest* LibEntryL()
    {
    return CTestModuleTest::NewL();
    }


// End of File
