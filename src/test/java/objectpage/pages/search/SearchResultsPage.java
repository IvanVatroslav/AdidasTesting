package objectpage.pages.search;

import objectpage.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class SearchResultsPage extends BasePage<SearchResultsPage> {
    @FindBy(className = "glass-product-card-container")
    private List<WebElement> searchResults;

    private final String productLinkSelector = "a[data-auto-id='glass-hockeycard-link']";
    private final By productNameLocator = By.className("glass-product-card__title");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        // Implement this method to return a unique element on the page,
        // useful for ensuring that the page has loaded.
        // Example: return searchBox; (if searchBox is unique to this page)
        return null;
    }

    @Override
    protected SearchResultsPage openPage() {
        return null;
    }

    public WebElement getProductNameElement(WebElement container) {
        return container.findElement(productNameLocator);
    }

    public List<WebElement> getSearchResults() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        return searchResults;
    }

    public boolean verifyAllProductNames(String expectedName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));

        for (WebElement container : searchResults) {
            WebElement productNameElement = container.findElement(productNameLocator);

            String actualProductName = productNameElement.getText().toLowerCase();
            if (!actualProductName.contains(expectedName.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public ItemPage clickRandomProduct() {
        if (searchResults.isEmpty()) {
            throw new IllegalStateException("No products to select.");
        }

        Random random = new Random();
        WebElement randomContainer = searchResults.get(random.nextInt(searchResults.size()));
        WebElement productLink = randomContainer.findElement(By.cssSelector(productLinkSelector));

        wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();

        return new ItemPage(driver);
    }
}
