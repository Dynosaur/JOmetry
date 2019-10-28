package rsrc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigReader {

    private File configFile;

    private ArrayList<String> rawCharacters = new ArrayList<>();

    public ConfigReader(File file) {
        if(!file.exists())
            throw new IllegalArgumentException("File \'" +  file.getPath() + " could not be found.");
        configFile = file;
    }

    void read() {
        try {
            Scanner scanner = new Scanner(configFile).useDelimiter("");
            while(scanner.hasNext()) {
                String input = scanner.next();
                if(input.equals("\n"))
                    rawCharacters.add("\"Newline character\"");
                else if(input.equals("\r"))
                    rawCharacters.add("\"Return carriage character\"");
                else if(input.equals("\t"))
                    rawCharacters.add("\"Tab character\"");
                else rawCharacters.add("\"" + input + "\"");
            }
            scanner.close();
            System.out.println(rawCharacters);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("This is an unexpected exception; the config file should exist when the ConfigReader is created.");
            System.exit(1);
        }
    }

}
