package old.combo;

import java.util.ArrayList;

import static old.util.ArrayListMethods.*;

import old.token.composite.Term;
import old.token.unary.Operator;

/**
 * @author Alejandro Doberenz
 * @version 5/16/2019
 *
 * An equation is two expressions separated by a single equals sign. Every equation has a left and right side.
 */
public class Equation extends Combination {

    // The left and right side of the equation as expressions
    private Expression leftSide, rightSide;

    public Equation(String input) {
        super(input);
        // Check if input is an equation, if not then throw an exception
        if(isEquation(original)) {
            int equalsIndex = individualTokens.indexOf(Operator.EQUALS);
            leftSide = new Expression(tokenArrayToString(new ArrayList<>(individualTokens.subList(0, equalsIndex))));
            rightSide = new Expression(tokenArrayToString(new ArrayList<>(individualTokens.subList(equalsIndex + 1, individualTokens.size()))));
        } else throw new IllegalArgumentException("Input is not an equation.");
    }

    @Override public String toString() {
        return leftSide + "= " + rightSide;
    }

    // Checks if a given string can be converted to an equation
    public static boolean isEquation(String input) {

        // Break string into array list of individualTokens
        ArrayList<old.token.Token> tokens = new ArrayList<>(Combination.unaryParse(input));

        // Check if there is only one equals sign in the equation
        if(getNumberOfInstances(tokens, Operator.EQUALS) == 1) {

            // Separate the equation into its left and right parts
            int equalsIndex = tokens.indexOf(Operator.EQUALS);
            ArrayList<old.token.Token> leftSide = new ArrayList<>(tokens.subList(0, equalsIndex));
            ArrayList<old.token.Token> rightSide = new ArrayList<>(tokens.subList(equalsIndex + 1, tokens.size()));

            // Parse the array list of individualTokens into a string
            String left = tokenArrayToString(leftSide);
            String right = tokenArrayToString(rightSide);

            // Check if the left and rides sides are either expressions or terms
            return Expression.isExpression(left) || Term.isTerm(left) &&
            Expression.isExpression(right) || Term.isTerm(right);

        } else return false;
    }

}
