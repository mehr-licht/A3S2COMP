import java.util.LinkedList;

public class SemanticVisitor extends SemanticManager implements JmmVisitor {

/***
 * Semantic rules for expressions, invocation of functions
 * */

    public SemanticVisitor(LinkedList<SymbolTable> list_symbol_tables ) {
        this.list_symbol_tables = list_symbol_tables;
    }

    public Object visit(ASTStart node, Object data) {
        System.out.println("HElllo WORLD from Smenantic");
        System.out.println(this.list_symbol_tables.getFirst().getName());
        node.jjtGetChild(0).jjtAccept(this, data);
        return null;
    }


    public Object visit(ASTClassBodyDeclaration node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        return null;
    }


    /**
     * Falta adicionar o extends Name()
     *
     * */
    public Object visit(ASTUnmodifiedClassDeclaration node, Object data) {
        //return super.visit(node, data); new Element
        return null;
    }
    public Object visit(ASTBODY node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTRETURN node, Object data) {
        return null;
    }


    public Object visit(ASTELSE node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTWHILE node, Object data) {
        return null;
    }

    public Object visit(ASTSTATEMENT node, Object data) {
        return null;
    }
    public Object visit(ASTCONDITION node, Object data) {
        return null;
    }
    public Object visit(ASTIF node, Object data) {
        return null;
    }
    public Object visit(ASTIntegerLiteral node, Object data) {
        return null;
    }

    public Object visit(ASTLiteral node, Object data) {
        return null;
    }

    public Object visit(ASTPrimarySuffix node, Object data) {
        return null;
    }
    public Object visit(ASTCastLookahead node, Object data) {
        return null;
    }
    public Object visit(ASTDIVMULT node, Object data) {
        return null;
    }

    public Object visit(ASTADDSUB node, Object data) {
        return null;
    }

    public Object visit(ASTMultiplicativeExpression node, Object data) {
        return null;
    }
    public Object visit(ASTAdditiveExpression node, Object data) {
        return null;
    }
    public Object visit(ASTLESSTHEN node, Object data) {
        return null;
    }
    /**
     * Exemplo dos acetatos on comments
     * */
    public Object visit(ASTBooleanLiteral node, Object data) {
    //return DataType.Boolean;
        return null;
    }

    /**
     * Exemplo do acetates em comments caso Assignment
     * */
    public Object visit(ASTASSIGNMENT node, Object data) {
        // Check identifier
        // DataType identType = node.jjtGetChild(0).visit(this,data);
        // if(identType == DataType.Error)
        //  return DataType.Error;
        //check expression
        //DataType exprType = node.jjtGetChild(1).visit(this,data);
        //if(identType != exprType)
        //{println("Error ASTAssignement");
        //    return DataType.Error;
        // }
        // return DataType.SKIP;
        return null;
    }
    /**
     * Exemplo dos acetatos em comments Casos do Identifier()
     * */
    public Object visit(ASTName node, Object data) {
        //String name = (String)node.jjtGetValue();
        //Consult SymbolTable
        //if(nameExists()){
        //  return DataTye
        // }else{
        // println("Error ASTIdentifier:  " +  name); )
        // return DataType.Error;
        // }
        return null;
    }
    public Object visit(ASTResultType node, Object data) {
        return null;
    }
    public Object visit(ASTPrimitiveType node, Object data) {
        return null;
    }
    public Object visit(ASTType node, Object data) {
        return null;
    }
    public Object visit(ASTConstructorDeclaration node, Object data) {
        return null;
    }
    public Object visit(ASTMethodDeclarator node, Object data) {
        return null;
    }

    public Object visit(ASTMethodDeclaration node, Object data) {
        return null;
    }

    public Object visit(ASTVariableDeclaratorId node, Object data) {
        return null;
    }

