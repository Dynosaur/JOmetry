package rsrc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The LogWriter provides a safe way to write to a log file. The intended implementation is a static LogWriter for
 * your class to minimize the creation of LogWriters, so you can not waste lines for try/catch blocks creating
 * a BufferedWriter.
 *
 * @author Alejandro Doberenz
 * @since 11/2/2019
 * @version 1.0
 */
public class LogWriter  {

    private File currentFile;
    private BufferedWriter currentWriter;
    private boolean openConnections = false;
    private int tabLevel = 0;

    public LogWriter() { }

    public LogWriter(File file) {
        if(!file.exists()) {
            throw new IllegalArgumentException(String.format("%s does not exist!", file.getPath()));
        }
        currentFile = file;
    }

    public LogWriter(String path) {
        this(new File(path));
    }

    public void writeln(String message) {
        if(openConnections) {
            throw new IllegalArgumentException("There are still some open connections...");
        }
        if(currentFile == null) {
            throw new IllegalArgumentException("No file was specified to write to!");
        }
        if(!currentFile.exists()) {
            throw new IllegalArgumentException(String.format("The file %s could not be found.", currentFile.getPath()));
        }
        try {
            currentWriter = new BufferedWriter(new FileWriter(currentFile, true));
            openConnections = true;
            for(int i = 0; i < tabLevel; i++) {
                currentWriter.write("\t");
            }
            currentWriter.write(message + "\n");
            currentWriter.flush();
            currentWriter.close();
            openConnections = false;
        } catch(IOException e) {
            System.err.println("An exception occurred while attempting to create the BufferedWriter.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void writets(String message) {
        writeln(String.format("%s: %s", new java.util.Date().toString(), message));
    }

    public void setFile(File file) {
        if(!file.exists()) {
            throw new IllegalArgumentException(String.format("%s does not exist!", file.getPath()));
        }
        currentFile = file;
    }

    public void setFile(String path) {
        setFile(new File(path));
    }

    public void setTab(int tab) {
        if(tab < 0) {
            throw new IllegalArgumentException("The tab level cannot be set lower than 0.");
        }
        tabLevel = tab;
    }

}
