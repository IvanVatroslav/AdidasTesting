package stepdefinitions.stepswithoutlogin.navigationsteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import objectpage.nonpages.components.Header;
import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainNavigationMenuSteps {

private WebDriver driver;

private final Header header;

public MainNavigationMenuSteps() {
        this.driver = Hooks.driver.get();
        this.header = new Header(driver);
        }
    @Then("I verify the visibility and correctness of each item in the navigation menu")
    public void verifyVisibilityAndCorrectnessOfNavigationMenuItems(DataTable dataTable) {
        List<String> expectedMenuItems = dataTable.asList(String.class);
        List<String> actualMenuItems = header.getNavigationCategories();

        assertEquals("The number of menu items should match", expectedMenuItems.size(), actualMenuItems.size());
        for (int i = 0; i < expectedMenuItems.size(); i++) {
            assertEquals("Menu item text should match", expectedMenuItems.get(i), actualMenuItems.get(i));
        }
    }




}
