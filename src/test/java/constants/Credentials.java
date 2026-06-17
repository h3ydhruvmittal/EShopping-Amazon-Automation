package constants;

import utils.ConfigReader;

public enum Credentials {
    USERNAME(ConfigReader.getProperty("amazon.username")),
    PASSWORD(ConfigReader.getProperty("amazon.password"));

    private final String value;

    Credentials(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
