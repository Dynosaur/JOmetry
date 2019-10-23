package rsrc;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ConfigReader {


    private ArrayList<String> lines;

    public ConfigReader() {
        lines = new ArrayList<>();
    }

    void readFile(File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while(in.ready()) lines.add(in.readLine());
            lines.add(in.readLine());
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        ConfigReader reader = new ConfigReader();
        reader.readFile(new File("jOmetry.config"));
        System.out.println(reader.lines);
    }

}
