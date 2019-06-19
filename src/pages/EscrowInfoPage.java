package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class EscrowInfoPage extends UIElementMethods {
	
	String EscrowInformation = "//span[text()='Escrow Information']";
	String CheckLOSEscrowPage	=	"//span[text()='New Escrow Information']";
	String EscrowAddClosingFee	=	"//span[text()='Add Escrow Information']";
	String escrowitemID	=	"//input[@name='escrowItemId']/../following-sibling::td/div";
	String escrowitemType	=	"//span[text()='1003 - Mortgage Insurance']";
	String reCalculateBtn = "//a[@data-selenium-id='recalculateMonthsCollected']";
	String AnnualEscrowAmount	=	"//input[@name='annualEscrowAmt']";
	String Address = "//input[@name  = 'addressLineOne']/&///input[@name='postalCodeId']/&///input[@name  = 'city']";
	String MonthsCollected	=	"//input[@name='paymentsCollectedAtClosing']";
	String CushionMonths	=	"//input[@name='cushionMonths']";
	String PayeeName	=	"//input[@name='payeeName']";
	String escrowPostalCode	=	"//input[@name='postalCodeId']";
	String escrowCityAndState	=	"//li[text()='Stephentown']/&/1";
	String escrowTelephoneNUmber	=	"//input[@name='telephoneNumber']";
	String escrowPaymentCycle	=	"//input[@name='escrow-paymentcycle-inputEl']/../following-sibling::td/div/&/1";
	String escrowNewDate	=	"//span[text()='New Date']";
	String escrowPaymentDueDateMask	=	"//td[contains(@class,'x-grid-cell-headerId-datecolumncustom')]";
	String escrowPaymentDueDate	=	"//input[@name='disbursementDate']";
	String escrowDisburseAmountMask	=	"(//div[contains(@class,'x-grid-view-default x-unselectable')])[2]/table/tbody/tr/td[2]";
	String escrowAmountAmount	=	"//input[@name='disbursementAmt']";
	String escrowCushionMonths	=	"//input[@name='cushionMonths']";
	String escrowSaveAndClose	=	"//a[@data-selenium-id='saveButton']";
	String generateButton = "//span[text()='Generate...']";
	String checkSavedEscrowVerification = "//div[text()='Saving...']";

	
	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = "//span[text()='Closing']";
			String metaDataKey = "Escrow Information";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			xPath = "//span[text()='Escrow Information']";
			addEscrowInfoPage(xPath, metaDataKey);

		}
	}
	
	public void addEscrowInfoPage(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(EscrowInformation, metaDataKey);
		click(EscrowInformation, metaDataKey);
		waitForElementWithLocator(CheckLOSEscrowPage, metaDataKey);
		click(CheckLOSEscrowPage, metaDataKey);
		waitForElementWithLocator(EscrowAddClosingFee, metaDataKey);
		click(escrowitemID, metaDataKey);
		locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
		listWebElements(locator, metaDataKey);
		listElems.get(0).click();
		enterText(AnnualEscrowAmount, "1300", metaDataKey);
		enterText(MonthsCollected, "24", metaDataKey);
		click(reCalculateBtn, metaDataKey);
		enterAdressAndZip(Address, metaDataKey);
		click(generateButton, metaDataKey);
		Thread.sleep(500);
		clickOnElementUsingJavascript(escrowSaveAndClose, metaDataKey);
		checkElementLoadMask(checkSavedEscrowVerification, "Saving...", metaDataKey);
	}
}
