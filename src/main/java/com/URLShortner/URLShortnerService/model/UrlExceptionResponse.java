package com.URLShortner.URLShortnerService.model;

public class UrlExceptionResponse {

    private String message;
    private int errorCode;

    public UrlExceptionResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public UrlExceptionResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "UrlExceptionResponse{" +
                "message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
