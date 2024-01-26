package stepdefinitions.stepswithoutlogin.navigationsteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import objectpage.nonpages.components.Header;
import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks;

import java.util.List;

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
        header.verifyNavigationMenuItems(expectedMenuItems);
    }




}
