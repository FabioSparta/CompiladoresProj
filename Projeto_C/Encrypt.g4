grammar Encrypt;

import EncryptConfig;


main: statList EOF;

statList: ((stat)? ';')*;

stat: show          
    | declaration
    | assignment
    | file
    | operation
    | list
    | dict
    | conditional 
    | forLoop
    | whileLoop
    | doWhileLoop
    | switchSelect
    | function
    | stat2
    ;
    
declaration: '(' type ')' ( ID | assignment ); 

assignment: ID '<<' expr;

list: 'list' '[' type ']' ID '<<' '[' ((expr ',' )* (expr))? ']';
	
dict: 'dict' '[' type ',' type ']' ID '<<' '{'(((expr ':' expr) ',' )* (expr ':' expr))? '}';

file: 'file' ID '<<' 'open' '(' ( ID | STRING ) ')';

operation: 'READ' ID '<<' ID                            #readOperation	
         | 'WRITE' expr '>>' ID                         #writeOperation	
         | 'READB' ID '<<' ID                           #readbOperation	
         | 'WRITEB' expr '>>' ID                        #writebOperation
         | ID'(' ((expr ',' )* (expr))? ')'             #functionOperation
         | expr'.'ID'(' ((expr ',' )* (expr))? ')'      #idcallOperation
         ;

conditional: 'if' '(' expr ')' 'then' '{' 
                trueSL=statList 
            '}' ( 'else' 'if' '(' expr ')' 'then' '{' 
                statList 
            '}')* ('else' 'then' '{' 
                falseSL=statList  
            '}')?;

whileLoop: 'while' '(' expr ')' 'do' '{' 
                trueSL=statList 
            '}';

function: 'function' ID ( 'uses' (type ID ',' )* (type ID) )? ( 'returns' type )? '{' statList ('returns' expr ';')? '}';

forLoop: 'for' '(' 'var' (ID | declaration) 'in' (ID | '(' INTEGER ',' INTEGER ')') ('jump' expr)? ')' 'do' '{' listas=statList '}';

doWhileLoop: 'do' 'while' '(' expr ')' '{' 
                trueSL=statList 
            '}';

switchSelect: 'switch' '(' ID ')' '{' 
                ( '(' expr ')' ':' statList )*
                ('default' ':' defalt=statList)? 
            '}';


show: 'show' '>>' expr  #showNormal
	;

type:
     'integer' 
   | 'real'    
   | 'boolean' 
   | 'str'   
   | 'bytes' 
   ;


encryptAlg: ID'.encrypt('(ID|STRING)?')'; 

decryptAlg: ID'.decrypt('(ID|STRING)?')'; 

expr:
      encryptAlg                                                                                                #criptExpr
    | decryptAlg                                                                                                #decriptExpr
    | ID '('((expr ',' )* (expr) )? ')'                                                                         #funcallExpr 
    | expr'.'ID'(' ((expr ',' )*? (expr))? ')'                                                                  #idcallExpr
    | sign=('+'|'-') e=expr                         					                                        #signExpr
    | <assoc=right> e1=expr '^' e2=expr             					                                        #powExpr
    | e1=expr op=('*'| '/' | '%') e2=expr          				                                                #multDivExpr
    | e1=expr op=('+' | '-') e2=expr                					                                        #addSubExpr
    | e1=expr 
      op=('=' | '>' | '<' | '>=' | '<=' | '=>' | '=<' | '!=' | '!>' | '!<' | '!>=' | '!<=' | '!=>' | '!=<') 
      e2=expr                                                                                                   #comparisonExpr
    | '(' e=expr ')'                              					                                            #parenExpr
    | expr op=('and'|'or') expr                                                                                 #addcomparisonExpr
    | REAL                                          					                                        #realExpr
    | INTEGER                                       					                                        #integerExpr
    | BOOLEAN                                       					                                        #booleanExpr
    | ID 			                                           		                                            #idExpr
    | STRING										                                                            #strExpr
    ;	
	
BOOLEAN: 'true' | 'false';
ID: [a-zA-Z_][a-zA-Z_0-9]*;
STRING: '"' ( ESC | . )*? '"';
REAL: [0-9]+ '.' [0-9]*;
INTEGER: [0-9]+;
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '#' .*? '\n' -> skip;
MULTILINE_COMMENT: '#{' .*? '}' -> skip;
fragment ESC: '\\"' 
            | '\\\\';