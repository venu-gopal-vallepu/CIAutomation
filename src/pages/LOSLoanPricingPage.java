package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LOSLoanPricingPage extends UIElementMethods{

	String LOSLoanpricingTreeNode = "//span[text()='Loan Pricing']";
	String LOSClickSelctRate	=	"//span[text()='Select Rate']";
	String LOCCheckSelectRate	=	"//span[text()='Select Pricing']";
	String LOSSelectRate		=	"//td[contains(@class,'span_link')]";
	String LOScheckRateValues	=	"//div[text()='Total Rate and Points']";
	String LOSRateExceptionType	=	"//input[@name='proposedPricingExceptionTypeId']/../following-sibling::td/div";
	String LOSAdjust			=	"//span[text()='Adjust']";
	String LOSCheckAdjusWindow	=	"//span[text()='Exception Pricing']";
	String LOSAdjustDescription	=	"//input[@name='pricingItemDesc']";
	String LOSEnterDiscountValue=	"//div[@data-selenium-id='gridProposedPricing']//div[text()='Total Rate and Points']/../following-sibling::td[contains(@class,'headerId-gridProposedPricing_discountPointsPct')]/div/&///input[@name='pricePct']";
	String ClickValuePercentage	=	"//input[@name='costPct']";
	String AcceptPricingException	=	"//a[@data-selenium-id='btnOk']";
	String CheckSaveAdjustWindow	=	"//div[text()='Total Rate and Points']";
	String SaveLoanPricing		=	"//span[text()='Save']";
	String CheckSaveLoanPricing	=	"//div[text()='Saving...']";


	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000604");
			Thread.sleep(1500);
			String metaDataKey = "Loan Pricing";
			addLoanPricingDetails(LOSLoanpricingTreeNode, metaDataKey);
		}
	}


	public void addLoanPricingDetails(String xPath , String metaDataKey) throws InterruptedException{
	    click(LOSLoanpricingTreeNode, metaDataKey);
		waitForElementWithLocator(LOSClickSelctRate, metaDataKey);
		click(LOSClickSelctRate, metaDataKey);
		waitForElementWithLocator(LOCCheckSelectRate, metaDataKey);
		waitForElementWithLocator(LOSSelectRate, metaDataKey);
		listWebElements(LOSSelectRate, metaDataKey);
		if (listElems.size()>0) {
			listElems.get(0).click();
		}
		waitForElementWithLocator(LOScheckRateValues, metaDataKey);
		Thread.sleep(2000);
		click(LOSRateExceptionType, metaDataKey);
		locator = "//li[text()='Price Exception']";
		click(locator, metaDataKey);
		click(LOSAdjust, metaDataKey);
		waitForElementWithLocator(LOSCheckAdjusWindow, metaDataKey);
		Thread.sleep(2000);
		enterText(LOSAdjustDescription, "Description", metaDataKey);
		getTextAndEnterValue(LOSEnterDiscountValue, metaDataKey);
		click(ClickValuePercentage, metaDataKey);
		click(AcceptPricingException, metaDataKey);
		waitForElementWithLocator(CheckSaveAdjustWindow, metaDataKey);
		click(SaveLoanPricing, metaDataKey);
		checkElementLoadMask(CheckSaveLoanPricing, "Saving...", metaDataKey);
	}
}
