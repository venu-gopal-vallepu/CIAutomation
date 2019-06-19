package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class IncomePage extends UIElementMethods {

	String IncomepageTreeNode = "//div[text()='Income']";

	@Test
	public void enterAssetsInfoDir() throws IOException,
			InvalidFormatException, InterruptedException, IOException {
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin = new Login();
			objLogin.searchPortalLoan("CRM-1200003581");
			Thread.sleep(1500);
			String count = "4";
			String metaDataKey = "Income page";
			String xPath = "(//a[@data-selenium-id='btnAdd'])[3]/&/3";
			addIncome(xPath, count, metaDataKey);
		}
	}

	public void addIncome(String xPath, String count, String metaDataKey)
			throws InterruptedException {
		waitForElementWithLocator(IncomepageTreeNode, metaDataKey);
		click(IncomepageTreeNode, metaDataKey);

		locator = "//div[contains(@id,'loanapp-incomepanel')][contains(@class,'x-panel-default vertical-panel')][not(contains(@style,'display: none;'))]";
		listWebElements(locator, metaDataKey);
		ArrayList<WebElement> incomeForms = (ArrayList<WebElement>) ((ArrayList<WebElement>) listElems)
				.clone();
		int size = incomeForms.size();

		String[] arrowClickPath = xPath.split("/&/");
		String val = arrowClickPath[1];
		if (Integer.parseInt(val) <= size) {
			rand = new Random();
			waitForElementWithLocator(arrowClickPath[0], metaDataKey);
			int arrayValue = Integer.parseInt(arrowClickPath[1]);
			locator = "(//input[@name='baseEmploymentIncome'])" + "["
					+ arrayValue + "]";
			String[] baseEmployment = { "8200", "9100", "8800", "8500" };
			getRandomValueFromArray(baseEmployment, baseEmployment.length - 1);
			enterText(locator, randomValue, metaDataKey);

			locator = "(//input[@name='overtimePay'])" + "[" + arrayValue + "]";
			String[] overTime = { "100", "110", "150", "145" };
			getRandomValueFromArray(overTime, overTime.length - 1);
			enterText(locator, randomValue, metaDataKey);

			locator = "(//input[@name='bonuses'])" + "[" + arrayValue + "]";
			String[] bonuses = { "100", "110", "150", "145" };
			getRandomValueFromArray(bonuses, bonuses.length - 1);
			enterText(locator, randomValue, metaDataKey);

			locator = "(//input[@name='commissions'])" + "[" + arrayValue + "]";
			String[] commissions = { "100", "110", "150", "145" };
			getRandomValueFromArray(commissions, commissions.length - 1);
			enterText(locator, randomValue, metaDataKey);

			locator = "(//input[@name='dividends'])" + "[" + arrayValue + "]";
			String[] dividends = { "100", "110", "150", "145" };
			getRandomValueFromArray(dividends, dividends.length - 1);
			enterText(locator, randomValue, metaDataKey);

			click(arrowClickPath[0], metaDataKey);

			for (int randVal = 0; randVal < Integer.parseInt(count); randVal++) {
				locator = "//span[text()='Add Income']";
				waitForElementWithLocator(locator, metaDataKey);
				locator = "//input[@name='incomeTypeId']/../following-sibling::td/div";
				click(locator, metaDataKey);
				locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
				listWebElements(locator, metaDataKey);
				if (listElems.size() > 0) {
					mylist.clear();
					for (int i = 0; i < listElems.size(); i++) {
						element = listElems.get(i);
						String dropdownValue = element.getText();
						mylist.add(dropdownValue);
					}
				}
				getRandomValuefromList(mylist, mylist.size() - 1);
				String savedRecord = "//li[text()='" + randomValue + "']";
				click(locator, metaDataKey);
				Thread.sleep(500);
				locator = "//input[@name='periodicAmt']";
				String[] incomeAmount = { "300", "350", "400", "500" };
				getRandomValueFromArray(incomeAmount, incomeAmount.length - 1);
				enterText(locator, randomValue, metaDataKey);
				locator = "//a[@data-selenium-id='saveAddIncomeButton']";
				click(locator, metaDataKey);
				Thread.sleep(2000);
				element = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(savedRecord)));

			}
			locator = "(//span[text()='CANCEL'])[2]";
			click(locator, metaDataKey);
			Thread.sleep(500);
		}
	}

}
