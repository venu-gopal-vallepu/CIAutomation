package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class CRMAssetsPage extends UIElementMethods {
	String CRMassetsPageTreeNode = "//div[text()='Assets']";
	String LOSAssetsPageTreeNode = "//span[text()='Assets']";
	String LOSExpandFirstBorrowerInfo = "//tr[contains(@id,'0:main.loan.section.borrower.summary')]//img[contains(@class,'x-tree-elbow-plus x-tree-expander')]";
	String losNew = "//span[text()='New']";
	boolean CRMelemfound;
	

	@Test
	public void enterCRMAssetsInfo() throws IOException,
	InvalidFormatException, InterruptedException, IOException {
		System.out.println("enterred");
		if (dataListNum == 0) {
			Login objLogin = new Login();
			objLogin.searchPortalLoan("LEAD-1000009");
			String xPath = "(//span[text()='Add Asset'])[3]/&/add";
			String count = "4";
			String metaDataKey = "CRM Assets page";
			click(CRMassetsPageTreeNode, metaDataKey);
			addAssets(xPath, count, metaDataKey);
		}
	}

	public void addAssets(String xPath, String count, String metaDataKey)
			throws InterruptedException, FileNotFoundException,
			InvalidFormatException, IOException {
	
		waitForElementWithLocator(CRMassetsPageTreeNode, metaDataKey);
		click(CRMassetsPageTreeNode, metaDataKey);

		locator = "//div[contains(@id,'loanapp-assetspanel')][contains(@class,'x-panel vertical-panel')][not(contains(@style,'display: none;'))]";
		listWebElements(locator, metaDataKey);
		ArrayList<WebElement> assetForms = (ArrayList<WebElement>) ((ArrayList<WebElement>) listElems).clone();
		int size = assetForms.size();
		String[] arrowClickPath = xPath.split("/&/");
		String val = arrowClickPath[1];
		if (val.equals("add")||Integer.parseInt(val) <= size) {

			waitForElementWithLocator(arrowClickPath[0], metaDataKey);
			click(arrowClickPath[0], metaDataKey);
			CRMelemfound = true;

			String locator = "//label[text()='Asset Type']";
			waitForElementWithLocator(locator, metaDataKey);
			Thread.sleep(500);

			if (arrowClickPath[1].equals("add")) {

				locator = "//label[text()='Other Asset']";
				click(locator, metaDataKey);

				locator = "//input[@name='assetTypeId']/../following-sibling::td/div";
				click(locator, metaDataKey);
				Thread.sleep(500);
				locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
				mylist.clear();
				listWebElements(locator, metaDataKey);
				if (listElems.size() > 0) {
					for (int i = 0; i < listElems.size(); i++) {
						element = listElems.get(i);
						String dropdownValue = element.getText();
						mylist.add(dropdownValue);
					}
					System.out.println(mylist);
					locator = "//input[@name='assetTypeId']/../following-sibling::td/div";
					click(locator, metaDataKey);
					Thread.sleep(500);
					for (int randVal = 0; randVal < Integer.parseInt(count); randVal++) {
						locator = "//input[@name='assetTypeId']/../following-sibling::td/div";
						click(locator, metaDataKey);
						Random rand = new Random();
						int index = rand.nextInt(mylist.size());
						String item = mylist.get(index);
						System.out.println(item);
						element = driver.findElement(By.xpath("//li[text()='"
								+ item + "']"));
						element.click();
						Thread.sleep(500);

						locator = "//input[@name='shortDescription']";
						enterText(locator, "Description", metaDataKey);

						locator = "//input[@name='assetValueAmt']";
						enterText(locator, "15000", metaDataKey);

						locator = "//input[@name='marketValueAmt']";
						enterText(locator, "15000", metaDataKey);

						Thread.sleep(500);

						locator = "//a[@data-selenium-id='saveAddButton']";
						click(locator, metaDataKey);
						Thread.sleep(3000);
						locator = "//div[text()='" + item + "']";
						waitForElementWithLocator(locator, metaDataKey);
					}
					if (CRMelemfound == false) {
						locator = "(//span[text()='Cancel'])[2]";
					} else {
						locator = "(//span[text()='CANCEL'])[2]";
					}
					click(locator, metaDataKey);
					Thread.sleep(2000);
				}
			} else {
				locator = "//input[@name='assetTypeId']/../following-sibling::td/div";
				waitForElementWithLocator(locator, metaDataKey);
				click(locator, metaDataKey);
				Thread.sleep(500);
				locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
				listWebElements(locator, metaDataKey);
				mylist.clear();
				if (listElems.size() > 0) {
					for (int i = 0; i < listElems.size(); i++) {
						element = listElems.get(i);
						String dropdownValue = element.getText();
						mylist.add(dropdownValue);
					}
					System.out.println(mylist);
					locator = "//input[@name='assetTypeId']/../following-sibling::td/div";
					click(locator, metaDataKey);
					Thread.sleep(500);
					for (int randVal = 0; randVal < Integer.parseInt(count); randVal++) {
						locator = "//input[@name='assetTypeId']/../following-sibling::td/div";
						click(locator, metaDataKey);
						Random rand = new Random();
						int index = rand.nextInt(mylist.size());
						String item = mylist.get(index);
						System.out.println(item);
						element = driver.findElement(By.xpath("//li[text()='"
								+ item + "']"));
						element.click();
						Thread.sleep(500);
						driver.manage().timeouts()
						.implicitlyWait(500, TimeUnit.MILLISECONDS);
						boolean assetsValue = true;
						String[] instituteName = { "One Bank", "Bank One",
								"Blue Sage", "Federal Bank", "Bank  Of America",
						"Wells Fargo" };
						String[] marketRate = { "85000", "95000", "75000", "80000" };
						locator = "//input[@name='financialInstitutionName']";
						elementFound(locator);
						if (elemFound == true) {
							locator = "//input[@name='financialInstitutionName']";
							element = driver
									.findElement(By
											.xpath("//input[@name='financialInstitutionName']"));
							int instSize = instituteName.length;
							if (randVal < instSize) {
								enterText(locator, instituteName[randVal],
										metaDataKey);
							} else {
								enterText(locator, instituteName[1], metaDataKey);
							}
						}
						locator = "//input[@name='accountValueAmt']";
						elementFound(locator);
						if (elemFound == true) {
							locator = "//input[@name='accountValueAmt']";
							int marketRateSize = marketRate.length;
							if (randVal < marketRateSize) {
								enterText(locator, marketRate[randVal], metaDataKey);
							} else {
								enterText(locator, marketRate[1], metaDataKey);
							}
						}
						Thread.sleep(500);

						locator = "//a[@data-selenium-id='saveAddButton']";
						click(locator, metaDataKey);
						Thread.sleep(3000);
						locator = "//div[text()='" + item + "']";
						waitForElementWithLocator(locator, metaDataKey);
					}
					if (CRMelemfound == false) {
						locator = "(//span[text()='Cancel'])[2]";
					} else {
						locator = "(//span[text()='CANCEL'])[2]";
					}
					click(locator, metaDataKey);
					Thread.sleep(2000);
				} else {
					String NoValues = "NoListFound";
					System.out.println(NoValues);
				}

			}

		}
	}
}
