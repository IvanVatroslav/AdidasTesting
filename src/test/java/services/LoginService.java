package services;

import objectpage.login.GoogleLoginPage;
import objectpage.login.YahooLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginService {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginService(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void logIn() {
        String loginMethod = SetupProperties.getLoginMethod();
        String username;
        String password;

        if ("yahoo".equals(loginMethod)) {
            username = SetupProperties.getUsernameYahoo();
            password = SetupProperties.getPasswordYahoo();
            performYahooLogin(username, password);
        } else if ("google".equals(loginMethod)) {
            username = SetupProperties.getUsernameGoogle();
            password = SetupProperties.getPasswordGoogle();
            performGoogleLogin(username, password);
        } else {
            System.out.println("Invalid login method specified");
        }
    }

    private void performYahooLogin(String username, String password) {
        ConcreteHeaderPage headerPage = new ConcreteHeaderPage(driver); // This should be a concrete subclass of HeaderPage
        headerPage.openAccountPortal();

        ConcreteAccountPortalModalPage accountPortalModal = new ConcreteAccountPortalModalPage(driver); // This should be a concrete subclass of AccountPortalModalPage
        YahooLoginPage yahooLoginPage = accountPortalModal.clickYahooLogin();
        yahooLoginPage.enterUsername(username);
        yahooLoginPage.clickNextButtonLogin();
        yahooLoginPage.enterPassword(password);
        yahooLoginPage.clickLoginButton();
        yahooLoginPage.clickAuthButton();
    }

    private void performGoogleLogin(String username, String password) {
        ConcreteHeaderPage headerPage = new ConcreteHeaderPage(driver); // This should be a concrete subclass of HeaderPage
        headerPage.openAccountPortal();

        ConcreteAccountPortalModalPage accountPortalModal = new ConcreteAccountPortalModalPage(driver); // This should be a concrete subclass of AccountPortalModalPage
        GoogleLoginPage googleLoginPage = accountPortalModal.clickGoogleLogin();
        googleLoginPage.enterUsername(username);
        googleLoginPage.clickNextAfterUsername();
        googleLoginPage.enterPassword(password);
        googleLoginPage.clickNextAfterPassword();
    }
}
