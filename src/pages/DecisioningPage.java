package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class DecisioningPage extends UIElementMethods {
	String DecisioningPage = "//span[text()='Decisioning']";
	String LOSDecisioningType	=	"(//div[contains(@class,'x-form-arrow-trigger')])[2]";
	String LOSAddDecisionType	=	".//span[text()='Add']";
	String LOSDecisionApproved1	=	".//td[contains(@class,'x-grid-cell-headerId-checkcolumn')]";;
	String LOSApprovedDecision2	=	"(.//td[contains(@class,'x-grid-cell-headerId-checkcolumn')])[2]";
	String LOSSaveDecisioning	=	".//span[text()='Save']";
	String checkSavedDecisioningPage = "//div[text()='Saving...']";
	
	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = "//span[text()='Underwriting']";
			String metaDataKey = "Decisioning page";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addDecisionDetails(DecisioningPage, metaDataKey);
		}
	}
	
	
	public void addDecisionDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(DecisioningPage, metaDataKey);
		click(DecisioningPage, metaDataKey);
		waitForElementWithLocator(LOSAddDecisionType, metaDataKey);
		click(LOSDecisioningType, metaDataKey);
		locator = "//li[text()='Approve']";
		click(locator, metaDataKey);
		click(LOSAddDecisionType, metaDataKey);
		Thread.sleep(500);
		countAndClick(LOSDecisionApproved1, metaDataKey);
		click(LOSSaveDecisioning, metaDataKey);
		checkElementLoadMask(checkSavedDecisioningPage, "Saving...", metaDataKey);

	}
}
