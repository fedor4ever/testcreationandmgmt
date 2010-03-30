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

#ifndef UT_CCLASS1_H
#define UT_CCLASS1_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class CClass1;

// CLASS DECLARATION
class UT_CClass1: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_CClass1* NewL();
	static UT_CClass1* NewLC();
	~UT_CClass1();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

		void UT_Constructor_CClass1();
		void UT_Destructor_CClass1();
		void UT_PubFoo1();
	
private: // Constructors

	UT_CClass1();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	CClass1 *iCClass1;
};

#endif // UT_CCLASS1_H