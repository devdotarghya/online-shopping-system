package org.arghya.app.orderservice.exceptionhandler;

import org.arghya.app.orderservice.dto.ServiceErrorResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public HttpEntity<ServiceErrorResponse> handleGenericException(Exception e) {
        ServiceErrorResponse serviceErrorResponse = ServiceErrorResponse.builder()
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(serviceErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


