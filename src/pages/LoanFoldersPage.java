package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;
import com.wtc.globalAccelerators.Utils;

public class LoanFoldersPage extends UIElementMethods {
 
String loanFolders = "//tr[contains(@id,'main.loan.section.loanFolders')]";
	public void clickFirstElement(String metaDataKey){
		String getAllLocators = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
		listWebElements(getAllLocators, metaDataKey);
		listElems.get(0).click();
	}
	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = "//span[text()='Loan Folders']";
			String metaDataKey = "Loan folders";
			/*locator = "//tr[contains(@id,'main.loan.section.loanFolders')]";
			element = driver.findElement(By.xpath(locator));
			element.click();*/
			addLoanFolderDetails(xPath, metaDataKey);
			
		}
	}
	
	public void addLoanFolderDetails(String xPath , String metaDataKey) throws InterruptedException, IOException{
	
		waitForElementWithLocator(loanFolders, metaDataKey);
		click(loanFolders, metaDataKey);
		
		locator = "//span[text()='Upload Document']";
		waitForElementWithLocator(locator, metaDataKey);
	
		locator = "//input[@name='docTag']";
		enterText(locator, "Investor", metaDataKey);
		
		locator = "//label[text()='Tags']";
		click(locator, metaDataKey);
		Thread.sleep(200);
		
		locator = "//span[text()='Upload Document']";
		click(locator, metaDataKey);
		String uploadbtn = "//span[text()='Upload']";
		waitForElementWithLocator(uploadbtn, metaDataKey);
		
		locator = "//input[@name='filefolder']/../following-sibling::td/div";
		click(locator, metaDataKey);
		clickFirstElement(metaDataKey);
		
		locator = "//input[@name='filecategory']/../following-sibling::td/div";
		click(locator, metaDataKey);
		clickFirstElement(metaDataKey);
		
		locator = "//input[@name='filetype']/../following-sibling::td/div";
		click(locator, metaDataKey);
		clickFirstElement(metaDataKey);
		
		locator = "//input[@name='filedesc']";
		enterText(locator, "Description", metaDataKey);
		
		locator = "//input[@name='file']";
		Utils.readPropertiesFile();
		String path = Utils.properties.getProperty("convFixedPurchase");
		enterTextWithoutClearing(locator, path, metaDataKey);
		
		locator = "(//input[@name='docTag']/../following-sibling::td/div)[2]";
		click(locator, metaDataKey);
		clickFirstElement(metaDataKey);
		
		click(uploadbtn, metaDataKey);
		Thread.sleep(200);
		
		locator = "//span[text()='Success']";
		waitForElementWithLocator(locator, metaDataKey);
		
		locator = "//span[text()='OK']";
		click(locator, metaDataKey);
		
		
	}
}
