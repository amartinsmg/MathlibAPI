package com.amartinsmg.mathlibapi.core;

import java.lang.reflect.Method;

public class FunctionDispatcher {

    private final FunctionRegistry registry;

    public FunctionDispatcher(Class<?> clazz) {
        this.registry = new FunctionRegistry(clazz);
    }

    public Object call(Method m, Object[] args) throws Exception {
        return m.invoke(null, args);
    }

    public Method get(String fn) {
        Method m = registry.get(fn);

        if (m == null) {
            throw new RuntimeException("Function not found: " + fn);
        }

        return m;
    }
}
