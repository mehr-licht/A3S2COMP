/* Generated By:JJTree: Do not edit this line. ASTConstructorDeclaration.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */

public
class ASTConstructorDeclaration extends SimpleNode {
  public int line;
  public String value;
  public ASTConstructorDeclaration(int id) {
    super(id);
  }

  public ASTConstructorDeclaration(Jmm p, int id) {
    super(p, id);
  }

  public String toString(){
    return value  ;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(JmmVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=24db3cdfc5d6286f9ceebc712bca03d8 (do not edit this line) */