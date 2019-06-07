**PROJECT TITLE: miniJavi

**GROUP: 31


NAME1: Ricardo Manuel Gonçalves da Silva, NR1: 201607780, GRADE1: 17, CONTRIBUTION1: 25%

NAME2: Luis Ricardo Marques Oliveira, NR2: 201607946, GRADE2: 17, CONTRIBUTION2: 25%

NAME3: Duarte Miguel de Novo Faria, NR3: 201607176, GRADE3: 17, CONTRIBUTION3: 25%

NAME4: Miguel Dias de Carvalho, NR4: 201605757, GRADE4: 17, CONTRIBUTION4: 25%


GLOBAL Grade of the project: 17



** SUMMARY:

O nosso compilador compila código em Java--, utilizando uma gramatica LL(1) com lookaheads(2) locais e transforma em bytecodes Java, analisando o código lexical, sintactica e semanticamente e no final gerando o código correspondente de acordo  com o Jasmin.
As funções do nosso compilador também incluiem o output de erros semanticos que informam o utilizador onde é que o código deve ser melhorado.

** EXECUTE: (indicate how to run your tool)[TODO]:retirar o que está a mais

Para compilar o código fonte, precisa-se de javacc e de jjtree, por isso deve-se executar sequencialmente:
jjtree Jmm.jjt
javacc Jmm.jj
javac -verbose *.java
java MainProject <filename> //ex:java MainProject codeTestingFile
java -jar miniJavi.jar <filename> 
java -jar jasmin.jar _jasmim_generated.j


**DEALING WITH SYNTACTIC ERRORS: (Describe how the syntactic error recovery of your tool does work. Does it exit after the first error?)

O analisador sintático percorre o código e mal apanha um erro termina logo mas apanha todos os erros de sintaxe. 


**SEMANTIC ANALYSIS: (Refer the semantic rules implemented by your tool.)

Na análise semântica, lemos a AST e preenchemos as Symbol Tables ao mesmo tempo que detectamos erros.

Faz recuperação de erros nas condições do while. Ou seja, mal apanhe um erro semântico na condição do while salta para o fim da terminação e continua a analisar o resto do código.

A nossa symbol table tem como filhos uma linked list de symbol tables. Cada symbol table tem um hashmap de variaveis e/ou outro de parametros.
Estes hashmaps são compostos por um par com o nome do elemento como chave e o próprio elemento como parametro. Cada elemento pode ter os seguintes atributos: name, type, isInitialized, arguments, value e return value;

Mete todos os erros que existem numa estrutura de dados e depois efectua a impressão de todos os erros e sai.

O nosso compilador implementa todas as regras semanticas da linguagem Java--, nomeadamente:
 - Declarações /  inicializações de variáveis
 - Tipos e numero de argumentos das chamadas de funções
 - Verifica diferentes tipos de variáveis ao atribuir valores a uma variável e aquando de oprações/comparações
 - Se o tipo da variável de saída é compatível com o retorno da função


**INTERMEDIATE REPRESENTATIONS (IRs): 

Não temos Representações Intermédias.


**CODE GENERATION: 

Através do padrão visitor, a AST é visitada recuursivamente é feita a impressão de comandos Jasmin para cada nó no ficheiro.


**OVERVIEW: (refer the approach used in your tool, the main algorithms, the third-party tools and/or packages, etc.)

Usamos o JavaCC para fazer o parsinng dos ficheiros e criar a nossa AST.

Usamos o Jasmin para transformar os ficheiros de bytecodes Java em ficheiros .class.

Implementamos o padrão de desenho Visitor de acordo com o desenho criado pelo colectivo Gang Of Four.
O objectivo deste design pattern é o de definir uma nova operação sem introduzir modificações numa estrutura de objectos já existente.


A nossa aplicação funciona em três fases, todas elas usando o padrão Visitor:
Numa primeira fase é feita a análise sintatica.
A análise semantica é efectuada numa segunda fase.
Por fim dá-se a geração de código, através do padrão visitor, é feita a impressão de comandos Jasmin no ficheiro.


**TASK DISTRIBUTION: 

Todos os membros:
Desenvolvimento da gramática. Conversão da gramática para o formato LL(). Mecanismo de tratamento e recuperação de erros.
Criação da tabela de simbolos. Desenvolvimento da analise semantica. Desenvolvimento da análise de código. Testes e debug.


**PROS:

Está tudo funcional, exceptuando os arrays. Tirando isso é um compilador completamente funcional. Implementação do design pattern Visitor, que implica menos código. Gramática LL(1) com poucos LL(2) locais.
A construção de uma tabela de simbolos muito útil.


**CONS:

Não implementamos a geração de código para arrays nem quaisquer optimizações. Podiamos ter estruturado melhor o código.
