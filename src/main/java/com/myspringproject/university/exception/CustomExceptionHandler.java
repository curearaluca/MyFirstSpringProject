package com.myspringproject.university.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler({ProfessorNotFoundException.class, StudentNotFoundException.class, CollegeNotFoundException.class, FileNotFoundException.class})
    public ResponseEntity<ErrorResponse> notFound(HttpServletResponse response, Exception e) {
        log.error(e.getMessage(), e);
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception exception) {
        log.error("Unknown error occurred", exception);

        return buildErrorResponse("Unknown error occurred", INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus httpStatus) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMsg(message)
                .status(httpStatus.value())
                .build();
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
