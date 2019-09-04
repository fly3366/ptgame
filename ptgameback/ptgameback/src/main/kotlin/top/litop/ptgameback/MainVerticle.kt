package top.litop.ptgameback

import io.vertx.core.AbstractVerticle

class MainVerticle : AbstractVerticle() {
  override fun start(){
    vertx.deployVerticle("top.litop.ptgameback.room.MainService")
  }
  override fun stop(){

  }
}
