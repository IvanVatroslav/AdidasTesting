package stepdefinitions.stepswithlogin.accountsteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import objectpage.nonpages.components.SidePanel;
import objectpage.nonpages.modals.PersonalInfoModal;
import objectpage.pages.account.MyAccountPage;
import objectpage.pages.account.ProfilePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        profilePage.clickEditDetails();

        int[] randomDate = helper.getRandomDate();
        randomDay = randomDate[0];
        randomMonth = randomDate[1];
        randomYear = randomDate[2];
        personalInfoModal.enterBirthDate(randomDay, randomMonth, randomYear);
        personalInfoModal.clickSaveButton();
    }

    @Then("the new birth date should be saved and displayed")
    public void newBirthDateShouldBeSavedAndDisplayed() {
        profilePage.waitForModalInvisibility();
        String dateString = profilePage.getDisplayedDate();

        String[] dateParts = dateString.split("-");
        int year = Integer.parseInt(dateParts[0].trim());
        int month = Integer.parseInt(dateParts[2].trim());
        int day = Integer.parseInt(dateParts[1].trim());

        LocalDate date = LocalDate.of(year, month, day);

        Assert.assertEquals("Day does not match", randomDay, date.getDayOfMonth());
        Assert.assertEquals("Month does not match", randomMonth, date.getMonthValue());
        Assert.assertEquals("Year does not match", randomYear, date.getYear());
    }


    @SneakyThrows
    @When("the user attempts to change birth dates and names according to the following data")
    public void theUserAttemptsToChangeBirthDatesAndNames(DataTable dataTable) {
        profilePage.clickEditDetails();

        List<Map<String, String>> entries = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> entry : entries) {
            String date = entry.get("Date");
            String name = entry.get("Name");
            String outcome = entry.get("Outcome");

            String[] nameParts = name.split(" ", 2);
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            String[] dateParts = date.split("-");
            int day = Integer.parseInt(dateParts[1].trim());
            int month = Integer.parseInt(dateParts[2].trim());
            int year = Integer.parseInt(dateParts[0].trim());

            WebElement firstNameField = personalInfoModal.getFirstNameField();
            helper.replaceText(firstNameField, firstName);

            WebElement lastNameField = personalInfoModal.getLastNameField();
            helper.replaceText(lastNameField, lastName);

            personalInfoModal.enterBirthDate(day, month, year);

            switch (outcome) {
                case "save and display":
                    personalInfoModal.clickSaveButton();
                    profilePage.waitForModalInvisibility();
                    String actualName = profilePage.getDisplayedName();
                    String actualDate = profilePage.getDisplayedDate();

                    assertEquals("Displayed name should match expected name",
                            name.toLowerCase(),
                            actualName.toLowerCase());
                    assertEquals("Displayed date should match expected date", date, actualDate);
                    break;
                case "reject":
                    assertTrue(personalInfoModal.isErrorMessageDisplayed());
                    break;
            }
        }
    }

}
