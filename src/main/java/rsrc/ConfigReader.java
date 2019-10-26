package rsrc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigReader {

    private File configFile;

    private ArrayList<String> fileLines;

    public ConfigReader(File file) {
        if(!file.exists()) throw new IllegalArgumentException("File \'" +  file.getPath() + " could not be found.");
        configFile = file;
        fileLines = new ArrayList<>();
    }

    void read() {
        try {
            Scanner scanner = new Scanner(configFile);
            while(scanner.hasNext())
                fileLines.add(scanner.next());
            scanner.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("This is an unexpected exception; the config file should exist when the ConfigReader is created.");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        ConfigReader reader = new ConfigReader(new File("jOmetry.config"));
        reader.read();
        System.out.println(reader.fileLines);
    }

}
