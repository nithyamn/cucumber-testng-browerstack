package local;

import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//Tags can be added
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/local/localFeature.feature",
        glue = "local")
public class RunCucumberLocalTest extends AbstractTestNGCucumberTests {
}
