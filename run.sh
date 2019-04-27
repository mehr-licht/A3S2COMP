#!bin/bash
echo "-------------- jjtree Jmm.jjt     -------------------"
jjtree Jmm.jjt
echo "-------------- javacc Jmm.jj     -------------------"
javacc Jmm.jj;
echo "-------------- javac *.java     --------------"
javac -verbose *.java
echo "-------------- java Jmm codeTestingFile     -------------------"
java MainProject codeTestingFile;
