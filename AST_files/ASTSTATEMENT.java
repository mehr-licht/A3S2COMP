/* Generated By:JJTree: Do not edit this line. ASTSTATEMENT.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package AST_files;

public
class ASTSTATEMENT extends SimpleNode {
  public ASTSTATEMENT(int id) {
    super(id);
  }

  public ASTSTATEMENT(Jmm p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JmmVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=dfbd9dbabb97bce42913cbcf31ed7202 (do not edit this line) */
