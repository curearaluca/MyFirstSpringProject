package com.myspringproject.university.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> badRequest(HttpServletResponse response, Exception ex, BindingResult bindingResult) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(createErrorResponseForPathAndBodyValidationExceptions(ex, bindingResult));
    }

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

    private ErrorResponse createErrorResponseForPathAndBodyValidationExceptions(Exception e, BindingResult bindingResult) {
        var errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMsg(e.getMessage())
                .build();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorResponse.getErrors().add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        return errorResponse;
    }
}
