package com.wtc.excelutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import jxl.read.biff.BiffException;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wtc.globalAccelerators.ActionDriver;
import com.wtc.globalAccelerators.Utils;

/**
 * @author Gopal
 *
 */
public class XlsxMultiAppReaderUtil extends ActionDriver {
	public static XSSFCell cell;
	public static List rowCount = new ArrayList();
	public static String key;
	public static int dataRowNum;

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
		int prevKey = 0;
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
				String key = Utils.properties.getProperty(String.valueOf(cell));
				if (key == null) {
					keyValue = false;
					key = String.valueOf(cell);
				}else {
					keyValue = true;
				}
				//				String key = String.valueOf(cell);
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
				if(prevKey!=Integer.parseInt(key) && Integer.parseInt(key)!=1){
					prevKey=Integer.parseInt(key);

					rowCount.add(rowNum);
				}
				multiValueMap.put(key, metaDataValueString);
			}
			rowCount.add(metaRowCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return multiValueMap;
	}


	public static List<HashMap<String, String>> getFieldDataValues(String dataFileName) throws InvalidFormatException {
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

			if (dataRowCount > 2) {
				Random myRand = new Random();
				int Low = 1;
				int High = dataRowCount;
				dataRowNum = myRand.nextInt(High-Low) + Low;
				System.out.println(dataRowNum);
			} else {
				dataRowNum = 1;
			}
			for (int rowNum = 1; rowNum < dataRowCount; rowNum++) {
				System.out.println("ddd");
				System.out.println("Present row num is :" + rowNum);
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
