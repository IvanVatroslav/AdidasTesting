package ObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {

//    WebDriver driver;
//    WebDriverWait wait;

    static By CookiesAccept_xpath = By.xpath("//button[@class=\"gl-cta gl-cta--tertiary\"]");
    static By CustomerInfoButton_xpath = By.xpath("//a[@data-auto-id='customer-info-button']");
    static By YahooButton_id = By.id("social-button-yahoo");
//    public MainPage(WebDriver driver, WebDriverWait wait) {
//        this.driver = driver;
//        this.wait = wait;
//    }

    public static void acceptCookies() {
        WebElement cookiesAcceptButton = Base.getWait().until(ExpectedConditions.elementToBeClickable(CookiesAccept_xpath));
    cookiesAcceptButton.click();
    }

    public static void openCustomerInfo() {
        WebElement customerInfoButton = Base.getDriver().findElement(CustomerInfoButton_xpath);
        customerInfoButton.click();
    }

    public static void clickYahooButton() {
        WebElement yahooButton = Base.getWait().until(ExpectedConditions.elementToBeClickable(YahooButton_id));
        yahooButton.click();
    }
}
