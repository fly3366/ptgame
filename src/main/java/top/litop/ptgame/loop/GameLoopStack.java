package top.litop.ptgame.loop;

import top.litop.ptgame.util.ast.AstDecode;
import top.litop.ptgame.util.ast.AstTree;
import top.litop.ptgame.util.ast.BasicJsonAstDecode;
import top.litop.ptgame.util.swtich.BasicSwitcher;

import java.util.Map;

public class GameLoopStack {
    private PlayerProp[] playersProp;

    private AstTree masterTree;

    private AstTree localAstTree;

    private AstTree mayNextAstTree;

    public GameLoopStack(String AstTreeValue) {
        this.localAstTree = BasicJsonAstDecode.astTreeDecode(AstTreeValue);
        Map<String, Integer>[] props = localAstTree.getLocalAst().getProps();
        this.playersProp = new PlayerProp[props.length];
        int propFlag = 0;
        for (Map<String, Integer> prop : props) {
            playersProp[propFlag].setProps(prop);
            playersProp[propFlag].setPlayerName("player" + propFlag);
            propFlag++;
        }
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
