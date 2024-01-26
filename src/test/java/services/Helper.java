package services;

import objectpage.pages.account.AddressBookPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Helper {

    private static int randomYear;
    private Random rand;
    private WebDriver driver;
    private WebDriverWait wait;


    private final AddressBookPage basePage;



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








    private String convertStateNameToAbbreviation(String stateName) {
        Map<String, String> stateAbbreviations = new HashMap<>();
        stateAbbreviations.put("Florida", "FL");
        stateAbbreviations.put("Maine", "ME");

        return stateAbbreviations.getOrDefault(stateName, stateName);
    }

    public static void replaceText(WebElement element, String text) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
    }


    public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot newScreen = (TakesScreenshot) driver;
        File src = newScreen.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png";
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }

}



