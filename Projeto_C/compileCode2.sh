#!/bin/bash
antlr4-clean
rm 'Output.java'
clear
antlr4-build
antlr4-run 'testsGrammar2.txt'
javac 'Output.java'
java 'Output'
rm 'testsGrammar2.txt'
rm 'AlgTest.java'