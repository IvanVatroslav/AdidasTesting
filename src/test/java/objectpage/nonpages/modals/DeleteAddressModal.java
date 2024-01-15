package objectpage.nonpages.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteAddressModal {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final By CONFIRM_DELETE_BUTTON_XPATH = By.xpath("//button[@data-auto-id='confirm-delete']");

    public DeleteAddressModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void confirmDelete() {
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_DELETE_BUTTON_XPATH));
        confirmDeleteButton.click();
    }
}
