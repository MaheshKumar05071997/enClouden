package com.qa.ct.juducialbranch.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	DataFormatter formatter;

	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + "/testData/ctJudicialData.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			// TODO: handle exception
			data = "";
		}
		workbook.close();
		fis.close();
		return data;

	}

//	public String getCompleteSheetData() throws IOException {
//		fis = new FileInputStream(System.getProperty("user.dir") + "/testData/ctJudicialData.xlsx");
//		workbook = new XSSFWorkbook(fis);
//		sheet = workbook.getSheetAt(0);
//		String cellValue = null;
//		int rowCount = sheet.getPhysicalNumberOfRows();
//		for (int i = 0; i < rowCount; i++) {
//			XSSFRow row = sheet.getRow(i);
//			int cellCount = row.getPhysicalNumberOfCells();
//			for (int j = 0; j < cellCount; j++) {
//				XSSFCell cell = row.getCell(j);
//				System.out.println(i);
//				System.out.println(j);
//				cellValue = getCellValue(cell);
//				
//				System.out.println(cellValue);
//				return cellValue;
//			}
//		}
//		workbook.close();
//		fis.close();
//		return cellValue;
//	}

//	public static String getCellValue(XSSFCell cell) {
//		switch (cell.getCellType()) {
//		case NUMERIC:
//			return String.valueOf(cell.getNumericCellValue());
//		case BOOLEAN:
//			return String.valueOf(cell.getBooleanCellValue());
//		case STRING:
//			return String.valueOf(cell.getStringCellValue());
//		default:
//			return cell.getStringCellValue();
//		}
//	}
}
