package com.onordhusen.repayment.utils;

/**
 * Stellt nützliche Double Funktionalitäten bereit.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
public class DoubleUtils
{

    /**
     * Bietet die Möglichkeit doubles zu runden.
     *
     * @param value Die Zahl, die gerundet werden soll.
     * @param decimals Auf wie viele Nachkommastellen gerundet werden soll.
     * @return Die gerundete Zahl.
     *
     * @since 1.0.0
     */
    public static double round(final double value, final int decimals) {
        final int exponent = (int) Math.pow(10, decimals);
        return (double) Math.round(value * exponent) / exponent;
    }

    /**
     * Bietet die Möglichkeit doubles nach dem Währungsschema zu runden.
     *
     * @param value Die Zahl, die gerundet werden soll.
     * @return Die gerundete Zahl.
     *
     * @since 1.0.0
     */
    public static double roundPrice(final double value) {
        return round(value, 2);
    }

}
