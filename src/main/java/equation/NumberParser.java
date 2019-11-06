package equation;

import java.util.ArrayList;

import static equation.NumberParser.AggregateType.*;
import static symbol.constant.Constant.isConstant;
import static rsrc.ResourceManager.LEGAL_TEXT_STRING;

/**
 * The {@code NumberParser} class's purpose is to merge numbers and decimal points within a {@code String}, and
 * aggregate mathematical symbols and numbers into a list of {@code AggregateType}. This is the first step in converting
 * a {@code String} into a mathematical statement. The {@code AggregateType} is used to assign a aggregated
 * {@code String} a subclass of {@link symbol.Symbol}. This class does not require resource
 *
 * @author  Alejandro Doberenz
 * @since   9/22/2019
 * @version 1.1
 */
public final class NumberParser {

    public enum AggregateType {
        LEGAL,
        ILLEGAL,
        EMPTY,
        NUMBER,
        POINT,
    }
    public static AggregateType determineAggregateType(char character) {
        if(character == '.') {
            return POINT;
        }
        if(String.valueOf(character).matches("\\s.")) {
            return EMPTY;
        }

        if(obj.equals('.') || obj.equals(".")) return DECIMAL_POINT;
        else if(isConstant(obj)) return NUMBER;
        else if(obj instanceof String || obj instanceof Character) {
            if(obj instanceof String) {
                if(((String) obj).length() != 1) return ILLEGAL_TEXT;
                else obj = ((String) obj).toCharArray()[0];
            }
            if(LEGAL_TEXT_STRING.contains(String.valueOf(obj))) return LEGAL_TEXT;
            else if(Character.isWhitespace((char) obj)) return WHITE_SPACE;
            else return ILLEGAL_TEXT;
        } throw new IllegalArgumentException(obj + " could not be assigned an AggregateType.");
    }

    /**
     * Preforms the {@code determineAggregateType(Object)} method on an array of chars. A {@code char} array was used
     * because the {@code determineAggregateType(Object)} method converts {@code String}s to {@code char}s.
     *
     * @param characters An array that will be passed one by one to the {@code determineAggregateType(Object)} method
     * @return An {@code ArrayList} of the passed array's {@code AggregateType}
     */
    public static ArrayList<AggregateType> determineAggregateTypeArray(char[] characters) {
        ArrayList<AggregateType> aggregateTypes = new ArrayList<>(0);
        for(char c : characters)
            aggregateTypes.add(determineAggregateType(c));
        return aggregateTypes;
    }

    /**
     * Cuts a {@code String} into separate {@code String}s that contain only a single mathematical symbol, or a number.
     *
     * @param statement A {@code String} that contains a mathematical statement
     * @return An {@code ArrayList} of {@code String}s
     */
    public static ArrayList<String> aggregateNumbers(String statement) {
        char[] chars = statement.toCharArray();
        ArrayList<AggregateType> aggregateTypes = determineAggregateTypeArray(chars);
        ArrayList<String> builtStrings = new ArrayList<>(0);

        StringBuilder builder = new StringBuilder();
        builder.append(chars[0]);
        AggregateType lastType = aggregateTypes.get(0);

        for(int i = 1; i < chars.length; i++) {
            char currentCharacter = chars[i];
            AggregateType currentType = aggregateTypes.get(i);

            if(currentType == EMPTY || currentType == ILLEGAL) {
                continue;
            }

            if(lastType == NUMBER || lastType == DECIMAL_POINT) {
                if(currentType != NUMBER && currentType != DECIMAL_POINT) {
                    builtStrings.add(builder.toString());
                    builder = new StringBuilder();
                }
            } else {
                builtStrings.add(builder.toString());
                builder = new StringBuilder();
            }
            builder.append(currentCharacter);
            lastType = currentType;
            if(i == chars.length - 1) builtStrings.add(builder.toString());
        }
        return builtStrings;
    }

}
