package com.onordhusen.repayment.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fängt Validierungsfehler ab und loggt diese.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    private final Logger logger;

    public ValidationExceptionHandler() {
        this.logger = LoggerFactory.getLogger(ValidationExceptionHandler.class);
    }

    /**
     * Fängt HandlerMethodValidationException ab und gibt eine custom response zurück.
     *
     * @param validationException HandlerMethodValidationException
     * @return Alle fehlerhaften fields und deren Validierungsfehler als message.
     *
     * @since
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationExceptions(final HandlerMethodValidationException validationException) {
        Map<String, List<String>> errorMessages = new HashMap<>();
        List<String> errors = new ArrayList<>();
        List<? extends MessageSourceResolvable> validationErrors = validationException.getAllErrors();

        for (MessageSourceResolvable validationError: validationErrors) {
            final String errorMessage = validationError.getDefaultMessage();
            String field = "<UNKNOWN_FIELD>";
            try {
                for (Object errorArgument: validationError.getArguments()) {
                    DefaultMessageSourceResolvable je = (DefaultMessageSourceResolvable) errorArgument;
                    field = je.getDefaultMessage();
                }
            }
            catch (Exception ignored){
                ;
            }
            final String msg = field + ' ' + errorMessage;
            logger.warn(msg);
            errors.add(msg);
        }
        errorMessages.put("errors", errors);
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

}
