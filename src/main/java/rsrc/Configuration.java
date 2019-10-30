package rsrc;

public class Configuration {

    private String configName, configValue;

    public Configuration(String name) {
        configName = name;
        configValue = "";
    }

    public void append(String appendedValue) {
        configValue += appendedValue.replace("\t", "");
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", configName, configValue);
    }

    public String getName() {
        return configName;
    }

    public String getValue() {
        return configValue;
    }

    public void setValue(String newValue) {
        configValue = newValue;
    }

}
