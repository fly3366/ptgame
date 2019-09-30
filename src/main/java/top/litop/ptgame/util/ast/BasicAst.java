package top.litop.ptgame.util.ast;

import java.util.Map;

public class BasicAst implements Ast {
    public Map<String, String> getClientDisplaySource() {
        return clientDisplaySource;
    }

    public void setClientDisplaySource(Map<String, String> clientDisplaySource) {
        this.clientDisplaySource = clientDisplaySource;
    }

    public Map<String, Integer>[] getProps() {
        return clientPlayerProps;
    }

    public void setClientPlayerProps(Map<String, Integer>[] clientPlayerProps) {
        this.clientPlayerProps = clientPlayerProps;
    }

    public String[] getClientFork() {
        return clientFork;
    }

    public void setClientFork(String[] clientFork) {
        this.clientFork = clientFork;
    }

    //获取数据
    Map<String, String> clientDisplaySource;
    //获取属性
    Map<String, Integer> clientPlayerProps[];
    //获取参考选项
    String clientFork[];

    @Override
    public String[] getNext() {
        return this.clientFork;
    }
}
