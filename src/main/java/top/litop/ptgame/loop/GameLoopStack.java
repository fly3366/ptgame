package top.litop.ptgame.loop;

import top.litop.ptgame.util.ast.AstTree;
import top.litop.ptgame.util.ast.BasicJsonAstDecode;
import top.litop.ptgame.util.swtich.BasicSwitcher;

public class GameLoopStack {
    private PlayerProp[] playersProp;

    private AstTree masterTree;

    private AstTree localAstTree;

    private AstTree mayNextAstTree;

    public GameLoopStack(AstTree localAstTree) {
        this.localAstTree = localAstTree;
    }

    ;

    public AstTree getLocalAstTree() {
        return localAstTree;
    }

    public void gotoNext() {
        if (this.localAstTree.getNextAstValues().length == 0) {
            //END
        } else {
            this.localAstTree = BasicJsonAstDecode.astTreeDecode(
                    localAstTree.getNextAstValues()[BasicSwitcher.staticSwitch(localAstTree)]
            );
        }
    }
}
