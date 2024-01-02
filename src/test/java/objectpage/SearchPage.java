package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {
    private static final WebElement SEARCH_BOX_LOCATOR = Header.getSearchTextBox();

    private static final By PRODUCT_NAME_LOCATOR = By.xpath(".//p[@class='glass-product-card__title']");
    private static final By NO_RESULTS_LOCATOR = By.xpath("//h4[contains(text(), 'OOPS – NO RESULTS FOR')]");
    private static final By SEARCH_RESULTS_LOCATOR = By.xpath("//div[@id='main-content']//div[@data-auto-id='product_container']");
    private static WebDriver driver;
    private static WebDriverWait wait;

    static {
        driver = Base.getDriver();
        wait = Base.getWait();
    }

    public static List<WebElement> getSearchResults() {
        return driver.findElements(SEARCH_RESULTS_LOCATOR);
    }

    public static void searchFor(String keyword) {
        WebElement searchBox = SEARCH_BOX_LOCATOR;
        searchBox.sendKeys(keyword + Keys.ENTER);
    }

    public static final By getProductNameLocator() {
        return PRODUCT_NAME_LOCATOR;
    }

    public static final By getNoResultsMessageLocator() {
        return NO_RESULTS_LOCATOR;
    }
}
