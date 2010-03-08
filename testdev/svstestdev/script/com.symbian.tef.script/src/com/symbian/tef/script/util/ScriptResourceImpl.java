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


package com.symbian.tef.script.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.TokenStreamSelector;

import com.symbian.comment.impl.CommentTagImpl;
import com.symbian.comment.parser.CommentLexer;
import com.symbian.ini.util.WrappedResourceDiagnostic;
import com.symbian.tef.script.AsyncDelay;
import com.symbian.tef.script.Command;
import com.symbian.tef.script.Concurrent;
import com.symbian.tef.script.Consecutive;
import com.symbian.tef.script.Container;
import com.symbian.tef.script.CreateObject;
import com.symbian.tef.script.Delay;
import com.symbian.tef.script.LoadServer;
import com.symbian.tef.script.LoadSuite;
import com.symbian.tef.script.Outstanding;
import com.symbian.tef.script.Pause;
import com.symbian.tef.script.Prefix;
import com.symbian.tef.script.Print;
import com.symbian.tef.script.Repeat;
import com.symbian.tef.script.RestoreObject;
import com.symbian.tef.script.RunProgram;
import com.symbian.tef.script.RunScript;
import com.symbian.tef.script.RunUtils;
import com.symbian.tef.script.ScriptFactory;
import com.symbian.tef.script.SharedActiveScheduler;
import com.symbian.tef.script.SharedData;
import com.symbian.tef.script.Store;
import com.symbian.tef.script.StoreActiveScheduler;
import com.symbian.tef.script.Tef;
import com.symbian.tef.script.TefComment;
import com.symbian.tef.script.TefModel;
import com.symbian.tef.script.TestBlock;
import com.symbian.tef.script.TestCase;
import com.symbian.tef.script.TestStep;
import com.symbian.tef.script.parser.ScriptLexer;
import com.symbian.tef.script.parser.ScriptParser;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see com.symbian.tef.script.util.ScriptResourceFactoryImpl
 * @generated
 */
