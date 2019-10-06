package equation;

import java.util.ArrayList;

import static equation.NumberParser.AggregateType.*;
import static symbol.constant.Constant.isConstant;
import static rsrc.Resources.LEGAL_TEXT_LIST;

/**
 * The {@code NumberParser} class's purpose is to merge numbers and decimal points within a {@code String}, and
 * aggregate mathematical symbols and numbers into a list of {@code AggregateType}. This is the first step in converting
 * a {@code String} into a mathematical statement. The {@code AggregateType} is used to assign a aggregated
 * {@code String} a subclass of {@link symbol.Symbol}. This class does not require resource
 *
 * @author  Alejandro Doberenz
 * @version 1.0.3
 * @since   9/22/2019
 */
public final class NumberParser {

    /**
     * The {@code AggregateType} is a rough categorization of the different type of characters the parser may encounter.
     * {@code LEGAL_TEXT} represents non-numerical values (Such as {@link symbol.operator.Operator} that are still
     * legal in a mathematical sense. Characters that are legal are defined in {@code /resources/legal_text}.
     *
     * {@code ILLEGAL_TEXT} represents any non-legal text, such as an ampersand or percent symbol. These are not
     * explicitly defined, rather all characters are assumed to be illegal until they are checked to be legal.
     *
     * {@code WHITE_SPACE} represents any white space characters, such as new lines, tabs, or spaces. The parser must
     * keep track of these because it automatically removes all white space characters.
     *
     * {@code NUMBER} represents a numerical character 0-9. The parser marks these so it can combine numbers that
     * are next to a number or a decimal point.
     *
     * {@code DECIMAL_POINT} represents a decimal point. The parser
     *
     * @author  Alejandro Doberenz
     * @version 2.0
     * @since   9/22/2019
     */
    public enum AggregateType {
        LEGAL_TEXT,
        ILLEGAL_TEXT,
        WHITE_SPACE,
        NUMBER,
        DECIMAL_POINT,
    }

    /**
     * Attempts to assign a given object an {@code AggregateType}. It will throw an {@code IllegalArgumentException} in
     * the event one cannot be found.
     *
     * @param obj An object you want to assign an {@code AggregateType}
     * @return The {@code AggregateType} of the passed object
     */
    public static AggregateType determineAggregateType(Object obj) {
        if(obj.equals('.') || obj.equals(".")) return DECIMAL_POINT;
        else if(isConstant(obj)) return NUMBER;
        else if(obj instanceof String || obj instanceof Character) {
            if(obj instanceof String) {
                if(((String) obj).length() != 1) return ILLEGAL_TEXT;
                else obj = ((String) obj).toCharArray()[0];
            }
            if(LEGAL_TEXT_LIST.contains(obj)) return LEGAL_TEXT;
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

            if(currentType == WHITE_SPACE) continue;

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
