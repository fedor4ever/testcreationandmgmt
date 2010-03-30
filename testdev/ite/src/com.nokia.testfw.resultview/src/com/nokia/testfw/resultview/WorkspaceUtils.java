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
package com.nokia.testfw.resultview;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author xiaoma
 *
 */
public class WorkspaceUtils {

	/**
	 * open specified file in associate editor, move cursor to the right position
	 * @return true, the file opened successfully
	 */
	public static boolean openFile(String fileName, int line, int column) {
		//find file in workspace
		IFile file = findFileByName(fileName);
		if (file == null) {
			ResultViewPlugin.log(IStatus.WARNING, "can't find file to open:" + fileName);
			return false;
		}
		
		try {
//			//open editor
			IWorkbenchPage page =
				 PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorPart part = IDE.openEditor(page, file, true);
			selectLine(part, line);
			//todo: find a way to locate line and column
			
//			   HashMap map = new HashMap();
//			   map.put(IMarker.LINE_NUMBER, new Integer(line));
//			   IMarker marker = file.createMarker(IMarker.TEXT);
//			   marker.setAttributes(map);
//			   marker.delete();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return false;
	}
	
	private static void selectLine(IEditorPart editorPart, int lineNumber) {
		if (lineNumber > 0 && editorPart instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor)editorPart;
			IEditorInput input = editorPart.getEditorInput();
			
			int offset = 0;
			int length = 0;
			IDocumentProvider provider = textEditor.getDocumentProvider();
			try {
				provider.connect(input);
			} catch (CoreException e) {
				// unable to link
				e.printStackTrace();
				return;
			}
			IDocument document = provider.getDocument(input);
			try {
				IRegion region= document.getLineInformation(lineNumber - 1);
				offset = region.getOffset();
				length = region.getLength();
			} catch (BadLocationException e) {
				// unable to link
				e.printStackTrace();
			}
			provider.disconnect(input);
			if (offset >= 0 && length >=0) {
				textEditor.selectAndReveal(offset, length);
			}
		}
	}
	
	/**
	 * find flie by name. 
	 * this will loop up all files in workspace opened project and return the first matched
	 * file
	 * @return the file or null
	 */
	private static IFile findFileByName(String fileName) {
		IProject[] projects =
			ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (IProject project : projects) {
			if (!project.isOpen()) {
				continue;
			}
			IFile file = findFileByName(project, fileName);
			if (file != null) {
				return file;
			}
		}
		return null;
	}
	
	private static IFile findFileByName(IContainer container, String fileName) {
		IResource file = null;
		file = container.findMember(fileName);
		if (file != null && file instanceof IFile) {
			return (IFile)file;
		}
		try {
			for (IResource res:container.members()) {
				if (res instanceof IContainer) {
					file = findFileByName((IContainer)res, fileName);
					if (file != null) {
						return (IFile)file;
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
