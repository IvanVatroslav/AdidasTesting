package objectpage.nonpages.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public  class DeleteAddressModal extends BaseModal {


    private static final By CONFIRM_DELETE_BUTTON_XPATH = By.xpath("//button[@data-auto-id='confirm-delete']");

    public DeleteAddressModal(WebDriver driver) {
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

    public  void confirmDelete() {
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_DELETE_BUTTON_XPATH));
        confirmDeleteButton.click();
    }
}
