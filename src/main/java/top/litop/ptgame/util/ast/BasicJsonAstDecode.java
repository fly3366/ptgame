package top.litop.ptgame.util.ast;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Promise;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BasicJsonAstDecode implements AstDecode {

    public static AstTree astTreeDecode(Object value) {

        final Object initValue = Json.decodeValue((String) value);
        final JsonObject initJsonObj = (JsonObject) initValue;


        BasicAst localAst = new BasicAst();

        Map<String, String> clientSource = new HashMap<String, String>();

        Iterator<Map.Entry<String, Object>> clientValue = initJsonObj.getJsonObject("clientValue").iterator();
        while (clientValue.hasNext()) {
            Map.Entry<String, Object> next = clientValue.next();
            clientSource.put(next.getKey(), next.getValue().toString());
        }
        localAst.setClientDisplaySource(clientSource);


        JsonArray initProps = initJsonObj.getJsonArray("initProps");
        Map<String, Integer> playerProps[] =
                new HashMap[initProps.size()];

        Iterator<Object> initPropsValue = initProps.iterator();
        int initPropsFlag = 0;
        while (initPropsValue.hasNext()) {
            JsonObject next = (JsonObject) initPropsValue.next();
            Iterator<Map.Entry<String, Object>> onePlayerProp = next.iterator();
            while (onePlayerProp.hasNext()) {
                Map.Entry<String, Object> onePlayerOneProp = onePlayerProp.next();
                playerProps[initPropsFlag].put(onePlayerOneProp.getKey(), Integer.parseInt(onePlayerOneProp.getValue().toString()));
            }
            initPropsFlag++;
        }
        localAst.setClientPlayerProps(playerProps);


        JsonArray nextAstFork = initJsonObj.getJsonArray("nextStage");

        String clientFork[] = new String[nextAstFork.size()];
        Iterator<Object> nextAstForkIterator = nextAstFork.iterator();
        int nextAstForkFlag = 0;
        while (nextAstForkIterator.hasNext()) {
            JsonObject next = (JsonObject) nextAstForkIterator.next();
            clientFork[nextAstForkFlag] = next.encode();
            nextAstForkFlag++;
        }
        localAst.setClientFork(clientFork);

        JsonArray nextAstValue = initJsonObj.getJsonArray("next");
        int nextAstValueFlag = 0;
        String nextAstTreeValue[] = new String[nextAstValue.size()];
        Iterator<Object> nextAstValueIterator = nextAstValue.iterator();
        while (nextAstValueIterator.hasNext()) {
            JsonObject next = (JsonObject) nextAstValueIterator.next();
            nextAstTreeValue[nextAstValueFlag] = next.encode();
            nextAstValueFlag++;


        }
        System.out.println("JSON解析完毕");
        return new BasicJsonAstTree(localAst, nextAstTreeValue);
    }
}
