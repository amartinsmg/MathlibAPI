package com.amartinsmg.mathlibapi.core.exceptions;

public class BusinessException extends ApiException {

    public BusinessException(String m) {
        super(422, m);
    }
}
