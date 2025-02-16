package com.onordhusen.repayment.dtos;

import java.util.List;

/**
 * Tilgungsplan als Datensatz.
 *
 * @param loanAmount Der Darlehensbetrag. In Form zb. EURO.CENT.
 * @param totalInterest Der Gesamtzinssatz. In Form zb. EURO.CENT.
 * @param monthlyRate Die monatliche Rate. In Form zb. EURO.CENT.
 * @param initialRepaymentRate Die anfängliche Tilgung. In Form zb. 3.6 = 3,6 %.
 * @param interestRate Der Sollzinssatz. In Form zb. 3.6 = 3,6 %
 * @param fixedInterestPeriod Die Dauer der Sollzinsbindung. In Form zb. 10 = 10 Jahre.
 * @param residualDebt Die Restschuld. In Form zb. EURO.CENT.
 * @param yearPeriod Die Jahre der Dauer des Kredits. In Form zb. 10 = 10 Jahre.
 * @param monthPeriod Die Monate der Dauer des Kredits. In Form zb. 5 = 5 Monate.
 * @param totalRepayment Der Gesamtbetrag. In Form zb. EURO.CENT.
 * @param repaymentPlan Der Tilgungsplan als Liste von jährlicher Aufgliederung. Siehe {@link RepaymentPlanDto}.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */

public record RepaymentDto(
    double loanAmount,
    double totalInterest,
    double monthlyRate,
    double initialRepaymentRate,
    double interestRate,
    int fixedInterestPeriod,
    double residualDebt,
    int yearPeriod,
    int monthPeriod,
    double totalRepayment,
    List<RepaymentPlanDto> repaymentPlan
)
{}
