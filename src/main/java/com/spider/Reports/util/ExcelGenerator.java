package com.spider.Reports.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.spider.Reports.entity.CitizenPlan;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {

	public void generate(HttpServletResponse response,List<CitizenPlan> records, File file) throws Exception {
//      Workbook workbook = new XSSFWorkbook();         
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet =   workbook.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name ");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benifit Amt ");
		
		int dataRowIndex = 1;
		
		for (CitizenPlan plan : records) {
			Row dataRow =sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getPlanStartDate()+"");
			dataRow.createCell(5).setCellValue(plan.getPlanEndDate()+"");
			

			if (null != plan.getPlanStartDate()) {
				dataRow.createCell(4).setCellValue(plan.getPlanStartDate()+"");
			}else {
				dataRow.createCell(4).setCellValue("N/A");
			}
			
			if (null != plan.getPlanEndDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanEndDate()+"");
			}else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			
			if (null != plan.getBenifitAmt()) {
				dataRow.createCell(6).setCellValue(plan.getBenifitAmt());
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
		     
			dataRowIndex++;
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		
		ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
		workbook.close();
		
		
	}

}
