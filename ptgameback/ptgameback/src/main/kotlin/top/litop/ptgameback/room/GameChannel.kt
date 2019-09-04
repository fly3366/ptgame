package top.litop.ptgameback.room

import io.vertx.core.AbstractVerticle
import top.litop.ptgameback.config.ChannelConfig

class GameChannel : AbstractVerticle {
  lateinit var gameChannelConfig: ChannelConfig
  var isReady:Boolean
  constructor() {
    this.isReady = false
  }

  fun getGameChannelConfig():String{
    if(this.isReady)
      return this.gameChannelConfig.channelId
    else
      return "初始化的"
  }


  private fun channelInit(): Boolean {
    this.gameChannelConfig = ChannelConfig("A001", "Default",this)
    return true
  }

  override fun start() {
    var eb = vertx.eventBus()

    var consumer = eb.consumer<Any>("room.defaultChannel")
    consumer.handler({ message ->
      println("默认频道收到了信息: ${message.body()}")

    })
    consumer.completionHandler({ res ->
      if (res.succeeded()) {
        if (this.channelInit()) {
          println(this.gameChannelConfig.channelId + "频道注册成功")
          this.isReady=true
        } else
          println("默认频道注册失败!")
      } else {
        println("默认频道注册失败!")
      }
    })
  }
}
