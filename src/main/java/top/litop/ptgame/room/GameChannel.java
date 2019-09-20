package top.litop.ptgame.room;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import top.litop.ptgame.config.ChannelConfig;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GameChannel extends AbstractVerticle {
    private ChannelConfig gameChannelConfig;
    private boolean isReady = false;
    MessageConsumer<Object> messageConsumer;
    volatile Integer defaultRoomId = 1;

    private boolean channelInit() {
        EventBus eventBus = vertx.eventBus();
        this.messageConsumer = eventBus.consumer("channel.A001.push");
        this.messageConsumer.completionHandler(res -> {
            if (res.succeeded()) {
                System.out.println("A001频道初始化成功");
            } else {
                System.out.println("A001频道初始化失败");
            }
        });
        this.gameChannelConfig = new ChannelConfig("A001", "Default");
        return true;
    }

    public String getChannelName() {
        if (isReady) {
            return this.gameChannelConfig.getChannelName();
        } else {
            return "Initializing";
        }
    }

    private Integer getRoomId() {
        this.defaultRoomId++;
        return this.defaultRoomId;
    }

    @Override
    public void start() throws Exception {
        if (this.channelInit()) {
            this.isReady = true;
            System.out.println(this.getChannelName() + "频道Online");
            this.messageConsumer.handler(message -> {
                JsonObject res = JsonObject.mapFrom(message.body());
                System.out.println("收到创建请求" + res.toString());
                if (res.getInteger("option").equals(1)) {
                    vertx.deployVerticle("top.litop.ptgame.room.GameRoom",
                            new DeploymentOptions()
                                    .setConfig(new JsonObject()
                                            .mergeIn(res)
                                            .put("channelId", this.gameChannelConfig.getChannelId().toString())

                                    )
                    );
                } else if (res.getInteger("option").equals(0)) {
                    System.out.println(res.getString("roomName")
                            + res.getString("roomId")
                            + "已创建");
                }
            });
        } else {
            System.out.println(this.getChannelName() + "频道Offline");
        }
        vertx.eventBus().publish("channel.A001.push", new JsonObject()
                .put("option", 1)
                .put("roomName", "Default")
                .put("roomId", "R001")
        );
    }

    @Override
    public void stop() throws Exception {
        System.out.println(this.getChannelName() + "频道Offline");
    }
}