package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class ScheduleLoanForClosingpage extends UIElementMethods {
	String ScheduleLoanForClosingTreeNode = "//span[text()='Schedule Loan for Closing']";
	String CheckClosingInfoScreen	=	"//span[text()='Closing Info']";
	String LOSInterimIntrestType	=	".//input[@name='interimInterestTypeId']";
	String LOSClosingEstimatedDate	=	"//input[@name='anticipatedSettlementDate']";
	String LOSScheduleSetFirmDate	=	"//label[text()='Set As Firm Date']";
	String LOSScheduleTimeZone	=	".//input[@name='scheduledClosingTimeZone']";
	String LOSClosingSettlementDate	=	"//label[text()='Same as Estimated Date']/&/1";
	String LOSClosingAgent	=	"//input[@name='pkgSentToClosingAgentDate']";
	String LOSClosingHUDApprovalDate	=	"//input[@name='hudTitleApprovalDate']";
	String LOSMERSDesignatedMortgage	=	"//label[text()='MERS Designated as Mortgagee']";
	String LOSMERSBatchDate	=	"//input[@name='mersBatchDate']";
	String LOSClosingRegistrationStatus	=	".//input[@name='mersRegistrationStatusId']";
	String LOSClosingActualSettlementDate	=	"//input[@name='loanClosedDate']";
	String LOSClosingACtualExpirationDate	=	"//input[@name='actualRescissionDate']";
	String LOSClosingACtualFundingDate	=	"//input[@name='actualFundsAdvancedDate']";
	String LOSPackageRecievedDate	=	"//input[@name='closingPackageReceivedDate']";
	String LOSClosingStatus	=	".//input[@name='closingStatusTypeId']/../following-sibling::td/div/&/1";
	String LOSClosingWaiveInitialLOan	=	"//table[@data-selenium-id='waiveInitLeSevenDayWpYn']//label[text()='No']";
	String LOSClosingWaiveInitialClosingDisclosure	=	"//table[@data-selenium-id='waiveInitCdThreeDayWpYn']//label[text()='No']";
	String ClosingLocationTreepanel	=	"//span[text()='Closing Location']";
	String CheckClosingLocationPage	=	"//label[text()='Closing/Settlement Company']";
	String ClosingAgent	=	"//input[@name='closingAgentCompanyId']/../following-sibling::td/div/&/1";
	String CopyFromClosingCompany	=	"//span[text()='Copy from Closing Company']";
	String SaveScheduleLoanForClosing	=	".//span[text()='Save']";
	String CheckSavedClosingPopUp	=	"//span[text()='Saved']";
	String AcceptClosingPOPUp	=	"//span[text()='OK']";
//	addclosing/theprivider
	String serviceProviderAuthorityName = "//input[@name='serviceProviderLicenseAuthorityName']";
	String serviceProviderIssuedate = "//input[@name='serviceProviderLicenseIssueDate']";
	String serviceProviderLicence = "//input[@name='serviceProviderLicense']";
	String contactAgencyName = "//input[@name='contactPersonName']";
	String licenceAuthorityLevel = "//input[@name='contactLicenseAuthorityType']";
	String LicenceAuthorityName = "//input[@name='contactLicenseAuthorityName']";
	String LicenceIssuedate = "//input[@name='contactLicenseIssueDate']";
	String EditClosingAdress = "//input[@name='agentAddressLine1']/&///input[@name='postalCodeId']/&///input[@name='city']";
	String contactPhone = "//input[@name='phone']";
	String contactMobile = "//input[@name='mobile']";
	String contactEmail = "//input[@name='email']";
	String closeEditClosingInfoWindow = "(//a[@data-selenium-id='saveButton'])[2]";
	String checkSavedClosingVerification = "//div[text()='Saving...']";
//addsetlementMailingLocation
	String addSettlementbtn = "//span[text()='Add Settlement / Mailing Location']";
	String checkSettlementWindow = "//label[text()='Copy Provider Company']";
	String copyProviderName = "//input[@name='closingAgentId']/../following-sibling::td/div";
	String copyVendorbtn = "//span[text()='Copy Vendor']";
	String mailingInstruction = "//input[@name='mailingInstructionId']";
	String AdditionalInstructions = "//textarea[@name='additionalInstructions']";


	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = "//span[text()='Closing']";
			String metaDataKey = "Schedule Loan for Closing";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			xPath = "//span[text()='Schedule Loan for Closing']";
			addScheduleLoanForClosing(xPath, metaDataKey);

		}
	}

	public void addSettlementMailingLocation(String xPath , String metaDataKey) throws InterruptedException{
		click(addSettlementbtn, metaDataKey);
		waitForElementWithLocator(checkSettlementWindow, metaDataKey);
//		enterText(copyProviderName, "Solidifi Title Agency, LTD, LLC", metaDataKey);
		selectDropdownFirstValue(copyProviderName, metaDataKey);
		click(checkSettlementWindow, metaDataKey);
		click(copyVendorbtn, metaDataKey);
		Thread.sleep(200);
		enterText(mailingInstruction, "Borrower closing at alternative location", metaDataKey);
		click(checkSettlementWindow, metaDataKey);
		enterText(AdditionalInstructions, "additional instructions", metaDataKey);
		click(closeEditClosingInfoWindow, metaDataKey);
		checkElementLoadMask(checkSavedClosingVerification, "Saving...", metaDataKey);
	}
	
	
	
	public void addClosingTitleProvider(String xPath , String metaDataKey) throws InterruptedException{
		locator = "//span[text()='Closing/Title Provider']";
		click(locator, metaDataKey);
		locator = "//span[text()='Add Closing / Title Provider']";
		click(locator, metaDataKey);
		String checkClosing = "//label[text()='Provider Type']";
		waitForElementWithLocator(checkClosing, metaDataKey);
		locator = "//input[@name='serviceProviderType']/../following-sibling::td/div";
		selectDropdownFirstValue(locator, metaDataKey);
		click(checkClosing, metaDataKey);
		locator = "//span[text()='Copy Vendor']";
		click(locator, metaDataKey);
		locator = "//input[@name='serviceProviderLicenseAuthorityType']";
		enterText(locator, "Public Local", metaDataKey);
		click(checkClosing, metaDataKey);
		locator = "//input[@name='serviceProviderLicenseAuthorityType']";
		enterText(locator, "Public Local", metaDataKey);
		enterText(serviceProviderAuthorityName, "sudershan", metaDataKey);
		String text = todayDate();
		enterText(serviceProviderIssuedate, text, metaDataKey);
		enterText(serviceProviderLicence, "GF78d", metaDataKey);
		enterText(contactAgencyName, "Sudershan", metaDataKey);
		enterText(licenceAuthorityLevel, "Public Local", metaDataKey);
		click(checkClosing, metaDataKey);
		enterText(LicenceAuthorityName, "public", metaDataKey);
		text = todayDate();
		enterText(LicenceIssuedate, text, metaDataKey);
		enterAdressAndZip(EditClosingAdress, metaDataKey);
		enterText(contactPhone, "(777) 777-7777", metaDataKey);
		enterText(contactMobile, "(777) 777-7777", metaDataKey);
		enterText(contactEmail, "sudershan@busa.com", metaDataKey);
		clickOnElementUsingJavascript(closeEditClosingInfoWindow, metaDataKey);
		
		checkElementLoadMask(checkSavedClosingVerification, "Saving...", metaDataKey);

	}

	public void addScheduleLoanForClosing(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(ScheduleLoanForClosingTreeNode, metaDataKey);
		click(ScheduleLoanForClosingTreeNode, metaDataKey);
		waitForElementWithLocator(CheckClosingInfoScreen, metaDataKey);
		addClosingTitleProvider(xPath, metaDataKey);
		addSettlementMailingLocation(xPath, metaDataKey);
		click(CheckClosingInfoScreen, metaDataKey);
		waitForElementWithLocator(LOSInterimIntrestType, metaDataKey);
		enterText(LOSInterimIntrestType, "Collected at Closing", metaDataKey);
		String interinType = "//label[text()='Interim Interest Type']";
		click(interinType, metaDataKey);
		addDate(LOSClosingEstimatedDate, "14", metaDataKey);
//		click(LOSScheduleSetFirmDate, metaDataKey);
//		Thread.sleep(100);
//		enterText(LOSScheduleTimeZone, "US/Alaska", metaDataKey);
//		click(interinType, metaDataKey);
		String text = todayDate();
		enterText(LOSClosingAgent, text, metaDataKey);
		enterText(LOSClosingHUDApprovalDate, text, metaDataKey);
		click(LOSMERSDesignatedMortgage, metaDataKey);
		enterText(LOSMERSBatchDate, text, metaDataKey);
		enterText(LOSClosingRegistrationStatus, "Registered", metaDataKey);
		click(interinType, metaDataKey);
//		click(LOSClosingWaiveInitialLOan, metaDataKey);
//		click(LOSClosingWaiveInitialClosingDisclosure, metaDataKey);
		Thread.sleep(100);
		click(SaveScheduleLoanForClosing, metaDataKey);
		waitForElementWithLocator(CheckSavedClosingPopUp, metaDataKey);
		click(AcceptClosingPOPUp, metaDataKey);

	}
}
