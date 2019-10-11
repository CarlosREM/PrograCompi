package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ProcFuncsDeclaration extends Declaration {

  public ProcFuncsDeclaration(Declaration d1AST, Declaration d2AST,
                         SourcePosition thePosition) {
    super (thePosition);
    d1 = d1AST;
    d2 = d2AST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitProcFuncsDeclaration(this, o);
  }

  public Declaration d1;
  public Declaration d2;
}
