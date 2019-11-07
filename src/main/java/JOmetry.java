import rsrc.ResourceManager;
import equation.NumberParser;

/**
 * Runs the JOmetry program.
 *
 * @author  Alejandro Doberenz
 * @version 1.1
 * @since   9/12/2019
 */
public class JOmetry {

    public static void main(String[] args) throws java.io.IOException {
        ResourceManager resourceManager = new ResourceManager();
        resourceManager.readConfigurations();
        NumberParser parser = new NumberParser(resourceManager);
        System.out.println(parser.aggregateNumbers("5 + 1092389a1.2x + 0.382"));
    }

}
