package com.adactin.base;

import com.adactin.pages.*;
import com.adactin.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class BaseTest {
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

    public void browserDriverSetup(String browser) {

        if (browser.equalsIgnoreCase(Constants.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "C://Users//sathe//Downloads//chromedriver.exe");
            driver = new ChromeDriver();
            System.out.println("Chrome Browser Launched");
            driver.manage().window().maximize();
        }

        if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
            System.setProperty("webdriver.firefox.driver", "C://Users//sathe//Downloads//chromedriver.exe");
            driver = new FirefoxDriver();
            System.out.println("Firefox Browser Launched");
            driver.manage().window().maximize();
        }

        setDriver(driver);
    }

    public void goToURL(String url) {
        driver.get(url);
    }
}