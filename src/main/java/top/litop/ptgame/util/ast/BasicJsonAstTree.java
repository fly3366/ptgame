package top.litop.ptgame.util.ast;

import java.util.TreeMap;

public class BasicJsonAstTree extends TreeMap implements AstTree {

    BasicAst localAst = null;

    String nextAstValue[] = null;

    public BasicJsonAstTree(BasicAst localAst, String nextAst[]) {
        this.localAst = localAst;
        this.nextAstValue = nextAst;
    }


    @Override
    public Ast getLocalAst() {
        return this.localAst;
    }

    @Override
    public String[] getNextAstValues() {
        return this.nextAstValue;
    }
}
