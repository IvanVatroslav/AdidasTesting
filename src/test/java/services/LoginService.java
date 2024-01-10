package services;

import objectpage.nonpages.components.Header;
import objectpage.nonpages.modals.LoginModal;
import objectpage.pages.login.GoogleLoginPage;
import objectpage.pages.login.YahooLoginPage;
import org.openqa.selenium.WebDriver;

public class LoginService {
    private WebDriver driver;

    public LoginService(WebDriver driver) {
        this.driver = driver;
    }

    public void logIn() {
        String loginMethod = SetupProperties.getLoginMethod();
        String username;
        String password;

        LoginModal loginModal = new LoginModal(driver);
        Header header = new Header(driver);
        header.getAccountPortalTrigger().click();

        if ("yahoo".equals(loginMethod)) {
            username = SetupProperties.getUsernameYahoo();
            password = SetupProperties.getPasswordYahoo();
            performYahooLogin(loginModal, username, password);
        } else if ("google".equals(loginMethod)) {
            username = SetupProperties.getUsernameGoogle();
            password = SetupProperties.getPasswordGoogle();
            performGoogleLogin(loginModal, username, password);
        } else {
            System.out.println("Invalid login method specified");
        }
    }

    private void performYahooLogin(LoginModal loginModal, String username, String password) {
        YahooLoginPage yahooLoginPage = loginModal.chooseYahooLogin();
        yahooLoginPage.enterUsername(username);
        yahooLoginPage.clickNextButtonLogin();
        yahooLoginPage.enterPassword(password);
        yahooLoginPage.clickLoginButton();
        yahooLoginPage.clickAuthButton();
    }

    private void performGoogleLogin(LoginModal loginModal, String username, String password) {
        GoogleLoginPage googleLoginPage = loginModal.chooseGoogleLogin();
        googleLoginPage.enterUsername(username);
        googleLoginPage.clickNextAfterUsername();
        googleLoginPage.enterPassword(password);
        googleLoginPage.clickNextAfterPassword();
    }
}
