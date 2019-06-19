package com.wtc.globalAccelerators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pages.AssetVerficationPage;
import pages.CRMAssetsPage;
import pages.BorrowersPage;
import pages.CRMLoanTermsPage;
import pages.ClosingCostDetailsPage;
import pages.CompliancePage;
import pages.ConsentPage;
import pages.CreditPage;
import pages.DecisioningPage;
import pages.DeclarationsPage;
import pages.DemographicPage;
import pages.DocumentPackagePage;
import pages.EmploymentPage;
import pages.EmploymentVerificationPage;
import pages.EscrowForCompletionPage;
import pages.EscrowInfoPage;
import pages.FHAPage;
import pages.FundingInformationPage;
import pages.IncomePage;
import pages.IncomeVerificationPage;
import pages.LOSHousingExpensePage;
import pages.LOSLoanPricingPage;
import pages.LegalDescriptionPage;
import pages.LiabilityPage;
import pages.LiabilityVerificationPage;
import pages.LoanConditionsPage;
import pages.LoanFoldersPage;
import pages.LoanStauspage1;
import pages.LoanstatusPage;
import pages.PricingPage;
import pages.PropertyFloodPage;
import pages.REOPage;
import pages.ScheduleLoanForClosingpage;
import pages.ShippingPage;
import pages.VendorServicesPage;
import pages.crmRatiosPage;
import pages.PurposeAndPropertyPage;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.wtc.excelutil.ReadExcel;
import com.wtc.excelutil.XlsxExcelReaderUtil;
import com.wtc.excelutil.XlsxMultiAppReaderUtil;

public class DriverMethods extends UIElementMethods {
	public ExtentHtmlReporter extentHtmlReporter;
	public ExtentTest extentTest;
	public com.aventstack.extentreports.ExtentReports extentReports;

	public static void getErrors() {
		System.err.println(metaDataKey);
		String errors = Reporter.getOutput().toString();
		String[] logErrors = errors.split(",");
		Reporter.log(metaDataKey + logErrors[0]);
	}

	public static XlsxExcelReaderUtil excelReaderUtil = new XlsxExcelReaderUtil();
	public static XlsxMultiAppReaderUtil appReaderUtil = new XlsxMultiAppReaderUtil();
	public static ReadExcel excel = new ReadExcel();
	public static Map<String, String> metaDataMap = new LinkedHashMap<String, String>();
	public static LinkedHashMap<String, String> fieldDataValueMap = new LinkedHashMap<String, String>();
	public static List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
	static String metaDataKey;
	public boolean mortgagePercentage;

	public static String[][] getExcelValues() throws InvalidFormatException,
	IOException {
		Utils.readPropertiesFile();
		String credentials = Utils.loader.getResource(
				Utils.properties.getProperty("HomebridgeCredentials")).getPath();
		return excel.getCellData(credentials, "Sheet1");
	}


