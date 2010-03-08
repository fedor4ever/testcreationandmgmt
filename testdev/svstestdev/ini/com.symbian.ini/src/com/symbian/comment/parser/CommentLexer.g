header {
package com.symbian.comment.parser;

import java.util.ArrayList;
import java.util.List;

import com.symbian.ini.util.WrappedResourceDiagnostic;

import antlr.TokenStreamSelector;
}

class CommentLexer extends Lexer;

options {
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

// Whitespace -- ignored
WS
	: ( ' ' {$setType(Token.SKIP);}
		| '\t' {$setType(Token.SKIP);}
	)+ 
	;

TAG
	: '!'! (' '!|'\t'!)* ( 
	lTagKey:TAG_KEY					{$setToken(lTagKey);}
	| lTagValue:TAG_TRAILING_VALUE	{$setToken(lTagValue);}
	)
	;
	
private TAG_KEY
	{
		CommentTagToken lCommentTokenTag = new CommentTagToken();
	}
	: '@'! lTagKey:ID (lTagValue:STRING)?
	{
		lCommentTokenTag.setTag(lTagKey.getText());
		lCommentTokenTag.setValue(lTagValue.getText());
		$setToken(lCommentTokenTag);
	}
	;

private TAG_TRAILING_VALUE
	{
		CommentTagToken lCommentTokenTag = new CommentTagToken();
	}
	: lTagValue:STRING
	{
		lCommentTokenTag.setTrailingValue(lTagValue.getText());
		$setToken(lCommentTokenTag);
	}
	;

EOL
	options {
		generateAmbigWarnings=false;
	}
	: ('\n'!|'\r'!('\n'!)?)
	{
		newline();
		sSelector.pop();
	}
	;

STRING
	: (' '!|'\t'!)* (~('\n'|'\r'))* EOL!
	;

private ID
	: (' '!|'\t'!)* (~('\n'|'\r'|' '|'\t'))+
	;
 
