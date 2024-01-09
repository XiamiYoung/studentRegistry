package edu.dufe.student.registry.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.dufe.student.registry.data.in.StudentRegistryData;

public class ExcelUtils {
	
	public static ByteArrayInputStream generateExcelForRegistries(ArrayList<StudentRegistryData> registryList) throws Exception{
		// create excel xls sheet
		Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("报名信息表");
	    sheet.setDefaultColumnWidth(30);

	    // create style for header cells
	    CellStyle style = workbook.createCellStyle();
	    Font font = workbook.createFont();
	    font.setFontName("Arial");
	    style.setFillForegroundColor(HSSFColor.BLUE.index);
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    font.setBold(true);
	    font.setColor(HSSFColor.WHITE.index);
	    style.setFont(font);


	    // create header row
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("姓名");
	    header.getCell(0).setCellStyle(style);
	    header.createCell(1).setCellValue("性别");
	    header.getCell(1).setCellStyle(style);
	    header.createCell(2).setCellValue("自考考号");
	    header.getCell(2).setCellStyle(style);
	    header.createCell(3).setCellValue("年级");
	    header.getCell(3).setCellStyle(style);
	    header.createCell(4).setCellValue("专业");
	    header.getCell(4).setCellStyle(style);
	    header.createCell(5).setCellValue("报考语种");
	    header.getCell(5).setCellStyle(style);
	    header.createCell(6).setCellValue("联系电话");
	    header.getCell(6).setCellStyle(style);
	    header.createCell(7).setCellValue("身份证号码");
	    header.getCell(7).setCellStyle(style);
	    header.createCell(8).setCellValue("照片文件名");
	    header.getCell(8).setCellStyle(style);

	    int rowCount = 1;
	    Row dataRow = null;
	    for(StudentRegistryData studentRegistryData : registryList){
	        dataRow =  sheet.createRow(rowCount++);
	        dataRow.createCell(0).setCellValue(studentRegistryData.getStudentName());
	        dataRow.createCell(1).setCellValue(studentRegistryData.getStudentSex());
	        dataRow.createCell(2).setCellValue(studentRegistryData.getStudentStudyNum());
	        dataRow.createCell(3).setCellValue(studentRegistryData.getStudentGrade());
	        dataRow.createCell(4).setCellValue(studentRegistryData.getStudentMajor());
	        dataRow.createCell(5).setCellValue(studentRegistryData.getLanguage());
	        dataRow.createCell(6).setCellValue(studentRegistryData.getStudentTel());
	        dataRow.createCell(7).setCellValue(studentRegistryData.getStudentIdentityNum());
	        dataRow.createCell(8).setCellValue(studentRegistryData.getStudentIdentityNum());
	     }
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
