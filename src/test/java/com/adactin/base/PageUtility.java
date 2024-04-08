package com.adactin.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class PageUtility {

    protected WebDriver driver;
    private Select select;

    public PageUtility(WebDriver driver) {
        this.driver=driver;
    }

    public String getTitle(String info) {
        String title = driver.getTitle();
        System.out.println("Browser Title for "+info+" is "+title);
        return title;
    }

    public String getUrl(String info) {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL for "+info+" is "+url);
        return url;
    }

    public void enterData(By by,String data,String info){
        try {
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(data);
            System.out.println("Data entered on element "+info+" with data "+data);
        }catch (Exception e){
            System.out.println("Cannot enter data on element "+info+" with data "+data);
        }
    }

    public void elementClick(By by,String info){
        try {
            driver.findElement(by).click();
            System.out.println("Clicked on element "+info);
        }catch (Exception e){
            System.out.println("Clicked on element "+info);
        }
    }

    public String getText(By by,String info){
        String text = driver.findElement(by).getText();
        System.out.println("Text from "+info+" is "+text);
        return text.trim();
    }

    public String getAttributeValue(By by,String name,String info){
        String text = driver.findElement(by).getAttribute(name);
        System.out.println("Text from "+info+" is "+text);
        return text.trim();
    }

    public void selectOption(By by,String optionToSelect) {
        select = new Select(driver.findElement(by));
        select.selectByVisibleText(optionToSelect);
        System.out.println("Option selected is "+optionToSelect);
    }

    public boolean isSelected(By by,String info) {
        boolean selected = false;
        selected = driver.findElement(by).isSelected();
        if (selected) {
            System.out.println(info+" is selected");
        } else  {
            System.out.println(info+" is unselected");
        }
        return selected;
    }

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String systemDate = formatter.format(date);
        System.out.println("Current System Date is "+systemDate);
        return systemDate;
    }

    public String getTomorrowsDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(new Date().getTime() + 86400000);
        String tmrwDate = formatter.format(date);
        System.out.println("Tomorrows Date is "+tmrwDate);
        return tmrwDate;
    }

    public String getSelectedDropdownValue(By by,String info) {
        select = new Select(driver.findElement(by));
        String text = select.getFirstSelectedOption().getText();
        System.out.println("Option from "+info+" selected is "+text);
        return text;
    }

    public boolean isDisplayed(By by,String info) {
        boolean displayed = driver.findElement(by).isDisplayed();
        if (displayed) {
            System.out.println("Element " + info + " is displayed");
        } else {
            System.out.println("Element " + info + " is Not displayed");
        }
        return displayed;
    }

    public void waitForElementVisibility(By by,String info) {
        WebElement element = driver.findElement(by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Element "+info+" is visible");
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        System.out.println("Scrolled down");
    }


}
