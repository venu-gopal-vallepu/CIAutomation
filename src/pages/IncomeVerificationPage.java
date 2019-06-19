package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class IncomeVerificationPage extends  UIElementMethods {
	String incomeVerificationPage = "//span[text()='Income Verification']";
	String CheckLosIncomeVerificationScreen	=	"//span[text()='New']";
	String ClickAllIncomeCopyAmounts	=	"//img[@data-qtip='Copy amounts to verified']";
	String mannerVerfied = "//table[contains(@class,'gridview')]//tr/td[6]/&///input[contains(@class,' x-form-focus x-field-form-focus')]/&///span[contains(text(),'Total')]";
	String LOSIncomeVerificationDates	=	"//tbody/tr/td[contains(@class,'headerId-datecolumncustom')]/&///input[contains(@class,' x-form-focus x-field-form-focus')]/&///span[contains(text(),'Total')]";
	String LOSsaveIncomeAmountVerified	=	"//span[text()='Save']";
	String checkSavedEmpVerification = "//div[text()='Saving...']";

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = ".//span[text()='Underwriting']";
			String metaDataKey = "Income verfication page";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addIncomeDetails(incomeVerificationPage, metaDataKey);

		}
	}
	
	public void addIncomeDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(incomeVerificationPage, metaDataKey);
		click(incomeVerificationPage, metaDataKey);
		waitForElementWithLocator(CheckLosIncomeVerificationScreen, metaDataKey);
		countAndClick(ClickAllIncomeCopyAmounts, metaDataKey);

		String[] mVeerified = mannerVerfied.split("/&/");
		String text = "PAYSTUB";
		clickOnMaskAndEnterValue(mVeerified[0], mVeerified[1], mVeerified[2], text, metaDataKey);

		String[] maskLoctaors = LOSIncomeVerificationDates.split("/&/");
		String text1 = todayDate();
		clickOnMaskAndEnterValue(maskLoctaors[0], maskLoctaors[1], maskLoctaors[2], text1, metaDataKey);
		click(LOSsaveIncomeAmountVerified, metaDataKey);

		checkElementLoadMask(checkSavedEmpVerification, "Saving...", metaDataKey);
	}
}
