package rsrc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author  Alejandro Doberenz
 * @version 2.0
 * @since   9/19/2019
 *
 * A separate class to load in resources from files.
 * It is imperative to load resources before using features of the program.
 */
public class Resources {

    public interface ReaderRunnable {

        void run(int readData);

    }

    private static boolean hasLoadedResources = false;

    public static final ArrayList<Character> ALPHABET_LIST = new ArrayList<>(0);
    public static final ArrayList<Character> LEGAL_TEXT_LIST = new ArrayList<>(0);

    /**
     * Begin to load all resources. This must be called before all other operations begin.
     */
    public static void loadResources() {
        if(hasLoadedResources) return;
        loadGenericFile("src/main/resources/alphabet", ALPHABET_LIST);
        loadGenericFile("src/main/resources/legal_text", LEGAL_TEXT_LIST);
    }

    public static void loadFile(File targetFile, ReaderRunnable action) {
        try {
            FileReader reader = new FileReader(targetFile);
            try {
                while(reader.ready())
                    action.run(reader.read());
                try {
                    reader.close();
                } catch(IOException e) { e.printStackTrace(); System.exit(1); }
            } catch(IOException e) { e.printStackTrace(); System.exit(1); }
        } catch(FileNotFoundException e) { e.printStackTrace(); System.exit(1); }
    }

    public static void loadFile(String targetPath, ReaderRunnable action) {
        loadFile(new File(targetPath), action);
    }

    public static void loadGenericFile(String path, ArrayList<Character> characterArray) {
        ReaderRunnable action = readData -> {
            if(Character.isWhitespace(readData)) return;
            characterArray.add((char) readData);
        };
        loadFile(path, action);
    }

}
