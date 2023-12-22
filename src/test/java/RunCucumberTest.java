import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"ObjectPage", "stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false,
        tags = "" +
 //               " @MainNavigationMenu " +
                "  @MenSectionSubcategories"
             //   " or @SearchFunctionality"
             //   " or @SearchFunctionality_invalid"
                //" or @AddressBook"
)
public class RunCucumberTest {

}