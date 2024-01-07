package stepdefinitions;

import objectpage.BasePage;
import objectpage.HeaderPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    private static String getStateXPath(String stateName) { //AddressBookPage class
        return String.format("//ul[@id='gl-dropdown-custom__listbox--checkout-dropdown']/li[contains(text(), '%s')]", stateName);
    }



    public Helper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Example timeout
        this.rand = new Random();
    }
    public boolean doesElementExist(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void checkWebPage(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
        String currentUrl = driver.getCurrentUrl();
        assertEquals("The user is not on the " + url + " page", "https://www.adidas.com/us", currentUrl);
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

        randomYear = rand.nextInt(LocalDate.now().getYear() - 1900) + 1900 - 13;    // 13 years old or older
        return new int[]{randomDay, randomMonth, randomYear};
    }




    public void testNavigationMenuItems(List<String> menuItems) {
        for (String menuItemText : menuItems) {
            By menuItemXPath = HeaderPage.getMenSubcategoryXPath(menuItemText);
            WebElement menuItem = driver.findElement(menuItemXPath);
            assertTrue("Menu item should be visible", menuItem.isDisplayed());
            assertEquals("Menu item text should match", menuItemText, menuItem.getText().trim());
        }
    }



    public void removeAllAddresses() {
        while (true) {
            List<WebElement> removeButtons = driver.findElements(DELETE_ADDRESS_BUTTON_XPATH);
            if (removeButtons.isEmpty()) {
                break;
            }
            try {
                removeButtons.get(0).click();
                WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_DELETE_BUTTON_XPATH));
                confirmDeleteButton.click();
                BasePage.waitForModalInvisibility();
            } catch (StaleElementReferenceException e) {
                System.out.println("Caught a stale element exception, retrying...");
            }
        }
    }


    public void addNewAddress(String firstName, String lastName, String streetAddress, String city, String state, String zipCode, String phoneNumber) {
        BasePage.waitForModalInvisibility();
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
        BasePage.waitForModalInvisibility();
        List<WebElement> addressElements = driver.findElements(SAVED_ADDRESS_DIV_XPATH);
        assertEquals("Number of addresses does not match", expectedAddresses.size(), addressElements.size());

        for (int i = 0; i < expectedAddresses.size(); i++) {
            Map<String, String> expectedAddress = expectedAddresses.get(i);
            WebElement addressElement = addressElements.get(i);

            String expectedFullName = expectedAddress.get("FirstName") + " " + expectedAddress.get("LastName");
            assertEquals(expectedFullName, addressElement.findElement(STRONG_TAG_XPATH).getText());

            // Using the ADDRESS_DETAIL_DIV_XPATH constant
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



