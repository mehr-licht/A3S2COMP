import java.util.*;

public class JasminVisitor extends JasminGenerator implements JmmVisitor {

  private Type storeType = Type.UNDEFINED;
  static  int counter = 1;
  static String label = "Label";
  private Map<String, Integer> mapStores = new HashMap<String, Integer>();


  public JasminVisitor(LinkedList<SymbolTable> list_symbol_tables) {
    super(list_symbol_tables);
  }

  /** ******* */
  @Override
  public Object visit(SimpleNode node, Object data) {

    return null;
  }

  /** ******* */
  @Override
  public Object visit(ASTStart node, Object data) {
    node.jjtGetChild(0).jjtAccept(this, null);
    System.out.println("Finished generated code");
    return null;
  }
  /** **** */
  @Override
  public Object visit(ASTUnmodifiedClassDeclaration node, Object data) {
    String extends_class = null;

    this.getWriter().print(".class public ");
    this.getWriter().println(node.value);
    this.lineNumber++;

    //Existe extends
    if (node.jjtGetChild(0) instanceof ASTName) {
      extends_class = ((ASTName) node.jjtGetChild(0)).value;
      this.getWriter().println(".super java/lang/" + extends_class);
      this.lineNumber++;
      this.getWriter().println("");
      this.getWriter().println("");
      //TODO acabar invocacao da chamada do constructor da super class

     //Nao existe extends: case: default constructor
    }else{
      this.getWriter().println(".super java/lang/Object");
      this.getWriter().println("");
        this.getWriter().println(".method public <init>()V");
        this.getWriter().println("aload_0");
        this.getWriter().println("invokespecial java/lang/Object/<init>()V");
        this.getWriter().println(".end method");
        this.getWriter().println("");

    }
//TODO DELEte this code
    //    // caso nao haja extendeds faz este print
//    //;default construtor
//    if (case_no_extends) {
//      this.getWriter().println(".super java/lang/Object");
//      this.getWriter().println("");
//      this.lineNumber++;
////        this.getWriter().println(".method public <init>()V");
////        this.getWriter().println("aload_0");
////        this.getWriter().println("invokespecial java/lang/Object/<init>()V");
////        this.getWriter().println(".end method");
////        this.getWriter().println("");
//    }

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTINICIALIZACAO node, Object data) {


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTNCD node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTCONSTRUCTOR node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTMETODO node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTFD node, Object data) {
//So preciso de por aqui quando acontece o //TODO new creio
    //aqui tenho qu epor na hasmap
//    System.out.println("Hello world");
//    boolean arraydIniticializada = ((ASTType) node.jjtGetChild(0)).isArray;
//    String nomearray = node.jjtGetChild(1).toString();
//    //Preciso de imprimir?
//    if(arraydIniticializada){
//      mapStores.put(nomearray,counter);
//      counter++;
//    }


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTMethodDeclarationLookahead node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      this.getWriter().print(node.value);

      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTINIT node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTVariableDeclaratorId node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }

