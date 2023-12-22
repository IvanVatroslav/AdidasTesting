package ObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {
    private static WebDriver driver = Base.getDriver();


    public static List<WebElement> getSearchResult() {
        List<WebElement> productsList = driver.findElements(By.xpath("//div[@class='plp-grid___1FP1J']/div"));
        return productsList;
    }


    public static List<WebElement> getSearchResultTitle() {
        List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='plp-grid___1FP1J']/div//p[@class=\"glass-product-card__title\"]"));
        return productNames;
    }
}
