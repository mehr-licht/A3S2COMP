package AST_files;

/* Generated By:JJTree: Do not edit this line. ASTIntegerLiteral.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTIntegerLiteral extends SimpleNode {
  private String name;

  public ASTIntegerLiteral(int id) {
    super(id);
  }

  public ASTIntegerLiteral(Jmm p, int id) {
    super(p, id);
  }

  public void setName(String n) {
    name = n;
  }

  public String toString() {

    return "IL[" + name + "]";
  }

}
/* JavaCC - OriginalChecksum=77b2cc61fdd36fe0135d09c031af74fd (do not edit this line) */
