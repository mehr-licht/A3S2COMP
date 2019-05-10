import java.util.HashMap;



public class Utils {
    public static HashMap<String,String> conditionalsHashMap= new HashMap<String,String>();
    public static HashMap<String,String> operationsHashMap= new HashMap<String,String>();

    /**
     * Code Generator Jasmim instructions
     *
     * */
    static {
        conditionalsHashMap.put(">", "if_icmple");
        conditionalsHashMap.put(">=", "if_icmplt");
        conditionalsHashMap.put("<", "if_icmpge");
        conditionalsHashMap.put("<=", "if_icmpgt");
        conditionalsHashMap.put("==", "if_icmpne");
        conditionalsHashMap.put("!=", "if_icmpeq");
        
        operationsHashMap.put("+", "iadd");
        operationsHashMap.put("-", "isub");
        operationsHashMap.put("*", "imul");
        operationsHashMap.put("/", "idiv");
        operationsHashMap.put(">>", "ishr");
        operationsHashMap.put("<<", "ishl");
        operationsHashMap.put(">>>", "iushr");
        operationsHashMap.put("|", "ior");
        operationsHashMap.put("&", "iand");
        operationsHashMap.put("^", "ixor");
    }

}
