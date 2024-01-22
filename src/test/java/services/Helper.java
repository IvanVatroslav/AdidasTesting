package services;

import objectpage.pages.account.AddressBookPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Helper {

    private static int randomYear;
    private Random rand;
    private WebDriver driver;
    private WebDriverWait wait;

    //temporary place for these constants
    private static final By DELETE_ADDRESS_BUTTON_XPATH = By.xpath("//button[@data-auto-id='delete_address']"); // AddressBookPage class
    private static final By CONFIRM_DELETE_BUTTON_XPATH = By.xpath("//button[@data-auto-id='confirm-delete']"); // AddressBookPage class
    private static final By PLUS_BUTTON_XPATH = By.xpath("//span[@data-testid='plus']"); // AddressBookPage class
    private static final By FIRST_NAME_INPUT_XPATH = By.xpath("//input[@name='firstName']"); // AddressBookPage class
    private static final By LAST_NAME_INPUT_XPATH = By.xpath("//input[@name='lastName']"); // AddressBookPage class
    private static final By ADDRESS_INPUT_XPATH = By.xpath("//input[@name='address1']"); // AddressBookPage class
    private static final By CITY_INPUT_XPATH = By.xpath("//input[@name='city']"); // AddressBookPage class
    private static final By ZIPCODE_INPUT_XPATH = By.xpath("//input[@name='zipcode']"); // AddressBookPage class
    private static final By PHONE_NUMBER_INPUT_XPATH = By.xpath("//input[@name='phoneNumber']"); // AddressBookPage class
    private static final By ARROW_RIGHT_LONG_BUTTON_XPATH = By.xpath("//span[@data-testid='arrow-right-long']"); // AddressBookPage class
    private static final By COMBOBOX_DIV_XPATH = By.xpath("//div[@role='combobox']"); // AddressBookPage class
    private static final By CHECKOUT_DROPDOWN_LIST_XPATH = By.id("gl-dropdown-custom__listbox--checkout-dropdown"); // AddressBookPage class
    private static final By SAVED_ADDRESS_DIV_XPATH = By.xpath("//div[@data-auto-id='saved_address']"); // AddressBookPage class
    private static final By STRONG_TAG_XPATH = By.xpath(".//strong"); // AddressBookPage class
    private static final By ADDRESS_DETAIL_DIV_XPATH = By.xpath(".//div[contains(@class, 'gl-vspace-bpall-small')]"); // AddressBookPage class
    final static Logger logger = Logger.getLogger(Helper.class);

    private final AddressBookPage basePage;


    private String getStateXPath(String stateName) {
        return String.format("//ul[@id='gl-dropdown-custom__listbox--checkout-dropdown']/li[contains(text(), '%s')]", stateName);
    }


    public Helper() {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();
        this.basePage = new AddressBookPage(driver);
        this.rand = new Random();
    }

    public boolean doesElementExist(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public int[] getRandomDate() {
        int randomMonth = rand.nextInt(12) + 1;

        int randomDay;
        if (randomMonth == 2) {
            // Check for leap year
            if (randomYear % 4 == 0 && (randomYear % 100 != 0 || randomYear % 400 == 0)) {
                randomDay = rand.nextInt(29) + 1;
            } else {
                randomDay = rand.nextInt(28) + 1;
            }
        } else if (randomMonth == 4 || randomMonth == 6 || randomMonth == 9 || randomMonth == 11) {
            randomDay = rand.nextInt(30) + 1;
        } else {
            randomDay = rand.nextInt(31) + 1;
        }

        int currentYear = LocalDate.now().getYear();
        int latestYearOfBirth = currentYear - 13; // Only 13+ years old can register
        randomYear = rand.nextInt(latestYearOfBirth - 1900 + 1) + 1900;
        return new int[]{randomDay, randomMonth, randomYear};
    }




    public void selectSpecificState(String stateName) {
        WebElement dropdown = driver.findElement(COMBOBOX_DIV_XPATH);
        dropdown.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_DROPDOWN_LIST_XPATH));

        String stateXPath = getStateXPath(stateName);
        WebElement stateOption = driver.findElement(By.xpath(stateXPath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateOption);
        stateOption.click();
    }





    private String convertStateNameToAbbreviation(String stateName) {
        Map<String, String> stateAbbreviations = new HashMap<>();
        stateAbbreviations.put("Florida", "FL");
        stateAbbreviations.put("Maine", "ME");

        return stateAbbreviations.getOrDefault(stateName, stateName);
    }

    public static void replaceText(WebElement element, String text) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
    }
}



