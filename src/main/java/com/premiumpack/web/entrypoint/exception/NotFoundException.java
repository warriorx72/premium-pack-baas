package com.premiumpack.web.entrypoint.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends TechnicalException {

    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

    public NotFoundException(String message) {
        super(message);
        this.code = 404;
        this.message = "Not found";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
