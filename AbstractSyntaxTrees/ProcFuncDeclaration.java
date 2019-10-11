package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ProcFuncDeclaration extends Declaration {

  public ProcFuncDeclaration(Declaration declarationAST, SourcePosition thePosition) {
    super (thePosition);
    declaration = declarationAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitProcFuncDeclaration(this, o);
  }

  public Declaration declaration;
}
