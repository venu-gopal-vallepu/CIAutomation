package pages;

import com.wtc.globalAccelerators.UIElementMethods;

public class PropertyFloodPage extends UIElementMethods {
    String propertyFloodTreeNode = ".//span[text()='Property Flood Certification']";
	String PropertyFloodCheckPropertflood = "//div[text()='No Flood Info Found']";
	String PropertyFloodnewflood ="//span[text()='New']";
	String PropertyFloodFloodcertificate1 =	"//input[@name='floodCertCompanyId']/../following-sibling::td/div";
	String PropertyFloodcertificatedate	="//input[@name='floodCertificateDate']";
	String PropertyFloodCertificatenumber = "//input[@name='floodCertificateNumber']";
	String PropertyFloodcommunityname	="//input[@name='nfipCommunityName']";
	String PropertyFloodcommunitynumbr= "//input[@name='nfipCommunityNumber']"	;
	String PropertyFloodMapnumber="//input[@name='nfipMapPanelNumber']";
	String PropertyFloodMaprevisedate="//input[@name='nfipMapEffectiveRevisedDate']";
	String PropertyFloodLOMA="//label[text()='Yes']";
	String PropertyFloodLOMADate="//input[@name='lomaLetterDate']";
	String PropertyFloodLOMR="//label[text()='Yes']";
	String PropertyFloodLOMRDate=".//input[@name='lomrLetterDate']"	;
	String PropertyFloodfloodzonecode="//input[@name='floodZoneCode']";
	String PropertyFloodMobilehome="(.//label[text()='Yes'])[2]";
	String PropertyFloodFloodInsurance="//input[@name='floodInsuranceTypeId']";
//	String PropertyFloodCBRAOrOPA=".//input[@name='cbraOPADesignationDate']";	
	String PropertyFloodSpecialfloodzone="(.//label[text()='Yes'])[4]";	
	String PropertyFloodLifeofloan="(.//label[text()='Yes'])[5]";
	String PropertyFloodFloodCertificate="(.//label[text()='Yes'])[7]";
	String PropertyFloodsavePropertyflood = "//span[text()='Save & Close']";
	String ChecksavePropertyflood="//table[contains(@class,'x-gridview')]";

	public void  addPropertyFloodDetails(String xPath, String metaDataKey) throws InterruptedException{
		click(propertyFloodTreeNode, metaDataKey);
		waitForElementWithLocator(PropertyFloodCheckPropertflood, metaDataKey);
		click(PropertyFloodnewflood, metaDataKey);
		click(PropertyFloodFloodcertificate1, metaDataKey);
		Thread.sleep(500);
		locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
		listWebElements(locator, metaDataKey);
		if (listElems.size()>0) {
			listElems.get(0).click();
			String text = todayDate();
			enterText(PropertyFloodcertificatedate, text, metaDataKey);
			text ="AEW7456y4D789";
			enterText(PropertyFloodCertificatenumber, text, metaDataKey);
			text ="LAGUNA NIGUEL, CITY OF";
			enterText(PropertyFloodcommunityname, text, metaDataKey);
			text ="AEW7456y4D789";
			enterText(PropertyFloodcommunitynumbr, text, metaDataKey);
			text ="06059C0439J";
			enterText(PropertyFloodMapnumber, text, metaDataKey);
			text = todayDate();
			enterText(PropertyFloodMaprevisedate, text, metaDataKey);
			click(PropertyFloodLOMA, metaDataKey);
			text = todayDate();
			enterText(PropertyFloodLOMADate, text, metaDataKey);
			//			PropertyFloodfloodzonecode
			text = "124AS454";
			enterText(PropertyFloodfloodzonecode, text, metaDataKey);
			click(PropertyFloodMobilehome, metaDataKey);
			//			PropertyFloodFloodInsurance
			text = "Regular Program of NFIP";
			enterText(PropertyFloodFloodInsurance, text, metaDataKey);

			click(PropertyFloodSpecialfloodzone, metaDataKey);
			click(PropertyFloodLifeofloan, metaDataKey);
			click(PropertyFloodFloodCertificate, metaDataKey);
			click(PropertyFloodsavePropertyflood, metaDataKey);
			waitForElementWithLocator(ChecksavePropertyflood, metaDataKey);
		}

	}
}
