package com.boot.core.common.exception;

/**
 * 文件上传格式异常
 */
public class FileFormatErrorException extends RuntimeException {

    public FileFormatErrorException(){}

    public FileFormatErrorException(String errorMessage) {
        super(errorMessage);
    }
    public FileFormatErrorException(Throwable cause) {
        super(cause);
    }
}
