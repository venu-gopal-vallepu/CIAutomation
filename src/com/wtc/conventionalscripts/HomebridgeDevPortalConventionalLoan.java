package com.wtc.conventionalscripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.wtc.globalAccelerators.BaseClass;

public class HomebridgeDevPortalConventionalLoan extends BaseClass {

  	public static int getErrorCount() {
		return errorCount;
	}

	public static void setErrorCount(int errorCount) {
		HomebridgeDevPortalConventionalLoan.errorCount = errorCount;
	}

	public static String getScript_id() {
		return script_id;
	}

	public static void setScript_id(String script_id) {
		HomebridgeDevPortalConventionalLoan.script_id = script_id;
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		HomebridgeDevPortalConventionalLoan.filePath = filePath;
	}

	@BeforeSuite
	public void getTestCountFromExcel(ITestContext context) throws InterruptedException, IOException, InvalidFormatException {
		String className = this.getClass().getSimpleName();
		extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"\\Reports\\"+className+".html");
		extentReports = new com.aventstack.extentreports.ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		metaDataFile = "HomebridgeDevPortalConventionalLoanMetaData";
		dataFile = "HomebridgeDevPortalConventionalLoanData";
		String reportsPath = "Results";
		extentTest  = extentReports.createTest(reportsPath);
//		appReaderUtil.rowCount.add(0);
		getTestCount(context, metaDataFile, dataFile);
	}

	@BeforeMethod
	public void openBrowser() throws InvalidFormatException, IOException {
		String s[][] = getExcelValues();
		browser = s[0][4];
//		launchBrowser();
	}

	@Test
	public void testCRMAndLOS() throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException {
		startPoint = "";
		endPoint = "";
		String s[][] = getExcelValues();
		String PortalURL = s[0][1];
		String LpURL = s[1][1];
		PortalApp = true;
		LPApp = false;
		String crmLoanNumber = "";
		String lpNumber = "";  
		enterDataIntoApp(PortalURL, LpURL,crmLoanNumber,lpNumber,"schintak", "RuP901","schint", "RuP901",PortalApp,LPApp);
		Assert.assertEquals(results, true, currentRowNumber + " Testcase failed");
	}

	@AfterMethod
	public void getReports(ITestResult result) {
		getResults(result);
		driver.close();
	}
	@AfterSuite
	public void checkResult() {
		driver.quit();

	}
}


