package symbol.variable;

import symbol.Symbol;

import static rsrc.Resources.ALPHABET_LIST;

public class Variable extends Symbol {

    public static boolean isVariable(Object obj) {
        if(obj instanceof Variable) return true;
        else if(obj instanceof String || obj instanceof Character) {
            if(obj instanceof String) {
                if(((String) obj).length() > 1) return false;
                else obj = ((String) obj).toCharArray()[0];
            }
            return ALPHABET_LIST.contains(obj);
        } else return false;
    }

    public Variable(String symbol) {
        super(symbol);
    }

}
