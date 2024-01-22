package stepdefinitions.stepswithlogin;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.nonpages.components.Header;
import objectpage.nonpages.components.SidePanel;
import objectpage.pages.account.MyAccountPage;
import objectpage.pages.account.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LogoutSteps {
    private WebDriver driver;
    private Header header;
    private SidePanel sidePanel;
    private ProfilePage profilePage;
private MyAccountPage myAccountPage;
    public LogoutSteps() {
        this.driver = Hooks.driver.get();
        this.header = new Header(driver);
        this.sidePanel = new SidePanel(driver);
        this.profilePage = new ProfilePage(driver);
        this.myAccountPage = new MyAccountPage(driver);
    }

    @When("the user logs out")
    public void theUserLogsOut() {
        header.getAccountPortalTrigger().click();
        sidePanel.clickAccountLink();
        myAccountPage.clickOnAccountSection();

        profilePage.clickLogOutButton();
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLogin() {
        String expectedUrl = "https://www.adidas.com/us/account-login";
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        assertEquals("User is not redirected to the login page after logout.", expectedUrl, actualUrl);
    }
}
