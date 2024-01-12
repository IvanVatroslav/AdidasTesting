package objectpage.pages;

import objectpage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage<SearchPage> {

    private static final By SEARCH_BOX_LOCATOR = By.xpath("//input[@data-auto-id='searchinput-desktop']");
    private static final By SEARCH_RESULTS_LOCATOR = By.xpath("//div[@id='main-content']//div[@data-auto-id='product_container']");
    private static final By NO_RESULTS_MESSAGE_LOCATOR = By.xpath("//h4[contains(text(), 'OOPS – NO RESULTS FOR')]");
    private static final By PRODUCT_NAME_LOCATOR = By.xpath(".//p[@class='glass-product-card__title']");

    @FindBy(xpath = "//input[@data-auto-id='searchinput-desktop']")
    private static WebElement searchBox;

    @FindBy(xpath = "//div[@id='main-content']//div[@data-auto-id='product_container']")
    private static List<WebElement> searchResults;

    @FindBy(xpath = "//h4[contains(text(), 'OOPS – NO RESULTS FOR')]")
    private WebElement noResultsMessageLocator;

    @FindBy(xpath = ".//p[@class='glass-product-card__title']")
    private WebElement productNameElement;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected SearchPage openPage() {
        return null;
    }

    public static void searchFor(String keyword) {
        searchBox.sendKeys(keyword + Keys.ENTER);
    }

    public By getNoResultsMessageLocator() {
        return NO_RESULTS_MESSAGE_LOCATOR;
    }

    public By getProductNameLocator() {
        return PRODUCT_NAME_LOCATOR;
    }

    public List<WebElement> getSearchResults() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SEARCH_RESULTS_LOCATOR));

        return searchResults;
    }


}
