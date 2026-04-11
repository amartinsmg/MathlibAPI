package com.amartinsmg.mathlibapi.core;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.core.dispatcher.FunctionDispatcher;
import com.amartinsmg.mathlibapi.core.exceptions.ApiException;
import com.amartinsmg.mathlibapi.core.schema.SchemaGenerator;
import com.amartinsmg.mathlibapi.core.schema.SchemaValidator;
import com.amartinsmg.mathlibapi.core.schema.models.FunctionSchema;

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

    public Object execEngine(String fn, Map<String, Object> args) {

        Method m = dispatcher.get(fn);

        FunctionSchema fnSchema = schema.getFunctionSchema(fn);

        SchemaValidator.validate(fnSchema, args);

        Object[] convertedArgs = TypeConverter.convertArgs(fnSchema, args);

        try {
            Object result = dispatcher.call(m, convertedArgs);
            return TypeConverter.normalizeReturn(result);
        } catch (Exception e) {
            if (e instanceof ApiException ex) {
                throw ex;
            }

            System.err.println(e.getMessage());
            throw new ApiException(500, "Execution error");
        }
    }
}
