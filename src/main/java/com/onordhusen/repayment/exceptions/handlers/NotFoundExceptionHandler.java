package com.onordhusen.repayment.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;

/**
 * Fängt alle nicht gefundenen Seiten Fehler ab und loggt diese.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class NotFoundExceptionHandler {

    private final Logger logger;

    public NotFoundExceptionHandler() {
        this.logger = LoggerFactory.getLogger(NotFoundExceptionHandler.class);
    }

    /**
     * Fängt NoResourceFoundException ab und gibt eine custom response zurück.
     *
     * @param exception NoResourceFoundException
     * @return Die Fehlermeldung als message.
     *
     * @since 1.0.0
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<HashMap<String, String>> handleNoHandlerFound(NoResourceFoundException exception) {
        logger.warn(exception.getMessage());
        HashMap<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
