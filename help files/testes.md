
## Testes

Este ficheiro serve de histórico aos testes manuais que foram feitos
a cada uma das funções de gramática presentes no ficheiro jjt.
Por cada teste efectuado está o correspondente resultado: true ou false.
Serão representados por _T_ ou por _F_, respectivamente. O primeiro significa 
que o teste em causa passou, o segundo o contrário

    Type():{}
1. int **T**
2. int[] **T**
3. boolean **T**
4. boolean[] **T**


    VarDeclaration():{}
1. int i; **T**
2. int[] i; **T**
3. boolean i; **T**
4. boolean[] i; **T**
    
    
    void MethodDeclaration():{}
    
1. public int getFoo(){return foo;} **T**
2. public boolean getFoo(){return foo;} **T**
3. public int[] getFoo(){return foo;}  **T**
4. public boolean[] getFoo(){return foo;} **T**
5. public boolean[] getFoo(int i){return foo;} **T**
6. public boolean[] getFoo(int i, boolean j){return foo;}



Não esta a skipar os newlines na consola, ou seja tem nao podemos
fazer copy paste do git hub para teste, porque poe \n na consola


### Duvidas para o prof
O new line 
   
    \n
    
1. Nao esta como skip nao podemos polo como skip? Acho que faz sentido 
2. Existem classes aqui?  O identifier no type pode ser classe, ou este caso não é considerado?