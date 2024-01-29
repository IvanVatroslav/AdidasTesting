package stepdefinitions.stepswithlogin;

import io.cucumber.java.en.Given;
import objectpage.pages.MainPage;
import org.openqa.selenium.WebDriver;
import services.LoginService;
import stepdefinitions.Hooks;

public class LoginSteps {
    private final MainPage mainPage;
    private final LoginService loginService;
    private final WebDriver driver;


    public LoginSteps() {
            this.driver = Hooks.driver.get();
            this.mainPage = new MainPage(driver);
            this.loginService = new LoginService(driver);

        }

        @Given("the user is logged in and on the main page")
        public void userIsLoggedInAndOnMainPage() {
            mainPage.checkWebPage();
            loginService.logIn();
        }
    }

