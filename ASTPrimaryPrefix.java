/* Generated By:JJTree: Do not edit this line. ASTPrimaryPrefix.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */

public
class ASTPrimaryPrefix extends SimpleNode {
  public ASTPrimaryPrefix(int id) {
    super(id);
  }

  public ASTPrimaryPrefix(Jmm p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JmmVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=3396f251642ad583d17a0af8cac97bc2 (do not edit this line) */