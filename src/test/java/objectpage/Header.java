package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final By HEADER_FLYOUT = By.xpath("//div[@data-auto-id='header-flyout']");
    private static final By MENS_SECTION_LOCATOR = By.xpath("//a[@role='menu' and @href='/us/men']");
    private static final By SEARCH_TEXTBOX_LOCATOR = By.xpath("//input[@data-auto-id='searchinput-desktop']");
    private static final String MEN_SUBCATEGORY_XPATH_TEMPLATE = "//a[contains(@href, '/us/men')]/div[text()='%s']";
    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = Base.getWait();
    }

    public void hoverOverMensSection() {
        WebElement mensSection = driver.findElement(MENS_SECTION_LOCATOR);
        new Actions(driver).moveToElement(mensSection).perform();
    }

    public static WebElement getSearchTextBox() {
        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_TEXTBOX_LOCATOR));
        return driver.findElement(SEARCH_TEXTBOX_LOCATOR);
    }

    public WebElement getHeaderFlyout() {
        return driver.findElement(HEADER_FLYOUT);
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
