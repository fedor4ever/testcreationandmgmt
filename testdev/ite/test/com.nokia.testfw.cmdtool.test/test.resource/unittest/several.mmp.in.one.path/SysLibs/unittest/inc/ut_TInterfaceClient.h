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

#ifndef UT_TINTERFACECLIENT_H
#define UT_TINTERFACECLIENT_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class TInterfaceClient;

// CLASS DECLARATION
class UT_TInterfaceClient: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_TInterfaceClient* NewL();
	static UT_TInterfaceClient* NewLC();
	~UT_TInterfaceClient();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

		void UT_GetByDiscoveryL();
		void UT_GetBySpecificationL();
		void UT_GetDefaultL();
	
private: // Constructors

	UT_TInterfaceClient();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	TInterfaceClient *iTInterfaceClient;
};

#endif // UT_TINTERFACECLIENT_H