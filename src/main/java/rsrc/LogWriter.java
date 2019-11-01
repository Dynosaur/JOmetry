package rsrc;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class LogWriter extends PrintWriter  {

    public LogWriter(File file) throws IOException {
        super(new java.io.BufferedWriter(new java.io.FileWriter(file, true)));
    }

    public void timestamp(int tabs, String message) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < tabs; i++)
            builder.append("\t");
        builder.append(new java.util.Date().toString());
        builder.append(": ");
        builder.append(message);
        super.println(builder.toString());
    }

}
