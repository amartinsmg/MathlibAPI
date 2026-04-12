package com.amartinsmg.mathlibapi.core.schema;

import java.util.List;
import java.util.Map;

import com.amartinsmg.mathlibapi.core.exceptions.ValidationException;
import com.amartinsmg.mathlibapi.core.schema.models.FunctionSchema;
import com.amartinsmg.mathlibapi.core.schema.models.ParamSchema;

public class SchemaValidator {

    public static void validate(
            FunctionSchema fn,
            Map<String, Object> args
    ) {
        if (args.size() != fn.params.size()) {
            throw new ValidationException("Unexpected arguments");
        }
        for (ParamSchema p : fn.params) {
            if (!args.containsKey(p.name)) {
                throw new ValidationException("Missing argument: '" + p.name + "'");
            }
            var typeDef = p.type;
            if (!isValidType(p.type, args.get(p.name))) {
                throw new ValidationException(
                        "Invalid argument '" + p.name
                        + "': expected " + typeDef.toString());
            }
            if (!isValidRange(args.get(p.name), p.min, p.max)) {
                throw new ValidationException(
                        "Argument '" + p.name + "' out of range ["
                        + p.min + ", " + p.max + "]");
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
                case "string":
                    return arg instanceof String;
                default:
                    return false;
            }
        } else if (typeDef instanceof Map map) {
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
                    var objArg = (Map<String, Object>) arg;
                    var props = (Map<String, Object>) map.get("properties");
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

    public static boolean isValidRange(Object arg, Number min, Number max) {
        if (!(arg instanceof Number num)) {
            return true;
        }

        Double n = num.doubleValue();

        if (min != null && n < min.doubleValue()) {
            return false;
        }
        return !(max != null && n > max.doubleValue());
    }

    public static boolean isWholeNumber(Number n) {
        return Math.floor(n.doubleValue()) == n.doubleValue();
    }

}
