package services;

import objectpage.pages.account.AddressBookPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;

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


    public void addNewAddress(String firstName, String lastName, String streetAddress, String city, String state, String zipCode, String phoneNumber) {
        basePage.waitForModalInvisibility();
        wait.until(ExpectedConditions.elementToBeClickable(PLUS_BUTTON_XPATH)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_INPUT_XPATH)).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT_XPATH).sendKeys(lastName);
        driver.findElement(ADDRESS_INPUT_XPATH).sendKeys(streetAddress);
        driver.findElement(CITY_INPUT_XPATH).sendKeys(city);
        selectSpecificState(state);
        driver.findElement(ZIPCODE_INPUT_XPATH).sendKeys(zipCode);
        driver.findElement(PHONE_NUMBER_INPUT_XPATH).sendKeys(phoneNumber);
        driver.findElement(ARROW_RIGHT_LONG_BUTTON_XPATH).click();
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


    public void assertAddresses(List<Map<String, String>> expectedAddresses) {
        basePage.waitForModalInvisibility();
        List<WebElement> addressElements = driver.findElements(SAVED_ADDRESS_DIV_XPATH);
        assertEquals("Number of addresses does not match", expectedAddresses.size(), addressElements.size());

        for (int i = 0; i < expectedAddresses.size(); i++) {
            Map<String, String> expectedAddress = expectedAddresses.get(i);
            WebElement addressElement = addressElements.get(i);

            String expectedFullName = expectedAddress.get("FirstName") + " " + expectedAddress.get("LastName");
            assertEquals(expectedFullName, addressElement.findElement(STRONG_TAG_XPATH).getText());

            String fullAddress = addressElement.findElement(ADDRESS_DETAIL_DIV_XPATH).getText();
            String[] addressParts = fullAddress.split("\n");

            assertEquals(expectedAddress.get("Address"), addressParts[0]);
            String expectedStateAbbreviation = convertStateNameToAbbreviation(expectedAddress.get("State"));
            String expectedCityStateZip = expectedAddress.get("City") + ", " + expectedStateAbbreviation + ", " + expectedAddress.get("Zip") + ", US";
            assertEquals(expectedCityStateZip, addressParts[1]);
            assertEquals(expectedAddress.get("Phone"), addressParts[2]);
        }
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



