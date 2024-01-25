package objectpage.nonpages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.time.Duration;

public abstract class BaseComponents {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BaseComponents(WebDriver driver) {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();
        PageFactory.initElements(driver, this);
    }
    protected BaseComponents(WebDriver driver, boolean waitForLoad) {
        this(driver);
        if (waitForLoad) {
            waitForLoad();
        }
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

    protected void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10L)).withMessage("Waiting for " + getClass().getName() + " load")
                .until(ExpectedConditions.visibilityOf(getUniqueElement()));
    }
    private boolean waitForElement(By locator) {
        WebDriverWait newWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            newWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
