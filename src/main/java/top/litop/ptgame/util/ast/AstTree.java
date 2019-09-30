package top.litop.ptgame.util.ast;

public interface AstTree {
    Ast localAst = null;
    Ast nextAst[] = null;

    public Ast getLocalAst();

    public String[] getNextAstValues();
}
