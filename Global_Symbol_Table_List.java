import java.util.LinkedList;

public class Global_Symbol_Table_List {

    public LinkedList<SymbolTable> list_symbol_tables = new LinkedList<SymbolTable>();

    /**
     * CONSTRUCTOr Default
     * */
    public Global_Symbol_Table_List() {
        SymbolTable symbolTable = new SymbolTable();
        this.list_symbol_tables.push(symbolTable);
    }
    public Global_Symbol_Table_List(SymbolTable symbolTable)
    {
        this.list_symbol_tables.push(symbolTable);
    }

    /**
     * Returns the first element in this list. READ ONLY
     * @return SymbolTable the first ST from the list
     * */
    public SymbolTable get_Top_Stack(){
        return this.list_symbol_tables.getFirst();
    }

    /**
     * Pushes an element onto the stack represented by this list. javaDocs
     * Inserts in the begging
     * @param
     * */
    public void insert_Top_Stack_push(SymbolTable symbolTable){

        this.list_symbol_tables.push(symbolTable);
    }

    /**
     * Removes and returns the first element from this list. READ & DELETE
     * @return ST
     * */
    public SymbolTable popFront(){
        return this.list_symbol_tables.removeFirst();
    }

  /** Returns the last element in this list. */
  public SymbolTable getRootSymbolTable() {
        return this.list_symbol_tables.getLast();
    }

}