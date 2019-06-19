package pages;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.sun.jna.platform.win32.WinDef.LCID;
import com.wtc.globalAccelerators.UIElementMethods;

public class DemographicPage extends UIElementMethods {
	String demographicPage = "//div[text()='Demographic Info']";
	String checkDemographic = "//label[text()='Male']";

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("enterred");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("1100000675");
			Thread.sleep(1500);
			String metaDataKey = "Demographic page";
			addDemographicDetails(demographicPage, metaDataKey);
		}
	}

	public void addDemographicDetails(String xPath, String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(demographicPage, metaDataKey);
		click(demographicPage, metaDataKey);
		Thread.sleep(2000);
		waitForElementWithLocator(checkDemographic, metaDataKey);
		String locator = "//table[contains(@id,'info_borrower')][not(contains(@style,'display: none;'))]//input";
		listWebElements(locator, metaDataKey);

		ArrayList<WebElement> demographicBorrowers = (ArrayList<WebElement>) ((ArrayList<WebElement>)listElems).clone();
		for (int i = 0; i < demographicBorrowers.size(); i++) {
			element = demographicBorrowers.get(i);
			element.click();
			Thread.sleep(500);

			String appTaken = "//table[@data-selenium-id='appTakenByTypeGroupId']//input";
			listWebElements(appTaken, metaDataKey);
			getElementFormList(listElems , listElems.size());
			element.click();
			Thread.sleep(200);

			String locator1 = "//table[contains(@class,' x-item-disabled')]//label[text()='Yes']";
			elementFound(locator1);
			if (elemFound == false) {
				locator = "//label[text()='Yes']";
				click(locator, metaDataKey);
				locator = "(//label[text()='Yes'])[2]";
				click(locator, metaDataKey);
				locator = "(//label[text()='Yes'])[3]";
				click(locator, metaDataKey);
				Thread.sleep(200);
			}

			String ethnicity = "//table[@data-selenium-id='ethnicityGroup']//input[@role='checkbox']";
			listWebElements(ethnicity, metaDataKey);
			getElementFormList(listElems , listElems.size());
			element.click();
			Thread.sleep(200);

			String gender = "//table[@data-selenium-id='genderGroup']//input[@role='checkbox']";
			listWebElements(gender, metaDataKey);
			getElementFormList(listElems , listElems.size());
			element.click();
			Thread.sleep(300);

			String race = "//table[@data-selenium-id='raceGroup']//input[@role='checkbox']";
			listWebElements(race, metaDataKey);
			getElementFormList(listElems , listElems.size());
			element.click();
			Thread.sleep(200);
			
			locator = "//span[text()='SAVE']";
			click(locator, metaDataKey);

			locator = "//div[text()='Demographic Info updated']";
			waitForElementWithLocator(locator, metaDataKey);
			Thread.sleep(2000);

		}
	}


}
