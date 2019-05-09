
import java.util.LinkedList;

public class Element {

    private String name;
    private String type;
    Object value;
    private boolean isInitialized = false;

    private LinkedList<Element> arguments = null;
    private Element returnValue;
    private int varnum = -1;
    private boolean isArray;


    /**Constructores*/
    public Element(){
    }
    public Element(String name, String type) {
        this.name = name;
        this.type = type;
//        if (type == Type.FUNCTION)
//            arguments = new LinkedList<Element>();
    }

    public Element(String name, String type, boolean isInitialized) {
        this.name = name;
        this.type = type;
//        if (type == Type.FUNCTION)
//            arguments = new LinkedList<Element>();
        this.isInitialized = isInitialized;
    }

    public Element(String name, String type, boolean isInitialized, Object value) {
        this.name = name;
        this.type = type;
//        if (type == Type.FUNCTION)
//            arguments = new LinkedList<Element>();
        this.isInitialized = isInitialized;
        this.value = value;
    }

    public Element(String name, boolean isInitialized, Element returnValue, LinkedList<Element> arguments) {
        this.name = name;
//        this.type = Type.FUNCTION;
        this.isInitialized = isInitialized;
        this.returnValue = returnValue;
        this.arguments = arguments;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    /****         SETS  *******/

    public void setVarnum(int varnum) {
        this.varnum = varnum;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setArguments(LinkedList<Element> arguments) {
        this.arguments = arguments;
    }
    public void setReturnValue(Element returnValue) {
        this.returnValue = returnValue;
    }
    public void setReturn(Element e) {
        returnValue = e;
    }
    /***/
    public String getName() {
        return name;
    }
    public Element getReturnValue() {
        return returnValue;
    }
    public String getType() {
        return type;
    }

//    public String getTypeStr() {
//        return Type.getTypeStr(type);
//    }
    public boolean isInitialized() {
        return isInitialized;
    }

//    public String getJasminTypes() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Element element : arguments) {
//            stringBuilder.append(element.getJasminType());
//        }
//        return stringBuilder.toString();
//    }

//    public String getJasminType() {
//        if (type == Type.ARRAY) {
//            return "[I";
//        }
//
//        if (type == Type.INTEGER)
//            return "I";
//
//        return "Unknown";
//    }

    public Object getValue() {
        return value;
    }

    public int getVarnum() {
        return varnum;
    }

//    public String getJasminReturnValueType(){
//        if(returnValue == null || returnValue.name == null){
//            return "V";
//        }
//        return returnValue.getJasminType();
//    }

    public Element getReturn() {
        return returnValue;
    }

    public void addArgument(Element e) {
        arguments.add(e);
    }

    public void addArguments(LinkedList<Element> e) {
        arguments.addAll(e);
    }

    public LinkedList<Element> getArguments() {
        return arguments;
    }

    public void setInitialized(boolean v) {
        isInitialized = v;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {

        String string = "[" + name + ", " + getType() + ", ";
        string += (isInitialized ? "Initialized" : "Not Initialized") + ", ";
        string += ((value == null) ? "null" : (String) value) + "]";
        return string;

    }

//    @Override
//    public int hashCode() {
//        return name.hashCode() ^ Type.getTypeStr(type).hashCode();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Element)) return false;
//        Element pairo = (Element) o;
//        return this.name.equals(pairo.getName()) && Type.getTypeStr(type).equals(pairo.getTypeStr());
//    }


}