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
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Helper {

    private static int randomMonth;
    private static int randomDay;
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
        // Generate a random month between 1 and 12
        randomMonth = rand.nextInt(12) + 1; // months: 1-12

        // Generate a random day. Days range from 1-28/30/31 depending on the month
        if (randomMonth == 2) { // February
            // Check for leap year
            if (randomYear % 4 == 0 && (randomYear % 100 != 0 || randomYear % 400 == 0)) {
                randomDay = rand.nextInt(29) + 1; // 1-29 for leap year February
            } else {
                randomDay = rand.nextInt(28) + 1; // 1-28 for non-leap year February
            }
        } else if (randomMonth == 4 || randomMonth == 6 || randomMonth == 9 || randomMonth == 11) {
            randomDay = rand.nextInt(30) + 1; // 1-30 for April, June, September, November
        } else {
            randomDay = rand.nextInt(31) + 1; // 1-31 for all other months
        }

        // Generate a random year (example range: 1900 - current year)
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
            // Load the properties file
            prop.load(input);

            // Get username and password from properties file
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            // Perform the login process using the username and password
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
        // Navigate to the page where the menu is located
        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@data-auto-id='main-menu']/li"));
        int itemCount = menuItems.size();
        for (int i = 1; i <= driver.findElements(By.xpath("//ul[@data-auto-id='main-menu']/li")).size(); i++) {


            closeStupidLoginPopup();


            // Refresh the element reference to avoid stale element reference issues
            WebElement item = driver.findElement(By.xpath("//ul[@data-auto-id='main-menu']/li[" + i + "]"));

            wait.until(ExpectedConditions.elementToBeClickable(item)).click();
            // Wait for the page to load and perform your checks here
            // ... additional verification logic ...

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@data-auto-id='main-menu']")));
        }
    }

    public void closeStupidLoginPopup() {
        List<WebElement> closeElements = Base.getDriver().findElements(By.xpath("//*[@id=\"account-portal-modal\"]/div/div/button/span"));
        if (!closeElements.isEmpty()) { //close stupid popup
            WebElement closeElement = closeElements.get(0);
            if (closeElement.isDisplayed()) {
                closeElement.click(); // Close the element
                Base.getWait().until(ExpectedConditions.invisibilityOf(closeElement)); // Wait for the element to disappear
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

                // Wait for the confirmation button to be clickable after clicking "Remove"
                WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-auto-id='confirm-delete']")));
                confirmDeleteButton.click();

                // Wait for the DOM to update after the address has been deleted
                // Replace Thread.sleep with dynamic wait if possible
                Thread.sleep(1000);
            } catch (StaleElementReferenceException e) {
                // Element reference is stale, refresh the elements list by continuing the loop
                System.out.println("Caught a stale element exception, retrying...");
            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    public void addNewAddress(String firstName, String lastName, String streetAddress, String city, String state, String zipCode, String phoneNumber) {
       // driver.findElement(By.xpath("//span[@data-testid=\"plus\"]")).click();
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
        // Click the dropdown to display the options
        WebElement dropdown = driver.findElement(By.xpath("//div[@role='combobox']"));
        dropdown.click();

        // Wait for the dropdown options to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gl-dropdown-custom__listbox--checkout-dropdown")));

        // Find all dropdown options
        List<WebElement> stateOptions = driver.findElements(By.xpath("//ul[@id='gl-dropdown-custom__listbox--checkout-dropdown']/li"));

        // Generate a random index to select
        Random rand = new Random();
        WebElement randomOption = stateOptions.get(rand.nextInt(stateOptions.size()));

        // Scroll into view of the random option and click it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomOption);
        randomOption.click();
    }


}
