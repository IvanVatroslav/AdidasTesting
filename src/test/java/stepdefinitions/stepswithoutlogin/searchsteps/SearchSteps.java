package stepdefinitions.stepswithoutlogin.searchsteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.nonpages.components.Header;
import objectpage.nonpages.components.SidePanel;
import objectpage.pages.search.NoResultsPage;
import objectpage.pages.search.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.Helper;
import stepdefinitions.Hooks;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchSteps {


private WebDriver driver;
private WebDriverWait wait;

private NoResultsPage noResultsPage;
private SearchResultsPage searchResultsPage;
private Header header;
private Helper helper;
private final SidePanel sidePanel;
public SearchSteps() {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();

        this.noResultsPage = new NoResultsPage(driver);
        this.searchResultsPage = new SearchResultsPage(driver);
        this.sidePanel = new SidePanel(driver);
        this.header = new Header(driver);
        this.helper = new Helper();
        }










    @When("I search for {string}")
    public void searchFor(String searchString) {
        // Check if the side panel is open and close it if necessary
        if (sidePanel.isPanelOpen()) {
            sidePanel.closeSidePanel();
        }

        // Now wait for the search box to be clickable and perform the search action
        header.searchFor(searchString);
    }




    @Then("the search results page should open")
    public void verifySearchResultsPageOpens() {
        String expectedUrlPattern = "https://www.adidas.com/us/search?q=";
        String currentUrl = driver.getCurrentUrl();
        assertTrue("The user is not on a search page", currentUrl.startsWith(expectedUrlPattern));
    }

    @Then("the list of products should not be empty")
    public void verifyListOfProductsIsNotEmpty() {

        List<WebElement> productContainers = searchResultsPage.getSearchResults();
        assertFalse("Product list is empty", productContainers.isEmpty());
    }

    @Then("all products should have the name {string}")
    public void verifyAllProductsHaveName(String expectedName) {
        assertTrue("Not all product names match: " + expectedName,
                searchResultsPage.verifyAllProductNames(expectedName));
    }



    @When("I search for an invalid keyword")
    public void searchForInvalidKeyword() {
        noResultsPage.searchFor("invalid_keyword");
    }

    @Then("a no results page should be displayed")
    public void verifyNoResultsPageIsDisplayed() {
        WebElement noResultsLocator = noResultsPage.getNoResultsMessageLocator();
        boolean elementExists = helper.doesElementExist(noResultsLocator);
        assertTrue("No results element does not exist", elementExists);
    }



}
