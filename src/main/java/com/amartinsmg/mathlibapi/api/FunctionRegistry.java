package com.amartinsmg.mathlibapi.api;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.amartinsmg.mathlibapi.schema.annotations.ApiFunction;
import com.amartinsmg.mathlibapi.service.MathService;

public class FunctionRegistry {

    private final Map<String, Method> registry = new HashMap<>();

    public FunctionRegistry() {
        register(MathService.class);
    }

    public void register(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ApiFunction.class)) {
                ApiFunction apiFunc = method.getAnnotation(ApiFunction.class);
                String name = apiFunc.name().isEmpty()
                        ? method.getName()
                        : apiFunc.name();
                registry.put(name, method);
            }
        }
    }

    public Method get(String name) {
        return registry.get(name);
    }

}
