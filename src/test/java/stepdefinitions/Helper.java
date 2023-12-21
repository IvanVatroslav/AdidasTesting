package stepdefinitions;

import ObjectPage.Base;
import ObjectPage.MainPage;
import ObjectPage.YahooPage;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;

public class Helper {

    private static int randomMonth;
    private static int randomDay;
    private static int randomYear;

    public static int[] getRandomDate() {
        Random rand = new Random();
        // Generate a random month between 1 and 12
        randomMonth = rand.nextInt(12) + 1; // months: 1-12

        // Generate a random day. Days range from 1-28/30/31 depending on the month
        if (randomMonth == 2) { // February
            // Check for leap year
            if (randomYear % 4 == 0 && (randomYear % 100 != 0 || randomYear % 400 == 0)) {
                randomDay = rand.nextInt(29) + 1; // 1-29 for leap year February
            } else {
                randomDay = rand.nextInt(28) + 1; // 1-28 for non-leap year February
            }
        } else if (randomMonth == 4 || randomMonth == 6 || randomMonth == 9 || randomMonth == 11) {
            randomDay = rand.nextInt(30) + 1; // 1-30 for April, June, September, November
        } else {
            randomDay = rand.nextInt(31) + 1; // 1-31 for all other months
        }

        // Generate a random year (example range: 1900 - current year)
        randomYear = rand.nextInt(LocalDate.now().getYear() - 1900) + 1900;
    return new int[]{randomDay, randomMonth, randomYear};
    }
    public static void logIn() {
        Properties prop = new Properties();
        try (InputStream input = Helper.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // Load the properties file
            prop.load(input);

            // Get username and password from properties file
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            // Perform the login process using the username and password
            Base.getWait().until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            MainPage.acceptCookies();
            MainPage.openCustomerInfo();
            MainPage.clickYahooButton();
            YahooPage.getLoginTextBox().sendKeys(username);
            YahooPage.clickNextButtonLogin();
            YahooPage.getPasswordTextBox().sendKeys(password);
            YahooPage.clickLoginButton();
            YahooPage.clickAuthButton();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}