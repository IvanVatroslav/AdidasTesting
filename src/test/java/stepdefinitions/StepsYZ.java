package stepdefinitions;

import ObjectPage.Base;
import ObjectPage.Header;
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

import static org.junit.Assert.*;

public class StepsYZ {
    //YZT1
    WebDriver driver = Base.getDriver();
    WebDriverWait wait = Base.getWait();

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        // Base.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@data-auto-id='main-menu']/li[2]/a"))).click();
    }

    @Then("I verify navigation menu functionality")
    public void i_verify_navigation_menu_functionality() {
        Helper.testNavigationMenuItems();
    }
    //YZT2


    @When("I hover over the Men's section in the main menu")
    public void i_hover_over_the_men_s_section_in_the_main_menu() {
        Header.hoverOverMensSection();
    }

    @Then("I should see the dropdown with sub-categories")
    public void i_should_see_the_dropdown_with_sub_categories() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("I sequentially navigate through each sub-category in Men's section")
    public void i_sequentially_navigate_through_each_sub_category_in_men_s_section() {
        Header.navigateToSubCategory();

        // Add verification for each sub-category here
        // ...

        // Navigate back to the Men's section to hover again

    }

    @Then("I should see the relevant products and content for each sub-category")
    public void i_should_see_the_relevant_products_and_content_for_each_sub_category() {
        // Write code here that turns the phrase above into concrete actions
    }

    //YZT3


    @When("I search for 'SAMBA OG SHOES'")
    public void i_search_for_samba_og_shoes() {
        WebElement searchBox = Header.getSearchTextBox();
        searchBox.sendKeys("SAMBA OG SHOES" + Keys.ENTER);
        //searchBox.submit();
    }

    @Then("the search results page should open")
    public void the_search_results_page_should_open() {
        WebElement searchTitleElement = Header.getSearchResultTitle();
        String actualText = searchTitleElement.getText().toLowerCase(); // Convert to lower case
        assertEquals("Search title does not match", "“samba og shoes”", actualText); // Compare with lower case
    }


    @Then("the list of products should not be empty")
    public void the_list_of_products_should_not_be_empty() {

    }

    @Then("all products should have the name 'SAMBA OG SHOES'")
    public void all_products_should_have_the_name_samba_og_shoes() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-content\"]/div/div[2]/div/div/div[2]/div[1]")));

        // Find all div elements that are children of the container div which contains the products
        List<WebElement> productContainers = driver.findElements(By.xpath("//*[@id=\"main-content\"]/div/div[2]/div/div/div[2]/div[1]"));

        // Verify that the product container list is not empty
        assertFalse("Product list is empty", productContainers.isEmpty());

        // Iterate over each product container and check the product name
        for (WebElement container : productContainers) {
            // Find the product name within the container using the provided class
            WebElement productNameElement = container.findElement(By.xpath(".//p[@class='glass-product-card__title']"));
            wait.until(ExpectedConditions.visibilityOf(productNameElement)); // Wait for the product name element to be visible
            String actualProductName = productNameElement.getText().toLowerCase(); // Convert product name to lower case

            // Assert that the actual product name contains the expected text
            assertTrue("Product name does not match: " + actualProductName, actualProductName.contains("samba og shoes"));
        }
    }

    //YZT4
    @When("I search for 'invalid_keyword'")
    public void i_search_for_invalid_keyword() {
        WebElement searchBox = driver.findElement(By.xpath("//input[@class='_input_1f3oz_13']"));
        searchBox.sendKeys("invalid_keyword" + Keys.ENTER);
    }

    @Then("a no results page should be displayed")
    public void a_no_results_page_should_be_displayed() {

        WebElement noResultsElement = driver.findElement(By.xpath("//h4[contains(@class, 'nohits_title___3kFIK') and contains(text(), 'NO RESULTS')]"));
        assertTrue("No results page is not displayed", noResultsElement.isDisplayed());
    }

    //YZT5
    @When("the user navigates to the address book page")
    public void the_user_navigates_to_the_address_book_page() {
        WebElement addressBookLink = driver.findElement(By.xpath("//a[@data-auto-id=\"members-home-account-address-book\"]"));
        addressBookLink.click();
    }

    // Example usage within a step definition
    @And("the user removes any old addresses")
    public void the_user_removes_any_old_addresses() {
        Helper.removeAllAddresses();
    }

    @And("the user adds a new address with specific details")
    public void the_user_adds_a_new_address_with_specific_details() {
        Helper.addNewAddress("John", "Doe", "123 Main St", "Anytown", "Anystate", "12345", "1234567890");
    }

    @When("the user adds another new address with different details")
    public void the_user_adds_another_new_address_with_different_details() {
        Helper.addNewAddress("Jane", "Roe", "456 Elm Street", "Difftown", "Diffstate", "67890", "0987654321");
    }

    @Then("both new addresses should be saved and displayed in the address book")
    public void both_new_addresses_should_be_saved_and_displayed_in_the_address_book() {
    /*    // Find all the address elements on the page
        List<WebElement> addresses = driver.findElements(By.xpath("//div[contains(@class, 'address-card__overflow-guard___')]"));

        // Initialize flags to check if addresses are present
        boolean isFirstAddressPresent = false;
        boolean isSecondAddressPresent = false;

        // Iterate through the list of addresses and check if the text matches the expected address lines
        for (WebElement address : addresses) {
            String addressText = address.getText();
            if (addressText.contains("123 Main St")) {
                isFirstAddressPresent = true;
            }
            if (addressText.contains("456 Elm Street")) {
                isSecondAddressPresent = true;
            }

            // If both addresses are found, no need to continue checking
            if (isFirstAddressPresent && isSecondAddressPresent) {
                break;
            }
        }

        // Assert that both addresses have been found and displayed
        assertTrue("First address is not displayed in the address book", isFirstAddressPresent);
        assertTrue("Second address is not displayed in the address book", isSecondAddressPresent);
    } */
    }


}
