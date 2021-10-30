// Generated from /home/joaots/Desktop/Compiladores/Prática/Trabalho Final/comp1920-g08/Projeto_C/EncryptConfig.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EncryptConfigLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, ID=13, STRING=14, INTEGER=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "ID", "STRING", "INTEGER", "ESC"
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


	public EncryptConfigLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EncryptConfig.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21}\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\7\16d\n\16\f\16\16\16g\13"+
		"\16\3\17\3\17\3\17\7\17l\n\17\f\17\16\17o\13\17\3\17\3\17\3\20\6\20t\n"+
		"\20\r\20\16\20u\3\21\3\21\3\21\3\21\5\21|\n\21\3m\2\22\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\2\3\2\5\5"+
		"\2C\\aac|\6\2\62;C\\aac|\3\2\62;\2\u0080\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\3#\3\2\2\2\5&\3\2\2\2\7(\3\2\2\2\t/\3\2\2\2\13\61"+
		"\3\2\2\2\r8\3\2\2\2\17B\3\2\2\2\21L\3\2\2\2\23P\3\2\2\2\25R\3\2\2\2\27"+
		"W\3\2\2\2\31Y\3\2\2\2\33a\3\2\2\2\35h\3\2\2\2\37s\3\2\2\2!{\3\2\2\2#$"+
		"\7>\2\2$%\7>\2\2%\4\3\2\2\2&\'\7*\2\2\'\6\3\2\2\2()\7e\2\2)*\7{\2\2*+"+
		"\7r\2\2+,\7j\2\2,-\7g\2\2-.\7t\2\2.\b\3\2\2\2/\60\7+\2\2\60\n\3\2\2\2"+
		"\61\62\7\60\2\2\62\63\7n\2\2\63\64\7q\2\2\64\65\7c\2\2\65\66\7f\2\2\66"+
		"\67\7*\2\2\67\f\3\2\2\289\7\60\2\29:\7g\2\2:;\7p\2\2;<\7e\2\2<=\7t\2\2"+
		"=>\7{\2\2>?\7r\2\2?@\7v\2\2@A\7*\2\2A\16\3\2\2\2BC\7\60\2\2CD\7f\2\2D"+
		"E\7g\2\2EF\7e\2\2FG\7t\2\2GH\7{\2\2HI\7r\2\2IJ\7v\2\2JK\7*\2\2K\20\3\2"+
		"\2\2LM\7c\2\2MN\7n\2\2NO\7i\2\2O\22\3\2\2\2PQ\7<\2\2Q\24\3\2\2\2RS\7o"+
		"\2\2ST\7q\2\2TU\7f\2\2UV\7g\2\2V\26\3\2\2\2WX\7m\2\2X\30\3\2\2\2YZ\7r"+
		"\2\2Z[\7c\2\2[\\\7f\2\2\\]\7f\2\2]^\7k\2\2^_\7p\2\2_`\7i\2\2`\32\3\2\2"+
		"\2ae\t\2\2\2bd\t\3\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\34\3\2"+
		"\2\2ge\3\2\2\2hm\7$\2\2il\5!\21\2jl\13\2\2\2ki\3\2\2\2kj\3\2\2\2lo\3\2"+
		"\2\2mn\3\2\2\2mk\3\2\2\2np\3\2\2\2om\3\2\2\2pq\7$\2\2q\36\3\2\2\2rt\t"+
		"\4\2\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2v \3\2\2\2wx\7^\2\2x|\7"+
		"$\2\2yz\7^\2\2z|\7^\2\2{w\3\2\2\2{y\3\2\2\2|\"\3\2\2\2\b\2ekmu{\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}