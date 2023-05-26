package com.cms.demo.exceptions;

import com.cms.demo.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionException;

@EnableWebMvc
@RestControllerAdvice
@Slf4j
public class RestExceptionResolver {

    private static final String LOG_MESSAGE = "message - {}  error - {}";


    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Response<Void>> handleApiException(IllegalArgumentException e) {
        log.warn(LOG_MESSAGE, e.getMessage(), e);
        return new ResponseEntity<>(new Response<>(false, "400", e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = MethodNotAllowedException.class)
    public ResponseEntity<Response<Void>> handleMethodNotAllowedExceptionException(MethodNotAllowedException e) {
        log.warn("Method Not Allowed Exception {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new Response<>(false, String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), "Illegal Method Called"), HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(value = CompletionException.class)
    public ResponseEntity<Response<Void>> handleCompletionException(CompletionException e) {
        log.warn("Completion Exception {}", e.getMessage(), e);
        if (e.getMessage().contains("MethodNotAllowedException")) {
            return new ResponseEntity<>(
                    new Response<>(false, String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), "Illegal Method Called"), HttpStatus.METHOD_NOT_ALLOWED);
        }
        log.error("Completion Exception {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new Response<>(false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    protected ResponseEntity<Response<Void>> validatorException(MissingServletRequestParameterException e) {
        log.warn("Missing Servlet Request Parameter Exception {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new Response<>(false, String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<Response<Void>> validatorException(MethodArgumentNotValidException e) {
        log.warn("Method Argument Not Valid Exception {}", e.getBindingResult(), e);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new Response<>(false, "400", String.join(",", errors.values())),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ResponseEntity<Response<Void>> internalServerErrorHandler(HttpMessageNotReadableException e) {
        log.warn("Http Message Not Readable Exception {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new Response<>(false, String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage().substring(0, e.getMessage().indexOf(':'))), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    protected ResponseEntity<Response<Void>> exceptionHandler(DataIntegrityViolationException e) {
        log.warn("Data Integrity Violation Exception {}", e.getMessage(), e);
        String message = e.getMessage();
        if (Optional.ofNullable(e.getMessage()).orElse("").contains("duplicate")) {
            message = "Duplicate";
        }
        return new ResponseEntity<>(
                new Response<>(false, String.valueOf(HttpStatus.BAD_REQUEST.value()), message), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Response<Void>> constraintViolationException(ConstraintViolationException e) {
        log.warn("Bad Request Exception {}", e.getMessage(), e);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(e.getStackTrace()).limit(5).forEach(sb::append);
        return new ResponseEntity<>(
                new Response<>(false, e.getMessage(), sb.toString()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Response<Object>> handleUnknownException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new Response<>(false, "500", "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
