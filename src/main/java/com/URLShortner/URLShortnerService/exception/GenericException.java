package com.URLShortner.URLShortnerService.exception;


public class GenericException extends Exception {
    private int errorCode;

    private String message = null;

    public GenericException(int errorCode) {
        this.errorCode = errorCode;
    }

    public GenericException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GenericException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public GenericException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public GenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public GenericException(int errorCode, String message){
        super(message);
        this.message = message;
        this.errorCode = errorCode;

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
