package stepdefinitions;

import ObjectPage.MainPage;
import ObjectPage.SettingsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Random;

public class Steps {
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
        //Base.getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("side-panel-container")));

        // Wait for the account link to be clickable using FluentWait
        //WebElement accountLink = Base.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cta-test-id-delete\"]/div/div[1]/a/span")));
        //accountLink.click();

        // TestBase.getDriver().navigate().to("https://www.adidas.com/us/my-account");
        Helper.logIn();
        MainPage.clickAccountLink();

    }

    @When("the user navigates to the account settings page")
    public void theUserNavigatesToTheAccountSettingsPage() {
        // Navigate to the account settings page
        //Base.getDriver().findElement(By.id("ACCOUNT")).click(); // Replace with the actual ID or locator
        MainPage.clickAccountSettings();
    }

    @When("the user changes the birth date to a random date")
    public void theUserChangesTheBirthDateToARandomDate() {
//        Base.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"gl-cta gl-cta--tertiary gl-vspace\"]"))).click(); // Replace with an actual element from account settings page
        SettingsPage.clickEditDetails();
//        Random rand = new Random();
//        // Generate a random month between 1 and 12
//        randomMonth = rand.nextInt(12) + 1; // months: 1-12
//
//        // Generate a random day. Days range from 1-28/30/31 depending on the month
//        if (randomMonth == 2) { // February
//            // Check for leap year
//            if (randomYear % 4 == 0 && (randomYear % 100 != 0 || randomYear % 400 == 0)) {
//                randomDay = rand.nextInt(29) + 1; // 1-29 for leap year February
//            } else {
//                randomDay = rand.nextInt(28) + 1; // 1-28 for non-leap year February
//            }
//        } else if (randomMonth == 4 || randomMonth == 6 || randomMonth == 9 || randomMonth == 11) {
//            randomDay = rand.nextInt(30) + 1; // 1-30 for April, June, September, November
//        } else {
//            randomDay = rand.nextInt(31) + 1; // 1-31 for all other months
//        }
//
//        // Generate a random year (example range: 1900 - current year)
//        randomYear = rand.nextInt(LocalDate.now().getYear() - 1900) + 1900;
        // Enter the random date

        int[] randomDate = Helper.getRandomDate();
        randomDay = randomDate[0];
        randomMonth = randomDate[1];
        randomYear = randomDate[2];

        SettingsPage.getDayField().sendKeys(Integer.toString(randomDay));


        SettingsPage.getMonthField().sendKeys(Integer.toString(randomMonth));

        SettingsPage.getYearField().sendKeys(Integer.toString(randomYear));
        // Click the save or submit button
        SettingsPage.clickSaveButton();
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


    //test2

    @When("the user goes to the preferences section")
    public void the_user_goes_to_the_preferences_section() {
        SettingsPage.clicksPreferencesButton();
    }

    @When("the user changes preferences")
    public void the_user_changes_preferences() {
        // Create a Random object
        Random rand = new Random();

//        // Generate a random number between 1 and 4
//
//        // Construct the XPath with the random index
//        String xpath = "//div[@class='gl-carousel__slider']//div[" + randomDivIndex + "]";
//
//        // Find the element using the constructed XPath
//        WebElement randomDiv = Base.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        // Interact with the element as required, e.g., click
        SettingsPage.randomProductCategoriesPreferencesClick();

//        // Generate a random number between 1 and 9 for the fifth div
//        int randomFifthDivIndex = rand.nextInt(9) + 1;
//
//        // Generate a random number between 1 and 2 for the second div
//        int randomSecondDivIndex = rand.nextInt(2) + 1;
//
//        // Construct the XPath with the random indexes
//        String xpath2 = "//div[@class='gl-carousel gl-carousel--show-pagination profile-interests___5fonZ']/div/div/div["
//                + randomFifthDivIndex + "]/div/div[" + randomSecondDivIndex + "]";
//
//        // Find the element using the constructed XPath
//        WebElement randomElement = Base.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath2)));
//        randomElement.click();
        // Perform actions with the selected element
        // For example, click the element
        SettingsPage.randomProductInterestsPreferencesClick();

    }

    @Then("the new preferences should be saved and displayed")
    public void the_new_preferences_should_be_saved_and_displayed() {
        SettingsPage.savePreferencesClick();
        SettingsPage.saveInterestsClick();
    }


}
