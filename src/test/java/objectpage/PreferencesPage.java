package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PreferencesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Random rand = new Random();

    private final By PREFERENCES_BUTTON_XPATH = By.xpath("//a[@data-auto-id='members-home-account-preferences']");
    private final By SAVE_PREFERENCES_ID = By.id("preferences-update-CTA-product-categories");
    private final By SAVE_INTERESTS_XPATH = By.id("preferences-update-CTA-interests");
    private final String PRODUCT_CATEGORIES_XPATH = "//h5[@data-auto-id='ma-shopping-preferences-title']/following-sibling::div//div[@class='gl-carousel__content']";
    private final String INTERESTS_CONTAINER_XPATH = "//h5[@data-auto-id='ma-interests-title']/following-sibling::div//div[@class='gl-carousel__content']/div[@role='list']";
    private final String INTEREST_TILES_XPATH = INTERESTS_CONTAINER_XPATH + "//div[contains(@data-auto-id, 'interest_')]";

    public PreferencesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clicksPreferencesButton() {
        WebElement preferencesButton = wait.until(ExpectedConditions.elementToBeClickable(PREFERENCES_BUTTON_XPATH));
        preferencesButton.click();
    }

    public void randomProductCategoriesPreferencesClick() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PRODUCT_CATEGORIES_XPATH)));
        List<WebElement> productCategories = driver.findElements(By.xpath(PRODUCT_CATEGORIES_XPATH));
        if (!productCategories.isEmpty()) {
            int randomIndex = rand.nextInt(productCategories.size());
            WebElement randomCategory = productCategories.get(randomIndex);
            randomCategory.click();
        }
    }

    public void randomProductInterestsPreferencesClick() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(INTERESTS_CONTAINER_XPATH)));
        List<WebElement> interestTiles = driver.findElements(By.xpath(INTEREST_TILES_XPATH));
        if (!interestTiles.isEmpty()) {
            int randomTileIndex = rand.nextInt(interestTiles.size());
            WebElement randomTile = interestTiles.get(randomTileIndex);
            randomTile.click();
        }
    }

    public void savePreferencesClick() {
        WebElement savePreferences = wait.until(ExpectedConditions.elementToBeClickable(SAVE_PREFERENCES_ID));
        savePreferences.click();
    }

    public void saveInterestsClick() {
        WebElement saveInterests = wait.until(ExpectedConditions.elementToBeClickable(SAVE_INTERESTS_XPATH));
        saveInterests.click();
    }
}
