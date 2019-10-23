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
        rsrc.Resources.loadResources();
        System.out.println(generateSymbols(aggregateNumbers("y = 4x + 10b")));
    }

}
