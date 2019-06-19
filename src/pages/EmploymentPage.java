package pages;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class EmploymentPage extends UIElementMethods{

	String Employmentpage = "//div[text()='Employment']";

	@Test
	public void enterEmploymentPage() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("LEAD-1000012");
			Thread.sleep(1500);
			String metaDataKey = "Employment page";
			String xPath = "(//div[text()='Additional Employment'])[1]/&/2";
			addEmployment(xPath, metaDataKey);
		}
	}

	public void addEmployment(String xPath , String metaDataKey ) throws InterruptedException{
		waitForElementWithLocator(Employmentpage, metaDataKey);
		click(Employmentpage, metaDataKey);
		locator = "//div[contains(@id,'loanapp-employmentpanel')][contains(@class,'x-panel-default vertical-panel')][not(contains(@style,'display: none;'))]";
		listWebElements(locator, metaDataKey);
		ArrayList<WebElement> employmentForms = (ArrayList<WebElement>) ((ArrayList<WebElement>)listElems).clone();
		int size = employmentForms.size();
		String[] arrowClickPath = xPath.split("/&/");
		String val = arrowClickPath[1];
		if (val.equals("add") || Integer.parseInt(val)<=size) {
			waitForElementWithLocator(arrowClickPath[0], metaDataKey);
			int arrayValue = 0;
			String[] currEployment;
			if (arrowClickPath[1].equals("add")) {
				click(arrowClickPath[0], metaDataKey);
				arrayValue = 5;

			} else {
				arrayValue = Integer.parseInt(arrowClickPath[1]);
				locator = "(//*[@data-selenium-id='currentlyEmployed']//*[text()='Yes'])"+ "["+ arrayValue +"]";
				click(locator, metaDataKey);
			}

			locator = "(//input[@name='employerName'])"+ "["+ arrayValue +"]";
			String[] employerName = {"Starbucks", "National Consulting" , "Unified manufacturing" , "It's a Grind cofee house","Cofee house","Manufacturing company"};
			enterText(locator, employerName[arrayValue-1], metaDataKey);
			locator = "(//input[@name='addressLineOne'])"+ "["+ arrayValue +"]";
			String[] adressLineone = {"215 street" , "325 street" , "545 street" , "147 street","148 street","872 street"};
			enterText(locator, adressLineone[arrayValue-1], metaDataKey);
			locator = "(//input[@name='postalCodeId'])"+ "["+ arrayValue +"]";
			String locator2 = "(//input[@name='city'])"+ "["+ arrayValue +"]";
			enterZipCode(locator, locator2 , "08220");
			locator = "(//input[@name='telephoneNumber'])"+ "["+ arrayValue +"]";
			String[] telephoneNumbers = {"(789) 457-8522","(589) 457-8722","(989) 458-6522","(689) 657-6522","(789) 358-8522","(889) 358-8722"};
			enterText(locator,  telephoneNumbers[arrayValue-1], metaDataKey);
			locator = "(//input[@name='telephoneExtension'])" + "["+ arrayValue +"]";
			enterText(locator, "512", metaDataKey);
			if (arrayValue <= 4) {
				locator = "(//*[@data-selenium-id='selfEmployed']//*[text()='No'])" + "["+ arrayValue +"]";
			}
			click(locator, metaDataKey);
			Thread.sleep(300);
			locator = "//table[@data-selenium-id='businessTypeId'][contains(@style,'display: none;')]" ; 
			elementFound(locator);
			if (elemFound == false) {
				locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
				listWebElements(locator, metaDataKey);
				if (listElems != null && listElems.size()>0 ) {
					listElems.get(0).click();
				}
			}

			if (arrowClickPath[1].equals("add")) {
				locator = "//span[text()='Tax Preparer']";
				click(locator, metaDataKey);

				locator = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]/div[text()='Loading...']";
				checkElementLoadMask(locator, "Loading...", metaDataKey);

				locator = "//input[@name='companyName']";
				enterText(locator, "Bluesage", metaDataKey);

				locator = "//input[@name='contactName']";
				enterText(locator, "sudershan", metaDataKey);
				//input[@name='addressLineOne']
				locator = "(//input[@name='addressLineOne'])[6]/&/(//input[@name='postalCodeId'])[6]/&/(//input[@name='city'])[6]";
				enterAdressAndZip(locator, metaDataKey);
				//a[@data-selenium-id='saveBtn']
				locator = "//a[@data-selenium-id='saveBtn']";
				click(locator, metaDataKey);
				Thread.sleep(200);
			}

			locator = "//label[text()='Secondary']";
			click(locator, metaDataKey);
			Thread.sleep(100);

			locator = "//label[text()='Years']";
			click(locator, metaDataKey);
			Thread.sleep(100);

			locator= "(//input[@name='yearsAtEmployer'])" + "["+ arrayValue +"]";
			enterText(locator, "3", metaDataKey);
			locator= "(//input[@name='monthsAtEmployer'])" + "["+ arrayValue +"]";
			enterText(locator, "2", metaDataKey);
			locator= "(//input[@name='yearsInProfession'])" + "["+ arrayValue +"]";
			enterText(locator, "4", metaDataKey);
			locator= "(//input[@name='monthsInProfession'])" + "["+ arrayValue +"]";
			enterText(locator, "2", metaDataKey);
			String[] employeePosition = {"Manager" , "VP Sales" , "Quality Analyst" , "Supervisor","HR Manager","Finance Manager"};
			String[] employeeTitle = {"Shift Manager" , "Welding Supervisor" , "Quality Contol Manager" , "Supervisor","HR Manager","Finance Manager"};
			String[] employeeBusinessType = {"Food Service" , "Food Service", "Food Service" , "Food Service","Food Service","Food Service"};
			locator = "(//input[@name='employeePosition'])"  + "["+ arrayValue +"]";
			enterText(locator,employeePosition[arrayValue-1], metaDataKey);
			locator = "(//input[@name='employeeTitle'])"  + "["+ arrayValue +"]";
			enterText(locator,employeeTitle[arrayValue-1], metaDataKey);
			locator = "(//input[@name='businessType'])"  + "["+ arrayValue +"]";
			enterText(locator,employeeBusinessType[arrayValue-1], metaDataKey);

			if (arrowClickPath[1].equals("add")) {
				locator ="//a[@data-selenium-id='saveButton']";
				click(locator, metaDataKey);
				Thread.sleep(1000);
			}

			locator = "//span[text()='SAVE']";
			click(locator, metaDataKey);
			Thread.sleep(5000);  
		}
	}

}
