package stepdefinitions;

import ObjectPage.Base;
import ObjectPage.MainPage;
import ObjectPage.SettingsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Steps {

    private int randomDay;
    private int randomMonth;
    private int randomYear;

    private static WebDriver driver = Base.getDriver();
    Helper helper = new Helper(driver);

    @Given("the user is logged in and on the main page")
    public void theUserIsLoggedInAndOnTheMainPage() {
        helper.checkWebPage("https://www.adidas.com/us");
        helper.logIn();
        MainPage.clickAccountLink();

    }

    @When("the user navigates to the account settings page")
    public void theUserNavigatesToTheAccountSettingsPage() {
    MainPage.clickAccountSettings();
    }

    @When("the user changes the birth date to a random date")
    public void theUserChangesTheBirthDateToARandomDate() {
        SettingsPage.clickEditDetails();

        int[] randomDate = helper.getRandomDate();
        randomDay = randomDate[0];
        randomMonth = randomDate[1];
        randomYear = randomDate[2];
        SettingsPage.getDayField().sendKeys(Integer.toString(randomDay));
        SettingsPage.getMonthField().sendKeys(Integer.toString(randomMonth));
        SettingsPage.getYearField().sendKeys(Integer.toString(randomYear));
        SettingsPage.clickSaveButton();
    }

    @Then("the new birth date should be saved and displayed")
    public void theNewBirthDateShouldBeSavedAndDisplayed() {
        // Wait for the modal to disappear
        Base.getWait().until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class='gl-modal__main']")));

        WebElement dateElement = Base.getDriver().findElement(By.xpath("//div[@data-auto-id='ma-dateOfBirth-overview']"));
        String dateString = dateElement.getText();

        // Split the date string and rearrange it into a standard format
        String[] dateParts = dateString.split("-");
        String reformattedDate = dateParts[0] + "-" + dateParts[2] + "-" + dateParts[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(reformattedDate, formatter);

        Assert.assertEquals("Day does not match", randomDay, date.getDayOfMonth());
        Assert.assertEquals("Month does not match", randomMonth, date.getMonthValue());
        Assert.assertEquals("Year does not match", randomYear, date.getYear());
    }




    //test2

    @When("the user goes to the preferences section")
    public void the_user_goes_to_the_preferences_section() {
        SettingsPage.clicksPreferencesButton();
    }

    @When("the user changes preferences")
    public void the_user_changes_preferences() {

        SettingsPage.randomProductCategoriesPreferencesClick();
        SettingsPage.randomProductInterestsPreferencesClick();

    }

    @Then("the new preferences should be saved and displayed")
    public void the_new_preferences_should_be_saved_and_displayed() {
        SettingsPage.savePreferencesClick();
        SettingsPage.saveInterestsClick();
    }


}
