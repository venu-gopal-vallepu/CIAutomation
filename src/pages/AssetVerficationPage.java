package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class AssetVerficationPage extends UIElementMethods {
	String AssetVerificationTreeNode = "//span[text()='Asset Verification']";
	String ClickAllAssetsCopyAmounts	=	"//img[@data-qtip='Copy value to verified and used']";
	String mannerVerfied = "//table[contains(@class,'gridview')]//tr/td[5]/&///input[contains(@class,' x-form-focus x-field-form-focus')]/&///span[contains(text(),'Total')]";
	String LOSAssetsVerificationDates	=	"//tr[contains(@id,'FIN_ACCT')]/td[contains(@class,'headerId-datecolumncustom')]/div/&///input[contains(@class,' x-form-focus x-field-form-focus')]/&///span[contains(text(),'Total')]";
	String LOSsaveAssetsAmountVerified	=	"//span[text()='Save']";
	String checkSavedAssetVerification = "//div[text()='Saving...']";


	@Test
	public void enterAssetVerification() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("5000000003");
			Thread.sleep(1500);
			String xPath = ".//span[text()='Underwriting']";
			String metaDataKey = "AssetVerificationPage";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			xPath = ".//span[text()='Asset Verification']";
			waitForElementWithLocator(xPath, metaDataKey);
			addAssetDetails(xPath, metaDataKey);
		}
	}

	public void addAssetDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(AssetVerificationTreeNode, metaDataKey + " : AssetVerificationTreeNode");
		click(AssetVerificationTreeNode, metaDataKey + "AssetVerificationTreeNode");
		waitForElementWithLocator(ClickAllAssetsCopyAmounts, metaDataKey + "ClickAllAssetsCopyAmounts");
		countAndClick(ClickAllAssetsCopyAmounts, metaDataKey);
		String[] mVeerified = mannerVerfied.split("/&/");
		String text = "Statement";
		clickOnMaskAndEnterValue(mVeerified[0], mVeerified[1], mVeerified[2], text, metaDataKey);
		String[] maskLoctaors = LOSAssetsVerificationDates.split("/&/");
		String text1 = todayDate();
		clickOnMaskAndEnterValue(maskLoctaors[0], maskLoctaors[1], maskLoctaors[2], text1, metaDataKey);
		click(LOSsaveAssetsAmountVerified, metaDataKey);
		checkElementLoadMask(checkSavedAssetVerification, "Saving...", metaDataKey);

	}

}
