package stepdefinitions;

import ObjectPage.Base;
import ObjectPage.MainPage;
import ObjectPage.YahooPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Helper {

    private static int randomYear;
    private Random rand;

    private WebDriver driver;
    private WebDriverWait wait;

    public Helper(WebDriver driver) {
        this.driver = driver;
        this.wait = Base.getWait();
        this.rand = new Random();

    }
    public void checkWebPage(String url) {
        wait.until(ExpectedConditions.urlToBe(url));

        String currentUrl = driver.getCurrentUrl();

        assertEquals("The user is not on the" + url + "page", "https://www.adidas.com/us", currentUrl);
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

        randomYear = rand.nextInt(LocalDate.now().getYear() - 1900) + 1900;
        return new int[]{randomDay, randomMonth, randomYear};
    }

    public void logIn() {
        Properties prop = new Properties();
        try (InputStream input = Helper.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            prop.load(input);

            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            Base.getWait().until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            MainPage.openCustomerInfo();
            MainPage.clickYahooButton();
            YahooPage.getLoginTextBox().sendKeys(username);
            YahooPage.clickNextButtonLogin();
            YahooPage.getPasswordTextBox().sendKeys(password);
            YahooPage.clickLoginButton();
            YahooPage.clickAuthButton();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void testNavigationMenuItems(List<String> menuItems) {
        List<WebElement> foundMenuItems = driver.findElements(By.xpath("//ul[@data-auto-id='main-menu']/li/a"));

        assertEquals("Number of menu items should match", menuItems.size(), foundMenuItems.size());

        for (int i = 0; i < menuItems.size(); i++) {
            WebElement menuItem = foundMenuItems.get(i);
            String actualText = menuItem.getText().trim();
            String expectedText = menuItems.get(i);

            assertTrue("Menu item should be visible", menuItem.isDisplayed());
            assertEquals("Menu item text should match", expectedText, actualText);
        }
    }


    public void closeStupidLoginPopup() {
        List<WebElement> closeElements = Base.getDriver().findElements(By.xpath("//*[@id=\"account-portal-modal\"]/div/div/button/span"));
        if (!closeElements.isEmpty()) {
            WebElement closeElement = closeElements.get(0);
            if (closeElement.isDisplayed()) {
                closeElement.click();
                Base.getWait().until(ExpectedConditions.invisibilityOf(closeElement));
            }
        }
    }


    public void removeAllAddresses() {
        while (true) {
            List<WebElement> removeButtons = driver.findElements(By.xpath("//button[@data-auto-id='delete_address']"));

            if (removeButtons.isEmpty()) {
                break;
            }

            try {
                WebElement firstRemoveButton = removeButtons.get(0);
                firstRemoveButton.click();

                WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-auto-id='confirm-delete']")));
                confirmDeleteButton.click();

                Thread.sleep(1000);
            } catch (StaleElementReferenceException e) {
                System.out.println("Caught a stale element exception, retrying...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    public void addNewAddress(String firstName, String lastName, String streetAddress, String city, String state, String zipCode, String phoneNumber) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='gl-modal__main']")));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-testid='plus']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']"))).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(streetAddress);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);

        selectSpecificState(state);

        driver.findElement(By.xpath("//input[@name='zipcode']")).sendKeys(zipCode);
        driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phoneNumber);

        driver.findElement(By.xpath("//span[@data-testid='arrow-right-long']")).click();
    }


    public void selectRandomState() {
        WebElement dropdown = driver.findElement(By.xpath("//div[@role='combobox']"));
        dropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gl-dropdown-custom__listbox--checkout-dropdown")));

        List<WebElement> stateOptions = driver.findElements(By.xpath("//ul[@id='gl-dropdown-custom__listbox--checkout-dropdown']/li"));

        Random rand = new Random();
        WebElement randomOption = stateOptions.get(rand.nextInt(stateOptions.size()));

        // Scroll into view of the random option and click it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomOption);
        randomOption.click();
    }

    public void selectSpecificState(String stateName) {
        WebElement dropdown = driver.findElement(By.xpath("//div[@role='combobox']"));
        dropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gl-dropdown-custom__listbox--checkout-dropdown")));

        WebElement stateOption = driver.findElement(By.xpath("//ul[@id='gl-dropdown-custom__listbox--checkout-dropdown']/li[contains(text(), '" + stateName + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateOption);
        stateOption.click();
    }


    public void assertAddresses(List<Map<String, String>> expectedAddresses) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='gl-modal__main']")));


        List<WebElement> addressElements = driver.findElements(By.xpath("//div[@data-auto-id='saved_address']"));

        assertEquals("Number of addresses does not match", expectedAddresses.size(), addressElements.size());

        for (int i = 0; i < expectedAddresses.size(); i++) {
            Map<String, String> expectedAddress = expectedAddresses.get(i);
            WebElement addressElement = addressElements.get(i);

            assertEquals(expectedAddress.get("FirstName") + " " + expectedAddress.get("LastName"),
                    addressElement.findElement(By.xpath(".//strong")).getText());

            String fullAddress = addressElement.findElement(By.xpath(".//div[contains(@class, 'gl-vspace-bpall-small')]")).getText();
            String[] addressParts = fullAddress.split("\n");

            assertEquals(expectedAddress.get("Address"), addressParts[0]);
            String expectedStateAbbreviation = convertStateNameToAbbreviation(expectedAddress.get("State"));
            assertEquals(expectedAddress.get("City") + ", " + expectedStateAbbreviation + ", " + expectedAddress.get("Zip") + ", US", addressParts[1]);
            assertEquals(expectedAddress.get("Phone"), addressParts[2]);
        }
    }



    private String convertStateNameToAbbreviation(String stateName) {
        Map<String, String> stateAbbreviations = new HashMap<>();
        stateAbbreviations.put("Florida", "FL");
        stateAbbreviations.put("Maine", "ME");

        return stateAbbreviations.getOrDefault(stateName, stateName);
    }


}



