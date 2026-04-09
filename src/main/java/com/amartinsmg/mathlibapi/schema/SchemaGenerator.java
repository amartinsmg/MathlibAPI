package com.amartinsmg.mathlibapi.schema;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.schema.annotations.ApiFunction;

public class SchemaGenerator {

    public static List<Map<String, Object>> generateSchema(Class<?> clazz) {
        List<Map<String, Object>> schema = new ArrayList<>();

        for (Method m : clazz.getDeclaredMethods()) {
            if (!m.isAnnotationPresent(ApiFunction.class)) {
                continue;
            }

            ApiFunction apiFunc = m.getAnnotation(ApiFunction.class);

            Map<String, Object> func = new LinkedHashMap<>();

            String name = apiFunc.name().isEmpty() ? m.getName() : apiFunc.name();

            func.put("name", name);

            if (!apiFunc.description().isEmpty()) {
                func.put("description", apiFunc.description());
            }

            func.put("returnType", m.getReturnType().getSimpleName());

            List<Map<String, Object>> args = new ArrayList<>();

            Parameter[] params = m.getParameters();

            for (Parameter p : params) {
                Map<String, Object> arg = new LinkedHashMap<>();
                arg.put("name", p.getName());
                arg.put("type", p.getType().getSimpleName());
                args.add(arg);
            }

            func.put("params", args);

            schema.add(func);
        }

        return schema;
    }
}
