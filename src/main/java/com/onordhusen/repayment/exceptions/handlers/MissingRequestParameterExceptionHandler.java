package com.onordhusen.repayment.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * Fängt alle fehlenden Parameter Fehler ab und loggt diese.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class MissingRequestParameterExceptionHandler {

    private final Logger logger;

    public MissingRequestParameterExceptionHandler() {
        this.logger = LoggerFactory.getLogger(MissingRequestParameterExceptionHandler.class);
    }


    /**
     * Fängt MissingServletRequestParameterException ab und gibt eine custom response zurück.
     *
     * @param exception MissingServletRequestParameterException
     * @return Die Fehlermeldung als message.
     *
     * @since 1.0.0
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<HashMap<String, String>> handleNoHandlerFound(MissingServletRequestParameterException exception) {
        logger.warn(exception.getMessage());
        HashMap<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
