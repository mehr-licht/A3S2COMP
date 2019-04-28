import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SymbolTable {

    private SymbolTableContextManager symbolTableContextManager = new SymbolTableContextManager(this);
    private SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor(this.symbolTableContextManager);
//    private SemanticVisitor semanticVisitor = new SemanticVisitor(this.symbolTableContextManager);
//    private SemanticVisitorAssigns semanticVisitorAssigns = new SemanticVisitorAssigns(this.symbolTableContextManager);


    private String name;
    private boolean isFunction = false;
    private boolean isConditional = false;

    private HashMap<String, Element> elements = new HashMap<String, Element>();
    private HashMap<String, Element> parameters = null;
    private Element returnValue = null;
    private SymbolTable parent = null;


    private LinkedList<SymbolTable> children = new LinkedList<SymbolTable>();

    /**
     * Empty Constructor
     * */
    public SymbolTable() {  System.out.println("construct1.0  SymbolTable >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public SymbolTable(String name, boolean isConditional) {
        System.out.println("construct1.1 SymbolTable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        this.name = name;
        this.isConditional = isConditional;
    }

    /**
     * Constructor
     * @param name
     * @param returnValue
     * @param elements
     * */
    public SymbolTable(String name, Element returnValue, LinkedList<Element> elements) {
        System.out.println("construct12>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        this.name = name;
        this.isFunction = true;
        this.isConditional = false;
        this.returnValue = returnValue;
        this.parameters = new HashMap<String, Element>();
        addParameters(elements);
    }




    public void addElement(Element element) {
        elements.put(element.getName(), element);
    }


    private void addParameters(LinkedList<Element> elements) {
        if (elements != null) {
            for (Element element : elements)
                parameters.put(element.getName(), element);
        }
    }

    public void addChild(SymbolTable symbolTable) {
        symbolTable.setParent(this);
        children.addFirst(symbolTable);
    }


    public SymbolTable popChild() {
        SymbolTable symbolTable = children.pop();
        children.addLast(symbolTable);
        return symbolTable;
    }


    public LinkedList<Element> getParameters() {
        return this.symbolTableContextManager.getRootSymbolTable().getElement(name).getArguments();
    }

    public LinkedList<Element> getElements() {
        return new LinkedList<Element>(elements.values());
    }

    public SymbolTableContextManager getSymbolTableContextManager() {
        return symbolTableContextManager;
    }

    public Element getReturnValue() {
        return returnValue;
    }

    public Element getElement(String name) {
        if (returnValue != null)
            if (returnValue.equals(name))
                return returnValue;
        if (elements.containsKey(name)) {
            return elements.get(name);
        }
        if (isFunction)
            if (parameters.containsKey(name))
                return parameters.get(name);

        if (parent == null)
            return null;
        return parent.getElement(name);
    }

    private int getDepth() {
        if (parent == null)
            return 0;
        return 1 + parent.getDepth();
    }

    public String getName() {
        return name;
    }

    public String getJasminReturnType() {
        if (returnValue == null || returnValue.getName() == null)
            return "V";
        else
            return returnValue.getJasminType();
    }



    public int getLocals() {
        int counter = elements.size();
        if (parameters != null) {
            counter += parameters.size();
        }
        if (getDepth() == 1) {
            counter++;
        }

        for (SymbolTable child : children) {
            counter += child.getLocals();
        }

        return counter;
    }

    public SymbolTableVisitor getSymbolTableVisitor() {
        return symbolTableVisitor;
    }

//    public SemanticVisitor getSemanticVisitor() {
//        return semanticVisitor;
//    }
//
//    public SemanticVisitorAssigns getSemanticVisitorAssigns() {
//        return semanticVisitorAssigns;
//    }


    /**
     *
     * SETS **************************************************************
     *
     * */

    public void setParent(SymbolTable symbolTable) {
        this.parent = symbolTable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLineNumbers() {
        for (SymbolTable symbolTable : children) {
            symbolTable.setLineNumbers(0);
        }
    }

    private int setLineNumbers(int line) {
        if (getDepth() == 1) {
            LinkedList<Element> arguments = symbolTableContextManager.getRootSymbolTable().getElement(name).getArguments();
            if (arguments != null) {

                for (Element element : arguments) {
                    element.setVarnum(line++);
                }
            }
            returnValue.setVarnum(line++);
        }
        for (Element element : elements.values()) {
            element.setVarnum(line++);
        }

        for (SymbolTable child : children) {
            line = child.setLineNumbers(line);
        }

        return line;
    }

    private String MapToString(String name, HashMap<String, Element> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + ":\n");

        for (Map.Entry<String, Element> entry : map.entrySet()) {
            String identifier = entry.getKey();
            Element element = entry.getValue();

            stringBuilder.append("\t" + identifier + " -> " + element + "\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        if (name != null) {

            if (!isConditional) {
                stringBuilder.append((isFunction) ? "Function: " : "MODULE: ");
            }
            stringBuilder.append(name);
        }

        if (isFunction) {
            stringBuilder.append("\tReturn: ");
            stringBuilder.append((returnValue == null) ? "void" : returnValue.toString());
            stringBuilder.append("\n");
            stringBuilder.append(MapToString("Parameters", parameters));
        } else
            stringBuilder.append("\n");

        stringBuilder.append(MapToString("Locals", elements));
        stringBuilder.append("\n.............................................................................................................\n\n");

        for (SymbolTable s : children) {
            stringBuilder.append(s.toString());
        }
        return stringBuilder.toString();
    }


}