package pages;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

/**
 * @author testadmin
 *
 */
public class CRMLoanTermsPage extends UIElementMethods {
	String loanTermsNode = "//div[text()='Loan Terms']";
	String LoanTermsApplicationDate = "//input[@name='applicationDateTime']";
	String WaitForLoanTermsLoad = "//div[text()='Loading...']";
	String CheckLoantermsSettlementDate = "//label[text()='Combined LTV Ratio']";
	String LoanTermsSettlementDate = "//input[@name='settlementDate']";;
	String LoanTermsLienType = "//input[@name='lienTypeId']";
	String checkLien = "//label[text()='Lien Type']";
	String EnterPropertyDetails = "//input[@name='occupancyTypeId']/&///label[text()='Property Type']/&///input[@name='buildingTypeId']";
	String ProductDetails = "//input[@name='mortgageTypeId']/../following-sibling::td/div/&///input[@name='pricingTierId']/../following-sibling::td/div/&///input[@name='amortizationTypeId']/../following-sibling::td/div/&///input[@name='productPricingTemplateId']/../following-sibling::td/div/&///input[@name='investorId']/../following-sibling::td/div/&///input[@name='loanPurposeTypeId']/../following-sibling::td/div/&///input[@name='purchasePriceAmt']/&///input[@name='loanAmt']";
	String LoanTermsMortgage = "//input[@name='mortgageTypeId']/../following-sibling::td/div/&/1";
	String LoanTermsPricing = "//input[@name='pricingTierId']/../following-sibling::td/div/&/1";
	String LoanTermsAmrtization = "//input[@name='amortizationTypeId']/../following-sibling::td/div/&/1";
	String LoanTermsProduct = "//input[@name='productPricingTemplateId']/../following-sibling::td/div/&/1";
	String LoanTermsDocumentationType = "//input[@name='documentationTypeId']/../following-sibling::td/div/&/1";
	String LoanTermsLoanPurpose = "//input[@name='loanPurposeTypeId']/../following-sibling::td/div/&/1";
	String LoanTermsPurposeOfRefinance = "//input[@name='refiPurposeTypeId']/../following-sibling::td/div/&/1";
	String LoanTermsRefinanceType = "//input[@name='refinanceTypeId']/../following-sibling::td/div/&/1";
	String LoanTermsPricingInvestor = "//input[@name='investorId']/../following-sibling::td/div/&/1";
	String LoanArmPlanID = "//input[@name='armPlanId']/../following-sibling::td/div/&/1";
	String LoanProgramTypeID = "//input[@name='programTypeId']/../following-sibling::td/div/&/1";
	String LoanScore = "//input[@name='representativeCreditScore']";
	String Purchasevalue = "//input[@name='purchasePriceAmt']";
	String LoanTermsdownPaymentPct = "//input[@name='downpaymentPct']";
	String LoanAmount = "//input[@name='loanAmt']";
	String CheckLoanAmount = "//input[@name='totalLoanAmt']";
	String MortgageContingencyDate = "//input[@name='mortgageContingencyDate']";
	String saveLoanTerms = "//span[text()='SAVE']";
	String CheckLoantermsPOPUP = "//span[text()='Lender Loan Number']";
	String LenderLoanNumber = "//div/b[contains(text(),'Lender Loan Number')]";
	String lenderloanOKbutton = "//span[text()='OK']";
	String VerifyLoanTermslOad = "//input[@name='totalLoanAmt']";
	String metaDataKey = "Loan Terms";

	// FHA Window Locators
	String FHAWindow = "//a[@data-selenium-id='btnFHADetail']";
	String FHASectionID = "//input[@name='fhaSectionOfActId']/../following-sibling::td/div";
	String checkFHADetailsWindow = "//input[@name='fhaAdpCodeId']";
	String FHAADPCode = "//input[@name='fhaAdpCodeId']/../following-sibling::td/div";
	String AgencyNumber1 = "//input[@name='agencyCaseNumber1']";
	String AgencyNumber2 = "//input[@name='agencyCaseNumber2']";
	String FHACaseDate = "fhaCaseAssignmentDate";
	String FHALoanAmount = "//input[@name='maxAllowableLoanAmount']/&/(//input[@name='loanAmt'])[2]";
	String saveFHADetailsWindow = "//a[@data-selenium-id='calculate_btn']";

//	VA Window Locators
	String VAWindow = "//a[@data-selenium-id='btnVADetail']";
	String checkVAWindow = "//input[@name='vaCaseAssignmentDate']";
	String VAAgencyCaseNuber1 = "//input[@name='agencyCaseNumber1']";
	String VAAgencyCaseNuber2 = "//input[@name='agencyCaseNumber2']";
	String VAAgencyCaseNuber3= "//input[@name='agencyCaseNumber3']";
	String VAAgencyCaseNuber4 = "//input[@name='agencyCaseNumber4']";
	String VACaseDate = "//input[@name='vaCaseAssignmentDate']";
	String saveVAWindow = "//a[@data-selenium-id='calculate_btn']";
	
//	USDA Window Locators
	String USDAWindow = "//a[@data-selenium-id='btnUSDADetail']";
	String USDACaseDate = "//input[@name='usdaCaseNumberDate']";
	String saveUSDAWindow = "//a[@data-selenium-id='calculate_btn']";
	@Test
	public void loanTerms() throws InterruptedException {
		System.out.println("enterred");
		if (dataListNum == 0) {
			Login objLogin = new Login();
			objLogin.searchPortalLoan("CRM-1200001550");
			String metaDataKey = "LoanTerms page";
			addLoanTerms(loanTermsNode, metaDataKey);
		}
	}

