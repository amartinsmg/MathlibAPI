package com.amartinsmg.mathlibapi.core.schema;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.core.schema.annotations.ApiFunction;
import com.amartinsmg.mathlibapi.core.schema.annotations.ApiParam;
import com.amartinsmg.mathlibapi.core.schema.models.FunctionSchema;
import com.amartinsmg.mathlibapi.core.schema.models.ParamSchema;

public class SchemaGenerator {

    private final Map<String, FunctionSchema> schema;

    public SchemaGenerator(Class<?> clazz) {
        Map<String, FunctionSchema> fnMap = new HashMap<>();

        for (Method m : clazz.getDeclaredMethods()) {
            if (!m.isAnnotationPresent(ApiFunction.class)) {
                continue;
            }

            ApiFunction apiFunc = m.getAnnotation(ApiFunction.class);

            FunctionSchema func = new FunctionSchema();

            String name = apiFunc.name().isEmpty() ? m.getName() : apiFunc.name();

            func.name = name;

            func.namespace = apiFunc.namespace().isEmpty()
                    ? func.name
                    : apiFunc.namespace();

            func.description = apiFunc.description();

            func.returnType = formattType(m.getReturnType());

            func.params = formattParameters(m);

            fnMap.put(name, func);
        }

        this.schema = fnMap;
    }

    public List<Map<String, Object>> getSchema() {
        List<Map<String, Object>> dict = new ArrayList<>();

        for (FunctionSchema fn : this.schema.values()) {
            Map<String, Object> fnMap = new HashMap<>();

            fnMap.put("name", fn.name);
            fnMap.put("description", fn.description);
            fnMap.put("namespace", fn.namespace);
            fnMap.put("returnType", fn.returnType);

            List<Map<String, Object>> paramList = new ArrayList<>();

            for (ParamSchema p : fn.params) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("name", p.name);
                paramMap.put("type", p.type);

                paramList.add(paramMap);
            }

            fnMap.put("params", paramList);

            dict.add(fnMap);
        }

        return dict;
    }

    public FunctionSchema getFunctionSchema(String fn) {
        return schema.get(fn);
    }

    protected static ArrayList<ParamSchema> formattParameters(Method m) {
        ArrayList<ParamSchema> args = new ArrayList<>();

        Parameter[] params = m.getParameters();

        for (Parameter p : params) {
            ParamSchema arg = new ParamSchema();
            ApiParam apiParam = p.getAnnotation(ApiParam.class);
            String name = (apiParam != null && !apiParam.name().isEmpty())
                    ? apiParam.name()
                    : p.getName();
            arg.name = name;
            arg.type = formattType(p.getType());
            args.add(arg);
        }

        return args;
    }

    protected static Object formattType(Class<?> type) {

        if (type.isArray()) {
            Map<String, Object> arraySchema = new HashMap<>();
            arraySchema.put("type", "array");
            arraySchema.put("items", formattType(type.getComponentType()));
            return arraySchema;
        }

        if (type == int.class || type == Integer.class) {
            return "int32";
        }

        if (type == long.class || type == Long.class) {
            return "int64";
        }

        if (type == float.class || type == Float.class) {
            return "float";
        }

        if (type == double.class || type == Double.class) {
            return "double";
        }

        if (type == boolean.class || type == Boolean.class) {
            return "boolean";
        }

        if (type == String.class) {
            return "string";
        }

        Map<String, Object> objSchema = new HashMap<>();
        objSchema.put("type", "object");

        Map<String, Object> properties = new HashMap<>();

        for (Field f : type.getDeclaredFields()) {
            f.setAccessible(true);
            properties.put(f.getName(), formattType(f.getType()));
        }

        objSchema.put("properties", properties);

        return objSchema;

    }
}
