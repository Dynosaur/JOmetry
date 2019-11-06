import equation.NumberParser;
import rsrc.ResourceManager;

import static equation.NumberParser.aggregateNumbers;
import static equation.SymbolGenerator.generateSymbols;

/**
 * Runs the JOmetry program.
 *
 * @author  Alejandro Doberenz
 * @version 1.0
 * @since   9/12/2019
 */
public class JOmetry {

    public static void main(String[] args) {
        ResourceManager.start();
        System.out.println(NumberParser.aggregateNumbers("10 ..1 # + y = x"));
    }

}
