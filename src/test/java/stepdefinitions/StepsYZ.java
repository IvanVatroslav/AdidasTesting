package stepdefinitions;

import ObjectPage.Base;
import ObjectPage.Header;
import ObjectPage.SearchPage;
import ObjectPage.SettingsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
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
    public void i_am_on_the_homepage() {
        helper.checkWebPage("https://www.adidas.com/us");
    }

    @Then("I verify navigation menu functionality")
    public void i_verify_navigation_menu_functionality() {
        helper.testNavigationMenuItems();
    }
    //YZT2


    @When("I hover over the Men's section in the main menu")
    public void i_hover_over_the_men_s_section_in_the_main_menu() {
        header.hoverOverMensSection();
    }

    @Then("I should see the dropdown with sub-categories")
    public void i_should_see_the_dropdown_with_sub_categories() {
    }

    @Then("I sequentially navigate through each sub-category in Men's section")
    public void i_sequentially_navigate_through_each_sub_category_in_men_s_section() {
        header.navigateToSubCategory();
    }

    @Then("I should see the relevant products and content for each sub-category")
    public void i_should_see_the_relevant_products_and_content_for_each_sub_category() {
    }

    //YZT3


    @When("I search for 'SAMBA OG SHOES'")
    public void i_search_for_samba_og_shoes() {
        WebElement searchBox = header.getSearchTextBox();
        searchBox.sendKeys("SAMBA OG SHOES" + Keys.ENTER);
    }

    @Then("the search results page should open")
    public void the_search_results_page_should_open() {
        WebElement searchTitleElement = header.getSearchResultTitle();
        String actualText = searchTitleElement.getText().toLowerCase(); // Convert to lower case
        assertEquals("Search title does not match", "“samba og shoes”", actualText); // Compare with lower case
    }


    @Then("the list of products should not be empty")
    public void the_list_of_products_should_not_be_empty() {

    }

    @Then("all products should have the name 'SAMBA OG SHOES'")
    public void all_products_should_have_the_name_samba_og_shoes() {
        List<WebElement> productContainers = SearchPage.getSearchResults();

        assertFalse("Product list is empty", productContainers.isEmpty());

        for (WebElement container : productContainers) {
            WebElement productNameElement = container.findElement(By.xpath(".//p[@class='glass-product-card__title']"));
            wait.until(ExpectedConditions.visibilityOf(productNameElement)); // Wait for the product name element to be visible
            String actualProductName = productNameElement.getText().toLowerCase(); // Convert product name to lower case

            assertTrue("Product name does not match: " + actualProductName, actualProductName.contains("samba og shoes"));
        }
    }

    //YZT4
    @When("I search for 'invalid_keyword'")
    public void i_search_for_invalid_keyword() {
        SearchPage.searchFor("invalid_keyword");
    }

    @Then("a no results page should be displayed")
    public void a_no_results_page_should_be_displayed() {
        assertTrue("No results page is not displayed", SearchPage.isNoResultsDisplayed());
    }

    //YZT5
    @When("the user navigates to the address book page")
    public void the_user_navigates_to_the_address_book_page() {
        SettingsPage.navigateToAddressBookPage();

    }

    @And("the user removes any old addresses")
    public void the_user_removes_any_old_addresses() {
        helper.removeAllAddresses();
    }

    @And("the user adds new addresses")
    public void the_user_adds_new_addresses(DataTable addressTable) {
        storedAddresses = addressTable.asMaps(String.class, String.class);
        for (Map<String, String> address : storedAddresses) {
            helper.addNewAddress(address.get("FirstName"), address.get("LastName"), address.get("Address"), address.get("City"), address.get("State"), address.get("Zip"), address.get("Phone"));
        }
    }


    @Then("the new addresses should be saved and displayed in the address book")
    public void the_new_addresses_should_be_saved_and_displayed_in_the_address_book() {
        helper.assertAddresses(storedAddresses);
    }


}



