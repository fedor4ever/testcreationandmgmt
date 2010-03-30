/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies).
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

package com.nokia.testfw.codegen.ui.parser;

import java.util.Collection;

import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICElementVisitor;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

final public class TranslationUnitCollector implements ICElementVisitor {
	private final Collection<ITranslationUnit> fSources;
	private final Collection<ITranslationUnit> fHeaders;
	private final IProgressMonitor fProgressMonitor;

	public TranslationUnitCollector(Collection<ITranslationUnit> sources,
			Collection<ITranslationUnit> headers, IProgressMonitor pm) {
		fSources = sources;
		fHeaders = headers;
		fProgressMonitor = pm;
	}

	public boolean visit(ICElement element) throws CoreException {
		if (fProgressMonitor.isCanceled()) {
			return false;
		}
		switch (element.getElementType()) {
		case ICElement.C_UNIT:
			ITranslationUnit tu = (ITranslationUnit) element;
			if (fSources != null && tu.isSourceUnit()) {
				fSources.add(tu);
			} else if (fHeaders != null && tu.isHeaderUnit()) {
				fHeaders.add(tu);
			}
			return false;
		case ICElement.C_CCONTAINER:
		case ICElement.C_PROJECT:
			return true;
		}
		return false;
	}
}