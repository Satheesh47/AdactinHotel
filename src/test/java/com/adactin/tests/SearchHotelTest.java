package com.adactin.tests;

import com.adactin.base.BaseTest;
import com.adactin.pages.LoginPage;
import com.adactin.utilities.Constants;
import com.adactin.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class SearchHotelTest extends BaseTest {
    @BeforeTest
    public void testClassInitialize() {
        System.out.println("***** Search Hotel Test Class initialized *****");
    }

    @BeforeMethod
    public void launchBrowser() {
        browserDriverSetup();
        goToURL(Constants.LOGIN_URL);
        System.out.println("Navigated to "+Constants.LOGIN_URL);
        loginPage = new LoginPage(getDriver());
        loginPage.enterUsernamePassword(Constants.USERNAME,Constants.PASSWORD);
        searchHotelPage = loginPage.clickLogin();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
        System.out.println("Browser Closed");
    }

    @AfterTest
    public void testClassComplete() {
        System.out.println("***** Search Hotel Test Class Completed *****");
    }

    @Test(priority = 1)
    public void verifySearchHotelURLAndTitle() {
        Assert.assertTrue(searchHotelPage.validateUsernameDisplay(Constants.USERNAME),"Username display mismatch");
        Assert.assertTrue(searchHotelPage.validateSearchHotelUrl(Constants.SEARCH_HOTEL_PAGE_URL));
        Assert.assertTrue(searchHotelPage.validateSearchHotelTitle(Constants.SEARCH_HOTEL_PAGE_TITLE));
        Assert.assertTrue(searchHotelPage.validatePageHeader(Constants.SEARCH_HOTEL_PAGE_HEADER));
    }

    @Test(priority = 2)
    public void searchHotelWithoutLocation() {
        searchHotelPage.clickSearchButton();
        searchHotelPage.validateLocationErrorMessage(Constants.LOCATION_ERROR_MESSAGE);
    }

    @Test(priority = 3)
    public void resetSearchHotelForm() throws IOException {
        searchHotelPage.resetSearchHotel(ExcelUtility.readExcel(0,1),ExcelUtility.readExcel(1,1),
                ExcelUtility.readExcel(2,1),ExcelUtility.readExcel(3,1),
                ExcelUtility.readExcel(4,1),ExcelUtility.readExcel(5,1),
                ExcelUtility.readExcel(6,1),ExcelUtility.readExcel(7,1));
        Assert.assertTrue(searchHotelPage.validateResetFunctionality(Constants.LOCATION_RESET_VALUE,Constants.HOTEL_RESET_VALUE),"Reset validation failed");
    }

    @Test(priority = 4)
    public void searchHotel() throws InterruptedException, IOException {
        searchHotelPage.searchForHotel(ExcelUtility.readExcel(0,1),ExcelUtility.readExcel(1,1),
                ExcelUtility.readExcel(2,1),ExcelUtility.readExcel(3,1),
                ExcelUtility.readExcel(4,1),ExcelUtility.readExcel(5,1),
                ExcelUtility.readExcel(6,1),ExcelUtility.readExcel(7,1));
        Assert.assertTrue(searchHotelPage.validatePageHeader(Constants.SELECT_HOTEL_HEADER));
        Assert.assertFalse(searchHotelPage.verifySelectHotelCheckBox(),"Checkbox is selected");
    }

    @Test(priority = 5)
    public void verifyThePriceOfTheHotel() throws IOException {
        searchHotelPage.searchForHotel(ExcelUtility.readExcel(0,1),ExcelUtility.readExcel(1,1),
                ExcelUtility.readExcel(2,1),ExcelUtility.readExcel(3,1),
                ExcelUtility.readExcel(4,1),ExcelUtility.readExcel(5,1),
                ExcelUtility.readExcel(6,1),ExcelUtility.readExcel(7,1));
        Assert.assertTrue(searchHotelPage.validatePriceOfTheHotel(Constants.HOTEL_PRICE),"Hotel Price is not matching");
        Assert.assertTrue(searchHotelPage.validatePriceOfTheHotelWithGST(Constants.HOTEL_PRICE_GST),"Hotel Price with GST is not matching");
    }

    @Test(priority = 6)
    public void cancelTheSelectHotel() throws IOException {
        searchHotelPage.searchForHotel(ExcelUtility.readExcel(0,1),ExcelUtility.readExcel(1,1),
                ExcelUtility.readExcel(2,1),ExcelUtility.readExcel(3,1),
                ExcelUtility.readExcel(4,1),ExcelUtility.readExcel(5,1),
                ExcelUtility.readExcel(6,1),ExcelUtility.readExcel(7,1));
        searchHotelPage.clickCancelButton();
        Assert.assertTrue(searchHotelPage.validatePageHeader(Constants.SEARCH_HOTEL_PAGE_HEADER));
    }

    @Test(priority = 7)
    public void continueWithoutSelectingHotel() throws IOException {
        searchHotelPage.searchForHotel(ExcelUtility.readExcel(0,1),ExcelUtility.readExcel(1,1),
                ExcelUtility.readExcel(2,1),ExcelUtility.readExcel(3,1),
                ExcelUtility.readExcel(4,1),ExcelUtility.readExcel(5,1),
                ExcelUtility.readExcel(6,1),ExcelUtility.readExcel(7,1));
        Assert.assertTrue(searchHotelPage.validateWithoutSelectingHotelErrorMessage(Constants.CONTINUE_ERROR_MESSAGE));
    }
}