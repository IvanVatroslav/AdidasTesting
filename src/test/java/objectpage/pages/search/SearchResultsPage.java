package objectpage.pages.search;

import objectpage.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class SearchResultsPage extends BasePage<SearchResultsPage> {
    @FindBy(className = "glass-product-card-container")
    private List<WebElement> searchResults;

    private final String productLinkSelector = "a[data-auto-id='glass-hockeycard-link']";
    private final By productNameLocator = By.className("glass-product-card__title");
    private static final Logger logger = LogManager.getLogger(SearchResultsPage.class);

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
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
        boolean allMatch = true;
        expectedName = expectedName.toLowerCase().trim();

        for (WebElement container : searchResults) {
            WebElement productNameElement = container.findElement(productNameLocator);
            wait.until(ExpectedConditions.visibilityOf(productNameElement));

            String actualProductName = productNameElement.getText().toLowerCase().trim();
            if (!actualProductName.contains(expectedName)) {
                logger.error("Mismatch found: Expected [{}], but found [{}]", expectedName, actualProductName);
                allMatch = false;
            }
        }
        return allMatch;
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

    public void checkWebPage(String expectedUrlSubstring) {
        try {
            boolean isUrlAsExpected = wait.until(driver -> driver.getCurrentUrl().contains(expectedUrlSubstring));
            assertTrue("The user is not on the expected page containing: " + expectedUrlSubstring, isUrlAsExpected);
        } catch (TimeoutException e) {
            String currentUrl = driver.getCurrentUrl();
            logger.error("Timeout waiting for URL. Current URL: " + currentUrl);
            throw e;
        }
    }
}

