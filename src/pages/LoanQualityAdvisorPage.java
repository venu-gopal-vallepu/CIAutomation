package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LoanQualityAdvisorPage extends UIElementMethods {

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{/*
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.loginLendingHive();
			Thread.sleep(1500);
			String xPath = "//span[text()='Closing']";
			String metaDataKey = "Funding Information";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			xPath = "//span[text()='Escrow Information']";
//			addEscrowInfoPage(xPath, metaDataKey);

		}
	*/}
	
	
	public void addLoanQualityDetails(){
		
	}
	
}
