package com.estate.exception.handler;


import com.estate.exception.FileNotFoundException;
import com.estate.exception.FileStorageException;
import com.estate.exception.InternalException;
import com.estate.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestControllerAdvice
@EnableWebMvc
public class CustomExceptionHandler {


//    @ExceptionHandler({ ConstraintViolationException.class })
//    public ResponseEntity<Object> handleConstraintViolation(
//            ConstraintViolationException ex) {
//        List<String> errors = new ArrayList<>();
//        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            errors.add(violation.getRootBeanClass().getName() + " " +
//                    violation.getPropertyPath() + ": " + violation.getMessage());
//        }
//        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, errors);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ErrorMessage> handleNoHandlerFoundException(
            NoHandlerFoundException ex) {
        Logger logger = LoggerFactory.getLogger(NoHandlerFoundException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }


    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(Exception ex) {
        Logger logger = LoggerFactory.getLogger(AuthenticationException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(Exception ex) {
        Logger logger = LoggerFactory.getLogger(AccessDeniedException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        Logger logger = LoggerFactory.getLogger(NotFoundException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Logger logger = LoggerFactory.getLogger(MethodArgumentTypeMismatchException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex) {
        Logger logger = LoggerFactory.getLogger(HttpRequestMethodNotSupportedException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorMessage);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        Logger logger = LoggerFactory.getLogger(HttpMediaTypeNotSupportedException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorMessage);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleFileNotFoundException(FileNotFoundException ex) {
        Logger logger = LoggerFactory.getLogger(FileNotFoundException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorMessage> handleFileStorageException(FileStorageException ex) {
        Logger logger = LoggerFactory.getLogger(FileStorageException.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler({Exception.class, InternalException.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        Logger logger = LoggerFactory.getLogger(Exception.class);
        logger.error("Runtime error: {}", ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

    }

}
