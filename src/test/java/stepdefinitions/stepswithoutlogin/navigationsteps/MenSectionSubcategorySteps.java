package stepdefinitions.stepswithoutlogin.navigationsteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.nonpages.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenSectionSubcategorySteps {
private WebDriver driver;
private WebDriverWait wait;

private Header header;

public MenSectionSubcategorySteps() {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();
        this.header = new Header(driver);
        }



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
}
