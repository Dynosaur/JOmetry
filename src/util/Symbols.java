package util;

import symbol.Symbol;
import symbol.constant.FloatingPoint;
import symbol.constant.Integer;
import symbol.variable.Variable;

import static symbol.Symbol.isSymbol;
import static symbol.constant.Constant.isConstant;
import static symbol.constant.FloatingPoint.isFloat;
import static symbol.operator.Operator.isOperator;
import static symbol.operator.Operator.createOperator;
import static symbol.variable.Variable.isVariable;

import static util.Symbols.SymbolType.*;

public class Symbols {

    enum SymbolType {
        FLOATING_POINT,
        INTEGER,
        OPERATOR,
        VARIABLE
    }

    public static SymbolType determineSymbolType(Object obj) {
        if(!isSymbol(obj)) throw new IllegalArgumentException(obj + " could not be converted to a Symbol!");
        if(isConstant(obj))
            if(isFloat(obj)) return FLOATING_POINT;
            else return INTEGER;
        else if(isOperator(obj)) return OPERATOR;
        else if(isVariable(obj)) return VARIABLE;
        else throw new IllegalArgumentException(obj + " could not be assigned a SymbolType!");
    }

    public static Symbol createSymbol(Object obj) {
        switch(determineSymbolType(obj)) {
            case FLOATING_POINT:
                return new FloatingPoint(String.valueOf(obj));
            case INTEGER:
                return new Integer(String.valueOf(obj));
            case OPERATOR:
                return createOperator(obj);
            case VARIABLE:
                return new Variable(String.valueOf(obj));
        }
        throw new RuntimeException("Unknown exception occurred.");
    }

}
