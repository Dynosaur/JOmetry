/**
 * @author  Alejandro Doberenz
 * @version 1.0
 * @since   9/12/2019
 *
 * Runs the JOmetry program.
 */
public class JOmetry {

    public static void main(String[] args) {
        rsrc.Resources.loadResources();
        /*
        SymbolParser parser = new SymbolParser("y = 2.3x + b");
        parser.toSymbolList();
         */
        System.out.println(equation.NumberAggregator.aggregateNumbers("y = 10.25x + 20.1"));
    }

}
