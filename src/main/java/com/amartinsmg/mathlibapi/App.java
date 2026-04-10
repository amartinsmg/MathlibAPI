package com.amartinsmg.mathlibapi;

import java.net.InetSocketAddress;
import java.util.Map;

import com.amartinsmg.mathlibapi.core.ApiCore;
import com.amartinsmg.mathlibapi.handler.RouterHandler;
import com.amartinsmg.mathlibapi.service.MathService;
import com.amartinsmg.mathlibapi.utils.JsonUtils;
import com.sun.net.httpserver.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {
        ApiCore core = new ApiCore(MathService.class);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

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

                Map<String, Object> body = (Map<String, Object>) JsonUtils.readJson(ex.getRequestBody());

                String fn = (String) body.get("fn");
                Map<String, Object> argsMap = (Map<String, Object>) body.get("args");

                var result = core.execEngine(fn, argsMap);
                String json = JsonUtils.toJson(result);

                ex.getResponseHeaders().add("Content-Type", "application/json");
                ex.sendResponseHeaders(200, json.length());
                ex.getResponseBody().write(json.getBytes());
                ex.close();
            }).handle(exchange);
        });

    }
}
