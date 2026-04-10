package com.amartinsmg.mathlibapi.api;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.schema.FunctionSchema;

public class TypeConverter {

    public static Object[] convertArgs(FunctionSchema fn, Map<String, Object> args) {
        int length = fn.params.size();
        Object[] arr = new Object[length];
        for (int i = 0; i < length; i++) {
            String name = fn.params.get(i).name;
            Object type = fn.params.get(i).type;
            arr[i] = convertType(type, args.get(name));
        }
        return arr;
    }

    protected static Object convertType(Object typeDef, Object arg) {
        if (typeDef instanceof String type) {
            if (arg instanceof Number num) {
                switch (type) {
                    case "int32":
                        return num.intValue();
                    case "int64":
                        return num.longValue();
                    case "float":
                        return num.floatValue();
                    case "double":
                        return num.doubleValue();
                    default:
                }
            } else if ("boolean".equals(type) && arg instanceof Boolean) {
                return arg;
            }
            return arg;
        }
        if (typeDef instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) typeDef;
            String type = (String) map.get("type");
            if ("array".equals(type) && arg instanceof List<?> list) {
                return list.stream()
                        .map(o -> convertType(map.get("items"), o))
                        .toArray();
            } else if ("object".equals(type) && arg instanceof Map<?, ?> inputMap) {
                Map<String, Object> properties = (Map<String, Object>) map.get("properties");
                Map<String, Object> result = new HashMap<>();

                for (Map.Entry<String, Object> e : properties.entrySet()) {
                    String key = e.getKey();
                    var fieldType = e.getValue();

                    var value = inputMap.get(key);

                    var converted = convertType(fieldType, value);

                    result.put(key, converted);
                }

                return result;
            }
        }

        return null;
    }

    public static Object normalizeReturn(Object value) {

        if (value == null) {
            return null;
        }

        if (value instanceof Number
                || value instanceof Boolean
                || value instanceof String) {
            return value;
        }

        if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                list.add(normalizeReturn(Array.get(value, i)));
            }
            return list;
        }

        if (value instanceof List<?> list) {
            return list.stream()
                    .map(TypeConverter::normalizeReturn)
                    .toList();
        }

        if (value instanceof Map<?, ?> map) {
            Map<String, Object> normalized = new HashMap<>();
            map.forEach((k, v) -> {
                normalized.put(String.valueOf(k), normalizeReturn(v));
            });
            return normalized;
        }

        return object2Map(value);
    }

    protected static Map<?, ?> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();

        for (Field f : obj.getClass().getFields()) {
            try {
                var value = f.get(obj);
                map.put(f.getName(), normalizeReturn(value));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return map;
    }

}
