package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LoanStauspage1 extends UIElementMethods {
	String LOSLoanstatus	=	".//span[text()='Loan Status']";
	String CheckLoanStatusWindow	=	"//label[text()='Old Milestone / Status']";
	String LOSProcessingMIlestone	=	".//input[@name='newStatus']/../following-sibling::td/div";
	String LOSsaveProcessingMIlestone	=	".//span[text()='Save']";;
	String LOSCheckSaveFirstMIlestone	=	"//div[text()='Saving...']";


	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.loginLendingHive();
			Thread.sleep(1500);
			String xPath = "//span[text()='Processing']";
			String metaDataKey = "Funding Information";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			xPath = ".//span[text()='Loan Status']";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addLoanStatusDetails(xPath, metaDataKey);

		}
	}
	
	public void addLoanStatusDetails(String xPath, String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(CheckLoanStatusWindow, metaDataKey);
//		xPath = "//div[text()='Underwriting - Initial Underwriting Review']/&/2";
		String[] arrowpath = xPath.split("/&/");
	
		elementFound(arrowpath[0]);
		if (elemFound == true) {
			if(arrowpath[1].equals("1")){
				String[] loanStatusTypes = {"Application - Application - In Process","Application - Application - Activated","Set-Up  - Setup - In Process","Processing - Processing - In Process","Underwriting - Initial Underwriting Review"};
				for (int i = 0; i < loanStatusTypes.length; i++) {
					click(LOSProcessingMIlestone, metaDataKey);
					Thread.sleep(200);
					locator = "//span[contains(text(),'"+loanStatusTypes[i]+"')]";
					elementFound(locator);
					if (elemFound == true) {
						click(locator, metaDataKey);
						click(LOSsaveProcessingMIlestone, metaDataKey);
						checkElementLoadMask(LOSCheckSaveFirstMIlestone, "Saving...", metaDataKey);
					}
				}
			}else if(arrowpath[1].equals("2")){
				String[] loanStatusTypes1 = {"Clear to Close - Clear to Close", "Closing - Closing - In Process" , "Closing - Docs Out"};
				for (int i = 0; i < loanStatusTypes1.length; i++) {
					click(LOSProcessingMIlestone, metaDataKey);
					Thread.sleep(200);
					locator = "//span[contains(text(),'"+loanStatusTypes1[i]+"')]";
					elementFound(locator);
					if (elemFound == true) {
						click(locator, metaDataKey);
						click(LOSsaveProcessingMIlestone, metaDataKey);
						checkElementLoadMask(LOSCheckSaveFirstMIlestone, "Saving...", metaDataKey);
					}
				}
			}else if(arrowpath[1].equals("3")){
				String[] loanStatusTypes2 = {};
				for (int i = 0; i < loanStatusTypes2.length; i++) {
					click(LOSProcessingMIlestone, metaDataKey);
					Thread.sleep(200);
					locator = "//span[contains(text(),'"+loanStatusTypes2[i]+"')]";
					elementFound(locator);
					if (elemFound == true) {
						click(locator, metaDataKey);
						click(LOSsaveProcessingMIlestone, metaDataKey);
						checkElementLoadMask(LOSCheckSaveFirstMIlestone, "Saving...", metaDataKey);
					}
				}
			}
			
		}  
	}
}
