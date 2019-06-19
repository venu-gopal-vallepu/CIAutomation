package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.wtc.globalAccelerators.UIElementMethods;

public class DocumentPackagePage extends UIElementMethods {
	String documentpackageTreeNode = "//span[text()='Document Package']";
	String CheckDocumentPackageScreen	=	"//span[text()='Retrieve Package List']";
	String GetInitialDisclosure	=	"(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[2]";
	String initialDisclosurevalue = "//li[text()='Initial Disclosure']";
	String  preDisclosurevalue = "//li[text()='Pre-Disclosure']";
	String CheckInitialDisclosure	=	"(//input[contains(@name,'combocustom')])[2]";
	String RetrievePackageList	=	"//span[text()='Retrieve Package List']";
	String CheckRetrievePackageList	=	"//div[text()='Processing Request ...']";
	String SelecRetrievePackageList	=	"//div[contains(@class,'x-column-header-inner-empty')]";
	String DocDestination	=	"(//input[contains(@name,'combocustom')]/../following-sibling::td/div)[3]";
	String PrintDocuments	=	"//span[text()='Send']";
	String WaitForDocuments	=	"//div[text()='Generating documents ...']";
	String CloseDocumentsPOPup	=	"(//img[@class='x-tool-img x-tool-close'])[2]";

	
	@Test
	public void enterAssetsInfoDir() throws IOException, InvalidFormatException, InterruptedException, IOException, AWTException{
		System.out.println("entered");
		if (dataListNum == 0) {
			Login objLogin =new Login();	
			objLogin.searchLP("1100000664");
			Thread.sleep(1500);
			String metaDataKey = "Document package";
			addDocumentpackageDetails(documentpackageTreeNode, metaDataKey , "Pre-Disclosure");
		}
	}
	
	
	
	
	public void addDocumentpackageDetails(String xPath,String metaDataKey, String text) throws InterruptedException, AWTException{
//		waitForElementWithLocator(documentpackageTreeNode, metaDataKey);
		click(documentpackageTreeNode, metaDataKey);
		waitForElementWithLocator(CheckDocumentPackageScreen, metaDataKey);
		click(GetInitialDisclosure, metaDataKey);
		if(text.equals("Initial Disclosure")){
			elementFound(initialDisclosurevalue);
			enterDocPackageDetails( initialDisclosurevalue, metaDataKey);
		}else{
			elementFound(preDisclosurevalue);
			enterDocPackageDetails( preDisclosurevalue, metaDataKey);
		}
	}


	public void enterDocPackageDetails(String xPath11,String metaDataKey) throws InterruptedException, AWTException{
		if (elemFound == true) {
			click(xPath11, metaDataKey);
			click(RetrievePackageList, metaDataKey);
			checkElementLoadMask(CheckRetrievePackageList, "Processing Request ...", metaDataKey);
			click(SelecRetrievePackageList, metaDataKey);
			click(DocDestination, metaDataKey);
			locator = "//li[text()='E-Sign']";
			click(locator, metaDataKey);
			click(PrintDocuments, metaDataKey);
			checkElementLoadMask(WaitForDocuments, "Generating documents ...", metaDataKey);
//			click(CloseDocumentsPOPup, metaDataKey);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			Thread.sleep(300);

		} else {
			intialDisclosureFound = false;
		}
	}

}


