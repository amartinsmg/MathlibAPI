package com.amartinsmg.mathlibapi.core.exceptions;

public class ValidationException extends ApiException {

    public ValidationException(String m) {
        super(400, m);
    }
}
