package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class CreditPage extends UIElementMethods {
	
	String creditPageTreeNode = "//div[text()='Credit']";
	String checkCreditPage = "//span[text()='Order a Credit Report']";
	String checkBox1 = "(//input[@role='checkbox'])[1]";
	String checkBox2 = "(//input[@role='checkbox'])[2]";
	String createLink = "//span[text()='Create Link']";
	String saveLink = "//span[text()='Save']";
	String orderCreditReport = "//span[text()='Order a Credit Report']";
	String CheckCreditReportPopUp = "//span[text()='Order Credit Reports']";
	String addBorrower = "//div[@data-selenium-id='applicantOrder']//input[@role='checkbox']";
	String addToOrder = "//span[text()='Add to Order']";
	String EdiProviderType = "//input[@name='ediProviderTypeId']/../following-sibling::td/div";
	String submitOrder = "//span[text()='Submit Order']";
	
	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("enterred");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("LEAD-1000012");
			String metaDataKey = "Credits page";
			addCreditDetails(creditPageTreeNode, metaDataKey);
		}
	}
	
	
	public void addCreditDetails(String xPath ,String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(creditPageTreeNode, metaDataKey); 
		click(creditPageTreeNode, metaDataKey);
		waitForElementWithLocator(checkCreditPage, metaDataKey); 
		click(checkBox1, metaDataKey);
		click(checkBox2, metaDataKey);
		click(createLink, metaDataKey);
		click(saveLink, metaDataKey);
		Thread.sleep(2000);
		click(orderCreditReport, metaDataKey);
		waitForElementWithLocator(CheckCreditReportPopUp, metaDataKey);
		countAndClick(addBorrower, metaDataKey);
		Thread.sleep(200);
		click(addToOrder, metaDataKey);
		Thread.sleep(200);
		selectDropdownValueByText(EdiProviderType, metaDataKey, "Fannie Mae");
		clickOnElementUsingJavascript(submitOrder, metaDataKey);
		locator = "//div[text()='Saving...']";
		String text1 = "Saving...";
		checkElementLoadMask(locator, text1, metaDataKey);
		locator  = "//span[text()='Error']";
		elementFound(locator);
		if (elemFound == true) {
			locator  = "//span[text()='OK']";
			click(locator, metaDataKey);
			locator  = "(//a[@data-selenium-id='cancelButton'])[2]";
			clickOnElementUsingJavascript(locator, metaDataKey);
			Thread.sleep(1000);
		}else{
			locator = "//div[text()='COMPLETE']";
			waitForElementWithLocator(locator, metaDataKey);
		}

	}
}
