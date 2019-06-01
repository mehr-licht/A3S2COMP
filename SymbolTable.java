import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SymbolTable {


  private String name;
  private boolean isFunction = false;
  private boolean isConditional = false;

  private String end_def_return_name = null;
  private Element end_return_element = null;


  private SymbolTable parent_symbol_table;
  private HashMap<String, Element> variables = new HashMap<String, Element>();
  private HashMap<String, Element> parameters = new HashMap<String, Element>();
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

  public SymbolTable(String name, Element end_return_element, LinkedList<Element> variables) {
    this.name = name;
    this.isFunction = true;
    this.isConditional = false;
    this.end_return_element = end_return_element;
    this.parameters = new HashMap<String, Element>();
    addParameters(variables);
  }


  /** GETS **** */

  /**
   * Lista 1 de 2 lista de variaveis tipo linked list
   * */
  public LinkedList<Element> getVariablesv2() {
    return variablesv2;
  }

  /**
   * Devolve a lista de parametros
   * */
  public HashMap<String, Element> getParameters() {
    return this.parameters;
  }




  public void setVariablesv2(LinkedList<Element> variablesv2) {
    this.variablesv2 = variablesv2;
  }


  public String getEnd_def_return_name() {
    return end_def_return_name;
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


  public HashMap<String, Element> getVariables() {
    return this.variables;
  }

  public Element getEnd_return_element() {
    return end_return_element;
  }
  /** TODO -> needs checking */
  public Element getElement(String name) {
    if (end_return_element != null) if (end_return_element.equals(name)) return end_return_element;
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

  public void setEnd_return_element(Element end_return_element) {
    this.end_return_element = end_return_element;
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
  public void setEnd_def_return_name(String end_def_return_name) {
    this.end_def_return_name = end_def_return_name;
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
    //            end_return_element.setVarnum(line++);
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
  //            stringBuilder.append((end_return_element == null) ? "void" : end_return_element.toString());
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
