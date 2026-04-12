package com.amartinsmg.mathlibapi.filter;

import java.io.IOException;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

public class CorsFilter extends Filter {

    @Override
    public void doFilter(HttpExchange ex, Chain ch) throws IOException {
        ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        ex.getResponseHeaders().add("Access-Control-Allow-Methods",
                "GET,POST,PUT,DELETE,OPTIONS");
        if (ex.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            ex.sendResponseHeaders(204, -1);
            ex.close();
        } else {
            ch.doFilter(ex);
        }
    }

    @Override
    public String description() {
        return "Enables CORS support and handles preflight OPTIONS requests";
    }
}
