/* Generated By:JJTree: Do not edit this line. ASTDIVMULT.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTDIVMULT extends SimpleNode {
  public int line;
  public String value;

  public ASTDIVMULT(int id) {
    super(id);
  }

  public ASTDIVMULT(Jmm p, int id) {
    super(p, id);
  }
  public String toString(){
    return value;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(JmmVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=2e45f825a2c9ccaa722b4b7bb7775b11 (do not edit this line) */
