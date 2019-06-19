package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class CompliancePage extends UIElementMethods {
	String LOSComplianceease	=	"//span[text()='ComplianceEase']";
	String LOSComplianceSendRequest	=	"//span[text()='Send Request']";
	String LOSComplianceSubmit	=	"//span[text()='OK']";
	String LOSComplianceCheckSubmit	=	"//div[text()='Submitting loan to ComplianceEase...']";
	String LOSAcceptComplianceError	=	"//span[text()='OK']";
    String DocPackageType  = "//table[@data-selenium-id='docPackage']//input[contains(@class,'x-form-field x-form-text x-trigger-noedit')]/../following-sibling::td/div";


	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("5000000003");
			Thread.sleep(1500);
			addComplianceDetails(LOSComplianceease, "Compliance Page");
		}
	}
	
	public void addComplianceDetails(String xPath,String metaDataKey) throws InterruptedException{
		click(LOSComplianceease, metaDataKey);
		waitForElementWithLocator(LOSComplianceSendRequest, metaDataKey);
		click(LOSComplianceSendRequest, metaDataKey);
		click(LOSComplianceSubmit, metaDataKey);
		checkElementLoadMask(LOSComplianceCheckSubmit, "Submitting loan to ComplianceEase...", metaDataKey);
		Thread.sleep(2000);
		locator = "//div[@data-selenium-id='compAnalysisSummaryGrid']//table//tr";
		elementFound(locator);
	}
}
