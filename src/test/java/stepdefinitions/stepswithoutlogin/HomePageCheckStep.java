package stepdefinitions.stepswithoutlogin;

import io.cucumber.java.en.Given;
import objectpage.pages.MainPage;
import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks;

public class HomePageCheckStep {


    private WebDriver driver;

    private MainPage mainPage;

    public HomePageCheckStep() {
        this.driver = Hooks.driver.get();
        this.mainPage = new MainPage(driver);
    }







    @Given("I am on the homepage")
    public void onHomepage() {
        mainPage.checkWebPage();
    }
}
