package com.onordhusen.repayment.dtos;

/**
 * Jährliche Aufgliederung des Tilgungsplans.
 *
 * @param year Das Jahr des Datensatzes. In Form zb. 1 = 1 Jahr
 * @param rate Die Rate des Jahrs. In Form zb. EURO.CENT.
 * @param interestPortion Der Zinsanteil des Jahrs. In Form zb. EURO.CENT.
 * @param repaymentPortion Der Tilgungsanteil des Jahrs. In Form zb. EURO.CENT.
 * @param residualDebt Die Restschuld am Ende des Jahrs. In Form zb. EURO CENT.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
public record RepaymentPlanDto(
    int year,
    double rate,
    double interestPortion,
    double repaymentPortion,
    double residualDebt
)
{}
