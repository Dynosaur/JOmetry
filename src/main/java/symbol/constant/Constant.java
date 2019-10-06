package symbol.constant;

import symbol.Symbol;

/**
 * The {@code Constant} class represents a mathematical number.
 *
 * @author  Alejandro Doberenz
 * @version 1.1.4
 * @since   9/14/2019
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

    Constant(Number number) {
        super(String.valueOf(number));
        constantValue = number;
    }

    @Override public String toString() { return toWordString(); }

    public String toWordString() { return util.Strings.numberToWord(getValue().doubleValue()); }

    public Number getValue() { return constantValue; }

}
