package config;

import utils.log.Log;

import java.io.IOException;
import java.util.Properties;

public class Config {

    private final Properties configFile;
    private static Config instance;
    private static String path;

    public Config() throws IOException {

        try {
            configFile = new Properties();
            if (path == null){
                setConfigFilePath("/config/configuration.properties");
            }
            configFile.load(this.getClass().getResourceAsStream(path));

        } catch (IOException e) {
            Log.error("Filed to load property file");
            throw e;
        }
    }

    public String getValue(String key) {
        return configFile.getProperty(key);
    }

    public static void setConfigFilePath(String configFilePath) {
        path = configFilePath;
        instance = null;
    }

    public static String getProperty(String key) throws IOException {
        if (instance == null)
            instance = new Config();
        return instance.getValue(key);
    }
}
