package rsrc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;
import java.nio.file.FileAlreadyExistsException;

public class ResourceManager {

    private File configFile;
    private File mainLogDirectory;
    private File testLogDirectory;
    private File logFile;

    private LogWriter writer;

    private boolean hasReadConfigs = false;

    public String ALPHABET_STRING = null;
    public String NUMBERS_STRING = null;
    public String LEGAL_TEXT_STRING = null;

    public ResourceManager() throws IOException {
        File logDir = new File("logs");
        if(!logDir.exists()) {
            if(!logDir.mkdir()) {
                throw new IOException("The log directory could not be made.");
            }
        }

        File resourceManagerLogDir = new File("logs\\ResourceManager");
        if(!resourceManagerLogDir.exists()) {
            if(!resourceManagerLogDir.mkdir()) {
                throw new IOException("The ResourceManagerLogs directory could not be made.");
            }
        }

        String logName = new Date().toString().replace(" ", "_").replace(":", ".");
        logFile = new File("logs\\ResourceManager\\" + logName + ".log");

        if(logFile.exists())
            throw new FileAlreadyExistsException(logFile.getPath());

        if(!logFile.createNewFile())
            throw new IOException("The log file could not be created.");

        writer = new LogWriter(logFile);
        writer.writets("Log file successfully created.");
        writer.writets("Checking for config file at jOmetry/jOmetry.config...");
        configFile = new File("jOmetry.config");
        if(configFile.exists()) {
            writer.writets("jOMetry/jOmetry.config exists.");
        }
        else {
            writer.writets("jOMetry/jOmetry.config does not exist. Creating file...");
            configFile.createNewFile();
            writer.writets("Successful.");
            writer.writets("Writing default configurations...");

            writer.setFile(configFile);

            writer.writeln("Numbers:");
            writer.setTab(1);
            writer.writeln("0123456789");

            writer.setTab(0);
            writer.writeln("Alphabet:");
            writer.setTab(1);
            writer.writeln("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            writer.writeln("abcdefghijklmnopqrstuvwxyz");

            writer.setTab(0);
            writer.writeln("Legal Text:");
            writer.setTab(1);
            writer.writeln("\\Alphabet\\");
            writer.writeln("+-*/=[]{}()");

            writer.setFile(logFile);
            writer.setTab(0);
            writer.writets("Done.");
        }

        writer.writets("ResourceManager constructor complete.");
        writer.writeln("");
    }

    public void readConfigurations() {
        writer.setFile(logFile);
        writer.setTab(0);
        writer.writets("Loading configurations...");
        writer.setTab(1);

        try {
            writer.writets("Attempting to create ConfigurationFile object...");
            ConfigurationFile configFile = new ConfigurationFile("jOmetry.config");
            writer.writets("Successful.");
            writer.writets("Loading configurations...");
            writer.setTab(2);

            writer.writets("Loading Alphabet...");
            writer.setTab(3);
            String alphabetPayload = configFile.getConfiguration("Alphabet");
            writer.writeln(alphabetPayload);
            ALPHABET_STRING = alphabetPayload;
            writer.setTab(2);

            writer.writets("Loading Numbers...");
            writer.setTab(3);
            String numberPayload = configFile.getConfiguration("Numbers");
            writer.writeln(numberPayload);
            NUMBERS_STRING = numberPayload;
            writer.setTab(2);

            writer.writets("Loading Legal Text...");
            writer.setTab(3);
            String validPayload = configFile.getConfiguration("Legal Text");
            writer.writeln(validPayload);
            LEGAL_TEXT_STRING = validPayload;
            writer.setTab(2);

            writer.setTab(0);
            writer.writets("Done.");
            hasReadConfigs = true;
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public String getAlphabet() {
        if(ALPHABET_STRING != null) {
            return ALPHABET_STRING;
        }
        else {
            throw new IllegalStateException("ALPHABET_STRING was not initialized.");
        }
    }

    public String getNumbers() {
        if(NUMBERS_STRING != null) {
            return NUMBERS_STRING;
        }
        else {
            throw new IllegalStateException("NUMBERS_STRING was not initialized.");
        }
    }

    public String getLegalText() {
        if(LEGAL_TEXT_STRING != null) {
            return LEGAL_TEXT_STRING;
        }
        else {
            throw new IllegalStateException("LEGAL_TEXT_STRING was not initialized.");
        }
    }

    public  void purgeConfigurations(boolean verbose) {
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

    private void printDelete(File fileToDelete, boolean verbose) {
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

    private boolean recursiveDelete(File fileToDelete) {
        if(fileToDelete.isDirectory()) {
            ArrayList<File> fileArray = new ArrayList<>(java.util.Arrays.asList(fileToDelete.listFiles()));
            for(File file : fileArray) recursiveDelete(file);
        }
        return fileToDelete.delete();
    }

    public void clean() {
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
