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
    @FindBy(xpath = "//div[@id='main-content']//div[@data-auto-id='product_container']")
    private List<WebElement> searchResults;

    // This is a locator for the product name within each product container.
    private final By productNameLocator = By.xpath(".//p[@class='glass-product-card__title']");

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
        // Find and return the product name element within the given container.
        return container.findElement(productNameLocator);
    }

    public List<WebElement> getSearchResults() {
        // This will wait until all elements in the searchResults list are visible.
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        return searchResults;
    }


    public boolean verifyAllProductNames(String expectedName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));

        for (WebElement container : searchResults) {
            WebElement productNameElement = container.findElement(productNameLocator);
            wait.until(ExpectedConditions.visibilityOf(productNameElement));

            String actualProductName = productNameElement.getText().toLowerCase();
            if (!actualProductName.contains(expectedName.toLowerCase())) {
                return false; // Return false immediately if any product name doesn't match
            }
        }
        return true; // All product names matched
    }

    public ItemPage clickRandomProduct() {
        List<WebElement> visibleProducts = getSearchResults();
        if (visibleProducts.isEmpty()) {
            throw new IllegalStateException("No products to select.");
        }

        // Randomly select a product
        Random random = new Random();
        int randomIndex = random.nextInt(visibleProducts.size());
        WebElement randomProduct = visibleProducts.get(randomIndex);

        // Click the selected product
        wait.until(ExpectedConditions.elementToBeClickable(randomProduct)).click();

        // Return the new ItemPage object
        return new ItemPage(driver);
    }

}



