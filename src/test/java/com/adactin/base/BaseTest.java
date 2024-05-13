package com.adactin.base;

import com.adactin.pages.*;
import com.adactin.utilities.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class BaseTest {
    String browser;
    protected LoginPage loginPage;
    protected SearchHotelPage searchHotelPage;
    protected BookingHotelPage bookingHotelPage;
    protected BookingConfirmationPage bookingConfirmationPage;
    protected LogoutPage logoutPage;
    protected WebDriverWait wait;

    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeSuite
    public void frameworkInitialize() {
        System.out.println("***** Framework Initialized *****");
    }

    @AfterSuite
    public void frameworkComplete() {
        System.out.println("***** Framework Completed *****");
    }

    public void browserDriverSetup() {

        browser = System.getProperty("browserProperty");

        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("Chrome Browser Launched");
            driver.manage().window().maximize();
        }

        if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            System.out.println("Edge Browser Launched");
            driver.manage().window().maximize();
        }

        setDriver(driver);
    }

    public void goToURL(String url) {
        driver.get(url);
    }
}