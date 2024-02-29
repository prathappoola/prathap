package Rama_test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestScenario_2 {

    public static String hubURL = "https://hub.lambdatest.com/wd/hub";
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "version", "platform"})
    public void setUp(String browser, String version, String platform) throws MalformedURLException {
    	// public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "95");
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("build", "Selenium 4");
        capabilities.setCapability("name", this.getClass().getName());
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("console", "info");
        capabilities.setCapability("selenium_version", "4.0.0");
        capabilities.setCapability("tunnel", false);

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "topennms");
        ltOptions.put("accessKey", "hBpdTFnFrRNrweezqtnpr4xer9eUImuRnza3m27HgUFVIgVgaU");


        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new java.net.URL(hubURL), capabilities);
        System.out.println(driver);
    }

    @Test
    public void TestScenario2() {
    	
        driver.get("https://www.lambdatest.com/selenium-playground");

        driver.findElement(By.xpath("//a[normalize-space()='Drag & Drop Sliders']")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement slider = driver.findElement(By.xpath("(//input[@class='sp__range'])[3]"));

        int maxValue = Integer.parseInt(slider.getAttribute("max"));

        int desiredValue = (int) (0.95 * maxValue);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1]", slider, desiredValue);

        String slidervalue = slider.getAttribute("value");
        System.out.println("The value of the slider: " + slidervalue);
    }

    
    @AfterMethod
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
