import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JasminVisitor extends JasminGenerator implements JmmVisitor {

  private Type storeType = Type.UNDEFINED;
  static  int counter = -1;
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
    boolean case_no_extends = true;

    this.getWriter().print(".class public ");
    this.getWriter().println(node.value);
    this.lineNumber++;
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {

      if (node.jjtGetChild(i) instanceof ASTName) {
        extends_class = ((ASTName) node.jjtGetChild(i)).value;
        this.getWriter().println(".super java/lang/" + extends_class);
        this.lineNumber++;
        case_no_extends = false;
        this.getWriter().println("");
//        this.getWriter().println(".method public <init>()V");
//        this.getWriter().println("aload_0");
        //da classe ques estmoas a estender
//        this.getWriter().println("invokespecial java/lang/Object/<init>()V");
//        this.getWriter().println(".end method");
        this.getWriter().println("");
      }
      // caso nao haja extendeds faz este print
      if (case_no_extends) {
        this.getWriter().println(".super java/lang/Object");
        this.getWriter().println("");
        this.lineNumber++;
//        this.getWriter().println(".method public <init>()V");
//        this.getWriter().println("aload_0");
//        this.getWriter().println("invokespecial java/lang/Object/<init>()V");
//        this.getWriter().println(".end method");
        this.getWriter().println("");
      }

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

    //leitura do valor de retorno
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
//       caso outros metodos
        } else {

          this.getWriter().print(".method public ");

          this.getWriter().print(((ASTMethodDeclarator) node.jjtGetChild(i)).value);
          this.getWriter().print("(");

          if (node.jjtGetChild(i).jjtGetNumChildren() > 0) {
            // impressão dos argumentos do meétodos
            for (int j = 0; j < node.jjtGetChild(i).jjtGetNumChildren(); j++) {

              if (node.jjtGetChild(i).jjtGetChild(j) instanceof ASTType) {

                String tmp = node.jjtGetChild(i).jjtGetChild(j).jjtGetChild(0).toString();
                boolean checkisArray = ((ASTType) node.jjtGetChild(i).jjtGetChild(j)).isArray;
                String conversion_types = JasminGenerator.conversitonTypesArguments(tmp, checkisArray);
                this.getWriter().print(conversion_types);

              } else {
                //TODO faz o push das variaveis
                //prints de istore  iload ??
//                Imprime os nomes dos argumentos (ja nao é necessario)
//                String tmp = node.jjtGetChild(i).jjtGetChild(j).toString();
//                this.getWriter().print(tmp);
              }

              //Impressao da virgula
              if (j % 2 == 1) {
                this.getWriter().print("; ");
              }

            } //end ciclo de leitura dos argumentos

          } //end if quando há filhos/ ou seja quando nao ha argumentos

          this.getWriter().print(")");
          String conversion_types = JasminGenerator.conversitonTypesArguments(resulting_type, check_method_return);
          this.getWriter().println("" + conversion_types);
          this.lineNumber++;
          //print stacks //TODO HARDCODDE
          this.getWriter().print(".limit locals ");
          this.getWriter().println("5");
          this.lineNumber++;
          this.getWriter().print(".limit stack ");
          this.getWriter().println("10");
          this.lineNumber++;
        }
      }


      // Se nao houver return no codigo java print o default para o jasmim
      if (node.jjtGetChild(i) instanceof ASTRETURN){
        check_ast_return = true;
      }
      node.jjtGetChild(i).jjtAccept(this, data);
    }

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
    node.jjtGetChild(0).jjtAccept(this, data);
    return null;
  }

  @Override
  public Object visit(ASTPrimitiveType node, Object data) {
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

      if(node.value.toString().equals("ioPlus")){

        if(node.value2.toString().equals("print") || node.value2.toString().equals("println")){

          String datatype = node.jjtGetParent().jjtGetChild(node.jjtGetParent().jjtGetNumChildren()-1).
                  jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).toString();
          String init_type = JasminGenerator.conversitonTypesArguments(datatype,false);

          //Caso em que o argumento eh o nome de uma variavel
          if(init_type == null){
            //procura o tipo de eleemnto. //tipo de no
            //-------------COULD DO A REFACTOR ----BEGIN //
            Element newDataType = JasminGenerator.find_type_element(this.list_symbol_tables, datatype, ((ASTMethodDeclarator) node.jjtGetParent().jjtGetChild(1)).value);
            type_to_print = JasminGenerator.conversitonTypesArguments(newDataType.getType(),false);
            //-------------COULD DO A REFACTOR ----END //

            //fazer o load da variavel no argumento do metodo
            this.getWriter().print("iload ");
            int index = mapStores.get(newDataType.getName());
            this.getWriter().println(index);
            this.lineNumber++;
          }

          this.getWriter().println("invokestatic io/" + node.value2 +"("+ type_to_print + ")V");
          this.lineNumber++;
        }

      }else if(node.value.toString().equals("MathUtils")){
        this.getWriter().println("invoke MathUtils/" + node.value2);
        this.lineNumber++;
      }else{
        this.getWriter().print("ldc ");
        this.getWriter().println(node.value);
        this.lineNumber++;
      }

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


      //Assignment de booleanos
    }else if(node.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0).
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


    }else{
      this.getWriter().println("WARNING //TODO");

    }
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


    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
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

    boolean flag = false;



    for (int i = 0; i < node.jjtGetNumChildren(); i++) {


      //Processar a condicao //TODO falta testar com &&
      if(node.jjtGetChild(i) instanceof ASTCONDITION ){
        node.jjtGetChild(i).jjtAccept(this, data);
        //depois de por os filhos na stack escreve
        this.getWriter().print("if_icmpge ");
        this.getWriter().println(middleLabel);

      }else if(node.jjtGetChild(i) instanceof ASTSTATEMENT ){
        node.jjtGetChild(i).jjtAccept(this, data);

        this.getWriter().print("goto ");
        this.getWriter().println(finalLabel);
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
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
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
    // caso em que  return; case voild
    if (node.jjtGetNumChildren() == 0) {
      this.getWriter().println("return");
      this.lineNumber++;
      this.getWriter().println(".end method");
      this.lineNumber++;
      return null;
    } else { // caso em que efectivamente retorna algo

      // caso de inteiros
      if (node.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0) instanceof ASTLiteral) {
        node.jjtGetChild(0).jjtAccept(this, data);
        this.getWriter().println("ireturn");
        this.lineNumber++;
      } else if (false) { // caso arays //TODO
        this.getWriter().println("areturn");
        this.lineNumber++;
      } else if (false) { // caso de variables
        // ret 2 return ti the address held in local variablw 2
        // JVM return from subroutine
      }
      this.getWriter().println(".end method");
      this.lineNumber++;
    }

    return null;
  }

  //    public Object visit(ASTFunction node, Object data) {
  //        SymbolTable currentSymbolTable = this.jasminGenerator.get_Top_Stack();
  //        this.jasminGenerator.insert_Top_Stack_push(currentSymbolTable.popChild());
  //        this.jasminGenerator.writeBeginMethod(this.jasminGenerator.get_Top_Stack());
  //        this.jasminGenerator.writeStackAndLocals(20,
  // this.jasminGenerator.get_Top_Stack().getLocals());
  //
  //        Element returnElement = this.jasminGenerator.get_Top_Stack().getEnd_return_element();
  //        if(returnElement.getType() != Type.UNDEFINED){
  //            this.jasminGenerator.writeLoadScalar(0);
  //            if(returnElement.getType() == Type.ARRAY){
  //                this.jasminGenerator.writeArray();
  //            }
  //            this.jasminGenerator.writeStoreElement(returnElement, false);
  //        }
  //        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
  //            node.jjtGetChild(i).jjtAccept(this, data);
  //        }
  //
  // this.jasminGenerator.writeEndMethod(this.jasminGenerator.get_Top_Stack().getEnd_return_element());
  //        this.jasminGenerator.popFront();
  //        return null;
  //    }
  //
  //
  //
  //    public Object visit(ASTAccess node, Object data) {
  //        SymbolTable currentSymbolTable = this.jasminGenerator.get_Top_Stack();
  //        Element element = currentSymbolTable.getElement((String) node.value);
  //
  //        if((boolean) data){
  //            if(element.getType() == Type.ARRAY){
  //                if(node.jjtGetNumChildren() == 0){
  //                    storeType = Type.ARRAY;
  //                }
  //                else{
  //                    this.jasminGenerator.writeLoadElement(element);
  //                    node.jjtGetChild(0).jjtAccept(this,false);
  //                    storeType = Type.INTEGER;
  //                }
  //            }
  //            else {
  //                storeType = Type.INTEGER;
  //            }
  //            return element;
  //        }
  //
  //        this.jasminGenerator.writeLoadElement(element);
  //
  //        if(element.getType() != Type.ARRAY)
  //            return null;
  //
  //        Object object = node.jjtGetChild(0).jjtAccept(this, false);
  //        if(object!= null){
  //            if(object instanceof Boolean){
  //                if(!(Boolean)object)
  //                    return null;
  //            }
  //        }
  //
  //        this.jasminGenerator.writeIaload();
  //
  //        return null;
  //    }

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
