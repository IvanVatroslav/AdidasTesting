package stepdefinitions.stepswithoutlogin.navigationsteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.nonpages.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class MenSectionSubcategorySteps {

    private Header header;

    public MenSectionSubcategorySteps() {
        WebDriver driver = Hooks.driver.get();
        WebDriverWait wait = Hooks.wait.get();
        this.header = new Header(driver);
    }

    @When("I hover over the Men's section in the main menu")
    public void hoverOverMensSectionInMainMenu() {
        header.hoverOverMensSection();
    }

    @Then("I should see the dropdown with sub-categories")
    public void verifyDropdownWithSubCategoriesIsVisible() {
        assertTrue("Dropdown with sub-categories is not visible", header.isMensDropdownVisible());
    }

    @Then("I verify the following sub-categories are correct")
    public void verifyFollowingSubCategoriesAreCorrect(List<String> subcategoryTexts) {
        header.verifyMensSubcategories(subcategoryTexts);
    }
}
