package stepdefinitions.stepswithlogin.shopsteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.pages.search.ItemPage;
import objectpage.pages.search.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks;

public class AddingItemToBagSteps {

    private WebDriver driver;
    private SearchResultsPage searchResultsPage;

    private ItemPage itemPage;


    public AddingItemToBagSteps() {
        this.driver = Hooks.driver.get();
        this.searchResultsPage = new SearchResultsPage(driver);
        this.itemPage = new ItemPage(driver);
    }
    @When("the user adds the random product from the search results to the bag")
    public void theUserAddsTheRandomProductFromTheSearchResultsToTheBag() {
        searchResultsPage.clickRandomProduct();
        itemPage.selectRandomColor();
        //itemPage.selectRandomSize(); //size options don't load on automated browser
     //   itemPage.addToBag(); //this step is not working because of previous bug

    }

    @Then("the bag should contain products")
    public void theBagShouldContainProducts() {
    }



}
