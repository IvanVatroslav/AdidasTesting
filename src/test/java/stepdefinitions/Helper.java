package stepdefinitions;

import ObjectPage.Base;
import ObjectPage.MainPage;
import ObjectPage.SettingsPage;
import ObjectPage.YahooPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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


    public int[] getRandomDate() {
        int randomMonth = rand.nextInt(12) + 1; // months: 1-12

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

    public void testNavigationMenuItems() {
        WebDriver driver = Base.getDriver();
        WebDriverWait wait = Base.getWait();
        for (int i = 1; i <= driver.findElements(By.xpath("//ul[@data-auto-id='main-menu']/li")).size(); i++) {


            closeStupidLoginPopup();

            WebElement item = driver.findElement(By.xpath("//ul[@data-auto-id='main-menu']/li[" + i + "]"));

            wait.until(ExpectedConditions.elementToBeClickable(item)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@data-auto-id='main-menu']")));
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

    public void addNewAddress(String firstName, String lastName, String streetAddress, String city, String zipCode, String phoneNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-testid=\"plus\"]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"firstName\"]"))).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name=\"lastName\"]")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@name=\"address1\"]")).sendKeys(streetAddress);
        driver.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys(city);
        selectRandomState();
        driver.findElement(By.xpath("//input[@name=\"zipcode\"]")).sendKeys(zipCode);
        driver.findElement(By.xpath("//input[@name=\"phoneNumber\"]")).sendKeys(phoneNumber);

        driver.findElement(By.xpath("//span[@data-testid=\"arrow-right-long\"]")).click();
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

    public void checkWebPage(String url) {
        wait.until(ExpectedConditions.urlToBe(url));

        String currentUrl = driver.getCurrentUrl();

        assertEquals("The user is not on the" + url + "page", "https://www.adidas.com/us", currentUrl);
    }


    public void assertAddresses(){


        WebElement firstAddressLineElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SettingsPage.getFirstAddressLineXPath()));
        String firstAddressLine = firstAddressLineElement.getText();

        WebElement secondAddressLineElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SettingsPage.getSecondAddressLineXPath()));
        String secondAddressLine = secondAddressLineElement.getText();


        // Check if the address lines contain the expected addresses
        boolean isFirstAddressPresent = firstAddressLine.contains("123 Main St");
        boolean isSecondAddressPresent = secondAddressLine.contains("456 Elm Street");

        assertTrue("First address (123 Main St) is not displayed in the address book", isFirstAddressPresent);
        assertTrue("Second address (456 Elm Street) is not displayed in the address book", isSecondAddressPresent);
    }

}
