package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class ConsentPage extends UIElementMethods {
	String consentPageTreeNode = "//div[text()='Consent']";
	String checkConsentPage = "//span[text()='Borrower Consent Information']";
	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("enterred");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("LEAD-1000012");
			String metaDataKey = "Consent page";
			enterConsentDetails(consentPageTreeNode, "Verbal",metaDataKey);
		}
	}
	
	public void enterConsentDetails(String xPath ,String text, String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(consentPageTreeNode, metaDataKey);
		click(consentPageTreeNode, metaDataKey);
		waitForElementWithLocator(checkConsentPage, metaDataKey);
		String locator = "//tr[contains(@class,'x-grid-data-row')]/td[2]" ;
		countAndClick(locator, metaDataKey);
		String loc1 = "//tr[contains(@class,'x-grid-data-row')]/td[3]" ;
		String loc2 = "//input[contains(@class,'x-field-default-form-focus')]" ;
		String loc3 = "//tr[contains(@class,'x-grid-data-row')]/td[1]" ;
		clickOnMaskAndEnterValue(loc1, loc2, loc3, text, metaDataKey);
		locator = "//span[text()='SAVE']";
		click(locator, metaDataKey);
		Thread.sleep(2000);
	}
}
