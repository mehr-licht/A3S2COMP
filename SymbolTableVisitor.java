import java.util.LinkedList;

public class SymbolTableVisitor extends Global_Symbol_Table_List implements JmmVisitor {

  public SymbolTableVisitor() {
    this.list_symbol_tables = new LinkedList<SymbolTable>();
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

    // TODO
    // Only for debugging reasons
    System.out.println("last and the");
    return null;
  }



  /** * */
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



  /**  */
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


  /** */
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
  /** */
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

  /***/
  public Object visit(ASTName node, Object data) {
    if (node.parent instanceof ASTASSIGNMENT) {
      // this.list_symbol_tables.getFirst().getChildren_list_of_symbol_tables().getFirst().isConditional();

    }
    if (node.parent instanceof ASTMethodDeclaration) {
      Element element = new Element(node.value2,node.value);
      element.setInitialized(true);
      this.list_symbol_tables.getFirst().addVariablesV2(element);
      element.setValue( node.jjtGetParent().jjtGetChild(node.jjtGetParent().jjtGetNumChildren()-1).
              jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).toString() );
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

/** NOTES ******* */

  //    public Object visit(ASTDeclaration node, Object data) {
  //        SymbolTable currentSymbolTable = this.globalSymbolTableList.get_Top_Stack();
  //
  //        boolean initialized = false;
  //        Type variableType = Type.UNDEFINED;
  //
  //        Element leftType = (Element) node.jjtGetChild(0).jjtAccept(this, data);
  //        Element rightType = null;
  //        if (node.jjtGetNumChildren() == 2) {
  //
  //            rightType = (Element) node.jjtGetChild(1).jjtAccept(this, data);
  //
  //            if (rightType.getType() == Type.INTEGER && leftType.getType() == Type.INTEGER) {
  //                initialized = true;
  //                variableType = rightType.getType();
  //
  //            } else if (rightType.isInitialized()) {
  //
  //                if (rightType.getType() == Type.ARRAY) {
  //                    initialized = true;
  //                    variableType = Type.ARRAY;
  //                } else {
  ////                    SemanticManager.addError(node.line,
  ////                            "Error Assigning Variable " + leftType.getName() + " -> Type array
  // is incompatible with Type int!");
  //                }
  //            } else {
  ////                SemanticManager.addError(node.line,
  ////                        "Error Assigning Variable " + rightType.getName() + " -> Was not
  // initialized!");
  ////                SemanticManager.addError(node.line,
  ////                        "Error Assigning Variable " + leftType.getName() + " -> Cannot assign
  // a variable to Undefined!");
  //            }
  //        } else {
  //            variableType = leftType.getType();
  //        }
  //
  //        Element element = null;
  //
  //        element = new Element(leftType.getName(), variableType);
  //        element.setInitialized(initialized);
  //
  //        if(rightType!=null){
  //            element.setValue(rightType.getValue());
  //        }
  //
  //        currentSymbolTable.addVariables(element);
  //
  //        return null;
  //    }

  //    public Object visit(ASTScalarDeclaration node, Object data) {
  //        Element element = (Element) node.jjtGetChild(node.jjtGetNumChildren() -
  // 1).jjtAccept(this, null);
  //
  //        if (node.jjtGetNumChildren() == 2) {
  //            element.setValue("-" + element.getValue());
  //        }
  //        return new Element("", Type.INTEGER, true, element.value);
  //    }

  //    public Object visit(ASTSign node, Object data) {
  //        return null;
  //    }

  //    public Object visit(ASTScalar node, Object data) {
  //        return new Element("", Type.INTEGER, true, node.value);
  //    }

  //    public Object visit(ASTArrayDeclaration node, Object data) {
  //
  //        Element element = (Element) node.jjtGetChild(0).jjtAccept(this, true);
  //
  //        if (element == null) {
  //            return new Element(element.getName(), Type.ARRAY, false);
  //        }
  //
  //        return new Element(element.getName(), Type.ARRAY, element.isInitialized());
  //    }

  //    public Object visit(ASTFunction node, Object data) {
  //
  //        LinkedList<Element> parameters = (LinkedList<Element>)
  // node.jjtGetChild(1).jjtAccept(this, data);
  //        Element returnValue = (Element) node.jjtGetChild(0).jjtAccept(this, data);
  //        String functionName = (String) node.jjtGetValue();
  //
  //        Element function = new Element(functionName, true, returnValue, parameters);
  //        function.setInitialized(true);
  //
  //        if(parameters!= null) {
  //            for (Element element : parameters) {
  //                if (element.getName().equals(returnValue.getName())) {
  //                    returnValue.setInitialized(true);
  //                }
  //            }
  //        }
  //        SymbolTable currentST = this.globalSymbolTableList.get_Top_Stack();
  //
  //        currentST.addVariables(function);
  //        currentST.addChild(new SymbolTable(functionName, returnValue, parameters));
  //
  //        this.globalSymbolTableList.insert_Top_Stack_push(currentST.popChild());
  //        node.jjtGetChild(2).jjtAccept(this, data);
  //
  //
  /// *        if (!returnValue.isInitialized() && returnValue.getType() != Type.UNDEFINED) {
  //            SemanticManager.addError(node.line, "Error: Function " + functionName + " must
  // return a value!");
  //        }*/
  //
  //        this.globalSymbolTableList.popFront();
  //        return null;
  //    }
  //    public Object visit(ASTParameters node, Object data) {
  //
  //        LinkedList<Element> elements = new LinkedList<Element>();
  //
  //        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
  //
  //            Element element = (Element) node.jjtGetChild(i).jjtAccept(this, data);
  //            element.setInitialized(true);
  //
  //            elements.add(element);
  //        }
  //        return elements;
  //    }

  //    public Object visit(ASTVariable node, Object data) {
  //
  //        if (node.jjtGetNumChildren() > 0)
  //            return new Element((String) node.jjtGetValue(), Type.ARRAY);
  //
  //        return new Element((String) node.jjtGetValue(), Type.INTEGER);
  //    }

  //    public Object visit(ASTArrayElement node, Object data) {
  //        return null;
  //    }

  //    public Object visit(ASTStatements node, Object data) {
  //        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
  //            node.jjtGetChild(i).jjtAccept(this, data);
  //        }
  //        return null;
  //    }

  //    public Object visit(ASTAssign node, Object data) {
  //
  //        Element leftElement = (Element) node.jjtGetChild(0).jjtAccept(this, data);
  //
  //
  //
  //
  //        if (leftElement == null) {
  ////            SemanticManager.addError(node.line,
  ////                    "Error: Left Side Variable is undefined!");
  //            return null;
  //        }
  //
  //        Element rightElement = (Element) node.jjtGetChild(1).jjtAccept(this, data);
  //        if(rightElement == null){
  ////            SemanticManager.addError(node.line,
  ////                    "Error: Right Side Variable is undefined!");
  //            return null;
  //        }
  //
  //        System.out.println(leftElement.toString());
  //        System.out.println(rightElement.toString());
  //
  //        if (leftElement.getType() == Type.UNDEFINED && !leftElement.isInitialized()) {
  //            leftElement.setType(rightElement.getType());
  //            this.globalSymbolTableList.get_Top_Stack().addVariables(leftElement);
  //
  //        } else if (leftElement.getType() == Type.FUNCTION) {
  //
  //            leftElement = new Element(leftElement.getName(), rightElement.getType());
  //            this.globalSymbolTableList.get_Top_Stack().addVariables(leftElement);
  //        }
  //
  //        if (!rightElement.isInitialized()) {
  ////            SemanticManager.addError(node.line,
  ////                    "Error variable: " + leftElement.getName() + " -> Cannot Assign a variable
  // to undefined!");
  //
  //        } else if (leftElement.getType() == Type.UNDEFINED || rightElement.getType() ==
  // Type.UNDEFINED) {
  //
  //            if (leftElement.getType() == Type.UNDEFINED) {
  //                leftElement.setType(rightElement.getType());
  //            }
  //
  //            leftElement.setInitialized(rightElement.isInitialized());
  //
  //        } else if (leftElement.getType() == rightElement.getType()) {
  //            leftElement.setInitialized(rightElement.isInitialized());
  //        } else {
  ////            SemanticManager.addError(node.line,
  ////                    "Error: Right side variable of type " +
  // Type.getTypeStr(leftElement.getType()) + " is incompatible with type " +
  // Type.getTypeStr(rightElement.getType()));
  //        }
  //
  //        return null;
  //    }

  //    public Object visit(ASTOperation node, Object data) {
  //
  //        Element leftElement = (Element) node.jjtGetChild(0).jjtAccept(this, data);
  //        Element rightElement = (Element) node.jjtGetChild(1).jjtAccept(this, data);
  //
  //        if (!leftElement.isInitialized()) {
  //
  //            if
  // (this.globalSymbolTableList.get_Top_Stack().getElement(leftElement.getName()) ==
  // null) {
  ////                SemanticManager.addError(node.line,
  ////                        "Error: Left side variable: " + leftElement.getName() + " is
  // undefined!");
  //
  //            } else {
  ////                SemanticManager.addError(node.line,
  ////                        "Error: Left side variable: " + leftElement.getName() + " is not
  // initialized!");
  //
  //            }
  //        }
  //
  //        if (!rightElement.isInitialized()) {
  //
  //            if
  // (this.globalSymbolTableList.get_Top_Stack().getElement(rightElement.getName()) ==
  // null) {
  ////                SemanticManager.addError(node.line,
  ////                        "Error: Right side variable: " + rightElement.getName() + " is
  // undefined");
  //
  //            } else {
  ////                SemanticManager.addError(node.line,
  ////                        "Error: Right side var:" + rightElement.getName() + " is not
  // initialized!");
  //            }
  //        }
  //
  //
  //        if (leftElement.getType() == Type.UNDEFINED || rightElement.getType() == Type.UNDEFINED)
  // {
  //            return new Element("", Type.UNDEFINED, true);
  //        }
  //
  //        if (leftElement.getType() == rightElement.getType())
  //            return new Element("", leftElement.getType(), true);
  //
  //        if (leftElement.getType() != Type.UNDEFINED && rightElement.getType() != Type.UNDEFINED)
  // {
  //
  ////            SemanticManager.addError(node.line,
  ////                    "Error: Right side variable of type " +
  // Type.getTypeStr(rightElement.getType()) + " is incompatible with type left side variable of
  // type " + Type.getTypeStr(leftElement.getType()));
  //        }
  //
  //        return new Element("", Type.UNDEFINED);
  //    }

  //    public Object visit(ASTAccess node, Object data) {
  //        SymbolTable currentSymbolTable = this.globalSymbolTableList.get_Top_Stack();
  //        Element element = currentSymbolTable.getElement((String) node.jjtGetValue());
  //
  //        if (element == null) {
  //            return new Element((String) node.jjtGetValue(), Type.UNDEFINED);
  //        }
  //
  //        if (node.jjtGetNumChildren() == 1 && element.getType() != Type.ARRAY &&
  // element.getType()!= Type.UNDEFINED) {
  ////            SemanticManager.addError(node.line,
  ////                    "Error: variable " + node.jjtGetValue() + " isn't an array!");
  //
  //            return null;
  //
  //        } else if (node.jjtGetNumChildren() == 1) {
  //
  //            Element child = (Element) node.jjtGetChild(0).jjtAccept(this, null);
  //
  //            if (!child.isInitialized()) {
  //                Element childSymbolTable = currentSymbolTable.getElement(child.getName());
  //                if (childSymbolTable == null) {
  ////                    SemanticManager.addError(node.line,
  ////                            "Error: variable " + child.getName() + " is not declared!");
  //                    return null;
  //
  //                } else if (!childSymbolTable.isInitialized()) {
  ////                    SemanticManager.addError(node.line,
  ////                            "Error: variable " + child.getName() + " is not initialized!");
  //                    return null;
  //                }
  //                return childSymbolTable;
  //            }
  //            return child;
  //        }
  //        return element;
  //    }

  //    public Object visit(ASTTerm node, Object data) {
  //
  //        return (Element) node.jjtGetChild(node.jjtGetNumChildren() - 1).jjtAccept(this, data);
  //
  //    }

  //    public Object visit(ASTFunctionName node, Object data) {
  //        return null;
  //    }

  //    public Object visit(ASTSize node, Object data) {
  //        return new Element("", Type.INTEGER, true);
  //    }

  //    public Object visit(ASTConditionalOperation node, Object data) {
  //
  //        Element leftElement = (Element) node.jjtGetChild(0).jjtAccept(this, true);
  //
  //        if (leftElement == null) {
  ////            SemanticManager.addError(node.line,
  ////                    "Error: Left side variable is undefined!");
  //            return null;
  //
  //        } else if (!leftElement.isInitialized()) {
  ////            SemanticManager.addError(node.line,
  ////                    "Error: Left side variable " + leftElement.getName() + " isn't
  // initialized!");
  //        }
  //
  //
  //        Element rightElement = (Element) node.jjtGetChild(1).jjtAccept(this, true);
  //
  //        if (rightElement == null) {
  ////            SemanticManager.addError(node.line,
  ////                    "Error: Right side variable is undefined!");
  //            return null;
  //        }
  //
  //        if (!rightElement.isInitialized()) {
  ////            SemanticManager.addError(node.line,
  ////                    "Error: Right side variable " + rightElement.getName() + " isn't
  // initialized!");
  //
  //        } else if (rightElement.getType() != Type.UNDEFINED && leftElement.getType() !=
  // Type.UNDEFINED) {
  //            if (leftElement.getType() != rightElement.getType()) {
  ////                SemanticManager.addError(node.line,
  ////                        "Error: Right side variable of type" +
  // Type.getTypeStr(rightElement.getType()) + " is incompatible with left side variable of type " +
  // Type.getTypeStr(leftElement.getType()));
  //            }
  //        }
  //
  //        return null;
  //    }
