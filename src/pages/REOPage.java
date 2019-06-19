package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class REOPage extends UIElementMethods {
	String REOTreeNode = "//div[text()='REO Information']";
	String REOAddproperty = "//span[text()='Add Property']";
    
	@Test
	public void enterInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("CRM-1200003568");
			String metaDataKey = "REO page";
			addREODetails(REOTreeNode, metaDataKey);
		}
	}

	public void addREODetails(String xPath ,String metaDataKey){
		waitForElementWithLocator(REOTreeNode, metaDataKey);
		click(REOTreeNode, metaDataKey);
		waitForElementWithLocator(REOAddproperty, metaDataKey);
		click(REOAddproperty, metaDataKey);
		String locator = "//span[text()='Property Info']";
		waitForElementWithLocator(locator, metaDataKey);
		locator = "//table[@data-selenium-id='contactCbGroup']//label[contains(@class,'x-form-cb-label-after')]";
		click(locator, metaDataKey);
		locator = "//label[text()='Subject Property']";
		click(locator, metaDataKey);
		locator = "//label[text()='Same as Present Address']";
		click(locator, metaDataKey);

		locator = "//input[@name='propertyTypeId']";
		enterText(locator, "Single Family", metaDataKey);
		locator = "//label[text()='Property Status']";
		click(locator, metaDataKey);

		locator = "//input[@name='propertyStatusTypeId']";
		enterText(locator, "Retained", metaDataKey);
		locator = "//label[text()='Property Status']";
		click(locator, metaDataKey);

		locator = "//input[@name='marketValueAmt']";
		enterText(locator, "69000", metaDataKey);

		locator = "//label[text()='Primary Residence']";
		click(locator, metaDataKey);

		locator = "//input[@name='propertyTaxesAmt']";
		enterText(locator, "100", metaDataKey);

		locator = "//input[@name='floodInsuranceAmt']";
		enterText(locator, "50", metaDataKey);

		locator = "//input[@name='hazardInsuranceAmt']";
		enterText(locator, "75", metaDataKey);

		locator = "//input[@name='associationFeesAmt']";
		enterText(locator, "100", metaDataKey);

		locator = "//input[@name='otherExpensesAmt']";
		enterText(locator, "100", metaDataKey);

		locator = "(//span[text()='SAVE'])[2]";
		click(locator, metaDataKey);

		locator = "//div[text()='Retained']";
		waitForElementWithLocator(locator, metaDataKey);

	}
}
