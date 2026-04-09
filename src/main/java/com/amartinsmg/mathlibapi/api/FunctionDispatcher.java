package com.amartinsmg.mathlibapi.api;

import java.lang.reflect.Method;

public class FunctionDispatcher {

    private final FunctionRegistry registry;

    public FunctionDispatcher(Class<?> clazz){
        this.registry = new FunctionRegistry(clazz);
    }

    public Object call(String name, Object[] args) throws Exception {
        Method m = registry.get(name);

        if(m == null){
            throw new RuntimeException("Function not found: " + name);
        }

        return m.invoke(null, args);
    }
}
