package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;



public class crmRatiosPage extends UIElementMethods {
	String ratiospage = "//div[text()='Ratios']";

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("1100000611");
			Thread.sleep(1500);
			String metaDataKey = "Ratios Page";
			String xPath = "//div[text()='Ratios']";
			addCRMRatiosDetails(ratiospage, metaDataKey);
		}
	}


	public void addCRMRatiosDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(ratiospage, metaDataKey);
		click(ratiospage, metaDataKey);
		locator = "//div[text()='Loan Summary']";
		waitForElementWithLocator(locator, metaDataKey);
		locator = "//a[contains(@class,'x-btn inline-button')]";
		listWebElements(locator, metaDataKey);
		for (int i = 0; i < listElems.size(); i++) {
			listElems.get(i).click();
			Thread.sleep(1000);
			locator = "//div[contains(@class,'x-css-shadow')][contains(@style,'display: block;')]";
			element = driver.findElement(By.xpath(locator));
			actions.moveToElement(element).sendKeys(Keys.ESCAPE);
			Thread.sleep(500);
			locator = "//a[contains(@class,'x-btn inline-button')]";
			listWebElements(locator, metaDataKey);
		}
	}
}
