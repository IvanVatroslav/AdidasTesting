package objectpage.pages.account;

import objectpage.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AccountLoginPage extends BasePage<AccountLoginPage> {
    public AccountLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected AccountLoginPage openPage() {
        return null;
    }
}
