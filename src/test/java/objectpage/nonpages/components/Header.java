package objectpage.nonpages.components;

import objectpage.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.util.List;
import java.util.stream.Collectors;

public class Header {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@data-auto-id='header-flyout']")
    private WebElement headerFlyout;

    @FindBy(xpath = "//a[@role='menu' and @href='/us/men']")
    private WebElement mensSection;

    @FindBy(xpath = "//input[@data-auto-id='searchinput-desktop']")
    private WebElement searchTextbox;

    @FindBy(xpath = "//a[@data-auto-id='customer-info-button']")
    private WebElement accountPortalTrigger;

    private static final String MEN_SUBCATEGORY_XPATH_TEMPLATE = "//a[contains(@href, '/us/men')]/div[text()='%s']";

    @FindBy(css = "li[data-auto-id='navigation-flyout'] > a")
    private List<WebElement> navigationMenuItems;

    public Header(WebDriver driver) {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();
        PageFactory.initElements(driver, this);
    }

    public Header hoverOverMensSection() {
        new Actions(driver).moveToElement(mensSection).perform();
        return this;
    }

    public Header enterSearchKeyword(String searchKeyword) {
        wait.until(ExpectedConditions.elementToBeClickable(searchTextbox));
        searchTextbox.sendKeys(searchKeyword);
        return this;
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

    public WebElement getSearchTextBox() {
        return searchTextbox;
    }

    public WebElement getAccountPortalTrigger() {
        return accountPortalTrigger;
    }

    public List<String> getNavigationCategories() {
        return navigationMenuItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}