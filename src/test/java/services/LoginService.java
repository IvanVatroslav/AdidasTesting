package services;

import objectpage.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class LoginService {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginService(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void logIn() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            prop.load(input);

            String loginMethod = prop.getProperty("loginMethod");
            String username = prop.getProperty("username_" + loginMethod);
            String password = prop.getProperty("password_" + loginMethod);

            if ("yahoo".equals(loginMethod)) {
                performYahooLogin(username, password);
            } else if ("google".equals(loginMethod)) {
                performGoogleLogin(username, password);
            } else {
                System.out.println("Invalid login method specified");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void performYahooLogin(String username, String password) {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openAccountPortal(); // Method to open the account portal modal

        AccountPortalModalPage accountPortalModal = new AccountPortalModalPage(driver);
        YahooLoginPage yahooLoginPage = accountPortalModal.clickYahooLogin();
        yahooLoginPage.enterUsername(username);
        yahooLoginPage.clickNextButtonLogin();
        yahooLoginPage.enterPassword(password);
        yahooLoginPage.clickLoginButton();
        yahooLoginPage.clickAuthButton();
    }

    private void performGoogleLogin(String username, String password) {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openAccountPortal(); // Method to open the account portal modal

        AccountPortalModalPage accountPortalModal = new AccountPortalModalPage(driver);
        GoogleLoginPage googleLoginPage = accountPortalModal.clickGoogleLogin();
        googleLoginPage.enterUsername(username);
        googleLoginPage.clickNextAfterUsername();
        googleLoginPage.enterPassword(password);
        googleLoginPage.clickNextAfterPassword();
    }
}
