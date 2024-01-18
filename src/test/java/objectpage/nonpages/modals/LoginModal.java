package objectpage.nonpages.modals;

import objectpage.pages.login.GoogleLoginPage;
import objectpage.pages.login.YahooLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginModal extends BaseModal {

    @FindBy(id = "loginPopup")
    private WebElement loginPopup;

    @FindBy(id = "social-button-google")
    private WebElement googleLoginButton;

    @FindBy(id = "social-button-yahoo")
    private WebElement yahooLoginButton;

    public LoginModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected By getModalLocator() {
        return null;
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }


    public GoogleLoginPage chooseGoogleLogin() {
        wait.until(ExpectedConditions.visibilityOf(googleLoginButton));
        wait.until(ExpectedConditions.elementToBeClickable(googleLoginButton));

        googleLoginButton.click();
        return new GoogleLoginPage(driver);
    }



    public YahooLoginPage chooseYahooLogin() {
        wait.until(ExpectedConditions.visibilityOf(yahooLoginButton));
        wait.until(ExpectedConditions.elementToBeClickable(yahooLoginButton));

        yahooLoginButton.click();
        return new YahooLoginPage(driver);
    }


}
