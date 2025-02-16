package com.onordhusen.repayment.services.interfaces;

import com.onordhusen.repayment.dtos.RepaymentDto;

/**
 * Enthält die Richtlinien für die Implementierung der Businesslogik des Tilgungsplans.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RepaymentService {

    /**
     * Berechnet einen Tilgungsplan.
     *
     * @param loanAmount Der Darlehensbetrag als double. In Form zb. EURO.CENT.
     * @param initialRepayment Die anfängliche Tilgung als double. In Form zb. 3.6 = 3,6%.
     * @param interestRate Der Sollzinssatz als double. In Form zb. 3.6 = 3,6%.
     * @param fixedInterestPeriod Die Dauer der Sollzinsbindung als int. In Form zb. 10 = 10 Jahre.
     * @return RepaymentDto Vollständig berechneter Tilgungsplan.
     *
     * @since 1.0.0
     */
    RepaymentDto calculate(
            final double loanAmount,
            final double initialRepayment,
            final double interestRate,
            final int fixedInterestPeriod
    );

}
