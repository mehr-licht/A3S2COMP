/* Generated By:JJTree: Do not edit this line. ASTMethodDeclarator.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */

public
class ASTMethodDeclarator extends SimpleNode {
  public String value;
  public int line;

  public ASTMethodDeclarator(int id) {
    super(id);
  }

  public ASTMethodDeclarator(Jmm p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JmmVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=ca20d7b49ab6176b6bbfa4ec01684f97 (do not edit this line) */