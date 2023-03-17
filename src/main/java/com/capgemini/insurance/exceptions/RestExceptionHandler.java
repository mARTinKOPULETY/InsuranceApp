package com.capgemini.insurance.exceptions;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import javax.servlet.http.*;
import java.time.*;
import java.time.format.*;

@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
//        HttpStatus status = ex.getStatus();
//        String errorMessage = ex.getReason();
//
//        ErrorResponse errorResponse = new ErrorResponse(status, ex.getReason());
//        logger.error( "\u001B[31mError " + status.value() + ": " + errorMessage + "\u001B[0m");
//
//        return new ResponseEntity<>(errorResponse, status);
//    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusExceptionT(HttpServletRequest request, ResponseStatusException ex) {
        HttpStatus status = ex.getStatus();
        String errorMessage = ex.getReason();
        Instant timestamp = Instant.now();

        ErrorResponse errorResponse = new ErrorResponse(status.value(), errorMessage,  request.getRequestURI(), timestamp);

        logger.error("\u001B[31mError " + status.value() + ": " + errorMessage + "\u001B[0m");

        return new ResponseEntity<>(errorResponse, status);
    }


}