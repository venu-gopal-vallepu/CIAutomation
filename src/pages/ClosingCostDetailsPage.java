package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class ClosingCostDetailsPage extends UIElementMethods {
	String closingCostDetailsTreeNode = "//span[text()='Closing Cost Details']";
	String AddBtn = "//span[text()='Add']";
	String elemFocused = "//input[contains(@class,'x-field-form-focus')]";
	String closingCostSourceMask = "(//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[2]";
	String closingCostAmountMask = "//td[contains(@class,'x-grid-cell-headerId-currencycolumn')]";
	String closingCostsFundType = "//span[text()='Closing Cost Funds Type']";


	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("5000000003");
			Thread.sleep(1500);
			String xPath = "//span[text()='Processing']";
			String metaDataKey = "Closing Cost Details";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addClosingCostDetails(closingCostDetailsTreeNode, metaDataKey);
		}
	}

	public void addClosingCostDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(closingCostDetailsTreeNode, metaDataKey);
		click(closingCostDetailsTreeNode, metaDataKey);
		Thread.sleep(200);

		waitForElementWithLocator(AddBtn, metaDataKey);
		click(AddBtn, metaDataKey);

		enterText(elemFocused, "Bridge Loan", metaDataKey);

		click(closingCostSourceMask, metaDataKey);
		enterText(elemFocused, "Borrower", metaDataKey);

		click(closingCostAmountMask, metaDataKey);
		enterText(elemFocused, "100", metaDataKey);

		click(closingCostsFundType, metaDataKey);

		locator = "//span[text()='Save']";
		click(locator, metaDataKey);

		Thread.sleep(300);

	}
}
