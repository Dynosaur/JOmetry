import symbol.Symbol;

public class Term {

    private String termString, termStrictString;

    public Term(String symbol) {

    }

    public static boolean isTerm(String test) {
        return !test.contains("+");
    }

    public Symbol[] toSymbolArray() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < termString.length(); i++) {

        }
        return null;
    }

    public static Term toStrictTerm(Term term) {
        Symbol[] symbols = term.toSymbolArray();
        return null;
    }

}
