import equation.NumberParser;
import rsrc.ResourceManager;

import java.io.IOException;

import static equation.NumberParser.aggregateNumbers;

/**
 * Runs the JOmetry program.
 *
 * @author  Alejandro Doberenz
 * @version 1.0
 * @since   9/12/2019
 */
public class JOmetry {

    public static void main(String[] args) throws IOException {
        ResourceManager rm = new ResourceManager();
        System.out.println(NumberParser.aggregateNumbers("5 + 1092389a1.2x + 0.382"));
    }

}
