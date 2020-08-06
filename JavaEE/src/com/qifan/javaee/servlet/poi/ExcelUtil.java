package com.qifan.javaee.servlet.poi;

import com.qifan.javaee.servlet.bean.Student;
import com.qifan.javaee.servlet.dao.StuDao;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;

public class ExcelUtil {

    public static HSSFWorkbook getExcel() {

        int rowCount = 0;
        HSSFWorkbook wkb = new HSSFWorkbook();
        HSSFSheet sheet = wkb.createSheet();
        HSSFRow rowHead = sheet.createRow(rowCount++);
        rowHead.createCell(0).setCellValue("学号");
        rowHead.createCell(1).setCellValue("姓名");
        rowHead.createCell(2).setCellValue("年龄");

        ArrayList<Student> students = StuDao.selectAll();
        for (Student student : students) {
            HSSFRow row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getAge());
        }
        return wkb;
    }




}
