package com.amartinsmg.mathlibapi.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.amartinsmg.mathlibapi.core.schema.annotations.ApiFunction;

public class FunctionRegistry {

    private final Map<String, Method> registry = new HashMap<>();

    public FunctionRegistry(Class<?> clazz) {
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ApiFunction.class)) {
                ApiFunction apiFunc = m.getAnnotation(ApiFunction.class);
                String name = apiFunc.name().isEmpty()
                        ? m.getName()
                        : apiFunc.name();
                registry.put(name, m);
            }
        }
    }

    public Method get(String name) {
        return registry.get(name);
    }

}
