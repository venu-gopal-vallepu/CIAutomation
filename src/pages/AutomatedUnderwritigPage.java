package pages;

import com.wtc.globalAccelerators.UIElementMethods;

public class AutomatedUnderwritigPage extends UIElementMethods {
	String LOSAutomatdUnderwriting	=	"//span[text()='Automated Underwriting']";
	String CheckLOSAutomatdUnderwritingPage	=	"//label[text()='AUS Only Order']";
	String LOSSelectFanie	=	"//label[text()='Fannie Mae Desktop Underwriter (DU)']";
	String LOSGetRefValue	=	"//input[@name='creditReferenceNumber1']";
	String LOSEnterDUValue	=	"//input[@name='ausCasefileId']";
	String LOSFaneSendRequest	=	"//span[text()='Send Request']";
	String WaitForfanieRecord	=	"//div[text()='sudershan chintakayala']";

	public void addAutomaedDetails(String xPath , String metaDataKey){/*
		click(xPath, metaDataKey);
		waitForElementWithLocator(CheckLOSAutomatdUnderwritingPage, metaDataKey);
		click(LOSSelectFanie, metaDataKey);
		click(CheckLOSAutomatdUnderwritingPage, metaDataKey);
	 */}
}
