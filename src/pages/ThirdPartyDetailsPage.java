package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class ThirdPartyDetailsPage extends UIElementMethods {
	String thirdPartyTreePanel = "//span[text()='Third Party Info']";
	String checkThirdPartyinfo = "//a[@data-selenium-id='tpi_newButton']";
	String checkThirdPartyPopup = "//label[text()='Other Party Type']";
	String otherPartyinfo = "//input[@name='otherPartyTypeId']";
	String firstName = "//input[@name='firstName']";
	String middleName = "//input[@name='middleName']";
	String lastName = "//input[@name='lastName']";
	String suffix = "//input[@name='nameSuffix']";
	String saveThirdparty = "//a[@data-selenium-id='tpid_saveButton']";
	String checksavedThirdParty = "//div[text()='Saving...']";


	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("2300002463");
			Thread.sleep(1500);
			String xPath = "//span[text()='Processing']";
			String metaDataKey = "Third";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addThirdPartyDetails(thirdPartyTreePanel, metaDataKey);
		}
	}
	
	
	public void  addThirdPartyDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(thirdPartyTreePanel, metaDataKey);
		click(thirdPartyTreePanel, metaDataKey);
		waitForElementWithLocator(checkThirdPartyinfo, metaDataKey);
		click(checkThirdPartyinfo, metaDataKey);
		waitForElementWithLocator(checkThirdPartyPopup, metaDataKey);
		enterText(otherPartyinfo, "Seller", metaDataKey);
		click(checkThirdPartyPopup, metaDataKey);
		enterText(firstName, "Carl", metaDataKey);
		enterText(middleName, "K", metaDataKey);
		enterText(lastName, "Max", metaDataKey);
		enterText(suffix, "Sr.", metaDataKey);
		xPath = "//input[@name='addressLineOne']/&///input[@name='postalCodeId']/&///input[@name='city']";
		enterAdressAndZip(xPath, metaDataKey);
		click(saveThirdparty, metaDataKey);
		checkElementLoadMask(checksavedThirdParty, "Saving...", metaDataKey);
	}

}
