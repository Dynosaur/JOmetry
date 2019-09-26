package symbol.operator;

/**
 * Represents a mathematical operator, such as adding or subtracting.
 *
 * @author  Alejandro Doberenz
 * @version 1.2.1
 * @since   9/20/2019
 */
public class Operator extends symbol.Symbol {

    public static Operator ADD = new Operator("+");
    public static Operator SUBTRACT = new Operator("-");
    public static Operator MULTIPLY = new Operator("*");
    public static Operator DIVIDE = new Operator("/");
    public static Operator EQUALS = new Operator("=");

    public final static Operator[] OPERATORS = {ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUALS};

    public static boolean isOperator(Object obj) {
        if(obj instanceof Operator) return true;
        if(obj instanceof String || obj instanceof Character) {
            String string;
            if(obj instanceof String) string = (String) obj;
            else string = String.valueOf(obj);
            for(Operator op : OPERATORS)
                if(string.equals(op.getSymbol())) return true;
            return false;
        } else return false;
    }

    public static Operator createOperator(Object obj) {
        if(!isOperator(obj)) throw new IllegalArgumentException("Could not create Operator: obj could not be converted to an Operator.");
        if(obj instanceof String || obj instanceof Character) {
            String string;
            if(obj instanceof String) string = (String) obj;
            else string = String.valueOf(obj);
            for(Operator op : OPERATORS)
                if(string.equals(op.getSymbol())) return op;
            throw new IllegalArgumentException("Could not Create Operator: obj did not match any existing Operators.");
        } else throw new IllegalArgumentException("Could not create Operator: obj is not a String or Character.");
    }

    private Operator(String symbol) {
        super(symbol);
    }

}
