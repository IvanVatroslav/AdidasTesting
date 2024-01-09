package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage extends BasePage<SearchPage> {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@data-auto-id='searchinput-desktop']")
    private static WebElement searchBox;

    @FindBy(xpath = "//div[@id='main-content']//div[@data-auto-id='product_container']")
    private static List<WebElement> searchResults;

    @FindBy(xpath = "//h4[contains(text(), 'OOPS – NO RESULTS FOR')]")
    private WebElement noResultsMessage;

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
        return (By) noResultsMessage;
    }

    public WebElement getProductNameLocator() {
        return productNameElement;
    }

    public static List<WebElement> getSearchResults() {
        return searchResults;
    }


}
