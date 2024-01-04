package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import objectpage.Base;
import objectpage.Header;
import objectpage.SearchPage;
import objectpage.SettingsPage;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StepsYZ {
    //YZT1
    WebDriver driver = Base.getDriver();
    WebDriverWait wait = Base.getWait();
    Helper helper = new Helper(driver);
    Header header = new Header(driver);
    private List<Map<String, String>> storedAddresses;


    @Given("I am on the homepage")
    public void onHomepage() {
        helper.checkWebPage("https://www.adidas.com/us");
    }

    @Then("I verify the visibility and correctness of each item in the navigation menu")
    public void verifyVisibilityAndCorrectnessOfNavigationMenuItems(DataTable dataTable) {
        List<String> expectedMenuItems = dataTable.asList(String.class);
        helper.testNavigationMenuItems(expectedMenuItems);
    }

    // YZT2

    @When("I hover over the Men's section in the main menu")
    public void hoverOverMensSectionInMainMenu() {
        header.hoverOverMensSection();
    }

    @Then("I should see the dropdown with sub-categories")
    public void verifyDropdownWithSubCategoriesIsVisible() {
        WebElement dropdown = header.getHeaderFlyout();
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        assertTrue("Dropdown with sub-categories is not visible", dropdown.isDisplayed());
    }

    @Then("I verify the following sub-categories are correct")
    public void verifyFollowingSubCategoriesAreCorrect(List<String> subcategoryTexts) {
        for (String expectedText : subcategoryTexts) {
            WebElement subCategoryElement = header.getMenSubcategoryElement(expectedText);
            wait.until(ExpectedConditions.visibilityOf(subCategoryElement));
            String actualText = subCategoryElement.getText();
            assertEquals("Expected text not found: " + expectedText, expectedText, actualText);
        }
    }

    // YZT3

    @When("I search for {string}")
    public void searchFor(String string) {
        WebElement searchBox = header.getSearchTextBox();
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
            WebElement productNameElement = container.findElement(SearchPage.getProductNameLocator());
            wait.until(ExpectedConditions.visibilityOf(productNameElement));
            String actualProductName = productNameElement.getText().toLowerCase();
            assertTrue("Product name does not match: " + actualProductName, actualProductName.contains(string.toLowerCase()));
        }
    }

    // YZT4

    @When("I search for an invalid keyword")
    public void searchForInvalidKeyword() {
        SearchPage.searchFor("invalid_keyword");
    }


    @Then("a no results page should be displayed")
    public void verifyNoResultsPageIsDisplayed() {
        boolean elementExists = helper.doesElementExist(SearchPage.getNoResultsMessageLocator());
        assertTrue("No results element does not exist", elementExists);
    }

    // YZT5

    @When("the user navigates to the address book page")
    public void navigateToAddressBookPage() {
        SettingsPage.navigateToAddressBookPage();
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


    // YZT6
    @SneakyThrows
    @When("the user attempts to change birth dates and names according to the following data")
    public void theUserAttemptsToChangeBirthDatesAndNames(DataTable dataTable) {
        SettingsPage.clickEditDetails();

        List<Map<String, String>> entries = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> entry : entries) {
            String date = entry.get("Date");
            String name = entry.get("Name");
            String outcome = entry.get("Outcome");

            String[] nameParts = name.split(" ", 2);
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            String[] dateParts = date.split("-");
            String day = dateParts[1];
            String month = dateParts[2];
            String year = dateParts[0];


            WebElement firstNameField = SettingsPage.getFirstNameField();
            Helper.replaceText(firstNameField, firstName);

            WebElement lastNameField = SettingsPage.getLastNameField();
            Helper.replaceText(lastNameField, lastName);

            WebElement dayField = SettingsPage.getDayField();
            dayField.sendKeys(day);

            WebElement monthField = SettingsPage.getMonthField();
            monthField.sendKeys(month);

            WebElement yearField = SettingsPage.getYearField();
            yearField.sendKeys(year);


            switch (outcome) {
                case "save and display":
                    SettingsPage.clickSaveButton();
                    Base.waitForModalInvisibility();
                    String actualName = SettingsPage.getDisplayedName();
                    String actualDate = SettingsPage.getDisplayedDate();

                    Assert.assertEquals("Displayed name should match expected name",
                            name.toLowerCase(),
                            actualName.toLowerCase());
                    Assert.assertEquals("Displayed date should match expected date", date, actualDate);
                    break;
                case "reject":
                    Assert.assertTrue(SettingsPage.isErrorMessageDisplayed());
                    break;
            }

        }

    }
}