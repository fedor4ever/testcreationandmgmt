header{ 	
package com.symbian.tef.script.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import antlr.TokenStreamSelector;

import com.symbian.comment.parser.CommentUtils;
import com.symbian.ini.util.WrappedResourceDiagnostic;
import com.symbian.tef.script.Concurrent;
import com.symbian.tef.script.Container;
import com.symbian.tef.script.Consecutive;
import com.symbian.tef.script.Delay;
import com.symbian.tef.script.LoadServer;
import com.symbian.tef.script.LoadSuite;
import com.symbian.tef.script.Pause;
import com.symbian.tef.script.Prefix;
import com.symbian.tef.script.Print;
import com.symbian.tef.script.Repeat;
import com.symbian.tef.script.RunProgram;
import com.symbian.tef.script.RunScript;
import com.symbian.tef.script.RunUtils;
import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.SharedData;
import com.symbian.tef.script.Tef;
import com.symbian.tef.script.TefModel;
import com.symbian.tef.script.TestCase;
import com.symbian.tef.script.TestStep;
import com.symbian.tef.script.TestBlock;
import com.symbian.tef.script.CreateObject;
import com.symbian.tef.script.RestoreObject;
import com.symbian.tef.script.Command;
import com.symbian.tef.script.Store;
import com.symbian.tef.script.Outstanding;
import com.symbian.tef.script.AsyncDelay;
import com.symbian.tef.script.SharedActiveScheduler;
import com.symbian.tef.script.StoreActiveScheduler;
import com.symbian.tef.script.UtilityCommand;
import com.symbian.tef.script.impl.ScriptFactoryImpl;
import com.symbian.tef.script.util.IniUtils;
import com.symbian.tef.script.util.ScriptResourceImpl;
import com.symbian.ini.*;

}

/////////////////////////////////////////////////////
//----------------------------------------------------------------------------
// The parser
//----------------------------------------------------------------------------
class ScriptParser extends Parser;

options {
	defaultErrorHandler = true;
	importVocab = CommentLexer;
	k=3;
}

{
	/** Script EMF Factory **/
	protected static final ScriptFactoryImpl SCRIPT_FACTORY = (ScriptFactoryImpl) ScriptFactoryImpl.eINSTANCE;
	
	/** Collection of Recognition Exceptions **/
	private List<WrappedResourceDiagnostic> iExceptions = new ArrayList<WrappedResourceDiagnostic>();

	/** Resource **/
	private ScriptResourceImpl iResource;

	/** cur testblock **/
	private TestBlock curTestBlock;

	@Override
	public void reportError(RecognitionException aRecognitionException) {
		super.reportError(aRecognitionException);

		WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(aRecognitionException)
			.setColumn(aRecognitionException.getColumn())
			.setLine(aRecognitionException.getLine())
			.setLocation(aRecognitionException.getFilename());

		iExceptions.add(lDiagnostic);
	}

	/** Returns all exceptions that occured during parsing.
	 * 
	 * @return The list of wrapped exceptions that occured during parsing
	 */
	public List<WrappedResourceDiagnostic> getExceptions() {
		return iExceptions;
	}
	
	/** Sets the EMF resource for this Parser
	 * @param aResource
	 */
	public void setResource(ScriptResourceImpl aResource) {
		iResource = aResource;
	}

}


model returns [TefModel lTefModel = SCRIPT_FACTORY.createTefModel()]
	{
		Tef lTef;
	}
	: ((EOF) => {break;}
	| lTef=tef[lTefModel]
	{
		if (lTef != null) {
			lTefModel.getTef().add(lTef);
		}
	})*
	EOF
	;

tef [EObject aEObject] returns [Tef lTef = null]
	{
		int lPos = 0;
	}
	: (lTef=container[aEObject]
	| lTef=leaf[aEObject]
	| {lPos = mark();} (END_TESTCASE | REMOVE_PREFIX | END_REPEAT | EOF) 
	{
		if (aEObject.eContainer() instanceof Container) {
			rewind(lPos);
		}
	})
	;

container[EObject aEObject] returns [Tef lTef = null] 
	: lTef=testCase
	| lTef=prefix
	| lTef=repeat
	| lTef=testBlock

	;

