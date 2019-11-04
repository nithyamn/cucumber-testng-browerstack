package single;

import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//Tags can be added
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/single/singleFeature.feature",
        glue = "single")
public class RunCucumberSingleTest extends AbstractTestNGCucumberTests {
}
