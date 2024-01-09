package services;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetupProperties {
    final static Logger log = Logger.getLogger(SetupProperties.class);

    private static Properties properties = new Properties();

    static {
        try (InputStream input = SetupProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                log.error("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            log.error("Failed to load properties file: " + e.getMessage(), e);
        }
    }

    public static String getUsernameYahoo() {
        return properties.getProperty("username_yahoo");
    }

    public static String getUsernameGoogle() {
        return properties.getProperty("username_google");
    }

    public static String getPasswordYahoo() {
        return properties.getProperty("password_yahoo");
    }

    public static String getPasswordGoogle() {
        return properties.getProperty("password_google");
    }

    public static String getMainUrl() {
        return properties.getProperty("main_url");
    }

    public static String getLoginMethod() {
        return properties.getProperty("loginMethod");
    }
}