leaf [EObject aEObject] returns [Tef lTef = null] 
	: lTef=testStep
	| lTef=print
	| lTef=loadSuite
	| lTef=loadServer
	| lTef=runUtils
	| lTef=runProgram
	| lTef=runScript
	| lTef=pause
	| lTef=delay
	| lTef=consecutive
	| lTef=concurrent
	| lTef=sharedData
	| lTef=create_object
	| lTef=restore_object
	| lTef=store
	| lTef=outstanding
	| lTef=async_delay
	| lTef=shared_active_scheduler
	| lTef=store_active_scheduler
	| lTef=command
	| comment[aEObject]

		
;

testCase returns [TestCase lTestCase = SCRIPT_FACTORY.createTestCase()]
	{
		Tef lTef;
	}
	: START_TESTCASE! lTestCaseStartName:ID
	( EOF! {break;}
	| END_TESTCASE! lTestCaseEndName:ID 
		{
			if (lTestCaseStartName.getText().equalsIgnoreCase(lTestCaseEndName.getText())) {
				break;
			}
		}
	| lTef=tef[lTestCase]
		{
			if (lTef != null) {
				lTestCase.getTef().add(lTef);
			}
		}
		)*
	{
		lTestCase.setName(lTestCaseStartName.getText());
	}
	;

testBlock returns [TestBlock lTestBlock = SCRIPT_FACTORY.createTestBlock()]
	{
		Tef lTef;
		curTestBlock = lTestBlock;
	}
	: START_TEST_BLOCK! (EXLAMATION! "Heap"! EQ! lHeap:ID)? lTimeOut:ID lServername:ID lIniFile:ID{lTestBlock.setIniFile(lIniFile.getText()); IniUtils.loadIni(lIniFile.getText(), iResource);}
	( EOF! {break;}
	| END_TEST_BLOCK! (
				  (EXLAMATION "PanicString" EQ lPanicString:ID) 
					{
						if(lPanicString!=null)
						{
							lTestBlock.setPanicString(lPanicString.getText());
						}

					}
				| (EXLAMATION "PanicCode" EQ lPanicCode:ID)
					{
						if(lPanicCode!=null)
						{
							lTestBlock.setPanicCode(Integer.parseInt(lPanicCode.getText()));
						}

					}
			  )*
			  {
			//	lIniFileStack.pop();
				break;
			  }
	| lTef=tef[lTestBlock]
		{
			if (lTef != null) {
				lTestBlock.getTef().add(lTef);
			}
		}
		)*
	{
		lTestBlock.setTimeout(Integer.parseInt(lTimeOut.getText()));
		if(lHeap!=null)
		{
			lTestBlock.setHeap(Integer.parseInt(lHeap.getText()));
		}
		lTestBlock.setServer(lServername.getText());
			
	//	lIniFileStack.push(lIniFile.getText());
	}
	; 	



prefix returns [Prefix lPrefix = SCRIPT_FACTORY.createPrefix()] 
	{
		Tef lTef;
	}
	: PREFIX!
	( (EOF! | REMOVE_PREFIX!) {break;}
	| lTef=tef[lPrefix]
		{
			if (lTef != null) {
				lPrefix.getTef().add(lTef);
			}
		})*
	;

repeat returns [Repeat lRepeat = SCRIPT_FACTORY.createRepeat()]
	{
		Tef lTef;
	}
	: START_REPEAT! lIni:ID  lSection:ID lName:ID
	( (EOF! | END_REPEAT!) {break;}
	| lTef=tef[lRepeat]
		{
			if (lTef != null) {
				lRepeat.getTef().add(lTef);
			}
		})*
	{
		IniUtils.getSection(lIni.getText(), lSection.getText(), lRepeat, iResource);
		lRepeat.setName(lName.getText());
	}
	;

testStep returns [TestStep lTestStep = SCRIPT_FACTORY.createTestStep()]
	: RUN_TEST_STEP!
	(negativeTest[lTestStep])* lTimeoutString:ID
	{
		try {
			lTestStep.setTimeout(Integer.parseInt(lTimeoutString.getText()));
		} catch (NumberFormatException lNumberFormatException) {
			throw new RecognitionException("Timeout could not be parsed to integer",
				LT(1).getFilename(),
				LT(1).getLine(),
				LT(1).getColumn());
		}
	}
	lServer:ID lMethod:ID (lIniToken:ID lSectionToken:ID
	{
		IniUtils.getSection(lIniToken.getText(), lSectionToken.getText(), lTestStep, iResource);
	})?
	{
		lTestStep.setServer(lServer.getText());
		lTestStep.setMethod(lMethod.getText());
	}
	;

