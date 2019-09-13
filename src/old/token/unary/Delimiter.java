package old.token.unary;

public class Delimiter extends Unary {

    public static final Delimiter OPEN_PAR = new Delimiter("(");

    public static final Delimiter CLOSE_PAR = new Delimiter(")");

    public static final Delimiter OPEN_SQR = new Delimiter("[");

    public static final Delimiter CLOSE_SQR = new Delimiter("]");

    public static final Delimiter OPEN_CURL = new Delimiter("{");

    public static final Delimiter CLOSE_CURL = new Delimiter("}");

    private static final Delimiter[] delimiters = {OPEN_PAR, CLOSE_PAR, OPEN_SQR, CLOSE_SQR, OPEN_CURL, CLOSE_CURL};

    public Delimiter(String value) {
        super(value, Type.DELIMITER);
    }

    @Override public String toString() {
        return symbol;
    }

    public static boolean isDelimiter(String input) {
        for(Delimiter delimiter : delimiters) if(delimiter.equals(input)) return true;
        return false;
    }

    public static Delimiter determineDelimiter(String input) {
        for(Delimiter delimiter : delimiters) if(delimiter.equals(input)) return delimiter;
        throw new IllegalArgumentException("\'" + input + "\' is not a delimiter!");
    }

    public static boolean containsDelimiters(String input) {
        for(String token : input.split(""))
            for(Delimiter delimiter : delimiters)
                if(token.equals(delimiter)) return true;
        return false;
    }

}
