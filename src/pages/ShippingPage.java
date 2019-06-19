package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class ShippingPage extends UIElementMethods{

	String LOSClosingShipping	=	"//span[text()='Shipping / Post-Closing']";
	String LOSCheckShippingScreen	=	"//input[@name='loanClosedDate']";
	String recurssionDate =	"//input[@name='actualRescissionDate']";
	String FundingDate  =	"//input[@name='actualFundsAdvancedDate']";
	String paymentdate =	"//input[@name='actualFirstPaymentDueDate']";
	String maturityDate =	"//input[@name='actualMaturityDate']";
	String packageDate =	"//input[@name='closingPackageReceivedDate']";
	String  closingStatus =	"//input[@name='closingStatusTypeId']";
	String saveShipping = "//span[text()='Save']";

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.loginLendingHive();
			Thread.sleep(1500);
			String xPath = "//span[text()='Closing']";
			String metaDataKey = "Shipping Information";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			waitForElementWithLocator(LOSClosingShipping, metaDataKey);
			addShippingDetails(LOSClosingShipping, metaDataKey);
			
		}
	}
	public void addShippingDetails(String xPath  , String metaDataKey) throws InterruptedException{
		click(xPath, metaDataKey);
		waitForElementWithLocator(LOSCheckShippingScreen, metaDataKey);
		click(LOSCheckShippingScreen, metaDataKey);
		String text = todayDate();
		enterText(recurssionDate, text, metaDataKey);
		enterText(FundingDate, text, metaDataKey);
		enterText(paymentdate, text, metaDataKey);
		enterText(maturityDate, text, metaDataKey);
		enterText(packageDate, text, metaDataKey);
		enterText(closingStatus, "Confirmed", metaDataKey);
		click(saveShipping, metaDataKey);
		Thread.sleep(300);
		
	}
}
