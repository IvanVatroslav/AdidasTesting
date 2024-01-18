package objectpage.nonpages.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CookiesModal extends BaseModal {

    private static final By COOKIES_ACCEPT_BUTTON = By.xpath("//button[@class='gl-cta gl-cta--tertiary']");

    public CookiesModal(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }


    public void acceptCookies() {
        WebElement cookiesAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(COOKIES_ACCEPT_BUTTON));
        cookiesAcceptButton.click();
    }
    @Override
    protected By getModalLocator() {
        return null;
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

}
