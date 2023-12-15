import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Random;

public class EditBirthdaySteps{

    @Given("the user is logged in and on the main page")
    public void theUserIsLoggedInAndOnTheMainPage() {
        // Code to log in the user and verify the main page is displayed
    }

    @When("the user navigates to the account settings page")
    public void theUserNavigatesToTheAccountSettingsPage() {
        // Code to navigate to the account settings page
    }

    @When("the user changes the birth date to a random date")
    public void theUserChangesTheBirthDateToARandomDate() {
        // Generating a random date
        Random rand = new Random();
        int randomDay = rand.nextInt(28) + 1; // Assuming 28 to avoid month-end complexities
        int randomMonth = rand.nextInt(12) + 1;
        int randomYear = rand.nextInt(2022 - 1900) + 1900; // Random year between 1900 and 2022

        // Code to change the birth date on the account settings page
        // Example: driver.findElement(By.id("birthDay")).sendKeys(Integer.toString(randomDay));
        // Repeat for month and year
    }

    @Then("the new birth date should be saved and displayed")
    public void theNewBirthDateShouldBeSavedAndDisplayed() {
        // Code to assert that the birth date has been updated
        // This might involve checking the value of the date fields, or verifying a success message
    }


}
