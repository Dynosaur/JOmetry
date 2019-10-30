package rsrc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigurationFile {

    private File sourceFile;

    private boolean fileHasBeenRead = false;

    private ArrayList<Configuration> fileConfigurations = new ArrayList<>();

    public ConfigurationFile(File file) throws IOException {
        if(!file.exists())
            throw new IOException("File could not be found.");
        if(!file.getName().endsWith(".config"))
            throw new IOException("File is not a config file.");
        sourceFile = file;
    }

    public ConfigurationFile(String filePath) throws IOException {
        this(new File(filePath));
    }

    private void parseConfigurations() {
        try {
            Scanner in = new Scanner(sourceFile).useDelimiter("");
            ArrayList<String> fileCharacters = new ArrayList<>();

            while(in.hasNext())
                fileCharacters.add(in.next());
            in.close();

            ArrayList<String> parsedKeywords = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < fileCharacters.size(); i++) {
                String token = fileCharacters.get(i);
                if(i == fileCharacters.size() - 1) {
                    builder.append(token);
                    parsedKeywords.add(builder.toString());
                    continue;
                }
                if(token.equals("\r") || token.equals("\n")) {
                    if(builder.length() == 0)
                        continue;
                    parsedKeywords.add(builder.toString());
                    builder = new StringBuilder();
                } else
                    builder.append(token);
            }

            Configuration currentConfig = null;
            for(String keyword : parsedKeywords) {
                if(!keyword.startsWith("\t") && keyword.endsWith(":")) {
                    currentConfig = new Configuration(keyword.replace(":", ""));
                    fileConfigurations.add(currentConfig);
                } else if(keyword.startsWith("\t") && currentConfig != null)
                    currentConfig.append(keyword);
            }

            for(Configuration config : fileConfigurations) {
                for(Configuration configCheck : fileConfigurations) {
                    if(config.equals(configCheck))
                        continue;
                    if(config.getValue().contains(String.format("\\%s\\", configCheck.getName())))
                        config.setValue(config.getValue().replace(String.format("\\%s\\", configCheck.getName()), configCheck.getValue()));
                }
            }

            fileHasBeenRead = true;
        } catch(FileNotFoundException e) {
            System.err.println("Unexpected FileNotFoundException has occurred.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getConfiguration(String configName) {
        if(!fileHasBeenRead)
            parseConfigurations();
        for(Configuration config : fileConfigurations)
            if(config.getName().equals(configName))
                return config.getValue();
        throw new RuntimeException(String.format("No configuration with the name %s.", configName));
    }

}
