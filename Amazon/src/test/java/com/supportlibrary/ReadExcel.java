package com.supportlibrary;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {
	static String path="C:\\Users\\shank\\OneDrive\\Documents\\Shankar\\TestFrameWork\\Amazon\\src\\test\\resources\\Datatable\\TestData.xls";
	static String sheetName="EggTimer";
	
	 
	
	  public ReadExcel(String path, String sheetName) {
		
		this.path = path;
		this.sheetName = sheetName;
	}
   
	  
    public static void main(String args[]) throws InvalidFormatException, IOException {
    	ReadExcel r=new ReadExcel(path,sheetName);
    	String[][] value=r.getCellData(path, sheetName);
    	
    	
    }

	public String[][] getCellData(String path, String sheetName) throws InvalidFormatException, IOException {
	  FileInputStream stream = new FileInputStream(path);
	  Workbook workbook = WorkbookFactory.create(stream);
	  Sheet s = workbook.getSheet(sheetName);
	  int rowcount = s.getLastRowNum();
	  int cellcount = s.getRow(0).getLastCellNum();
	  String data[][] = new String[rowcount][cellcount];
	  for (int i = 1; i <= rowcount; i++) {
	   Row r = s.getRow(i);
	   for (int j = 0; j < cellcount; j++) {
	   Cell c = r.getCell(j);
	try {
	if (c.getCellType() == c.CELL_TYPE_STRING) {
	      data[i - 1][j] = c.getStringCellValue();
	} 
	else
	 {
	      data[i - 1][j] = String.valueOf(c.getNumericCellValue());
	}
	} catch (Exception e) {
	     e.printStackTrace();
	}
	   }
	  }
	  return data;
	 }
	  
}