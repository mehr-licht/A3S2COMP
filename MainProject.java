import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import AST_files.*;
import Symbol.*;
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
            root.dump("");
            construc_Symbol_Table(root);
            //construct_Functiond_SymbolTable(root); //TODO
            //adicionar a funcao de leitura da arvore
            //adicioanr a funcao da tabela de simbolos

            System.out.println("Jmm Parser:  Java program parsed successfully.");
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
            SymbolTable globalST = new SymbolTable();
            for(int i =0; i < start.jjtGetNumChildren(); i++){

            }



        }
    };






}
