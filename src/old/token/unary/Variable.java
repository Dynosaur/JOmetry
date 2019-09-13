package old.token.unary;

import old.token.Token;

public class Variable extends Unary {

    private char name;

    private double value;

    public Variable(String symbol) {
        super(symbol, Token.Type.VARIABLE);
        if(isVariable(symbol)) name = symbol.charAt(0);
        else throw new IllegalArgumentException("Variable name is not valid.");
    }

    @Override public String toString() {
        return String.valueOf(name);
    }

    public static boolean isVariable(String input) {
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

}
