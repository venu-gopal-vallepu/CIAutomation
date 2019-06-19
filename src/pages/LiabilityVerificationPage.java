package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LiabilityVerificationPage extends UIElementMethods {

	String LOSLiabilityVerificationInfo	=	".//span[text()='Liability Verification']";
	String checlLiabiltyScreen = "//span[text()='New Liability']";
	String ClickAllLiabiltyCopyAmounts	=	"//img[@data-qtip='Copy amounts to verified and used']";;
	String LOSLiabityAmountID	=	"(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[2]";
	String LOSLiabityVerifiedBalAmount	=	".//input[@name='verifiedBalAmt']";
	String LOSLiabityVerifiedpmtAmount	=	".//input[@name='verifiedPmtAmt']";
	String LOSLiabityVerifiedNumOfPmts	=	".//input[@name='verifiedNumOfPmts']";
	String LOSLiabityVerifiedUsedBalAmount	=	".//input[@name='usedBalAmt']";
	String LOSLiabityVerifiedUsedPmtAmount	=	".//input[@name='usedPmtAmt']";
	String LOSLiabityVerifiedUsednoofpmts	=	".//input[@name='usedNumOfPmts']";
	String LOSSaveLiabilityusedinfo	=	".//span[text()='Ok']";
	String LOSsaveLiabiltyVerification	=	".//span[text()='Save']";
	String verificationDocumentDate = 	"//input[@name='verificationDocumentDate']";
	String mannerverified = "//input[@name='mannerVerified']";
	String checkSavedLiabVerification = "//div[text()='Saving...']";

	
	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = ".//span[text()='Underwriting']";
			String metaDataKey = "Liability Verificationpage";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			xPath = ".//span[text()='Liability Verification']";
			waitForElementWithLocator(xPath, metaDataKey);
			addLiabilityDetails(xPath, metaDataKey);
		}
	}
	
	public void addLiabilityDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(LOSLiabilityVerificationInfo, metaDataKey);
		click(LOSLiabilityVerificationInfo, metaDataKey);
		waitForElementWithLocator(xPath, metaDataKey);
		countAndClick(ClickAllLiabiltyCopyAmounts, metaDataKey);
		Thread.sleep(500);
		doubleClick(LOSLiabityAmountID, metaDataKey);
		waitForElementWithLocator(verificationDocumentDate, metaDataKey);
		String text = todayDate();
		enterText(verificationDocumentDate, text, metaDataKey);
		String mverfied = "Liability Statement";
		click(mannerverified, metaDataKey);
		enterText(mannerverified, mverfied, metaDataKey);
		click(verificationDocumentDate, metaDataKey);
		Thread.sleep(200);
		click(LOSSaveLiabilityusedinfo, metaDataKey);
		click(LOSsaveLiabiltyVerification, metaDataKey);
		checkElementLoadMask(checkSavedLiabVerification, "Saving...", metaDataKey);
	}
}
