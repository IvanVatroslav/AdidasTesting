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
    private final By addToBagButtonLocator = By.xpath("//button[@data-auto-id='add-to-bag']");
    private final By colorOptionsLocator = By.xpath("//div[@data-testid='sidebar-color-chooser']" +
            "//div[contains(@class,'color-chooser-grid')]" +
            "//a[not(contains(@class, 'out-of-stock')) and not(contains(@class, 'hidden'))]");
    private final By sizeOptionsLocator = By.xpath("//section[@data-testid='buy-section']" +
            "//button[contains(@class, 'size___') and not(contains(@class, 'size--unavailable'))]");
    public ItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected ItemPage openPage() {
        return null;
    }

    public ItemPage selectRandomColor() {

        List<WebElement> colorOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(colorOptionsLocator));

        if (colorOptions.isEmpty()) {
            throw new IllegalStateException("No color options available.");
        }

        wait.until(ExpectedConditions.elementToBeClickable(colorOptionsLocator));

        colorOptions = driver.findElements(colorOptionsLocator);

        WebElement randomColorOption = colorOptions.get(new Random().nextInt(colorOptions.size()));
        randomColorOption.click();
        return this;
    }


    public ItemPage selectRandomSize() {
        List<WebElement> sizeOptions = driver.findElements(sizeOptionsLocator);
        if (sizeOptions.isEmpty()) {
            throw new IllegalStateException("No size options available.");
        }
        WebElement randomSizeOption = sizeOptions.get(new Random().nextInt(sizeOptions.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomSizeOption)).click();
        return this;
    }


    public ItemPage addToBag() {
        WebElement addToBagButton = wait.until(ExpectedConditions.elementToBeClickable(addToBagButtonLocator));
        addToBagButton.click();
        return this;
    }
}
