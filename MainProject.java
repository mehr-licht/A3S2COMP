package A3S2COMP;

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import AST_files.*;
//import sun.jvm.hotspot.memory.SymbolTable;
//import sun.jvm.hotspot.memory.SymbolTable;

import java.nio.file.Paths;
import java.util.List;


public class MainProject {

    private HashMap<String, SymbolTable> symbol_table = new HashMap<String,SymbolTable>();

    private String moduleName;
    private int contadorErros = 0;

    //Main
    public static void main(String args[]) {
        Jmm myJmm;
        if (args.length == 0) {
            System.out.println("Jmm Parser:  Reading from standard input . . .");
            myJmm = new Jmm(System.in);

        } else if (args.length == 1) {
            System.out.println("Jmm Parser:  Reading from file " + args[0] + " . . .");
            try {
                myJmm = new Jmm(new java.io.FileInputStream(args[0]));
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

        new MainProject(myJmm);

    }

    public MainProject(Jmm myJmm){
        try {
            SimpleNode root = myJmm.Start();

            System.out.println("Nokun 1");
            SymbolTable symbolTable = new SymbolTable();
            System.out.println("Nokun 2");
            root.jjtAccept(symbolTable.getSymbolTableVisitor(),null);
            symbolTable.setLineNumbers();
            root.dump("");
            System.out.println("Nokun 3");

            //construc_Symbol_Table(root);
            //construct_Functiond_SymbolTable(root); //TODO
            //adicionar a funcao de leitura da arvore
            //adicioanr a funcao da tabela de simbolos

            System.out.println("Jmm Parser:  Java program parsed successfully.");
            System.exit(0);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.out.println("Jmm Parser:  Encountered errors during parse.");
        }
    }

    public void construc_Symbol_Table(SimpleNode root){
        if(root!= null && root instanceof ASTStart){
            ASTStart start = (ASTStart) root;
        //    this.moduleName == "module - " + module.name;

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            /**Global one*/
            SymbolTable globalSymbolTable = new SymbolTable();
            //start.


            for(int i =0; i < start.jjtGetChild(0).jjtGetNumChildren(); i++){
                System.out.println(start.jjtGetChild(0).jjtGetChild(i)); //resulta 1 - porque temos uma funcao intermedia que nao faz nda
                System.out.println(start.jjtGetChild(0).jjtGetChild(i).jjtGetNumChildren()); //resulta 1 - porque temos uma funcao intermedia que nao faz nda

                System.out.println(start.jjtGetChild(0) + "entrou no for"); //result 3 metodos como apresentado na AST
//                System.out.println(start.jjtGetChild(1) + "entrou2 no for"); //result 3 metodos como apresentado na AST

                //declarations
//                if (start.jjtGetChild(i) instanceof ASTMethodDeclaration) {
//                    ASTMethodDeclarator element = (ASTMethodDeclarator) start.jjtGetChild(i).jjtGetChild(0);
//
//                    System.out.println(element.jjtGetNumChildren());
//                    if (globalSymbolTable.getFromAll(element.getName) != null && (globalSymbolTable.getAcessType(element.getName) == "array" && start.jjtGetChild(i).jjtGetNumChildren() == 2))
//                        printSemanticError(element.getName, element.getLine, "Redefinition of global variable.");
//                    else if (globalSymbolTable.getFromAll(element.getName) != null && (globalSymbolTable.getAcessType(element.getName) == "int" && start.jjtGetChild(i).jjtGetNumChildren() != 2))
//                        printSemanticError(element.getName, element.getLine, "Redefinition of global variable.");
//                    else {
//                        if (start.jjtGetChild(i).jjtGetNumChildren() == 2) {
//                            globalSymbolTable.addVariable(element.getName, "array");
//                        } else {
//                            globalSymbolTable.addVariable(element.getName, "int");
//                        }
//                    }
                    //functions
//                } else{
                        //TODO testar com outro codigo para ver se entra no else
//                    else if (module.jjtGetChild(i) instanceof ASTFunction) {
//                    ASTFunction function = (ASTFunction) module.jjtGetChild(i);
//
//                    if (this.symbolTables.get(function.name) == null)
//                        this.symbolTables.put(function.name, new SymbolTable());
//                    else {
//                        printSemanticError(function.name, function.line, "Duplicate function.");
//                    }
//
//                    fillFunctionParametersReturn(function);
//                }
//            }
//            this.symbolTables.put(this.moduleName, globalSymbolTable);


        }



        }
    };


}



