package old.token.composite;

import old.token.unary.Operator;
import old.token.Token;

import static old.token.Token.Type.*;

import java.util.ArrayList;

import static old.token.Token.Type.OPERATOR;

public class Term extends Composite {

    private ArrayList<Token> individualTokens;

    public Term(String value) {
        super(value, Type.TERM);
        individualTokens = new ArrayList<>(old.combo.Combination.unaryParse(symbol));
    }

    public static boolean isTerm(String input) {
        if(old.combo.Combination.determineType(input) == old.combo.Combination.Type.EQUATION) return false;
        ArrayList<Token> tokens = new ArrayList<>(old.combo.Combination.unaryParse(input));
        for(Token element : tokens) {
            if(element == Operator.POWER) continue;
            if(element.getType() == OPERATOR) return false;
        }
        return true;
    }

    @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Token element : individualTokens) builder.append(element);
        return builder.toString();
    }

    public static boolean isTermElement(Token input) {
        return input == Operator.POWER || input.getType() == CONSTANT || input.getType() == VARIABLE;
    }

}
