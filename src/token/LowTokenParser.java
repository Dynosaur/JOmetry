package token;

import java.util.ArrayList;

import static util.Strings.isNumber;

/**
 *
 */
public class LowTokenParser {

    enum LowerTokenType {
        CONSTANT,
        OPERATOR,
        SPACE,
        TEXT,
        PARTITION,
        ESCAPE,
    }

    private String originalString;

    public LowTokenParser(String statement) {
        originalString = statement;
    }

    public ArrayList<LowerTokenType> parseLowerTokenTypes() {
        ArrayList<LowerTokenType> tokenTypes = new ArrayList<>(0);
        for(char token : originalString.toCharArray()) {
            if(token == ' ') tokenTypes.add(LowerTokenType.SPACE);
            else if(token == '\\') tokenTypes.add(LowerTokenType.ESCAPE);
            else if(token == '+' || token == '-' || token == '*' || token == '/' || token == '^' || token == '=') tokenTypes.add(LowerTokenType.OPERATOR);
            else if(token == '(' || token == '[' || token == '{' || token== ')' || token == ']' || token == '}') tokenTypes.add(LowerTokenType.PARTITION);
            else if(isNumber(String.valueOf(token))) tokenTypes.add(LowerTokenType.CONSTANT);
            else if(rsrc.Resources.ALPHABET_LIST.contains(token)) tokenTypes.add(LowerTokenType.TEXT);
            else throw new IllegalArgumentException("Unknown character: \'" + token + "\' Code: " + (int) token);
        }
        return tokenTypes;
    }

}
