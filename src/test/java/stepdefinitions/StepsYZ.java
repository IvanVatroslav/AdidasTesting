package stepdefinitions;

import ObjectPage.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
        WebElement mensSection = driver.findElement(By.xpath("//a[@role='menu' and @href='/us/men']"));
        new Actions(driver).moveToElement(mensSection).perform();
    }

    @Then("I should see the dropdown with sub-categories")
    public void i_should_see_the_dropdown_with_sub_categories() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("I sequentially navigate through each sub-category in Men's section")
    public void i_sequentially_navigate_through_each_sub_category_in_men_s_section() {
        String[] subCategoryHrefs = {
                "/us/men-trending",
                "/us/men-shoes",
                "/us/men-clothing",
                "/us/men-accessories",
                "/us/men-performance",
                "/us/men?grid=true"
        };

        for (String href : subCategoryHrefs) {

            WebElement subCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='" + href + "']")));
            subCategoryElement.click();
            Helper.closeStupidLoginPopup();

            // Add verification for each sub-category here
            // ...

            // Navigate back to the Men's section to hover again
            WebElement mensSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@role='menu' and @href='/us/men']")));
            new Actions(driver).moveToElement(mensSection).perform();
        }
    }

    @Then("I should see the relevant products and content for each sub-category")
    public void i_should_see_the_relevant_products_and_content_for_each_sub_category() {
        // Write code here that turns the phrase above into concrete actions
    }

    //YZT3


    @When("I search for 'SAMBA OG SHOES'")
    public void i_search_for_samba_og_shoes() {
        WebElement searchBox = driver.findElement(By.xpath("//input[@class=\"_input_1f3oz_13\"]")); // Replace with your search box ID
        searchBox.sendKeys("SAMBA OG SHOES"+ Keys.ENTER);
        //searchBox.submit();
    }

    @Then("the search results page should open")
    public void the_search_results_page_should_open() {
        WebElement searchTitleElement = driver.findElement(By.xpath("//h1[@class=\"gl-vspace heading___3g-L_ heading--search\"]"));
        String actualText = searchTitleElement.getText();
        assertEquals("Search title does not match", "“SAMBA OG SHOES”", actualText);
    }

    @Then("the list of products should not be empty")
    public void the_list_of_products_should_not_be_empty() {
        List<WebElement> productsList = driver.findElements(By.xpath("//div[@class='plp-grid___1FP1J']/div"));
        assertFalse("Product list is empty", productsList.isEmpty());
    }

    @Then("all products should have the name 'SAMBA OG SHOES'")
    public void all_products_should_have_the_name_samba_og_shoes() {
        List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='plp-grid___1FP1J']/div//ELEMENT_CONTAINING_PRODUCT_NAME"));

        for (WebElement productName : productNames) {
            assertTrue("Product name does not match", productName.getText().contains("SAMBA OG SHOES"));
        }
    }

}
