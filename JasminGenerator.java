import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;

public class JasminGenerator extends Global_Symbol_Table_List{


    public PrintWriter writer;
    private String filepath;
    public Integer lineNumber = 0;

    private String moduleName;

    /**
     * Byte [] codeBuffer
     * int getCodePosition()
     * void addInstruction(Utils.instruction)
     * void addInstrution(Utils.instruction , int nscope, int narg)
     * void writeCode(String p_strPath)
     *
     * */
    public JasminGenerator(LinkedList<SymbolTable> list_symbol_tables){
        this.list_symbol_tables =  list_symbol_tables;

        this.filepath = "_jasmim_generated.j";

        try {
            writer = new PrintWriter(new FileOutputStream("_jasmim_generated.j"),true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PrintWriter getWriter() {
        return writer;
    }


    public static String conversitonTypesArguments(String string, boolean isArray){
        String codeJasmin = null;


        if(string.equals("int")){
            codeJasmin = "I";
        }else if(string.equals("boolean")){
            codeJasmin = "Z";
        }else if(string.equals("void")){
            codeJasmin = "V";
        }

        if(isArray){
            codeJasmin ="[I";
        }

        return codeJasmin;
    }




    public static Element find_type_element(LinkedList<SymbolTable> listaIterar, String nomeVariavel, String nomeFuncao){

        //percorre a lista à procura do nome do metodo
        while(!listaIterar.isEmpty()){
            SymbolTable symbolTable = listaIterar.pop();

            if(symbolTable.getName().equals(nomeFuncao)){
                LinkedList<Element> listaVariaveis = symbolTable.getVariablesv2();
                while(!listaVariaveis.isEmpty()){
                    Element testeElement = listaVariaveis.pop();
                    if(testeElement.getName().equals(nomeVariavel) && testeElement.getType() != null){
                           return  testeElement;
                    }
                }
            }
        }

        return null;
    }


    public void writePutstatic(Element element) {
        writer.print("putstatic " + moduleName + "/" + element.getName() + " ");
//        println(element.getJasminType());

    }

    public void writeGetStatic(Element element) {
        writer.print("getstatic " + moduleName + "/" + element.getName() + " ");
//        println(element.getJasminType());
    }

    public void writeInvokeStatic(String moduleName, String methodName, String types, String returnValue) {
        writer.print("invokestatic " + moduleName + "/" + methodName + "(" + types + ")");
        println(returnValue);
    }

    public void writeInvokeStatic(Element element) {
//        writeInvokeStatic(moduleName, element.getName(), element.getJasminTypes(), element.getJasminReturnValueType());
    }


    public void writeSingleWord(String singleWord) {
        println("ldc " + singleWord);
    }

    public void writeArray() {
        println("newarray int");
    }

    public void writeArraySize() {
        println("arraylength");
    }

    public void writeLoadElement(Element element) {
//        int varnum = element.getVarnum();
//        if (element.getType() == Type.ARRAY) {
//            if (varnum != -1) {
//                writeAload(varnum);
//                return;
//            }
//        }
//        if (element.getType() == Type.INTEGER) {
//            if (varnum != -1) {
//                writeIload(varnum);
//                return;
//            }
//        }
        writeGetStatic(element);

    }

    public void writeStoreElement(Element element, boolean storeIndex) {
//        if (element.getType() == Type.INTEGER) {
//            if (element.getVarnum() != -1) {
//                writeIstore(element.getVarnum());
//                return;
//            }
//        }
//        if (element.getType() == Type.ARRAY) {
//            if (storeIndex) {
//                writeIastore();
//                return;
//            }
//            if (element.getVarnum() != -1) {
//                writeAstore(element.getVarnum());
//                return;
//            }
//        }
        writePutstatic(element);
    }

    private void writeIstore(int value) {
        println("istore " + value);
    }

    private void writeIastore() {
        println("iastore ");
    }

    private void writeAstore(int value) {
        println("astore " + value);
    }

    private void writeAload(int lineNumber) {
        println("aload " + lineNumber);
    }

    private void writeIload(int lineNumber) {
        println("iload " + lineNumber);
    }

    public void writeIaload() {
        println("iaload");
    }

    public void writeSign(String sign) {
        println(Utils.operationsHashMap.get(sign));
    }

    public void writeIneg() {
        println("ineg");
    }

    public void writePop(){
        println("pop");
    }

    public void writeLoadScalar(Integer value){
        if(value>=0 && value <=5){
            writeIConst(value);
        }
        else {
            writeBipush(value);
        }
    }

    private void writeIConst(Integer value){
        println("iconst_" + value);
    }

    private void writeBipush(Integer value){
        println("bipush " + value);

    }

    private void println(String line) {
        writer.println(line);
        lineNumber++;
    }


}