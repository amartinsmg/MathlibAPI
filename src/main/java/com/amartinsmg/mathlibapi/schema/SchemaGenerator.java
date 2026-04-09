package com.amartinsmg.mathlibapi.schema;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.schema.annotations.ApiFunction;
import com.amartinsmg.mathlibapi.schema.annotations.ApiParam;

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

            func.put("returnType", formattType(m.getReturnType()));

            func.put("params", formattParameters(m));

            schema.add(func);
        }

        return schema;
    }

    protected static List<Map<String, Object>> formattParameters(Method m) {
        List<Map<String, Object>> args = new ArrayList<>();

        Parameter[] params = m.getParameters();

        for (Parameter p : params) {
            Map<String, Object> arg = new LinkedHashMap<>();
            ApiParam apiParam = p.getAnnotation(ApiParam.class);
            String name = (apiParam != null && !apiParam.name().isEmpty())
                    ? apiParam.name()
                    : p.getName();
            arg.put("name", name);
            arg.put("type", formattType(p.getType()));
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

        Map<String, Object> objSchema = new HashMap<>();
        objSchema.put("type", "object");
        
        Map<String, Object> properties = new HashMap<>();
        
        for (Field f : type.getDeclaredFields()) {
            f.setAccessible(true);
            properties.put(f.getName(), formattType(f.getType()));
        }
        
        objSchema.put("properies", properties);

        return objSchema;

    }
}
