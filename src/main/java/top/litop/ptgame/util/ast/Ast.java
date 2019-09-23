package top.litop.ptgame.util.ast;

import java.util.Map;

public interface Ast {
    //获取数据
    Map<String, String> clientDisplaySource = null;
    //获取属性
    Map<String, Integer> clientPlayerProps[] = null;
    //获取参考选项
    String clientFork[] = null;
}
