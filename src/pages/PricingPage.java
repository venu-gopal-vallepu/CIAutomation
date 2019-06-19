package pages;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class PricingPage extends UIElementMethods {
	String PricingPage = "//div[text()='Pricing']";
	public void selectPricingValue(String metaDataKey ){
		String[] value = { "0.000%", "0.250%","1.500%", "-0.500", "0.125%", "0.500%","1.000%" };
		boolean elemFound = false;
		for (int i = 0; i < value.length; i++) {
			String priceRate = value[i];
			String locator = "//td[contains(@class,'span_link')]/div[text()="+ "'" + priceRate + "'" + "]";
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
			int elemNumber = driver.findElements(By.xpath(locator)).size();
			if (elemNumber > 0) {
				click(locator , metaDataKey);
				elemFound = true;
				break;
			} else {
				System.out.println(metaDataKey + "element not dsplayed");
			}
		}
		if (elemFound == false) {
			locator = "//td[contains(@class,'span_link')]/div";
			click(locator, metaDataKey);
		}
	}



	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("enterred");
		if (dataListNum == 0) {

			Login objLogin =new Login();	
			objLogin.searchPortalLoan("1100000608");
			String xPath = "//span[text()='Add Asset']/&/1";
			String count = "2";
			String metaDataKey = "Pricing page";
			//			click(CRMassetsPageTreeNode, metaDataKey);
			addPricingInfo(xPath, count, metaDataKey);
		}
	}


	public void addPricingInfo(String xPath ,String text, String metaDataKey) throws InterruptedException{
		waitForElementWithLocator(PricingPage, metaDataKey);
		click(PricingPage, metaDataKey);
		locator = "//span[text()='Select Rate']";
		waitForElementWithLocator(locator, metaDataKey);
		String locator = "//input[@name='rateLockTypeId']/../following-sibling::td/div";
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
			rand = new Random();
			int index = rand.nextInt(mylist.size());
			String item = mylist.get(index);
			System.out.println(item);

			element = driver.findElement(By.xpath("//li[text()='"+ item +"']"));
			element.click();
			Thread.sleep(500);
			locator = "//span[text()='Select Rate']";
			click(locator, metaDataKey);
			locator = "//span[text()='Select Pricing']";
			waitForElementWithLocator(locator, metaDataKey);
			selectPricingValue(metaDataKey);
			locator = "//div[text()='Total Rate and Points']";
			waitForElementWithLocator(locator, metaDataKey);
			locator = "//span[text()='SAVE']";
			click(locator, metaDataKey);
			String val11 = "Floating";
			if (!item.equals(val11)){
				locator = "//span[text()='Confirmation']";
				waitForElementWithLocator(locator, metaDataKey);
				locator = "//a[@data-selenium-id='yes']";
				click(locator, metaDataKey);
			}

			locator = "//div[text()='Saving...']";
			text = "Saving...";
			checkElementLoadMask(locator, text, metaDataKey);
			click(locator, metaDataKey);

		}
	}
}
