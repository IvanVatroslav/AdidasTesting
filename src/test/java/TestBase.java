import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to the chromedriver executable

        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        // Additional setup can be done here (like maximizing window, setting timeouts, etc.)
    }

    @After
    public void tearDown() {
        // Close the browser and WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
