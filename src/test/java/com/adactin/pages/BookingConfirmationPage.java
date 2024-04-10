package com.adactin.pages;

import com.adactin.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingConfirmationPage extends BaseClass {

    private By lblBookingConfirmationHeader = By.xpath("//td[@class='login_title']");
    private By orderNumber = By.id("order_no");

    public BookingConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyBookingConfirmationHeader(String expectedHeader) {
        waitForElementVisibility(orderNumber,"Order Number");
        String actualHeader = getText(lblBookingConfirmationHeader, "Booking Confirmation Header");
        return actualHeader.equals(expectedHeader);
    }

    public String getOrderNo(){
        String orderNo = getAttributeValue(orderNumber,"value","Booking Order Number");
        return orderNo;
    }
}
