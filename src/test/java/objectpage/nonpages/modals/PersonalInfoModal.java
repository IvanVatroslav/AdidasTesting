package objectpage.nonpages.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalInfoModal extends BaseModal {
    private WebDriver driver;
    private WebDriverWait wait;
    public PersonalInfoModal(WebDriver driver) {
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
}
