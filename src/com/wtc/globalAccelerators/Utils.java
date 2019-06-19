package com.wtc.globalAccelerators;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.javascript.host.URL;
import com.wtc.excelutil.ReadExcel;

public class Utils {
	 public static Properties properties;
	 public static ClassLoader loader = Thread.currentThread().getContextClassLoader();

	 public static void readPropertiesFile() throws IOException {
			java.net.URL resourceUrl = loader.getResource("resources/OR.properties");
			properties = new Properties();
			try(InputStream input = resourceUrl.openStream()) {
				properties.load(input);
			}
		}
	/*public static String[][] getExcelValues() throws InvalidFormatException, IOException {
		System.out.println("Get values: ");
		readPropertiesFile();
		ReadExcel excel = new ReadExcel();
		String credentials  = properties.getProperty("BluesageCredentials");
		return excel.getCellData(credentials, "Sheet1");	
	}*/
	public static void getErrors(){
		String errors = Reporter.getOutput().toString();
		String[] logErrors = errors.split(",");
		//Reporter.log(metaDataKey + ": Unable to find the element" + logErrors[0]);
	}
	
}
