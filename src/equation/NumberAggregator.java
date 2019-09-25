package equation;

import java.util.ArrayList;

import static equation.NumberAggregator.AggregateType.*;
import static symbol.constant.Constant.isConstant;
import static rsrc.Resources.LEGAL_TEXT_LIST;

/**
 * The {@code NumberAggregator} class contains the {@code aggregateNumbers} method which, when given a {@code String}, will
 * return a {@code String ArrayList} with variables, operators, and numbers separated from each other. The main function
 * of the {@code NumberAggregator} is to combine numbers and decimals together.
 * @author  Alejandro Doberenz
 * @version 1.0
 * @since   9/22/2019
 */
public final class NumberAggregator {

    enum AggregateType {
        LEGAL_TEXT,
        ILLEGAL_TEXT,
        WHITE_SPACE,
        NUMBER,
        DECIMAL_POINT,
    }

    private static AggregateType determineAggregateType(Object obj) {
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

    private static ArrayList<AggregateType> determineAggregateTypeArray(char[] characters) {
        ArrayList<AggregateType> aggregateTypes = new ArrayList<>(0);
        for(char c : characters)
            aggregateTypes.add(determineAggregateType(c));
        return aggregateTypes;
    }

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
