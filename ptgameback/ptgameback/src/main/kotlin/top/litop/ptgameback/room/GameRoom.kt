package top.litop.ptgameback.room

import io.vertx.core.AbstractVerticle

class GameRoom : AbstractVerticle {
  constructor(){

  }
  override fun start() {
    var eb = vertx.eventBus()

    var consumer = eb.consumer<Any>("room.defalutChannel")
    consumer.handler({ message ->
      println("默认频道收到了信息: ${message.body()}")
      var oneGame = eb.consumer<Any>("room.defultChannel.oneRoom")
      consumer.completionHandler({ res ->
      if (res.succeeded()) {
        println("默认房间注册成功")
      } else {
        println("默认房间注册失败!")
      }
    })
    })
    consumer.completionHandler({ res ->
      if (res.succeeded()) {
        println("默认频道注册成功")
      } else {
        println("默认频道注册失败!")
      }
    })
  }
}
