
import java.util.LinkedList;

public class JasminVisitor extends JasminGenerator implements JmmVisitor {

    private Type storeType = Type.UNDEFINED;

    public JasminVisitor(LinkedList<SymbolTable> list_symbol_tables) {
        super(list_symbol_tables);
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        return null;
    }


    @Override
    public Object visit(ASTStart node, Object data) {
        System.out.println("HElllo WORLD - Jasmim");
        node.jjtGetChild(0).jjtAccept(this, null);
        return null;
    }

    @Override
    public Object visit(ASTUnmodifiedClassDeclaration node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTINICIALIZACAO node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTNCD node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTCONSTRUCTOR node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTMETODO node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFD node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTMethodDeclarationLookahead node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTINIT node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTVariableDeclaratorId node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTMethodDeclarator node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTConstructorDeclaration node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTType node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTPrimitiveType node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTResultType node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTName node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTASSIGNMENT node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTLESSTHEN node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTAdditiveExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTADDSUB node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTMultiplicativeExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTDIVMULT node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTCastLookahead node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTPrimarySuffix node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTLiteral node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTBooleanLiteral node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIF node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTCONDITION node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSTATEMENT node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTELSE node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTWHILE node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTBODY node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTRETURN node, Object data) {
        return null;
    }

//    public Object visit(ASTModule node, Object data) {
//        String moduleName = (String) node.jjtGetValue();
//
//        this.jasminGenerator.writeModule(moduleName);
//
//        LinkedList<Element> elements = this.jasminGenerator.getRootSymbolTable().getElements();
//
//        int fields = this.jasminGenerator.writeFields(elements);
//
//        if( fields > 0)
//        {
//            this.jasminGenerator.writeInitMethod();
//        }
//        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
//            node.jjtGetChild(i).jjtAccept(this, data);
//            if (i == fields-1) {
//                this.jasminGenerator.writeEndMethod();
//            }
//        }
//
//        return null;
//    }
//
//    public Object visit(ASTDeclaration node, Object data) {
//        Element leftNode = (Element) node.jjtGetChild(0).jjtAccept(this, true);
//        if(leftNode.getType() != Type.ARRAY)
//            return null;
//
//        node.jjtGetChild(1).jjtAccept(this, false);
//        this.jasminGenerator.writePutstatic(leftNode);
//        return null;
//    }
//
//    public Object visit(ASTScalarDeclaration node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTSign node, Object data) {
//
//        return (String)node.jjtGetValue();
//    }
//
//    public Object visit(ASTScalar node, Object data) {
//        this.jasminGenerator.writeSingleWord((String)node.jjtGetValue());
//        return "I";
//    }
//
//    public Object visit(ASTArrayDeclaration node, Object data) {
//        node.jjtGetChild(0).jjtAccept(this,false);
//        this.jasminGenerator.writeArray();
//        return null;
//    }
//
//    public Object visit(ASTFunction node, Object data) {
//        SymbolTable currentSymbolTable = this.jasminGenerator.get_Top_Stack();
//        this.jasminGenerator.insert_Top_Stack_push(currentSymbolTable.popChild());
//        this.jasminGenerator.writeBeginMethod(this.jasminGenerator.get_Top_Stack());
//        this.jasminGenerator.writeStackAndLocals(20, this.jasminGenerator.get_Top_Stack().getLocals());
//
//        Element returnElement = this.jasminGenerator.get_Top_Stack().getReturnValue();
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
//        this.jasminGenerator.writeEndMethod(this.jasminGenerator.get_Top_Stack().getReturnValue());
//        this.jasminGenerator.popFront();
//        return null;
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
//        Element element = this.jasminGenerator.get_Top_Stack().getElement((String)node.value);
//
//        if((boolean) data)
//            return element;
//
//        this.jasminGenerator.writeLoadElement(element);
//        return element.getJasminType();
//    }
//
//    public Object visit(ASTArrayElement node, Object data) {
//        return null;
//    }
//
//    public Object visit(ASTStatements node, Object data) {
//        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
//            node.jjtGetChild(i).jjtAccept(this, true);
//        }
//        return null;
//    }
//
//    public Object visit(ASTAssign node, Object data) {
//        storeType = Type.UNDEFINED;
//        Element element = (Element) node.jjtGetChild(0).jjtAccept(this, true);
//        node.jjtGetChild(1).jjtAccept(this, false);
//        this.jasminGenerator.writeStoreElement(element, storeType == Type.INTEGER);
//        storeType = Type.UNDEFINED;
//
//        return null;
//    }
//
//    public Object visit(ASTOperation node, Object data) {
//        node.jjtGetChild(0).jjtAccept(this, false);
//        node.jjtGetChild(1).jjtAccept(this, false);
//
//        this.jasminGenerator.writeSign((String) node.value);
//
//        return null;
//    }
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
//
//    public Object visit(ASTTerm node, Object data) {
//
//        if(node.jjtGetNumChildren() == 2){
//            String sign = (String)node.jjtGetChild(0).jjtAccept(this, true);
//            node.jjtGetChild(1).jjtAccept(this, false);
//            if(sign.equals("-")){
//                this.jasminGenerator.writeIneg();
//            }
//        }
//        else{
//            node.jjtGetChild(0).jjtAccept(this, false);
//        }
//        return null;
//    }
//
//
//    public Object visit(ASTCall node, Object data) {
//        SymbolTable rootSymbolTable = this.jasminGenerator.getRootSymbolTable();
//
//        String types = (String)node.jjtGetChild(node.jjtGetNumChildren()-1).jjtAccept(this,false);
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
//    public Object visit(ASTFunctionName node, Object data) {
//        return node.value;
//    }
//
//    public Object visit(ASTSize node, Object data) {
//        this.jasminGenerator.writeArraySize();
//
//        return Boolean.FALSE;
//    }
//
//    public Object visit(ASTConditionalOperation node, Object data) {
//        ASTAccess leftNode = (ASTAccess)node.jjtGetChild(0);
//        leftNode.jjtAccept(this,false);
//        SimpleNode rightNode = (SimpleNode)node.jjtGetChild(1);
//        rightNode.jjtAccept(this,false);
//
//        return null;
//    }
//
//    public Object visit(ASTWhile node, Object data) {
//
//        ASTConditionalOperation conditionNode = (ASTConditionalOperation) node.jjtGetChild(0);
//        ASTStatements statementNode = (ASTStatements) node.jjtGetChild(1);
//
//        this.jasminGenerator.writeWhile(conditionNode, statementNode, this);
//
//        return null;
//    }
//
//    public Object visit(ASTIf node, Object data) {
//
//        ASTConditionalOperation conditionNode = (ASTConditionalOperation)node.jjtGetChild(0);
//        ASTStatements ifNode = (ASTStatements)node.jjtGetChild(1);
//        ASTStatements elseNode = null;
//        if(node.jjtGetNumChildren() > 2){
//            elseNode = (ASTStatements)node.jjtGetChild(2);
//        }
//
//        this.jasminGenerator.writeIf(conditionNode, ifNode, elseNode, this);
//
//        return null;
//    }
//
//    public Object visit(ASTArgumentList node, Object data) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i =0; i < node.jjtGetNumChildren(); i++){
//            stringBuilder.append(node.jjtGetChild(i).jjtAccept(this,false));
//        }
//        return stringBuilder.toString();
//        }
//
//    public Object visit(ASTString node, Object data) {
//        this.jasminGenerator.writeSingleWord((String)node.value);
//        return "Ljava/lang/String;";
//    }
}