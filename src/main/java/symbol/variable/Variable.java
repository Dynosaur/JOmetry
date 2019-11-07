package symbol.variable;

import rsrc.ResourceManager;
import symbol.Symbol;

public class Variable extends Symbol {

    private static ResourceManager resourceManager;

    public static void setResourceManager(ResourceManager manager) {
        resourceManager = manager;
    }

    public static boolean isVariable(Object obj) {
        if(obj instanceof Variable) return true;
        else if(obj instanceof String || obj instanceof Character) {
            if(obj instanceof String) {
                if(((String) obj).length() > 1) return false;
                else obj = ((String) obj).toCharArray()[0];
            }
            return resourceManager.getAlphabet().contains(String.valueOf(obj));
        } else return false;
    }

    public Variable(String symbol) {
        super(symbol);
    }

}
