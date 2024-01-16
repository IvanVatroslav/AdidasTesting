package stepdefinitions.stepswithlogin.accountsteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.pages.account.PreferencesPage;
import objectpage.pages.account.ProfilePage;
import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks;

public class PreferencesSteps {


    private final ProfilePage profilePage;
    private final PreferencesPage preferencesPage;
    private WebDriver driver;

    public PreferencesSteps() {
        this.driver = Hooks.driver.get();
        this.profilePage = new ProfilePage(driver);
        this.preferencesPage = new PreferencesPage(driver);
    }


    @When("the user goes to the preferences section")
    public void userGoesToPreferencesSection() {
        profilePage.clicksPreferencesButton();
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
