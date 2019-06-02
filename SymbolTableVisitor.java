import java.util.LinkedList;

public class SymbolTableVisitor extends Global_Symbol_Table_List implements JmmVisitor {

  public int counter;

  public SymbolTableVisitor() {
    this.list_symbol_tables = new LinkedList<SymbolTable>();
    this.counter = -1;
  }

  /** Usa este constructor */
  public SymbolTableVisitor(Global_Symbol_Table_List globalSymbolTableList) {
    this.list_symbol_tables = new LinkedList<SymbolTable>();
  }

  /** */
  @Override
  public Object visit(SimpleNode node, Object data) {
    return null;
  }

  /** Visita este nó, e faz o print de tudo */
  @Override
  public Object visit(ASTStart node, Object data) {
    node.jjtGetChild(0).jjtAccept(this, data);
    return null;
  }

  /** Processa os nomes das classes e adiciona uma symbol table à lista global das symbol tables */
  @Override
  public Object visit(ASTUnmodifiedClassDeclaration node, Object data) {
    SymbolTable st = new SymbolTable();
    String extends_class  = null;

    st.setName(node.value);
    try {
      if (st.getName() != null) {
        this.list_symbol_tables.push(st);
      }
    } catch (LinkageError e) {
      // Semantica err nao existe no class
      SemanticManager.addError(
              "Could not find class "
                      + st.getName()
                      + ", due to: "
                      + e.getClass().getName()
                      + ": "
                      + e.getMessage());
    }
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {

      //case extends adiciona os filhos da classe
      if(node.jjtGetChild(i) instanceof ASTName){
        extends_class = ((ASTName) node.jjtGetChild(i)).value;
        SymbolTable st_extended = new SymbolTable(extends_class,false);
        this.list_symbol_tables.getFirst().addChild(st_extended);
      }

      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }



  /****/
  @Override
  public Object visit(ASTELSE node, Object data) {
    SymbolTable symbolTable = this.list_symbol_tables.getFirst().getChildren_list_of_symbol_tables().getFirst();

    SymbolTable symbolTable_else = new SymbolTable(node.toString(), true);

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      Element element = new Element();

      //adicionar os Filhos da variaveies
      if(node.jjtGetChild(i) instanceof ASTASSIGNMENT){
        element.setName(node.jjtGetChild(i).jjtGetChild(0).toString());
        element.setInitialized(true);
        Element element1 = new Element();
        element1.setName(node.jjtGetChild(i).jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).toString());
        element.setReturnValue(element1);

        //Para inicialiacoes com numeros
        if(node.jjtGetChild(i).jjtGetChild(1).jjtGetChild(0).jjtGetChild(0) instanceof ASTLiteral){
          element.setType("int");
          element.setValue( node.jjtGetChild(i).jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).toString() );
        }

      }
      symbolTable_else.addVariablesV2(element);
    }

    symbolTable.addChild(symbolTable_else);

    return null;
  }



  /***/
  @Override
  public Object visit(ASTSTATEMENT node, Object data) {
    SymbolTable symbolTable = this.list_symbol_tables.getFirst().getChildren_list_of_symbol_tables().getFirst();
    SymbolTable symbolTable_statment = new SymbolTable(node.toString(), true);


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      Element element = new Element();

      //caso se for assignement
      if(node.jjtGetChild(i) instanceof ASTASSIGNMENT){
        element.setName(node.jjtGetChild(i).jjtGetChild(0).toString());
        element.setInitialized(true);
        Element element1 = new Element();
        element1.setName(node.jjtGetChild(i).jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).toString());
        element.setReturnValue(element1);
      }

      symbolTable_statment.addVariablesV2(element);

    }
    symbolTable.addChild(symbolTable_statment);
    return null;
  }


  /***/
  @Override
  public Object visit(ASTCONDITION node, Object data) {
    //obter a symbol table do if
    SymbolTable symbolTable = this.list_symbol_tables.getFirst().getChildren_list_of_symbol_tables().getFirst();
    SymbolTable symbolTable_cond = new SymbolTable(node.toString(), true);
    //Por uma symbol table no if
    symbolTable.addChild(symbolTable_cond);

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  /***/
  @Override
  public Object visit(ASTIF node, Object data) {

    SymbolTable symbolTable = new SymbolTable("IF",true);
    this.list_symbol_tables.getFirst().addChild(symbolTable);

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);

    }

    return null;
  }

  /***/
  @Override
  public Object visit(ASTLiteral node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }
  /***/
  @Override
  public Object visit(ASTMultiplicativeExpression node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }
  /***/
  @Override
  public Object visit(ASTAdditiveExpression node, Object data) {

    if(node.parent instanceof ASTMethodDeclaration){
      this.list_symbol_tables.getFirst().getVariablesv2().getFirst().setInitialized(true);
      String valorBooleanDefined = "true";
      this.list_symbol_tables.getFirst().getVariablesv2().getFirst().setValue(valorBooleanDefined);
    }

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {

      node.jjtGetChild(i).jjtAccept(this, data);

    }
    return null;
  }

  /***
   * Poe-se st LessThen com os seus dois fihos na st Condition de cada IF
   *
   * */
  @Override
  public Object visit(ASTLESSTHEN node, Object data) {
    SymbolTable conditional = this.list_symbol_tables.getFirst().
            getChildren_list_of_symbol_tables().getFirst().
            getChildren_list_of_symbol_tables().getFirst();

    SymbolTable lessthen = new SymbolTable(node.toString(),true);


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      Element element = new Element();
      String nameEle = node.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0).toString();
      element.setName(nameEle);

      if(node.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0) instanceof ASTLiteral){
        element.setType("int");
      }
      if(node.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0) instanceof ASTName){

      }

      lessthen.addVariablesV2(element);

    }
    conditional.addChild(lessthen);

    return null;
  }

  /**
   * */
  public Object visit(ASTASSIGNMENT node, Object data) {

    Element element = new Element();
    element.setName(node.jjtGetChild(0).toString());
    element.setInitialized(true);

    this.list_symbol_tables.getFirst().getVariablesv2().push(element);

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  @Override
  public Object visit(ASTConditionalAndExpression node, Object data) {
    return null;
  }

  /***/
  public Object visit(ASTName node, Object data) {
    if (node.parent instanceof ASTASSIGNMENT) {
      // this.list_symbol_tables.getFirst().getChildren_list_of_symbol_tables().getFirst().isConditional();

    }
    if (node.parent instanceof ASTMethodDeclaration) {
      Element element = new Element(node.value2,node.value);
      element.setInitialized(true);

      if(node.toString().equals("ioPlus") ||
              node.toString().equals("io") ||
              node.toString().equals("MathUtils") ){
        element.setValue(node.value2);
      } else {
        element.setValue(
                node.jjtGetParent()
                        .jjtGetChild(node.jjtGetParent().jjtGetNumChildren() - 1)
                        .jjtGetChild(0)
                        .jjtGetChild(0)
                        .jjtGetChild(0)
                        .toString());
      }

      this.list_symbol_tables.getFirst().addVariablesV2(element);

    }


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }

    return null;
  }

  public Object visit(ASTResultType node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  /** ********* */
  public Object visit(ASTPrimitiveType node, Object data) {
    // A diferenciação ocorre no nó superior ASTType
    // para boolean ou int
    return null;
  }

  /** Adiciona as variáveis ao método especificado Inserção na primeira st da lista global */
  @Override
  public Object visit(ASTType node, Object data) {

    if (node.parent instanceof ASTMethodDeclaration) {
      Element element = new Element();
      element.setType(node.jjtGetChild(0).toString());
      counter++;
      element.setVarnum(counter);
      this.list_symbol_tables.getFirst().addVariablesV2(element);

    } else if (node.parent instanceof ASTResultType) {
      this.list_symbol_tables.getFirst().setEnd_def_return_name(node.jjtGetChild(0).toString());
    }
    return null;
  }

  /** */
  @Override
  public Object visit(ASTConstructorDeclaration node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  /** Constrói os elementos dos argumentos e adciona à ultima st inserida */
  @Override
  public Object visit(ASTMethodDeclarator node, Object data) {

    SymbolTable st = this.list_symbol_tables.getFirst();

    String methodoName = node.value;
    String tipo_variavel = new String();
    String nome_do_argumento = new String();

    boolean flag_type = false;
    boolean flag_var = false;
    boolean is_array = false;

    // Aqui é que se distingue o main
    if (methodoName != null) {
      st.setName(methodoName);
    }

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      Element element = new Element();

      // argumentos de uma funcao type e nome da variavels
      if (node.jjtGetChild(i) instanceof ASTType) {

        tipo_variavel = node.jjtGetChild(i).jjtGetChild(0).toString();
        is_array = ((ASTType) node.jjtGetChild(i)).isArray;
        flag_type = true;

      } else if (node.jjtGetChild(i) instanceof ASTVariableDeclaratorId) {
        nome_do_argumento = ((ASTVariableDeclaratorId) node.jjtGetChild(i)).value;
        flag_var = true;

      }else if (node.jjtGetChild(i) instanceof ASTRETURN) {
        // acho que nao e aqui

      }


      if (flag_type && flag_var) {
        element.setType(tipo_variavel);
        element.setName(nome_do_argumento);
        element.setInitialized(true);
        element.setArray(is_array);
        st.getParameters().put(element.getName(), element);
        flag_type = false;
        flag_var = false;
      }

      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  /** Tratamento das inicializações */
  @Override
  public Object visit(ASTMethodDeclaration node, Object data) {
    SymbolTable temp_st = new SymbolTable();
    Element temp_element = new Element();

    this.list_symbol_tables.push(temp_st);

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }

    return null;
  }

  @Override
  public Object visit(ASTVariableDeclaratorId node, Object data) {

    if (node.parent instanceof ASTMethodDeclaration) {
      this.list_symbol_tables.getFirst().getVariablesv2().getFirst().setName(node.value);
      if (node.isArray) {
        this.list_symbol_tables.getFirst().getVariablesv2().getFirst().setArray(true);
      }
    }

    if (node.parent instanceof ASTUnmodifiedClassDeclaration) {
      System.out.println("Warning not sure why this");
      // TODO DISCUSS
    }
    //    Element var = new Element(node.value);
    //    this.list_symbol_tables.getFirst().addVariables();

    return null;
  }

  @Override
  public Object visit(ASTMethodDeclarationLookahead node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }

  /** */
  @Override
  public Object visit(ASTINIT node, Object data) {

    return null;
  }

  /** */
  @Override
  public Object visit(ASTFD node, Object data) {
    return null;
  }
  /** */
  @Override
  public Object visit(ASTMETODO node, Object data) {

    return null;
  }

  /** */
  // TODO needs working on the symbol table
  @Override
  public Object visit(ASTRETURN node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }

    String nodeName = (String) node.value;
    //        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
    //            String module = (String) node.jjtGetValue();
    //
    //               System.out.println("1Value : " );
    //               System.out.println( "1##" +  node.jjtGetChild(i).toString());
    //               System.out.println( "1##" + module + ": Unmodifed visit function >>>>>> AAA");
    //           }
    //

    //    if (node.jjtGetNumChildren() == 1) { // ultimo nó
    //      return new Element(nodeName, Type.ARRAY);
    //    } else {
    //      if (nodeName == null) {
    //        return new Element(nodeName, Type.UNDEFINED);
    //      } else {
    //        return new Element(nodeName, Type.INTEGER);
    //      }
    //    }
    return null;
  }
  /**
   * only for overidding
   *
   * @param
   * @param
   */
  public Object visit(ASTClassBodyDeclaration node, Object data) {
    return null;
  }

  /** */
  @Override
  public Object visit(ASTPrimarySuffix node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
  }
  /** */
  @Override
  public Object visit(ASTCastLookahead node, Object data) {
    return null;
  }

  /** */
  @Override
  public Object visit(ASTDIVMULT node, Object data) {
    return null;
  }

  /** */
  @Override
  public Object visit(ASTADDSUB node, Object data) {
    return null;
  }
  /** */
  @Override
  public Object visit(ASTBooleanLiteral node, Object data) {

    return null;
  }
  /** */
  public Object visit(ASTIntegerLiteral node, Object data) {
    return null;
  }
  /** */
  @Override
  public Object visit(ASTWHILE node, Object data) {
    return null;
  }
  /** */
  @Override
  public Object visit(ASTCONSTRUCTOR node, Object data) {
    return null;
  }
  /** */
  @Override
  public Object visit(ASTNCD node, Object data) {
    return null;
  }
  /** */
  @Override
  public Object visit(ASTINICIALIZACAO node, Object data) {
    return null;
  }
  /** */
  @Override
  public Object visit(ASTBODY node, Object data) {
    return null;
  }
}

