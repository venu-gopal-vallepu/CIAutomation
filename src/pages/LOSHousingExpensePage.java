package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;



public class LOSHousingExpensePage extends UIElementMethods {
	String housingExpense = "//span[text()='Housing Expense']";
	
	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String metaDataKey = "Housing Expenses page";
			addHousingExpensePageDetails(housingExpense, "1500", metaDataKey);
		}
	}
	
	
	public void addHousingExpensePageDetails(String xPath , String metaDataKey , String testData){
		waitForElementWithLocator(housingExpense, metaDataKey);
		click(housingExpense, metaDataKey);
		locator = "//label[text()='Rent']";
		waitForElementWithLocator(locator, metaDataKey);
		enterText(locator, testData , metaDataKey);
		locator = "//span[text()='Save']";
		click(locator,metaDataKey);
	}
}
