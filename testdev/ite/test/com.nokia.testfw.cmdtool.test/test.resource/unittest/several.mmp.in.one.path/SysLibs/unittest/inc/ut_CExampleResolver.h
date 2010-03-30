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

#ifndef UT_CEXAMPLERESOLVER_H
#define UT_CEXAMPLERESOLVER_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class CExampleResolver;

// CLASS DECLARATION
class UT_CExampleResolver: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_CExampleResolver* NewL();
	static UT_CExampleResolver* NewLC();
	~UT_CExampleResolver();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

		void UT_Destructor_CExampleResolver();
		void UT_IdentifyImplementationL();
		void UT_ListAllL();
		void UT_NewL();
	
private: // Constructors

	UT_CExampleResolver();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	CExampleResolver *iCExampleResolver;
};

#endif // UT_CEXAMPLERESOLVER_H