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
* Description: This file contains the header file of the Test 
* test module of the Test Framework.
*
*/

#ifndef TEST_H
#define TEST_H

//  INCLUDES
#include "StifTestModule.h"

// CONSTANTS
//const <type constant_var = constant;>
//Mark "None" if not any, this goes for other sections as well.


static TPtrC8 CaseName( TInt aNumber )
{
    static const char* const casenames[] =
    {
	("Passing case"),
    ("Failing case"),
	("One line print"),
	("One line print with parsing"),
	("Printing with negative priority"),
	("Printing with too long description"),
	("Printing with too long text"),
	("Printing with too long desc&text"),
	("Printing with empty desc"),
	("Printing with empty text"),
	("Printing with empty desc twice"),
	("Print overflows when parsing"),
	("Print queue overflow"),
	("Print queue overflow with leave"),
	("Print queue overflow with kill (does not work in Visual Studio)"),
    ("Printing"),
    ("Sleep"),
    ("Wait state event"),
    ("Set and unset state event"),
    ("Wait indication and print"),
    ("Set indication event 20 times in every 1 sec"),
	("Test module crash (does not work in Visual Studio) "),
	("Test module kill"),
	("Test module exception"),
	("Test module leaves"),
	("Test module panic"),
	("Test module two state event set"),
	("Test module two indication event sending"),
	("Non existing test case1"),
	("A test case with a very long case name which will cause clipping here and there to fit it to buffers in server, engine or in UI"),
	("memory leak (fail case)"),
	("alloved memory leak"),
    ("Max size of the result description"),
    ("Test result setting"),
    ("Test result setting (fail case)"),
    ("Test result setting with max desc length"),
    ("Test result setting with too length desc. Description will cutted"),
    ("TL Macro testing"),
    ("T1L,T2L,T3L, T4L, T5L Macro testing with multible expected result"),
    ("TL Macro(fail case, -6) Macro testing, TL"),
    ("Verify that test case passes after macro testing"),
    ("T1L Macro(fail case, -6, result 0) STIF TF's macro testing with multible expected result"),
    ("Verify that test case passes after macro testing"),
    ("T2L Macro(fail case, -6, result 0) STIF TF's macro testing with multible expected result"),
    ("Verify that test case passes after macro testing"),
    ("T3L Macro(fail case, -6, result 0) STIF TF's macro testing with multible expected result"),
    ("Verify that test case passes after macro testing"),
    ("T3L Macro(fail case, -2, result -2) STIF TF's macro testing with multible expected result"),
    ("Verify that test case passes after macro testing"),
    ("T4L Macro(fail case, -6, result 0) STIF TF's macro testing with multible expected result"),
    ("Verify that test case passes after macro testing"),
    ("T4L Macro(fail case, -2, result -2) STIF TF's macro testing with multible expected result"),
    ("Verify that test case passes after macro testing"),
    ("T5L Macro(fail case, -6, result 0) STIF TF's macro testing with multible expected result"),
    ("Verify that test case passes after macro testing"),
    ("T5L Macro(fail case, -2, result -2) STIF TF's macro testing with multible expected result"),

    ("Verify that test case passes after macro testing"),
    ("Test SetExitReason with killing test thread (passes)"),
    ("Test module sleeps one minute (passes)"),
    ("T5L Macro(fail case, -222, result -222)"),
    ("TAL-TA5L Macros tests with allow results"),
    ("Test module fails always with -222 (fails)"),
    
	};

	const TUint8 **keys = (const TUint8**) casenames;
    TPtrC8 keyword( keys[ aNumber ] ); 
    return keyword;

    };

// MACROS
//#define <macro macro_def>


// DATA TYPES
//enum <declaration>
//typedef <declaration>
//extern <data_type;>


// FUNCTION PROTOTYPES
//<type function_name(arg_list);>


// FORWARD DECLARATIONS
//<class FORWARD_CLASSNAME;>


// CLASS DECLARATION


// DESCRIPTION
// <one line short description>
// <other description lines>

