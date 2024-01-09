package objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginModalPage extends BasePage {
    @FindBy(id = "loginPopup")
    private WebElement loginPopup;

    @FindBy(id = "googleLoginButton")
    private WebElement googleLoginButton;

    @FindBy(id = "yahooLoginButton")
    private WebElement yahooLoginButton;

    public LoginModalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openLoginPopup() {
        loginPopup.click();
    }

    public GoogleLoginPage chooseGoogleLogin() {
        googleLoginButton.click();
        return new GoogleLoginPage(driver);
    }

    public YahooLoginPage chooseYahooLogin() {
        yahooLoginButton.click();
        return new YahooLoginPage(driver);
    }
}
