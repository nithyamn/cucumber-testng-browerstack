package parallel;

import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.Keys;
import org.testng.asserts.Assertion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;


public class StepDefs {

    WebDriver driver;
    WebElement element;
    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME"); //OR String USERNAME = "<browserstack-username>"
    public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");//OR String AUTOMATE_KEY = "<browserstack-accesskey>"
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Given("Open Chrome")
    public void open_Chrome() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "76.0");
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "parallel_test");

        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @Given("Open Firefox")
    public void open_Firefox() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "70.0");
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "parallel_test");

        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @Given("Open Safari")
    public void open_Safari() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "Mojave");
        caps.setCapability("browser", "Safari");
        caps.setCapability("browser_version", "12.0");
        /*
        caps.setCapability("os_version", "9.0");
        caps.setCapability("device", "Samsung Galaxy S10");
        caps.setCapability("real_mobile", "true");
        */
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "parallel_test");

        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @When("Search for BrowserStack")
    public void search_for_BrowserStack() {
        //start a new chrome browser
        driver.get("https://www.google.co.in/");
        element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack", Keys.ENTER);
        //element.submit();
    }
    @Then("Display Title")
    public void display_Title() {
        String title = driver.getTitle();
        System.out.println(title);
        driver.quit();
    }

}
