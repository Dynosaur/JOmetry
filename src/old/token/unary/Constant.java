package old.token.unary;

import old.token.Token;

public class Constant extends Unary {

    private double value;

    public Constant(String raw) {
        super(raw, Token.Type.CONSTANT);
        value = Double.parseDouble(raw);
    }

    @Override public String toString() {
        return spellOutValue(value);
    }

    public static boolean isConstant(String input) {

        try {
            Double.parseDouble(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }

    }

    public static boolean containsConstant(String input) {

        for(String element : input.split(""))
            if(isConstant(element)) return true;

        return false;

    }

    public static String spellOutValue(Double value) {
        if(value / 1000000 >= 1) {
            return spellOutValue(Math.floor(value / 1000000)) + " million" +
                    ((Math.abs(value - Math.floor(value / 1000000) * 1000000) < 1) ? "" : " and " + spellOutValue(value - Math.floor(value/1000000) * 1000000));
        }
        if(value / 1000 >= 1) {
            return spellOutValue(Math.floor(value / 1000)) + " thousand" +
                    ((Math.abs(value - Math.floor(value / 1000) * 1000) < 0.1) ? "" : " and " + spellOutValue(value - Math.floor(value/1000) * 1000));
        }
        if(value / 100 >= 1) {
            return spellOutValue(Math.floor(value / 100)) + " hundred" +
                ((Math.abs(value - Math.floor(value / 100) * 100) < 0.1) ? "" : " and " + spellOutValue(value - Math.floor(value/100) * 100));
        }
        if(value / 90 >= 1) return "ninety" + ((value - 90 < 0.1) ? "" : "-" + spellOutValue(value - 90));
        if(value / 80 >= 1) return "eighty" + ((value - 80 < 0.1) ? "" : "-" + spellOutValue(value - 80));
        if(value / 70 >= 1) return "seventy" + ((value - 70 < 0.1) ? "" : "-" + spellOutValue(value - 70));
        if(value / 60 >= 1) return "sixty" + ((value - 60 < 0.1) ? "" : "-" + spellOutValue(value - 60));
        if(value / 50 >= 1) return "fifty" + ((value - 50 < 0.1) ? "" : "-" + spellOutValue(value - 50));
        if(value / 40 >= 1) return "forty" + ((value - 40 < 0.1) ? "" : "-" + spellOutValue(value - 40));
        if(value / 30 >= 1) return "thirty" + ((value - 30 < 0.1) ? "" : "-" + spellOutValue(value - 30));
        if(value / 20 >= 1) return "twenty" + ((value - 20 < 0.1) ? "" : "-" + spellOutValue(value - 20));
        if(value == 19) return "nineteen";
        if(value == 18) return "eighteen";
        if(value == 17) return "seventeen";
        if(value == 16) return "sixteen";
        if(value == 15) return "fifteen";
        if(value == 14) return "fourteen";
        if(value == 13) return "thirteen";
        if(value == 12) return "twelve";
        if(value == 11) return "eleven";
        if(value == 10) return "ten";
        if(value == 9) return "nine";
        if(value == 8) return "eight";
        if(value == 7) return "seven";
        if(value == 6) return "six";
        if(value == 5) return "five";
        if(value == 4) return "four";
        if(value == 3) return "three";
        if(value == 2) return "two";
        if(value == 1) return "one";
        if(value == 0) return "zero";

        if(value == 1000.0D) return "thousand";
        if(Math.floor(value/1000) >= 1) return spellOutValue(value - 1000);
        if(Math.floor(value/100) >= 1) return spellOutValue(value - 100) + " hundred";
        else return "null";
    }

}
