package com.adactin.tests;

import com.adactin.base.BaseTest;
import com.adactin.pages.LoginPage;
import com.adactin.utilities.Constants;
import com.adactin.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class BookHotelTest extends BaseTest {
    @BeforeTest
    public void testClassInitialize() {
        System.out.println("***** Book Hotel Test Class initialized *****");
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
        System.out.println("***** Book Hotel Test Class Completed *****");
    }

    @Test(priority = 1)
    public void verifyBookHotelPageHeaderTitleAndUrl() throws IOException {
        bookingHotelPage = searchHotelPage.bookAHotel(ExcelUtility.readExcel(0,1),ExcelUtility.readExcel(1,1),
                ExcelUtility.readExcel(2,1),ExcelUtility.readExcel(3,1),
                ExcelUtility.readExcel(4,1),ExcelUtility.readExcel(5,1),
                ExcelUtility.readExcel(6,1),ExcelUtility.readExcel(7,1));
        Assert.assertTrue(bookingHotelPage.validateBookHotelPageHeaderTitleUrl(Constants.BOOK_HOTEL_PAGE_HEADER,
                Constants.BOOK_HOTEL_PAGE_TITLE,Constants.BOOK_HOTEL_PAGE_URL),"Booking Hotel Page Header/Title/Url validation mismatched");
    }

    @Test(priority = 2)
    public void bookAHotel() throws IOException {
        bookingHotelPage = searchHotelPage.bookAHotel(ExcelUtility.readExcel(0,1),ExcelUtility.readExcel(1,1),
                ExcelUtility.readExcel(2,1),ExcelUtility.readExcel(3,1),
                ExcelUtility.readExcel(4,1),ExcelUtility.readExcel(5,1),
                ExcelUtility.readExcel(6,1),ExcelUtility.readExcel(7,1));
        bookingHotelPage.enterBookingDetails(ExcelUtility.readExcel(8,1),ExcelUtility.readExcel(9,1),
                ExcelUtility.readExcel(10,1), ExcelUtility.readExcel(11,1),
                ExcelUtility.readExcel(12,1),ExcelUtility.readExcel(13,1),
                ExcelUtility.readExcel(14,1),ExcelUtility.readExcel(15,1));
        bookingConfirmationPage = bookingHotelPage.clickBookNow();
        Assert.assertTrue(bookingConfirmationPage.verifyBookingConfirmationHeader(Constants.BOOKING_CONFIRMATION_PAGE_HEADER),"Booking Confirmation page header mismatched");
        Assert.assertNotNull(bookingConfirmationPage.getOrderNo(),"Order Number is null");
    }
}
