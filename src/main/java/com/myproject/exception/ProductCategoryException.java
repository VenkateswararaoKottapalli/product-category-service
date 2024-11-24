package com.myproject.exception;

import lombok.Data;

@Data
public class ProductCategoryException extends RuntimeException {

    private final String errorCode;

    public ProductCategoryException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
