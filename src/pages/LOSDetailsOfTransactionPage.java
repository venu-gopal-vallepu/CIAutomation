package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LOSDetailsOfTransactionPage extends UIElementMethods {
	String detailsOfTransaction = "//span[text()='Details of Transaction']";
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = ".//span[text()='Underwriting']";
			String metaDataKey = "Details Of transaction n page";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			xPath = "//span[text()='Details of Transaction']";
			waitForElementWithLocator(xPath, metaDataKey);
			addDotDetails(xPath, metaDataKey);
		}
	}
	
	
public void addDotDetails(String xPath , String metaDataKey) throws InterruptedException{
	waitForElementWithLocator(xPath, metaDataKey);
	click(xPath, metaDataKey);
	locator = "//span[text()='Detail']";
	waitForElementWithLocator(locator, metaDataKey);
	Thread.sleep(2000);
	click(locator, metaDataKey);
	locator = "//span[text()='Other Credit']";
	waitForElementWithLocator(locator, metaDataKey);
	Thread.sleep(500);
	locator = "(//span[text()='Cancel'])[2]";
	click(locator, metaDataKey);
	Thread.sleep(500);
}
}
