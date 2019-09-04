package top.litop.ptgameback.config

import top.litop.ptgameback.room.GameChannel

class ChannelConfig {
  public val channelId: String
  public var channelName: String

  class RoomConfig {
    public val roomId: String
    public var roomName: String
    public var eventLine:GameChannel
    constructor(roomId: String, roomName: String,eventLine:GameChannel) {
      this.roomId = roomId
      this.roomName = roomName
      this.eventLine = eventLine
      var eb = eventLine.vertx.eventBus()
      eb.publish("room.defaultChannel", roomId+"房间:"+roomName+"被频道"+eventLine.getGameChannelConfig()+"创建")
    }
  }

  private var gameRoomList = mutableMapOf<String,RoomConfig>()

  constructor(channelId: String, channelName: String,eventLine:GameChannel) {
    this.channelId = channelId
    this.channelName = channelName
    this.gameRoomList.put("World",RoomConfig("0","World",eventLine))
  }
}
