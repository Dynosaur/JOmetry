import java.util.ArrayList;

import static util.Strings.isNumber;

public class Token {

    enum LowerTokenType {
        CONSTANT,
        OPERATOR,
        SPACE,
        TEXT,
        PARTITION,
        ESCAPE,
    }

    enum HigherTokenType {
        INTEGER,
        FLOAT,
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        POWER,
        EQUALS,
        OPEN_PARENTHESES,
        OPEN_SQUARE,
        OPEN_CURLY,
        CLOSE_PARENTHESES,
        CLOSE_SQUARE,
        CLOSE_CURLY
    }

    private static ArrayList<Character> alphabet = rsrc.Resources.ALPHABET;

    public static ArrayList<LowerTokenType> parseLowerTokenTypes(String statement) {
        ArrayList<LowerTokenType> tokenTypes = new ArrayList<>(0);
        for(char token : statement.toCharArray()) {
            if(token == ' ') tokenTypes.add(LowerTokenType.SPACE);
            if(token == '\\') tokenTypes.add(LowerTokenType.ESCAPE);
            else if(token == '+' || token == '-' || token == '*' || token == '/' || token == '^' || token == '=') tokenTypes.add(LowerTokenType.OPERATOR);
            else if(token == '(' || token == '[' || token == '{' || token== ')' || token == ']' || token == '}') tokenTypes.add(LowerTokenType.PARTITION);
            else if(isNumber(String.valueOf(token))) tokenTypes.add(LowerTokenType.CONSTANT);
            else if(alphabet.contains(token)) tokenTypes.add(LowerTokenType.TEXT);
            else throw new IllegalArgumentException("Unknown character: \'" + token + "\'");
        }
        return tokenTypes;
    }

    public static ArrayList<HigherTokenType> parseHigherTokenTypes(String statement) {
        ArrayList<HigherTokenType> tokenTypes = new ArrayList<>(0);
        for(char token : statement.toCharArray()) {

        }
    }

}
