#!bin/bash
jjtree Jmm.jjt;
echo               -------------- jjtree Jmm.jjt     -------------------;
cd AST_files;
javacc Jmm.jj;
echo               -------------- javacc Jmm.jj     -------------------;
cd ..
javac -verbose -d classFiles/ AST_files/*.java;
echo                       -------------- javac *.java     -------------------;
javac -verbose -d classFiles/ *.java
javac -verbose -d classFiles/ Symbol/*.java
cd classFiles
echo            -------------- java Jmm codeTestingFile     -------------------;
java MainProject ../codeTestingFile;


