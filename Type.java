
import java.util.HashMap;

public enum Type {
    UNDEFINED, INTEGER, ARRAY, FUNCTION, CLASS;
    public static final HashMap<Type, String> typeHashMap;
    
    static
    {
        typeHashMap = new HashMap<>();
        typeHashMap.put(UNDEFINED, "Undefined");
        typeHashMap.put(INTEGER, "Integer");
        typeHashMap.put(ARRAY, "Array");
        typeHashMap.put(FUNCTION, "Function");
        typeHashMap.put(FUNCTION, "Class");
        
    }
    public static String getTypeStr(Type type){
        return typeHashMap.get(type);
    }
}
