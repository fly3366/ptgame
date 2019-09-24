package top.litop.ptgame.util.ast;

import java.util.TreeMap;

public class BasicAstTree extends TreeMap implements AstTree {

    Ast localAst = null;

    AstTree nextAst[] = null;

    public BasicAstTree(Ast localAst, AstTree nextAst[]) {
        this.localAst = localAst;
        this.nextAst = nextAst;
    }

}
