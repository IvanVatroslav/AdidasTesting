package objectpage.nonpages.modals;

import objectpage.nonpages.BaseComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public abstract class BaseModal extends BaseComponents {

    public BaseModal(WebDriver driver) {
        super(driver);
    }

    protected abstract By getModalLocator();


}
