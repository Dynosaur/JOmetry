package old.combo;

import old.token.*;
import old.token.composite.Term;
import old.token.unary.Constant;
import old.token.unary.Space;
import old.token.unary.Delimiter;
import old.token.unary.Operator;
import old.token.unary.Variable;

import static old.token.Token.Type.*;

import java.util.ArrayList;

/**
 *
 */
public abstract class Combination {

    public enum Type {

        EQUATION,
        EXPRESSION

    }

    String original;

    ArrayList<Token> individualTokens;

    Combination(String combination) {

        original = combination;
        individualTokens = new ArrayList<>(unaryParse(original));

    }

    public ArrayList<Token> getIndividualTokens() {

        return individualTokens;
    }

    // Determines the type a string would be
    public static Type determineType(String input) {

        if(Expression.isExpression(input)) return Type.EXPRESSION;
        if(Equation.isEquation(input)) return Type.EQUATION;
        else throw new IllegalArgumentException("Indeterminate type: \'" + input + "\'");
    }

    // Takes a string and parses it into a token array list
    public static ArrayList<Token> unaryParse(String input) {

        // Separate every character into its own string
        ArrayList<String> stringTokens = new ArrayList<>(java.util.Arrays.asList(input.trim().split("")));

        ArrayList<Token> tokens = new ArrayList<>();

        // For each string token, determine its type and add it to the token list
        for(String element : stringTokens)
            switch(Token.determineType(element)) {
                case SPACE:
                    tokens.add(Space.SPACE);
                    break;
                case CONSTANT:
                    tokens.add(new Constant(element));
                    break;
                case OPERATOR:
                    tokens.add(Operator.determineOperator(element));
                    break;
                case DELIMITER:
                    tokens.add(Delimiter.determineDelimiter(element));
                    break;
                case VARIABLE:
                    tokens.add(new Variable(element));
                    break;
            }

        // Loop through and combine all constants to largest form ('1' and '2' becomes '12')
        if(Constant.containsConstant(input)) {

            // Builds chains of constants so that they may be simplified
            ArrayList<Chain<Token>> chains = new ArrayList<>();
            boolean lastWasNumber = false;
            int chainLength = 0;
            int chainStart = 0;
            for(int i = 0; i < tokens.size(); i++) {
                Token element = tokens.get(i);
                boolean isNumber = element.getType().equals(CONSTANT);

                // If the element is a term, skip it
                if(element.getType() == TERM) continue;

                // If the last element wasn't a number and the current element is a number, start a new chain
                if(!lastWasNumber && isNumber) {
                    lastWasNumber = true;
                    chainLength++;
                    chainStart = i;
                    continue;
                }

                // If the last element was a number and the current element is a number, add to the chain
                if(lastWasNumber && isNumber) {
                    chainLength++;
                    continue;
                }

                // If the last element was a number, the current element isn't a number, and the chain is less than 2 length, clear the current chain
                if(lastWasNumber && !isNumber && chainLength < 2) {
                    lastWasNumber = false;
                    chainLength = 0;
                }

                // If the last element was a number, the current element isn't a number, and the chain is at least 2 length, add the chain to the list
                if(lastWasNumber && !isNumber && chainLength >= 2) {
                    lastWasNumber = false;
                    Chain<Token> chain = new Chain<>(tokens, chainStart, chainLength);
                    chains.add(chain);
                    StringBuilder builder = new StringBuilder();
                    for(Token token : chain.elementList) builder.append(token.getSymbol());
                    Constant constant = new Constant(builder.toString());
                    tokens.set(chain.indexStart, constant);
                    chainLength = 0;
                }

            }
            // If this isn't here, the loop can end with a chain not added to the list, so that's why this is here
            if(chainLength >= 2) {
                Chain<Token> chain = new Chain<>(tokens, chainStart, chainLength);
                chains.add(chain);
                StringBuilder builder = new StringBuilder();
                for(Token token : chain.elementList) builder.append(token.getSymbol());
                Constant constant = new Constant(builder.toString());
                tokens.set(chain.indexStart, constant);
            }

            // Remove any of the extra characters that are no longer used
            for(int i = chains.size() - 1; i >= 0; i--)
                for(int j = chains.get(i).indices + chains.get(i).indexStart - 1; j > chains.get(i).indexStart; j--)
                    tokens.remove(j);

        }

        return tokens;

    }

    // Parse a string into terms rather than individual tokens
    public static ArrayList<Token> compositeParse(String input) {

        ArrayList<Token> tokens = new ArrayList<>(unaryParse(input));

        // Loop through and create terms to simplify list
        ArrayList<Chain<Token>> chains = new ArrayList<>();
        boolean lastWasValid = false;
        int chainLength = 0;
        int chainStart = 0;
        for(int i = 0; i < tokens.size(); i++) {
            Token element = tokens.get(i);
            boolean isValid = Term.isTermElement(element);
            if(element.getType() == TERM) continue;
            if(!lastWasValid && isValid) {
                lastWasValid = true;
                chainLength++;
                chainStart = i;
                continue;
            }
            if(lastWasValid && isValid) {
                chainLength++;
                continue;
            }
            if(lastWasValid && !isValid && chainLength < 2) {
                lastWasValid = false;
                chainLength = 0;
            }
            if(lastWasValid && !isValid && chainLength >= 2) {
                lastWasValid = false;
                Chain<Token> chain = new Chain<>(tokens, chainStart, chainLength);
                chains.add(chain);
                StringBuilder builder = new StringBuilder();
                for(Token token : chain.elementList) builder.append(token.getSymbol());
                Term term = new Term(builder.toString());
                tokens.set(chain.indexStart, term);
                chainLength = 0;
            }
        }
        if(chainLength >= 2) {
            Chain<Token> chain = new Chain<>(tokens, chainStart, chainLength);
            chains.add(chain);
            StringBuilder builder = new StringBuilder();
            for(Token token : chain.elementList) builder.append(token.getSymbol());
            Term term = new Term(builder.toString());
            tokens.set(chain.indexStart, term);
        }

        for(int i = chains.size() - 1; i >= 0; i--)
            for(int j = chains.get(i).indices + chains.get(i).indexStart - 1; j > chains.get(i).indexStart; j--)
                tokens.remove(j);

        return tokens;
    }

    private static class Chain<E> {

        ArrayList<E> origin;

        private ArrayList<E> elementList;

        private int indexStart, indices;

        public Chain(ArrayList<E> list, int start, int number) {

            origin = list;
            indexStart = start;
            indices = number;
            elementList = new ArrayList<>();
            for(int i = 0; i < indices; i++) elementList.add(origin.get(i + indexStart));
        }

    }

}
