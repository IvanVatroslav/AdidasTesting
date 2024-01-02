package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class SettingsPage {
    static private final By EDIT_DETAILS_BUTTON_XPATH = By.xpath("//button[@class='gl-cta gl-cta--tertiary gl-vspace']");
    static private final By DAY_FIELD_ID = By.id("date-of-birth-day");
    static private final By MONTH_FIELD_ID = By.id("date-of-birth-month");
    static private final By YEAR_FIELD_ID = By.id("date-of-birth-year");
    static private final By SAVE_BUTTON_XPATH = By.xpath("//button[@data-auto-id='personal-info:button.submit']");

    static private final By PREFERENCES_BUTTON_XPATH = By.xpath("//a[@data-auto-id='members-home-account-preferences']");


    static private final By SAVE_PREFERENCES_ID = By.id("preferences-update-CTA-product-categories");
    static private final By SAVE_INTERESTS_XPATH = By.id("preferences-update-CTA-interests");


    static private final By ADDRESS_BOOK_LINK_XPATH = By.xpath("//a[@data-auto-id='members-home-account-address-book']");
    private static final String PRODUCT_CATEGORIES_XPATH = "//h5[@data-auto-id='ma-shopping-preferences-title']/following-sibling::div//div[@class='gl-carousel__content']";

    private static final By DATE_OF_BIRTH_OVERVIEW_XPATH = By.xpath("//div[@data-auto-id='ma-dateOfBirth-overview']");

    private static final String INTERESTS_CONTAINER_XPATH = "//h5[@data-auto-id='ma-interests-title']/following-sibling::div//div[@class='gl-carousel__content']/div[@role='list']";
    private static final String INTEREST_TILES_XPATH = INTERESTS_CONTAINER_XPATH + "//div[contains(@data-auto-id, 'interest_')]";
    static private final Random rand = new Random();

    public static WebElement getDateOfBirthOverview() {
        Base.getWait().until(ExpectedConditions.visibilityOfElementLocated(DATE_OF_BIRTH_OVERVIEW_XPATH));
        return Base.getDriver().findElement(DATE_OF_BIRTH_OVERVIEW_XPATH);
    }
    public static void navigateToAddressBookPage() {
        WebElement addressBookLink = Base.getWait().until(ExpectedConditions.elementToBeClickable(ADDRESS_BOOK_LINK_XPATH));
        addressBookLink.click();
    }

    public static void clickEditDetails() {
        WebElement editDetails = Base.getWait().until(ExpectedConditions.visibilityOfElementLocated(EDIT_DETAILS_BUTTON_XPATH));
        editDetails.click();
    }

    public static WebElement getDayField() {
        WebElement dayField = Base.getDriver().findElement(DAY_FIELD_ID);
        dayField.clear();
        return dayField;
    }

    public static WebElement getMonthField() {
        WebElement monthField = Base.getDriver().findElement(MONTH_FIELD_ID);
        monthField.clear();
        return monthField;
    }

    public static WebElement getYearField() {
        WebElement yearField = Base.getDriver().findElement(YEAR_FIELD_ID);
        yearField.clear();
        return yearField;
    }

    public static void clickSaveButton() {
        WebElement saveButton = Base.getDriver().findElement(SAVE_BUTTON_XPATH);
        saveButton.click();
    }

    public static void clicksPreferencesButton() {
        WebElement PreferencesButton = Base.getWait().until(ExpectedConditions.elementToBeClickable(PREFERENCES_BUTTON_XPATH));
        PreferencesButton.click();
    }


    public static void randomProductCategoriesPreferencesClick() {
        Base.getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(PRODUCT_CATEGORIES_XPATH)));

        List<WebElement> productCategories = Base.getDriver().findElements(By.xpath(PRODUCT_CATEGORIES_XPATH));


        if (!productCategories.isEmpty()) {
            int randomIndex = rand.nextInt(productCategories.size());

            WebElement randomCategory = productCategories.get(randomIndex);
            randomCategory.click();
        } else {
            System.out.println("No product categories found.");
        }
    }


    public static void randomProductInterestsPreferencesClick() {
        Base.getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(INTERESTS_CONTAINER_XPATH)));

        List<WebElement> interestTiles = Base.getDriver().findElements(By.xpath(INTEREST_TILES_XPATH));

        if (!interestTiles.isEmpty()) {
            int randomTileIndex = rand.nextInt(interestTiles.size());

            WebElement randomTile = interestTiles.get(randomTileIndex);
            randomTile.click();
        } else {
            System.out.println("No interest tiles found.");
        }
    }




    public static void savePreferencesClick() {
        WebElement savePreferences = Base.getWait().until(ExpectedConditions.elementToBeClickable(SAVE_PREFERENCES_ID));
        savePreferences.click();
    }

    public static void saveInterestsClick() {
        WebElement saveInterests = Base.getWait().until(ExpectedConditions.elementToBeClickable(SAVE_INTERESTS_XPATH));
        saveInterests.click();
    }


}
