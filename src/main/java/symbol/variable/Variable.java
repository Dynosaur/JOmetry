package symbol.variable;

import symbol.Symbol;

import static rsrc.ResourceManager.ALPHABET_STRING;

public class Variable extends Symbol {

    public static boolean isVariable(Object obj) {
        if(obj instanceof Variable) return true;
        else if(obj instanceof String || obj instanceof Character) {
            if(obj instanceof String) {
                if(((String) obj).length() > 1) return false;
                else obj = ((String) obj).toCharArray()[0];
            }
            return ALPHABET_STRING.contains(String.valueOf(obj));
        } else return false;
    }

    public Variable(String symbol) {
        super(symbol);
    }

}
