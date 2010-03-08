header {
package com.symbian.ini.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import antlr.TokenStreamSelector;

import com.symbian.comment.Comment;
import com.symbian.comment.parser.CommentUtils;
import com.symbian.ini.Data;
import com.symbian.ini.IniFactory;
import com.symbian.ini.IniModel;
import com.symbian.ini.IniPackage;
import com.symbian.ini.Section;
import com.symbian.ini.impl.IniFactoryImpl;
import com.symbian.ini.util.WrappedResourceDiagnostic;
}

/////////////////////////////////////////////////////
//----------------------------------------------------------------------------
// The parser
//----------------------------------------------------------------------------
class IniParser extends Parser;

options {
	defaultErrorHandler = true;
	importVocab = CommentLexer;
}

{
	/** Factory for EMF Ini Files **/
	protected static final IniFactoryImpl INI_FACTORY = (IniFactoryImpl) IniFactory.eINSTANCE;
	
	/** Collection of Recognition Exceptions **/
	private List<WrappedResourceDiagnostic> iExceptions = new ArrayList<WrappedResourceDiagnostic>();

	@Override
	public void reportError(RecognitionException aRecognitionException) {
		super.reportError(aRecognitionException);

		if (!aRecognitionException.getMessage().contains("0xFFFF")) {
			WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(aRecognitionException)
				.setColumn(aRecognitionException.getColumn())
				.setLine(aRecognitionException.getLine())
				.setLocation(aRecognitionException.getFilename());
	
			iExceptions.add(lDiagnostic);
		}
	}

	/** Returns all exceptions that occured during parsing.
	 * 
	 * @return The list of wrapped exceptions that occured during parsing
	 */
	public List<WrappedResourceDiagnostic> getExceptions() {
		return iExceptions;
	}
}
	
ini returns [IniModel lIni = INI_FACTORY.createIniModel()]
	{
		Section lSection = null;
		Data lData = null;
		Comment lComment = null;
	}
	: ( (EOF) => {break;} 
	| lSection=section {lIni.getIni().add(lSection);}
	| comment[lIni]
	| lData=data {lIni.getIni().add(lData);})*
	EOF
	;
	
section returns [Section lSection = INI_FACTORY.createSection()]
	{
		Data lData = null;
	}
	: LEFT_SQ_BRAKET! lName:ID RIGHT_SQ_BRAKET! {lSection.setName(lName.getText());}
		( (LEFT_SQ_BRAKET | EOF) => {break;}
		| comment[lSection]
		| lData=data {lSection.getIniLeaf().add(lData);})*
	;
	
data returns [Data lDataMap = INI_FACTORY.createData()]
	: lKey:ID lValue:VALUE
	{
		lDataMap.setKey(lKey.getText());
		lDataMap.setValue(lValue.getText());
	}
	;



comment [EObject aEObject]
	: lCommentToken:COMMENT 
	{
		EStructuralFeature lFeature = null;
		if (aEObject instanceof Section) {
			lFeature = IniPackage.Literals.SECTION__INI_LEAF;
		} else if (aEObject instanceof IniModel) {
			lFeature = IniPackage.Literals.INI_MODEL__INI;
		}
		
		CommentUtils.setComment(aEObject, INI_FACTORY.createIniComment(), lCommentToken, lFeature);
	}
	;
	
/////////////////////////////////////////////////////
//----------------------------------------------------------------------------
// The lexer
//----------------------------------------------------------------------------
class IniLexer extends Lexer;

options {
	k = 2;
	charVocabulary = '\u0000'..'\uFFFE'; // Allow everything but \uFFFF
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
		
		if (!aRecognitionException.getMessage().contains("0xFFFF")) {
			WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(aRecognitionException)
				.setColumn(aRecognitionException.getColumn())
				.setLine(aRecognitionException.getLine())
				.setLocation(aRecognitionException.getFilename());
	
			iExceptions.add(lDiagnostic);
		}
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

LEFT_SQ_BRAKET	: '[';
RIGHT_SQ_BRAKET	: ']';
FORWARD_SLASH	: '/';
STAR			: '*';
	
VALUE
	{
		StringBuffer lValueBuffer = new StringBuffer();
		int line, column = 0;
	}
	: '='! lValue:STRING {lValueBuffer.append(lValue.getText());}
	({
		boolean lIsEqual = false;
		for (int lLookAhead = 1; lLookAhead < 50; lLookAhead++) {
			try {
				if (LA(lLookAhead) == '=') {
					lIsEqual = true;
				}
			} catch (Throwable lThrowable) {
				break;
			}
		}

		if (LA(1) == '[' || LA(1) == '/' || lIsEqual) {
			break;
		}
	}
	lOtherValue:STRING
	{
		lValueBuffer.append("\n" + lOtherValue.getText());
	})*
	{
		$setText(lValueBuffer.toString());
	}
	;

COMMENT
	: ("//"!|"/*")
	{
		sSelector.push("CommentLexer");
		$setToken(sSelector.nextToken());
		_token.setType(_ttype);
	}
	;
	
WS
	: ( ' '
		| '\t'
		| '\n' { newline(); }
		| '\r' ('\n')?	{ newline(); }
	)+ {$setType(Token.SKIP);}
	;
	
ID
//	: ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|':'|'+'|'@'|'\\'|'/'|'!'|','|'.'|'%'|'&'|'-'|';'|'?'|'"'|'\''|'`')+
	: (~(' '|'\t'|'\r'|'\n'|']'|'['|'='))+
	;
	
private STRING
	options {
		testLiterals = true;
	}
	: (' '!|'\t'!)*(~('\n'|'\r'))*('\n'!|'\r'!('\n'!)?) {newline();}
	;
