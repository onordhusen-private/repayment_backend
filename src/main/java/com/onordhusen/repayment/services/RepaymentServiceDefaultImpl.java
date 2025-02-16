package com.onordhusen.repayment.services;

import com.onordhusen.repayment.constants.RepaymentValidationConstants;
import com.onordhusen.repayment.services.interfaces.RepaymentService;
import com.onordhusen.repayment.dtos.RepaymentDto;
import com.onordhusen.repayment.dtos.RepaymentPlanDto;
import com.onordhusen.repayment.utils.DoubleUtils;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;

import static com.onordhusen.repayment.constants.DateConstants.MONTHS_PER_YEAR;
import static com.onordhusen.repayment.constants.RepaymentValidationConstants.*;

/**
 * Die Standard Implementierung der Businesslogik des Tilgungsplans.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class RepaymentServiceDefaultImpl implements RepaymentService {

    /**
     * Berechnet einen Tilgungsplan.
     * Alle Parameter werden validiert. Somit kann die Methode außerhalb des Kontexts verwendet werden.
     * @see RepaymentValidationConstants beinhaltet alle Validierungskonstanten.
     *
     * @param loanAmount Der Darlehensbetrag als double. In Form zb. EURO.CENT.
     * @param initialRepayment Die anfängliche Tilgung als double. In Form zb. 3.6 = 3,6%.
     * @param interestRate Der Sollzinssatz als double. In Form zb. 3.6 = 3,6%.
     * @param fixedInterestPeriod Die Dauer der Sollzinsbindung als int. In Form zb. 10 = 10 Jahre.
     * @return RepaymentDto Vollständig berechneter Tilgungsplan.
     *
     * @since 1.0.0
     */
    public RepaymentDto calculate(
            @Min(VALIDATION_MIN_LOAN_AMOUNT)
            @Max(VALIDATION_MAX_LOAN_AMOUNT)
            final double loanAmount,

            @Min(VALIDATION_MIN_INITIAL_REPAYMENT)
            @Max(VALIDATION_MAX_INITIAL_REPAYMENT)
            final double initialRepayment,

            @Min(VALIDATION_MIN_INTEREST_RATE)
            @Max(VALIDATION_MAX_INTEREST_RATE)
            final double interestRate,

            @Min(VALIDATION_MIN_FIXED_INTEREST_PERIOD)
            @Max(VALIDATION_MAX_FIXED_INTEREST_PERIOD)
            final int fixedInterestPeriod
    ) {

        // Im folgenden verwenden die Formeln dezimale Prozente.
        final double internalInitialRepayment = initialRepayment / 100;
        final double internalInterestRate = interestRate / 100;

        // Berechnung des monatlichen Sollzinsatzes.
        final double monthlyInterestRate = internalInterestRate / MONTHS_PER_YEAR;

        // Berechnung der monatlichen Rate.
        double monthlyRate = loanAmount * ((internalInterestRate + internalInitialRepayment) / MONTHS_PER_YEAR);
        monthlyRate = DoubleUtils.roundPrice(monthlyRate);

        // Initialisierung des Tilgungsplanes.
        final List<RepaymentPlanDto> repaymentPlans = new ArrayList<>();
        double residualDebt = loanAmount;
        double residualDebtEnd = 0;
        double totalInterest = 0;
        double totalRepayment = 0;
        int yearPeriod = -1;
        int monthPeriod = 0;

        // Jedes Jahr einzeln berechnen, bis die Restschuld 0,00 € ist.
        do {
            // Jahresplan initialisieren, bzw. zurücksetzen.
            ++yearPeriod;
            double yearlyInterestPortion = 0;
            double yearlyRepaymentPortion = 0;
            double yearlyRate = 0;

            // Jeden Monat einzeln berechnen, da monatlich abgerechnet wird.
            // So kann gewährleistet werden, dass die Jahreswerte auf Grund von Cent-Rundungen nicht abweichen.
            for (monthPeriod = 1; monthPeriod <= MONTHS_PER_YEAR; ++monthPeriod) {

                // Berechnung der Jährlichen Rate.
                yearlyRate += monthlyRate;
                yearlyRate = DoubleUtils.roundPrice(yearlyRate);

                // Berechnung des monatlichen Zinsanteils.
                double monthlyInterestPortion = residualDebt * monthlyInterestRate;
                monthlyInterestPortion = DoubleUtils.roundPrice(monthlyInterestPortion);

                // Berechnung des jährlichen Zinsanteils.
                yearlyInterestPortion += monthlyInterestPortion;
                yearlyInterestPortion = DoubleUtils.roundPrice(yearlyInterestPortion);

                // Berechnung der Zinsen der gesamten Laufzeit.
                totalInterest += monthlyInterestPortion;
                totalInterest = DoubleUtils.roundPrice(totalInterest);

                // Berechnung des monatlichen Tilgungsanteils.
                double monthlyRepaymentPortion = Math.min(monthlyRate - monthlyInterestPortion, residualDebt);
                monthlyRepaymentPortion = DoubleUtils.roundPrice(monthlyRepaymentPortion);

                // Berechnung des jährlichen Tilgungsanteils.
                yearlyRepaymentPortion += monthlyRepaymentPortion;
                yearlyRepaymentPortion = DoubleUtils.roundPrice(yearlyRepaymentPortion);

                // Berechnung der Restschuld des Monats.
                residualDebt -= monthlyRepaymentPortion;
                residualDebt = DoubleUtils.roundPrice(residualDebt);

                // Feststellen, ob der Tilgungsplan fertig berechnet wurde.
                if (residualDebt <= 0.0d) {
                    break;
                }
            }

            // Ggf. Berechnung der Restschuld nach Ablauf der Sollzinsbindung
            if (yearPeriod == fixedInterestPeriod - 1) {
                residualDebtEnd = residualDebt;
            }

            // Fertig berechnetes Jahr dem Tilgungsplan hinzufügen.
            RepaymentPlanDto repaymentPlan = new RepaymentPlanDto(
                yearPeriod + 1,
                yearlyRate,
                yearlyInterestPortion,
                yearlyRepaymentPortion,
                residualDebt
            );
            repaymentPlans.add(repaymentPlan);

        } while(residualDebt > 0);

        // Berechnung des Gesamtbetrags
        totalRepayment = totalInterest + loanAmount;
        totalRepayment = DoubleUtils.roundPrice(totalRepayment);

        // Korrektur, damit die Monate bei Abschluss nicht 12 sein können.
        if (monthPeriod == MONTHS_PER_YEAR) {
            monthPeriod = 0;
            ++yearPeriod;
        }

        // Rückgabeobjekt bauen
        return new RepaymentDto(
            loanAmount,
            totalInterest,
            monthlyRate,
            initialRepayment,
            interestRate,
            fixedInterestPeriod,
            residualDebtEnd,
            yearPeriod,
            monthPeriod,
            totalRepayment,
            repaymentPlans
        );
    }

}
