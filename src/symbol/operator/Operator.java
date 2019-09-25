package symbol.operator;

/**
 * @author  Alejandro Doberenz
 * @version 1.2
 * @since   9/20/2019
 *
 * Represents a mathematical operator, such as adding or subtracting.
 */
public class Operator extends symbol.Symbol {

    public static Operator ADD = new Operator("+");
    public static Operator SUBTRACT = new Operator("-");
    public static Operator MULTIPLY = new Operator("*");
    public static Operator DIVIDE = new Operator("/");
    public static Operator EQUALS = new Operator("=");

    public static Operator[] operators = {ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUALS};

    public static boolean isOperator(Object obj) {
        if(obj instanceof Operator) return true;
        if(obj instanceof String || obj instanceof Character) {
            String string;
            if(obj instanceof String) string = (String) obj;
            else string = String.valueOf(obj);
            for(Operator op : operators)
                if(string.equals(op.getSymbol())) return true;
            return false;
        } else return false;
    }

    public Operator(String symbol) {
        super(symbol);
    }

}
