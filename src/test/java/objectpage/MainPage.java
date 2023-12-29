package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {


    static By CookiesAccept_xpath = By.xpath("//button[@class=\"gl-cta gl-cta--tertiary\"]");
    static By CustomerInfoButton_xpath = By.xpath("//a[@data-auto-id='customer-info-button']");
    static By YahooButton_id = By.id("social-button-yahoo");
    static By AccountLink_xpath = By.xpath("//*[@id=\"cta-test-id-delete\"]/div/div[1]/a/span");

    static By AccountSettings_xpath = By.xpath("//*[@id=\"ACCOUNT\"]");


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

    public static void clickAccountLink() {
        WebElement accountLink = Base.getWait().until(ExpectedConditions.elementToBeClickable(AccountLink_xpath));
        accountLink.click();
    }
    public static void clickAccountSettings() {
        WebElement accountSettings = Base.getDriver().findElement(AccountSettings_xpath);
        accountSettings.click();
    }



}
