public class Term {

    private String termString;

    public Term(String symbol) {

    }

    public static boolean isTerm(String test) {
        return !test.contains("+");
    }

}
