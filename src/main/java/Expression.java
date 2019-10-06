/**
 * An Expression represents a mathematical expression, or a sequence of numbers, variables, and/or OPERATORS, such as
 * x + 5, or (2x + 4)(x + 3), but do not contain a equals sign. Expressions can be simplified but cannot be solved.
 *
 * @author  Alejandro Doberenz
 * @since   9/12/2019
 * @version 0.1
 */
public class Expression {

    public class NoExpressionException extends Exception {

        public NoExpressionException(String message) {
            super(message);
        }

    }

    private String expressionString;

    public Expression(String exp) throws NoExpressionException {
        if(!isExpression(exp)) throw new NoExpressionException("No expression found in string.");
        expressionString = exp;
    }

    void simplify() {
    }

    public static boolean isExpression(String test) {
        return  test.length() > 1 &&
                !test.contains("=");
    }

}
