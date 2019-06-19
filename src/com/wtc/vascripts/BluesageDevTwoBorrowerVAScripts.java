package com.wtc.vascripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.wtc.globalAccelerators.ActionDriver;
import com.wtc.globalAccelerators.Utils;

public class BluesageDevTwoBorrowerVAScripts extends ActionDriver {

	public static int getErrorCount() {
		return errorCount;
	}

	public static void setErrorCount(int errorCount) {
		BluesageDevTwoBorrowerVAScripts.errorCount = errorCount;
	}

	public static String getScript_id() {
		return script_id;
	}

	public static void setScript_id(String script_id) {
		BluesageDevTwoBorrowerVAScripts.script_id = script_id;
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		BluesageDevTwoBorrowerVAScripts.filePath = filePath;
	}

	public int currentRowNumber = 0;

	@BeforeSuite
	public void launchApp(ITestContext context) throws InterruptedException, IOException, InvalidFormatException {
		System.out.println(System.getProperty("user.dir"));
		extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/BluesageTwoBorrowerConventionalResults.html");
		extentReports = new com.aventstack.extentreports.ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		String s[][] = getExcelValues();
		script_id = "x_1";
		getWebdriverMethods("BluesageDevTwoBorrowerVALoanMetaData",
				"BluesageDevTwoBorrowerVALoanData");
		int dataListCount = dataList.size();

		ITestNGMethod currentTestMethod = null;
		for (ITestNGMethod testNGMethod : context.getAllTestMethods()) {
			if (testNGMethod.getInstance() == this) {
				currentTestMethod = testNGMethod;
				break;
			}
		}
		currentTestMethod.setInvocationCount(dataListCount);
	}

	@BeforeMethod
	public void openBrowser() throws InvalidFormatException, IOException {
		String s[][] = getExcelValues();
		try {
			String browser = s[0][4];
			if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", Utils.properties.getProperty("Chromedriverpath"));
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			} else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}
			extentReports.setSystemInfo("Browser", browser);
			resultsList.add(browser);
			String date = todayDate();
			resultsList.add(date);
			actions = new Actions(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("FinalTedst");
		}
	}

	public void loginLendingHive(HashMap<String, String> dataListMap)
			throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		String s[][] = getExcelValues();
		String URL = s[0][1];
		String[] urlEnvironment = URL.split("-");
		String[] appEnvironment = urlEnvironment[1].replace(".", ",").split(",");
		extentReports.setSystemInfo("Environment", appEnvironment[0]);
		resultsList.add(appEnvironment[0]);
		System.out.println(s[0][1]);
		driver.get(URL);
		driver.findElement(By.xpath(".//input[@name='userName-inputEl']")).sendKeys(s[0][2]);
		Thread.sleep(500);
		driver.findElement(By.xpath(".//input[@name='password-inputEl']")).click();
		driver.findElement(By.xpath(".//input[@name='password-inputEl']")).sendKeys(s[0][3]);
		Thread.sleep(500);
		element = driver.findElement(By.xpath("//span[text()='Login']"));
		actions.moveToElement(element).click().perform();
		List rowCountList = appReaderUtil.rowCount;
		processRequest(0, (Integer) rowCountList.get(0), dataListMap);
		actionMethods();
	}
	
	public void CRM(HashMap<String, String> dataListMap,int rowCount) throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException{
		String s[][] = getExcelValues();
		try {
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String URL = s[1][1];
			System.out.println(s[1][1]);
			driver.get(URL);
			driver.navigate().refresh();
			Thread.sleep(5000);
			driver.findElement(By.xpath(".//input[@name='userName']")).sendKeys(s[1][2]);
			driver.findElement(By.xpath(".//input[@name='password']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(".//input[@name='password']")).sendKeys(s[1][3]);
			Thread.sleep(1000);
			element = driver.findElement(By.xpath(".//span[text()='Login']"));
			actions.moveToElement(element).click().perform();
		} catch (InterruptedException e) {
			System.out.println("Tedst");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("FinalTedst");
		}
		if (script_id.length()>0) {
			script_id =  "x_2";

		} else {
			script_id = "x_2";
		}
		List rowCountList = appReaderUtil.rowCount;
		int x1=(Integer)rowCountList.get(0);
		int rowCount1 = (Integer)rowCountList.get(1);
		getWebdriverMethods("BluesageDevTwoBorrowerVALoanMetaData",
				"BluesageDevTwoBorrowerVALoanData");
		processRequest(x1,rowCount1,dataListMap);
		actionMethods();
	}

	@Test
	public void TestMethod() throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException {
		List rowCountList = appReaderUtil.rowCount;
		if (currentRowNumber < dataList.size()) {
			loginLendingHive(dataList.get(currentRowNumber));
			CRM(dataList.get(currentRowNumber),(Integer)rowCountList.get(0));
			currentRowNumber++;
			if (results == true) {
				resultsList.add(String.valueOf("Passed"));
			} else {
				resultsList.add(String.valueOf("Passed"));
			}
			String fileName = Utils.properties.getProperty("TestLoanNumbers");
			copyTestResultsToExcel(fileName);
			String text = String.valueOf(fieldDataValueMap.get("Product"));
			extentTest = extentReports.createTest(text, "This is to verify test reports");
			resultsList.clear();
			Assert.assertEquals(results, true, currentRowNumber + "nd Testcase failed");
		}
	}

	@AfterMethod
	public void closeBrowser(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Passed", ExtentColor.GREEN));
		}
		driver.quit();
	}

	@AfterSuite
	public void checkResult() {
		extentReports.flush();
	}
}
