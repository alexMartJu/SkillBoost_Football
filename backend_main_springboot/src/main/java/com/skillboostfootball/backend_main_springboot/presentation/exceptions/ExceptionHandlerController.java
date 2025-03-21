package com.skillboostfootball.backend_main_springboot.presentation.exceptions;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.*;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.auth.TokenExpiredException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.subtiposTecnificacion.SubtipoTecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones.TecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.pistas.PistaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.*;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.suscripciones.SuscripcionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.NoAvailableTrainingsException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.roles.RoleNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.EmailTakenException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.UsuarioNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.reservas.*;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.graficas.GraficaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.evaluaciones.EvaluacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.organizaciones.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
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

    @ExceptionHandler(SubtipoTecnificacionNotFoundException.class)
    public ResponseEntity<?> handleSubtipoTecnificacionNotFound(SubtipoTecnificacionNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "SubtipoTecnificacion");
    }

    @ExceptionHandler(TecnificacionNotFoundException.class)
    public ResponseEntity<?> handleTecnificacionNotFound(TecnificacionNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Tecnificacion");
    }

    @ExceptionHandler(PistaNotFoundException.class)
    public ResponseEntity<?> handlePistaNotFound(PistaNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Pista");
    }

    @ExceptionHandler(EntrenamientoNotFoundException.class)
    public ResponseEntity<?> handleEntrenamientoNotFound(EntrenamientoNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Entrenamiento");
    }

    @ExceptionHandler(SuscripcionNotFoundException.class)
    public ResponseEntity<?> handleSuscripcionNotFound(SuscripcionNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Suscripcion");
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<?> handleProfileNotFound(ProfileNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Profile");
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleRoleNotFound(RoleNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Role");
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<?> handleUsuarioNotFound(UsuarioNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Usuario");
    }

    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<?> handleReservaNotFound(ReservaNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Reserva");
    }

    @ExceptionHandler(GraficaNotFoundException.class)
    public ResponseEntity<?> handleGraficaNotFound(GraficaNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Grafica");
    }

    @ExceptionHandler(EvaluacionNotFoundException.class)
    public ResponseEntity<?> handleEvaluacionNotFound(EvaluacionNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Evaluacion");
    }

    @ExceptionHandler(OrganizacionNotFoundException.class)
    public ResponseEntity<?> handleOrganizacionNotFound(OrganizacionNotFoundException ex, WebRequest request) {
        return handleResourceNotFound(ex, request, "Organizacion");
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<?> handleEmailTaken(EmailTakenException ex, WebRequest request) {
        return handleTaken(ex, request, "email");
    }

    @ExceptionHandler(OrganizacionNameTakenException.class)
    public ResponseEntity<?> handleOrganizacionTaken(OrganizacionNameTakenException ex, WebRequest request) {
        return handleTaken(ex, request, "organizacionName");    
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<?> handleTokenExpired(TokenExpiredException ex, WebRequest request) {
        var status = HttpStatus.UNAUTHORIZED; // 401
        var error = createErrorBuilder(ex.getMessage()).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({AccessDeniedException.class, AuthorizationDeniedException.class})
    public ResponseEntity<?> handleAccessDenied(Exception ex, WebRequest request) {
        var status = HttpStatus.FORBIDDEN;
        
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", "error");
        errorDetails.put("code", "access_denied");
        errorDetails.put("message", "No tienes permisos para acceder a este recurso");
        errorDetails.put("path", request.getDescription(false).replace("uri=", ""));
        
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var error = createErrorBuilder(ex.getMessage()).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntrenamientoInvalidStateException.class)
    public ResponseEntity<?> handleEntrenamientoInvalidState(EntrenamientoInvalidStateException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = createErrorBuilder(ex.getMessage()).build();
        
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntrenamientoFullException.class)
    public ResponseEntity<?> handleEntrenamientoFull(EntrenamientoFullException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = createErrorBuilder(ex.getMessage()).build();
        
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(AgeNotAllowedException.class)
    public ResponseEntity<?> handleAgeNotAllowed(AgeNotAllowedException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = createErrorBuilder(ex.getMessage()).build();
        
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NoAvailableTrainingsException.class)
    public ResponseEntity<?> handleNoAvailableTrainings(NoAvailableTrainingsException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = createErrorBuilder(ex.getMessage()).build();
        
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ReservaAlreadyExistsException.class)
    public ResponseEntity<?> handleReservaAlreadyExists(ReservaAlreadyExistsException ex, WebRequest request) {
        var status = HttpStatus.CONFLICT;
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
