package top.litop.ptgame.config;

import java.util.Map;

public class ChannelConfig {
    private String channelId;
    private String channelName;
    private Map<String, RoomConfig> gameRoomList;

    public class RoomConfig {
        private String roomId;
        private String roomName;

        public RoomConfig(String roomId, String roomName) {
            this.roomId = roomId;
            this.roomName = roomName;
        }

        public String getRoomId() {
            return roomId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }
    }

    public RoomConfig roomConfigBuilder(String roomId, String roomName) {
        return new RoomConfig(roomId, roomName);
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Map<String, RoomConfig> getGameRoomList() {
        return gameRoomList;
    }

    public void setGameRoomList(RoomConfig roomConfig) {
        this.gameRoomList.put(roomConfig.getRoomId(), roomConfig);
    }

    public ChannelConfig(String channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

}
