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

#ifndef UT_CIMPLEMENTATIONCLASSONE_H
#define UT_CIMPLEMENTATIONCLASSONE_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class CImplementationClassOne;

// CLASS DECLARATION
class UT_CImplementationClassOne: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_CImplementationClassOne* NewL();
	static UT_CImplementationClassOne* NewLC();
	~UT_CImplementationClassOne();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

		void UT_Destructor_CImplementationClassOne();
		void UT_DoMethodL();
		void UT_NewL();
	
private: // Constructors

	UT_CImplementationClassOne();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	CImplementationClassOne *iCImplementationClassOne;
};

#endif // UT_CIMPLEMENTATIONCLASSONE_H