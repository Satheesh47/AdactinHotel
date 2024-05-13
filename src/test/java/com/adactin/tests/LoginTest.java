package com.adactin.tests;

import com.adactin.base.BaseTest;
import com.adactin.pages.LoginPage;
import com.adactin.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {
    @BeforeTest
    public void testClassInitialize() {
        System.out.println("***** Login Test Class initialized *****");
    }

    @BeforeMethod
    public void launchBrowser() {
        browserDriverSetup();
        goToURL(Constants.LOGIN_URL);
        System.out.println("Navigated to "+Constants.LOGIN_URL);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
        System.out.println("Browser Closed");
    }

    @AfterTest
    public void testClassComplete() {
        System.out.println("***** Login Test Class Completed *****");
    }

    @Test(priority = 1)
    public void login_with_valid_Username_invalid_password() {
        loginPage.login(Constants.USERNAME,"Adactin2023");
        Assert.assertTrue(loginPage.validateInvalidLoginError("Invalid Login details"),
                "Login Error Message mismatching");
    }

    @Test(priority = 2)
    public void login_with_invalid_Username_valid_password() {
        loginPage.login("STester2023",Constants.PASSWORD);
        Assert.assertTrue(loginPage.validateInvalidLoginError("Invalid Login details"),
                "Login Error Message mismatching");
    }

    @Test(priority = 3)
    public void login_with_blank_Username_valid_password() {
        loginPage.login("",Constants.PASSWORD);
        Assert.assertTrue(loginPage.validateUsernameLoginError("Enter Username"),
                "Username login Error Message mismatching");
    }

    @Test(priority = 4)
    public void login_with_valid_Username_blank_password() {
        loginPage.login(Constants.USERNAME,"");
        Assert.assertTrue(loginPage.validatePasswordLoginError("Enter Password"),
                "Password login Error Message mismatching");
    }

    @Test(priority = 5)
    public void login_with_blank_Username_blank_password() {
        loginPage.login("","");
        Assert.assertTrue(loginPage.validateUsernameLoginError("Enter Username"),
                "Username login Error Message mismatching");
    }

    @Test(priority = 6)
    public void login_with_valid_Username_valid_password() {
        loginPage.enterUsernamePassword(Constants.USERNAME,Constants.PASSWORD);
        searchHotelPage = loginPage.clickLogin();
        Assert.assertTrue(searchHotelPage.validateUsernameDisplay(Constants.USERNAME),"Not logged in successfully");
    }

    @Test(priority = 7)
    public void verifyLoginTitle() {
        loginPage.enterUsernamePassword(Constants.USERNAME,Constants.PASSWORD);
        searchHotelPage = loginPage.clickLogin();
        Assert.assertTrue(searchHotelPage.validateUsernameDisplay(Constants.USERNAME),"Not logged in successfully");
        loginPage.verifyLoginTitle(Constants.LOGIN_PAGE_TITLE);
    }
}