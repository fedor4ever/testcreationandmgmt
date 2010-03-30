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

#ifndef UT_CCHILD_H
#define UT_CCHILD_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class CChild;

// CLASS DECLARATION
class UT_CChild: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_CChild* NewL();
	static UT_CChild* NewLC();
	~UT_CChild();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

		void UT_Add();
		void UT_Constructor_CChild();
		void UT_Del();
		void UT_Destructor_CChild();
	
private: // Constructors

	UT_CChild();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	CChild *iCChild;
};

#endif // UT_CCHILD_H