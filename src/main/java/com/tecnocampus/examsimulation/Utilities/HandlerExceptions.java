package com.tecnocampus.examsimulation.Utilities;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(NotFoundException.class)
    @ApiResponse(responseCode = "404", description = "not found")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFound(NotFoundException e, HttpServletRequest request) {
        return new ErrorResponse(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ApiResponse(responseCode = "400", description = "bad request")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(BadRequestException e, HttpServletRequest request) {
        return new ErrorResponse(e, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAcceptableException.class)
    @ApiResponse(responseCode = "406", description = "can't accept it")
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse notAccepted(NotAcceptableException e, HttpServletRequest request) {
        return new ErrorResponse(e, request, HttpStatus.NOT_ACCEPTABLE);
    }

}

