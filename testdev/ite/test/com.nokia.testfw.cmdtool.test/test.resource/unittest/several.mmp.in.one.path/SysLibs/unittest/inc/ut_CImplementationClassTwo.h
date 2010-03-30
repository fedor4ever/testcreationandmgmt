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

#ifndef UT_CIMPLEMENTATIONCLASSTWO_H
#define UT_CIMPLEMENTATIONCLASSTWO_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class CImplementationClassTwo;

// CLASS DECLARATION
class UT_CImplementationClassTwo: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_CImplementationClassTwo* NewL();
	static UT_CImplementationClassTwo* NewLC();
	~UT_CImplementationClassTwo();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

		void UT_Destructor_CImplementationClassTwo();
		void UT_DoMethodL();
		void UT_NewL();
	
private: // Constructors

	UT_CImplementationClassTwo();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	CImplementationClassTwo *iCImplementationClassTwo;
};

#endif // UT_CIMPLEMENTATIONCLASSTWO_H