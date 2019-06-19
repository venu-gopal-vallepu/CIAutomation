package pages;

import com.wtc.globalAccelerators.UIElementMethods;

public class LoanstatusPage extends UIElementMethods {
	String LOSLoanstatus	=	".//span[text()='Loan Status']";
	String CheckLoanStatusWindow	=	"//label[text()='Old Milestone / Status']";
	String LOSProcessingMIlestone	=	".//input[@name='newStatus']/../following-sibling::td/div";
	String LOSProcessingMIlestonestatus	=	".//li/span[contains(text(),'Application - Application - In Process')]";
	String LOSsaveProcessingMIlestone	=	".//span[text()='Save']";;
	String LOSCheckSaveFirstMIlestone	=	"//div[text()='Saving...']";

	public void addLoanStatusDetails(String xPath, String metaDataKey , String testData) throws InterruptedException{
		waitForElementWithLocator(CheckLoanStatusWindow, metaDataKey);
		click(LOSProcessingMIlestone, metaDataKey);
		locator = "//span[contains(text(),'"+testData+"')]";
		elementFound(locator);
		if (elemFound == true) {
			System.out.println("Loan status given in excel is found " + testData);
		}else{
			String[] loanStatusTypes = {"Application - Application - Activated","testing123", "dfdd"};
			for(int i = 0; i < loanStatusTypes.length; i++) {
				locator = "//span[contains(text(),'"+loanStatusTypes[i]+"')]";
				elementFound(locator);
				if (elemFound == true) {
					break;
				} else {
					continue;
				}
			}
		}
		elementFound(locator);
		if (elemFound==true) {
			click(locator, metaDataKey);
			click(LOSsaveProcessingMIlestone, metaDataKey);
			checkElementLoadMask(LOSCheckSaveFirstMIlestone, "Saving...", metaDataKey);
		}

	}
}



