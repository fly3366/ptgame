package top.litop.ptgameback.room

import io.vertx.core.AbstractVerticle

class GameRoom : AbstractVerticle {
  constructor(){

  }
  override fun start() {
    var eb = vertx.eventBus()

    var consumer = eb.consumer<Any>("room.gameroom")
    consumer.handler({ message ->
      println("gameroom收到了信息: ${message.body()}")
      vertx.deployVerticle("top.litop.ptgameback.room.GameRoom")
    })
    consumer.completionHandler({ res ->
      if (res.succeeded()) {
        println("gameroom注册成功")
      } else {
        println("gameroom注册失败!")
      }
    })
  }
}