public class ScriptResourceImpl extends XMLResourceImpl {

	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public ScriptResourceImpl(URI uri) {
		super(uri);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doLoad(InputStream aInputStream, Map aArgumenentsMap)
			throws IOException {
		// Create Unicode Reader
		Reader lReader = null;
		if (aArgumenentsMap != null && aArgumenentsMap.get(XMLResource.OPTION_ENCODING) != null) {
			lReader = new BufferedReader(new InputStreamReader(aInputStream, (String) aArgumenentsMap.get(XMLResource.OPTION_ENCODING)));
		} else {
			lReader = new BufferedReader(new InputStreamReader(aInputStream));
		}

		// Create Lexors
		ScriptLexer lScriptLexer = new ScriptLexer(lReader);
		CommentLexer lCommentLexer = new CommentLexer(lScriptLexer.getInputState());

		// Create Selector
		TokenStreamSelector lSelector = new TokenStreamSelector();
		lScriptLexer.setTokenStreamSelector(lSelector);
		lCommentLexer.setTokenStreamSelector(lSelector);

		// Add the Comment Lexer
		lSelector.addInputStream(lScriptLexer, "ScriptLexer");
		lSelector.addInputStream(lCommentLexer, "CommentLexer");
		lSelector.select("ScriptLexer");

		// Create Parser
		ScriptParser lParser = new ScriptParser(lSelector);
		lParser.setResource(this);

		TefModel lTefModel = null;

		try {
			
			lTefModel = lParser.model();
			
		} catch (RecognitionException lRecognitionException) {
			
			WrappedResourceDiagnostic lDiagnositic = new WrappedResourceDiagnostic(lRecognitionException)
				.setColumn(lRecognitionException.getColumn())
				.setLine(lRecognitionException.getLine())
				.setLocation(lRecognitionException.getFilename());
			
			if (lTefModel == null) {
				getErrors().add(lDiagnositic);
			} else {
				getWarnings().add(lDiagnositic);
			}
			
		} catch (TokenStreamException lTokenStreamException) {
			
			WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(lTokenStreamException.getMessage(),
					lTokenStreamException);
			
			if (lTefModel == null) {
				getErrors().add(lDiagnostic);
			} else {
				getWarnings().add(lDiagnostic);
			}
			
		} catch (Throwable lThrowable) {
			getErrors().add(new WrappedResourceDiagnostic(new Exception(lThrowable)));
		} finally {
			
			if (lTefModel == null) {
				lTefModel = ScriptFactory.eINSTANCE.createTefModel();
				TestStep lTestStep = ScriptFactory.eINSTANCE.createTestStep();
				lTefModel.getTef().add(lTestStep);
				
				getErrors().addAll(lParser.getExceptions());
				getErrors().addAll(lScriptLexer.getExceptions());
				getErrors().addAll(lCommentLexer.getExceptions());
			} else {
				getWarnings().addAll(lParser.getExceptions());
				getWarnings().addAll(lScriptLexer.getExceptions());
				getWarnings().addAll(lCommentLexer.getExceptions());
			}
			
			getContents().add(lTefModel);
		}
		
	}

	@Override
	public void doSave(OutputStream aOutputStream, Map options)
			throws IOException {
		final PrintWriter lPrintWriter = new PrintWriter(aOutputStream);

		ScriptSwitch lScriptSwitch = new ScriptSwitch() {

			@Override
			public Object caseTefComment(TefComment aTefComment) {
				String lCommentString = "";
				
				if (aTefComment.getComment() != null) {
					lCommentString += "//" +  aTefComment.getComment();
				}
				
				CommentTagImpl lCommentTagImpl = (CommentTagImpl) aTefComment.getTag();
				if (lCommentTagImpl != null) {
					
					String lCommentValue = "";
					String lCommentValueLiteral = (String) lCommentTagImpl.getValue();
					if (lCommentValueLiteral != null) {
						String[] lCommentSplit = lCommentValueLiteral.split("\r\n");
						lCommentValue = lCommentSplit[0];
						for (int lIter = 1; lIter < lCommentSplit.length; lIter++) {
							lCommentValue += "\r\n//!\t\t" + lCommentSplit[lIter];
						}
					}
					
					lCommentString += "//! @"
						+ lCommentTagImpl.getKey() + " "
						+ lCommentValue;
					
				}
				
				return lCommentString;
			}
			
			@Override
			public Object caseConcurrent(Concurrent object) {
				return "CONCURRENT";
			}

			@Override
			public Object caseConsecutive(Consecutive object) {
				return "CONSECUTIVE";
			}

			@Override
			public Object caseDelay(Delay object) {
				return "\r\nDELAY " + object.getTimeout() + "\r\n";
			}

			@Override
			public Object caseLoadServer(LoadServer object) {
				return "LOAD_SERVER "
						+ object.getServer() + " "
						+ (object.isSharedData() ? "-SHAREDDATA" : "");
			}

			@Override
			public Object caseLoadSuite(LoadSuite object) {
				return "LOAD_SUITE "
						+ object.getServer() + " "
						+ (object.isSharedData() ? "-SHAREDDATA" : "");
			}

			@Override
			public Object casePause(Pause object) {
				return "\r\nPAUSE\r\n";
			}

			@Override
			public Object casePrefix(Prefix object) {
				return "\r\nPREFIX";
			}

			@Override
			public Object casePrint(Print object) {
				return "\r\nPRINT " + object.getPrint() + "\r\n";
			}

			@Override
			public Object caseRepeat(Repeat object) {
				return "\r\nSTART_REPEAT " 
						+ object.getIniPersistance() + " "
						+ (object.getSection() == null ? "null" : object.getSection().getName()) + " "
						+ object.getName() + " ";
			}

			@Override
			public Object caseRunProgram(RunProgram object) {
				return "RUN_PROGRAM " + object.getName() + " "
						+ object.getTimeout();
			}

			@Override
			public Object caseRunScript(RunScript object) {
				return "RUN_SCRIPT " + object.getScriptPersistance();
			}

			@Override
			public Object caseRunUtils(RunUtils object) {
				String lParams = "";
				for (Iterator lIter = object.getParam().iterator(); lIter
						.hasNext();) {
					lParams += " " + lIter.next();
				}

				return "RUN_UTILS "
						+ (object.getUtilityCommand() == null ? "null" : object.getUtilityCommand().getLiteral() + lParams);
			}

			@Override
			public Object caseSharedData(SharedData object) {
				return "SHARED_DATA "
						+ object.getIniPersistance() + " "
						+ (object.getSection() == null ? "null" : object.getSection().getName());
			}

			@Override
			public Object caseTestCase(TestCase object) {
				return "\r\nSTART_TESTCASE " + object.getName();
			}

			@Override
			public Object caseTestStep(TestStep object) {
				String lNegativeTestString = " ";
				if (object.isSetError()) {
					if(object.getError().trim().length()>0)
					{
						lNegativeTestString += " !Error=" + object.getError();
	
					}
				}
				if (object.isSetPanicCode()) {
					lNegativeTestString += " !PanicCode=" + object.getPanicCode();
				}
				
				if (object.isSetPanicString()) {
					if(object.getPanicString().trim().length()>0)
					{
						lNegativeTestString += " !PanicString=" + object.getPanicString();
				
					}
				}
				if (object.isSetHeap()) {
					lNegativeTestString += " !Heap=" + object.getHeap();
				}
				if (object.isSetResult()) {
					if(object.getResult().trim().length()>0)
					{
						lNegativeTestString += " !Result=" + object.getResult();
				
					}
				}
				if (object.isSetOOM()) {
					lNegativeTestString += " !OOM=" + object.getOOM();
				}

				return "RUN_TEST_STEP" + lNegativeTestString + " "
						+ object.getTimeout() + " " 
						+ object.getServer() + " "
						+ object.getMethod() + " "
						+ (object.getIniPersistance() == null  ? "" : object.getIniPersistance() + " "
						+ (object.getSection() == null ? object.getSectionPersistance() : object.getSection().getName()));
			}
			
			@Override
			public Object caseCreateObject(CreateObject object) {
			    return "CREATE_OBJECT " + formatString(object.getObjectType())
			        + " " + formatString(object.getObjectName()); 	
			}
			
			@Override
			public Object caseRestoreObject(RestoreObject object) {
			    return "Restore_OBJECT " + formatString(object.getObjectType())
			        + " " + formatString(object.getObjectName());
			}
			
			@Override
			public Object caseCommand(Command object) {
				StringBuffer cmdStr = new StringBuffer("COMMAND ");
				if (object.isSetError()) {
					cmdStr.append("!Error=" + object.getError());
				}
				if (object.isSetAsyncError()) {
					cmdStr.append(" !AsyncError=" + object.getAsyncError());
				}
				cmdStr.append(" " + formatString(object.getObjectName())
						+ " " + formatString(object.getFunctionName()));
				if (object.getSection() != null) {
					cmdStr.append(" " + object.getSection().getName());
				}
			    return cmdStr.toString();
			}
		
			@Override
			public Object caseStore(Store object) {
				StringBuffer cmdStr = new StringBuffer("STORE ");
				if (object.getSection() == null) {
					cmdStr.append("null");
				} else {
				    cmdStr.append(formatString(object.getSection().getName()));
				}
				return cmdStr.toString();
			}
			
			@Override
			public Object caseOutstanding(Outstanding object) {
				StringBuffer cmdStr = new StringBuffer("OUTSTANDING ");
				if (object.isSetPollInterval()) {
					cmdStr.append(" " + object.getPollInterval());
				}
				if (object.getObjectName() != null 
						&& !object.getObjectName().equals("")) {
				    cmdStr.append(" " + object.getObjectName());
				}
				return cmdStr.toString();
			}
			
			@Override
			public Object caseAsyncDelay(AsyncDelay object) {
				return "ASYNC_DELAY " + object.getTimeout();
			}
			
			@Override
			public Object caseSharedActiveScheduler(SharedActiveScheduler object) {
				return "SHARED_ACTIVE_SCHEDULER";
			}
			
			@Override
			public Object caseStoreActiveScheduler(StoreActiveScheduler object) {
				return "STORE_ACTIVE_SCHEDULER";
			}
			
			@Override
			public Object caseTestBlock(TestBlock object) {
				StringBuffer block = new StringBuffer("\r\nSTART_TEST_BLOCK");
				if (object.isSetHeap()) {
					if (object.getHeap().intValue() < 256) {
						block.append(" !Heap=" + 256);
					} else {
					    block.append(" !Heap=" + object.getHeap());
					}
				}
				block.append(" " + object.getTimeout());
				block.append(" " + formatString(object.getServer()));
				block.append(" " + formatString(object.getIniFile()));
				return block.append("\r\n").toString();
			}
			
			protected String formatString(String input) {
				if (input == null) {
					return "null";
				}
			    input = input.trim();
				if (input.equals("")) {
					return "null";
				}
				return input;
			}
		};
		
		
	
		String lOutput="";
		//get the root of a model
		ContentsEList modelList = (ContentsEList)getContents();
		//the root of script (SHOULD BE TefModel)
		TefModel model = (TefModel)modelList.get(0);
		EList children = model.getTef();
		
		Iterator childrenIter = children.iterator();
		while(childrenIter.hasNext())
		{				
			Tef tef = (Tef)childrenIter.next();
			lOutput+=printTree(tef,lScriptSwitch);		
		}
	
		lPrintWriter.println(lOutput);

		lPrintWriter.flush();
		lPrintWriter.close();
	}

	/**
	 * parse tef script model to string
	 * @param tree a tef script tree node
	 * @param lScriptSwitch
	 * @return the string be parsed form tef model
	 */
    public String printTree(Tef tree,ScriptSwitch lScriptSwitch)
    {
    	String lOutput = "";
    
    	
    		lOutput += (String) lScriptSwitch.doSwitch((EObject)tree);
    		lOutput +="\r\n";
    		if(tree instanceof Container )
    		{
    			
    			Container container =(Container)tree;
    			EList children = container.getTef();
    			Iterator childrenIter = children.iterator();
    			while(childrenIter.hasNext())
    			{   				
    					lOutput += printTree((Tef)childrenIter.next(),lScriptSwitch);
    					lOutput +="\r\n";   			
    			}
    			    			
    			if (tree instanceof TestCase) {
        			lOutput += ("END_TESTCASE " + ((TestCase) tree).getName() + "\r\n");
    			} else if (tree instanceof Prefix) {
    				lOutput += ("REMOVE_PREFIX\r\n");
    			} else if (tree instanceof Repeat) {
    				lOutput += ("END_REPEAT\r\n");
    			} else if (tree instanceof Repeat) {
    				lOutput += ("END_REPEAT\r\n");
    			} else if (tree instanceof TestBlock) {
					TestBlock block = (TestBlock)tree;
					StringBuffer sb = new StringBuffer("END_TEST_BLOCK");
					if (block.isSetPanicCode()) {
						sb.append(" !PanicCode=" + block.getPanicCode());
					}
					if (block.isSetPanicString() && block.getPanicString() != null
							&& !block.getPanicString().equals("")) {
						sb.append(" !PanicString=" + block.getPanicString());
					}
					lOutput += sb.append("\r\n").toString();
				}
    			
    		}
    		
    	
    	return lOutput;
    }
    /**
     * @deprecated
     */
	public void caseContainer(Container object, Stack<Container> aContainerStack) {

	}
    /**
     * @deprecated
     * @param object
     * @return
     */
	public String endContainer(EObject object) {
		if (object.eContainer() instanceof Container) {
			Container lContainer = (Container) object.eContainer();
			EList lList = lContainer.getTef();
			if (lList.indexOf(object) == lList.size() - 1) {
				if (lContainer instanceof TestCase) {
					return "END_TESTCASE " + ((TestCase) lContainer).getName() + "\r\n";
				} else if (lContainer instanceof Prefix) {
					return "REMOVE_PREFIX\r\n";
				} else if (lContainer instanceof Repeat) {
					return "END_REPEAT\r\n";
				} else if (lContainer instanceof TestBlock) {
					TestBlock block = (TestBlock)lContainer;
					StringBuffer sb = new StringBuffer("END_TEST_BLOCK");
					if (block.isSetPanicCode()) {
						sb.append(" !PanicCode=" + block.getPanicCode());
					}
					if (block.isSetPanicString() && block.getPanicString() != null) {
						sb.append(" !PanicString=" + block.getPanicString());
					}
					return sb.append("\r\n").toString();
				}
				
			}
		}

		return null;
	}

} // ScriptResourceImpl
