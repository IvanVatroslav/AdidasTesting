package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepsYZ {

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        Helper.testNavigationMenuItems();
        // Base.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@data-auto-id='main-menu']/li[2]/a"))).click();
    }
    @Then("I verify navigation menu functionality")
    public void i_verify_navigation_menu_functionality() {
        // Write code here that turns the phrase above into concrete actions
    }
}
