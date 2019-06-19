package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wtc.globalAccelerators.UIElementMethods;

public class PortalRegisterPage extends UIElementMethods{
	String validateMortgageType = "//input[@name='mortgageTypeId']";
	String FHADetailsWindow = "//span[text()='FHA Details']";
	String checkFHADetailsWindow = "//span[text()='Edit FHA Details']";
	String monthBuilt = "//input[@name='monthBuilt']";
	String yearBuilt = "//input[@name='yearBuilt']";
	String saveFHAWindow = "//a[@data-selenium-id='fhaDetailSaveButton']";
	String uploadFile = "//input[@id='newLoanIn_duFile-button-fileInputEl']";
	String importFileTreenode = "//span[text()='Import Loan File']";
	String clickImportBtn = "//a[@data-selenium-id='newLoanIn_btnImport']";
	String convMortgageInsuranceBtn = "//a[@data-selenium-id='btnMortgageIns']";
	String checkConvMortWindow = "//span[text()='Conventional Mortgage Insurance']";
	String pmiType = "//input[@name='pmiPlanTypeId']/../following-sibling::td/div";
	String newBtn = "//a[@data-selenium-id='newBtn']";
	String premiumRateMask = "//td[contains(@class,' x-grid-cell-headerId-gridcolumn')]";
	String inputFocusField = "//input[contains(@class,' x-field-form-focus x-field-default-form-focus')]";
	String startingPointMask = "(//td[contains(@class,' x-grid-cell-headerId-gridcolumn')])[2]";
	String EndingPointMask = "(//td[contains(@class,' x-grid-cell-headerId-gridcolumn')])[3]";
	String checkSaveConvMort = "//span[text()='Ending Payment']";
	String saveConvMort = "//a[@data-selenium-id='saveBtn']";
	String upfrontAmount	 = "//input[@name='upfrontAmountDue']";
	String amountFinanced = "//input[@name='amountFinanced']";
	String saveFHAMortgageWindow = "//a[@data-selenium-id='okButton']";
	String checkmilitaryType = "//input[@name='militaryType']";
	String militaryType = "//input[@name='militaryType']/../following-sibling::td/div";
	String getGuarantee = "//a[@data-selenium-id='btnMIQuote']";
	String saveVAMortgage = "//a[@data-selenium-id='okButton']";

	@org.testng.annotations.Test
	public void enterLoanRegistrationDetails() throws InterruptedException{
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("");
			String metaDataKey = "RegisterPage";
			importFile(metaDataKey);
			enterHBFHADetails(metaDataKey);
		}
	}
	public void importFile(String metaDataKey){
		waitForElementWithLocator(importFileTreenode, metaDataKey);
		click(importFileTreenode, metaDataKey);
		enterTextWithoutClearing(uploadFile, "C:/Projects/HBScriptsPOM/HBImportFiles/Purchase1B.txt", metaDataKey);
		click(clickImportBtn, metaDataKey);
	}

	public void enterHBFHADetails(String metaDataKey) throws InterruptedException{
		String text = getAttributeValue(validateMortgageType, metaDataKey);
		if (text.equals("FHA")) {
			wait = new WebDriverWait(driver, 10);
			click(FHADetailsWindow, metaDataKey);
			waitForElementWithLocator(checkFHADetailsWindow, metaDataKey);
			enterText(monthBuilt, "Jan", metaDataKey);
			enterText(yearBuilt, "2016", metaDataKey);
			Thread.sleep(500);
			click(saveFHAWindow, metaDataKey);
			Thread.sleep(1500);
			enterFHAMortgageDetails(metaDataKey);
		}
	}

	public void checkMortgagePercentage(String metaDataKey){
		locator = "//input[@name='loanToValueRatio']";
		String text = getAttributeValue(locator, metaDataKey);
		System.out.println(text);
		String[] percentValue = text.replace(".", ",").split(",");
		if (Integer.parseInt(percentValue[0])> 80) {
			System.out.println("value greater than 80");
			mortgagePercentage = true;
		}
		else{
			mortgagePercentage = false;			
		}
	}
	public void enterConventionalMortgageDetails(String metaDataKey) throws InterruptedException{
		checkMortgagePercentage(metaDataKey);
		if (mortgagePercentage == true ) {
			click(convMortgageInsuranceBtn, metaDataKey);
			waitForElementWithLocator(checkConvMortWindow, metaDataKey);
			selectDropdownValueByText(pmiType, metaDataKey, "Borrower Paid Monthly Premiums");
			Thread.sleep(500);
			click(newBtn, metaDataKey);
			click(premiumRateMask, metaDataKey);
			enterText(inputFocusField, "0.5", metaDataKey);
			click(startingPointMask, metaDataKey);
			enterText(inputFocusField, "1", metaDataKey);
			click(premiumRateMask, metaDataKey);
			enterText(EndingPointMask, "120", metaDataKey);
			Thread.sleep(200);
			click(checkSaveConvMort, metaDataKey);
			click(saveConvMort, metaDataKey);
			Thread.sleep(2000);
		}
	}

	public void enterFHAMortgageDetails(String metaDataKey){
		checkMortgagePercentage(metaDataKey);
		if (mortgagePercentage == true ) {
			click(convMortgageInsuranceBtn, metaDataKey);
			waitForElementWithLocator(saveFHAMortgageWindow, metaDataKey);
			getAttributeAndEnterValue(upfrontAmount +"/&/"+ amountFinanced, metaDataKey);
			click(saveFHAMortgageWindow, metaDataKey);
		}
	}

	public void enterVAMortgageDetails(String metaDataKey) throws InterruptedException{
		String text = getAttributeValue(validateMortgageType, metaDataKey);
		if (text.equals("VA")) {
			checkMortgagePercentage(metaDataKey);
			if (mortgagePercentage == true ) {
				click(validateMortgageType, metaDataKey);
				waitForElementWithLocator(checkmilitaryType, metaDataKey);
				selectDropdownValueByText(militaryType, metaDataKey, "Reserves/National Guard");
				click(getGuarantee, metaDataKey);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(checkMaskLoad)));
				getAttributeAndEnterValue(upfrontAmount +"/&/"+ amountFinanced, metaDataKey);
				click(checkmilitaryType, metaDataKey);
				click(saveVAMortgage, metaDataKey);
				Thread.sleep(1000);
			}
		}

	}

}

