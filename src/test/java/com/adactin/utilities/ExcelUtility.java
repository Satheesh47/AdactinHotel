package com.adactin.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtility {

    public static String readExcel(int rNum, int cNum) throws IOException {
        File f = new File(Constants.EXCEL_PATH);
        FileInputStream fin = new FileInputStream(f);
        Workbook book = new XSSFWorkbook(fin);
        Sheet sheet = book.getSheet("Search Hotel");
        Row row = sheet.getRow(rNum);
        Cell cell = row.getCell(cNum);
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
        return value;
    }
}
