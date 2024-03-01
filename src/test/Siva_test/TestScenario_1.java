package Siva_test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestScenario_1 {

    public static String hubURL = "https://hub.lambdatest.com/wd/hub";
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "version", "platform"})
    public void setUp(String browser, String version, String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
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
        ltOptions.put("user", "mullurisiva");
        ltOptions.put("accessKey", "CVLrm6ctyWxpw2X8o6UvHnU95mSj8lD3nRwuppMvevK68UWlkl");

        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new java.net.URL(hubURL), capabilities);
        System.out.println(driver);
    }

    @Test
    public void TestScenario1() {
        try {
        	
            driver.get("https://www.lambdatest.com/selenium-playground");
            
            driver.findElement(By.linkText("Simple Form Demo")).click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            if (driver.getCurrentUrl().contains("simple-form-demo")) {
                System.out.println("URL contains 'simple-form-demo'");
            } else {
                System.out.println("URL does not contain 'simple-form-demo'");
            }

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            String expected = "Welcome to LambdaTest";

            driver.findElement(By.id("user-message")).sendKeys(expected);

            driver.findElement(By.xpath("//button[normalize-space()='Get Checked Value']")).click();

            String actual = driver.findElement(By.id("message")).getText();

            Assert.assertEquals(actual, expected, "Both text same..........");

            if (expected.equalsIgnoreCase(actual)) {
                System.out.println("Message Same.............'");
            } else {
                System.out.println("Message different.............'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
