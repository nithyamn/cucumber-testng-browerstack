package parallel;

import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.*;
import org.testng.asserts.Assertion;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import org.openqa.selenium.Keys;


public class StepDefs {

    WebDriver driver;
    JavascriptExecutor jse;
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
        caps.setCapability("browser_version", "latest");
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
        caps.setCapability("browser_version", "latest-1");
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
        caps.setCapability("browser_version", "latest");
        /*
        caps.setCapability("os_version", "9.0");
        caps.setCapability("device", "Samsung Galaxy S10");
        caps.setCapability("real_mobile", "true");
        */
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "parallel_test");

        driver = new RemoteWebDriver(new URL(URL), caps);
        jse = (JavascriptExecutor) driver;
    }

    @When("Search for BrowserStack")
    public void search_for_BrowserStack() {
        //start a new chrome browser
        driver.get("https://www.google.co.in/");
        try{
            driver.findElement(By.id("L2AGLb")).click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack", Keys.ENTER);
        //element.submit();
    }
    @Then("Display Title")
    public void display_Title() {
        String title = driver.getTitle();
        if(driver.getTitle().contains("BrowserStack"))
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Successful!\"}}");
        else
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Unsuccessful!\"}}");

        System.out.println(title);
        driver.quit();
    }

}
