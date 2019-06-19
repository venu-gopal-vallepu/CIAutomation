package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class VendorServicesPage extends UIElementMethods {

	String LOSVendorServices	=	"//span[text()='Vendor Services']";
	String CheckVendorServices	=	"//span[text()='Order New Service']";
	String LOSTitleSelectService	=	"//input[@placeholder='--Select a Service--']/../following-sibling::td/div";
	String LOSTitleOrderNewService	=	"//span[text()='Order New Service']";
	String LOSTitleOrderManualMethod	=	"//label[text()='Manual']";
	String LOSTitleOrderAutomatedMethod	=	"//label[text()='Automated']";;
	String LOSTitleSubmitOrder	=	"//span[text()='Submit Order']";
	String LOSTitleOrderCheck	=	"//div[text()='Title Order updated successfully']";
	String LOSTitleOrderCreated	=	"//span[text()='OK']";
	String LOSTitleCheckvendorServices	=	"//div[text()='Title']";
	String LOSClosingSelectService	=	"//input[@placeholder='--Select a Service--']/../following-sibling::td/div/&/1";
	String LOSClosingOrderNewService	=	"//span[text()='Order New Service']";
	String LOSClosingOrderMethod	=	"//label[text()='Manual']/&/1";
	String LOSClosingSubmitOrder	=	"//span[text()='Submit Order']";
	String LOSClosingOrderCheck	=	"//div[text()='Closing Order updated successfully']";
	String LOSClosingOrderCreated	=	"//span[text()='OK']";
	String LOSClosingCheckvendorServices	=	"//div[text()='Title']";


	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("5000000006");
			Thread.sleep(1500);
			String xPath = "//span[text()='Processing']";
			String metaDataKey = "Vendor Services";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addVendorServices(LOSVendorServices, metaDataKey);
		}
	}

	public void addVendorServices(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(LOSVendorServices, metaDataKey);
		click(LOSVendorServices, metaDataKey);
		waitForElementWithLocator(CheckVendorServices, metaDataKey);
		Thread.sleep(1500);
		String[] vendorServicesTypes = {"Title Insurance", "Closing Service"};
		for (int i = 0; i < vendorServicesTypes.length; i++) {
			waitForElementWithLocator(LOSTitleSelectService, metaDataKey);
			click(LOSTitleSelectService, metaDataKey);
			locator = "//li[text()='"+ vendorServicesTypes[i] +"']";
			click(locator, metaDataKey);
			click(LOSTitleOrderNewService, metaDataKey);
			waitForElementWithLocator(LOSTitleOrderAutomatedMethod, metaDataKey);
			click(LOSTitleOrderAutomatedMethod, metaDataKey);
			click(LOSTitleSubmitOrder, metaDataKey);
			waitForElementWithLocator(LOSTitleOrderCreated, metaDataKey);
			Thread.sleep(500);
			click(LOSTitleOrderCreated, metaDataKey);
			locator = "//div[contains(@class,'x-grid-with-col-lines x-grid-no-row-lines')]//table";
			waitForElementWithLocator(locator, metaDataKey);
			Thread.sleep(500);

		} 
	}
}
