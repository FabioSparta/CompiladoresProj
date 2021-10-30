# Este projeto foi desenvolvido por:
  > Alexandra---Carvalho-----93346  
  
  > Anthony-----Pereira-----93016
  
  > Fabio-------Carmelino-----9340
  
  > João--------Soares-----93078
  
  > Pedro-------Iglésias---89318
  
  > Pedro-------Marques----89069

## Notes:
  > A nossa gramática compila o codigo escrito em codigo java.
  > Temos uma gramática Encrypt na pasta Projecto_C que é o compilador.
  > Temos uma gramática EncryptConfig que vai ser usada para interpretar a parte do tema do projeto no nosso caso vai ser a parte de encriptação e desencriptação isto é:
    -Ler um algoritmo.
    -Configurar um algoritmo.
    -Encriptar informação.
    -Desencriptar informação.

## Temos prototipos de uso da gramática na pasta Prototypes.
  Temos um bash script que executa os seguintes comandos para facilitar trabalho: 
  cp 'testsGrammar1.txt' './Projeto_C/'---->Copia o ficheiro que vai analisar para a pasta onde esta localizada a gramatica
  cd './Projeto_C'------------------------->Entra dentro da pasta
  ./compileCode.sh------------------------->Executa um bash script
  \---antlr4-clean------------------------->Faz clean a todos ficheiros
  \---rm 'Output.java'--------------------->Remove o resultado da compilação caso exista
  \---clear-------------------------------->Limpa a consola
  \---antlr4-build------------------------->Faz build a tudo na pasta
  \---antlr4-run 'testsGrammar1.txt'------->Compila o ficheiro copiado criando o ficheiro Output.java
  \---javac 'Output.java'------------------>Compila o ficheiro java
  \---java 'Output'------------------------>Corre o ficheiro java
  \---rm 'testsGrammar1.txt'--------------->Remove o ficheiro copiado
  cd './..'-------------------------------->Volta à pasta anterior
