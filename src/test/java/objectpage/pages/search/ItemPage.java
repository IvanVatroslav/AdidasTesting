package objectpage.pages.search;

import objectpage.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class ItemPage extends BasePage<ItemPage> {

    public ItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        // Replace with an actual unique element from ItemPage.
        return null;
    }

    @Override
    protected ItemPage openPage() {
        // Implement the logic to open this page, if needed.
        return null;
    }

    public ItemPage selectRandomColor() {
        List<WebElement> colorOptions = driver.findElements(By.xpath("//div[@data-testid='sidebar-color-chooser']//div[contains(@class,'color-chooser-grid')]//a[not(contains(@class, 'out-of-stock')) and not(contains(@class, 'hidden'))]"));
        if (colorOptions.isEmpty()) {
            throw new IllegalStateException("No color options available.");
        }
        WebElement randomColorOption = colorOptions.get(new Random().nextInt(colorOptions.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomColorOption)).click();
        return this;
    }

    public ItemPage selectRandomSize() {
        List<WebElement> sizeOptions = driver.findElements(By.xpath("//section[@data-testid='buy-section']//button[contains(@class, 'size___') and not(contains(@class, 'size--unavailable'))]"));
        if (sizeOptions.isEmpty()) {
            throw new IllegalStateException("No size options available.");
        }
        WebElement randomSizeOption = sizeOptions.get(new Random().nextInt(sizeOptions.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomSizeOption)).click();
        return this;
    }
}
