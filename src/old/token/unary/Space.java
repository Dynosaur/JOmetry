package old.token.unary;

import old.token.Token;

public class Space extends Unary {

    public static final Space SPACE = new Space(" ");

    private Space(String value) {
        super(value, Token.Type.SPACE);
    }

    @Override public String toString() {
        return " ";
    }

    public static boolean isSpace(String input) {
        return input.equals(" ");
    }

}
