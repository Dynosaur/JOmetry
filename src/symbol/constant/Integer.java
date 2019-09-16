package symbol.constant;

import static java.lang.Integer.parseInt;

public class Integer extends Constant {

    public Integer(int value) {
        super(value);
    }

    public Integer(String value) {
        this(parseInt(value));
    }

}
