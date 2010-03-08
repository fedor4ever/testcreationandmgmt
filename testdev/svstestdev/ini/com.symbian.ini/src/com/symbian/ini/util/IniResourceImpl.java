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


package com.symbian.ini.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.TokenStreamSelector;

import com.symbian.comment.impl.CommentTagImpl;
import com.symbian.comment.parser.CommentLexer;
import com.symbian.ini.Data;
import com.symbian.ini.IniComment;
import com.symbian.ini.IniFactory;
import com.symbian.ini.IniModel;
import com.symbian.ini.Section;
import com.symbian.ini.parser.IniLexer;
import com.symbian.ini.parser.IniParser;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * 
 * @see com.symbian.ini.util.IniResourceFactoryImpl
 * @generated
 */
public class IniResourceImpl extends ResourceImpl {

	/**
	 * Creates an instance of the resource. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param uri
	 *            the URI of the new resource.
	 * @generated
	 */
	public IniResourceImpl(URI uri) {
		super(uri);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doLoad(InputStream aInputStream, Map aArgumenentsMap)
			throws IOException {

		// Create Unicode Reader
		Reader lReader = null;
		if (aArgumenentsMap != null
				&& aArgumenentsMap.get(XMLResource.OPTION_ENCODING) != null) {
			lReader = new BufferedReader(new InputStreamReader(aInputStream,
					(String) aArgumenentsMap.get(XMLResource.OPTION_ENCODING)));
		} else {
			lReader = new BufferedReader(new InputStreamReader(aInputStream));
		}

		// Create Lexors
		IniLexer lIniLexer = new IniLexer(lReader);
		CommentLexer lCommentLexer = new CommentLexer(lIniLexer.getInputState());

		// Create Selector
		TokenStreamSelector lSelector = new TokenStreamSelector();

		// Set Selector to Lexors
		lIniLexer.setTokenStreamSelector(lSelector);
		lCommentLexer.setTokenStreamSelector(lSelector);

		// Add the comment Lexer
		lSelector.addInputStream(lIniLexer, "IniLexer");
		lSelector.addInputStream(lCommentLexer, "CommentLexer");
		lSelector.select("IniLexer");

		// Create Parser
		IniParser lParser = new IniParser(lSelector);

		IniModel lIni = null;

		try {

			lIni = lParser.ini();

		} catch (RecognitionException lRecognitionException) {

			WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(
					lRecognitionException).setColumn(
					lRecognitionException.getColumn()).setLine(
					lRecognitionException.getLine()).setLocation(
					lRecognitionException.getFilename());

			if (lIni == null) {
				getErrors().add(lDiagnostic);
			} else {
				getWarnings().add(lDiagnostic);
			}

		} catch (TokenStreamException lTokenStreamException) {

			WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(
					lTokenStreamException.getMessage(), lTokenStreamException);

			if (lIni == null) {
				getErrors().add(lDiagnostic);
			} else {
				getWarnings().add(lDiagnostic);
			}

		} catch (Throwable lThrowable) {
			getErrors().add(
					new WrappedResourceDiagnostic(new Exception(lThrowable)));
		} finally {

			if (lIni == null) {
				lIni = IniFactory.eINSTANCE.createIniModel();
				Section lSection = IniFactory.eINSTANCE.createSection();
				lIni.getIni().add(lSection);

				getErrors().addAll(lParser.getExceptions());
				getErrors().addAll(lIniLexer.getExceptions());
				getErrors().addAll(lCommentLexer.getExceptions());
			} else {
				getWarnings().addAll(lParser.getExceptions());
				getWarnings().addAll(lIniLexer.getExceptions());
				getWarnings().addAll(lCommentLexer.getExceptions());
			}

			getContents().add(lIni);
		}
	}

	@Override
	protected void doSave(OutputStream aOutputStream, Map aArgumentsMap)
			throws IOException {

		final PrintWriter lPrintWriter = new PrintWriter(aOutputStream);

		IniSwitch lSaveSwitch = new IniSwitch() {

			@Override
			public Object caseIniComment(IniComment aIniComment) {
				String lCommentString = "";

				if (aIniComment.getComment() != null) {
					lCommentString += "//" + aIniComment.getComment() + "\r\n";
				}

				CommentTagImpl lCommentTagImpl = (CommentTagImpl) aIniComment
						.getTag();
				if (lCommentTagImpl != null) {

					String lCommentValue = "";
					String lCommentValueLiteral = (String) lCommentTagImpl
							.getValue();
					if (lCommentValueLiteral != null) {
						String[] lCommentSplit = lCommentValueLiteral
								.split("\r\n");
						lCommentValue = lCommentSplit[0];
						for (int lIter = 1; lIter < lCommentSplit.length; lIter++) {
							lCommentValue += "\r\n//!\t\t"
									+ lCommentSplit[lIter];
						}
					}

					lCommentString += "//! @" + lCommentTagImpl.getKey() + " "
							+ lCommentValue + "\r\n";

				}

				return lCommentString;
			}

			@Override
			public Object caseData(Data aEntry) {
				return aEntry.getKey() + " = " + aEntry.getValue();
			}

			@Override
			public Object caseSection(Section aSection) {
				return "[" + aSection.getName() + "]";
			}
		};

		for (TreeIterator lIterator = getAllContents(); lIterator.hasNext();) {
			String lPrintLine = (String) lSaveSwitch
					.doSwitch((EObject) lIterator.next());
			if (lPrintLine != null) {
				lPrintWriter.println(lPrintLine);
			}
		}

		lPrintWriter.println();

		lPrintWriter.flush();
		lPrintWriter.close();
	}

} // IniResourceImpl
