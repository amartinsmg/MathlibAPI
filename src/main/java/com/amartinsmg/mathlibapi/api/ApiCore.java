package com.amartinsmg.mathlibapi.api;

import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.schema.SchemaGenerator;

public class ApiCore {

    private final FunctionDispatcher dispacher;
    private final List<Map<String, Object>> schema;

    public ApiCore(Class<?> clazz) {
        this.dispacher = new FunctionDispatcher(clazz);
        this.schema = SchemaGenerator.generateSchema(clazz);
    }

    public List<Map<String, Object>> getSchema() {
        return schema;
    }
}
