package com.amartinsmg.mathlibapi.core.exceptions;

public class ApiException extends RuntimeException {

    private final int status;

    public ApiException(int status, String m) {
        super(m);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