negativeTest [TestStep aTestStep]
	: EXLAMATION!
	( "Error" EQ! lErrorValue:ID {aTestStep.setError(lErrorValue.getText());}
	| "PanicString" EQ! lPanicStringValue:ID {aTestStep.setPanicString(lPanicStringValue.getText());}
	| "PanicCode" EQ! lPanicCodeValue:ID
	{
		try {
			aTestStep.setPanicCode(Integer.parseInt(lPanicCodeValue.getText()));
		} catch (NumberFormatException aNumberFormatException) {
			throw new RecognitionException("Panic code could not be parsed to integer",
				LT(1).getFilename(),
				LT(1).getLine(),
				LT(1).getColumn());
		}
	}
	| "Result" EQ! lResultValue:ID {aTestStep.setResult(lResultValue.getText());}
	| "Heap" EQ! lHeapValue:ID
	{
		try {
			aTestStep.setHeap(Integer.parseInt(lHeapValue.getText()));
		} catch (NumberFormatException aNumberFormatException) {
			throw new RecognitionException("Heap could not be parsed to integer",
				LT(1).getFilename(),
				LT(1).getLine(),
				LT(1).getColumn());
		}
	}
	| "OOM" EQ! lOomValue:ID
	{
		try {
			aTestStep.setOOM(Integer.parseInt(lOomValue.getText()));
		} catch (NumberFormatException lNumberFormatException) {
			throw new RecognitionException("OOM could not be parsed to integer",
				LT(1).getFilename(),
				LT(1).getLine(),
				LT(1).getColumn());
		}
	})
	;

print returns [Print lPrint = SCRIPT_FACTORY.createPrint()]
	: lPrintToken:PRINT
	{
		lPrint.setPrint(lPrintToken.getText());
	}
	;

loadSuite returns [LoadSuite lLoadSuite = SCRIPT_FACTORY.createLoadSuite()]
	: LOAD_SUITE! lServer:ID (SHARED_DATA_DASH {lLoadSuite.setSharedData(true);})?
	{
		lLoadSuite.setServer(lServer.getText());
	}
	;

loadServer returns [LoadServer lLoadServer = SCRIPT_FACTORY.createLoadServer()]
	: LOAD_SERVER! lServer:ID (SHARED_DATA_DASH {lLoadServer.setSharedData(true);})?
	{
		lLoadServer.setServer(lServer.getText());
	}
	;

runUtils returns [RunUtils lRunUtils = SCRIPT_FACTORY.createRunUtils()]
	: RUN_UTILS! 
	lName:ID  {
		UtilityCommand lCommand = UtilityCommand.getByName(lName.getText());
		if (lCommand != null) {
			lRunUtils.setUtilityCommand(lCommand);
		}
	}
	( ("\n" | "\r") => {break;}
	| lParam:ID {lRunUtils.getParam().add(lParam.getText());})+
	;

runProgram returns [RunProgram lRunProgram = SCRIPT_FACTORY.createRunProgram()]
	{
		StringBuffer lNameBuffer = new StringBuffer();
	}
	: lRunProgramToken:RUN_PROGRAM! lTimeout:ID
	((EOL) => {break;}
	| lName:ID {lNameBuffer.append(lName.getText() + " ");})+
	{
		lRunProgram.setName(lNameBuffer.toString());
		if (lRunProgramToken.getText().contains("ws")) {
			lRunProgram.setWS(true);
		}
		try {
			lRunProgram.setTimeout(Integer.parseInt(lTimeout.getText()));
		} catch (NumberFormatException lNumberFormatException) {
			throw new RecognitionException("Timeout could not be parsed to integer",
				LT(1).getFilename(),
				LT(1).getLine(),
				LT(1).getColumn());
		}
	}
	;

runScript returns [RunScript lRunScript = SCRIPT_FACTORY.createRunScript()]
	: RUN_SCRIPT! lScript:ID
	{
		IniUtils.getScript(lScript.getText(), lRunScript, iResource);
	}
	;

pause returns [Pause lPause = SCRIPT_FACTORY.createPause()]
	: PAUSE!
	;

delay returns [Delay lDelay = SCRIPT_FACTORY.createDelay()]
	: DELAY! lTimeoutString:ID
	{
		try {
			int lTimeout = Integer.parseInt(lTimeoutString.getText());
			lDelay.setTimeout(lTimeout);
		} catch (NumberFormatException lNumberFormatException) {
			// Do nothing
		}
	}
	;

