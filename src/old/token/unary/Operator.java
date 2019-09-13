package old.token.unary;

import old.token.Token;

public class Operator extends Unary {

    private String stringName;

    public static final Operator ADD = new Operator("+", "plus");

    public static final Operator SUBTRACT = new Operator("-", "minus");

    public static final Operator MULTIPLY = new Operator("*", "times");

    public static final Operator DIVIDE = new Operator("/", "divided by");

    public static final Operator POWER = new Operator("^", "to the power of");

    public static final Operator EQUALS = new Operator("=", "equals");

    public static final Operator[] operators = {ADD, SUBTRACT, MULTIPLY, DIVIDE, POWER, EQUALS};

    private Operator(String symbol, String name) {
        super(symbol, Token.Type.OPERATOR);
        stringName = name;
    }

    @Override public String toString() {
        return stringName;
    }

    public static boolean isOperator(String input) {
        for(Operator operator : operators) if(operator.equals(input)) return true;
        return false;
    }

    public static Operator determineOperator(String input) {
        if(isOperator(input)) {
            for(int i = 0; i < operators.length; i++)
                if(input.equals(operators[i].symbol)) return operators[i];
            throw new IllegalArgumentException("\'" + input + "\' is not in the operators list!");
        } else throw new IllegalArgumentException("\'" + input + "\' is not an operator!");
    }

}
