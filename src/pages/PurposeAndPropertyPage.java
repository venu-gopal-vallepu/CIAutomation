package pages;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class PurposeAndPropertyPage extends UIElementMethods {

	String PurposeAndProperty				=	"//div[text()='Purpose & Property']";
	String CheckPurposeAndProperty			=	"//label[text()='Property Located']";
	String PurposeAndPropertyLoanpurpose	=	"//input[@name='purposeTypeId']/../following-sibling::td/div/&/1";
	String BorrowerAddress					=	"//input[@name='addressLineOne']/&///input[@name='postalCodeId']/&///input[@name='city']";
	String PurposeAndPropertyAdress			=	"//input[@name='addressLineOne']";
	String PurposeAndPropertyZip			=	"//input[@name='postalCodeId']";
	String PurposeAndPropertyState			=	"//li[text()='Stephentown Center']/&/1";
	String PurposeAndPropertyOccupancy		=	"//input[@name='occupancyTypeId']";
	String SubPropertyType					=	"//input[@name='buildingTypeId']/../following-sibling::td/div";
	String PurposeAndPropertyNoOfUnits		=	"//input[@name='familyUnits']";
	String BuildingStatus					=	"//input[@name='buildingStatusTypeId']";
	String PurposeAndPropertyHazardTax		=	"//input[@name='hazIns']";
	String PurposeAndPropertyPropertyTax	=	"//input[@name='propTaxes']";
	String PurposeAndPropertyFloodTax		=	"//input[@name='floodIns']";
	String PurposeAndPropertyHomeOwner		=	"//input[@name='hoaFees']";
	String PurposeAndPropertyOtherExpences	=	"//input[@name='otherExp']";
	String SavePurpose						=	"//a[@data-selenium-id='btnLoanAppSave']";
	String checkSavedPurpose				= "//div[text()='Saving...']";


	@Test
	public void enterEmploymentPage() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("LEAD-1200002008");
			Thread.sleep(1500);                                                                                                                                                                                
			String metaDataKey = "Purpose page";
			addPurposeAndPropertyDetails(PurposeAndProperty,"Purchase" ,metaDataKey);
		}
	}

	public void addPurposeAndPropertyDetails(String xPath , String text  ,String metaDataKey) throws InterruptedException{
		click(PurposeAndProperty, metaDataKey);
		waitForElementWithLocator(CheckPurposeAndProperty, metaDataKey);

		if (text.equals("Purchase")) {
			enterAdressAndZip(BorrowerAddress, metaDataKey);
			enterText(PurposeAndPropertyOccupancy, "Primary Residence", metaDataKey);
			click(PurposeAndPropertyAdress, metaDataKey);
		}
		click(SubPropertyType, metaDataKey);
		locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
		mylist.clear();
		listWebElements(locator, metaDataKey);
		if (listElems.size()>0) {
			for (int i = 0; i < listElems.size(); i++) {
				element = listElems.get(i);
				String dropdownValue = element.getText();
				mylist.add(dropdownValue);
			}

			Random  rand = new Random();
			int index = rand.nextInt(mylist.size());
			String item = mylist.get(index);
			System.out.println(item);
			element = driver.findElement(By.xpath("//li[text()='"+ item +"']"));
			element.click();
			Thread.sleep(500);

			String[] familyUnits = {"1"};
			rand = new Random();
			int index1 = rand.nextInt(familyUnits.length);
			String item1 = familyUnits[index1];

			elementFound(PurposeAndPropertyNoOfUnits);
			if (elemFound == true) {
				enterText(PurposeAndPropertyNoOfUnits, item1, metaDataKey);
				int instSize = familyUnits.length;
			}


			enterText(BuildingStatus, "Existing", metaDataKey);
			click(PurposeAndPropertyAdress, metaDataKey);

			enterText(PurposeAndPropertyHazardTax, "50", metaDataKey);
			enterText(PurposeAndPropertyPropertyTax, "75", metaDataKey);
			enterText(PurposeAndPropertyFloodTax, "100", metaDataKey);
			enterText(PurposeAndPropertyHomeOwner, "85", metaDataKey);
			enterText(PurposeAndPropertyOtherExpences, "100", metaDataKey);

			clickOnElementUsingJavascript(SavePurpose, metaDataKey);
			checkElementLoadMask(checkSavedPurpose, "Saving...", metaDataKey);

			element = driver.findElement(By.xpath("//input[@name='stateId']"));
			purposeAndPropertyState = element.getAttribute("value");
			System.out.println("Purpose and property state is :  " + purposeAndPropertyState);

		}

	}
}
