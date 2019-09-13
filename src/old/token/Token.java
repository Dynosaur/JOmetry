package old.token;

import old.token.unary.Constant;
import old.token.unary.Space;
import old.token.unary.Delimiter;
import old.token.unary.Operator;
import old.token.unary.Variable;

public abstract class Token {

    public enum Type {

        OPERATOR,
        CONSTANT,
        VARIABLE,
        SPACE,
        EXPONENT,
        DELIMITER,
        TERM

    }

    protected String symbol;

    protected Type type;

    public Token(String value, Type group) {
        symbol = value;
        type = group;
    }

    @Override public String toString() {
        return "[\'" + symbol + "\', " + type + "]";
    }

    @Override public boolean equals(Object obj) {
        if(obj instanceof String) {
            String string = (String) obj;
            return string.equals(symbol);
        }
        if(obj instanceof Token) {
            Token token = (Token) obj;
            return token.symbol.equals(symbol);
        } else return false;
    }

    public String getSymbol() { return symbol; }

    public Type getType() { return type; }

    public String getFormatted() {
        return symbol;
    }

    public static Type determineType(String input) {
        if(Space.isSpace(input)) return Type.SPACE;
        if(Constant.isConstant(input)) return Type.CONSTANT;
        if(Operator.isOperator(input)) return Type.OPERATOR;
        if(Delimiter.isDelimiter(input)) return Type.DELIMITER;
        if(Variable.isVariable(input)) return Type.VARIABLE;
        throw new IllegalArgumentException("Indeterminate type: \'" + input + "\'");
    }

}
