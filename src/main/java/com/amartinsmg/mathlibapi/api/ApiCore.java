package com.amartinsmg.mathlibapi.api;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.schema.SchemaGenerator;

public class ApiCore {

    private final FunctionDispatcher dispatcher;
    private final SchemaGenerator schema;

    public ApiCore(Class<?> clazz) {
        this.dispatcher = new FunctionDispatcher(clazz);
        this.schema = new SchemaGenerator(clazz);
    }

    public List<Map<String, Object>> getSchema() {
        return schema.getSchema();
    }

    public Object engine(String fn, Map<String, Object> args) throws Exception {

        Method m = dispatcher.get(fn);

        var fnSchema = schema.getFunctionSchema(fn);

        SchemaValidator.validate(fnSchema, args);

        var convertedArgs = TypeConverter.convertArgs(fnSchema, args);

        return dispatcher.call(m, convertedArgs);
    }
}
