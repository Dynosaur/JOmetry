import symbol.constant.FloatingPoint;
import symbol.constant.Integer;

public class JOmetry {

    static class Operator {

        public static char ADD = '+';
        public static char SUB = '-';
        public static char MUL = '*';
        public static char DIV = '/';
        public static char EXP = '^';

        public static final char[] OPERATORS = { ADD, SUB, MUL, DIV, EXP };

        public static boolean isOperator(char symbol) {
            for(char operator : OPERATORS)
                if(symbol == operator) return true;
            return false;
        }

    }

    enum Type {
        INTEGER,
        FLOAT,
        VARIABLE,
        OPERATOR,
        EXPRESSION,
        EQUATION
    }

    static class ForeignSymbolException extends Exception {

        public ForeignSymbolException(char symbol) {
            super(symbol + " is not a recognized symbol!");
        }

    }

    static Type determine(String input) throws ForeignSymbolException {
        try {
            java.lang.Integer.parseInt(input);
            return Type.INTEGER;
        } catch(NumberFormatException notInt) {
            try {
                double d = Double.parseDouble(input);
                if(d - (int) d == 0) {
                    return Type.INTEGER;
                }
                return Type.FLOAT;
            } catch(NumberFormatException notFloat) {
                if(input.length() == 1) {
                    char symbol = input.charAt(0);
                    if(Character.isLetter(symbol)) return Type.VARIABLE;
                    else if(Operator.isOperator(symbol)) return Type.OPERATOR;
                    else throw new ForeignSymbolException(symbol);
                } else {
                    if(input.contains("=")) {
                        return Type.EQUATION;
                    } else {
                        return Type.EXPRESSION;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Term.isTerm("x5 - 2"));
        System.out.println(Term.isTerm("x5 + -2"));
        Token.parse("x5 - 2");
        FloatingPoint f = new FloatingPoint(2.215);
        System.out.println(f.toWordString());
    }

}