	public void addLoanTerms(String xPath, String metaDataKey)
			throws InterruptedException {
		click(loanTermsNode, metaDataKey);
		checkElementLoadMask(checkMaskLoad, "Loading...", metaDataKey);
		waitForElementWithLocator(xPath, metaDataKey);
		addDate(LoanTermsSettlementDate, "14", metaDataKey);
		enterText(LoanTermsLienType, "First Mortgage", metaDataKey);
		click(checkLien, metaDataKey);
		enterPropertyDetails(EnterPropertyDetails, metaDataKey);
		conventionalProductDetails(ProductDetails, metaDataKey);
		enterText(LoanScore, "740", metaDataKey);
		String text = todayDate();
		enterText(MortgageContingencyDate, text, metaDataKey);
		click(saveLoanTerms, metaDataKey);
		waitForElementWithLocator(CheckLoantermsPOPUP, metaDataKey);
		getElemText(LenderLoanNumber, metaDataKey);
		getNumberFromText(loannumber1);
		click(lenderloanOKbutton, metaDataKey);
		locator = "//div[text()='Saving...']";
		text = "Saving...";
		checkElementLoadMask(locator, text, metaDataKey);
		Thread.sleep(1500);
	}

	public void addFHAInfo() throws InterruptedException {
		elementFound(FHAWindow);
		if (elemFound == true) {
			selectDropdownFirstValue(FHASectionID, metaDataKey);
			waitForElementWithLocator(FHAWindow, metaDataKey);
			click(FHAWindow, metaDataKey);
			waitForElementWithLocator(checkFHADetailsWindow, metaDataKey);
			Thread.sleep(2000);
			selectDropdownFirstValue(FHAADPCode, metaDataKey);
			enterText(AgencyNumber1, "111", metaDataKey);
			enterText(AgencyNumber2, "11", metaDataKey);
			String text1 = todayDate();
			enterText(FHACaseDate, text1, metaDataKey);
			Thread.sleep(500);
			getAttributeAndEnterValue(FHALoanAmount, metaDataKey);
			clickOnElementUsingJavascript(saveFHADetailsWindow, metaDataKey);
			Thread.sleep(5000);
			waitForElementWithLocator(LoanTermsSettlementDate, metaDataKey);
		}
	}
	
	public void addVAInfo() throws InterruptedException{
		elementFound(VAWindow);
		if(elemFound == true){
			waitForElementWithLocator(VAWindow, metaDataKey);
			click(VAWindow, metaDataKey);
			waitForElementWithLocator(checkVAWindow, metaDataKey);
			Thread.sleep(2000);
			enterText(VAAgencyCaseNuber1, "11", metaDataKey);
			enterText(VAAgencyCaseNuber2, "11", metaDataKey);
			enterText(VAAgencyCaseNuber3, "1", metaDataKey);
			enterText(VAAgencyCaseNuber4, "111", metaDataKey);
			locator = "//input[@name='vaCaseAssignmentDate']";
			String text = todayDate();
			enterText(VACaseDate, text, text);
			clickOnElementUsingJavascript(saveVAWindow, metaDataKey);
			Thread.sleep(2500);
			
		}
	}

	public void addUSDAInfo() throws InterruptedException{
		elementFound(USDAWindow);
		if(elemFound == true){
            waitForElementWithLocator(USDAWindow, metaDataKey);
			click(USDAWindow, metaDataKey);
			waitForElementWithLocator(USDACaseDate, metaDataKey);
			String text = todayDate();
			enterText(USDACaseDate, text, metaDataKey);
			Thread.sleep(500);
			clickOnElementUsingJavascript(saveUSDAWindow, metaDataKey);
			Thread.sleep(2000);
			waitForElementWithLocator(LoanTermsSettlementDate, text);


		}

	
	}
}
