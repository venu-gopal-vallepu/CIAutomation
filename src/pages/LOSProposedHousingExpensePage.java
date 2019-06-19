package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LOSProposedHousingExpensePage extends UIElementMethods {
    String proposedHousingExpenseTreeNode = ".//span[text()='Proposed Housing Exp.']";
	String ProposedHousingfirstmortgage	=	"//input[@name='firstMtgPmtAmt']";
	String ProposedHousingfinancing	= "//input[@name='otherMtgPmtAmt']";
	String ProposedHousingHazard	="//input[@name='hazardInsuranceAmt']";
	String ProposedHousingRealEstate = "//input[@name='propertyTaxesAmt']";
	String ProposedHousingMortgage	="//input[@name='pmiPmtAmt']";
	String ProposedHousingHomeowner="//input[@name='associationFeesAmt']";
	String ProposedHousingFloodInsurance= ".//input[@name='floodInsuranceAmt']";
	String ProposedHousingAddExpensesBtn = "//span[text()='Add Other Expenses']";
	String Addbtn = "//span[text()='Add']";
	String ExpenseTypeId= "//td[contains(@class,'x-grid-cell-headerId-gridcolumn')]";
	String ExpenseType = "//input[@name='propertyExpenseTypeId']";
	String AmountID = "//td[contains(@class,'x-grid-cell-headerId-currencycolumn')]";
	String Amount = "//input[@name='propertyExpenseAmt']";
	String CheckCommentgrid = "//span[text()='Amount']";
	String saveExpenses = "(.//span[text()='Save'])[2]";
	String SaveProposedHousng = "//span[text()='Save']";

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String metaDataKey = "Loan Pricing";
			addProposedHousingDetails(proposedHousingExpenseTreeNode, metaDataKey);
		}
	}
	
	public void addProposedHousingDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(proposedHousingExpenseTreeNode, metaDataKey);
		click(proposedHousingExpenseTreeNode, metaDataKey);
        click(ProposedHousingAddExpensesBtn, metaDataKey);
//        click(Addbtn, metaDataKey);
        Thread.sleep(500);
        click(ExpenseTypeId, metaDataKey);
        enterText(ExpenseType, "50", metaDataKey);
        click(AmountID, metaDataKey);
        enterText(Amount, "50", metaDataKey);
        click(CheckCommentgrid, metaDataKey);
        click(saveExpenses, metaDataKey);
        click(SaveProposedHousng, metaDataKey);
        
	}
}
