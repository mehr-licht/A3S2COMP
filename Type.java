
import java.util.HashMap;

public enum Type {
    UNDEFINED, INTEGER, ARRAY, FUNCTION, CLASS, BOOLEAN, VOID;
    public static final HashMap<Type, String> typeHashMap;
    
    static
    {
        typeHashMap = new HashMap<>();
        typeHashMap.put(UNDEFINED, "undefined");
        typeHashMap.put(INTEGER, "integer");
        typeHashMap.put(INTEGER, "int");
        typeHashMap.put(ARRAY, "int[]");
        typeHashMap.put(FUNCTION, "Function");
        typeHashMap.put(CLASS, "class");
        typeHashMap.put(BOOLEAN, "boolean");
        typeHashMap.put(VOID, "void");


    }
    public static String getTypeStr(Type type){

        return typeHashMap.get(type);
    }

}
