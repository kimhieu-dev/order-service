package com.nkh.orderservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    RESPONSE_NULL(2000,"Response is null",HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(2001,"Product not found",HttpStatus.NOT_FOUND),
    PRODUCT_NOT_ENOUGH(2002,"Product not enough",HttpStatus.BAD_REQUEST),

    UNCATEGORIZED_ERROR(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    ;
    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
