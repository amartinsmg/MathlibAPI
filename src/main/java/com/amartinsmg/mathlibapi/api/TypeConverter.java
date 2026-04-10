package com.amartinsmg.mathlibapi.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.schema.FunctionSchema;

public class TypeConverter {

    public Object[] convertArgs(FunctionSchema fn, Map<String, Object> args) {
        int length = fn.params.size();
        Object[] arr = new Object[length];
        for (int i = 0; i < length; i++) {
            String name = fn.params.get(i).name;
            Object type = fn.params.get(i).type;
            arr[i] = convertType(type, args.get(name));
        }
        return arr;
    }

    protected Object convertType(Object typeDef, Object arg) {
        if (typeDef instanceof String type) {
            if (arg instanceof Number num) {
                switch (type) {
                    case "int32":
                        return num.intValue();
                    case "int64":
                        return num.longValue();
                    case "float":
                        return num.longValue();
                    case "double":
                        return num.longValue();
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
}
