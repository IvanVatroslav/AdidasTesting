package ObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Helper;

public class Header {
    static WebDriver driver = Base.getDriver();
    static WebDriverWait wait = Base.getWait();

    public static void hoverOverMensSection() {
        WebElement mensSection = driver.findElement(By.xpath("//a[@role='menu' and @href='/us/men']"));
        new Actions(driver).moveToElement(mensSection).perform();
    }

    public static void navigateToSubCategory() {
        String[] subCategoryHrefs = {
                "/us/men-trending",
                "/us/men-shoes",
                "/us/men-clothing",
                "/us/men-accessories",
                "/us/men-performance",
                "/us/men?grid=true"
        };

        for (String href : subCategoryHrefs) {

            WebElement subCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='" + href + "']")));
            subCategoryElement.click();
            Helper.closeStupidLoginPopup();
            Header.hoverOverMensSection();

        }

    }

    public static WebElement getSearchTextBox() {
        WebElement searchBox = driver.findElement(By.xpath("//input[@class=\"_input_1f3oz_13\"]")); // Replace with your search box ID
        return searchBox;
    }

    public static WebElement getSearchResultTitle() {
        WebElement searchTitleElement = driver.findElement(By.xpath("//h1[@class=\"gl-vspace heading___3g-L_ heading--search\"]"));
        return searchTitleElement;
    }



}
