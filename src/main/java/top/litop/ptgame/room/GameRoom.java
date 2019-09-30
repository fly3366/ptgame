package top.litop.ptgame.room;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import top.litop.ptgame.config.ChannelConfig;
import top.litop.ptgame.loop.GameLoopStack;


public class GameRoom extends AbstractVerticle {
    private String roomName;
    private String roomId;
    private boolean isReady = false;
    private MessageConsumer<Object> roomEvent;

    private boolean roomInit() {
        JsonObject config = vertx.getOrCreateContext().config();
        this.roomName = config.getString("roomName").toString();
        this.roomId = config.getString("roomId").toString();
        EventBus eventBus = vertx.eventBus();
        this.roomEvent = eventBus.consumer("channel.A001." + this.roomId + ".push");
        this.roomEvent.completionHandler(res -> {
            if (res.succeeded()) {
                System.out.println(this.roomId + "房间道初始化成功");
            } else {
                System.out.println(this.roomId + "房间初始化失败");
            }
        });
        return true;
    }

    @Override
    public void start() throws Exception {
        if (this.roomInit()) {

            System.out.println(this.roomId + "房间Online");
            vertx.eventBus().publish("channel.A001.push", new JsonObject()
                    .put("option", 0)
                    .put("roomName", this.roomName)
                    .put("roomId", this.roomId)
            );
            this.roomEvent.handler(message -> {
                //GameLoop
                GameLoopStack gameLoopStack = new GameLoopStack("{\n" +
                        "  \"version\": 1,\n" +
                        "  \"name\": \"某某杀人案（2人玩家）\",\n" +
                        "  \"players\": 2,\n" +
                        "  \"level\": 0,\n" +
                        "  \"initProps\": [\n" +
                        "    {\n" +
                        "      \"san\": 10\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"san\": 10\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientValue\": {\n" +
                        "    \"displayWords\": \"Welcome\",\n" +
                        "    \"clientMusic\": \"none\"\n" +
                        "  },\n" +
                        "  \"nextStage\": [\n" +
                        "    {\n" +
                        "      \"type\": 1,\n" +
                        "      \"displayWords\": \"I choose A\",\n" +
                        "      \"queryEqual\": {\n" +
                        "        \"equalPlayer\": 0,\n" +
                        "        \"equalValue\": [\n" +
                        "          1\n" +
                        "        ],\n" +
                        "        \"equalObject\": 5\n" +
                        "      }\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"type\": 1,\n" +
                        "      \"displayWords\": \"I choose B\",\n" +
                        "      \"queryEqual\": {\n" +
                        "        \"equalPlayer\": 1,\n" +
                        "        \"equalValue\": [\n" +
                        "          1\n" +
                        "        ],\n" +
                        "        \"equalObject\": 5\n" +
                        "      }\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"next\": [\n" +
                        "    {\n" +
                        "      \"version\": 1,\n" +
                        "      \"name\": \"testA\",\n" +
                        "      \"level\": -1,\n" +
                        "      \"initProps\": [\n" +
                        "      ],\n" +
                        "      \"clientValue\": {\n" +
                        "        \"displayWords\": \"A end\"\n" +
                        "      },\n" +
                        "      \"nextStage\": [\n" +
                        "      ],\n" +
                        "      \"next\": [\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"version\": 1,\n" +
                        "      \"name\": \"testB\",\n" +
                        "      \"level\": -1,\n" +
                        "      \"initProps\": [\n" +
                        "      ],\n" +
                        "      \"clientValue\": {\n" +
                        "        \"displayWords\": \"B end\"\n" +
                        "      },\n" +
                        "      \"nextStage\": [\n" +
                        "      ],\n" +
                        "      \"next\": [\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}\n");

            });
        } else {

            System.out.println(this.roomId + "房间初始化失败");
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println(this.roomId + "房间Offline");
    }
}
