/* Generated By:JJTree: Do not edit this line. ASTName.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */

public
class ASTName extends SimpleNode {
  public int line;
  public int line2;
  public String value;
  public String value2;

  public ASTName(int id) {
    super(id);
  }

  public ASTName(Jmm p, int id) {
    super(p, id);
  }

public String toString(){
    return value + value2;
}
  /** Accept the visitor. **/
  public Object jjtAccept(JmmVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=f271150458a6638b57a4dfe577dfafdb (do not edit this line) */
