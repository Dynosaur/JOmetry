package token;

import java.util.ArrayList;

import symbol.Symbol;
import symbol.constant.Constant;

import static token.LowTokenParser.LowerTokenType;
import static util.ArrayLists.toCharacterArray;

public class SymbolBuilder {

    private ArrayList<Character> rawCharacters = new ArrayList<>(0);
    private ArrayList<LowerTokenType> lowTokens = new ArrayList<>(0);

    public void append(char raw, LowerTokenType low) {
        rawCharacters.add(raw);
        lowTokens.add(low);
    }

    public Symbol toSymbol() {
        Symbol symbol = new Symbol(new String(toCharacterArray(rawCharacters)));
        if(Constant.isConstant(symbol)) {
            System.out.println("is constant");
        }
        System.out.println(symbol.getSymbol());
        return new symbol.constant.Integer("1");
    }

    @Override public String toString() {
        return rawCharacters.toString() + "\n" + lowTokens.toString();
    }

}
