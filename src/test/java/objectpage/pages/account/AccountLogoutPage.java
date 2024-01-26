package objectpage.pages.account;

import objectpage.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AccountLogoutPage extends BasePage<AccountLogoutPage> {
    public AccountLogoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected AccountLogoutPage openPage() {
        return null;
    }
}
