package com.amartinsmg.mathlibapi.core.exceptions;

public class FunctionNotFoundException extends ApiException {

    public FunctionNotFoundException(String fn) {
        super(404, "Function not found: " + fn);
    }
}
