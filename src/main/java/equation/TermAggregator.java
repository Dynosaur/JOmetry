package equation;

import java.util.ArrayList;

import symbol.Symbol;
import symbol.constant.Constant;
import symbol.operator.Operator;
import symbol.variable.Variable;

import static equation.TermAggregator.SymbolType.*;
import static equation.SymbolGenerator.generateSymbols;

public class TermAggregator {

    enum SymbolType {
        CONSTANT,
        OPERATOR,
        VARIABLE,
    }

    class TermBuilder {

    }

    private static SymbolType determineSymbolType(Symbol symbol) {
        if(symbol instanceof Constant) return CONSTANT;
        if(symbol instanceof Operator) return OPERATOR;
        if(symbol instanceof Variable) return VARIABLE;
        else throw new IllegalArgumentException(symbol + " is not a Constant, Operator, or Variable!");
    }

    public static void aggregateTerms(String statement) {
        ArrayList<Symbol> symbols = generateSymbols(statement);
        System.out.println(symbols);
        for(int i = 0; i < symbols.size(); i++) {
            switch(determineSymbolType(symbols.get(i))) {
                case CONSTANT:
            }
            System.out.println(determineSymbolType(symbols.get(i)));
        }
    }

    public static void main(String[] args) {
        rsrc.Resources.loadResources();
        aggregateTerms("y = mx + 10");
    }

}
