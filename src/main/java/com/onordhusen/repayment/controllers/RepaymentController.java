package com.onordhusen.repayment.controllers;

import com.onordhusen.repayment.constants.RepaymentValidationConstants;
import com.onordhusen.repayment.dtos.RepaymentDto;
import com.onordhusen.repayment.services.interfaces.RepaymentService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.onordhusen.repayment.constants.RepaymentValidationConstants.*;

/**
 * Die Eingangsklasse für alle Requests bezüglich Tilgungen.
 * Außerdem werden hier Servicefehler aufgefangen und entsprechende Rückmeldungen definiert.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/repayment")
@CrossOrigin(origins = "${cors.allowed.origin}", maxAge = 3600)
public class RepaymentController {

    private final Logger logger;

    private final RepaymentService repaymentService;

    /**
     * Der Logger und der Service für die Businesslogik wird hier initialisiert.
     *
     * @param repaymentService Service der die Businesslogik enthält.
     *
     * @since 1.0.0
     */
    public RepaymentController(final RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
        this.logger = LoggerFactory.getLogger(RepaymentController.class);
    }

    /**
     * Berechnet einen Tilgungsplan.
     * Alle Parameter sind Pflichtparamter und werden validiert.
     * @see RepaymentValidationConstants beinhaltet alle Validierungskonstanten.
     *
     * @param loanAmount Der Darlehensbetrag als double. In Form zb. EURO.CENT.
     * @param initialRepayment Die anfängliche Tilgung als double. In Form zb. 3.6 = 3,6%.
     * @param interestRate Der Sollzinssatz als double. In Form zb. 3.6 = 3,6%.
     * @param fixedInterestPeriod Die Dauer der Sollzinsbindung als int. In Form zb. 10 = 10 Jahre.
     * @return HTTP Response + Body.
     *
     * @since 1.0.0
     */
    @GetMapping
    public ResponseEntity<RepaymentDto> calculate(
            @RequestParam
            @Min(VALIDATION_MIN_LOAN_AMOUNT)
            @Max(VALIDATION_MAX_LOAN_AMOUNT)
            final double loanAmount,

            @RequestParam
            @Min(VALIDATION_MIN_INITIAL_REPAYMENT)
            @Max(VALIDATION_MAX_INITIAL_REPAYMENT)
            final double initialRepayment,

            @RequestParam
            @Min(VALIDATION_MIN_INTEREST_RATE)
            @Max(VALIDATION_MAX_INTEREST_RATE)
            final double interestRate,

            @RequestParam
            @Min(VALIDATION_MIN_FIXED_INTEREST_PERIOD)
            @Max(VALIDATION_MAX_FIXED_INTEREST_PERIOD)
            final int fixedInterestPeriod
    ) {

        try{
            return new ResponseEntity<>(
                repaymentService.calculate(
                    loanAmount,
                    initialRepayment,
                    interestRate,
                    fixedInterestPeriod
                ),
                HttpStatus.OK
            );
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}