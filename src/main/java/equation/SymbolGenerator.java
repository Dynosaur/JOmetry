package equation;

import java.util.ArrayList;

import symbol.Symbol;

import static util.Symbols.createSymbol;

public class SymbolGenerator {

    public static ArrayList<Symbol> generateSymbols(ArrayList<String> strings) {
        ArrayList<Symbol> symbols = new ArrayList<>(0);
        for(String string : strings)
            symbols.add(createSymbol(string));
        return symbols;
    }

    public static ArrayList<Symbol> generateSymbols(String statement) {
        return generateSymbols(NumberParser.aggregateNumbers(statement));
    }

}
