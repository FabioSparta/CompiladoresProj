#!/bin/bash
antlr4-clean
rm 'Output.java'
clear
antlr4-build
antlr4-run 'testsGrammar1.txt'
javac 'Output.java'
java 'Output'
rm 'testsGrammar1.txt'
