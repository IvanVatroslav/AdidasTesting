package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import objectpage.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.Helper;
import services.LoginService;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StepsYZ {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Helper helper;
    private final HeaderPage headerPage;
    private final SearchPage searchPage;
    private final ProfilePage profilePage;
    private final LoginService loginService;
    private List<Map<String, String>> storedAddresses;

    public StepsYZ(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.helper = new Helper(driver);
        this.headerPage = new HeaderPage(driver);
        this.searchPage = new SearchPage(driver);
        this.profilePage = new ProfilePage(driver);
        this.loginService = new LoginService(driver);
    }

    @Given("I am on the homepage")
    public void onHomepage() {
        helper.checkWebPage("https://www.adidas.com/us");
        loginService.logIn();
    }

    @Then("I verify the visibility and correctness of each item in the navigation menu")
    public void verifyVisibilityAndCorrectnessOfNavigationMenuItems(DataTable dataTable) {
        List<String> expectedMenuItems = dataTable.asList(String.class);
        helper.testNavigationMenuItems(expectedMenuItems);
    }

    @When("I hover over the Men's section in the main menu")
    public void hoverOverMensSectionInMainMenu() {
        headerPage.hoverOverMensSection();
    }

    @Then("I should see the dropdown with sub-categories")
    public void verifyDropdownWithSubCategoriesIsVisible() {
        WebElement dropdown = headerPage.getHeaderFlyout();
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        assertTrue("Dropdown with sub-categories is not visible", dropdown.isDisplayed());
    }

    @Then("I verify the following sub-categories are correct")
    public void verifyFollowingSubCategoriesAreCorrect(List<String> subcategoryTexts) {
        for (String expectedText : subcategoryTexts) {
            WebElement subCategoryElement = headerPage.getMenSubcategoryElement(expectedText);
            wait.until(ExpectedConditions.visibilityOf(subCategoryElement));
            String actualText = subCategoryElement.getText();
            assertEquals("Expected text not found: " + expectedText, expectedText, actualText);
        }
    }

    @When("I search for {string}")
    public void searchFor(String string) {
        WebElement searchBox = headerPage.getSearchTextBox();
        searchBox.sendKeys(string + Keys.ENTER);
    }

    @Then("the search results page should open")
    public void verifySearchResultsPageOpens() {
        String expectedUrlPattern = "https://www.adidas.com/us/search?q=";
        String currentUrl = driver.getCurrentUrl();
        assertTrue("The user is not on a search page", currentUrl.startsWith(expectedUrlPattern));
    }

    @Then("the list of products should not be empty")
    public void verifyListOfProductsIsNotEmpty() {
        List<WebElement> productContainers = SearchPage.getSearchResults();
        assertFalse("Product list is empty", productContainers.isEmpty());
    }

    @Then("all products should have the name {string}")
    public void verifyAllProductsHaveName(String string) {
        List<WebElement> productContainers = SearchPage.getSearchResults();
        for (WebElement container : productContainers) {
            WebElement productNameElement = container.findElement((By) searchPage.getProductNameLocator());
            wait.until(ExpectedConditions.visibilityOf(productNameElement));
            String actualProductName = productNameElement.getText().toLowerCase();
            assertTrue("Product name does not match: " + actualProductName, actualProductName.contains(string.toLowerCase()));
        }
    }

    @When("I search for an invalid keyword")
    public void searchForInvalidKeyword() {
        SearchPage.searchFor("invalid_keyword");
    }

    @Then("a no results page should be displayed")
    public void verifyNoResultsPageIsDisplayed() {
        boolean elementExists = helper.doesElementExist(searchPage.getNoResultsMessageLocator());
        assertTrue("No results element does not exist", elementExists);
    }

    @When("the user navigates to the address book page")
    public void navigateToAddressBookPage() {
        profilePage.navigateToAddressBookPage();
    }

    @And("the user removes any old addresses")
    public void removeAnyOldAddresses() {
        helper.removeAllAddresses();
    }

    @And("the user adds new addresses")
    public void addNewAddresses(DataTable addressTable) {
        storedAddresses = addressTable.asMaps(String.class, String.class);
        for (Map<String, String> address : storedAddresses) {
            helper.addNewAddress(address.get("FirstName"), address.get("LastName"), address.get("Address"), address.get("City"), address.get("State"), address.get("Zip"), address.get("Phone"));
        }
    }

    @Then("the new addresses should be saved and displayed in the address book")
    public void verifyNewAddressesAreSavedAndDisplayedInAddressBook() {
        helper.assertAddresses(storedAddresses);
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
            int day = Integer.parseInt(dateParts[1]);
            int month = Integer.parseInt(dateParts[2]);
            int year = Integer.parseInt(dateParts[0]);

            WebElement firstNameField = profilePage.getFirstNameField();
            Helper.replaceText(firstNameField, firstName);

            WebElement lastNameField = profilePage.getLastNameField();
            Helper.replaceText(lastNameField, lastName);

            profilePage.enterBirthDate(day, month, year);


            switch (outcome) {
                case "save and display":
                    profilePage.clickSaveButton();
                    BasePage.waitForModalInvisibility();
                    String actualName = profilePage.getDisplayedName();
                    String actualDate = profilePage.getDisplayedDate();

                    assertEquals("Displayed name should match expected name",
                            name.toLowerCase(),
                            actualName.toLowerCase());
                    assertEquals("Displayed date should match expected date", date, actualDate);
                    break;
                case "reject":
                    assertTrue(ProfilePage.isErrorMessageDisplayed());
                    break;
            }
        }
    }
}
