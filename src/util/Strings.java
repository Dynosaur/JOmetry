package util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * A utility class for miscellaneous {@code String} methods.
 *
 * @author  Alejandro Doberenz
 * @since   9/19/2019
 * @version 1.2
 */
public class Strings {

    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static String[] toStringArray(String string) {
        char[] chars = string.toCharArray();
        String[] strings = new String[chars.length];
        for(int i = 0; i < chars.length; i++)
            strings[i] = String.valueOf(chars[i]);
        return strings;
    }

    public static String intToWordString(long n) {
        double accuracy = 0.00001d;
        if(n >= 100) {
            if(n >= 1000000000000000000L) return bigWordRecurse(n, 1000000000000000000L, "quintillion", accuracy);
            if(n >= 1000000000000000L) return bigWordRecurse(n, 1000000000000000L, "quadrillion", accuracy);
            if(n >= 1000000000000L) return bigWordRecurse(n, 1000000000000L, "trillion", accuracy);
            if(n >= 1000000000) return bigWordRecurse(n, 1000000000, "billion", accuracy);
            if(n >= 1000000) return bigWordRecurse(n, 1000000, "million", accuracy);
            if(n >= 1000) return bigWordRecurse(n, 1000, "thousand", accuracy);
            if(n >= 100) return bigWordRecurse(n, 100, "hundred", accuracy);
        }
        if(n < 100 && n >= 20) {
            if(n >= 90) return wordRecurse(n, 90, "ninety");
            if(n >= 80) return wordRecurse(n, 80, "eighty");
            if(n >= 70) return wordRecurse(n, 70, "seventy");
            if(n >= 60) return wordRecurse(n, 60, "sixty");
            if(n >= 50) return wordRecurse(n, 50, "fifty");
            if(n >= 40) return wordRecurse(n, 40, "forty");
            if(n >= 30) return wordRecurse(n, 30, "thirty");
            if(n >= 20) return wordRecurse(n, 20, "twenty");
        } else if(n < 20 && n >= 10) {
            if(n == 19) return "nineteen";
            if(n == 18) return "eighteen";
            if(n == 17) return "seventeen";
            if(n == 16) return "sixteen";
            if(n == 15) return "fifteen";
            if(n == 14) return "fourteen";
            if(n == 13) return "thirteen";
            if(n == 12) return "twelve";
            if(n == 11) return "eleven";
            if(n == 10) return "ten";
        } else if(n < 10 && n >= 0) {
            if(n == 9) return "nine";
            if(n == 8) return "eight";
            if(n == 7) return "seven";
            if(n == 6) return "six";
            if(n == 5) return "five";
            if(n == 4) return "four";
            if(n == 3) return "three";
            if(n == 2) return "two";
            if(n == 1) return "one";
            if(n == 0) return "zero";
        } else if(n < 0) return "negative " + intToWordString(-n);
        throw new IllegalArgumentException(n + " could not be converted to a number!");
    }

    private static String wordRecurse(long number, long testNumber, String stringResult) {
        return stringResult + ((number - testNumber == 0) ? "" : " " + intToWordString(number - testNumber));
    }

    private static String bigWordRecurse(long number, long testNumber, String stringResult, double accuracy) {
        long divisions = (long) Math.floor(number / testNumber);
        long remainder = number - divisions * testNumber;
        return intToWordString(divisions) + " " + stringResult + ((remainder < accuracy) ? "" :  " and " + intToWordString(remainder));
    }

    public static String floatToWordString(double n) {
        BigDecimal big = new BigDecimal(n);
        long integerPart = big.longValue();
        BigDecimal floatPart = big.subtract(new BigDecimal(integerPart));

        String numbers = String.valueOf(floatPart.round(new MathContext(8)).doubleValue()).substring(2);
        StringBuilder builder = new StringBuilder();
        builder.append(intToWordString(integerPart));
        builder.append(" point ");
        for(int i = 0; i < numbers.length(); i++) {
            builder.append(intToWordString(Long.parseLong(String.valueOf(numbers.charAt(i)))));
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    public static String numberToWord(double n) {
        if(n - (long) n < 0.000001) return intToWordString((long) n);
        else return floatToWordString(n);
    }

}
