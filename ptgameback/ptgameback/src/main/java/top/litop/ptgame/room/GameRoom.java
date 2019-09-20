package top.litop.ptgame.room;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import top.litop.ptgame.config.ChannelConfig;


public class GameRoom extends AbstractVerticle {
    private String roomName;
    private String roomId;
    private boolean isReady = false;
    private MessageConsumer<Object> roomEvent;

    private boolean roomInit(){
        JsonObject config = vertx.getOrCreateContext().config();
        this.roomName = config.getString("roomName").toString();
        this.roomId = config.getString("roomId").toString();
        EventBus eventBus = vertx.eventBus();
        this.roomEvent = eventBus.consumer("channel.A001."+this.roomId+".push");
        this.roomEvent.completionHandler(res -> {
            if (res.succeeded()) {
                System.out.println(this.roomId+"房间道初始化成功");
            } else {
                System.out.println(this.roomId+"房间初始化失败");
            }
        });
        return true;
    }

    @Override
    public void start() throws Exception {
        if(this.roomInit()){

            System.out.println(this.roomId + "房间Online");
            vertx.eventBus().publish("channel.A001.push", new JsonObject()
                    .put("option", 0)
                    .put("roomName", this.roomName)
                    .put("roomId", this.roomId)
            );
            this.roomEvent.handler(message->{
                //GameLoop
            });
        }else {

            System.out.println(this.roomId + "房间初始化失败");
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println(this.roomId + "房间Offline");
    }
}
