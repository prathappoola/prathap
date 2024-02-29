package com.lambdatest;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

public class TestScenario_3 
{
	public static String hubURL = "https://hub.lambdatest.com/wd/hub";
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "version", "platform"})
    public void setUp(String browser, String version, String platform) throws MalformedURLException {
    	 DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("platformName", platform);
   		capabilities.setCapability("browserName", "Chrome");
   		capabilities.setCapability("browserVersion", "122");
        capabilities.setCapability("build", "Selenium 4");
        capabilities.setCapability("name", this.getClass().getName());
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("console", "info");
        capabilities.setCapability("selenium_version", "4.0.0");
        capabilities.setCapability("tunnel", false);

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "karrichandu175");
        ltOptions.put("accessKey", "wU7N4CcrpROimZ0AXtyUdilYzpOJr7ReJiQgrtgF2sXOPHeBah");

        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new java.net.URL(hubURL), capabilities);
        System.out.println(driver);
    }
	
    @Test
    public void testScenariothree() throws InterruptedException {
	    	
	    	driver.get("https://www.lambdatest.com/selenium-playground");
	    	
	    	driver.findElement(By.partialLinkText("Input Form Subm")).click();
	    	
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	
	    	driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
	    	
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	
	    	String Excepted = driver.findElement(By.name("name")).getAttribute("validationMessage");
	    	System.out.println("user name " + Excepted);
	    
	    	Assert.assertEquals("Please fill out this field.", Excepted, "Both text same..........");
	    	
	    	driver.findElement(By.name("name")).sendKeys("chandu1");
	    	driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("chanduone@yopmail.com");
	    	driver.findElement(By.id("inputPassword4")).sendKeys("testing@1");
	    	driver.findElement(By.xpath("//input[@placeholder='Company']")).sendKeys("3i");
	    	driver.findElement(By.xpath("//input[@placeholder='Website']")).sendKeys("http://3i.com");
	    	
	    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	
	    	Select drpCountry = new Select(driver.findElement(By.xpath("//select[@name='country']")));
	    	drpCountry.selectByVisibleText("United States");
	    	
	    	Thread.sleep(3000);
	    	
	    	driver.findElement(By.xpath("//input[@placeholder='City']")).sendKeys("HYD");
	    	driver.findElement(By.xpath("//input[@placeholder='Address 1']")).sendKeys("UJYIIIsS");
	    	driver.findElement(By.xpath("//input[@placeholder='Address 2']")).sendKeys("1678IKDsKD");
	    	
	    	driver.findElement(By.xpath("//input[@placeholder='State']")).sendKeys("ON");
	    	
	    	driver.findElement(By.xpath("//input[@placeholder='Zip code']")).sendKeys("98766");
	    	
	    	driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
	    	
	    	String Actual ="Thanks for contacting us, we will get back to you shortly.";
	    	
	    	String Excpted =driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
	    	
	    	 Assert.assertEquals(Actual, Excpted, "Both text same..........");
	    	
	    	
	    	
	    }
	    
    @AfterMethod
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void markStatus(String status, String reason, WebDriver driver) {
        JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
        jsExecute.executeScript("lambda-status=" + status);
        System.out.println(reason);
    }

}
