package pages;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class FundingInformationPage extends UIElementMethods {

	String LOSFundingInformation	=	"//span[text()='Funding Information']";
	String LOSCheckFundingInfoScreen	=	"//span[text()='New Funding Request']";
	String LOSFundingInfoNewRequest	=	"//span[text()='New Funding Request']";
	String LOSFundingBankDetails	=	"//a[@data-qtip='Bank Detail']";
	String LOScheckFundingBankDetails	=	"//span[text()='Funding Bank Detail']";
	String LOSFundingBankDetailsBankName	=	"(//input[@name='bankName'])[2]";
	String LOSFundingBankDetailsAdress	=	"//input[@name='addressLineOne']";
	String LOSFundingBankDetailsZip	=	"//input[@name='postalCodeId']";
	String LOSCityname = "//input[@name='city']";
	String LOSFundingBankDetailsstate	=	"//li[text()='Stephentown']/&/1";
	String LOSFundingBankDetailsABAAccountNumber	=	"//input[@name='abaNumber']";
	String LOSFundingBankDetailsAccountNUmber	=	"//input[@name='accountNumber']";
	String LOSFundingBankDetailsAccountName	=	"//input[@name='accountName']";
	String LOSFundingBankDetailsBeneficiaryAccountName	=	"//input[@name='accountBeneficiary']";
	String LOSFundingBankDetailsInstruction	=	"//textarea[@name='specialInstructions']";
	String editIcon = "//img[contains(@class,'lp-note')]";	
	String LOSAcceptFundingBankDetails	=	"//a[@data-selenium-id='saveButton']";
	String LOSSaveFundingBankDetails	=	"//span[text()='Save']";
	String requestStatusType = "//input[@name='fundingStatusTypeId']/../following-sibling::td/div";

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100001096");
			Thread.sleep(1500);
			String xPath = "//span[text()='Closing']";
			String metaDataKey = "Funding Information";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			waitForElementWithLocator(LOSFundingInformation, metaDataKey);
			addFundingDetails(LOSFundingInformation, metaDataKey);

		}
	}

	public void addFundingDetails(String xPath , String metaDataKey) throws InterruptedException{
		click(xPath, metaDataKey);
		waitForElementWithLocator(LOSCheckFundingInfoScreen, metaDataKey);
		click(LOSCheckFundingInfoScreen, metaDataKey);

		click(editIcon, metaDataKey);
		locator = "//input[@name='fundingStatusTypeId']/../following-sibling::td/div";
		elementFound(locator);

		if (elemFound == true) {
			click(locator, metaDataKey);
			List<WebElement> elems = driver.findElements(By.xpath(
					"//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li"));
			click(requestStatusType, metaDataKey);
			for (int i = 0; i < elems.size(); i++) {
				if (i>0) {
					click(editIcon, metaDataKey);
				}
				click(requestStatusType, metaDataKey);
				elems = driver.findElements(By.xpath(
						"//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li"));
				locator = "//input[@name='fundingStatusTypeId']";
				getAttributeValue(locator, metaDataKey);
				if (values.equals("Funds Sent")) {
					i = 1;
					elems.get(i).click();
					break;
				}else{
					elems.get(i).click();
				}
				
				
				click(LOSFundingBankDetails, metaDataKey);
				waitForElementWithLocator(LOScheckFundingBankDetails, metaDataKey);
				enterText(LOSFundingBankDetailsBankName, "Bluesage", metaDataKey);
				xPath = "//input[@name='addressLineOne']/&///input[@name='postalCodeId']/&///input[@name='city']";
				enterAdressAndZip(xPath, metaDataKey);
				enterText(LOSFundingBankDetailsABAAccountNumber, "14587", metaDataKey);
				enterText(LOSFundingBankDetailsAccountNUmber, "447456148", metaDataKey);
				enterText(LOSFundingBankDetailsAccountName, "SavingsAccount", metaDataKey);
				enterText(LOSFundingBankDetailsBeneficiaryAccountName, "Carl", metaDataKey);
				enterText(LOSFundingBankDetailsInstruction, "Instructions", metaDataKey);
				click(LOSAcceptFundingBankDetails, metaDataKey);
				Thread.sleep(300);
				click(LOSSaveFundingBankDetails, metaDataKey);
				checkElementLoadMask(checkMaskLoad, "Loading...", metaDataKey);
				Thread.sleep(500);

			}
		}



	}


}
