package com.javaexpress.carts.exception;

import com.javaexpress.carts.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CardAlreadyExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(CardAlreadyExistException ex) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), LocalDateTime.now());

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception ex) {
        log.error("Exception occurred: " + ex.getLocalizedMessage(), ex);
        return new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), LocalDateTime.now());

    }
}