consecutive returns [Consecutive lConsecutive = SCRIPT_FACTORY.createConsecutive()]
	: CONSECUTIVE!
	;

concurrent returns [Concurrent lConcurrent = SCRIPT_FACTORY.createConcurrent()]
	: CONCURRENT!
	;

sharedData returns [SharedData lSharedData = SCRIPT_FACTORY.createSharedData()]
	: SHARED_DATA! lIni:ID lSection:ID
	{
		IniUtils.getSection(lIni.getText(), lSection.getText(), lSharedData, iResource);
	}
	;

create_object returns [CreateObject lCreateObject =SCRIPT_FACTORY.createCreateObject()]
	: CREATE_OBJECT! lObjectType:ID lObjectName:ID
	{
		lCreateObject.setObjectName(lObjectName.getText());
		lCreateObject.setObjectType(lObjectType.getText());
	}
	;
	
restore_object returns [RestoreObject lRestoreObject = SCRIPT_FACTORY.createRestoreObject()]
	: RESTORE_OBJECT! lObjectType:ID lObjectName:ID
	{
		lRestoreObject.setObjectName(lObjectName.getText());
		lRestoreObject.setObjectType(lObjectType.getText());
	}
	;

store returns [Store lStore = SCRIPT_FACTORY.createStore()]
	: STORE! lSection:ID
	{
		Section section = IniUtils.getSection(curTestBlock.getIniFile(), lSection.getText(), iResource);
		if (section != null) 
		{
			lStore.setSection(section);
		}		
	}
	;

outstanding returns [Outstanding lOutstanding =SCRIPT_FACTORY.createOutstanding()]
	{
		int arguCount=0;
	}
	: 
	OUTSTANDING! (lArgu:ID {
					arguCount++;
					if(arguCount==1)
					{
							lOutstanding.setPollInterval(Integer.parseInt(lArgu.getText()));
	
					}
					else if(arguCount==2)
					{
							lOutstanding.setObjectName(lArgu.getText());

					}
					else
					{
						break;
					}
				})* 
	;

async_delay returns [AsyncDelay lAsynDelay = SCRIPT_FACTORY.createAsyncDelay()]
	:ASYNC_DELAY! lTimeout:ID
	{
		lAsynDelay.setTimeout(Integer.parseInt(lTimeout.getText()));
	}
	;
command returns [Command lCommand = SCRIPT_FACTORY.createCommand()]
	: COMMAND! ((EXLAMATION "Error" EQ lError:ID) | (EXLAMATION "AsyncError" EQ lAsyncError:ID))*
	lObjectName:ID lFunctionName:ID (lSection:ID)?
	{
		lCommand.setObjectName(lObjectName.getText());
		lCommand.setFunctionName(lFunctionName.getText());
		if(lError!=null)
		{
			lCommand.setError(Integer.parseInt(lError.getText()));
		}
		if(lAsyncError!=null)
		{	
			lCommand.setAsyncError(Integer.parseInt(lAsyncError.getText()));
		}
		if (lSection != null) 
		{
			Section section = IniUtils.getSection(curTestBlock.getIniFile(), lSection.getText(), iResource);
			if (section != null) 
			{
				lCommand.setSection(section);
			}
		}
	}
	;
shared_active_scheduler returns [SharedActiveScheduler lSharedActiveScheduler = SCRIPT_FACTORY.createSharedActiveScheduler()]
	: SHARED_ACTIVE_SCHEDULER!
	;

store_active_scheduler returns [StoreActiveScheduler lStoreActiveScheduler = SCRIPT_FACTORY.createStoreActiveScheduler()]
	: STORE_ACTIVE_SCHEDULER!
	;
comment [EObject aEObject]
	: lCommentToken:COMMENT
	{
		EStructuralFeature lFeature = null;
		if (aEObject instanceof TefModel) {
			lFeature = ScriptPackage.Literals.TEF_MODEL__TEF;
		} else {
			lFeature = ScriptPackage.Literals.CONTAINER__TEF;
		}
		
		CommentUtils.setComment(aEObject,
				SCRIPT_FACTORY.createTefComment(),
				lCommentToken,
				lFeature);
	}
	;

/////////////////////////////////////////////////////
//----------------------------------------------------------------------------
// The lexer
//----------------------------------------------------------------------------
class ScriptLexer extends Lexer;

