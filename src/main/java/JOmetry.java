import rsrc.ResourceManager;
import equation.NumberParser;
import symbol.variable.Variable;

import java.util.Scanner;

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
        Variable.setResourceManager(resourceManager);
        NumberParser parser = new NumberParser(resourceManager);
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("Enter an equation: ");
            String input = in.nextLine();
            System.out.println(parser.generateSymbols(input));
        }
    }

}
