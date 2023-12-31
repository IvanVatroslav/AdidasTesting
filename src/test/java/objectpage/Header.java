package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Header {
    private WebDriver driver;
    private static final By HEADER_FLYOUT = By.xpath("//div[@data-auto-id='header-flyout']");
    private static final String MEN_SUBCATEGORY_XPATH = "//a[contains(@href, '/us/men')]/div";

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverOverMensSection() {
        WebElement mensSection = driver.findElement(By.xpath("//a[@role='menu' and @href='/us/men']"));
        new Actions(driver).moveToElement(mensSection).perform();
    }

    public WebElement getSearchTextBox() {
        return driver.findElement(By.xpath("//input[@data-auto-id=\"searchinput-desktop\"]"));
    }

    public WebElement getHeaderFlyout() {
        return driver.findElement(HEADER_FLYOUT);
    }

    public WebElement getMenSubcategoryElement(String subcategoryText) {
        String xpath = MEN_SUBCATEGORY_XPATH + "[text()='" + subcategoryText + "']";
        return driver.findElement(By.xpath(xpath));
    }
}
