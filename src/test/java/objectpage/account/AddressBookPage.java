package objectpage.account;

import objectpage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressBookPage extends BasePage<AddressBookPage> {



    public AddressBookPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected AddressBookPage openPage() {
        return null;
    }


}