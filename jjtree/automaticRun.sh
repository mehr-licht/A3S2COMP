#!bin/bash
echo @@@@@@@@@@@@   "jjtree Jmm.jjt"   @@@@@@@@@@@@@@@@@@@@@@;
jjtree Jmm.jjt;

echo @@@@@@@@@@@  "javacc Jmm.jj"  @@@@@@@@@@@@@@@@@@@@@@@@@@;
javacc Jmm.jj;

echo @@@@@@@@@@@   "javac *.java"  @@@@@@@@@@@@@@@@@@@@@@@@@@@;
javac *.java;

echo @@@@@@@@@@@  "java Jmm"   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@;
java Jmm codeTestingFile
