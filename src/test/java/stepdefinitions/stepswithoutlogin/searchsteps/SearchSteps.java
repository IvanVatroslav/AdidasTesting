package stepdefinitions.stepswithoutlogin.searchsteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.nonpages.components.Header;
import objectpage.pages.search.NoResultsPage;
import objectpage.pages.search.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.Helper;
import stepdefinitions.Hooks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private NoResultsPage noResultsPage;
    private SearchResultsPage searchResultsPage;
    private Header header;
    private Helper helper;

    public SearchSteps() {
        WebDriver driver = Hooks.driver.get();
        WebDriverWait wait = Hooks.wait.get();

        this.noResultsPage = new NoResultsPage(driver);
        this.searchResultsPage = new SearchResultsPage(driver);
        this.header = new Header(driver);
        this.helper = new Helper();
    }

    @When("I search for {string}")
    public void searchFor(String searchString) {
        header.searchFor(searchString);
    }

    @Then("the search results page should open")
    public void verifySearchResultsPageOpens() {
        assertTrue("Search results page did not open", searchResultsPage.checkWebPage ("search"));
    }


    @Then("the list of products should not be empty")
    public void verifyListOfProductsIsNotEmpty() {
        assertFalse("Product list is empty", searchResultsPage.isProductListEmpty());
    }

    @Then("all products should have the name {string}")
    public void verifyAllProductsHaveName(String expectedName) {
        assertTrue("Not all product names match: " + expectedName, searchResultsPage.doAllProductsHaveName(expectedName));
    }

    @When("I search for an invalid keyword")
    public void searchForInvalidKeyword() {
        noResultsPage.searchFor("invalid_keyword");
    }

    @Then("a no results page should be displayed")
    public void verifyNoResultsPageIsDisplayed() {
        assertTrue("No results element does not exist", noResultsPage.isNoResultsMessageDisplayed());
    }
}
