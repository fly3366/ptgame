package top.litop.ptgame.room;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;

public class MainService extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();
        MessageConsumer<Object> consumer = eventBus.consumer("service.message.push");
        consumer.handler(message->{
           System.out.println("服务器收到信息"+message.body());
        });
        consumer.completionHandler(res->{
            if (res.succeeded()){
                System.out.println("服务器Online");
                DeploymentOptions initConfig = new DeploymentOptions();
                initConfig.setInstances(16);
                vertx.deployVerticle("top.litop.ptgame.room.GameChannel",initConfig);
            }else {
                System.out.println("服务器Error");
            }
        });
    }

    @Override
    public void stop() throws Exception {
        System.out.println("服务器Offline");
    }
}
