package com.adactin.tests;

import com.adactin.base.BaseTest;
import com.adactin.pages.LoginPage;
import com.adactin.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogoutTest extends BaseTest {
    @BeforeTest
    public void testClassInitialize() {
        System.out.println("***** Logout Test Class initialized *****");
    }

    @BeforeMethod
    public void launchBrowser() throws InterruptedException {
        browserDriverSetup();
        goToURL(Constants.LOGIN_URL);
        System.out.println("Navigated to "+Constants.LOGIN_URL);
        loginPage = new LoginPage(getDriver());
        loginPage.enterUsernamePassword(Constants.USERNAME,Constants.PASSWORD);
        searchHotelPage = loginPage.clickLogin();
        logoutPage = searchHotelPage.clickLogout();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
        System.out.println("Browser Closed");
    }

    @AfterTest
    public void testClassComplete() {
        System.out.println("***** Logout Test Class Completed *****");
    }

    @Test(priority = 1)
    public void verifyLogoutPageTitleAndURL() {
        Assert.assertTrue(logoutPage.validateLogoutPageTitle(Constants.LOGOUT_PAGE_TITLE),"Logout validation is failed");
        Assert.assertTrue(logoutPage.validateLogoutPageUrl(Constants.LOGOUT_PAGE_URL),"Logout Page URL mismatching");
    }

    @Test(priority = 2)
    public void verifyLogoutMessage() {
        Assert.assertTrue(logoutPage.verifyLogoutMessage(Constants.LOGOUT_MESSAGE));
    }

    @Test(priority = 3)
    public void verifyLoginLinkWorking() {
        Assert.assertTrue(logoutPage.isLoginLinkDisplayed());
        loginPage = logoutPage.clickLoginLink();
        Assert.assertTrue(loginPage.verifyLoginUrl(Constants.LOGIN_URL));
        Assert.assertTrue(loginPage.verifyLoginTitle(Constants.LOGIN_PAGE_TITLE));
    }
}