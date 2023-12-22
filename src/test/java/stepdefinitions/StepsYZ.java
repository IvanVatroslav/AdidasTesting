package stepdefinitions;

import ObjectPage.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

}
