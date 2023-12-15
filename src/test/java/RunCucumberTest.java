import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        plugin = {"pretty"},
        monochrome = true,
        tags = "@test2"
)
public class RunCucumberTest {
    // This class will be empty and is used as a hook for the Cucumber test engine
}