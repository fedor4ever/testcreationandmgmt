/*
 * Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies).
 * All rights reserved.
 * This component and the accompanying materials are made available
 * under the terms of the License "Symbian Foundation License v1.0"
 * which accompanies this distribution, and is available
 * at the URL "http://www.symbianfoundation.org/legal/epl-v10.html".
 *
 * Initial Contributors:
 * Nokia Corporation - initial contribution.
 *
 * Contributors:
 *
 * Description:
 *
 */

#ifndef UT_CTEST1_H
#define UT_CTEST1_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class CTest1;

// CLASS DECLARATION
class UT_CTest1: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_CTest1* NewL();
	static UT_CTest1* NewLC();
	~UT_CTest1();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

		void UT_Constructor_CTest1();
		void UT_Destructor_CTest1();
		void UT_Start();
		void UT_threadFunc();
	
private: // Constructors

	UT_CTest1();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	CTest1 *iCTest1;
};

#endif // UT_CTEST1_H