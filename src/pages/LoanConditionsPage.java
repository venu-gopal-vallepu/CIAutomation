package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LoanConditionsPage extends UIElementMethods {
	String loanConditionsTreeNode = "//span[text()='Loan Conditions']";
	String checkBoxLocator = "//div[contains(@class,'x-panel-body x-grid-with-col-lines x-grid-with-row-lines ')]//table//tr/td[contains(@class,'headerId-checkcolumncustom')][1]";
	String saveLoanConditions = "//span[text()='Save']";
	String checkSaveLoanCinditions = "//div[text()='Saving...']";

	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String xPath = "//span[text()='Underwriting']";
			String metaDataKey = "Underwriting page";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addLoanConditions(loanConditionsTreeNode, metaDataKey);
		}
	}
	
   public void addLoanConditions(String xPath , String metaDataKey) throws InterruptedException{
	   waitForElementWithLocator(loanConditionsTreeNode, metaDataKey);
	   click(loanConditionsTreeNode, metaDataKey);
	   waitForElementWithLocator(checkBoxLocator, metaDataKey);
	   countAndClick(checkBoxLocator, metaDataKey);
	   click(saveLoanConditions, metaDataKey);
	   checkElementLoadMask(checkSaveLoanCinditions, "Saving...", metaDataKey);
	   
	   
   }
}
