package com.adactin.pages;

import com.adactin.base.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BookingHotelPage extends PageUtility {

    private By pageHeader = By.xpath("(//td[@class='login_title'])[2]");
    private By txtFirstName = By.id("first_name");
    private By txtLastName = By.id("last_name");
    private By txtBilllingAddress = By.id("address");
    private By txtCreditCardNo = By.id("cc_num");
    private By drpCreditCardType = By.id("cc_type");
    private By drpExpiryMonth = By.id("cc_exp_month");
    private By drpExpiryYear = By.id("cc_exp_year");
    private By txtCreditCardCVV = By.id("cc_cvv");
    private By btnBookNow = By.id("book_now");
    private By btnCancel = By.id("cancel");

    public BookingHotelPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateBookHotelPageHeaderTitleUrl(String expectedHeader, String expectedTitle, String expectedUrl) {
        String actualHeader = getText(pageHeader,"Book Hotel Header");
        String actualTitle = getTitle("Book Hotel Title");
        String actualUrl = getUrl("Book Hotel Url");
        if(actualHeader.equals(expectedHeader) && actualTitle.equals(expectedTitle) && actualUrl.equals(expectedUrl)) {
            return true;
        }
        return false;
    }

    public void enterBookingDetails(String fName,String lName,String address,String creditCardNo,String creditCardType,String creditCardExpiryMonth,String creditCardExpiryYear,String creditCardCVVNo) {
        enterData(txtFirstName,fName,"First Name");
        enterData(txtLastName,lName,"Last Name");
        enterData(txtBilllingAddress,address,"Billing Address");
        enterData(txtCreditCardNo,creditCardNo,"Credit Card Number");
        selectOption(drpCreditCardType,creditCardType);
        selectOption(drpExpiryMonth,creditCardExpiryMonth);
        selectOption(drpExpiryYear,creditCardExpiryYear);
        enterData(txtCreditCardCVV,creditCardCVVNo,"Credit Card CVV Number");
    }

    public BookingConfirmationPage clickBookNow() {
        elementClick(btnBookNow,"Book Now");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return new BookingConfirmationPage(driver);
    }



}
