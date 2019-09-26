import static equation.NumberAggregator.aggregateNumbers;
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
        /*
        SymbolParser parser = new SymbolParser("y = 2.3x + b");
        parser.toSymbolList();
         */
        System.out.println(equation.NumberAggregator.aggregateNumbers("y = 10.25x + 20.1"));
        System.out.println(generateSymbols(aggregateNumbers("y = 10.25x + 20.1")));
    }

}
