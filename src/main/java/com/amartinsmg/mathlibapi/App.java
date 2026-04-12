package com.amartinsmg.mathlibapi;

import java.net.InetSocketAddress;
import java.util.Map;

import com.amartinsmg.mathlibapi.core.ApiCore;
import com.amartinsmg.mathlibapi.core.exceptions.ApiException;
import com.amartinsmg.mathlibapi.filter.CorsFilter;
import com.amartinsmg.mathlibapi.handler.RouterHandler;
import com.amartinsmg.mathlibapi.service.MathService;
import com.amartinsmg.mathlibapi.utils.JsonUtils;
import com.sun.net.httpserver.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {
        ApiCore core = new ApiCore(MathService.class);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/schema", exchange -> {
            new RouterHandler("GET", ex -> {
                var schema = core.getSchema();
                String json = JsonUtils.toJson(schema);

                ex.getResponseHeaders().add("Content-Type", "application/json");
                ex.sendResponseHeaders(200, json.length());
                ex.getResponseBody().write(json.getBytes());
                ex.close();
            }).handle(exchange);
        }).getFilters().add(new CorsFilter());

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
        }).getFilters().add(new CorsFilter());

        server.start();
        System.out.println("Server running on http://localhost:8000");
    }
}
