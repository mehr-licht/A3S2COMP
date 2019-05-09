
import java.util.HashMap;
//import sun.jvm.hotspot.memory.SymbolTable;
//import sun.jvm.hotspot.memory.SymbolTable;


public class MainProject {

    private HashMap<String, SymbolTable> symbol_table = new HashMap<String,SymbolTable>();

    private String moduleName;
    private int contadorErros = 0;

    //Main
    public static void main(String args[]) {
        Jmm jmm;
        if (args.length == 0) {
            System.out.println("Jmm Parser:  Reading from standard input . . .");
            jmm = new Jmm(System.in);

        } else if (args.length == 1) {
            System.out.println("Jmm Parser:  Reading from file " + args[0] + " . . .");
            try {
                jmm = new Jmm(new java.io.FileInputStream(args[0]));
            } catch (java.io.FileNotFoundException e) {
                System.out.println("Jmm Parser:  File " + args[0] + " not found.");
                return;
            }

        } else {
            System.out.println("Jmm Parser:  Usage is one of:");
            System.out.println("         java Jmm < inputfile");
            System.out.println("OR");
            System.out.println("         java Jmm inputfile");
            return;
        }

        new MainProject(jmm);

    }

    public MainProject(Jmm jmm){
        try {
            SimpleNode root = jmm.Start();

            SymbolTableVisitor visitante = new SymbolTableVisitor();

            root.jjtAccept(visitante,null);

//            symbolTable.setLineNumbers();
//            root.dump(""); //Imprime a árvore

            System.out.println("Value elements list <string, elemente>: " + visitante.list_symbol_tables.toString());
            System.out.println("Final print: + TESTE 01 " + visitante.toString());            
           
           
            System.out.println("Jmm Parser:  Java program parsed successfully.");
            
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.out.println("Jmm Parser:  Encountered errors during parse.");
        }
        System.exit(0);
    }


}



