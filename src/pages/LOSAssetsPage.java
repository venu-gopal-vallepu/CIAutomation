package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.wtc.globalAccelerators.UIElementMethods;

public class LOSAssetsPage extends UIElementMethods {

	String LOSAssetsPageTreeNode = "//span[text()='Assets']";
	String  LOSExpandFirstBorrowerInfo = "//tr[contains(@id,'0:main.loan.section.borrower.summary')]//img[contains(@class,'x-tree-elbow-plus x-tree-expander')]";
	String losNew = "//span[text()='New']";

	@Test
	public void enterLOSAssetsInfo() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("enterred");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchPortalLoan("1100000614");
			String xPath = "//span[text()='Add Asset']/&/add";
			String count = "4";
			String metaDataKey = "LOS Assets page";
			click(LOSAssetsPageTreeNode, metaDataKey);
			addAssets(xPath, count, metaDataKey);
		}
	}
	public  void addAssets(String xPath , String count , String metaDataKey) throws InterruptedException, FileNotFoundException, InvalidFormatException, IOException{
		String[] arrowClickPath ;
		//		locator = "//div[text()='Total Assets:']";

		waitForElementWithLocator(LOSExpandFirstBorrowerInfo, metaDataKey);
		click(LOSExpandFirstBorrowerInfo, metaDataKey);
		waitForElementWithLocator(LOSAssetsPageTreeNode, metaDataKey);
		click(LOSAssetsPageTreeNode, metaDataKey);
		waitForElementWithLocator(losNew, metaDataKey);
		click(losNew, metaDataKey);
		arrowClickPath = xPath.split("/&/");
		Thread.sleep(500);
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
			if (listElems.size()>0) {
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
					Random  rand = new Random();
					int index = rand.nextInt(mylist.size());
					String item = mylist.get(index);
					System.out.println(item);
					element = driver.findElement(By.xpath("//li[text()='"+ item +"']"));
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
					locator = "//div[text()='"+ item +"']";
					waitForElementWithLocator(locator, metaDataKey);
				}
				locator = "(//span[text()='Cancel'])[2]";
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
			if (listElems.size()>0) {
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
					Random  rand = new Random();
					int index = rand.nextInt(mylist.size());
					String item = mylist.get(index);
					System.out.println(item);
					element = driver.findElement(By.xpath("//li[text()='"+ item +"']"));
					element.click();
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
					boolean assetsValue = true ;
					String[] instituteName = {"One Bank", "Bank One", "Blue Sage","Federal Bank", "Bank  Of America", "Wells Fargo"};
					String[] marketRate = {"85000" ,"95000", "75000","80000"};
					locator = "//input[@name='financialInstitutionName']";
					elementFound(locator);
					if (elemFound == true) {
						locator = "//input[@name='financialInstitutionName']";
						element = driver.findElement(By.xpath("//input[@name='financialInstitutionName']"));
						int instSize = instituteName.length;
						if (randVal < instSize) {
							enterText(locator, instituteName[randVal], metaDataKey);
						}else{
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
						}else{
							enterText(locator, marketRate[1], metaDataKey);
						}
					}
					Thread.sleep(500);

					locator = "//a[@data-selenium-id='saveAddButton']";
					click(locator, metaDataKey);
					Thread.sleep(3000);
					locator = "//div[text()='"+ item +"']";
					waitForElementWithLocator(locator, metaDataKey);
				}

				locator = "(//span[text()='CANCEL'])[2]";
				click(locator, metaDataKey);
				Thread.sleep(2000);
			}else{
				String NoValues = "NoListFound";
				System.out.println(NoValues);
			}

		}




	}
}

