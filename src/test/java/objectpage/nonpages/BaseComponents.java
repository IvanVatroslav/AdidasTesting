package objectpage.nonpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import stepdefinitions.Hooks;

public abstract class BaseComponents {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseComponents(WebDriver driver) {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();
        PageFactory.initElements(driver, this);
    }

    protected abstract WebElement getUniqueElement();

    public void waitForPresence() {
        wait.until(ExpectedConditions.visibilityOf(getUniqueElement()));
    }

    public void waitForClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(getUniqueElement()));
    }

    public boolean isVisible() {
        try {
            return getUniqueElement().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
