package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Helper;

public class Header {
    private WebDriver driver;
    private WebDriverWait wait;
    private Helper helper;

    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = Base.getWait();
        this.helper = new Helper(driver);
    }

    public void hoverOverMensSection() {
        WebElement mensSection = driver.findElement(By.xpath("//a[@role='menu' and @href='/us/men']"));
        new Actions(driver).moveToElement(mensSection).perform();
    }

    public void navigateToSubCategory() {
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
            helper.closeStupidLoginPopup();
            hoverOverMensSection();
        }
    }

    public WebElement getSearchTextBox() {
        return driver.findElement(By.xpath("//input[@class=\"_input_1f3oz_13\"]"));
    }

    public WebElement getSearchResultTitle() {
        return driver.findElement(By.xpath("//h1[@class=\"gl-vspace heading___3g-L_ heading--search\"]"));
    }


}

