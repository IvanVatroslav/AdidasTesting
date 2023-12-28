package ObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {
    private static final By searchBoxLocator = By.xpath("//input[@class='_input_1f3oz_13']");

    private static final By noResultsMessageLocator = By.xpath("//h4[contains(@class, 'nohits_title___3kFIK') and contains(text(), 'NO RESULTS')]");

    private static WebDriver driver;
    static {
        driver = Base.getDriver();
    }

    public static List<WebElement> getSearchResults() {
        return driver.findElements(By.xpath("//*[@id=\"main-content\"]/div/div[2]/div/div/div[2]/div[1]"));
    }

    public static void searchFor(String keyword) {
        WebElement searchBox = driver.findElement(searchBoxLocator);
        searchBox.sendKeys(keyword + Keys.ENTER);
    }

    public static boolean isNoResultsDisplayed() {
        try {
            WebElement noResultsElement = driver.findElement(noResultsMessageLocator);
            return noResultsElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
