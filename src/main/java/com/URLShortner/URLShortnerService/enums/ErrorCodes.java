package com.URLShortner.URLShortnerService.enums;

public enum ErrorCodes {

    SUCCESS(1100,"Success"),
    INVALID_LONG_URL(1111,"Invalid input long URL"),
    INVALID_SHORT_URL(1112,"Invalid input short URL"),
    URL_NOT_AVAILABLE(1113, "The long URL does not exist"),
    ALIAS_NOT_AVAILABLE(1114, "The alias is already used. Please try with some different alias"),
    GENERIC_ERROR(1200,"Failed to process the request");
    private int errorCode;
    private String message;

    private ErrorCodes(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
