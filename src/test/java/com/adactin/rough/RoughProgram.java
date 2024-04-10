package com.adactin.rough;

import com.adactin.pages.SearchHotelPage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoughProgram {

    public static void main(String[] args) throws InterruptedException, IOException {

//        System.setProperty("webdriver.chrome.driver", "C://Users//sathe//Downloads//chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://adactinhotelapp.com/Logout.php");
//        driver.manage().window().maximize();
//        driver.findElement(By.id("username")).sendKeys("STester2024");
//        driver.findElement(By.id("password")).sendKeys("Adactin2024");
//        WebElement element = driver.findElement(By.id("login"));
//        driver.findElement(By.id("login")).click();

//        Thread.sleep(2000);
//        String text = driver.findElement(By.xpath("//td[@class='reg_success']")).getText();
//        System.out.println(text);

        File f = new File(System.getProperty("user.dir")+"//src//test//resources//Test_Data.xlsx");
        FileInputStream fin = new FileInputStream(f);
        Workbook book = new XSSFWorkbook(fin);
        Sheet sheet = book.getSheet("Search Hotel");
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(1);
        String value = "";
        CellType type = cell.getCellType();
        if(type == CellType.STRING) {
            value = cell.getStringCellValue();
        } else if (DateUtil.isCellDateFormatted(cell)) {
            Date date = cell.getDateCellValue();
            System.out.println(date);
            SimpleDateFormat sim = new SimpleDateFormat("dd,MM,yyyy");
            value = sim.format(date);
        } else if (type == CellType.NUMERIC) {
            long l = (long) cell.getNumericCellValue();
            value = String.valueOf(l);
        }

        System.out.println(value);

        //System.out.println(System.getProperty("user.dir"));



    }
}
