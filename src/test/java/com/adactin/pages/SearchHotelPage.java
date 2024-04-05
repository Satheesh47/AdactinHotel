package com.adactin.pages;

import com.adactin.base.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;


public class SearchHotelPage extends PageUtility {

    private By lblUsernameDisplay = By.id("username_show");
    private By drpLocation = By.id("location");
    private By drpHotels = By.id("hotels");
    private By drpRoomType = By.id("room_type");
    private By drpNumberOfRooms = By.id("room_nos");
    private By txtCheckInDate = By.id("datepick_in");
    private By txtCheckOutDate = By.id("datepick_out");
    private By drpAdultsPerRoom = By.id("adult_room");
    private By drpChildPerRoom = By.id("child_room");
    private By btnSearch = By.id("Submit");
    private By btnReset = By.id("Reset");
    private By pageHeader = By.xpath("//td[@class='login_title']");
    private By chkSelectHotel = By.xpath("//input[@id='radiobutton_0']");
    private By priceOfFirstHotel = By.xpath("//input[@id='price_night_0']");
    private By priceOfFirstHotelGST = By.xpath("//input[@id='total_price_0']");
    private By btnCancel = By.id("cancel");
    private By btnContinue = By.id("continue");
    private By locationErrorMessage = By.id("location_span");

    private By logoutLink = By.linkText("Logout");
    private By lblContinueErrorMessage = By.xpath("//label[@id='radiobutton_span']");

    public SearchHotelPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateUsernameDisplay(String username) {
        String text = getAttributeValue(lblUsernameDisplay,"value","Username Display").split(" ")[1];
        if(username.equals(text.substring(0,text.length()-1))) {
            return true;
        }
        return false;
    }

    public void searchForHotel(String location, String hotels, String roomType,String noOfRooms,String checkInDate,
                               String checkOutDate, String adultsPerRoom,String childPerRoom) {
        System.out.println("Entering the Search Hotel form");
        selectOption(drpLocation,location);
        selectOption(drpHotels,hotels);
        selectOption(drpRoomType,roomType);
        selectOption(drpNumberOfRooms,noOfRooms);
        enterData(txtCheckInDate,checkInDate,"Check In Date");
        enterData(txtCheckOutDate,checkOutDate,"Check Out Date");
        selectOption(drpAdultsPerRoom,adultsPerRoom);
        selectOption(drpChildPerRoom,childPerRoom);
        elementClick(btnSearch,"Search Button");
        System.out.println("Submitted the Search Hotel form");
    }

    public BookingHotelPage bookAHotel(String location, String hotels, String roomType,String noOfRooms,String checkInDate,
                               String checkOutDate, String adultsPerRoom,String childPerRoom) {
        System.out.println("Entering the Search Hotel form");
        selectOption(drpLocation,location);
        selectOption(drpHotels,hotels);
        selectOption(drpRoomType,roomType);
        selectOption(drpNumberOfRooms,noOfRooms);
        enterData(txtCheckInDate,checkInDate,"Check In Date");
        enterData(txtCheckOutDate,checkOutDate,"Check Out Date");
        selectOption(drpAdultsPerRoom,adultsPerRoom);
        selectOption(drpChildPerRoom,childPerRoom);
        elementClick(btnSearch,"Search Button");
        System.out.println("Submitted the Search Hotel form");
        elementClick(chkSelectHotel,"Hotel Checkbox");
        elementClick(btnContinue,"Continue Button");
        return new BookingHotelPage(driver);
    }

    public void resetSearchHotel(String location, String hotels, String roomType,String noOfRooms,String checkInDate,
                               String checkOutDate, String adultsPerRoom,String childPerRoom) {
        System.out.println("Entering the Search Hotel form");
        selectOption(drpLocation,location);
        selectOption(drpHotels,hotels);
        selectOption(drpRoomType,roomType);
        selectOption(drpNumberOfRooms,noOfRooms);
        enterData(txtCheckInDate,checkInDate,"Check In Date");
        enterData(txtCheckOutDate,checkOutDate,"Check Out Date");
        selectOption(drpAdultsPerRoom,adultsPerRoom);
        selectOption(drpChildPerRoom,childPerRoom);
        elementClick(btnReset,"Reset Button");
    }

    public boolean validatePageHeader(String expectedHeader) {
        String actualHeader = getText(pageHeader,"Get Page Header");
        return actualHeader.contains(expectedHeader);
    }

    public boolean validateSearchHotelUrl(String expectedUrl) {
        String actualUrl = getUrl("Search Hotel Page");
        return actualUrl.equals(expectedUrl);
    }

    public boolean validateSearchHotelTitle(String expectedTitle) {
        String actualTitle = getTitle("Search Hotel");
        return actualTitle.equals(expectedTitle);
    }

    public boolean verifySelectHotelCheckBox() {
        return isSelected(chkSelectHotel,"Select Hotel checkbox");
    }

    public boolean validatePriceOfTheHotel(String expectedPrice) {
        String actualPrice = getAttributeValue(priceOfFirstHotel,"value","Price of Hotel");
        return actualPrice.equals(expectedPrice);
    }

    public boolean validatePriceOfTheHotelWithGST(String expectedPrice) {
        String actualPrice = getAttributeValue(priceOfFirstHotelGST,"value","Price of Hotel with GST");
        return actualPrice.equals(expectedPrice);
    }

    public void clickCancelButton() {
        elementClick(btnCancel,"Cancel Button");
    }

    public void clickContinueButton() {
        elementClick(btnContinue,"Continue Button");
    }

    public void clickSearchButton() {
        elementClick(btnSearch,"Search Button");
    }

    public boolean validateLocationErrorMessage(String expected) {
        String actual = getText(locationErrorMessage,"Location Error Message");
        return actual.equals(expected);
    }

    public boolean validateResetFunctionality(String location, String hotel) {
        String actualLocation = getSelectedDropdownValue(drpLocation,"Location Dropdown");
        String actualHotel = getSelectedDropdownValue(drpHotels,"Hotel Dropdown");
        String actualCheckInDate = getAttributeValue(txtCheckInDate,"value","Check In Date");
        String actualCheckOutDate = getAttributeValue(txtCheckOutDate,"value","Check Out Date");
        if(actualLocation.equalsIgnoreCase(location) && actualHotel.equalsIgnoreCase(hotel)
            && actualCheckInDate.equals(getCurrentDate()) && actualCheckOutDate.equals(getTomorrowsDate())) {
            return true;
        }
        return false;
    }

    public LogoutPage clickLogout() {
        isDisplayed(logoutLink,"Logout");
        elementClick(logoutLink,"Logout");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return new LogoutPage(driver);
    }

    public BookingHotelPage selectTheHotelAndContinue() {
        elementClick(chkSelectHotel,"Select Hotel Checkbox");
        elementClick(btnContinue,"Continue Button");
        return new BookingHotelPage(driver);
    }

    public boolean validateWithoutSelectingHotelErrorMessage(String expectedMessage) {
        elementClick(btnContinue,"Continue Button");
        String actualMessage = getText(lblContinueErrorMessage,"Continue Error Message");
        return actualMessage.equals(expectedMessage);
    }
}
