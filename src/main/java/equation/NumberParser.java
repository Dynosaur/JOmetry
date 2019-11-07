package equation;

import rsrc.ResourceManager;
import symbol.constant.Constant;
import symbol.Symbol;
import util.Symbols;

import java.util.ArrayList;

import static equation.NumberParser.CharacterType.*;

public final class NumberParser {

    public enum CharacterType {
        LEGAL,
        ILLEGAL,
        EMPTY,
        NUMBER,
        POINT,
    }

    private ResourceManager resourceManager;

    public NumberParser(ResourceManager manager) {
        resourceManager = manager;
    }

    public boolean isLegalCharacter(char character) {
        for(char legal : resourceManager.getLegalText().toCharArray()) {
            if(character == legal) {
                return true;
            }
        }
        return false;
    }

    public CharacterType characterType(char character) {
        if(character == '.') {
            return POINT;
        }
        if(String.valueOf(character).matches("\\s")) {
            return EMPTY;
        }
        if(Constant.isConstant(character)) {
            return NUMBER;
        }
        if(isLegalCharacter(character)) {
            return LEGAL;
        }
        return ILLEGAL;
    }

    public ArrayList<String> aggregateNumbers(String statement) {
        char[] characters = statement.toCharArray();

        ArrayList<CharacterType> characterTypes = new ArrayList<>();
        for(char c : characters) {
            characterTypes.add(characterType(c));
        }

        ArrayList<String> builtStrings = new ArrayList<>(0);

        StringBuilder builder = new StringBuilder();
        builder.append(characters[0]);
        CharacterType lastType = characterTypes.get(0);

        for(int i = 1; i < characters.length; i++) {
            char currentCharacter = characters[i];
            CharacterType currentType = characterTypes.get(i);

            if(currentType == EMPTY || currentType == ILLEGAL) {
                continue;
            }

            if(lastType == NUMBER && (currentType == NUMBER ||  currentType == POINT)
            || lastType == POINT && currentType == NUMBER) {
                builder.append(currentCharacter);
                lastType = currentType;
            }

            if((lastType == NUMBER || lastType == POINT || lastType == LEGAL) && currentType == LEGAL
            || lastType == LEGAL && currentType == NUMBER) {
                builtStrings.add(builder.toString());
                builder = new StringBuilder();
                builder.append(currentCharacter);
                lastType = currentType;
            }

            if(i == characters.length - 1) {
                builtStrings.add(builder.toString());
            }
        }
        return builtStrings;
    }

    public ArrayList<Symbol> generateSymbols(ArrayList<String> strings) {
        ArrayList<Symbol> symbols = new ArrayList<>(0);
        for(String string : strings) {
            symbols.add(Symbols.createSymbol(string));
        }
        return symbols;
    }

    public ArrayList<Symbol> generateSymbols(String statement) {
        return generateSymbols(aggregateNumbers(statement));
    }

}
