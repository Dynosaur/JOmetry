package symbol.constant;

public class FloatingPoint extends Constant {

    public FloatingPoint(double value) {
        super(value);
    }

    public FloatingPoint(String value) {
        this(Double.valueOf(value));
    }

}
