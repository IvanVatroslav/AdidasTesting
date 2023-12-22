package ObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class SettingsPage {
    static private By editDetailsButton_xpath = By.xpath("//button[@class='gl-cta gl-cta--tertiary gl-vspace']");
    static private By dayField_id = By.id("date-of-birth-day");
    static private By monthField_id = By.id("date-of-birth-month");
    static private By yearField_id = By.id("date-of-birth-year");
    static private By saveButton_xpath = By.xpath("//*[@id='modal-root']/div/div/div/div[2]/form/div[2]/button[1]/span[1]");

    static private By PreferencesButton_xpath = By.xpath("//*[@id=\"app\"]/div/div[1]/div[1]/div/div/div[6]/div/div[1]/ul/li[3]/a");

    static private Random rand = new Random();

    static private By savePreferences_id = By.id("preferences-update-CTA-product-categories");
    static private By saveInterests_xpath = By.xpath("//*[@id=\"preferences-update-CTA-interests\"]/span[2]");

    // Update the XPath as needed

    static private By addressBookLink_xpath = By.xpath("//a[@data-auto-id='members-home-account-address-book']");

    public static void navigateToAddressBookPage() {
        WebElement addressBookLink = Base.getWait().until(ExpectedConditions.elementToBeClickable(addressBookLink_xpath));
        addressBookLink.click();
    }

    public static void clickEditDetails() {
        WebElement editDetails = Base.getWait().until(ExpectedConditions.visibilityOfElementLocated(editDetailsButton_xpath));
        editDetails.click();
    }

    public static WebElement getDayField() {
        WebElement dayField = Base.getDriver().findElement(dayField_id);
        dayField.clear();
        return dayField;
    }

    public static WebElement getMonthField() {
        WebElement monthField = Base.getDriver().findElement(monthField_id);
        monthField.clear();
        return monthField;
    }

    public static WebElement getYearField() {
        WebElement yearField = Base.getDriver().findElement(yearField_id);
        yearField.clear();
        return yearField;
    }

    public static void clickSaveButton() {
        WebElement saveButton = Base.getDriver().findElement(saveButton_xpath);
        saveButton.click();
    }

    public static void clicksPreferencesButton() {
        WebElement PreferencesButton = Base.getWait().until(ExpectedConditions.elementToBeClickable(PreferencesButton_xpath));
        PreferencesButton.click();
    }


    public static void randomProductCategoriesPreferencesClick() {
        int randomDivIndex = rand.nextInt(4) + 1;
        String randomCategoriesElement = "//div[@class='gl-carousel__slider']//div[" + randomDivIndex + "]";
        By productCategoriesPreferences_xpath = By.xpath(randomCategoriesElement);

        WebElement productCategoriesPreferences = Base.getWait().until(ExpectedConditions.elementToBeClickable((productCategoriesPreferences_xpath)));
        productCategoriesPreferences.click();
    }

    public static void randomProductInterestsPreferencesClick() {
        int randomFifthDivIndex = rand.nextInt(9) + 1;

        // Generate a random number between 1 and 2 for the second div
        int randomSecondDivIndex = rand.nextInt(2) + 1;

        // Construct the XPath with the random indexes
        String xpath2 = "//div[@class='gl-carousel gl-carousel--show-pagination profile-interests___5fonZ']/div/div/div["
                + randomFifthDivIndex + "]/div/div[" + randomSecondDivIndex + "]";

        // Find the element using the constructed XPath
        WebElement randomElement = Base.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath2)));
        randomElement.click();
    }

    public static void savePreferencesClick() {
        WebElement savePreferences = Base.getWait().until(ExpectedConditions.elementToBeClickable(savePreferences_id));
        savePreferences.click();
    }

    public static void saveInterestsClick() {
        WebElement saveInterests = Base.getWait().until(ExpectedConditions.elementToBeClickable(saveInterests_xpath));
        saveInterests.click();
    }
}
