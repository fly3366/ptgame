package top.litop.ptgame.util.ast;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class BasicJsonAstDecode implements AstDecode {

    @Override
    public AstTree astTreeDecode(Object value) {
        final Object initValue = Json.decodeValue((String) value);
        final JsonObject initJsonObj = (JsonObject) initValue;


        return null;
    }
}
