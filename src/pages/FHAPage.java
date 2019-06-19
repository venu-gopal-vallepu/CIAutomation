package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class FHAPage extends UIElementMethods{
	String FHAPage = "//span[text()='FHA Info']";
	String fhaPageVisibilty = "//input[@name='fhacasenumber']";
	String fhaRefinanceHUDAuth = "//input[@name='hudAuthNo']";
	String saveFHAScreen = "//a[@data-selenium-id='saveButton']";
	String FHACaseNumber = "//input[@name='fhacasenumber']";


	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100001087");
			Thread.sleep(1500);
			String metaDataKey = "FHAPage";
			addFHAInfo(FHAPage, metaDataKey);
		}
	}


	public void addFHAInfo(String xPath,String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(FHAPage, metaDataKey);
		click(FHAPage, metaDataKey);
		waitForElementWithLocator(fhaPageVisibilty, metaDataKey);
		getAttributeValue(fhaPageVisibilty, metaDataKey);
		if (values.isEmpty()) {
			System.out.println("FHA case id value is empty");
		}else{
			clearField(fhaRefinanceHUDAuth, metaDataKey);
		}
		click(saveFHAScreen, metaDataKey);
		Thread.sleep(1000);
		
	}

}
