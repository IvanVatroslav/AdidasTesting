import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.util.Random;

public class EditBirthdaySteps {
    //    private WebDriver driver; // WebDriver instance
//    private WebDriverWait wait; // Explicit wait
//    private FluentWait<WebDriver> fluentWait;
//
//    public EditBirthdaySteps(WebDriver driver) {
//        this.driver = TestBase.getDriver();
//        this.wait = TestBase.getWait();
//        this.fluentWait = TestBase.getFluentWait();
//
//    }
    private int randomDay;
    private int randomMonth;
    private int randomYear;


    @Given("the user is logged in and on the main page")
    public void theUserIsLoggedInAndOnTheMainPage() {
        TestBase.getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("side-panel-container")));

        // Wait for the account link to be clickable using FluentWait
        WebElement accountLink = TestBase.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cta-test-id-delete\"]/div/div[1]/a/span")));
        accountLink.click();

        // TestBase.getDriver().navigate().to("https://www.adidas.com/us/my-account");


    }

    @When("the user navigates to the account settings page")
    public void theUserNavigatesToTheAccountSettingsPage() {
        // Navigate to the account settings page
        TestBase.getDriver().findElement(By.id("ACCOUNT")).click(); // Replace with the actual ID or locator
        TestBase.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"gl-cta gl-cta--tertiary gl-vspace\"]"))).click(); // Replace with an actual element from account settings page
    }

    @When("the user changes the birth date to a random date")
    public void theUserChangesTheBirthDateToARandomDate() {
        Random rand = new Random();
        // Generate a random month between 1 and 12
        randomMonth = rand.nextInt(12) + 1; // months: 1-12

        // Generate a random day. Days range from 1-28/30/31 depending on the month
        if (randomMonth == 2) { // February
            // Check for leap year
            if (randomYear % 4 == 0 && (randomYear % 100 != 0 || randomYear % 400 == 0)) {
                randomDay = rand.nextInt(29) + 1; // 1-29 for leap year February
            } else {
                randomDay = rand.nextInt(28) + 1; // 1-28 for non-leap year February
            }
        } else if (randomMonth == 4 || randomMonth == 6 || randomMonth == 9 || randomMonth == 11) {
            randomDay = rand.nextInt(30) + 1; // 1-30 for April, June, September, November
        } else {
            randomDay = rand.nextInt(31) + 1; // 1-31 for all other months
        }

        // Generate a random year (example range: 1900 - current year)
        randomYear = rand.nextInt(LocalDate.now().getYear() - 1900) + 1900;
        // Enter the random date
        WebElement dayField = TestBase.getDriver().findElement(By.id("date-of-birth-day"));
        dayField.clear();
        dayField.sendKeys(Integer.toString(randomDay));

        WebElement monthField = TestBase.getDriver().findElement(By.id("date-of-birth-month"));
        monthField.clear();
        monthField.sendKeys(Integer.toString(randomMonth));

        WebElement yearField = TestBase.getDriver().findElement(By.id("date-of-birth-year"));
        yearField.clear();
        yearField.sendKeys(Integer.toString(randomYear));
        // Click the save or submit button
        TestBase.getDriver().findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div/div[2]/form/div[2]/button[1]/span[1]")).click(); // Replace with actual ID or locator
    }

    @Then("the new birth date should be saved and displayed")
    public void theNewBirthDateShouldBeSavedAndDisplayed() {

//        WebElement dateElement = TestBase.getDriver().findElement(By.xpath("//div[@data-auto-id=\"ma-dateOfBirth-overview\"]"));
//        String dateString = dateElement.getText();
//
//        // Parse the date
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date = LocalDate.parse(dateString, formatter);
//        Assert.assertEquals("Day does not match", randomDay, date.getDayOfMonth());
//        Assert.assertEquals("Month does not match", randomMonth, date.getMonthValue());
//        Assert.assertEquals("Year does not match", randomYear, date.getYear());
    }


}
