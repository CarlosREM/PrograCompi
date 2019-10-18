package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LoopForCommand extends Command {

  public LoopForCommand (Declaration dAST, Expression eAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    D = dAST;
    E = eAST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitLoopForCommand(this, o);
  }

  public Declaration D;
  public Expression E;
  public Command C;
}