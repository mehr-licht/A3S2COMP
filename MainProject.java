import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import AST_files.*;
import Symbol.*;
import java.nio.file.Paths;
import java.util.List;


public class MainProject {

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
            //adicionar a funcao de leitura da arvore
            //adicioanr a funcao da tabela de simbolos

            System.out.println("Jmm Parser:  Java program parsed successfully.");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.out.println("Jmm Parser:  Encountered errors during parse.");
        }
    }





}
