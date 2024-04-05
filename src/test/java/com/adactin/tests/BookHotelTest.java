package com.adactin.tests;

import com.adactin.base.BaseTest;
import com.adactin.pages.LoginPage;
import com.adactin.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.*;

public class BookHotelTest extends BaseTest {

    @BeforeTest
    public void testClassInitialize() {
        System.out.println("***** Book Hotel Test Class initialized *****");
    }

    @BeforeMethod
    public void launchBrowser() {
        browserDriverSetup(Constants.BROWSER);
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
    public void verifyBookHotelPageHeaderTitleAndUrl() {
        bookingHotelPage = searchHotelPage.bookAHotel("Sydney","Hotel Creek","Standard",
                "1 - One","28/04/2024","30/04/2024",
                "2 - Two","1 - One");
        Assert.assertTrue(bookingHotelPage.validateBookHotelPageHeaderTitleUrl(Constants.BOOK_HOTEL_PAGE_HEADER,
                Constants.BOOK_HOTEL_PAGE_TITLE,Constants.BOOK_HOTEL_PAGE_URL),"Booking Hotel Page Header/Title/Url validation mismatched");
    }

    @Test(priority = 2)
    public void bookAHotel() {
        bookingHotelPage = searchHotelPage.bookAHotel("Sydney","Hotel Creek","Standard",
                "1 - One","28/04/2024","30/04/2024",
                "2 - Two","1 - One");
        bookingHotelPage.enterBookingDetails("FTest","LTest","123 Test St,TestCity-100",
                "0123456789123456","VISA","January",
                "2030","123");
        bookingConfirmationPage = bookingHotelPage.clickBookNow();
        Assert.assertTrue(bookingConfirmationPage.verifyBookingConfirmationHeader(Constants.BOOKING_CONFIRMATION_PAGE_HEADER),"Booking Confirmation page header mismatched");
        Assert.assertNotNull(bookingConfirmationPage.getOrderNo(),"Order Number is null");
    }
}
