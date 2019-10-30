package rsrc;

import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The Resource Manager will control configurations for the program. This includes reading and writing to configuration
 * files, providing data to the program, and deleting unnecessary files.
 *
 * @author  Alejandro Doberenz
 * @since   10/17/2019
 * @version 0.3
 */
public class ResourceManager {

    // <editor-fold desc="Variables">
    private static boolean isFirstTime = true;

    private static File configFile = new File("jOmetry.config");
    private static File mainLogDirectory = new File("src\\main\\resources\\logs");
    private static File testLogDirectory = new File("src\\test\\resources\\logs");

    public static String ALPHABET_STRING = null;
    public static String LEGAL_TEXT_STRING = null;
    // </editor-fold>

    public static void start() {
        isFirstTime = !(
                configFile.exists()
                && mainLogDirectory.exists()
                && testLogDirectory.exists()
        );

        if(isFirstTime)
            createConfigurations(true);

        System.out.println("Loading configurations...");
        try {
            ConfigurationFile configFile = new ConfigurationFile("jOmetry.config");
            ALPHABET_STRING = configFile.getConfiguration("Alphabet");
            LEGAL_TEXT_STRING = configFile.getConfiguration("Legal Text");
            System.out.println("Done.");
        } catch(Exception e) {
            System.err.println("An error has occurred, it is recommended to wipe the configuration files.");
            clean();
            System.exit(1);
        }
    }

    public static void createConfigurations(boolean verbose) {
        if(verbose) System.out.println("Running first time setup...");
        try {
            if(configFile.createNewFile())
                System.out.println("Created jOmetry/jOmetry.config");
            if(mainLogDirectory.mkdirs())
                System.out.println("Created src/main/resources/logs");
            if(testLogDirectory.mkdirs())
                System.out.println("Created src/test/resources/logs");
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Writing default configurations...");
        try {
            PrintWriter out = new PrintWriter(configFile);
            out.println("Alphabet:");
            out.println("\tABCDEFGHIJKLMNOPQRSTUVWXYZ");
            out.println("\tabcdefghijklmnopqrstuvwxyz");
            out.println("Legal Text:");
            out.println("\t\\Alphabet\\");
            out.println("\t+-*/=[]{}()");
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Done.\n");
    }

    public static void purgeConfigurations(boolean verbose) {
        if(verbose) {
            System.out.println("\n\t=======================");
            System.out.println("\t\t\tPURGING ");
            System.out.println("Purging configuration and log files...");
        }
        printDelete(configFile, verbose);
        printDelete(mainLogDirectory, verbose);
        printDelete(testLogDirectory, verbose);
        if(verbose) {
            System.out.println("Done.");
            System.out.println("\t=======================");
        }
    }

    private static void printDelete(File fileToDelete, boolean verbose) {
        if(verbose)
            System.out.print(fileToDelete.getPath() + " deleted: [ ");
        if(fileToDelete.isDirectory()) {
            if(verbose) {
                if(recursiveDelete(fileToDelete))
                    System.out.print("x");
                else
                    System.out.print(" ");
            } else
                recursiveDelete(fileToDelete);
        } else {
            if(verbose) {
                if(fileToDelete.delete())
                    System.out.print("x");
                else
                    System.out.print(" ");
            } else
                fileToDelete.delete();
        }
        if(verbose)
            System.out.println(" ]");
    }

    private static boolean recursiveDelete(File fileToDelete) {
        if(fileToDelete.isDirectory()) {
            ArrayList<File> fileArray = new ArrayList<>(java.util.Arrays.asList(fileToDelete.listFiles()));
            for(File file : fileArray) recursiveDelete(file);
        }
        return fileToDelete.delete();
    }

    public static void clean() {
        Scanner in = new Scanner(System.in);
        System.out.println();
        while(true) {
            System.out.print("Are you sure you want to erase all test logs and configurations? ");
            String input = in.nextLine().toLowerCase();
            if(input.equals("yes") || input.equals("y")) {
                purgeConfigurations(true);
                return;
            } else if(input.equals("no") || input.equals("n")) {
                System.out.println("Purge aborted.");
                return;
            }
        }
    }

}
