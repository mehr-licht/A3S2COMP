## Intro

Este repositório contém o primeiro checkpoint para a UC Compiladores. 

## Instructions for running


There is two ways for running this project,both on LINUX terminal:

1. Runnig the following comands in order 


        jjtree Jmm.jjt

        javacc Jmm.jj
    
        javac *.java
        
        java Jmm codeTestingFile
    
File _codeTestingFile_  contains the code that will be readed by the parser. For the purpose of testing diferents code blocks just open the file and copy paste into the file all the code to be tested.

2.Go to the project folder and run:

    sudo chmod+x run.sh

The following command will give the right permissions to run the bash script _run.sh_.

File run.sh is a script bash who purpose is to call all the previous commands in point 1.

Next, run:

    sh run.sh

or

    ./run.sh

Below a few documentation notes from JAVACC documentation 

### Documentation Notes

    . TOKEN:         This is used to specify lexical tokens (see next example)

    . SPECIAL_TOKEN: This is used to specify lexical tokens that are to be
                 ignored during parsing.  In this sense, SPECIAL_TOKEN is
                 the same as SKIP.  However, these tokens can be recovered
                 within parser actions to be handled appropriately.

    . MORE:          This specifies a partial token.  A complete token is
                 made up of a sequence of MORE's followed by a TOKEN
                 or SPECIAL_TOKEN.
The regular expression:


    < ID: ["a"-"z","A"-"Z","_"] ( ["a"-"z","A"-"Z","_","0"-"9"] )* >
    
creates a new regular expression whose name is ID. This can be referred anywhere else in the grammar simply as <ID>. What follows in square brackets are a set of allowable characters - in this case it is any of the lower or upper case letters or the underscore. This is followed by 0 or more occurrences of any of the lower or upper case letters, digits, or the underscore.
Other constructs that may appear in regular expressions are:

    ( ... )+  : One or more occurrences of ...
  
    ( ... )?  : An optional occurrence of ... (Note that in the case
                  of lexical tokens, (...)? and [...] are not equivalent)
  
    ( r1 | r2 | ... ) : Any one of r1, r2, ...
    
A construct of the form [...] is a pattern that is matched by the characters specified in ... . These characters can be individual characters or character ranges. A "~" before this construct is a pattern that matches any character not specified in ... . Therefore:

    ["a"-"z"] matches all lower case letters
    ~[] matches any character
    ~["\n","\r"] matches any character except the new line characters


The main point to note is that the regular expressions in the SKIP specification are only
 ignored *between tokens* and not *within tokens*. This grammar accepts any sequence of 
 identifiers with white space in between.

A legal input for this grammar is:

    "abc xyz123 A B C \t\n aaa"

This is because any number of the SKIP regular expressions are allowed in between consecutive <Id>'s. However, the following is not a legal input:

    "xyz 123"

This is because the space character after "xyz" is in the SKIP category and therefore causes one token to end and another to begin. This requires "123" to be a separate token and hence does not match the grammar.

If spaces were OK within <Id>'s, then all one has to do is to replace the definition of Id to:

    TOKEN :
    {
        < Id: ["a"-"z","A"-"Z"] ( (" ")* ["a"-"z","A"-"Z","0"-"9"] )* >
    }

Note that having a space character within a TOKEN specification does not mean that the space
character cannot be used in the SKIP specification. 
All this means is that any space character that appears in the context where it can
be placed within an identifier will participate in the match for <Id>, whereas all other space
characters will be ignored. The details of the matching algorithm are described in the JavaCC
 documentation.

As a corollary, one must define as tokens anything within which characters such as white 
space characters must not be present. In the above example, if <Id> was defined as a grammar 
production rather than a lexical token as shown below this paragraph, then "xyz 123" would have 
been recognized as a legitimate <Id> (wrongly).

    void Id() :
    {}
    {
        <["a"-"z","A"-"Z"]> ( <["a"-"z","A"-"Z","0"-"9"]> )*
    }
Note that in the above definition of non-terminal Id, it is made up of a sequence of single character tokens (note the location of <...>s), and hence white space is allowed between these characters.


An expansion unit can be a parenthesized set of one or more expansion choices. In which case, a legal parse of the expansion unit is any legal parse of the nested expansion choices. The parenthesized set of expansion choices can be suffixed (optionally) by:

"+": Then any legal parse of the expansion unit is one or more repetitions of a legal parse of the parenthesized set of expansion choices.
"*": Then any legal parse of the expansion unit is zero or more repetitions of a legal parse of the parenthesized set of expansion choices.
"?": Then a legal parse of the expansion unit is either the empty token sequence or any legal parse of the nested expansion choices. An alternate syntax for this construct is to enclose the expansion choices within brackets "[...]".
