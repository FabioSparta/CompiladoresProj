// Generated from /home/joaots/Desktop/Compiladores/Prática/Trabalho Final/comp1920-g08/Projeto_C/EncryptConfig.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EncryptConfigParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, ID=13, STRING=14, INTEGER=15;
	public static final int
		RULE_stat2 = 0, RULE_assignmentC = 1, RULE_declarationC = 2, RULE_loadAlg = 3, 
		RULE_cripto = 4, RULE_encryptAlg = 5, RULE_decryptAlg = 6, RULE_put = 7, 
		RULE_exprC = 8;
	public static final String[] ruleNames = {
		"stat2", "assignmentC", "declarationC", "loadAlg", "cripto", "encryptAlg", 
		"decryptAlg", "put", "exprC"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'<<'", "'('", "'cypher'", "')'", "'.load('", "'.encrypt('", "'.decrypt('", 
		"'alg'", "':'", "'mode'", "'k'", "'padding'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "ID", "STRING", "INTEGER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "EncryptConfig.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EncryptConfigParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Stat2Context extends ParserRuleContext {
		public DeclarationCContext declarationC() {
			return getRuleContext(DeclarationCContext.class,0);
		}
		public AssignmentCContext assignmentC() {
			return getRuleContext(AssignmentCContext.class,0);
		}
		public PutContext put() {
			return getRuleContext(PutContext.class,0);
		}
		public LoadAlgContext loadAlg() {
			return getRuleContext(LoadAlgContext.class,0);
		}
		public CriptoContext cripto() {
			return getRuleContext(CriptoContext.class,0);
		}
		public Stat2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat2; }
	}

	public final Stat2Context stat2() throws RecognitionException {
		Stat2Context _localctx = new Stat2Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_stat2);
		try {
			setState(23);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				declarationC();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				assignmentC();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(20);
				put();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(21);
				loadAlg();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(22);
				cripto();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentCContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EncryptConfigParser.ID, 0); }
		public List<ExprCContext> exprC() {
			return getRuleContexts(ExprCContext.class);
		}
		public ExprCContext exprC(int i) {
			return getRuleContext(ExprCContext.class,i);
		}
		public AssignmentCContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentC; }
	}

	public final AssignmentCContext assignmentC() throws RecognitionException {
		AssignmentCContext _localctx = new AssignmentCContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assignmentC);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(ID);
			setState(26);
			match(T__0);
			setState(27);
			exprC();
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(28);
					match(T__0);
					setState(29);
					exprC();
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationCContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EncryptConfigParser.ID, 0); }
		public AssignmentCContext assignmentC() {
			return getRuleContext(AssignmentCContext.class,0);
		}
		public DeclarationCContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationC; }
	}

	public final DeclarationCContext declarationC() throws RecognitionException {
		DeclarationCContext _localctx = new DeclarationCContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declarationC);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(T__1);
			setState(36);
			match(T__2);
			setState(37);
			match(T__3);
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(38);
				match(ID);
				}
				break;
			case 2:
				{
				setState(39);
				assignmentC();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoadAlgContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EncryptConfigParser.ID, 0); }
		public TerminalNode STRING() { return getToken(EncryptConfigParser.STRING, 0); }
		public LoadAlgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loadAlg; }
	}

	public final LoadAlgContext loadAlg() throws RecognitionException {
		LoadAlgContext _localctx = new LoadAlgContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_loadAlg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(ID);
			setState(43);
			match(T__4);
			setState(44);
			match(STRING);
			setState(45);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CriptoContext extends ParserRuleContext {
		public EncryptAlgContext encryptAlg() {
			return getRuleContext(EncryptAlgContext.class,0);
		}
		public DecryptAlgContext decryptAlg() {
			return getRuleContext(DecryptAlgContext.class,0);
		}
		public CriptoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cripto; }
	}

	public final CriptoContext cripto() throws RecognitionException {
		CriptoContext _localctx = new CriptoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cripto);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				encryptAlg();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				decryptAlg();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EncryptAlgContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(EncryptConfigParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(EncryptConfigParser.ID, i);
		}
		public TerminalNode STRING() { return getToken(EncryptConfigParser.STRING, 0); }
		public EncryptAlgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_encryptAlg; }
	}

	public final EncryptAlgContext encryptAlg() throws RecognitionException {
		EncryptAlgContext _localctx = new EncryptAlgContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_encryptAlg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(ID);
			setState(52);
			match(T__5);
			setState(53);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(54);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecryptAlgContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(EncryptConfigParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(EncryptConfigParser.ID, i);
		}
		public TerminalNode STRING() { return getToken(EncryptConfigParser.STRING, 0); }
		public DecryptAlgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decryptAlg; }
	}

	public final DecryptAlgContext decryptAlg() throws RecognitionException {
		DecryptAlgContext _localctx = new DecryptAlgContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_decryptAlg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(ID);
			setState(57);
			match(T__6);
			setState(58);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(59);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PutContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EncryptConfigParser.ID, 0); }
		public ExprCContext exprC() {
			return getRuleContext(ExprCContext.class,0);
		}
		public PutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_put; }
	}

	public final PutContext put() throws RecognitionException {
		PutContext _localctx = new PutContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_put);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(ID);
			setState(62);
			match(T__1);
			setState(63);
			exprC();
			setState(64);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprCContext extends ParserRuleContext {
		public ExprCContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprC; }
	 
		public ExprCContext() { }
		public void copyFrom(ExprCContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ModeExprContext extends ExprCContext {
		public Token n;
		public TerminalNode ID() { return getToken(EncryptConfigParser.ID, 0); }
		public ModeExprContext(ExprCContext ctx) { copyFrom(ctx); }
	}
	public static class PaddingExprContext extends ExprCContext {
		public Token n;
		public TerminalNode ID() { return getToken(EncryptConfigParser.ID, 0); }
		public PaddingExprContext(ExprCContext ctx) { copyFrom(ctx); }
	}
	public static class AlgExprContext extends ExprCContext {
		public Token n;
		public TerminalNode ID() { return getToken(EncryptConfigParser.ID, 0); }
		public AlgExprContext(ExprCContext ctx) { copyFrom(ctx); }
	}
	public static class KeyExprContext extends ExprCContext {
		public Token n;
		public TerminalNode INTEGER() { return getToken(EncryptConfigParser.INTEGER, 0); }
		public KeyExprContext(ExprCContext ctx) { copyFrom(ctx); }
	}
	public static class AnyExprContext extends ExprCContext {
		public List<TerminalNode> ID() { return getTokens(EncryptConfigParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(EncryptConfigParser.ID, i);
		}
		public TerminalNode INTEGER() { return getToken(EncryptConfigParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(EncryptConfigParser.STRING, 0); }
		public AnyExprContext(ExprCContext ctx) { copyFrom(ctx); }
	}

	public final ExprCContext exprC() throws RecognitionException {
		ExprCContext _localctx = new ExprCContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_exprC);
		int _la;
		try {
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				_localctx = new AlgExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				((AlgExprContext)_localctx).n = match(T__7);
				setState(67);
				match(T__8);
				setState(68);
				match(ID);
				}
				break;
			case T__9:
				_localctx = new ModeExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				((ModeExprContext)_localctx).n = match(T__9);
				setState(70);
				match(T__8);
				setState(71);
				match(ID);
				}
				break;
			case T__10:
				_localctx = new KeyExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				((KeyExprContext)_localctx).n = match(T__10);
				setState(73);
				match(T__8);
				setState(74);
				match(INTEGER);
				}
				break;
			case T__11:
				_localctx = new PaddingExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				((PaddingExprContext)_localctx).n = match(T__11);
				setState(76);
				match(T__8);
				setState(77);
				match(ID);
				}
				break;
			case ID:
				_localctx = new AnyExprContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				match(ID);
				setState(79);
				match(T__8);
				setState(80);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << STRING) | (1L << INTEGER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21V\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\3\2\5\2\32\n\2\3\3\3\3\3\3\3\3\3\3\7\3!\n\3\f\3\16\3$\13\3\3\4\3"+
		"\4\3\4\3\4\3\4\5\4+\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\5\6\64\n\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nT\n\n\3\n\3\"\2\13\2\4\6"+
		"\b\n\f\16\20\22\2\4\3\2\17\20\3\2\17\21\2W\2\31\3\2\2\2\4\33\3\2\2\2\6"+
		"%\3\2\2\2\b,\3\2\2\2\n\63\3\2\2\2\f\65\3\2\2\2\16:\3\2\2\2\20?\3\2\2\2"+
		"\22S\3\2\2\2\24\32\5\6\4\2\25\32\5\4\3\2\26\32\5\20\t\2\27\32\5\b\5\2"+
		"\30\32\5\n\6\2\31\24\3\2\2\2\31\25\3\2\2\2\31\26\3\2\2\2\31\27\3\2\2\2"+
		"\31\30\3\2\2\2\32\3\3\2\2\2\33\34\7\17\2\2\34\35\7\3\2\2\35\"\5\22\n\2"+
		"\36\37\7\3\2\2\37!\5\22\n\2 \36\3\2\2\2!$\3\2\2\2\"#\3\2\2\2\" \3\2\2"+
		"\2#\5\3\2\2\2$\"\3\2\2\2%&\7\4\2\2&\'\7\5\2\2\'*\7\6\2\2(+\7\17\2\2)+"+
		"\5\4\3\2*(\3\2\2\2*)\3\2\2\2+\7\3\2\2\2,-\7\17\2\2-.\7\7\2\2./\7\20\2"+
		"\2/\60\7\6\2\2\60\t\3\2\2\2\61\64\5\f\7\2\62\64\5\16\b\2\63\61\3\2\2\2"+
		"\63\62\3\2\2\2\64\13\3\2\2\2\65\66\7\17\2\2\66\67\7\b\2\2\678\t\2\2\2"+
		"89\7\6\2\29\r\3\2\2\2:;\7\17\2\2;<\7\t\2\2<=\t\2\2\2=>\7\6\2\2>\17\3\2"+
		"\2\2?@\7\17\2\2@A\7\4\2\2AB\5\22\n\2BC\7\6\2\2C\21\3\2\2\2DE\7\n\2\2E"+
		"F\7\13\2\2FT\7\17\2\2GH\7\f\2\2HI\7\13\2\2IT\7\17\2\2JK\7\r\2\2KL\7\13"+
		"\2\2LT\7\21\2\2MN\7\16\2\2NO\7\13\2\2OT\7\17\2\2PQ\7\17\2\2QR\7\13\2\2"+
		"RT\t\3\2\2SD\3\2\2\2SG\3\2\2\2SJ\3\2\2\2SM\3\2\2\2SP\3\2\2\2T\23\3\2\2"+
		"\2\7\31\"*\63S";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}