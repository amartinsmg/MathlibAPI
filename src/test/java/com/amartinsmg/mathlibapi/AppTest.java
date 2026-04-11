package com.amartinsmg.mathlibapi;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import com.amartinsmg.mathlibapi.core.ApiCore;
import com.amartinsmg.mathlibapi.core.exceptions.ApiException;
import com.amartinsmg.mathlibapi.handler.RouterHandler;
import com.amartinsmg.mathlibapi.service.MathService;
import com.amartinsmg.mathlibapi.utils.JsonUtils;
import com.sun.net.httpserver.HttpServer;

public class AppTest {

    private static ApiCore core;
    private static HttpServer server;
    private static HttpClient client;
    private static int port;

    @BeforeAll
    static void startSever() throws Exception {

        client = HttpClient.newHttpClient();

        core = new ApiCore(MathService.class);

        server = HttpServer.create(new InetSocketAddress(0), 0);

        server.createContext("/schema", exchange -> {
            new RouterHandler("GET", ex -> {
                var schema = core.getSchema();
                String json = JsonUtils.toJson(schema);

                ex.getResponseHeaders().add("Content-Type", "application/json");
                ex.sendResponseHeaders(200, json.length());
                ex.getResponseBody().write(json.getBytes());
                ex.close();
            }).handle(exchange);
        });

        server.createContext("/exec", exchange -> {
            new RouterHandler("POST", ex -> {

                Object parsed;

                try {
                    parsed = JsonUtils.readJson(ex.getRequestBody());
                } catch (Exception e) {
                    throw new ApiException(400, "Invalid JSON body");
                }

                if (!(parsed instanceof Map body)) {
                    throw new ApiException(400, "JSON body must be an object");
                }

                var fn = body.get("fn");
                var requestArgs = body.get("args");

                if (!(fn instanceof String fnStr)) {
                    throw new ApiException(400, "'fn' must be a string");
                }
                if (requestArgs == null) {
                    throw new ApiException(400, "'args' is required");
                }
                if (!(requestArgs instanceof Map argsMap)) {
                    throw new ApiException(400, "'args' must be an object (key-value)");
                }

                var result = core.execEngine(fnStr, argsMap);
                String json = JsonUtils.toJson(Map.of("result", result));

                ex.getResponseHeaders().add("Content-Type", "application/json");
                ex.sendResponseHeaders(200, json.length());
                ex.getResponseBody().write(json.getBytes());
                ex.close();
            }).handle(exchange);
        });

        server.start();

        port = server.getAddress().getPort();
    }

    @AfterAll
    static void StopServer() {
        server.stop(port);
    }

    static String getUrl(String path) {
        return "http://localhost:" + port + path;
    }

    @TestFactory
    Collection<DynamicTest> contractTests() {
        var schema = core.getSchema();

        return schema.stream()
                .map(fn -> DynamicTest.dynamicTest(
                "Test function: " + fn.get("name"),
                () -> testFunction(fn)
        )).toList();
    }

    static void testFunction(Map<String, Object> fn) throws Exception {
        Map<String, Object> args = new HashMap<>();

        var params = (List<Map<String, Object>>) fn.get("params");

        for (var p : params) {
            String pName = (String) p.get("name");
            var pType = p.get("type");

            args.put(pName, generateValue(pType));
        }

        String body = JsonUtils.toJson(Map.of(
                "fn", fn.get("name"),
                "args", args
        ));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getUrl("/exec")))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200,
                response.statusCode(),
                "Error in function: " + fn.get("name"));
    }

    static Object generateValue(Object type) {
        if (type instanceof String t) {
            return switch (t) {
                case "int32", "int64" ->
                    1;
                case "float", "double" ->
                    1.0;
                case "string" ->
                    "test";
                case "boolean" ->
                    true;
                default ->
                    throw new RuntimeException("Unknown type: " + t);
            };

        }
        if (type instanceof Map<?, ?> map) {
            String typeName = (String) map.get("type");

            if ("array".equals(typeName)) {
                var itemType = map.get("items");
                return List.of(
                        generateValue(itemType),
                        generateValue(itemType),
                        generateValue(itemType));
            }
            if ("object".equals(typeName)) {
                var props = (Map<String, Object>) map.get("properties");
                Map<String, Object> obj = new HashMap<>();

                for (Map.Entry<String, Object> e : props.entrySet()) {
                    String key = e.getKey();
                    var fieldType = e.getValue();
                    var value = generateValue(fieldType);
                    obj.put(key, value);
                }
                return obj;
            }
        }
        throw new RuntimeException("Unsuported type: " + type);
    }

}
