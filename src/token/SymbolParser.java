package token;

import java.util.ArrayList;
import java.util.List;

import symbol.Symbol;

import static symbol.Symbol.isSymbol;
import static symbol.constant.Constant.isConstant;
import static symbol.operator.Operator.isOperator;
import static symbol.variable.Variable.isVariable;
import static token.LowTokenParser.LowerTokenType;
import static java.util.Arrays.asList;

public class SymbolParser {

    enum SymbolType {
        CONSTANT,
        OPERATOR,
        VARIABLE,
        WHITESPACE,
    }

    public static boolean isWhiteSpace(Object obj) {
        if(obj instanceof String || obj instanceof Character) {
            if(obj instanceof Character) obj = String.valueOf(obj);
            return ((String) obj).matches("^\\s*$");
        }
        return false;
    }

    private String originalString;
    private char[] rawCharacterArray;

    public SymbolParser(String statement) {
        originalString = statement;
    }

    public SymbolType getSymbolType(Object obj) {
        if(isSymbol(obj)) {
            if(isConstant(obj)) return SymbolType.CONSTANT;
            else if(isOperator(obj)) return SymbolType.OPERATOR;
            else if(isVariable(obj)) return SymbolType.VARIABLE;
        } else if(isWhiteSpace(obj)) return SymbolType.WHITESPACE;
        throw new IllegalArgumentException(obj + " is not a Symbol!");
    }

    public List<Symbol> toSymbolList() {
        char[] rawCharacters = originalString.toCharArray();
        ArrayList<Symbol> symbols = new ArrayList<>(0);
        for(int i = 0; i < rawCharacters.length; i++) {
            // identify current symbol type
            SymbolType sType = getSymbolType(rawCharacters[i]);
            System.out.println(sType);
        }

        // append or create new symbol?
        return null;
    }

}
