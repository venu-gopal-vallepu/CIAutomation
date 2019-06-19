package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class LiabilityPage extends UIElementMethods {
	String LiabiltyPageTreeNode = "//div[text()='Liabilities']";

	@Test
	public void enterLiabilityInfo() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("enterred");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("1100001163");
			String xPath = "(//span[text()='Add Liability'])[4]/&/4";
			String count = "2";
			String metaDataKey = "Liability page";
			addLiabilty(xPath, count, metaDataKey);
		}
	}

	public void addLiabilty(String xPath , String count , String metaDataKey) throws InterruptedException{

		waitForElementWithLocator(LiabiltyPageTreeNode, metaDataKey);
		click(LiabiltyPageTreeNode, metaDataKey);

		locator = "//div[contains(@id,'loanapp-liabilitiespanel')][contains(@class,'x-panel vertical-panel')][not(contains(@style,'display: none;'))]";
		listWebElements(locator, metaDataKey);
		ArrayList<WebElement> assetForms = (ArrayList<WebElement>) ((ArrayList<WebElement>) listElems).clone();
		int size = assetForms.size();
		String[] arrowClickPath = xPath.split("/&/");
		String val = arrowClickPath[1];
		if (val.equals("add")||Integer.parseInt(val) <= size) {
			waitForElementWithLocator(arrowClickPath[0], metaDataKey);
			click(arrowClickPath[0], metaDataKey);
			Thread.sleep(500);
			mylist.clear(); 
			locator = "//span[text()='Liability Details']";
			waitForElementWithLocator(locator, metaDataKey);
			if (arrowClickPath[1].equals("add")) {
				locator = "//label[text()='Other Monthly Debts']";
				click(locator, metaDataKey);
				Thread.sleep(100);
				locator = "//input[@name='otherLiabTypeId']/../following-sibling::td/div";
				click(locator, metaDataKey);
				locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
				listWebElements(locator, metaDataKey);
				if (listElems.size()>0) {
					for (int i = 0; i < listElems.size(); i++) {
						element = listElems.get(i);
						String dropdownValue = element.getText();
						mylist.add(dropdownValue);
					}
					System.out.println(mylist);
					locator = "//input[@name='otherLiabTypeId']/../following-sibling::td/div";
					click(locator, metaDataKey);
					for (int randVal = 0; randVal < Integer.parseInt(count); randVal++) {
						locator = "//input[@name='otherLiabTypeId']/../following-sibling::td/div";
						click(locator, metaDataKey);
						Random  rand = new Random();
						int index = rand.nextInt(mylist.size());
						String item = mylist.get(index);
						System.out.println(item);
						element = driver.findElement(By.xpath("//li[text()='"+ item +"']"));
						element.click();
						Thread.sleep(500);


						locator = "//input[@name='otherLiabDescription']";
						enterText(locator, "Description", metaDataKey);

						locator = "//input[@name='periodicPaymentAmt']";
						enterText(locator, "225", metaDataKey);

						locator = "//label[text()='Payment']";
						click(locator, metaDataKey);
						Thread.sleep(200);

						locator = "//input[@name='numberPaymentsRemaining']";
						enterText(locator, "25", metaDataKey);


						locator = "//a[@data-selenium-id='saveAddButton']";
						clickOnElementUsingJavascript(locator, metaDataKey);
						Thread.sleep(3000);
						locator = "//div[text()='"+ item +"']";
						waitForElementWithLocator(locator, metaDataKey);
					}
					locator = "(//span[text()='CANCEL'])[2]";
					clickOnElementUsingJavascript(locator, metaDataKey);
					Thread.sleep(2000);
				}
			}else{
				locator = "//input[@name='liabilityTypeId']/../following-sibling::td/div";
				click(locator, metaDataKey);
				locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
				listWebElements(locator, metaDataKey);
				if (listElems.size()>0) {
					for (int i = 0; i < listElems.size(); i++) {
						element = listElems.get(i);
						String dropdownValue = element.getText();
						mylist.add(dropdownValue);
					}
					System.out.println(mylist);
					locator = "//input[@name='liabilityTypeId']/../following-sibling::td/div";
					click(locator, metaDataKey);
					for (int randVal = 0; randVal < Integer.parseInt(count); randVal++) {
						locator = "//input[@name='liabilityTypeId']/../following-sibling::td/div";
						click(locator, metaDataKey);
						Random  rand = new Random();
						int index = rand.nextInt(mylist.size());
						String item = mylist.get(index);
						System.out.println(item);
						element = driver.findElement(By.xpath("//li[text()='"+ item +"']"));
						element.click();
						Thread.sleep(500);
						driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
						boolean liabilitiesValue = true ;
						String[] instituteName = {"wellsfargo", "BankOne", "Wellsfargo","Federal Bank", "Bank  Of America"};
						locator = "//input[@name='creditorName']";
						elementFound(locator);
						if (elemFound == true) {
							int instSize = instituteName.length;
							if (randVal < instSize) {
								enterText(locator, instituteName[randVal], metaDataKey);
							}else{
								enterText(locator, instituteName[1], metaDataKey);
							}
						}

						locator = "//input[@name='accountNumber']";
						enterText(locator, "AD44SDF89757458", metaDataKey);

						String[] payment = {"150" ,"350", "450","500"};
						locator = "//input[@name='periodicPaymentAmt']";
						elementFound(locator);
						if (elemFound == true) {
							int instSize = payment.length;
							if (randVal < instSize) {
								enterText(locator, payment[randVal], metaDataKey);
							}else{
								enterText(locator, payment[1], metaDataKey);
							}
						}

						String[] monthsRemaining = {"30" ,"40", "45","50"};
						locator = "//input[@name='numberPaymentsRemaining']";
						elementFound(locator);
						if (elemFound == true) {
							int instSize = monthsRemaining.length;
							if (randVal < instSize) {
								enterText(locator, monthsRemaining[randVal], metaDataKey);
							}else{
								enterText(locator, monthsRemaining[1], metaDataKey);
							}
						}

						locator = "//input[@name='liabilityStatusTypeId']/../following-sibling::td/div";
						click(locator, metaDataKey);
						Thread.sleep(500);
						ArrayList<String> mylist1 = new ArrayList<String>();
						locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
						listWebElements(locator, metaDataKey);
						if (listElems.size()>0) {
							for (int i = 0; i < listElems.size(); i++) {
								element = listElems.get(i);
								String dropdownValue = element.getText();
								mylist1.add(dropdownValue);
							}
							System.out.println(mylist1);
						}
						rand = new Random();
						index = rand.nextInt(mylist1.size());
						item = mylist1.get(index);
						System.out.println(item);
						element = driver.findElement(By.xpath("//li[text()='"+ item +"']"));
						element.click();
						Thread.sleep(500);


						locator = "//a[@data-selenium-id='saveAddButton']";
						clickOnElementUsingJavascript(locator, metaDataKey);
						Thread.sleep(3000);
						locator = "//div[text()='"+ item +"']";
						waitForElementWithLocator(locator, metaDataKey);
					}
					locator = "(//span[text()='CANCEL'])[2]";
					clickOnElementUsingJavascript(locator, metaDataKey);
					Thread.sleep(2000);
				}else{
					String NoValues = "NoListFound";
					System.out.println(NoValues);
				}
			}



		}
	}

}