package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.interactions.ClickAction;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LoanSourcePage extends UIElementMethods {
	String  LoanSource	=	"//div[text()='Loan Source']";
	String checkLoanSource = "//label[text()='Lender']";
	String  Lender	=	"//input[@name='lenderId']";
	String channel = "//input[@name='channelTypeId']";;
	String  SalesRegion	=	"//input[@name='salesRegionId']";
	String  salesBranch	=	"//input[@name='salesBranchId']";
	String  Fullfillmentcentre	=	"//input[@name='fulfillmentCenterId']";
	String pricingRegion	=	"//input[@name='marketTypeId']";
	String  LeadType	=	"//input[@name='leadTypeId']";
	String  LeadSource	=	"//input[@name='leadSourceId']";
	String saveLoansource = "//a[@data-selenium-id='btnLoanAppSave']";
	String  OtherLoanNumber	=	"//input[@n6ame='otherLoanNumber']";
	String  LoanTerms	=	"//span[text()='NEXT']";

	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("CRM-1200003006");
			Thread.sleep(1500);
			String metaDataKey = "Employment page";
			String xPath = "(//div[text()='Additional Employment'])[1]/&/1";
			addLoanSourceDetails(xPath, metaDataKey);
		}
	}
	
	
	public  void addLoanSourceDetails(String xPath ,String metaDataKey) throws InterruptedException{

		click(LoanSource, metaDataKey);
		waitForElementWithLocator(checkLoanSource, metaDataKey);

//		enterText(Lender, "Lender ABC", metaDataKey);
//		click(checkLoanSource, metaDataKey);

		enterText(channel, "Retail", metaDataKey);
		click(checkLoanSource, metaDataKey);

		enterText(SalesRegion, "Northeast", metaDataKey);
		click(checkLoanSource, metaDataKey);

		enterText(salesBranch, "Paramus", metaDataKey);
		click(checkLoanSource, metaDataKey);

		enterText(Fullfillmentcentre, "Retail Ops Northeast", metaDataKey);
		click(checkLoanSource, metaDataKey);

		/*enterText(pricingRegion, "Tier 1 Market", metaDataKey);
		click(checkLoanSource, metaDataKey);*/

		enterText(LeadType, "Affiliate", metaDataKey);
		click(checkLoanSource, metaDataKey);

		enterText(LeadSource, "AAA Realty", metaDataKey);
		click(checkLoanSource, metaDataKey);

		click(saveLoansource, metaDataKey);

		Thread.sleep(2000);

	}


}
