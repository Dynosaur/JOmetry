package old.combo;

import old.token.unary.Operator;

import old.token.Token;

import java.util.ArrayList;

/**
 * @author Alejandro Doberenz
 * @version 5/16/2019
 *
 * An expression is a group of terms connected via operators. An expression may not have an equals sign.
 */
public class Expression extends Combination {

    private ArrayList<Token> expressionTokens;

    public Expression(String expression) {
        super(expression);
        expressionTokens = new ArrayList<>(Combination.unaryParse(original));
    }

    @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Token element : old.util.ArrayListMethods.removeTypes(expressionTokens, Token.Type.SPACE)) {
            builder.append(element + " ");
        }
        return builder.toString();
    }

    public static boolean isExpression(String input) {

        ArrayList<Token> tokens = new ArrayList<>(Combination.unaryParse(input));

        for(Token token : tokens)
            if(token.equals(Operator.EQUALS)) return false;

        return true;

    }

}
