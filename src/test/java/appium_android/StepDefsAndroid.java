package appium_android;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class StepDefsAndroid {

    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME"); //OR String USERNAME = "<browserstack-username>"
    public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");//OR String AUTOMATE_KEY = "<browserstack-accesskey>"
    DesiredCapabilities caps;
    public AndroidDriver<AndroidElement> driver;
    String hash_id="";

    @Given("Open Application")
    public void open_Application() throws MalformedURLException {
        hash_id = UploadApp.upload(USERNAME,AUTOMATE_KEY);
        caps = new DesiredCapabilities();
        caps.setCapability("device", "Samsung Galaxy S8 Plus");
        caps.setCapability("os_version", "7.0");
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "single_android_test");
        caps.setCapability("app",hash_id);
        driver = new AndroidDriver<AndroidElement>(new URL("http://"+USERNAME+":"+AUTOMATE_KEY+"@hub-cloud.browserstack.com/wd/hub"), caps);
    }

    @When("Search for BrowserStack")
    public void search_for_BrowserStack() throws InterruptedException {
        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);

        List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
        assert(allProductsName.size() > 0);
    }

    @Then("Return result and close application")
    public void return_Result_And_Close_Application(){
        driver.quit();
    }

}
