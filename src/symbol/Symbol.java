package symbol;

public abstract class Symbol {

    protected String symbolString;

    public Symbol(String symbol) {
        symbolString = symbol;
    }

    public String getSymbol() { return symbolString; }

}
