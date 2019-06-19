package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class BorrowersPage extends UIElementMethods {
	String FirstBorrowerFirstName	=	"//input[@name='firstName']";
	String FirstBorrowerMiddleName	=	"//input[@name='middleName']";
	String FirstBorrowerLastName	=	"//input[@name='lastName']";
	String taxID					= 	"//input[@name='taxIdNumber']";
	String primaryBorrower 			=	"//input[@name='borrowerRoleId']";
	String checkSavedBorrowser		= 	"//div[text()='Saving...']";
    
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("enterred");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
//			objLogin.searc;
			String xPath = "//input[@name='firstName']";
			String count = "Four";
			String metaDataKey = "Brrowers page";
			
			locator = "//div[text()='Borrowers']";
			driver.findElement(By.xpath(locator)).click();
			
			locator = "//label[text()= '"+count+"']";
			waitForElementWithLocator(locator, metaDataKey);
			driver.findElement(By.xpath(locator)).click();
			
			String val = "VA";
			addBorrowerDetails(xPath, metaDataKey, val);
		}
	}

	String[] completeAdress = {"99508-1715 Birchwood St","99515-320 W 121st Ave", "99712-735 Aspen Heights Dr", "99508-1921 Norene St", "35811-240 Grasslands Rd",
			"35094-8608 Cedar Springs Cir", "36330-74 County Road 270","35206-7205 4th Ave S", "72417-121 Oak Meadow Cir","71922-2504 Hickory Grove Rd", "85234-2472 E Gemini St",
			"85377-12903 W Castlebar Dr", "93535-45704 Palm Ln","94089-1063 Morse Ave", "80113-4125 S Grant St","80024-10190 Wadsworth Blvd", "06370-4 Texas Dr",
			"06825-12 May St", "20032-800 Southern Ave Se",	"20020-2110 T St Se", "19963-377 Bay Ave","19951-30738 Molly B Rd", "32086-362 Rosa Ct",
			"32738-2245 Dumas Dr", "30549-85 Williamson St","30741-718 Cross St", "66441-2619 Paige Ln","67205-11008 W Greenspoint St", "82009-5535 Snowberry Dr",
			"82602-6500 W Riverside Ter", "26554-592 Pricketts Fort Rd","26451-533 Charles St", "53711-5846 Tree Line Dr","54458-586 Church Ave", "99403-2360 Valleyview Dr",
			"98552-950 State Route 109", "05663-23 Cole Ave","23410-1613 Flammarion Ct", "22630-1465 Canterbury Rd","84004-816 E Village Way", "84120-4385 W Sunnyvale Dr",
			"77710-4446 Spring Meadows Dr", "77338-6510 Twain Mark Ln","37087-226 Woolard St", "38556-349 Colditz Rd","57101-6401 W Bonnie Ct", "57325-200 Terrace Rd",
			"29461-238 White St", "29102-509 Skyview Dr","02863-81 Tremont St", "02822-37 West Shore Dr","42302-2340 Bulfinch Ave", "42408-17615 Cord Hamby Rd",
			"71207-957 Angus Rd", "71456-168 Riverside Ln","02780-16 Herbert St", "01247-30 W Shaft Rd Ter","21771-5480 Ross Ct", "20901-506 Stirling Rd",
			"04216-100 N Main St", "04401-124 W Broadway","48242-17527 Garfield", "48168-40593 N Northville Trl","55371-3774 Turner Dr Sw", "55115-1344 Echo Lake Ct",
			"63365-24 Hollywood Dr", "63123-4351 Mohegan Dr","39402-20 Katherine Cir", "38868-154 Shannon Ave","59759-24 Royal Elk Rd", "59803-2300 Larch Camp Rd",
			"27302-4603 Yarboroughs Mill Rd", "27406-6904 Woodhue Dr","58561-240 Main Ave", "58504-27 Custer Dr", "68847-255 M Ave","68803-663 Faidley Ct", "03077-10 Floral Ave",
			"03104-33 Whig Dr", "72223-4615 Old Oak Dr","35212-1336 Mccoy St", "92109-852 Thomas Ave","20019-3947 Burns Pl Se", "96732-275 Mohalu St",
			"60463-12532 S Melvina Ave", "47283-404 W Mulberry St","01056-131 Williams St", "48224-3968 Chatsworth St","55408-3455 Blaisdell Ave", "07042-52 Fairfield St",
			"03768-57 Goose Pond Rd", "88081-443 Ralph St","89432-550 Stanford Way", "58502-1308 Ridgeview Ln","12701-107 Cold Spring Rd", "97016-77285 Woodson Rd",
			"02886-296 Hunt Ave", "84058-1575 Sandhill Rd","05836-18 Foster Ter", "98368-870 Martin Rd","35811-240 Grasslands Rd", "85344-37241 S Buckskin Cir",
			"94536-990 Knollwood Dr", "07670-27 Peter Lynas Ct","27803-109 Robert Ryan Ct", "77069-5207 Court Of York","77401-4804 Palm St", "77328-357 County Road 3997",
			"77023-1721 Idylwood Dr","77075-10419 Clearwood Crossing Blvd","79915-7619 Monterrey Dr", "07042-52 Fairfield St","03768-57 Goose Pond Rd", "88011-443 Ralph St",
			"89432-550 Stanford Way", "58502-1308 Ridgeview Ln","12701-107 Cold Spring Rd", "97011-77285 Woodson Rd","02886-296 Hunt Ave", "84058-1575 Sandhill Rd",
			"05830-18 Foster Ter", "98368-870 Martin Rd" };

	public void addBorrowerDetails(String xPath , String metaDataKey , String EnterVADetails) throws InterruptedException{
		
		String locator = "//div[contains(@id,'loanapp-borrowerpanel')][contains(@class,'x-panel-default vertical-panel')][not(contains(@style,'display: none;'))]";
		listWebElements(locator, metaDataKey);
		ArrayList<WebElement> borrowerForms = (ArrayList<WebElement>) ((ArrayList<WebElement>)listElems).clone();

		String[] oneBorrowerDetails = {"Alice-Firstimer-991919991","Suzi-Builder-999606666","Penny-Public-999603000",
				"Ross-Blemished-000889999","Elizabeth-Spender-000667777","Ken-Customer-500507000",
				"Homer-Loanseeker-999601111","Sonny-Graves-001010021","Loco-Motion-999478801",
				"Pitt-Rock-666006666","Ron-Tintin-999725641","Lucky-Knownscore-999422345",
		"Ima-One-000010001"};

		String[] twoBorrowerDetails = {"Andy-America-999603333/Amy-America-500602222", "Patrick-Purchaser-999121234/Lorraine-Purchaser-999565678",
				"Dad-Firstimer-999008881/Mom-Firstimer-999008882","Wannna-House-000112222/Needa-House-999445555",
				"Mort-Gage-000000002/Ann-Gage-000000003","John-Homeowner-999405000/Mary-Homeowner-500222000"};

		String[] threeBorrowerDetails = {"Andy-America-999603333/Amy-America-500602222/Alice-Firstimer-991919991", 
				"Patrick-Purchaser-999121234/Lorraine-Purchaser-999565678/Alice-Firstimer-991919991",
				"Dad-Firstimer-999008881/Mom-Firstimer-999008882/Alice-Firstimer-991919991",
				"Wannna-House-000112222/Needa-House-999445555/Alice-Firstimer-991919991",
				"Mort-Gage-000000002/Ann-Gage-000000003/Alice-Firstimer-991919991",
		"John-Homeowner-999405000/Mary-Homeowner-500222000/Alice-Firstimer-991919991"};

		String[] fourBorrowerDetails = {"Andy-America-999603333/Amy-America-500602222/Alice-Firstimer-991919991/Suzi-Builder-999606666", 
				"Patrick-Purchaser-999121234/Lorraine-Purchaser-999565678/Alice-Firstimer-991919991/Suzi-Builder-999606666",
				"Dad-Firstimer-999008881/Mom-Firstimer-999008882/Alice-Firstimer-991919991/Suzi-Builder-999606666",
				"Wannna-House-000112222/Needa-House-999445555/Alice-Firstimer-991919991/Suzi-Builder-999606666",
				"Mort-Gage-000000002/Ann-Gage-000000003/Alice-Firstimer-991919991/Suzi-Builder-999606666",
		"John-Homeowner-999405000/Mary-Homeowner-500222000/Alice-Firstimer-991919991/Suzi-Builder-999606666"};

		String[] DOB = {"1/08/1971","2/02/1972","3/14/1973","4/15/1974","6/22/1975","7/23/1976","8/25/1977","9/30/1978",
				        "10/31/1979","11/01/1971","12/12/1972","1/13/1973","1/04/1970","2/11/1971","3/23/1971","4/14/1972",
				        "6/21/1971","7/23/1977","8/25/1978","9/30/1979","10/31/1980","11/01/1981","12/12/1982","1/13/1983"};
		
		String[] phoneNumber = {"(852) 117-8965","(862) 127-9962","(872) 217-9962","(882) 347-1478","(892) 447-7894",
								"(201) 147-8965","(202) 157-9962","(203) 247-9962","(212) 347-1478","(732) 447-7894",
								"(510) 177-8965","(511) 167-9962","(562) 247-9962","(662) 347-1478","(762) 447-7894"};
		String[] cotactInfoType = {"Mobile Phone / Cell","Work Phone","Home Phone","Home Fax","Work Fax"};


		int noOfBorrowers =  borrowerForms.size();
		String[]  borrowerArray = null;
		String[] compAddress = null;

		if (noOfBorrowers == 1) {
			getRandomValueFromArray(oneBorrowerDetails, oneBorrowerDetails.length - 1);
			String[] independentBorrowers = randomValue.split("/");
			borrowerArray = independentBorrowers.clone();
			/*borrowerArray = independentBorrowers.clone();
			borrowerArray = oneBorroweDetails.clone();*/
		} else if (noOfBorrowers == 2) {
			getRandomValueFromArray(twoBorrowerDetails, twoBorrowerDetails.length - 1);
			String[] independentBorrowers = randomValue.split("/");
			borrowerArray = independentBorrowers.clone();
		} else if (noOfBorrowers == 3) {
			getRandomValueFromArray(threeBorrowerDetails, threeBorrowerDetails.length - 1);
			String[] independentBorrowers = randomValue.split("/");
			borrowerArray = independentBorrowers.clone();
		} else {
			getRandomValueFromArray(fourBorrowerDetails, fourBorrowerDetails.length - 1);
			String[] independentBorrowers = randomValue.split("/");
			borrowerArray = independentBorrowers.clone();
			//	Andy-America-999603333(0)/Amy-America-500602222(1)
		}

		getRandomValueFromArray(completeAdress, completeAdress.length - 1);
		String[] independentBorrowers = randomValue.split("-");
		compAddress = independentBorrowers.clone();

		for (int i = 0; i < borrowerForms.size(); i++) {
			int val= i+1;
			String[] mortgageDetails = borrowerArray[i].split("-");

			locator = "(//input[@name='firstName'])"+"[" + val +"]";
			enterText(locator, mortgageDetails[0], metaDataKey);

			locator = "(//input[@name='lastName'])"+"[" + val +"]";
			enterText(locator, mortgageDetails[1], metaDataKey);

			locator = "(//input[@name='taxIdNumber'])"+"[" + val +"]";
			enterText(locator, mortgageDetails[2], metaDataKey);
			Thread.sleep(500);

			locator = "(//span[text()='Create New Contact'])"+"[" + val +"]";
			click(locator, metaDataKey);
			Thread.sleep(500);

			getRandomValueFromArray(DOB, DOB.length-1);
			locator = "(//input[@name='birthDate'])"+"[" + val +"]";
			enterText(locator, randomValue, metaDataKey);

			locator = "(//input[@name='maritalStatusId'])"+"[" + val +"]";
			enterText(locator, "Married", metaDataKey);
			click(primaryBorrower, metaDataKey);

			locator = "(//input[@name='citizenshipTypeId'])"+"[" + val +"]";
			enterText(locator, "US Citizen", metaDataKey);
			click(primaryBorrower, metaDataKey);

			locator = "(//input[@name='numberOfDependents'])"+"[" + val +"]";
			enterText(locator, "2", metaDataKey);
			Thread.sleep(200);

			locator = "(//input[@name='dependentAges'])"+"[" + val +"]";
			click(locator, metaDataKey);
			enterText(locator, "8,9", metaDataKey);

			getRandomValueFromArray(phoneNumber, phoneNumber.length-1);
			locator = "(//input[@name='telephoneNumber'])"+"[" + val +"]";
			enterText(locator, randomValue, metaDataKey);

			getRandomValueFromArray(cotactInfoType, cotactInfoType.length-1);
			locator = "(//input[@name='telephoneTypeId'])"+"[" + val +"]";
			enterText(locator, randomValue, metaDataKey);
			click(primaryBorrower, metaDataKey);

			locator = "(//input[@name='emailAddress'])"+"[" + val +"]";
			enterText(locator, "sudershan@bluesageusa.com", metaDataKey);

			locator = "(//input[@name='addressLineOne'])"+"[" + val +"]";
			enterText(locator, compAddress[1], metaDataKey);

			locator = "(//input[@name='postalCodeId'])"+"[" + val +"]";
			enterText(locator, compAddress[0], metaDataKey);
			
			locator = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]/div[text()='Loading...']";
			elementFound(locator);
			if (elemFound == true) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
			}

			locator = "(//input[@name='city'])"+"[" + val +"]";
			element = driver.findElement(By.xpath(locator));
			boolean verifyCity = element.getAttribute("value").length()>0;
			System.out.println("value.................." + verifyCity);
			if (verifyCity == false) {
				System.out.println("xxxxxx.................." + verifyCity);
				List<WebElement> elems = driver.findElements(By.xpath(
						"//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li"));
				System.out.println(elems.get(0).getText());
				elems.get(0).click();
				elems.clear();
			}

			locator = "(//input[@name='addressStatusId'])"+"[" + val +"]";;
			enterText(locator, "Own", metaDataKey);

			locator = "(//input[@name='numYearsApplicable'])"+"[" + val +"]";
			enterText(locator, "6", metaDataKey);

			locator = "(//input[@name='numMonthsApplicable'])"+"[" + val +"]";
			enterText(locator, "2", metaDataKey);
			click(primaryBorrower, metaDataKey);

			locator = "(//span[text()='Add Additional Residency...'])"+"[" + val +"]";
			click(locator, metaDataKey);

			String checkAddressWindow = "//div[contains(@class,'x-resizable x-window-resizable')]//label[text()='Type of Address']";
			waitForElementWithLocator(checkAddressWindow, metaDataKey);

			locator = "//div[contains(@class,'x-resizable x-window-resizable')]//input[@name='addressTypeId']";
			enterText(locator, "Former Address", metaDataKey);
			click(checkAddressWindow, metaDataKey);

			getRandomValueFromArray(completeAdress, completeAdress.length-1);
			String[] additionalReciidence =  randomValue.split("-");

			locator = "//div[contains(@class,'x-resizable x-window-resizable')]//input[@name='addressLineOne']";
			enterText(locator, additionalReciidence[1], metaDataKey);

			locator = "//div[contains(@class,'x-resizable x-window-resizable')]//input[@name='postalCodeId']";
			enterText(locator, additionalReciidence[0], metaDataKey);
			Thread.sleep(500);

			locator = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]/div[text()='Loading...']";
			elementFound(locator);
			if (elemFound == true) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
			}
		
			locator = "//div[contains(@class,'x-resizable x-window-resizable')]//input[@name='city']";
			element = driver.findElement(By.xpath(locator));
			boolean verifyCity1  = true;
			verifyCity1 = element.getAttribute("value").length()>0;
			System.out.println("value.................." + verifyCity);
			if (verifyCity1 == false) {
				System.out.println("xxxxxx.................." + verifyCity);
				List<WebElement> elems = driver.findElements(By.xpath(
						"//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li"));
				System.out.println(elems.get(0).getText());
				elems.get(0).click();
				elems.clear();
			}

			locator = "//a[@data-selenium-id='okButton']";
			click(locator, metaDataKey);

			Thread.sleep(300);
		}

		if (EnterVADetails.equals("VA")) {
			locator = "//a[@data-selenium-id='borrVAInfo']";
			click(locator, metaDataKey);
			String checkVaWindow = "//label[text()='Military Type']";
			waitForElementWithLocator(checkVaWindow, metaDataKey);

			locator = "//input[@name='militaryDutyTypeId']";
			enterText(locator, "Reserves/National Guard", metaDataKey);
			click(checkVaWindow, metaDataKey);

			locator = "//input[@name='vaEntitlementTypeId']";
			enterText(locator, "Vietnam War", metaDataKey);
			click(checkVaWindow, metaDataKey);

			locator = "//input[@name='militaryServiceBranchId']";
			enterText(locator, "Army", metaDataKey);
			click(checkVaWindow, metaDataKey);

			locator = "//input[@name='militaryServiceFromDate']";
			enterText(locator, "4/05/1990", metaDataKey);

			locator = "//input[@name='militaryServiceToDate']";
			enterText(locator, "4/05/1998", metaDataKey);

			locator ="//label[text()='Exempt From Funding Fee']";
			click(locator, metaDataKey);

			locator = "//label[text()='Never']";
			click(locator, metaDataKey);

			locator = "//span[text()='Ok']";
			click(locator, metaDataKey);
			Thread.sleep(200);

		}

		locator = "//a[@data-selenium-id='btnLoanAppSave']";
		click(locator, metaDataKey);
		checkElementLoadMask(checkSavedBorrowser, "Saving...", metaDataKey);
	}
}
