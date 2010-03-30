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

#ifndef UT_CCLASS2_H
#define UT_CCLASS2_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class CClass2;

// CLASS DECLARATION
class UT_CClass2: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_CClass2* NewL();
	static UT_CClass2* NewLC();
	~UT_CClass2();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

		void UT_Constructor_CClass2();
		void UT_Destructor_CClass2();
		void UT_PubFoo1();
		void UT_PubFoo2();
		void UT_staFoo4();
	
private: // Constructors

	UT_CClass2();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	CClass2 *iCClass2;
};

#endif // UT_CCLASS2_H