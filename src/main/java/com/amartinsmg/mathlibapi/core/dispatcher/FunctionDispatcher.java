package com.amartinsmg.mathlibapi.core.dispatcher;

import java.lang.reflect.Method;

import com.amartinsmg.mathlibapi.core.exceptions.FunctionNotFoundException;

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
            throw new FunctionNotFoundException(fn);
        }

        return m;
    }
}
