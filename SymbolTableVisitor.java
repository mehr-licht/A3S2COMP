import java.util.LinkedList;

public class SymbolTableVisitor implements JmmVisitor {
    
    SymbolTableContextManager symbolTableContextManager;



    public SymbolTableVisitor(){  
        System.out.println("constructor01 - SymbolTableVisitor  >>>>>>>>>>>>>>>>>>>>>>>>");
    }
  
    /**
     * Usa este constructor
     * */
    public SymbolTableVisitor(SymbolTableContextManager symbolTableContextManager) {
        this.symbolTableContextManager = symbolTableContextManager;
    }


    public Object visit(SimpleNode node, Object data) {
        return null;
    }

    /**
     * @param
     * @param
    */
    public Object visit(ASTStart node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        return null;
    }

    /**
     *
     *
     * */
    public Object visit(ASTClassBodyDeclaration node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        return null;
    }


    /**
     * @param
     * @param
     * */
    public Object visit(ASTUnmodifiedClassDeclaration node, Object data) {

        SymbolTable currentSymbolTable = this.symbolTableContextManager.getCurrentSymbolTable();

        String className = node.value;

        try {
            if (className != null) {
                System.out.println(" -- Numero de filhos: " + node.jjtGetNumChildren() );
                Element element = new Element(node.value,Type.CLASS);
                currentSymbolTable.addElement(element);
                System.out.println(" -- Numero de filhos: " + node.jjtGetNumChildren() );
            }
        } catch (LinkageError e) {
            // Semantica err nao existe no class
            //	LOG.log(Level.WARNING, "Could not find class " + className + ", due to: " + e.getClass().getName() + ": " + e.getMessage());
        }
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("_value_ :" + node.jjtGetChild(i).toString() + ": 03>>");
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    /**
     * //TODO
     * */
    public Object visit(ASTBODY node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println("Body visit function >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }


     //TODO
    /**
     * */
    public Object visit(ASTELSE node, Object data) {
        return null;
    }
    /**
     * //TODO
     * */
    public Object visit(ASTSTATEMENT node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("stmt>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
    /**
     * //TODO
     */
    public Object visit(ASTCONDITION node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println("cond>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    /**
     * Copy from the other branch
     */
    public Object visit(ASTIF node, Object data) {
        System.out.println("IF>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
     
        SymbolTable currentSymbolTable = this.symbolTableContextManager.getCurrentSymbolTable();

        node.jjtGetChild(0).jjtAccept(this, data); //if
        currentSymbolTable.addChild(new SymbolTable("If", true));
        this.symbolTableContextManager.pushFront(currentSymbolTable.popChild());

        node.jjtGetChild(1).jjtAccept(this, data); //if

        if (node.jjtGetNumChildren() == 3) //if else
            node.jjtGetChild(2).jjtAccept(this, data);

        this.symbolTableContextManager.popFront();
        return null;

    }
    /**
     * //TODO
     */
    public Object visit(ASTBooleanLiteral node, Object data) {
        System.out.println("bool>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    /**
     * //TODO
     */
    public Object visit(ASTIntegerLiteral node, Object data) {
        System.out.println("INItLit>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    /**
     * //TODO
     */
    public Object visit(ASTLiteral node, Object data) {
        System.out.println("lit>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTPrimarySuffix node, Object data) {
        System.out.println("PrimSuff>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTCastLookahead node, Object data) {
        System.out.println("CAStlook>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTDIVMULT node, Object data) {
        System.out.println("DivMult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }

    public Object visit(ASTADDSUB node, Object data) {
        System.out.println("AddSub>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }

    public Object visit(ASTMultiplicativeExpression node, Object data) {
        System.out.println("MultiplicativeExpr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTAdditiveExpression node, Object data) {
        System.out.println("MultiplicativeExpr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTLESSTHEN node, Object data) {
        System.out.println("LT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTASSIGNMENT node, Object data) {
      
        System.out.println("Assign>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTName node, Object data) {
        
        /*
		 * Only doing this for nodes where getNameDeclaration is null this means
		 * it's not a named node, i.e. Static reference or Annotation Doing this
		 * for memory - TODO: Investigate if there is a valid memory concern or
		 * not
		 
		if (node.getNameDeclaration() == null) {
			// Skip these scenarios as there is no type to populate in these cases:
			// 1) Parent is a PackageDeclaration, which is not a type
			// 2) Parent is a ImportDeclaration, this is handled elsewhere.
			if (!(node.jjtGetParent() instanceof ASTPackageDeclaration || node.jjtGetParent() instanceof ASTImportDeclaration)) {
				String name = node.getImage();
				if (name.indexOf('.') != -1) {
					name = name.substring(0, name.indexOf('.'));
				}
				populateType(node, name);
			}
		} else {
			// Carry over the type from the declaration
			if (node.getNameDeclaration().getNode() instanceof TypeNode) {
				node.setType(((TypeNode)node.getNameDeclaration().getNode()).getType());
			}
		}
        return super.visit(node, data);
        */
        return null;
    }
    public Object visit(ASTResultType node, Object data) {
        System.out.println("resultType>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTPrimitiveType node, Object data) {
    /*
        populateType(node, node.getImage());
        return super.visit(node, data);
    */
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
        System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
        node.jjtGetChild(i).jjtAccept(this, data);
    }
    return null;
    }
    public Object visit(ASTType node, Object data) {
      /*  super.visit(node, data);
		rollupTypeUnary(node);
        return data;
        */
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTConstructorDeclaration node, Object data) {
        System.out.println("constructorDecl>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return null;
    }
    public Object visit(ASTMethodDeclarator node, Object data) {

        System.out.println("04");
        String methodoName = node.value;
        if(node.value != null){
            //addciono o tipo da funcao void int Class
            //methodoName Element(methodoName, TYPE something);
            //add(Elemente)

        }

        System.out.println("04");


        return null;
    }

    public Object visit(ASTMethodDeclaration node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        } 
        return null;
    }

    public Object visit(ASTVariableDeclaratorId node, Object data) {
  
        LinkedList<Element> elements = new LinkedList<Element>();

        for (int i = 0; i < node.jjtGetNumChildren(); i++) {

            Element element = (Element) node.jjtGetChild(i).jjtAccept(this, data);
            element.setInitialized(true);

            elements.add(element);
        }
        return elements;
    }

    public Object visit(ASTINIT node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("INIT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
    public Object visit(ASTMethodDeclarationLookahead node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("MethoddeclLook>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
    public Object visit(ASTFD node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("TFD>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
    public Object visit(ASTMETODO node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("METODO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
    public Object visit(ASTCONSTRUCTOR node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("CONSTRUCTOR>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
    public Object visit(ASTNCD node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("NCD>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
    public Object visit(ASTINICIALIZACAO node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println("Value :" + node.jjtGetChild(i).toString() + ": Unmodifed visit function 01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");           
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("INICIALIZA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }
    //    public Object visit(ASTDeclaration node, Object data) {
//        SymbolTable currentSymbolTable = this.symbolTableContextManager.getCurrentSymbolTable();
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
////                            "Error Assigning Variable " + leftType.getName() + " -> Type array is incompatible with Type int!");
//                }
//            } else {
////                SemanticManager.addError(node.line,
////                        "Error Assigning Variable " + rightType.getName() + " -> Was not initialized!");
////                SemanticManager.addError(node.line,
////                        "Error Assigning Variable " + leftType.getName() + " -> Cannot assign a variable to Undefined!");
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
//        currentSymbolTable.addElement(element);
//
//        return null;
//    }

//    public Object visit(ASTScalarDeclaration node, Object data) {
//        Element element = (Element) node.jjtGetChild(node.jjtGetNumChildren() - 1).jjtAccept(this, null);
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
//        LinkedList<Element> parameters = (LinkedList<Element>) node.jjtGetChild(1).jjtAccept(this, data);
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
//        SymbolTable currentST = this.symbolTableContextManager.getCurrentSymbolTable();
//
//        currentST.addElement(function);
//        currentST.addChild(new SymbolTable(functionName, returnValue, parameters));
//
//        this.symbolTableContextManager.pushFront(currentST.popChild());
//        node.jjtGetChild(2).jjtAccept(this, data);
//
//
///*        if (!returnValue.isInitialized() && returnValue.getType() != Type.UNDEFINED) {
//            SemanticManager.addError(node.line, "Error: Function " + functionName + " must return a value!");
//        }*/
//
//        this.symbolTableContextManager.popFront();
//        return null;
//    }

    public Object visit(ASTRETURN node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println("return>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        String nodeName = (String) node.value;
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            String module = (String) node.jjtGetValue();

               System.out.println("1Value : " );
               System.out.println( "1##" +  node.jjtGetChild(i).toString());
               System.out.println( "1##" + module + ": Unmodifed visit function >>>>>> AAA");
           }
     

        if (node.jjtGetNumChildren() == 1) { //ultimo nó
            return new Element(nodeName, Type.ARRAY);
        } else {
            if (nodeName == null) {
                return new Element(nodeName, Type.UNDEFINED);
            } else {
                return new Element(nodeName, Type.INTEGER);
            }
        }
    }

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
//            this.symbolTableContextManager.getCurrentSymbolTable().addElement(leftElement);
//
//        } else if (leftElement.getType() == Type.FUNCTION) {
//
//            leftElement = new Element(leftElement.getName(), rightElement.getType());
//            this.symbolTableContextManager.getCurrentSymbolTable().addElement(leftElement);
//        }
//
//        if (!rightElement.isInitialized()) {
////            SemanticManager.addError(node.line,
////                    "Error variable: " + leftElement.getName() + " -> Cannot Assign a variable to undefined!");
//
//        } else if (leftElement.getType() == Type.UNDEFINED || rightElement.getType() == Type.UNDEFINED) {
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
////                    "Error: Right side variable of type " + Type.getTypeStr(leftElement.getType()) + " is incompatible with type " + Type.getTypeStr(rightElement.getType()));
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
//            if (this.symbolTableContextManager.getCurrentSymbolTable().getElement(leftElement.getName()) == null) {
////                SemanticManager.addError(node.line,
////                        "Error: Left side variable: " + leftElement.getName() + " is undefined!");
//
//            } else {
////                SemanticManager.addError(node.line,
////                        "Error: Left side variable: " + leftElement.getName() + " is not initialized!");
//
//            }
//        }
//
//        if (!rightElement.isInitialized()) {
//
//            if (this.symbolTableContextManager.getCurrentSymbolTable().getElement(rightElement.getName()) == null) {
////                SemanticManager.addError(node.line,
////                        "Error: Right side variable: " + rightElement.getName() + " is undefined");
//
//            } else {
////                SemanticManager.addError(node.line,
////                        "Error: Right side var:" + rightElement.getName() + " is not initialized!");
//            }
//        }
//
//
//        if (leftElement.getType() == Type.UNDEFINED || rightElement.getType() == Type.UNDEFINED) {
//            return new Element("", Type.UNDEFINED, true);
//        }
//
//        if (leftElement.getType() == rightElement.getType())
//            return new Element("", leftElement.getType(), true);
//
//        if (leftElement.getType() != Type.UNDEFINED && rightElement.getType() != Type.UNDEFINED) {
//
////            SemanticManager.addError(node.line,
////                    "Error: Right side variable of type " + Type.getTypeStr(rightElement.getType()) + " is incompatible with type left side variable of type " + Type.getTypeStr(leftElement.getType()));
//        }
//
//        return new Element("", Type.UNDEFINED);
//    }

//    public Object visit(ASTAccess node, Object data) {
//        SymbolTable currentSymbolTable = this.symbolTableContextManager.getCurrentSymbolTable();
//        Element element = currentSymbolTable.getElement((String) node.jjtGetValue());
//
//        if (element == null) {
//            return new Element((String) node.jjtGetValue(), Type.UNDEFINED);
//        }
//
//        if (node.jjtGetNumChildren() == 1 && element.getType() != Type.ARRAY && element.getType()!= Type.UNDEFINED) {
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
////                    "Error: Left side variable " + leftElement.getName() + " isn't initialized!");
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
////                    "Error: Right side variable " + rightElement.getName() + " isn't initialized!");
//
//        } else if (rightElement.getType() != Type.UNDEFINED && leftElement.getType() != Type.UNDEFINED) {
//            if (leftElement.getType() != rightElement.getType()) {
////                SemanticManager.addError(node.line,
////                        "Error: Right side variable of type" + Type.getTypeStr(rightElement.getType()) + " is incompatible with left side variable of type " + Type.getTypeStr(leftElement.getType()));
//            }
//        }
//
//        return null;
//    }

   public Object visit(ASTWHILE node, Object data) {
       node.jjtGetChild(0).jjtAccept(this, data);
       System.out.println("while00>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        SymbolTable currenSymbolTable = this.symbolTableContextManager.getCurrentSymbolTable();
        System.out.println("while01>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println("while02");
        currenSymbolTable.addChild(new SymbolTable("While", true));
        System.out.println("while03>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        this.symbolTableContextManager.pushFront(currenSymbolTable.popChild());
        System.out.println("while04");
        node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println("while05>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        this.symbolTableContextManager.popFront();
        System.out.println("while06");
        return null;
    }


//    public Object visit(ASTCall node, Object data) {
//        return new Element("", Type.UNDEFINED, true);
//    }

//    public Object visit(ASTArgumentList node, Object data) {
//        return null;
//    }

//    public Object visit(ASTString node, Object data) {
//        return null;
//    }


}