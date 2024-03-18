package com.devacademy.discussionforum.exception;

public enum SqlStateCode {
    FOREIGN_KEY_VIOLATION("23503");
    private final String code;

    SqlStateCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
