package top.litop.ptgame.util.ast;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class ExmapleAstTree {
    public AstTree getValue() {
        return new BasicAstTree();
    }

    private static String exmapleJson = Json.encode(
            new JsonObject()
                    .put("version", 1)
                    .put("name", "test")
                    .put("level", 0)
                    .put("initProps", new JsonObject()
                            .put("1", new JsonObject()
                                    .put("san", 0))
                            .put("2", new JsonObject()
                                    .put("san", 0)))
                    .put("initValue", "First")
    );
}
