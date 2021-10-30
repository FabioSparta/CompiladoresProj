grammar EncryptConfig;

stat2: declarationC 
	 | assignmentC
	 | put
	 | loadAlg
	 | cripto
	 ;

assignmentC: ID '<<' exprC ('<<' exprC)*?;

declarationC: '(' 'cypher' ')' ( ID | assignmentC ); 

loadAlg: ID'.load(' STRING ')';

cripto: encryptAlg
		|decryptAlg
		;

encryptAlg: ID'.encrypt('(ID|STRING)?')'; 

decryptAlg: ID'.decrypt('(ID|STRING)?')'; 

put: ID'(' exprC ')';

exprC:n='alg'':' ID				#algExpr
	| n='mode'':' ID			#modeExpr
	| n='k'':' INTEGER			#keyExpr
	| n='padding'':' ID			#paddingExpr
	| ID ':' (ID|INTEGER|STRING)  #anyExpr
	;
	
ID: [a-zA-Z_][a-zA-Z_0-9]*;
STRING: '"' ( ESC | . )*? '"';
INTEGER: [0-9]+;
fragment ESC: '\\"' 
            | '\\\\';