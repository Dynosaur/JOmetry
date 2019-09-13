package old.token.composite;

import java.util.ArrayList;

public class Composite extends old.token.Token {

    protected ArrayList<old.token.unary.Unary> unaryTokens = new ArrayList<>();

    public Composite(String value, Type group) {
        super(value, group);
    }

}
