#!bin/bash
jjtree Jmm.jjt;
echo               -------------- jjtree Jmm.jjt     -------------------;
javacc Jmm.jj;
echo               -------------- javacc Jmm.jj     -------------------;
    javac *.java;
echo                       -------------- javac *.java     -------------------;
echo            -------------- java Jmm codeTestingFile     -------------------;
java Jmm codeTestingFile;


