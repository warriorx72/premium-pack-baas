package com.premiumpack.web.entrypoint.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TechnicalException extends RuntimeException {

    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

    public TechnicalException(String message) {
        super(message);
        this.code = 500;
        this.message = "An error occurred";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
