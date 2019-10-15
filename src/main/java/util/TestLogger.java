package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Date;

public class TestLogger {

    private File logFile;
    private PrintWriter out;

    public TestLogger(File directory) {
        logFile = new File(directory + "\\" + new Date().toString().replace(' ', '_').replace(':', ';') + ".log");
        if(!directory.exists()) directory.mkdirs();
        if(logFile.exists()) throw new IllegalArgumentException("Cannot create log file: File already exists: " + logFile.getName());
        try {
            logFile.createNewFile();
            out = new PrintWriter(logFile);
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public TestLogger(String directoryPath) {
        this(new File(directoryPath));
    }

    public void writeLine(String line) {
        out.println(line);
    }

    public void finish() {
        out.flush();
    }

    public static void main(String[] args) {
        TestLogger logger = new TestLogger("src\\test\\resources\\logs\\");
        logger.writeLine("Hello, World!");
        logger.writeLine("Goodbye, world!");
        logger.finish();
    }

}
