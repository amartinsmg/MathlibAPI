package com.amartinsmg.mathlibapi.utils;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing JSON");
        }
    }

    public static Object readJson(InputStream is) {
        try {
            return mapper.readValue(is, Object.class);
        } catch (IOException e) {
            throw new RuntimeException("Error generating JSON");
        }
    }

}
