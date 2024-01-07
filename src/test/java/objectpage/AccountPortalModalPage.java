package objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPortalModalPage extends BasePage {

    @FindBy(id = "social-button-google")
    private WebElement googleLoginButton;

    @FindBy(id = "social-button-yahoo")
    private WebElement yahooLoginButton;


    public AccountPortalModalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public GoogleLoginPage clickGoogleLogin() {
        googleLoginButton.click();
        return new GoogleLoginPage(driver);
    }

    public YahooLoginPage clickYahooLogin() {
        yahooLoginButton.click();
        return new YahooLoginPage(driver);
    }

}
