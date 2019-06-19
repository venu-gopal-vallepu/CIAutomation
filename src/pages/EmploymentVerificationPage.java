package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class EmploymentVerificationPage extends UIElementMethods {
    String LOSEmploymentVerificationTreeNode = "//span[text()='Employment Verification']";
	String LOSEmployerverifiedwithID	=	"(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[11]";
	String LOSEmployerverifyiedName	=	"//input[@name='employmentVerifiedByName']";
	String LOSEmployerTitleID	=	"(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[12]";
	String LOSEmployerTitleName	=	"//input[@name='employmentVerifiedByTitle']";
	String LOSEmployerPhoneID	=	"(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[13]";
	String LOSEmployerPhonenumber	=	"//input[@name='telephoneNumber']";
	String LOSMannerverifiedID	=	"(.//td[contains(@class,'x-grid-cell-headerId-gridcolumn')])[14]";
	String LOSMannerinperson	=	"//input[@name='verificationTypeId']";
	String LOSEmployerDocumentID	=	"(.//td[contains(@class,'x-grid-cell-headerId-datecolumncustom')])[8]";
	String LOSEmployerDocumentDate	=	".//input[@name='verificationDocumentDate']";
	String LOSSaveEmployementverificationInfo	=	".//span[text()='Save']";
	String checkSavedEmpVerification = "//div[text()='Saving...']";

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = ".//span[text()='Underwriting']";
			String metaDataKey = "Emp Verificationpage";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addEmploymentVerification(LOSEmploymentVerificationTreeNode, metaDataKey);
		}
	}
	
	public void addEmploymentVerification(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(LOSEmploymentVerificationTreeNode, metaDataKey);
		click(LOSEmploymentVerificationTreeNode, metaDataKey);
		Thread.sleep(200);
		click(LOSEmployerverifiedwithID, metaDataKey);
		enterText(LOSEmployerverifyiedName, "Schintak", metaDataKey);

		click(LOSEmployerTitleID, metaDataKey);
		enterText(LOSEmployerTitleName, "Schintak", metaDataKey);

		click(LOSMannerverifiedID, metaDataKey);
		enterText(LOSMannerinperson, "Phone", metaDataKey);

		click(LOSEmployerDocumentID, metaDataKey);
		String text = todayDate();
		enterText(LOSEmployerDocumentDate, text, metaDataKey);

		click(LOSSaveEmployementverificationInfo, metaDataKey);
		checkElementLoadMask(checkSavedEmpVerification, "Saving...", metaDataKey);

	}
}
