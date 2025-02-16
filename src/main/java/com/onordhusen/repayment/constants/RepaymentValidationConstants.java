package com.onordhusen.repayment.constants;

/**
 * Validierungskonstanten im Bezug zum Tilgungsplan.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
public class RepaymentValidationConstants {

    /**
     * Erlaubter Minimum Darlehensbetrag. In Form von Währung. Zb. 10000 = 10.000,00 €
     *
     * @since 1.0.0
     */
    public static final long VALIDATION_MIN_LOAN_AMOUNT = 10_000;

    /**
     * Erlaubter Maximum Darlehensbetrag. In Form von Währung. Zb. 10000 = 10.000,00 €
     *
     * @since 1.0.0
     */
    public static final long VALIDATION_MAX_LOAN_AMOUNT = 10_000_000;

    /**
     * Erlaubte Minimum anfängliche Tilgung. In Form von Prozent. Zb. 10 = 10 %
     *
     * @since 1.0.0
     */
    public static final long VALIDATION_MIN_INITIAL_REPAYMENT = 1;

    /**
     * Erlaubte Maximum anfängliche Tilgung. In Form von Prozent. Zb. 10 = 10 %
     *
     * @since 1.0.0
     */
    public static final long VALIDATION_MAX_INITIAL_REPAYMENT = 15;

    /**
     * Erlaubter Minimum Sollzinssatz. In Form von Prozent. Zb. 10 = 10 %
     *
     * @since 1.0.0
     */
    public static final long VALIDATION_MIN_INTEREST_RATE = 1;

    /**
     * Erlaubter Maximum Sollzinssatz. In Form von Prozent. Zb. 10 = 10 %
     *
     * @since 1.0.0
     */
    public static final long VALIDATION_MAX_INTEREST_RATE = 100;

    /**
     * Erlaubte Minimum Dauer der Sollzinsbindung. In Form von Jahren. Zb. 10 = 10 Jahre
     *
     * @since 1.0.0
     */
    public static final long VALIDATION_MIN_FIXED_INTEREST_PERIOD = 1;

    /**
     * Erlaubte Maximum Dauer der Sollzinsbindung. In Form von Jahren. Zb. 10 = 10 Jahre
     *
     * @since 1.0.0
     */
    public static final long VALIDATION_MAX_FIXED_INTEREST_PERIOD = 30;

}
