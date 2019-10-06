package symbol.constant;

import static java.lang.Integer.parseInt;

/**
 * Represents a mathematical integer (Whole number).
 *
 * @author  Alejandro Doberenz
 * @version 1.1
 * @since   9/14/2019
 */
public class Integer extends Constant {

    public static boolean isInteger(Object obj) {
        if(isConstant(obj)) {
            if(obj instanceof String) {
                obj = Double.parseDouble((String) obj);
            }
            Number n = (Number) obj;
            return n.doubleValue() - n.intValue() == 0;
        } else return false;
    }

    public Integer(int value) {
        super(value);
    }

    public Integer(String value) {
        this(parseInt(value));
    }

}
