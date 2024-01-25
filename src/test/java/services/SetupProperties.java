package services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetupProperties {
    final static Logger log = LogManager.getLogger(SetupProperties.class);

    private static Properties configProperties = new Properties();
    private static Properties extentProperties = new Properties();

    static {
        try (InputStream input = SetupProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                log.error("Sorry, unable to find config.properties");
            }
            configProperties.load(input);
        } catch (IOException e) {
            log.error("Failed to load properties file: " + e.getMessage(), e);
        }

        try (InputStream input = SetupProperties.class.getClassLoader().getResourceAsStream("extent.properties")) {
            if (input == null) {
                log.error("Sorry, unable to find extent.properties");
            }
            extentProperties.load(input);
        } catch (IOException e) {
            log.error("Failed to load properties file: " + e.getMessage(), e);
        }
    }
    public static String getUsernameYahoo() {
        return configProperties.getProperty("username_yahoo");
    }

    public static String getUsernameGoogle() {
        return configProperties.getProperty("username_google");
    }

    public static String getPasswordYahoo() {
        return configProperties.getProperty("password_yahoo");
    }

    public static String getPasswordGoogle() {
        return configProperties.getProperty("password_google");
    }

    public static String getMainUrl() {
        return configProperties.getProperty("main_url");
    }

    public static String getLoginMethod() {
        return configProperties.getProperty("loginMethod");
    }


    public static String getScreenshotDir() {
        return extentProperties.getProperty("screenshot.dir");
    }
}
