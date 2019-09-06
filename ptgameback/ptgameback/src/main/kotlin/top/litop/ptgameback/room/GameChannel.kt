package top.litop.ptgameback.room

import io.vertx.core.AbstractVerticle
import top.litop.ptgameback.config.ChannelConfig

class GameChannel : AbstractVerticle {
  lateinit var gameChannelConfig: ChannelConfig
  var isReady: Boolean
  lateinit var gameEventMap: MutableMap<String, MessageConsumer<Any>>

  constructor() {
    this.isReady = false

  }

  fun getGameChannelConfig(): String {
    if (this.isReady)
      return this.gameChannelConfig.channelId
    else
      return "Initlizing"
  }


  private fun channelInit(): Boolean {
    this.gameChannelConfig = ChannelConfig("A001", "Default", this)
    this.gameEventMap = mutableMapOf()
    return true
  }

  private fun gameRoomCreate(roomId: String, roomName: String) {
    var eb = this.vertx.eventBus()
    var gameRoomEventLine = eb.consumer<Any>("room." + this.getGameChannelConfig() + "Channel." + roomId)
    gameRoomEventLine.completionHandler({ res ->
      if (res.succeeded()) {
        this.gameChannelConfig.setGameRoomList(roomId, ChannelConfig.RoomConfig(roomId, roomName, this))
        println(this.getGameChannelConfig() + "频道" + roomId + "房间注册成功")
      } else {
        println(this.getGameChannelConfig() + "频道" + roomId + "房间注册失败!")
      }
    })
  }

  override fun start() {
    var eb = this.vertx.eventBus()
    if (this.channelInit()) {
      this.isReady = true
    } else {
      println(this.getGameChannelConfig() + "频道初始化失败")
    }
    var channelMessage = eb.consumer<Any>("room." + this.getGameChannelConfig() + ".Channel")
    channelMessage.handler({ message ->
      println(this.getGameChannelConfig() + "频道收到了信息: ${message.body()}")
    })
    channelMessage.completionHandler({ res ->
      if (res.succeeded()) {
        println(this.getGameChannelConfig() + "频道注册成功")
        this.gameRoomCreate("0", "World")
      } else {
        println(this.getGameChannelConfig() + "频道注册失败!")
      }
    })
  }

  override fun stop() {
//    println(this.getGameChannelConfig() + "频道注册失败!")
  }
}
