package rsrc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigReader {

    private File configFile;

    private ArrayList<String> fileLines = new ArrayList<>();

    public ConfigReader(File file) {
        if(!file.exists())
            throw new IllegalArgumentException("File \'" +  file.getPath() + " could not be found.");
        configFile = file;
    }

    void read() {
        int lineNumber = 0;
        try {
            Scanner scanner = new Scanner(configFile).useDelimiter("");
            while(scanner.hasNext()) {
                String input = scanner.next();
                if(input.equals("\n"))
                    System.out.println("\"Newline character\"");
                else
                    if(input.equals("\r"))
                        System.out.println("\"Carriage return character\"");
                    else
                        if(input.equals("\t"))
                            System.out.println("\"Tab character\"");
                        else
                            System.out.println("\"" + input + "\"");
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("This is an unexpected exception; the config file should exist when the ConfigReader is created.");
            System.exit(1);
        }
    }

}
