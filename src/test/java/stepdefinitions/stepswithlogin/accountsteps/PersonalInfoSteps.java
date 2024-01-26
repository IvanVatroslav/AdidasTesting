package stepdefinitions.stepswithlogin.accountsteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.nonpages.components.SidePanel;
import objectpage.nonpages.modals.PersonalInfoModal;
import objectpage.pages.account.MyAccountPage;
import objectpage.pages.account.ProfilePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.Helper;
import stepdefinitions.Hooks;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonalInfoSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Helper helper;
    private final ProfilePage profilePage;
    private final SidePanel sidePanel;
    private final MyAccountPage myAccountPage;
    private final PersonalInfoModal personalInfoModal;
    private int randomDay;
    private int randomMonth;
    private int randomYear;
    public PersonalInfoSteps() {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();
        this.helper = new Helper();
        this.profilePage = new ProfilePage(driver);
        this.sidePanel = new SidePanel(driver);
        this.myAccountPage = new MyAccountPage(driver);
        this.personalInfoModal = new PersonalInfoModal(driver);
    }





    @When("the user changes the birth date to a random date")
    public void userChangesBirthDateToRandomDate() {
        int[] randomDate = helper.getRandomDate();
        randomDay = randomDate[0];
        randomMonth = randomDate[1];
        randomYear = randomDate[2];

        profilePage.setRandomBirthDateAndSave(randomDay, randomMonth, randomYear);
    }

    @Then("the new birth date should be saved and displayed")
    public void newBirthDateShouldBeSavedAndDisplayed() {

        LocalDate displayedDate = profilePage.getDisplayedBirthDate();

        Assert.assertEquals("Day does not match", randomDay, displayedDate.getDayOfMonth());
        Assert.assertEquals("Month does not match", randomMonth, displayedDate.getMonthValue());
        Assert.assertEquals("Year does not match", randomYear, displayedDate.getYear());
    }

//i'm unsure how exactly to Pageobjectify this /////// implement helper in BaseClass*

    @When("the user attempts to change birth dates and names according to the following data")
    public void theUserAttemptsToChangeBirthDatesAndNames(DataTable dataTable) {
        profilePage.clickEditDetails();
        List<Map<String, String>> entries = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> entry : entries) {
            personalInfoModal.changeBirthDateAndName(entry);

            if ("save and display".equals(entry.get("Outcome"))) {
                String actualName = profilePage.getDisplayedName();
                String actualDate = profilePage.getDisplayedDate();
                assertEquals("Displayed name should match expected name", entry.get("Name").toLowerCase(), actualName.toLowerCase());
                assertEquals("Displayed date should match expected date", entry.get("Date"), actualDate);
            } else if ("reject".equals(entry.get("Outcome"))) {
                assertTrue("Expected an error message", personalInfoModal.isErrorMessageDisplayed());
            }
        }
    }
}
