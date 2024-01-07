package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.BasePage;
import objectpage.PreferencesPage;
import objectpage.ProfilePage;
import objectpage.SidePanelPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import services.LoginService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Steps {

    private int randomDay;
    private int randomMonth;
    private int randomYear;

    private final WebDriver driver;
    private final Helper helper;
    private final SidePanelPage sidePanelPage;
    private final ProfilePage profilePage;
    private final PreferencesPage preferencesPage;
    private final LoginService loginService;

    public Steps(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper(driver);
        this.sidePanelPage = new SidePanelPage(driver);
        this.profilePage = new ProfilePage(driver);
        this.preferencesPage = new PreferencesPage(driver);
        this.loginService = new LoginService(driver);
    }

    @Given("the user is logged in and on the main page")
    public void userIsLoggedInAndOnMainPage() {
        helper.checkWebPage("https://www.adidas.com/us");
        loginService.logIn();
        sidePanelPage.clickAccountLink();
    }

    @When("the user navigates to the account settings page")
    public void userNavigatesToAccountSettingsPage() {

    }

    @When("the user changes the birth date to a random date")
    public void userChangesBirthDateToRandomDate() {
        profilePage.clickEditDetails();

        int[] randomDate = helper.getRandomDate();
        randomDay = randomDate[0];
        randomMonth = randomDate[1];
        randomYear = randomDate[2];
        profilePage.enterBirthDate(randomDay, randomMonth, randomYear);
        profilePage.clickSaveButton();
    }

    @Then("the new birth date should be saved and displayed")
    public void newBirthDateShouldBeSavedAndDisplayed() {
        BasePage.waitForModalInvisibility();
        String dateString = profilePage.getDisplayedDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Assert.assertEquals("Day does not match", randomDay, date.getDayOfMonth());
        Assert.assertEquals("Month does not match", randomMonth, date.getMonthValue());
        Assert.assertEquals("Year does not match", randomYear, date.getYear());
    }

    @When("the user goes to the preferences section")
    public void userGoesToPreferencesSection() {
        preferencesPage.clicksPreferencesButton();
    }

    @When("the user changes preferences")
    public void userChangesPreferences() {
        preferencesPage.randomProductCategoriesPreferencesClick();
        preferencesPage.randomProductInterestsPreferencesClick();
    }

    @Then("the new preferences should be saved and displayed")
    public void newPreferencesShouldBeSavedAndDisplayed() {
        preferencesPage.savePreferencesClick();
        preferencesPage.saveInterestsClick();
    }
}