options {
	k = 7;                  				// lookahead
	charVocabulary = '\u0000'..'\uFFFE';	// Allow everything but \uFFFF
	codeGenBitsetTestThreshold = 20;
	testLiterals = false;    				// don't automatically test for literals
	caseSensitiveLiterals = false;
	caseSensitive = false;
	defaultErrorHandler = true;
}

{
	/** Collection of Recognition Exceptions **/
	private List<WrappedResourceDiagnostic> iExceptions = new ArrayList<WrappedResourceDiagnostic>();
	
	/** Stream Selector **/
	private TokenStreamSelector sSelector;
	
	/** Set the selector for this lexor so that the lexer can switch if needed.
	 * 
	 * @param selector The selector to choose the lexor
	 */
	public void setTokenStreamSelector(TokenStreamSelector selector) {
		sSelector = selector;
	}
	
	@Override
	public void reportError(RecognitionException aRecognitionException) {
		super.reportError(aRecognitionException);
		
		WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(aRecognitionException)
			.setColumn(aRecognitionException.getColumn())
			.setLine(aRecognitionException.getLine())
			.setLocation(aRecognitionException.getFilename());

		iExceptions.add(lDiagnostic);
	}
	
	/** Recover from a lexical error.
	 * 
	 * @param aRecognitionException The ANTLR Lexer exception.
	 * @param aBitSet The bitset related to this error.
	 * @throws CharStreamException If it failed to make the EOF token.
	 * @throws TokenStreamException If it failed to make the EOF token.
	 */
	private void recover(RecognitionException aRecognitionException, BitSet aBitSet) throws CharStreamException, TokenStreamException {
		if (LA(1) == '\uFFFF') {
			uponEOF();
			_returnToken = makeToken(Token.EOF_TYPE);
		}
	}
	
	/** Returns all exceptions that occured during lexing the inputstream.
	 * 
	 * @return A list of WrappedException from the lexor.
	 */
	public List<WrappedResourceDiagnostic> getExceptions() {
		return iExceptions;
	}
}

// Whitespace & EOL
WS
	: ( ' '
		| '\t'
		| '\n' { newline(); }
		| '\r' ('\n')?	{ newline(); }
	)+ {$setType(Token.SKIP);}
	;
	
EOL
	options {
		generateAmbigWarnings=false;
	}
	: ('\n'!|'\r'!('\n'!)?) {newline();}
	{
		$setType(Token.SKIP);
	}
	;
	
// Char Tokens
EQ			: '=';
EXLAMATION	: '!';
STAR		: '*';

	
// String Tokens
SHARED_DATA		: "shared_data";
SHARED_DATA_DASH: "-shareddata";
CONCURRENT		: "concurrent";
CONSECUTIVE		: "consecutive";
DELAY			: "delay";
PAUSE			: "pause";
RUN_SCRIPT		: "run_script";
RUN_PROGRAM		: "run_program" | "run_ws_program";
RUN_UTILS		: "run_utils";
LOAD_SERVER		: "load_server";
LOAD_SUITE		: "load_suite";
END_REPEAT		: "end_repeat";
PREFIX			: "prefix";
RUN_TEST_STEP	: "run_test_step";
START_REPEAT	: "start_repeat";
REMOVE_PREFIX	: "remove_prefix";
START_TESTCASE	: "start_testcase";
END_TESTCASE	: "end_testcase";

CREATE_OBJECT	: "create_object";
RESTORE_OBJECT	: "restore_object";
COMMAND		: "command";
STORE		: "store";
OUTSTANDING	: "outstanding";
ASYNC_DELAY	: "async_delay";
START_TEST_BLOCK: "start_test_block";
END_TEST_BLOCK	: "end_test_block";
SHARED_ACTIVE_SCHEDULER		: "shared_active_scheduler";
STORE_ACTIVE_SCHEDULER		: "store_active_scheduler";

PRINT
	: "print"! (
	(' '!|'\t'!)+ lPrint:STRING	{$setText(lPrint.getText());}
	| '\r'!
	| '\n'!)

	;
// Comments
COMMENT
	: ("//"!|"/*")
	{
		sSelector.push("CommentLexer");
		$setToken(sSelector.nextToken());
		_token.setType(_ttype);
	}
	;
	
// Utilities
ID
	options {
		testLiterals = true;
	}
	: ('a'..'z'|'0'..'9'|'.'|','|'-'|'_'|'['|']'|':'|'\\'|'/'|'*'|'~')+
	;
	
private STRING
	options {
		testLiterals = true;
	}
	: (' '!|'\t'!)* (~('\n'|'\r'))* EOL!
	;
