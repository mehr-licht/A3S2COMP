import java.util.LinkedList;

public class SymbolTableContextManager {

    private LinkedList<SymbolTable> global_symbol_table = new LinkedList<SymbolTable>();

    /**
     * CONSTRUCTOR
     * @param
     * */
    public SymbolTableContextManager(SymbolTable symbolTable) {
        this.global_symbol_table.push(symbolTable);
    }

    /**
     * Returns the first element in this list. READ ONLY
     * @return SymbolTable the first ST from the list
     * */
    public SymbolTable getCurrentSymbolTable(){
        return this.global_symbol_table.getFirst();
    }

    /**
     * Pushes an element onto the stack represented by this list. javaDocs
     * Inserts in the begging
     * @param
     * */
    public void pushFront(SymbolTable symbolTable){
        this.global_symbol_table.push(symbolTable);
    }

    /**
     * Removes and returns the first element from this list. READ & DELETE
     * @return ST
     * */
    public SymbolTable popFront(){
        return this.global_symbol_table.removeFirst();
    }

  /** Returns the last element in this list. */
  public SymbolTable getRootSymbolTable() {
        return this.global_symbol_table.getLast();
    }

}