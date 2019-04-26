#!bin/bash
jjtree Jmm.jjt;
echo               -------------- jjtree Jmm.jjt     -------------------;
#cd AST_files;
javacc Jmm.jj;
echo               -------------- javacc Jmm.jj     -------------------;

echo                       -------------- javac *.java     --------------;
javac -verbose -d *.java
echo            -------------- java Jmm codeTestingFile     -------------------;
java MainProject codeTestingFile;
