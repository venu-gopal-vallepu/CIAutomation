package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class EscrowForCompletionPage extends UIElementMethods {
	String escrowForCompletionTreeNode = "//span[text()='Escrow for Completion']";
	String CheckEscrowCompletionPage	=	"//span[text()='Calculate']";
	String EscrowSource	=	"//input[@name='repairEscrowFundingTypeId']";
	String labelEscrow= "//label[text()='Escrow For Completion']";
	String escrowAmountManagedBy	=	"//input[@name='repairEscrowMgmtTypeId']";
	String EscroweventOfSurplus	=	"//input[@name='repairEscrowSurplusTypeId']";;
	String saveEscrowForCompletion	=	"//span[text()='Save']";
	String CheckEscowCompleteSavePopUp	=	"//span[text()='Saved']";;
	String AcceptEscowCompleteSavePopUp	=	"//span[text()='OK']";
	String LOSScheduleLoanForClosing	=	"//span[text()='Schedule Loan for Closing']";
	String AcceptSceduleLoanError	=	"//span[text()='OK']";



	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000710");
			Thread.sleep(1500);
			String xPath = "//span[text()='Closing']";
			String metaDataKey = "Escrow For Commpletion";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			xPath = "//span[text()='Escrow for Completion']";
			addEscrowForCompletion(xPath, metaDataKey);

		}
	}

	public void addEscrowForCompletion(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(escrowForCompletionTreeNode, metaDataKey);
		click(escrowForCompletionTreeNode, metaDataKey);
		waitForElementWithLocator(CheckEscrowCompletionPage, metaDataKey);
		String text = "Borrower";
		enterText(EscrowSource, text, metaDataKey);
		click(labelEscrow, metaDataKey);
		text = "Closing Agent";
		enterText(escrowAmountManagedBy, text, metaDataKey);
		click(labelEscrow, metaDataKey);
		text = "Applied to UPB";
		enterText(EscroweventOfSurplus, text, metaDataKey);
		click(labelEscrow, metaDataKey);
		click(saveEscrowForCompletion, metaDataKey);
		waitForElementWithLocator(CheckEscowCompleteSavePopUp, metaDataKey);
		click(AcceptEscowCompleteSavePopUp, metaDataKey);
		Thread.sleep(200);
	}
}
