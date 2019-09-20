package top.litop.ptgame;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.deployVerticle("top.litop.ptgame.room.MainService");
    }

    @Override
    public void stop() throws Exception {

    }
}