    public Object visit(ASTINIT node, Object data) {
        return null;
    }
    public Object visit(ASTMethodDeclarationLookahead node, Object data) {
        return null;
    }
    public Object visit(ASTFD node, Object data) {
        return null;
    }
    public Object visit(ASTMETODO node, Object data) {
        return null;
    }
    public Object visit(ASTCONSTRUCTOR node, Object data) {
        return null;
    }
    public Object visit(ASTNCD node, Object data) {
        return null;
    }
    public Object visit(ASTINICIALIZACAO node, Object data) {
        return null;
    }
    public Object visit(SimpleNode node, Object data) {
        return null;
    }


//    public Object visit(ASTModule node, Object data) {
//
//        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
//            node.jjtGetChild(i).jjtAccept(this, data);
//        }
//
//        return null;
//    }
//
//    public Object visit(ASTDeclaration node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTScalarDeclaration node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTSign node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTScalar node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTArrayDeclaration node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTFunction node, Object data) {
//        SymbolTable currenSymbolTable = this.globalSymbolTableList.get_Top_Stack();
//        this.globalSymbolTableList.insert_Top_Stack_push(currenSymbolTable.popChild());
//
//        node.jjtGetChild(2).jjtAccept(this, data);
//
//        this.globalSymbolTableList.popFront();
//
//        return null;
//
//    }
//
//    public Object visit(ASTReturn node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTParameters node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTVariable node, Object data) {
//
//        return (Element) this.globalSymbolTableList.get_Top_Stack().getElement((String) node.jjtGetValue());
//    }
//
//    public Object visit(ASTArrayElement node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTStatements node, Object data) {
//
//        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
//            node.jjtGetChild(i).jjtAccept(this, data);
//        }
//
//        return null;
//    }
//
//    public Object visit(ASTAssign node, Object data) {
//
//        Element element = (Element) node.jjtGetChild(0).jjtAccept(this, data);
//        Element function = (Element) node.jjtGetChild(1).jjtAccept(this, data);
//
//        if(function!= null) {
//            if(function.getType()== Type.UNDEFINED){
//                element.setType(Type.INTEGER);
//            }
//            else if (function.getReturn() != null) {
//                if (function.getReturn().getType() == Type.UNDEFINED) {
//                    SemanticManager.addError(node.line, "Cannot Assign Variable to Void!");
//                    return null;
//                }
//                if (element.getType() == Type.UNDEFINED) {
//                    element.setType(function.getReturn().getType());
//                }
//            }
//        }
//
//        return new Element(null, Type.UNDEFINED);
//    }
//
//    public Object visit(ASTOperation node, Object data) {
//
//        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
//            node.jjtGetChild(i).jjtAccept(this, data);
//        }
//
//        return null;
//    }
//
//    public Object visit(ASTAccess node, Object data) {
//        return this.globalSymbolTableList.get_Top_Stack().getElement((String) node.value);
//    }
//
//    public Object visit(ASTTerm node, Object data) {
//
//        return node.jjtGetChild(node.jjtGetNumChildren() - 1).jjtAccept(this, data);
//    }
//
//    public Object visit(ASTFunctionName node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTSize node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTConditionalOperation node, Object data) {
//
//        node.jjtGetChild(1).jjtAccept(this, data);
//
//        return null;
//    }
//
//    public Object visit(ASTWhile node, Object data) {
//
//
//
//        SymbolTable currenSymbolTable = this.globalSymbolTableList.get_Top_Stack();
//
//        node.jjtGetChild(0).jjtAccept(this, data);
//
//        this.globalSymbolTableList.insert_Top_Stack_push(currenSymbolTable.popChild());
//
//        node.jjtGetChild(1).jjtAccept(this, data);
//
//        this.globalSymbolTableList.popFront();
//
//        return null;
//    }
//
//    public Object visit(ASTIf node, Object data) {
//
//
//        SymbolTable currenSymbolTable = this.globalSymbolTableList.get_Top_Stack();
//        node.jjtGetChild(0).jjtAccept(this, data);
//
//        this.globalSymbolTableList.insert_Top_Stack_push(currenSymbolTable.popChild());
//
//        node.jjtGetChild(1).jjtAccept(this, data);
//
//        this.globalSymbolTableList.popFront();
//
//        if(node.jjtGetNumChildren() == 3){
//
//            this.globalSymbolTableList.insert_Top_Stack_push(currenSymbolTable.popChild());
//
//            node.jjtGetChild(2).jjtAccept(this, data);
//
//            this.globalSymbolTableList.popFront();
//        }
//        return null;
//    }
//
//    public Object visit(ASTCall node, Object data) {
//
//        if (node.jjtGetNumChildren() == 1) {
//
//            Element function = this.globalSymbolTableList.getRootSymbolTable().getElement((String) node.jjtGetValue());
//
//            if(function == null){
//                SemanticManager.addError(node.line,
//                        "Wrong function call : Function " + node.jjtGetValue() + " does not exist!");
//                return null;
//            }
//
//
//
//            LinkedList<Element> args = function.getArguments();
//            LinkedList<Element> parameters = (LinkedList<Element>) node.jjtGetChild(0).jjtAccept(this, data);
//
//
//            if(args.size() == 0 && node.jjtGetNumChildren() == 0)
//                return function;
//
//            if(node.jjtGetNumChildren() == 0 ){
//                SemanticManager.addError(node.line,
//                        "Incorrect function call on " + node.jjtGetValue() + " has illegal number of arguments! Should be " + args.size() + " argument(s).");
//                return null;
//            }
//
//            if(args.size() == 0 && parameters.size()!=0){
//                SemanticManager.addError(node.line,
//                        "Incorrect function call on " + node.jjtGetValue() + " has illegal number of arguments! This function does not accept any argument.");
//                return null;
//            }
//
//
//            int aux = args.size();
//
//            if (parameters.size() != args.size()) {
//
//                SemanticManager.addError(node.line, "Illegal number of arguments on " + node.jjtGetValue() + " Should be " + args.size() + " argument(s).");
//
//
//
//                if (aux > parameters.size()) {
//                    aux = parameters.size();
//                }
//            }
//
//            for (int i = 0; i < aux; i++) {
//
//                if(parameters.get(i) == null){
//                    continue;
//                }
//
//                if (parameters.get(i).getType() != args.get(i).getType()) {
//                    SemanticManager.addError(node.line,
//                            "Argument " + parameters.get(i).getName()
//                                    + " type error! Expected "
//                                    + args.get(i).getTypeStr() + " but got " + parameters.get(i).getTypeStr() + " instead!");
//                }
//            }
//            return function;
//        }
//
//        return null;
//    }
//
//    public Object visit(ASTArgumentList node, Object data) {
//
//        LinkedList<Element> vars = new LinkedList<Element>();
//
//        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
//            vars.add((Element) node.jjtGetChild(i).jjtAccept(this, data));
//        }
//
//        return vars;
//    }
//
//    public Object visit(ASTString node, Object data) {
//        return null;
//    }
}
