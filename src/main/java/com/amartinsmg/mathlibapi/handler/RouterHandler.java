package com.amartinsmg.mathlibapi.handler;

import java.io.IOException;
import java.util.Map;

import com.amartinsmg.mathlibapi.core.exceptions.ApiException;
import com.amartinsmg.mathlibapi.utils.JsonUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RouterHandler {

    private final String method;
    private final HttpHandler handler;

    public RouterHandler(String method, HttpHandler handler) {
        this.method = method;
        this.handler = handler;
    }

    public void handle(HttpExchange ex) throws IOException {
        if (!ex.getRequestMethod().equals(method)) {
            ex.sendResponseHeaders(405, -1);
            ex.close();
            return;
        }
        try {
            handler.handle(ex);
        } catch (ApiException e) {
            String error = JsonUtils.toJson(Map.of("error", e.getMessage()));

            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(e.getStatus(), error.length());
            ex.getResponseBody().write(error.getBytes());
            ex.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            String error = "{\"error\":\"fatal\"}";

            ex.sendResponseHeaders(500, error.length());
            ex.getResponseBody().write((error.getBytes()));
            ex.close();
        }
    }
}
