/*
* Copyright (c) 2010 Nokia Corporation and/or its subsidiary(-ies).
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


#ifndef UT_${class_object.NameUpperCase}_H
#define UT_${class_object.NameUpperCase}_H

// INCLUDES
#include <symbianunittest.h>

// FORWARD DECLARATIONS
class ${class_name};

// CLASS DECLARATION
class UT_${class_name}: public CSymbianUnitTest
{
public: // Constructors and destructor

	static UT_${class_name}* NewL();
	static UT_${class_name}* NewLC();
	~UT_${class_name}();

protected: // From CSymbianUnitTest

	void SetupL();
	void Teardown();

protected: // Test functions

	// [Test Case Declarations] Begin - Do not remove
//!!//!![Repeat Section Begin]::// [Test Case Declarations]
//!!foreach( $method_object in $class_object.Children)
	void UT_${method_object.NormalisedName}${RepeatCounter}();
//!!end
//!!//!![Repeat Section End]::// [Test Case Declarations]
	// [Test Case Declarations] End - Do not remove
private: // Constructors

	UT_${class_name}();
	void ConstructL();

private: // Data

	// The object to be tested as a member variable:
	${class_name} *i${class_name};
};

#endif // UT_${class_object.NameUpperCase}_H