NONSHARABLE_CLASS(CTestModuleTest) 
        :public CTestModuleBase
    {
    public: // Enumerations

    private: // Enumerations
        enum TestModuleTestCases
            {
			KTestModuleCasePass,
			KTestModuleCaseFail,
			KTestModuleOneLinePrint,
			KTestModuleSimplePrintParsing,			
			KTestModuleNegativePriorityPrint,
			KTestModuleTooLongDescription,
			KTestModuleTooLongText,
			KTestModuleTooLongDescAndText,
			KTestModuleEmptyDescription,
			KTestmoduleEmptyText,
			KTestModuleEmptyDescriptionTwice,
			KTestModulePrintParseOverflow,
			KPrintQueueOverflow,
			KPrintQueueOverflowWithLeave,
			KPrintQueueOverflowWithKill,
            KTestModulePrinting,
            KTestModuleSleep,
            KTestModuleStateEventWait,
            KTestModuleStateEventSetAndUnset,
            KTestModuleEventWait,
            KTestModuleEventSet,
			KTestModuleCrash,
			KTestModuleKill,
			KTestModuleException,
			KTestModuleLeave,
			KTestModulePanic,
			KTestModuleTwoStateEventSetAndUnset,
			KTestModuleTwoEventSet,
			KTestModuleCaseNonExisting,
			KCaseWithALongName,
			KCaseMemLeak,
			KCaseMemLeakAllowed,
            KCaseTestMaxResultDes,
            KCaseTestResultSetting,
            KCaseTestResultSettingFail,
            KCaseTestResultSettingWithMaxLength,
            KCaseTestResultSettingWithTooLength,
            KStifMacroTesting,
            KStifMacroTesting_INT,
            KStifMacroTesting_TL_fail_1,
            KCaseVerifyThatTestCasePassesAfterMacro1,
            KStifMacroTesting_T1L_fail_1,
            KCaseVerifyThatTestCasePassesAfterMacro2,
            KStifMacroTesting_T2L_fail_1,
            KCaseVerifyThatTestCasePassesAfterMacro3,
            KStifMacroTesting_T3L_fail_1,
            KCaseVerifyThatTestCasePassesAfterMacro4,
            KStifMacroTesting_T3L_fail_2,
            KCaseVerifyThatTestCasePassesAfterMacro5,
            KStifMacroTesting_T4L_fail_1,
            KCaseVerifyThatTestCasePassesAfterMacro6,
            KStifMacroTesting_T4L_fail_2,
            KCaseVerifyThatTestCasePassesAfterMacro7,
            KStifMacroTesting_T5L_fail_1,
            KCaseVerifyThatTestCasePassesAfterMacro8,
            KStifMacroTesting_T5L_fail_2,
            KCaseVerifyThatTestCasePassesAfterMacro9,
            KTestSetExitReasonWithKillingTestThread,
            KSleepOneMinute,
            KStifMacroTesting_General_1,
            KStifTAL_TA5LMacrosWithAllowresults,
            KFailAlways,
			KLastCase								// This must be last one
            };

    public:  // Constructors and destructor
        
        /**
        * Two-phased constructor.
        */
        static CTestModuleTest* NewL();
        
        /**
        * Destructor.
        */
        ~CTestModuleTest();

    public: // New functions
        
        /**
        * <member_description.>
        */
        //<type member_function( type arg1 );>

    public: // Functions from base classes
        
         /**
        * Init is used to initialize the Test Module. The Test Modules can use 
        * the config file to read parameters for Test Module initialization but
        * they can also have their own config file or some other routine to
        * initialize themselves.
        * This method is pure virtual and the Test Module shall implement it.
        */
        TInt InitL( TFileName& aIniFile, TBool aFirstTime );
        /**
        * Test cases are inquired from the Test Module by calling GetTestCases. 
        * Test cases are read from the config file specified by aConfigFile. 
        * If the Test Module does not use config files for test case definition 
        * it does not use aConfigFile parameter. Test cases are appended 
        * to CArrayPtrFlat<TTestCaseInfo> that is a list consisting of 
        * several TTestCaseInfo objects.
        * TTestCaseInfo class defines individual test cases and, if needed, 
        * a test set where the test case belongs to. TTestCaseInfo is 
        * defined in Table 9.
        * This method is pure virtual and the Test Module shall implement it.
        */
        TInt GetTestCasesL( const TFileName& aConfigFile, 
            RPointerArray<TTestCaseInfo>& aTestCases );
        /**
        * RunTestCase is used to run an individual test case specified 
        * by aTestCase. Test cases that can be run are requested from 
        * Test Module by GetTestCases method before calling RunTestCase.
        * This method is pure virtual and the Test Module shall implement it. 
        */
        TInt RunTestCaseL( const TInt aCaseNumber, 
                          const TFileName& aConfig,
                          TTestResult& aResult );
        
    protected:  // New functions
        
        /**
        * <member_description.>
        */
        //<type member_function( type arg1 );>

    protected:  // Functions from base classes
        
        /**
        * From <base_class member_description>
        */
        //<type member_function();>

    private:

        /**
        * C++ default constructor.
        */
        CTestModuleTest();

        /**
        * By default Symbian OS constructor is private.
        */
        void ConstructL();

        // Prohibit copy constructor if not deriving from CBase.
        // <classname( const classname& );>
        // Prohibit assigment operator if not deriving from CBase.
        // <classname& operator= ( const classname& );>

        /**
        * Get thread status. 
        */
        void ThreadStatus();

        /**
        * 
        */
        TInt MacroTestInt_1();

        /**
        * 
        */
        TInt MacroTestInt_2();

        /**
        * 
        */
        TInt MacroTestInt_General( TInt aReturn );

        /**
        * 
        */
        TBuf<50> MacroTestString_1();

        /**
        * 
        */
        TBool MacroTestBool_1();

        /**
        * 
        */
        TBool MacroTestBool_2();

        /**
        * 
        */
        TInt TALMacrosWithAllowresults();

    public:   //Data
        //<data_declaration;>
    
    protected:  // Data
        //<data_declaration;>

    private:    // Data
        //<data_declaration;>

    public:     // Friend classes
        //<friend_class_declaration;>

    protected:  // Friend classes
        //<friend_class_declaration;>

    private:    // Friend classes
        //<friend_class_declaration;>

    };

#endif      // TEST_H

// End of File