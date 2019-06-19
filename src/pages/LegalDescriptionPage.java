package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LegalDescriptionPage extends UIElementMethods{
    String legalDescription = ".//span[text()='Legal Description']";
	String SampleLaungauge =	"//input[@name='mannerTitleHeldIn']";
	String  Estatewillbeheld =	".//label[text()='Fee Simple']";
	String Nameswillbeheld =	".//textarea[@name='namesTitleHeldIn']";
	String LegalDescriptionMethod =	".//label[text()='Metes and Bounds']";
	String LotNumber    =	".//input[@name='lotNumber']";
	String Blocknumber	= ".//input[@name='blockNumber']";
	String  Censustract	= ".//input[@name='censusTractNumber']";
	String  TaxparcelID	= ".//input[@name='taxParcelNumber']";
	String PropertHeldRevocableTrust =	".//label[text()='No']";
	String SaveLegalDescription	= ".//span[text()='Save']";

	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String metaDataKey = "Legal Description Page";
			addLegalDescriptionDetails(legalDescription, metaDataKey);
		}
	}
	
	public void addLegalDescriptionDetails(String xPath , String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(legalDescription, metaDataKey);
		click(legalDescription, metaDataKey);
		waitForElementWithLocator(SampleLaungauge, metaDataKey);
		enterText(SampleLaungauge, "Joint Tenancy with Spouse", metaDataKey);
		click(Estatewillbeheld, metaDataKey);
		enterText(Nameswillbeheld, "Primary$CO-Borrowers", metaDataKey);
		click(LegalDescriptionMethod, metaDataKey);
		enterText(LotNumber, "AP64187", metaDataKey);
		enterText(Blocknumber, "WT8753", metaDataKey);
		enterText(Censustract, "1101.01", metaDataKey);
		enterText(TaxparcelID, "BL654362", metaDataKey);
		click(PropertHeldRevocableTrust, metaDataKey);
		click(SaveLegalDescription, metaDataKey);
		Thread.sleep(2000);


	}
}
