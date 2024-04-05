package com.adactin.pages;

import com.adactin.base.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends PageUtility {
    private By logoutMessage = By.xpath("//td[@class='reg_success']");
    private By loginLink = By.xpath("//a[normalize-space()='Click here to login again']");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateLogoutPageTitle(String expectedTitle) {
        waitForElementVisibility(logoutMessage,"Logout Message");
        String actualTitle = getTitle("Logout Page");
        return actualTitle.equals(expectedTitle);
    }

    public boolean validateLogoutPageUrl(String expectedUrl) {
        String actualUrl = getUrl("Logout Page");
        return actualUrl.equals(expectedUrl);
    }

    public boolean verifyLogoutMessage(String expectedMessage) {
        String actualText = getText(logoutMessage,"Logout Message");
        return actualText.contains(expectedMessage);
    }
    public boolean isLoginLinkDisplayed(){
        return isDisplayed(loginLink,"Login link");
    }

    public LoginPage clickLoginLink() {
        elementClick(loginLink,"Login link");
        return new LoginPage(driver);
    }
}