    return null;
  }

  /** Impressao dos metodos e argumentos */
  @Override
  public Object visit(ASTMethodDeclaration node, Object data) {
    String str_main = "main";
    String resulting_type = null;
    boolean check_method_return = false;
    boolean check_ast_return = false;

    //leitura do valor de retorno - filho 1
    if (node.jjtGetChild(0) instanceof ASTResultType){
      if( node.jjtGetChild(0).jjtGetNumChildren() > 0) {
        resulting_type = ((ASTPrimitiveType) node.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0)).toString();
        check_method_return = ((ASTType) node.jjtGetChild(0).jjtGetChild(0)).isArray;
      }else{
        resulting_type =  ((ASTResultType) node.jjtGetChild(0)).value;
      }


    }


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {

      //leitura dos metodos e impressao
      if (node.jjtGetChild(i) instanceof ASTMethodDeclarator) {

          // caso main metodo
          if (str_main.equals(((ASTMethodDeclarator) node.jjtGetChild(i)).value)) {
            this.getWriter().println(".method public static main([Ljava/lang/String;)V");
            this.lineNumber++;
            //print stacks //TODO HARDCODDE
            this.getWriter().print(".limit locals ");
            this.getWriter().println("5");
            this.lineNumber++;
            this.getWriter().print(".limit stack ");
            this.getWriter().println("5");
            this.lineNumber++;

      //caso Metodos notMain
      } else {
          
          //para guardar os parametros 
          ArrayList parametros = new ArrayList( );
          
          //impressao do cabeçalho do metodo
          this.getWriter().print(".method public ");
          this.getWriter().print(((ASTMethodDeclarator) node.jjtGetChild(i)).value);

          this.getWriter().print("(");
          mapStores.put( ((ASTMethodDeclarator) node.jjtGetChild(i)).value.toString(), counter);
          counter++;

          //tratamento dos Argumentos
          if (node.jjtGetChild(1).jjtGetNumChildren() > 0) {
            // impressão dos argumentos do métodos
              for (int j = 0; j < node.jjtGetChild(1).jjtGetNumChildren(); j++) {
                String parametroTipo = new String();
                boolean checkisArray = false;
                String conversion_types = new String();

                    if (node.jjtGetChild(1).jjtGetChild(j) instanceof ASTType) {

                      parametroTipo = node.jjtGetChild(1).jjtGetChild(j).jjtGetChild(0).toString();
                      checkisArray = ((ASTType) node.jjtGetChild(1).jjtGetChild(j)).isArray;
                      conversion_types = JasminGenerator.conversitonTypesArguments(parametroTipo, checkisArray);
                      parametros.add(conversion_types);
                      this.getWriter().print(conversion_types);

                    } else {
                      //Guardar o nome dos Paramentos para fazer os pritns do loads
                      String nomeParametro = node.jjtGetChild(1).jjtGetChild(j).toString();
                      mapStores.put(nomeParametro,counter);
                      counter++;

                    }

                    //Impressao da virgula
                    if (j % 2 == 1) {
//                      this.getWriter().print(";");
                    }

              } //end ciclo de leitura dos argumentos
          } //end if quando há filhos/ ou seja quando nao ha argumentos

          this.getWriter().print(")");
          String conversion_types = JasminGenerator.conversitonTypesArguments(resulting_type, check_method_return);
          this.getWriter().println("" + conversion_types);
          this.lineNumber++;

          //print stacks //TODO HARDCODDE : optimizations
          this.getWriter().print(".limit locals ");
          this.getWriter().println("5");
          this.lineNumber++;
          this.getWriter().print(".limit stack ");
          this.getWriter().println("10");
          this.lineNumber++;

          //impressao dos iloads consoante os Parametros
          //1 de 2
          //impressao do this
          this.getWriter().println("aload 0");
          //impressao dos restante argumentos 2 de 2
          Iterator<String> iter = parametros.iterator();
          int k = 1;
          while(iter.hasNext()){

            switch(iter.next().toString()){
              case "I":
                this.getWriter().print("iload ");
                break;
              case "Z":
                this.getWriter().print("iload ");
                break;
              case "V":
                System.out.println("WARNING CHECKPOINT line 269");
                break;
              case "[I":
                this.getWriter().print("aload ");
                break;
              default:
                break;
            }
            this.getWriter().println(k);
            k++;
          }
        }

      }


      // Se nao houver return no codigo java print o default para o jasmim
      if (node.jjtGetChild(i) instanceof ASTRETURN){
        check_ast_return = true;
        if( node.jjtGetChild(i).
                jjtGetChild(0).
                jjtGetChild(0).
                jjtGetChild(0).
                jjtGetChild(0) instanceof ASTName){
          String nameVariavel = node.jjtGetChild(i).
                  jjtGetChild(0).
                  jjtGetChild(0).
                  jjtGetChild(0).
                  jjtGetChild(0).toString();
         int index =  mapStores.get(nameVariavel);
         String conversion_types = JasminGenerator.conversitonTypesArguments(resulting_type, check_method_return);
          switch(conversion_types){
            case "I":
              this.getWriter().println("ireturn ");
              getWriter().println(".end method");

              break;
            case "Z":
              this.getWriter().println("ireturn ");
              getWriter().println(".end method");
              break;
            case "V":
              System.out.println("WARNING CHECKPOINT line 269");
              break;
            case "[I":
              this.getWriter().println("areturn ");
              getWriter().println(".end method");
              break;
            default:
              break;
          }
          this.getWriter().println("");

//         //impressao de um int
//
//        //impressao de uma variavel
//          this.getWriter().print("return");



        }

       // this.getWriter().print("return");



      }


      node.jjtGetChild(i).jjtAccept(this, data);
    }

    //print sem return no metodo return default
    if(!check_ast_return){
      this.getWriter().println("return");
      this.lineNumber++;
      this.getWriter().println(".end method");
      this.lineNumber++;
    }

    return null;
  }

  /** */
  @Override
  public Object visit(ASTMethodDeclarator node, Object data) {
    counter = node.jjtGetNumChildren()/2;
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }

    return null;
  }

  @Override
  public Object visit(ASTConstructorDeclaration node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  /** */
  @Override
  public Object visit(ASTType node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTPrimitiveType node, Object data) {

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTResultType node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  /** Processa o caso do extends Processa o caso do main que afinal não é necessario */
  @Override
  public Object visit(ASTName node, Object data) {
    String type_to_print = null;

    if (node.jjtGetParent().jjtGetParent() instanceof ASTMethodDeclarator) {
      System.out.println("Warning Jasmin Generator - line 291");


    } else if (node.jjtGetParent() instanceof ASTUnmodifiedClassDeclaration) {
      // caso do extends
      System.out.println("Warning Jasmin Generator - line 294");
      return null;



    } else if (node.parent instanceof ASTMethodDeclaration ){

      //Para usar o file io --todos os metodos sao final
      if (node.value.toString().equals("io")) {

//        //Parte 1 de 3 da invocacao de metodos,
//        this.getWriter().print("invokespecial io/");
//
//        switch (node.value2){
//          case "read":
//            this.getWriter().print("read()I");
//            break;
//          case "print":
//            this.getWriter().print("print(");
//            break;
//          case "println":
//            this.getWriter().print("println(");
//
//            break;
//
//          default:
//            this.getWriter().println("Warning line 329");
//            break;
//
//        }
//
//        //tratamento de 3 casos int, string int string e void
//        if (node.value2.toString().equals("print") || node.value2.toString().equals("println")) {
//
//          //tratamento do void -caso especial sem filhos
//          if(node.jjtGetParent().jjtGetChild(node.jjtGetNumChildren()-1).jjtGetNumChildren() == 0){
//              //void nao faz nenhuma impressao em jasmim
//
//          //Tratamento dos casos normais
//          }else{
//
//            //Impressao de inteiros
//            if(node.jjtGetParent()
//                    .jjtGetChild(node.jjtGetParent().jjtGetNumChildren() - 1)
//                    .jjtGetChild(0)
//                    .jjtGetChild(0)
//                    .jjtGetChild(0)
//                    .jjtGetChild(0)
//                    instanceof ASTLiteral){
//              //eh um numero
//              this.getWriter().println("I;");
//            }
//
//            //Impressao de Strings
//            if(node.jjtGetParent()
//                    .jjtGetChild(node.jjtGetParent().jjtGetNumChildren() - 1)
//                    .jjtGetChild(0)
//                    .jjtGetChild(0)
//                    .jjtGetChild(0)
//                    .jjtGetChild(0) instanceof  ASTName){
//              String datatype =
//                      node.jjtGetParent()
//                              .jjtGetChild(node.jjtGetParent().jjtGetNumChildren() - 1)
//                              .jjtGetChild(0)
//                              .jjtGetChild(0)
//                              .jjtGetChild(0)
//                              .jjtGetChild(0)
//                                  .toString();
//              String init_type = JasminGenerator.conversitonTypesArguments(datatype, false);
//              // Caso em que o argumento eh o nome de uma variavel
//              if (init_type == null) {
//                // procura o tipo de eleemnto. //tipo de no
//                // -------------COULD DO A REFACTOR ----BEGIN //
//                Element newDataType =
//                        JasminGenerator.find_type_element(
//                                this.list_symbol_tables,
//                                datatype,
//                                ((ASTMethodDeclarator) node.jjtGetParent().jjtGetChild(1)).value);
//                type_to_print = JasminGenerator.conversitonTypesArguments(newDataType.getType(), false);
//                // -------------COULD DO A REFACTOR ----END //
//
//                // fazer o load da variavel no argumento do metodo
//                this.getWriter().print("iload ");
//                int index = mapStores.get(newDataType.getName());
//                this.getWriter().println(index);
//                this.lineNumber++;
//              }
//
//            }
//
//
//          }//ended if do tratamento dos argumentos
//          //imprimir os argumentos
//          this.getWriter().println(")V");
//          this.lineNumber++;
//        }//ended if do tratamento do file io
//

        //Para usar o file do ioPlus -- Todos os metodos sao static
      }else if(node.value.toString().equals("ioPlus")){
        this.getWriter().print("invokestatic ioPlus/");
        switch (node.value2) {

          case "printResult":
            this.getWriter().println("printResult(I)V");
            break;
          case "printHelloWorld":
            this.getWriter().println("printHelloWorld()V");
            break;
          case "requestNumber":
            this.getWriter().println("requestNumber()I");
            break;
          default:
            this.getWriter().println("Warning line 373");
        }

        // Para usar o file do MathUtils --metodos apenas publicos
      }else if(node.value.toString().equals("MathUtils")){
        this.getWriter().println("invoke MathUtils/" + node.value2);
        this.lineNumber++;
      }else{
        this.getWriter().print("ldc ");
        this.getWriter().println(node.value);
        this.lineNumber++;
      }

    }else if(node.jjtGetParent().jjtGetParent() instanceof ASTADDSUB || node.jjtGetParent().jjtGetParent() instanceof ASTDIVMULT){
      this.getWriter().print("iload ");
      int index = mapStores.get(node.value);
      this.getWriter().println(index);
      this.lineNumber++;
    }

    return null;
  }

  @Override
  public Object visit(ASTASSIGNMENT node, Object data) {

    //Updated stores Nome da varivel
    String variavelNome = ((ASTName) node.jjtGetChild(0)).value;
    //Valor do int caso for
    String valorInt = new String();
    //valor do boolean se for o caso
    String valorBoolean = new String();

    //Processamento dos assignements para int e boolean e arrays - BEGIN
    if(node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0) instanceof ASTLiteral
            && node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() < 1
    ){

      valorInt = node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).toString();

      this.getWriter().print("bipush ");
      int result = Integer.parseInt(valorInt);
      this.getWriter().println(result);


      //Confirmacao se tem filhos para booelanos
    }else if( node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).
            jjtGetChild(0).jjtGetNumChildren() != 0 ){

      //Assignment de booleanos
      if (node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).
              jjtGetChild(0).jjtGetChild(0) instanceof ASTBooleanLiteral){


        //Processamento de boolean - irbuscar o valor
        valorBoolean = node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).
                jjtGetChild(0).toString();
        // imprime boolean
        this.getWriter().print("bipush ");
        if (valorBoolean.equals("true")) {
          this.getWriter().println(1);
        } else {
          this.getWriter().println(0);
        }
      }

      //Assignment de arrays - no pressuposto que existem mais
    }else if(node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() == 2){

      String nomeArray = node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).toString();
      String indexArray = node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(1).
              jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).toString();
      int indexArrayIntg = Integer.parseInt(indexArray);

      //TODO Aqui tenho  de ir buscar o valor dela à hasmap
      //saca -la de la por-la na stack  e fazer push dela para a nova variavel
      int stackPosition = mapStores.get(nomeArray);
      //TODO fazer qualquer coisa com o array

      for (int i = 0; i < node.jjtGetNumChildren(); i++) {
        node.jjtGetChild(i).jjtAccept(this, data);
      }

      //chamamento de classes e metodos
    }else if(node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() == 3){

      String classnome = node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).toString();
      String classdeclared = this.list_symbol_tables.get(this.list_symbol_tables.size()-1).getName();

      if(classnome.equals(classdeclared)){
        String metodonome = node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(1).toString();
        //vou a hasshar procura-la;
        int stackPosition = mapStores.get(metodonome);

        getWriter().print("aload ");
        getWriter().println(stackPosition);

        // Mandar aqui o   int
        if (node.jjtGetChild(1)
                .jjtGetChild(0)
                .jjtGetChild(0)
                .jjtGetChild(2)
                .jjtGetChild(0)
                .jjtGetChild(0)
                .jjtGetChild(0)
                .jjtGetChild(0)
            instanceof ASTLiteral) {
          String valorLiteral =
              node.jjtGetChild(1)
                  .jjtGetChild(0)
                  .jjtGetChild(0)
                  .jjtGetChild(2)
                  .jjtGetChild(0)
                  .jjtGetChild(0)
                  .jjtGetChild(0)
                  .jjtGetChild(0)
                  .toString();
          getWriter().print("bipush ");
          getWriter().println(valorLiteral);
          getWriter().print("istore ");
          counter++;
          getWriter().println(counter);
        }

        getWriter().print("invokevirtual ");
        getWriter().print(classnome);
        getWriter().print("/");
        getWriter().print(metodonome);
        getWriter().print("(");



        //impressao dos argumentos
        LinkedList<SymbolTable> temp = this.list_symbol_tables;
        for (Iterator p = temp.iterator(); p.hasNext();) {
          SymbolTable tempST = ((SymbolTable) p.next());

          if( tempST.getName().equals(metodonome) ){
            HashMap<String,Element> valueParamentroType = tempST.getParameters();
            Map<String,Element> map = valueParamentroType;
            for (Map.Entry<String,Element> entry : map.entrySet()) {
              getWriter().print(
                      JasminGenerator.conversitonTypesArguments(entry.getValue().getType(),false)
              );
//              getWriter().print(";");
            }
            getWriter().print(")");
            getWriter().println(
                    JasminGenerator.conversitonTypesArguments(tempST.getEnd_def_return_name(),false)
            );
          }
        }//fim do for
      }
    }//fim dos metodos e classes

    //TODO getfield com numeros
    //node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() == 3
    //node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).toString().equals("Nome da  clasese");
    //imprime this
    //imprime variavel
    // imprime metodo
    //
    //end processamento de acordo com os tipos
    this.lineNumber++;

    if(!mapStores.containsKey(variavelNome)){ //se NAO foi declarado antes
      mapStores.put(variavelNome, counter);
      this.getWriter().print("istore ");
      this.getWriter().println(counter);
      counter++;
    }else{                                    //Se ja foi declarado antes
      int index = mapStores.get(variavelNome);
      this.getWriter().print("istore ");
      this.getWriter().println(index);
    }
    this.lineNumber++;


    return null;
  }

  @Override
  public Object visit(ASTConditionalAndExpression node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTLESSTHEN node, Object data) {

    // Verifica os dois filhos associados a condicao
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {

      // filho eh um inteiro
      if (node.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0) instanceof ASTLiteral) {
        int result = Integer.parseInt(node.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0).toString());
        this.getWriter().print("bipush ");
        this.getWriter().println(result);
        this.lineNumber++;

        // Filho eh uma variavel
      } else if (node.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0) instanceof ASTName) {
        // fazer o load da variavel no argumento do metodo
        String variavelName = node.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0).toString();
        this.getWriter().print("iload ");
        int index = mapStores.get(variavelName);
        this.getWriter().println(index);
        this.lineNumber++;
      }

    }
    return null;
  }

  /** ******* */
  @Override
  public Object visit(ASTAdditiveExpression node, Object data) {
    node.jjtGetChild(0).jjtAccept(this, data);
    return null;
  }
  /** ******* */
  @Override
  public Object visit(ASTADDSUB node, Object data) {
    String str_add = "+";
    String str_sub = "-";
    node.jjtGetChild(0).jjtAccept(this, data);
    node.jjtGetChild(1).jjtAccept(this, data);

    if (str_add.equals(node.value)) {
      this.getWriter().println("iadd");
      this.lineNumber++;
    } else if (str_sub.equals(node.value)) {
      this.getWriter().println("isub");
      this.lineNumber++;
    }

    return null;
  }
  /** ******* */
  @Override
  public Object visit(ASTMultiplicativeExpression node, Object data) {
    node.jjtGetChild(0).jjtAccept(this, data);
    return null;
  }
  /** ******* */
  @Override
  public Object visit(ASTDIVMULT node, Object data) {
    String str_div = "/";
    String str_mul = "*";

    node.jjtGetChild(0).jjtAccept(this, data);
    node.jjtGetChild(1).jjtAccept(this, data);

    if (str_div.equals(node.value)) {
      this.getWriter().println("idiv");
      this.lineNumber++;
    } else if (str_mul.equals(node.value)) {
      this.getWriter().println("imul");
      this.lineNumber++;
    }

    return null;
  }

  @Override
  public Object visit(ASTCastLookahead node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTPrimarySuffix node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  /** **** */
  @Override
  public Object visit(ASTLiteral node, Object data) {

    this.getWriter().print("bipush ");
    this.getWriter().println(node.value);

    return null;
  }

  @Override
  public Object visit(ASTBooleanLiteral node, Object data) {

    String valorBoleano = node.value;
    node.jjtGetParent().jjtGetParent().jjtGetParent();
    if(node.jjtGetParent().jjtGetParent().jjtGetParent() instanceof ASTMethodDeclaration){
      this.getWriter().print("bipush ");
      if(valorBoleano.equals("true")){
        this.getWriter().println(1); //means true for boolean
      }else{
        this.getWriter().println(0); //means false for boolean
      }
    }
    this.getWriter().print("istore ");
    this.getWriter().println(counter);
    counter++;
    // mapStores.put()


    this.lineNumber++;
    return null;
  }

  @Override
  public Object visit(ASTIF node, Object data) {

    String finalLabel = label + this.lineNumber;
    String middleLabel = label + ++this.lineNumber;


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {


      //Processar a condicao //TODO falta testar com &&
      if(node.jjtGetChild(i) instanceof ASTCONDITION ){
        node.jjtGetChild(i).jjtAccept(this, data);
        //depois de por os filhos na stack escreve
        this.getWriter().print("if_icmpge ");
        this.getWriter().println(middleLabel);

      }else if(node.jjtGetChild(i) instanceof ASTSTATEMENT ){
        node.jjtGetChild(i).jjtAccept(this, data);

        // Para diferenciar os if com ou sem elses
        if (node.jjtGetNumChildren() == 3 ) {
          this.getWriter().print("goto ");
          this.getWriter().println(finalLabel);
        }

        this.getWriter().print(middleLabel);
        this.getWriter().println(": ");

      }else if(node.jjtGetChild(i) instanceof ASTELSE){
        node.jjtGetChild(i).jjtAccept(this, data);

        this.getWriter().print(finalLabel);
        this.getWriter().println(": ");

      }


    }// end for

    return null;
  }

  @Override
  public Object visit(ASTCONDITION node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTSTATEMENT node, Object data) {

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }

    return null;
  }

  @Override
  public Object visit(ASTELSE node, Object data) {


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }


    return null;
  }

  @Override
  public Object visit(ASTWHILE node, Object data) {
    String inicialLabel = label + this.lineNumber;
    String finalLabel = label + ++this.lineNumber;

    this.getWriter().print(inicialLabel);
    this.getWriter().println(": ");

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {

      if(node.jjtGetChild(i) instanceof ASTCONDITION ){

        node.jjtGetChild(i).jjtAccept(this, data);
        //depois de por os filhos na stack escreve
        this.getWriter().print("if_icmpge ");
        this.getWriter().println(finalLabel);

      }else if(node.jjtGetChild(i) instanceof ASTBODY ){
        node.jjtGetChild(i).jjtAccept(this, data);

        this.getWriter().print("goto ");
        this.getWriter().println(inicialLabel);

        this.getWriter().print(finalLabel);
        this.getWriter().println(": ");

      }



    }
    return null;
  }

  @Override
  public Object visit(ASTBODY node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  // TODO
  @Override
  public Object visit(ASTRETURN node, Object data) {

    // caso em que  return; case void
    if (node.jjtGetNumChildren() == 0) {
      this.getWriter().println("return");
      this.lineNumber++;
      this.getWriter().println(".end method");
      this.lineNumber++;
      return null;

    //Caso de variaveis no valor de return
    }else if(node.jjtGetChild(0).
            jjtGetChild(0).
            jjtGetChild(0).
            jjtGetChild(0) instanceof ASTName) {


      String variavelName = ((ASTName) node.jjtGetChild(0).
            jjtGetChild(0).
            jjtGetChild(0).
            jjtGetChild(0)).value;
      int index = mapStores.get(variavelName);

    } else { // caso em que efectivamente retorna algo
      // caso de inteiros
//      if (node.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0) instanceof ASTLiteral) {
//        node.jjtGetChild(0).jjtAccept(this, data);
//        this.getWriter().println("WARNING 896");
//        this.getWriter().println("ireturn");
//        this.lineNumber++;
//      } else if (false) { // caso arays //TODO
//        this.getWriter().println("WARNING 896");
//        this.getWriter().println("areturn");
//        this.lineNumber++;
//      } else if (false) { // caso de variables
//        this.getWriter().println("WARNING 896");
//        // ret 2 return ti the address held in local variablw 2
//        // JVM return from subroutine
//      }
//      this.getWriter().println(".end method");
//      this.lineNumber++;
    }

    return null;
  }

    //    public Object visit(ASTCall node, Object data) {
  //        SymbolTable rootSymbolTable = this.jasminGenerator.getRootSymbolTable();
  //
  //        String types =
  // (String)node.jjtGetChild(node.jjtGetNumChildren()-1).jjtAccept(this,false);
  //        String moduleName = (String)node.value;
  //        boolean pop = false;
  //        if(node.jjtGetNumChildren()==1){
  //            Element element = rootSymbolTable.getElement(moduleName);
  //            this.jasminGenerator.writeInvokeStatic(element);
  //            pop=element.getReturn().getName() != null;
  //        }
  //        else{
  //            String methodName = (String)node.jjtGetChild(0).jjtAccept(this,false);
  //            String returnValue;
  //            if(storeType == Type.ARRAY){
  //                returnValue = "[I";
  //            }
  //            else if(storeType == Type.INTEGER){
  //                returnValue = "I";
  //            }
  //            else
  //                returnValue = "V";
  //            this.jasminGenerator.writeInvokeStatic(moduleName, methodName, types, returnValue);
  //        }
  //        if(pop && (boolean)data){
  //            this.jasminGenerator.writePop();
  //        }
  //        return null;
  //    }
  //
  //
  //    public Object visit(ASTConditionalOperation node, Object data) {
  //        ASTAccess leftNode = (ASTAccess)node.jjtGetChild(0);
  //        leftNode.jjtAccept(this,false);
  //        SimpleNode rightNode = (SimpleNode)node.jjtGetChild(1);
  //        rightNode.jjtAccept(this,false);
  //
  //        return null;
  //    }
  //11
  //    public Object visit(ASTWhile node, Object data) {
  //
  //        ASTConditionalOperation conditionNode = (ASTConditionalOperation) node.jjtGetChild(0);
  //        ASTStatements statementNode = (ASTStatements) node.jjtGetChild(1);
  //
  //        this.jasminGenerator.writeWhile(conditionNode, statementNode, this);
  //
  //        return null;
  //    }

}
