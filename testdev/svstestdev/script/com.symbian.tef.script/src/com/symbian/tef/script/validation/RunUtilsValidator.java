/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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


package com.symbian.tef.script.validation;

import com.symbian.tef.script.UtilityCommand;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.symbian.tef.script.RunUtils}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface RunUtilsValidator {
	boolean validate();

	boolean validateUtilityCommand(UtilityCommand value);
	boolean validateParam(EList<String> value);
}
