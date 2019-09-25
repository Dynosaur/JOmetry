package symbol;

import static symbol.constant.Constant.isConstant;
import static symbol.operator.Operator.isOperator;
import static symbol.variable.Variable.isVariable;

/**
 * @author  Alejandro Doberenz
 * @version 1.1.1
 * @since   9/14/2019
 *
 * Represents a mathematical symbol, such as a number or variable.
 */
public class Symbol {

    public static boolean isSymbol(Object obj) {
        if(obj instanceof Symbol) return true;
        if(obj instanceof String || obj instanceof Character) {
            if(obj instanceof Character) obj = String.valueOf(obj);
            if(isConstant(obj)) return true;
            if(isOperator(obj)) return true;
            if(isVariable(obj)) return true;
            return false;
        } else return false;
    }

    protected String symbolString;

    public Symbol(String symbol) {
        symbolString = symbol;
    }

    public String getSymbol() { return symbolString; }

    @Override public boolean equals(Object obj) {
        if(obj instanceof Symbol) {
            Symbol symbol = (Symbol) obj;
            return getSymbol().equals(symbol.getSymbol());
        } else return false;
    }

}
