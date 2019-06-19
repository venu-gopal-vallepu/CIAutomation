package pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class ChecklistsPage extends UIElementMethods {
	String checklistTreeNodepage = "//span[text()='Checklists']";
	String checkCheckListPage = "//span[text()='Add Checklist Item']";
	
	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("5000000003");
			Thread.sleep(1500);
			String xPath = ".//span[text()='Underwriting']";
			String metaDataKey = "checklists Page";
			waitForElementWithLocator(xPath, metaDataKey);
			click(xPath, metaDataKey);
			addChecklistitems(checklistTreeNodepage, metaDataKey);
		}
	}

public void addChecklistitems(String xPath, String metaDataKey) throws InterruptedException{
	waitForElementWithLocator(checklistTreeNodepage, metaDataKey);
	Thread.sleep(500);
	click(checklistTreeNodepage, metaDataKey);
	waitForElementWithLocator(checkCheckListPage, metaDataKey);
	locator ="//div[@data-selenium-id='gridItems']//tr/td[5]";
    listWebElements(locator, metaDataKey);
    for (int i = 0; i < listElems.size(); i++) {
		listElems.get(i).click();
	}
  locator ="//span[text()='Save']";
  click(locator, metaDataKey);
  Thread.sleep(2000);

}
}
