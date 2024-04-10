package com.adactin.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class BaseClass {

    protected WebDriver driver;
    private Select select;
    private Actions action;
    private JavascriptExecutor js;

    public BaseClass(WebDriver driver) {
        this.driver=driver;
    }

    // 1
    public String getTitle(String info) {
        String title = driver.getTitle();
        System.out.println("Browser Title for "+info+" is "+title);
        return title;
    }

    // 2
    public String getUrl(String info) {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL for "+info+" is "+url);
        return url;
    }

    // 3
    public void enterData(By by,String data,String info){
        try {
            highlightElement(by);
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(data);
            System.out.println("Data entered on element "+info+" with data "+data);
        }catch (Exception e){
            System.out.println("Cannot enter data on element "+info+" with data "+data);
        }
    }

    // 4
    public void elementClick(By by,String info){
        try {
            highlightElement(by);
            driver.findElement(by).click();
            System.out.println("Clicked on element "+info);
        }catch (Exception e){
            System.out.println("Clicked on element "+info);
        }
    }

    // 5
    public void javascriptClick(WebElement element,String info){
        js = (JavascriptExecutor) driver;
        try {
            highlightElement(element);
            js.executeScript("arguments[0].click();", element);
            System.out.println("Clicked on element "+info);
        }catch (Exception e){
            System.out.println("Clicked on element "+info);
        }
    }

    // 6
    public String getText(By by,String info){
        highlightElement(by);
        String text = driver.findElement(by).getText();
        System.out.println("Text from "+info+" is "+text);
        return text.trim();
    }

    // 7
    public String getText(WebElement element,String info){
        highlightElement(element);
        String text = element.getText();
        System.out.println("Text from "+info+" is "+text);
        return text.trim();
    }

    // 8
    public String getAttributeValue(By by,String name,String info){
        String text = driver.findElement(by).getAttribute(name);
        System.out.println("Text from "+info+" is "+text);
        return text.trim();
    }

    // 9
    public void selectOption(By by,String optionToSelect) {
        highlightElement(by);
        select = new Select(driver.findElement(by));
        select.selectByVisibleText(optionToSelect);
        System.out.println("Option selected is "+optionToSelect);
    }

    // 10
    public boolean isSelected(By by,String info) {
        boolean selected = false;
        highlightElement(by);
        selected = driver.findElement(by).isSelected();
        if (selected) {
            System.out.println(info+" is selected");
        } else  {
            System.out.println(info+" is unselected");
        }
        return selected;
    }

    // 11
    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String systemDate = formatter.format(date);
        System.out.println("Current System Date is "+systemDate);
        return systemDate;
    }

    // 12
    public String getTomorrowsDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(new Date().getTime() + 86400000);
        String tmrwDate = formatter.format(date);
        System.out.println("Tomorrows Date is "+tmrwDate);
        return tmrwDate;
    }

    // 13
    public String getSelectedDropdownValue(By by,String info) {
        highlightElement(by);
        select = new Select(driver.findElement(by));
        String text = select.getFirstSelectedOption().getText();
        System.out.println("Option from "+info+" selected is "+text);
        return text;
    }

    // 14
    public boolean isDisplayed(By by,String info) {
        highlightElement(by);
        boolean displayed = driver.findElement(by).isDisplayed();
        if (displayed) {
            System.out.println("Element " + info + " is displayed");
        } else {
            System.out.println("Element " + info + " is Not displayed");
        }
        return displayed;
    }

    // 15
    public void waitForElementVisibility(By by,String info) {
        WebElement element = driver.findElement(by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Element "+info+" is visible");
    }

    // 16
    public void waitForElementInvisibility(By by,String info) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        System.out.println("Element "+info+" is invisible");
    }

    // 17
    public void waitForElementToBeClickable(By by,String info) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        System.out.println("Element "+info+" is clickable");
    }

    // 18
    public void scrollDown() {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        System.out.println("Scrolled down");
    }

    // 19
    public void refresh() {
        driver.navigate().refresh();
        System.out.println("Browser refreshed !!");
    }

    // 20
    public void navigateBrowserBack() {
        driver.navigate().back();
        System.out.println("Page navigated back");
    }

    // 21
    public void navigateBrowserForward() {
        driver.navigate().forward();
        System.out.println("Page navigated forward");
    }

    // 22
    public void highlightElement(By by) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','border:2px solid red')",driver.findElement(by));
    }

    // 23
    public void highlightElement(WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','border:2px solid red')",element);
    }

    // 24
    public boolean isEnabled(By by,String info) {
        boolean flag = false;
        flag = driver.findElement(by).isEnabled();
        if (flag == true) {
            System.out.println(info+" is enabled");
        } else {
            System.out.println(info+" is NOT enabled");
        }
        return flag;
    }

    // 25
    public void checkCheckBox(By by,String info) {
        if (!isSelected(by,info)) {
            elementClick(by, info);
            System.out.println(info+" is checked");
        } else {
            System.out.println(info+" is already checked");
        }
    }

    // 26
    public void uncheckCheckBox(By by,String info) {
        if (isSelected(by,info)) {
            elementClick(by, info);
            System.out.println(info+" is unchecked");
        } else {
            System.out.println(info+" is already unchecked");
        }
    }

    // 27
    public void mouseHover(By by,String info) {
        WebElement element = driver.findElement(by);
        action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    // 28
    public boolean isOptionExist(By by, String optionToVerify, String info) {
        select = new Select(driver.findElement(by));
        boolean exist = false;
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            String text = getText(option, "Option Exist");
            if (text.equals(optionToVerify)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            System.out.println(optionToVerify+" is exist");
        } else {
            System.out.println(optionToVerify+" is NOT exist");
        }
        return exist;
    }

    // 29
    public void doubleClick(By by, String info) {
        action = new Actions(driver);
        action.doubleClick(driver.findElement(by)).perform();
        action.perform();
        System.out.println("Double click performed on "+info);
    }

    // 30
    public void rightClick(By by, String info) {
        action = new Actions(driver);
        action.contextClick(driver.findElement(by)).build().perform();
        System.out.println("Right click performed on "+info);
    }

    // 31
    public void pressEnter(By by,String info) {
        driver.findElement(by).sendKeys(Keys.ENTER);
        System.out.println("Pressed Enter on "+info);
    }

    // 32
    public void alertDismiss() {
        driver.switchTo().alert().dismiss();
        System.out.println("Alert dismissed !!");
    }

    // 33
    public void alertAccept() {
        driver.switchTo().alert().accept();
        System.out.println("Alert accepted !!");
    }

    // 34
    public String getTextFromAlert() {
        String text = driver.switchTo().alert().getText();
        System.out.println("Text from alert is "+text);
        return text;
    }

    // 35
    public void scrollToElement(By by,String info) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
        System.out.println("Scrolled down to the element "+info);
    }





}