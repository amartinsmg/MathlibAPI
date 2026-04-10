package com.amartinsmg.mathlibapi.api;

import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.schema.FunctionSchema;
import com.amartinsmg.mathlibapi.schema.ParamSchema;

public class SchemaValidator {

    public static void validate(
            FunctionSchema fn,
            Map<String, Object> args
    ) {
        if (args.size() != fn.params.size()) {
            throw new RuntimeException("Unexpected arguments");
        }
        for (ParamSchema p : fn.params) {
            if (!args.containsKey(p.name)) {
                throw new RuntimeException("Missing argument: " + p.name);
            }
            Object typeDef = p.type;
            if (!isValidType(typeDef, args.get(p.name))) {
                throw new RuntimeException(
                        "Invalid argument " + p.name
                        + ": expected " + typeDef.toString());
            }
        }
    }

    public static boolean isValidType(Object typeDef, Object arg) {
        if (arg == null) {
            return false;
        }

        if (typeDef instanceof String type) {
            switch (type) {
                case "int32":
                case "int64":
                    return arg instanceof Integer
                            || arg instanceof Long
                            || (arg instanceof Number && isWholeNumber((Number) arg));
                case "float":
                case "double":
                    return arg instanceof Number;
                case "boolean":
                    return arg instanceof Boolean;
                default:
                    return false;
            }
        } else if (typeDef instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) typeDef;
            String type = (String) map.get("type");
            switch (type) {
                case "array":
                    if (!(arg instanceof List<?> list)) {
                        return false;
                    }
                    Object itemsType = map.get("items");
                    for (Object item : list) {
                        if (!isValidType(itemsType, item)) {
                            return false;
                        }
                    }
                    return true;
                case "object":
                    if (!(arg instanceof Map)) {
                        return false;
                    }
                    Map<String, Object> objArg = (Map<String, Object>) arg;
                    Map<String, Object> props = (Map<String, Object>) map.get("properties");
                    for (Map.Entry<String, Object> e : props.entrySet()) {
                        String key = e.getKey();
                        Object propType = e.getValue();
                        if (!objArg.containsKey(key)) {
                            return false;
                        }
                        if (!isValidType(propType, objArg.get(key))) {
                            return false;
                        }
                    }
                    return true;
                default:
                    return false;
            }
        }

        return false;
    }

    public static boolean isWholeNumber(Number n) {
        return Math.floor(n.doubleValue()) == n.doubleValue();
    }

}
