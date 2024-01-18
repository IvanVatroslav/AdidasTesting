package objectpage.nonpages.modals;

import objectpage.nonpages.BaseComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public abstract class BaseModal extends BaseComponents {

    public BaseModal(WebDriver driver) {
        super(driver);
    }

    protected abstract By getModalLocator();

    // Utilize the inherited methods from BaseComponents. If modal-specific behavior is needed,
    // these methods can be overridden here.

    // Additional modal-specific methods can be added here
}
