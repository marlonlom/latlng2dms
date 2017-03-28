package com.github.marlonlom.helpers.coordinates;

/**
 * The Class DecimalToDms.
 *
 * @author Mjlopezm
 */
public final class DecimalToDms {

    /**
     * Formats coordinate value in D°M′S″ format.
     *
     * @param coord coordinate in decimal format
     * @return coordinate in D°M′S″ format
     */
    public static String format(final double coord) {

        double coordinate = coord;

        double mod = coordinate % Constants.ONE;
        int intPart = (int) coordinate;

        final String degrees = String.valueOf(intPart);

        coordinate = mod * Constants.SIXTY;
        mod = coordinate % Constants.ONE;
        intPart = (int) coordinate;
        if (intPart < Constants.ZERO) {
            intPart *= Constants.ONE_NEGATIVE;
        }

        final String minutes = String.valueOf(intPart);

        coordinate = mod * Constants.SIXTY;
        intPart = (int) coordinate;
        if (intPart < Constants.ZERO) {
            intPart *= Constants.ONE_NEGATIVE;
        }

        String seconds = String.valueOf(intPart);
        return String.format(Constants.DMS_FORMAT, Math.abs(Integer.parseInt(degrees)), minutes,
                seconds);
    }

}
