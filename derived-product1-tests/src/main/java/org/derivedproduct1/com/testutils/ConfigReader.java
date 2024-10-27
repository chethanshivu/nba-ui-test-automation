package org.derivedproduct1.com.testutils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigReader {
    private static final String CONFIG_FILE = "config.yaml";
    private static Map<String, Object> config;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream(CONFIG_FILE);
            config = yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error loading config file: " + e.getMessage());
        }
    }

    public static String getBrowserType() {
        Map<String, Object> browserConfig = (Map<String, Object>) config.get("browser");
        return (String) browserConfig.get("type");
    }

    public static String getBrowserMode() {
        Map<String, Object> browserConfig = (Map<String, Object>) config.get("browser");
        return (String) browserConfig.get("mode");
    }

    public static int getImplicitWait() {
        Map<String, Object> browserConfig = (Map<String, Object>) config.get("browser");
        return (int) browserConfig.get("implicit_wait");
    }

    public static int getPageLoadWait() {
        Map<String, Object> browserConfig = (Map<String, Object>) config.get("browser");
        return (int) browserConfig.get("page_load_wait");
    }
}