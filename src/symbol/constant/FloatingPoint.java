package symbol.constant;

/**
 * Represents a decimal mathematical number.
 *
 * @author  Alejandro Doberenz
 * @version 1.1
 * @since   9/14/2019
 */
public class FloatingPoint extends Constant {

    public static boolean isFloat(Object obj) {
        if(isConstant(obj)) {
            if(obj instanceof String) {
                obj = Double.parseDouble((String) obj);
            }
            Number n = (Number) obj;
            return n.doubleValue() - n.intValue() != 0;
        } else return false;
    }

    public FloatingPoint(double value) {
        super(value);
    }

    public FloatingPoint(String value) {
        this(Double.valueOf(value));
    }

}
