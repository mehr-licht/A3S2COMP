import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SymbolTable {

  //    private Global_Symbol_Table_List globalSymbolTableList = new
  // Global_Symbol_Table_List(this);
  //    private SymbolTableVisitor symbolTableVisitor = new
  // SymbolTableVisitor(this.globalSymbolTableList);
  //    private SemanticVisitor semanticVisitor = new
  // SemanticVisitor(this.globalSymbolTableList);
  //    private SemanticVisitorAssigns semanticVisitorAssigns = new
  // SemanticVisitorAssigns(this.globalSymbolTableList);

  private String name;
  private boolean isFunction = false;
  private boolean isConditional = false;

  private HashMap<String, Element> variables = new HashMap<String, Element>();
  private HashMap<String, Element> parameters = null;
  private String return_type= null;
  private Element returnValue = null;
  private SymbolTable parent_symbol_table = null;


  private LinkedList<Element> variablesv2;
  private LinkedList<SymbolTable> children_list_of_symbol_tables = new LinkedList<SymbolTable>();


  /** Constructores ********************************************* */
  public SymbolTable() {
    this.variablesv2 = new LinkedList<Element>();
  }

  public SymbolTable(String name, boolean isConditional) {
    this.variablesv2 = new LinkedList<Element>();
    this.name = name;
    this.isConditional = isConditional;
  }

  public SymbolTable(String name, Element returnValue, LinkedList<Element> variables) {
    this.name = name;
    this.isFunction = true;
    this.isConditional = false;
    this.returnValue = returnValue;
    this.parameters = new HashMap<String, Element>();
    addParameters(variables);
  }


  /** GETS **** */

  public LinkedList<Element> getVariablesv2() {
    return variablesv2;
  }

  public void setVariablesv2(LinkedList<Element> variablesv2) {
    this.variablesv2 = variablesv2;
  }


  public String getReturn_type() {
    return return_type;
  }
  public SymbolTable getParent_symbol_table() {
    return parent_symbol_table;
  }

  public LinkedList<SymbolTable> getChildren_list_of_symbol_tables() {
    return children_list_of_symbol_tables;
  }

  public boolean isFunction() {
    return isFunction;
  }

  public boolean isConditional() {
    return isConditional;
  }

  public HashMap<String, Element> getParameters() {
    return this.parameters;
  }

  public HashMap<String, Element> getVariables() {
    return this.variables;
  }

  public Element getReturnValue() {
    return returnValue;
  }
  /** TODO -> needs checking */
  public Element getElement(String name) {
    if (returnValue != null) if (returnValue.equals(name)) return returnValue;
    if (variables.containsKey(name)) {
      return variables.get(name);
    }
    if (isFunction) if (parameters.containsKey(name)) return parameters.get(name);

    if (parent_symbol_table == null) return null;
    return parent_symbol_table.getElement(name);
  }

  private int getDepth() {
    if (parent_symbol_table == null) return 0;
    return 1 + parent_symbol_table.getDepth();
  }

  public String getName() {
    return name;
  }

  //    public String getJasminReturnType() {
  //        if (returnValue == null || returnValue.getName() == null)
  //            return "V";
  //        else
  //            return returnValue.getJasminType();
  //    }

  //    public int getLocals() {
  //        int counter = variables.size();
  //        if (parameters != null) {
  //            counter += parameters.size();
  //        }
  //        if (getDepth() == 1) {
  //            counter++;
  //        }
  //
  //        for (SymbolTable child : children_list_of_symbol_tables) {
  //            counter += child.getLocals();
  //        }
  //
  //        return counter;
  //    }
  //    public SemanticVisitor getSemanticVisitor() {
  //        return semanticVisitor;
  //    }
  //
  //    public SemanticVisitorAssigns getSemanticVisitorAssigns() {
  //        return semanticVisitorAssigns;
  //    }

  public void addVariables(Element element) {
    variables.put(element.getName(), element);
  }

  public void addVariablesV2(Element element) {
    variablesv2.push(element);
  }
  private void addParameters(LinkedList<Element> elements) {
    if (elements != null) {
      for (Element element : elements) parameters.put(element.getName(), element);
    }
  }

  public void addChild(SymbolTable symbolTable) {
    symbolTable.setParent_symbol_table(this);
    children_list_of_symbol_tables.addFirst(symbolTable);
  }

  public SymbolTable popChild() {
    SymbolTable symbolTable = children_list_of_symbol_tables.pop();
    children_list_of_symbol_tables.addLast(symbolTable);
    return symbolTable;
  }

  /** SETS ************************************************************* */
  public void setFunction(boolean function) {
    isFunction = function;
  }

  public void setConditional(boolean conditional) {
    isConditional = conditional;
  }

  public void setVariables(HashMap<String, Element> variables) {
    this.variables = variables;
  }

  public void setParameters(HashMap<String, Element> parameters) {
    this.parameters = parameters;
  }

  public void setReturnValue(Element returnValue) {
    this.returnValue = returnValue;
  }

  public void setChildren_list_of_symbol_tables(LinkedList<SymbolTable> children_list_of_symbol_tables) {
    this.children_list_of_symbol_tables = children_list_of_symbol_tables;
  }

  public void setParent_symbol_table(SymbolTable symbolTable) {
    this.parent_symbol_table = symbolTable;
  }

  public void setName(String name) {
    this.name = name;
  }
  public void setReturn_type(String return_type) {
    this.return_type = return_type;
  }
  public void setLineNumbers() {
    for (SymbolTable symbolTable : children_list_of_symbol_tables) {
      symbolTable.setLineNumbers(0);
    }
  }

  private int setLineNumbers(int line) {
    //        if (getDepth() == 1) {
    //            LinkedList<Element> arguments =
    // globalSymbolTableList.getRootSymbolTable().getElement(name).getArguments();
    //            if (arguments != null) {
    //
    //                for (Element element : arguments) {
    //                    element.setVarnum(line++);
    //                }
    //            }
    //            returnValue.setVarnum(line++);
    //        }
    //        for (Element element : variables.values()) {
    //            element.setVarnum(line++);
    //        }
    //
    //        for (SymbolTable child : children_list_of_symbol_tables) {
    //            line = child.setLineNumbers(line);
    //        }

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

  //    @Override
  //    public String toString() {
  //
  //        StringBuilder stringBuilder = new StringBuilder();
  //        if (name != null) {
  //
  //            if (!isConditional) {
  //                stringBuilder.append((isFunction) ? "Function: " : "MODULE: ");
  //            }
  //            stringBuilder.append(name);
  //        }
  //
  //        if (isFunction) {
  //            stringBuilder.append("\tReturn: ");
  //            stringBuilder.append((returnValue == null) ? "void" : returnValue.toString());
  //            stringBuilder.append("\n");
  //            stringBuilder.append(MapToString("Parameters", parameters));
  //        } else
  //            stringBuilder.append("\n");
  //
  //        stringBuilder.append(MapToString("Locals", variables));
  //
  // stringBuilder.append("\n.............................................................................................................\n\n");
  //
  //        for (SymbolTable s : children_list_of_symbol_tables) {
  //            stringBuilder.append(s.toString());
  //        }
  //        return stringBuilder.toString();
  //    }

}
