package symbol.constant;

import symbol.Symbol;

/**
 * @author  Alejandro Doberenz
 * @version 1.1.5
 * @since   9/14/2019
 *
 * Represents a mathematical number.
 */
public abstract class Constant extends symbol.Symbol {

    public static boolean isConstant(Object obj) {
        if(obj instanceof Constant) return true;
        else if(obj instanceof Number) return true;
        else if(obj instanceof String || obj instanceof Character || obj instanceof Symbol) {
            if(obj instanceof Character) obj = String.valueOf(obj);
            if(obj instanceof Symbol) obj = ((Symbol) obj).getSymbol();
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch(NumberFormatException e) {
                return false;
            }
        } else return false;
    }

    Number constantValue;



    public Constant(Number number) {
        super(String.valueOf(number));
        constantValue = number;
    }

    public Number getValue() { return constantValue; }

    public static String toWordString(double value) {
        int billion = 1000000000;
        int million = 1000000;
        double accuracy = 0.0000009;
        if(value / billion >= 1) {
            return toWordString(Math.floor(value / billion)) + " billion" +
                    ((Math.abs(value - Math.floor(value / billion) * billion) < accuracy)
                            ? ""
                            : " " + toWordString(value - Math.floor(value/billion) * billion));
        }
        if(value / million >= 1) {
            return toWordString(Math.floor(value / million)) + " million" +
                    ((Math.abs(value - Math.floor(value / million) * million) < accuracy)
                            ? ""
                            : " " + toWordString(value - Math.floor(value/million) * million));
        }
        if(value / 1000 >= 1) {
            return toWordString(Math.floor(value / 1000)) + " thousand" +
                    ((Math.abs(value - Math.floor(value / 1000) * 1000) < accuracy)
                            ? ""
                            : " " + toWordString(value - Math.floor(value/1000) * 1000));
        }
        if(value / 100 >= 1) {
            return toWordString(Math.floor(value / 100)) + " hundred" +
                    ((Math.abs(value - Math.floor(value / 100) * 100) < accuracy)
                            ? ""
                            : " " + toWordString(value - Math.floor(value/100) * 100));
        }
        if(value / 90 >= 1) return "ninety" + ((value - 90 < accuracy) ? "" : "-" + toWordString(value - 90));
        if(value / 80 >= 1) return "eighty" + ((value - 80 < accuracy) ? "" : "-" + toWordString(value - 80));
        if(value / 70 >= 1) return "seventy" + ((value - 70 < accuracy) ? "" : "-" + toWordString(value - 70));
        if(value / 60 >= 1) return "sixty" + ((value - 60 < accuracy) ? "" : "-" + toWordString(value - 60));
        if(value / 50 >= 1) return "fifty" + ((value - 50 < accuracy) ? "" : "-" + toWordString(value - 50));
        if(value / 40 >= 1) return "forty" + ((value - 40 < accuracy) ? "" : "-" + toWordString(value - 40));
        if(value / 30 >= 1) return "thirty" + ((value - 30 < accuracy) ? "" : "-" + toWordString(value - 30));
        if(value / 20 >= 1) return "twenty" + ((value - 20 < accuracy) ? "" : "-" + toWordString(value - 20));
        if(value / 19 >= 1) return "nineteen" + ((value - 19 < accuracy) ? "" : " point " + toWordString(value - 19));
        if(value / 18 >= 1) return "eighteen" + ((value - 18 < accuracy) ? "" : " point " + toWordString(value - 18));
        if(value / 17 >= 1) return "seventeen" + ((value - 17 < accuracy) ? "" : " point " + toWordString(value - 17));
        if(value / 16 >= 1) return "sixteen" + ((value - 16 < accuracy) ? "" : " point " + toWordString(value - 16));
        if(value / 15 >= 1) return "fifteen" + ((value - 15 < accuracy) ? "" : " point " + toWordString(value - 15));
        if(value / 14 >= 1) return "fourteen" + ((value - 14 < accuracy) ? "" : " point " + toWordString(value - 14));
        if(value / 13 >= 1) return "thirteen" + ((value - 13 < accuracy) ? "" : " point " + toWordString(value - 13));
        if(value / 12 >= 1) return "twelve" + ((value - 12 < accuracy) ? "" : " point " + toWordString(value - 12));
        if(value / 11 >= 1) return "eleven" + ((value - 11 < accuracy) ? "" : " point " + toWordString(value - 11));
        if(value / 10 >= 1) return "ten" + ((value - 10 < accuracy) ? "" : " point " + toWordString(value - 10));
        if(value / 9 >= 1) return "nine" + ((value - 9 < accuracy) ? "" : " point " + toWordString(value - 9));
        if(value / 8 >= 1) return "eight" + ((value - 8 < accuracy) ? "" : " point " + toWordString(value - 8));
        if(value / 7 >= 1) return "seven" + ((value - 7 < accuracy) ? "" : " point " + toWordString(value - 7));
        if(value / 6 >= 1) return "six" + ((value - 6 < accuracy) ? "" : " point " + toWordString(value - 6));
        if(value / 5 >= 1) return "five" + ((value - 5 < accuracy) ? "" : " point " + toWordString(value - 5));
        if(value / 4 >= 1) return "four" + ((value - 4 < accuracy) ? "" : " point " + toWordString(value - 4));
        if(value / 3 >= 1) return "three" + ((value - 3 < accuracy) ? "" : " point " + toWordString(value - 3));
        if(value / 2 >= 1) return "two" + ((value - 2 < accuracy) ? "" : " point " + toWordString(value - 2));
        if(value / 1 >= 1) return "one" + ((value - 1 < accuracy) ? "" : " point " + toWordString(value - 1));
        if(value > 0 && value < 1) {
            String scale = null;
            return "decimal";
        }
        if(value == 0) return "zero";
        else throw new IllegalArgumentException(String.format("\'%f\' could not be formatted!", value));
    }

    public static void decimalToWordString(double value) {
        if(!(value > 0 && value < 1)) System.out.println("to normal");
        int i = 0;
        while(true) {
            if(value == (int) value) break;
            value *= 10;
            i++;
        }
        System.out.println("Iterations: " + i);
        System.out.println("Multiple: " + Math.pow(10, i));
        System.out.println(value);
    }

    @Override public String toString() {
        return toWordString();
    }

    public String toWordString() {
        return toWordString(constantValue.doubleValue());
    }

    public String toCommaString() {
        char[] cArray = symbolString.toCharArray();
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < cArray.length; i++) {
            if(i!= 0 && i % 3 == 0) string.append(',');
            string.append(cArray[cArray.length - 1 - i]);
        }
        return string.reverse().toString();
    }

}
