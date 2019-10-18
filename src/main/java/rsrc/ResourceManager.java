package rsrc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;

/**
 * The Resource Manager will control configurations for the program. This includes reading and writing to configuration
 * files, providing data to the program, and deleting unnecessary files.
 *
 * @author  Alejandro Doberenz
 * @since   10/17/2019
 * @version 0.2
 */
public class ResourceManager {

    private static boolean isFirstTime = true;

    private static final String MAIN_LOGS_PATH = "src\\main\\resources\\logs";
    private static final String TEST_LOGS_PATH = "src\\test\\resources\\logs";

    private static File configFile = new File("jOmetry.config");
    private static File mainLogDirectory = new File(MAIN_LOGS_PATH);
    private static File testLogDirectory = new File(TEST_LOGS_PATH);

    public static void start() {
        isFirstTime = checkIsFirstTime();
        if(isFirstTime) {
            System.out.println("Running first time setup...");
            try {
                if(configFile.createNewFile()) System.out.println("Created jOmetry/jOmetry.config");
                if(mainLogDirectory.mkdirs()) System.out.println("Created src/main/resources/logs");
                if(testLogDirectory.mkdirs()) System.out.println("Created src/test/resources/logs");
            } catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.out.println("Writing default configurations...");
            try {
                PrintWriter out = new PrintWriter(configFile);
                out.println("# Alphabet");
                out.println("    ABCDEFGHIJKLMNOPQRSTUVWXYZ\r\n    abcdefghijklmnopqrstuvwxyz");
                out.println("# Legal Text");
                out.println("    $Alphabet");
                out.println("    +-*/=[]{}()");
                out.close();
            } catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.out.println("Done.");
        }

    }

    private static boolean checkIsFirstTime() {
        return !(configFile.exists() && mainLogDirectory.exists() && testLogDirectory.exists());
    }

    public static void purgeConfigurations() {
        System.out.println("\n     ====================");
        System.out.println("Purging configuration and log files...");
        printDelete(configFile);
        printDelete(mainLogDirectory);
        printDelete(testLogDirectory);
    }

    private static void printDelete(File fileToDelete) {
        System.out.print(fileToDelete.getPath() + " deleted: [ ");
        if(fileToDelete.isDirectory())
            if(recursiveDelete(fileToDelete)) System.out.print("x");
            else System.out.print(" ");
        else
            if(fileToDelete.delete()) System.out.print("x");
            else System.out.print(" ");
        System.out.println(" ]");
    }

    private static boolean recursiveDelete(File fileToDelete) {
        if(fileToDelete.isDirectory()) {
            ArrayList<File> fileArray = new ArrayList<>(java.util.Arrays.asList(fileToDelete.listFiles()));
            for(File file : fileArray) recursiveDelete(file);
        }
        return fileToDelete.delete();
    }

    public static void main(String[] args) {
        start();
        purgeConfigurations();
    }

}
