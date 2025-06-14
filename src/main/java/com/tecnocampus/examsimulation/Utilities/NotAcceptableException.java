package com.tecnocampus.examsimulation.Utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends RuntimeException {
    public NotAcceptableException(String message) {
        super("can't be accepted" + message);
    }
}
