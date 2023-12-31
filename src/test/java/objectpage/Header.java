package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Header {
    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;

    }

    public void hoverOverMensSection() {
        WebElement mensSection = driver.findElement(By.xpath("//a[@role='menu' and @href='/us/men']"));
        new Actions(driver).moveToElement(mensSection).perform();
    }


    public WebElement getSearchTextBox() {
        return driver.findElement(By.xpath("//input[@data-auto-id=\"searchinput-desktop\"]"));
    }




}

