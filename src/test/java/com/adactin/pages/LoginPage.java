package com.adactin.pages;

import com.adactin.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseClass {

    private By txtUserName = By.id("username");
    private By txtPassword = By.id("password");
    private By btnLogin = By.id("login");
    private By lblLoginError = By.xpath("//b[contains(text(),'Invalid Login details or Your Password might have ')]");
    private By lblUsernameLoginErr=By.id("username_span");
    private By lblPasswordLoginErr=By.id("password_span");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        enterUsernamePassword(username, password);
        elementClick(btnLogin,"Login Button");
    }

    public void enterUsernamePassword(String username, String password) {
        enterData(txtUserName,username,"User Name");
        enterData(txtPassword,password,"Password");
    }

    public boolean validateInvalidLoginError(String error) {
        if (getText(lblLoginError,"Login Error message").contains(error)) {
            return true;
        }
        return false;
    }

    public boolean validateUsernameLoginError(String error) {
        if(getText(lblUsernameLoginErr,"Username Login Error message").contains(error)) {
            return true;
        }
        return false;
    }

    public boolean validatePasswordLoginError(String error) {
        if(getText(lblPasswordLoginErr,"Password Login Error message").contains(error)) {
            return true;
        }
        return false;
    }

    public SearchHotelPage clickLogin() {
        elementClick(btnLogin,"Login Button");
        return new SearchHotelPage(driver);
    }

    public boolean verifyLoginTitle(String expectedTitle) {
        String actualTitle = getTitle("Login Page");
        return actualTitle.equals(expectedTitle);
    }

    public boolean verifyLoginUrl(String expectedUrl) {
        String actualUrl = getUrl("Login URL");
        return actualUrl.equals(expectedUrl);
    }


}
