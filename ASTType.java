/* Generated By:JJTree: Do not edit this line. ASTType.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTType extends SimpleNode {
private String name;

  public ASTType(int id) {
    super(id);
  }

  public ASTType(Jmm p, int id) {
    super(p, id);
  }

  public void setName(String n) {
    name = n;
  }

  public String toString() {
    return "[" + name + "]";
  }

}
/* JavaCC - OriginalChecksum=de59af60bb166da05476944689c03bfe (do not edit this line) */
