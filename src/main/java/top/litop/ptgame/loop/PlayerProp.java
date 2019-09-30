package top.litop.ptgame.loop;

import java.util.Map;

public class PlayerProp {
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Map<String, Integer> getProps() {
        return props;
    }

    public void setProps(Map<String, Integer> props) {
        this.props = props;
    }

    private String playerName;

    private Map<String, Integer> props;
}
