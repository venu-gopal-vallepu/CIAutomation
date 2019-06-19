package pages;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class DeclarationsPage extends UIElementMethods{
	String declarationsPAGE = "//div[text()='Declarations']";
	String checkPage = "//label[text()='No']";
	//	public ArrayList<WebElement> declarationForms = new ArrayList<WebElement>();

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("1100001165");
			Thread.sleep(1500);
			String metaDataKey = "Declarations page";
			addDeclarationsDetails(declarationsPAGE, metaDataKey);
		}
	}


	public void addDeclarationsDetails(String xPath, String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(declarationsPAGE, metaDataKey);
		click(declarationsPAGE, metaDataKey);
		Thread.sleep(2000);
		waitForElementWithLocator(checkPage, metaDataKey);
		String locator = "//div[contains(@id,'loanapp-declarationpanel')][contains(@class,'x-panel vertical-panel')][not(contains(@style,'display: none;'))]";
		listWebElements(locator, metaDataKey);
		int num;
		ArrayList<WebElement> declarationForms = (ArrayList<WebElement>) ((ArrayList<WebElement>)listElems).clone();
		for (int i = 0; i < declarationForms.size(); i++) {
			num = i;
			num++;
			String noLocator = "//div[contains(@class,'x-panel vertical-panel')]["+num+"][not(contains(@style,'display: none;'))]//label[text()='No']";
			listWebElements(noLocator, metaDataKey);
			for (int j = 0; j < listElems.size(); j++) {
				WebElement elem = listElems.get(j);
				if (j==11||j==12) {
					int val = j;
					val++;
					String yesLocator = "(//div[contains(@class,'x-panel vertical-panel')]["+num+"][not(contains(@style,'display: none;'))]//label[text()='Yes'])"+"[" + val + "]";
					elem = driver.findElement(By.xpath(yesLocator));
					//						element.click();
				}
				elem.click();
			}
			locator = "//div[contains(@class,'x-panel vertical-panel')]["+num+"][not(contains(@style,'display: none;'))]//input[@name='propertyType']";					
			enterText(locator, "Principal Residence", metaDataKey);

			locator = "//div[contains(text(),'What type of property')]";
			click(locator, metaDataKey);
			Thread.sleep(200);

			locator = "//div[contains(@class,'x-panel vertical-panel')]["+num+"][not(contains(@style,'display: none;'))]//input[@name='titleManner']";					
			enterText(locator, "Jointly with Spouse", metaDataKey);

			locator = "//div[contains(text(),'What type of property')]";
			click(locator, metaDataKey);
			Thread.sleep(200);

		}

		locator = "//a[@data-selenium-id='btnLoanAppSave']";
		click(locator, metaDataKey);
		Thread.sleep(2000);
	}
}
