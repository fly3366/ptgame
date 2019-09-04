package top.litop.ptgameback.room

import io.vertx.core.AbstractVerticle

class MainService : AbstractVerticle {
  constructor() {

  }

  override fun start() {
    var eb = vertx.eventBus()

    var consumer = eb.consumer<Any>("room.create.newgame")
    consumer.handler({ message ->
      println("服务器收到了信息: ${message.body()}")
    })
    consumer.completionHandler({ res ->
      if (res.succeeded()) {
        println("服务器注册成功")
        vertx.deployVerticle("top.litop.ptgameback.room.GameChannel")
      } else {
        println("服务器注册失败!")
      }
    })
  }
}
