package com.wtc.excelutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Gopal
 *
 */

public class XlsxExcelReaderUtil  {
	public static XSSFCell cell;

	public static String key;

	/**
	 * @param metaDataFileName
	 * - File name of meta data excel sheet.
	 * @return
	 * @return Map of meta-data values for the scripts field.
	 * @throws BiffException
	 * @throws IOException
	 */

	public static MultiValueMap getMetaDataValues(String metaDataFileName)
			throws IOException {
		String columnValue = "";
		MultiValueMap multiValueMap = new MultiValueMap();
		try {
			FileInputStream metaFile = new FileInputStream(metaDataFileName);
			XSSFWorkbook workbook = new XSSFWorkbook(metaFile);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int metaRowCount = sheet.getLastRowNum() + 1;
			System.out.println(metaRowCount);
			int metaCellCount = sheet.getRow(0).getLastCellNum();
			System.out.println(metaCellCount);
			for (int rowNum = 0; rowNum < metaRowCount; rowNum++) {
				String metaDataValueString = "";
				XSSFRow rowCells = sheet.getRow(rowNum);
				cell = rowCells.getCell(0);
				String key = String.valueOf(cell);
				for (int cellNum = 1; cellNum < metaCellCount; cellNum++) {
					cell = rowCells.getCell(cellNum);
					columnValue = String.valueOf(cell);
					if (metaDataValueString == null
							|| metaDataValueString.isEmpty())
						metaDataValueString = columnValue;
					else
						metaDataValueString = metaDataValueString + "&&"
								+ columnValue;
				}
				multiValueMap.put(key, metaDataValueString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return multiValueMap;
	}
	public static List<HashMap<String, String>> getFieldDataValues(String dataFileName) {
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		List<String> columnNameList = new ArrayList<String>();
		try {
			FileInputStream dataFile = new FileInputStream(dataFileName);
			XSSFWorkbook workbook = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int dataRowCount = sheet.getLastRowNum() + 1;
			System.out.println(dataRowCount);
			int dataCellcount = sheet.getRow(0).getLastCellNum();
			System.out.println(dataCellcount);
			XSSFRow rowCells = sheet.getRow(0);
			cell = rowCells.getCell(0);
			for (int j = 0; j < dataCellcount; j++) {
				cell = rowCells.getCell(j);
				columnNameList.add(String.valueOf(cell));
			}
			for (int rowNum = 1; rowNum < dataRowCount; rowNum++) {
				XSSFRow dataRowCells = sheet.getRow(rowNum);
				LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
				for (int j = 0; j < dataCellcount; j++) {
					XSSFCell cell = dataRowCells.getCell(j);
					String columnValue = String.valueOf(cell);
					dataMap.put(columnNameList.get(j), columnValue);
				} 
				dataList.add(dataMap);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
}
