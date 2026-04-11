package com.amartinsmg.mathlibapi;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.amartinsmg.mathlibapi.core.ApiCore;
import com.amartinsmg.mathlibapi.core.exceptions.ApiException;
import com.amartinsmg.mathlibapi.handler.RouterHandler;
import com.amartinsmg.mathlibapi.service.MathService;
import com.amartinsmg.mathlibapi.utils.JsonUtils;
import com.sun.net.httpserver.HttpServer;

public class AppTest {

    private static HttpServer server;
    private static HttpClient client;
    private static int port;

    @BeforeAll
    static void setup() throws Exception {
        client = HttpClient.newHttpClient();

        ApiCore core = new ApiCore(MathService.class);

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

                if (!(parsed instanceof java.util.Map body)) {
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
                if (!(requestArgs instanceof java.util.Map argsMap)) {
                    throw new ApiException(400, "'args' must be an object");
                }

                var result = core.execEngine(fnStr, argsMap);
                String json = JsonUtils.toJson(java.util.Map.of("result", result));

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
    static void teardown() {
        server.stop(0);
    }

    private String url(String path){
        return "http://localhost:" + port + path;
    }

    // ----------------------------
    // TESTES
    // ----------------------------

    @Test
    void testGetSchema() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url("/schema")))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("prime-factors"));
    }

    @Test
    void testPrimeFactors() throws Exception {
        String jsonBody = """
            {
                "fn": "prime-factors",
                "args": {
                    "num": 60
                }
            }
        """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url("/exec")))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());

        assertTrue(response.body().contains("2"));
        assertTrue(response.body().contains("3"));
        assertTrue(response.body().contains("5"));
    }

    @Test
    void testMean() throws Exception {
        String jsonBody = """
            {
                "fn": "mean",
                "args": {
                    "dataset": [1,8,2,5,2,3]
                }
            }
        """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url("/exec")))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());

        assertTrue(response.body().contains("3.5"));
    }
}