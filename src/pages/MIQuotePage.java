package pages;

import com.wtc.globalAccelerators.UIElementMethods;

public class MIQuotePage extends UIElementMethods {

	String Mipage = "//span[text()='Mortgage Insurance']";
	String checkMIRequired = "//div[text()='MI is not required']";
	String acceptMIPopUp = "//span[text()='OK']";
	String MIPlan = "//input[@name='pmiPlanTypeId']/../following-sibling::td/div";
	String MICompany = "//input[@name='pmiCompanyId']/../following-sibling::td/div";
	String CheckSaveMIQuotes	=	"//div[text()='Saving...']";
	public void clickFirstElement(String metaDataKey){
		locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
		listWebElements(locator, metaDataKey);
		listElems.get(0).click();
	}
	public void addMIDetails(String xPath , String metaDataKey) throws InterruptedException{
		click(Mipage, metaDataKey);
		elementFound(checkMIRequired);
		if (elemFound == false) {
			click(MIPlan, metaDataKey);
			clickFirstElement(metaDataKey);
			click(MICompany, metaDataKey);
			clickFirstElement(metaDataKey);
			Thread.sleep(300);
			locator = "//span[text()='Get Rates']";
			checkElementLoadMask(checkMaskLoad, "Loading...", metaDataKey);
			locator = "//span[text()='Error']";
			elementFound(locator);
			if(elemFound == true){
				locator = "//span[text()='OK']";
				click(locator, metaDataKey);
			}
			locator = "//span[text()='Save']";
			click(locator, metaDataKey);
			checkElementLoadMask(CheckSaveMIQuotes, "Saving...", metaDataKey);
		}else{
			click(acceptMIPopUp, metaDataKey);
		}
	}
}
