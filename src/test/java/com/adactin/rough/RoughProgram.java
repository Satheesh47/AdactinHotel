package com.adactin.rough;

import com.adactin.pages.SearchHotelPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class RoughProgram {

    public static void main(String[] args) throws InterruptedException {



        System.setProperty("webdriver.chrome.driver", "C://Users//sathe//Downloads//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://adactinhotelapp.com/Logout.php");
        driver.manage().window().maximize();
////        driver.findElement(By.id("username")).sendKeys("STester2024");
////        driver.findElement(By.id("password")).sendKeys("Adactin2024");
        WebElement element = driver.findElement(By.id("login"));
        driver.findElement(By.id("login")).click();

////        Thread.sleep(2000);
//        String text = driver.findElement(By.xpath("//td[@class='reg_success']")).getText();
//        System.out.println(text);

    }
}
