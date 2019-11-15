package symbol;

import symbol.constant.Constant;
import symbol.constant.Integer;

public class Factorial extends Symbol {

    public static boolean isFactorial(String symbol) {
        return symbol.matches("[0-9]+!");
    }

    private Constant base;

    public Factorial(String symbol) {
        super(symbol);
        if(!isFactorial(symbol)) {
            throw new IllegalArgumentException("Symbol is not a factorial.");
        }
        base = new Integer(symbol.substring(0, symbol.length() - 1));
    }

    public static int factorial(int f) {
        if(f == 0) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Factorial f = new Factorial("10!");
        System.out.println(f.base);
    }


}