	public void actionMethods() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		results = true;
		try {
			seconds = 0;
			errorCount = 0;
			for (int k = 0; k < fieldDataValueMap.keySet().size(); k++) {
				wait = new WebDriverWait(driver, 30);
				if (seconds < 80) {
					try {
						metaDataKey = (String) fieldDataValueMap.keySet()
								.toArray()[k];
						String metaDataVal = metaDataMap.get(metaDataKey);
						String[] methName = metaDataVal.split("&&");
						String methodName = methName[0];
						String[] arr = metaDataVal.split("&&");
						String xPath = "";
						String testData = String.valueOf(fieldDataValueMap.get(metaDataKey));

						for (String nextValue : arr) {
							if ((nextValue.contains("//"))) {
								xPath = nextValue;
							}
						}

						String dataSheetValue = String.valueOf(fieldDataValueMap
								.get(metaDataKey));
						if (metaDataKey.equals(Utils.properties.getProperty("property"))) {
							resultsList.add(dataSheetValue);
						}

						if (metaDataVal != null && methodName.equals("nodes")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								Thread.sleep(500);
								click(xPath , metaDataKey);
								Thread.sleep(1500);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("button")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								click(xPath , metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("enterreoinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String reoInfokey = String.valueOf(fieldDataValueMap.get("LoanPurpose"));
								if (reoInfokey.equals("Refinance")) {
									REOPage reoPage = new REOPage();
									reoPage.addREODetails(xPath , metaDataKey);
								}
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("entercreditinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								CreditPage creditPage = new CreditPage();
								creditPage.addCreditDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("enterpricinginfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								PricingPage pricingPage = new PricingPage();
								pricingPage.addPricingInfo(xPath, testData, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("enterdeclarationsinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								DeclarationsPage declarationsPage = new DeclarationsPage();
								declarationsPage.addDeclarationsDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("enterdemographicinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								DemographicPage demographicPage = new DemographicPage();
								demographicPage.addDemographicDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("entercrmratiosinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								crmRatiosPage crmRatiosPage = new crmRatiosPage();
								crmRatiosPage.addCRMRatiosDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("enterclosingcostdetailsinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								ClosingCostDetailsPage closingCostDetailsPage = new ClosingCostDetailsPage();
								closingCostDetailsPage.addClosingCostDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("entervendorinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								VendorServicesPage vendorServicesPage = new VendorServicesPage();
								vendorServicesPage.addVendorServices(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("radio")
								|| methodName.equals("checkbox")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String[] arrowClickPath = xPath.split("/&/");
								String locator = String.valueOf("(//label[text()='"+ String.valueOf(fieldDataValueMap.get(metaDataKey))+"'])"+ "[" + arrowClickPath[1] + "]");
								click(locator, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							} 
						}else if (metaDataVal != null && methodName.equals("links")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								PricingPage pricingPage = new PricingPage();
								pricingPage.selectPricingValue(metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("entercomfhadetails")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								CRMLoanTermsPage crmLoanTermsPage = new CRMLoanTermsPage();
								crmLoanTermsPage.addFHAInfo();
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("entercomvadetails")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								CRMLoanTermsPage crmLoanTermsPage = new CRMLoanTermsPage();
								crmLoanTermsPage.addVAInfo();
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("entercomusdaadetails")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								CRMLoanTermsPage crmLoanTermsPage = new CRMLoanTermsPage();
								crmLoanTermsPage.addUSDAInfo();
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("invisible")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String text = String.valueOf(fieldDataValueMap.get(metaDataKey));
								checkElementLoadMask(xPath, text, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("entercompleteadress")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								enterAdressAndZip(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("enterCoventionalProductDetails")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								conventionalProductDetails(xPath , metaDataKey);
							}else {
								System.out.println(metaDataKey + "null value");
							}
						} else if (metaDataVal != null && methodName.equals("appendmonth")) {
							if (testData != "null" && !testData.isEmpty()) {
								String text = String.valueOf(fieldDataValueMap.get(metaDataKey));
								addMonth(xPath,text ,metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("appenddate")) {
							if (testData != "null" && !testData.isEmpty()) {
								String text = String.valueOf(fieldDataValueMap.get(metaDataKey));
								addDate(xPath,text ,metaDataKey);
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}  else if (metaDataVal != null && methodName.equals("skipdata")) {
							if (testData != "null" && !testData.isEmpty()) {
								elementFound(xPath);
								if (elemFound == false) {
									for (int presentField = k ; presentField < fieldDataValueMap.keySet().size(); presentField++) {
										int count = 0;
										metaDataKey = (String) fieldDataValueMap.keySet()
												.toArray()[k];
										String metaDataVal1 = metaDataMap.get(metaDataKey);
										String[] value  = metaDataKey.split(" ");
										String text1 = value[0];
										if (metaDataKey.contains(text1)) {
											count ++;
										}else{
											break;
										}
										k += count;
									}	
								}
							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("currentdate")) {
							if (testData != "null" && !testData.isEmpty()) {
								String text = todayDate();
								enterText(xPath, text, metaDataKey);
							}else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("scroll")) {
							if (testData != "null" && !testData.isEmpty()) {
								scroll(xPath, metaDataKey);
							}else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("doubleclick")) {
							if (testData != "null" && !testData.isEmpty()) {
								doubleClick(xPath, metaDataKey);
							}else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("clickelementbytext")) {
							if (testData != "null" && !testData.isEmpty()) {
								Pattern pattern = Pattern.compile("\'([^\']*)\'");
								Matcher matcher = pattern.matcher(xPath);
								List<String> where = new ArrayList<String>();
								while(matcher.find()) {
									System.out.println(matcher.group(1));
									where.add(matcher.group(1));
								}

								String newValue = String.valueOf(fieldDataValueMap.get(metaDataKey));

								String newXpath = xPath.replace(
										String.valueOf(where.get(1)), newValue);

								driver.findElement(By.xpath(newXpath)).click();
								Thread.sleep(300);
							}else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("loadmask")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String style = driver.findElement(By.xpath("//div[@class='notification x-layer notification-default x-border-box']")).getAttribute("style");
								boolean isVisible = style
										.indexOf("display: none;") == -1;
							} else {
								System.err.println(metaDataKey + "null value");
							}
						}  else if (metaDataVal != null	&& methodName.equals("countandclick")) {
							if (testData != "null" && !testData.isEmpty()) {
								countAndClick(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						} else if (metaDataVal != null && methodName.equals("enterconsentinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								ConsentPage consentPage = new ConsentPage();
								consentPage.enterConsentDetails(xPath,testData,metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterpropertyfloodinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								PropertyFloodPage propertyFloodPage = new PropertyFloodPage();
								propertyFloodPage.addPropertyFloodDetails(xPath,metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						} else if (metaDataVal != null && methodName.equals("enterlegaldescriptiondetails")) {
							if (testData != "null" && !testData.isEmpty()) {
								LegalDescriptionPage legalDescriptionPage = new LegalDescriptionPage();
								legalDescriptionPage.addLegalDescriptionDetails(xPath,metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("entercompliancedetails")) {
							if (testData != "null" && !testData.isEmpty()) {
								CompliancePage compliancePage = new CompliancePage();
								compliancePage.addComplianceDetails(xPath, metaDataVal);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterchecklistinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								pages.ChecklistsPage checklistsPage = new pages.ChecklistsPage();
								checklistsPage.addChecklistitems(xPath,metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enteremployeverificationinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								EmploymentVerificationPage employmentVerificationPage = new EmploymentVerificationPage();
								employmentVerificationPage.addEmploymentVerification(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterincomeverficationinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								IncomeVerificationPage incomeVerificationPage = new IncomeVerificationPage();
								incomeVerificationPage.addIncomeDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterliabiltyverificationinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								LiabilityVerificationPage liabilityVerificationPage = new LiabilityVerificationPage();
								liabilityVerificationPage.addLiabilityDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterassetverificationinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								AssetVerficationPage assetVerficationPage = new AssetVerficationPage();
								assetVerficationPage.addAssetDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterlospricinginfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								LOSLoanPricingPage losLoanPricingPage = new LOSLoanPricingPage();
								losLoanPricingPage.addLoanPricingDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterdecisioninginfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								DecisioningPage  decisioningPage = new DecisioningPage();
								decisioningPage.addDecisionDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterdocpackageinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								DocumentPackagePage documentPackagePage = new DocumentPackagePage();
								documentPackagePage.addDocumentpackageDetails(xPath, metaDataKey , testData);
								if ( intialDisclosureFound == false) {
									break;
								}
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterloanconditionsinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								LoanConditionsPage  loanConditionsPage = new LoanConditionsPage();
								loanConditionsPage.addLoanConditions(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterloanstatusinnfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								LoanStauspage1 loanStauspage1 = new LoanStauspage1();
								loanStauspage1.addLoanStatusDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterescrowforcomletioninfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								EscrowForCompletionPage  escrowForCompletionPage = new EscrowForCompletionPage();
								escrowForCompletionPage.addEscrowForCompletion(xPath, metaDataVal);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterEscrowInfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								EscrowInfoPage escrowInfoPage = new EscrowInfoPage();
								escrowInfoPage.addEscrowInfoPage(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterfundinginfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								FundingInformationPage fundingInformationPage = new FundingInformationPage();
								fundingInformationPage.addFundingDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("entershippinginfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								ShippingPage shippingPage = new ShippingPage();
								shippingPage.addShippingDetails(xPath, metaDataKey);
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("enterborrowerinfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								BorrowersPage borrowersPage = new BorrowersPage();
								String val = String.valueOf(fieldDataValueMap.get("MortgageLoanType"));
								borrowersPage.addBorrowerDetails(xPath, metaDataKey,val);

							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null
								&& methodName.equals("countandentertext")) {
							String x = String.valueOf(fieldDataValueMap
									.get(metaDataKey));
							if (x != "null" && !x.isEmpty()) {
								String[] arrowClickPath = xPath.split("/&/");
								List<WebElement> elems = driver.findElements(By.xpath(arrowClickPath[0]));
								for(int l=0 ; l<elems.size(); l++) {
									Thread.sleep(300); 
									List<WebElement> elems1 = driver.findElements(By.xpath(arrowClickPath[0]));
									System.out.println(elems1.get(l));
									elems1.get(l).click();
									Thread.sleep(200); 
									element = driver.findElement(By.xpath(arrowClickPath[1]));
									element.click();
									element.sendKeys(
											String.valueOf(fieldDataValueMap
													.get(metaDataKey))); 
									Thread.sleep(300);
								}
							} else {
								System.out.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("gettext")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								long loanValue ;
								String text = getElemText(xPath, metaDataKey);
								System.out.println(text);
								getNumberFromText(text);
							}else {
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("attvalue")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								getAttributeValue(xPath, metaDataKey);
								actions.moveToElement(element).sendKeys(values).perform();
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("gettextandentervalue")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								getTextAndEnterValue(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("enterattributevalue")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								getAttributeAndEnterValue(xPath, metaDataVal);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("keystab")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								Thread.sleep(2000);
								click(xPath, metaDataKey);
								enterText(xPath, number, metaDataVal);
								clickOnKeyboardEnter(xPath, metaDataKey);
								Thread.sleep(3000);
							}else{
								System.out.println(metaDataKey + " value is null");
							}

						}else if (metaDataVal != null && methodName.equals("compare")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								element = driver.findElement(By.xpath(xPath));
								values = element.getAttribute("value");
								System.out.println(metaDataKey + values);
								if (values.equals(testData)) {
									System.out.println(metaDataKey + "  InitialDisclose value found ");
								} else {
									break;
								}
							} else {
								System.err.println(metaDataKey + " null value");
							}
						} else if (metaDataVal != null && methodName.equals("selectreferencevalue")) {
							if (testData != "null" && !testData.isEmpty()) {
								String[] arrowClickPath = xPath.split("/&/");
								String locator = arrowClickPath[0];
								click(locator, metaDataKey);
								String res = pluckValue(arrowClickPath[1], arrowClickPath[2], testData, arrowClickPath[3]);
								element = driver.findElement(By.xpath(".//li[text()='"+ res + "']"));
								actions.moveToElement(element).click().build().perform();
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("enterfhainfo")) {
							if (testData != "null" && !testData.isEmpty()) {
								FHAPage fhaPage = new FHAPage();
								fhaPage.addFHAInfo(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}   else if (metaDataVal != null && methodName.equals("getdropdownvalues")) {
							if (testData != "null" && !testData.isEmpty()) {
								Thread.sleep(1000);
								mylist.clear();
								listString = "";
								click(xPath, metaDataKey);
								String xPath1 = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
								listWebElements(xPath1, metaDataKey);
								String filename = Utils.properties.getProperty("HomeBridgeDropdownValues");
								if (listElems.size()>0) {
									for (int i = 0; i < listElems.size(); i++) {
										element = listElems.get(i);
										String dropdownValue = element.getText();
										mylist.add(dropdownValue);
									}
									System.out.println(mylist);
									listElems.clear();
									JavascriptExecutor js = (JavascriptExecutor) driver;
									String types1 = (String) js.executeScript("return JSON.stringify(Ext.pluck(Ext.pluck(Ext.getStore('RefLoanProgramTypes').data.items,'data'),'loanProgramTypeId'))");
									System.out.println(types1);
									copyLoanNumberToTextFile(filename, types1);
								}	else{
									String NoValues = "NoListFound";
									copyLoanNumberToTextFile(filename, NoValues);
								}
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null && methodName.equals("keyenter")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								Thread.sleep(500);
								clickOnKeyboardEnter(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}  else if (metaDataVal != null
								&& methodName.equals("enterval")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String text = String.valueOf(fieldDataValueMap.get(metaDataKey));
								enterText(xPath, text , metaDataKey);
								clickOnKeyboardEnter(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("dropdown")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String[] arrowClickPath = xPath.split("/&/");
								String locator = arrowClickPath[0];
								click(locator, metaDataKey);
								String locator1 = String.valueOf("(//li[text()='"+ String.valueOf(fieldDataValueMap.get(metaDataKey))+"'])"+ "[" + arrowClickPath[1] + "]");
								click(locator1, metaDataKey);

							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("clickdropdownfirstvalue")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String[] arrowClickPath = xPath.split("/&/");
								String locator = arrowClickPath[0];
								click(locator, metaDataKey);
								locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
								listWebElements(locator, metaDataKey);
								if (listElems.size()>0) {
									listElems.get(0).click();
								}


							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null	&& methodName.equals("waitforelement")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String text = String.valueOf(fieldDataValueMap.get(metaDataKey));
								String newValue = "'"+ String.valueOf(fieldDataValueMap.get(metaDataKey)) + "'";
								waitForElementWithText(xPath, newValue, metaDataKey , text);
								Thread.sleep(1000);
							} else{
								System.out.println(metaDataKey + " value is null");
							}

						} else if (metaDataVal != null	&& methodName.equals("waitforelemlocator")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								waitForElementWithLocator(xPath, metaDataKey);
								Thread.sleep(1000);
							} else{
								System.out.println(metaDataKey + " value is null");
							}

						}  else if (metaDataVal != null
								&& methodName.equals("attributes")) {
							startTime = System.nanoTime();
							String x = String.valueOf(fieldDataValueMap
									.get(metaDataKey));
							if (x != "null" && !x.isEmpty()) {
								element = driver.findElement(By.xpath(xPath));
								values = element.getAttribute("value");
								System.out.println(values);
								/*String[] percentValue = values.replace(".", ",").split(",");
								if (Integer.parseInt(percentValue[0])> 80) {
									System.out.println("value greater than 80");
									mortgagePercentage = true;
								}
								else{
									mortgagePercentage = false;			
								}*/
							} else {
								System.err.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null
								&& methodName.equals("enterConventionaMortgage")) {
							startTime = System.nanoTime();
							String x = String.valueOf(fieldDataValueMap
									.get(metaDataKey));
							if (x != "null" && !x.isEmpty()) {
								if (mortgagePercentage == true ) {
									driver.findElement(By.xpath("//a[@data-selenium-id='btnMortgageIns']")).click();
									String locator = "//span[text()='Conventional Mortgage Insurance']";
									element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
									driver.findElement(By.xpath("//input[@name='pmiPlanTypeId']")).sendKeys("Borrower Paid Monthly Premiums");	
									driver.findElement(By.xpath("//input[@name='terminationLtv']")).click();
									driver.findElement(By.xpath("//input[@name='pmiCompanyId']")).sendKeys("Essent Guarantee");	
									driver.findElement(By.xpath("//input[@name='terminationLtv']")).click();
									locator = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]/div[text()='Loading...']";
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
									driver.findElement(By.xpath("//a[@data-selenium-id='saveBtn']")).click();
									Thread.sleep(2000);
								}
							} else {
								System.err.println(metaDataKey + "null value");
							}
						}else if (metaDataVal != null && methodName.equals("altertab")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								clickOnKeyboarTab(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("clearfield")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								clearField(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("basevalue")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String[] arrowClickPath = xPath.split("/&/");
								String locator = String.valueOf("(//td/div[text()='"+ String.valueOf(fieldDataValueMap.get(metaDataKey))+"'])"+ "[" + arrowClickPath[1] + "]");
								click(locator, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("textbox")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String text = String.valueOf(fieldDataValueMap.get(metaDataKey));
								enterText(xPath, text, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("list")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
								listWebElements(locator, metaDataKey);
								if (listElems != null) {
									listElems.get(0).click();
									listElems.clear();
								}
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("path")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								String text = String.valueOf(fieldDataValueMap.get(metaDataKey));
								String path = Utils.properties.getProperty(text);
								//								File file = new File(path);
								enterTextWithoutClearing(xPath, path, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null	&& methodName.equals("javascript")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								clickOnElementUsingJavascript(xPath, metaDataKey);
								Thread.sleep(4000);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null	&& methodName.equals("selectassets")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								CRMAssetsPage assetsPage = new CRMAssetsPage();
								assetsPage.addAssets(xPath, testData, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null	&& methodName.equals("selectliability")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								LiabilityPage liabilityPage = new LiabilityPage();
								liabilityPage.addLiabilty(xPath, testData, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}   else if (metaDataVal != null	&& methodName.equals("selectemployment")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								EmploymentPage empPage = new EmploymentPage();
								empPage.addEmployment(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null	&& methodName.equals("selectincome")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								IncomePage incomePage = new IncomePage();
								incomePage.addIncome(xPath, testData, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}  else if (metaDataVal != null	&& methodName.equals("enterpurposeinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								PurposeAndPropertyPage purposeAndPropertyPage = new PurposeAndPropertyPage();
								purposeAndPropertyPage.addPurposeAndPropertyDetails(xPath,testData, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null	&& methodName.equals("enterloanfolderinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								LoanFoldersPage  loanFoldersPage = new LoanFoldersPage();
								loanFoldersPage.addLoanFolderDetails(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null	&& methodName.equals("enterscheduleloaninfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								ScheduleLoanForClosingpage scheduleLoanForClosingpage = new ScheduleLoanForClosingpage();
								scheduleLoanForClosingpage.addScheduleLoanForClosing(xPath, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null	&& methodName.equals("entercomplianceinfo")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								CompliancePage compliancePage = new CompliancePage();
								compliancePage.addComplianceDetails(xPath , metaDataKey);
								if (elemFound  == true) {
									break;
								}

							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null	&& methodName.equals("closewindows")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								closeAllWindowsExceptMainWindow(xPath,metaDataKey);
							} else{
								System.out.println(metaDataKey + " value is null");
							}

						} else if (metaDataVal != null	&& methodName.equals("clickonradomelement")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								listWebElements(xPath,metaDataKey);
								if (listElems.size()>0) {
									getElementFormList(listElems , listElems.size());
									element.click();
									Thread.sleep(200);
								}else{
									driver.findElement(By.xpath("//a[@data-selenium-id='btnClose']")).click();
								}
							} else{
								System.out.println(metaDataKey + " value is null");
							}

						}else if (metaDataVal != null	&& methodName.equals("losenterhousingexpense")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								LOSHousingExpensePage losHousingExpensePage = new LOSHousingExpensePage();
								losHousingExpensePage.addHousingExpensePageDetails(xPath, testData, metaDataKey);
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						} else if (metaDataVal != null && methodName.equals("span")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								locator = ".//li/span[contains(text(),'"
										+ String.valueOf(fieldDataValueMap
												.get(metaDataKey))
												+ "')]";
								click(locator, metaDataKey);

							} else {
								System.out.println(metaDataKey + " value is null");
							}
						}else if (metaDataVal != null	&& methodName.equals("validatecomplianceandbreak")) {
							startTime = System.nanoTime();
							if (testData != "null" && !testData.isEmpty()) {
								locator = "//div[@data-selenium-id='compAnalysisSummaryGrid']//div[@class='x-grid-empty']";
								elementFound(locator);
								if (elemFound == false) {
									break;
								}
							}else{
								System.out.println(metaDataKey + " value is null");
							}
						}
					} catch (Exception error) {
						results = false;
						resultsValue = "Fail";
						endTime = System.nanoTime();
						timeTaken = endTime - startTime;
						int milliSeconds = (int) (timeTaken / 1000000);
						seconds = (int) TimeUnit.MILLISECONDS.toSeconds(milliSeconds);
						System.err.println(metaDataKey + " issue at this field");
						extentTest.log(Status.FAIL, metaDataKey + " issue at this field : " + error.getMessage());	
						//						extentTest.log(Status.FAIL, error.getMessage());
						//						extentTest.log(Status.FAIL, new Throwable());
						System.out.println(seconds + " : loopTimeTaken");
						System.out.println();
						error.printStackTrace();
						/*String errors = error.getMessage();
						String[] logErrors = errors.split(",");
						Reporter.log(metaDataKey + ": Unable to find the element" + logErrors[0]);*/
					}
				} else {
					break;
				}
			}
			// }
		} finally {
			errorCount = Reporter.getOutput(Reporter.getCurrentTestResult()).size();
			System.err.println(" Error Count = " + errorCount);
			if (results == true) {
				resultsValue = "pass";
			}
		}
	}

}
