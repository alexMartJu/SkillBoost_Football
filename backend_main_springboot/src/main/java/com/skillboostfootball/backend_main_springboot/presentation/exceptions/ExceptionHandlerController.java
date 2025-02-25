package com.skillboostfootball.backend_main_springboot.presentation.exceptions;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    
    private static final String GENERIC_ERROR_MESSAGE = "Oops! Algo ha salido mal.";

    private Error.ErrorBuilder createErrorBuilder(Map<String, Object> errors) {
        return Error.builder()
                .errors(errors);
    }

    private Error.ErrorBuilder createErrorBuilder(String message) {
        return Error.builder()
                .status("error")
                .message(message);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {
        if (body == null || body instanceof String) {
            body = createErrorBuilder(GENERIC_ERROR_MESSAGE)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request, String name) {
        var status = HttpStatus.NOT_FOUND;
        var error = createErrorBuilder(toMap(name, ex.getMessage())).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    private ResponseEntity<?> handleTaken(TakenException ex, WebRequest request, String fieldName) {
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var message = ex.getMessage();

        var error = createErrorBuilder(toMap(fieldName, message)).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var error = createErrorBuilder(ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var message = ex.getMessage();

        var error = createErrorBuilder(message).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var status = HttpStatus.valueOf(statusCode.value());

        var map = new HashMap<String, Object>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), toList(fieldError.getDefaultMessage()));
        });

        var error = createErrorBuilder(map).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }
    
    @ExceptionHandler(Exception.class)  // Captura cualquier excepción no manejada previamente
    public ResponseEntity<?> handleUnexpectedError(Exception ex, WebRequest request) {
        // Logueamos el error inesperado para tener un registro
        log.error("Unexpected error occurred: ", ex);

        // Creamos una respuesta genérica para el cliente
        var status = HttpStatus.INTERNAL_SERVER_ERROR; // 500 Internal Server Error
        var error = createErrorBuilder(GENERIC_ERROR_MESSAGE).build();

        // Retornamos la respuesta con el mensaje de error y el código 500
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    private List<String> toList(String message) {
        var messageList = new ArrayList<String>();
        messageList.add(message);
        return messageList;
    }

    private Map<String, Object> toMap(String field, String message) {
        var map = new HashMap<String, Object>();
        map.put(field, toList(message));
        return map;
    }

}
