package local;

import com.browserstack.local.Local;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class StepDefs {
    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME"); //OR String USERNAME = "<browserstack-username>"
    public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");//OR String AUTOMATE_KEY = "<browserstack-accesskey>"
    //public static final String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
    //public static final String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    DesiredCapabilities caps;
    WebDriver driver;
    private static Local l;
    WebElement element;

    @Given("Open Browser")
    public void open_Browser() throws Exception {
        caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "75.0");
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "local_test");
        caps.setCapability("browserstack.local", "true");
        //caps.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);

        if (caps.getCapability("browserstack.local") != null && caps.getCapability("browserstack.local") == "true") {
            //System.out.println("INSIDE CODE BINDINGS");
            l = new Local();
            //System.out.println("Local object: " + l);
            //caps.setCapability("browserstack.local", browserstackLocal);//Note: the value for browserstack.local should be String and not boolean.
            //caps.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);

            if (caps.getCapability("browserstack.local") != null && caps.getCapability("browserstack.local") == "true") {
                l = new Local();
                Map<String, String> options = new HashMap<String, String>();
                options.put("key", AUTOMATE_KEY);
                l.start(options);
            }
            driver = new RemoteWebDriver(new URL(URL), caps);
        }
    }
    @When("Go to localhost")
    public void go_to_localhost () {
        driver.get("http://bs-local.com:45691/check");
    }

    @Then("Retrieve Title if Up and Running")
    public void retrieve_Title_if_Up_and_Running () throws Exception {
        System.out.println(driver.getTitle());
        driver.quit();
        if (l != null) {
            l.stop();
        }
        if (l != null) l.stop();
    }
}
