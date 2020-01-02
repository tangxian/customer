package com.boot.core.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 远程对接服务器异常
 */
public class ServerErrorException extends RuntimeException {

    public ServerErrorException(){}

    public ServerErrorException(String errorMessage) {
        super(errorMessage);
    }
    public ServerErrorException(Throwable cause) {
        super(cause);
    }
}
