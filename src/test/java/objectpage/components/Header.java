package objectpage.components;

import objectpage.BasePage;
import objectpage.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Header<T extends Header<T>> extends BasePage<T> {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//div[@data-auto-id='header-flyout']")
    private WebElement headerFlyout;

    @FindBy(xpath = "//a[@role='menu' and @href='/us/men']")
    private WebElement mensSection;

    @FindBy(xpath = "//input[@data-auto-id='searchinput-desktop']")
    private WebElement searchTextbox;

    @FindBy(xpath = "//a[@data-auto-id='customer-info-button']")
    private WebElement accountPortalTrigger;

    private static final String MEN_SUBCATEGORY_XPATH_TEMPLATE = "//a[contains(@href, '/us/men')]/div[text()='%s']";

    public Header(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public T hoverOverMensSection() {
        new Actions(driver).moveToElement(mensSection).perform();
        return (T) this;
    }

    public T enterSearchKeyword(String searchKeyword) {
        wait.until(ExpectedConditions.elementToBeClickable(searchTextbox));
        searchTextbox.sendKeys(searchKeyword);
        return (T) this;
    }

    public SearchPage submitSearch() {
        searchTextbox.sendKeys(Keys.ENTER);
        return new SearchPage(driver);
    }

    public WebElement getHeaderFlyout() {
        return headerFlyout;
    }

    public WebElement getMenSubcategoryElement(String subcategoryText) {
        String xpath = String.format(MEN_SUBCATEGORY_XPATH_TEMPLATE, subcategoryText);
        return driver.findElement(By.xpath(xpath));
    }

    public static By getMenSubcategoryXPath(String category) {
        String xpath = String.format(MEN_SUBCATEGORY_XPATH_TEMPLATE, category);
        return By.xpath(xpath);
    }
}
