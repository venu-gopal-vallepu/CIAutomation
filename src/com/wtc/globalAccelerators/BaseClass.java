package com.wtc.globalAccelerators;

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
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pages.Login;

public class BaseClass extends ActionDriver {

	public int currentRowNumber = 0;

	public void getTestCount(ITestContext context, String metaDataFile , String dataFile) throws InterruptedException, IOException, InvalidFormatException {
		System.out.println(System.getProperty("user.dir"));
		String s[][] = getExcelValues();
		script_id = "x_1";
		getWebdriverMethods(metaDataFile,dataFile);
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

	public void launchBrowser() throws InvalidFormatException, IOException {
		try {
			if (browser.equals("chrome")) {
				String location = System.getProperty("user.dir");
				System.setProperty("webdriver.chrome.driver", location + "\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			} else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}
			actions = new Actions(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("FinalTedst");
		}
	}

	public void EnterCRMStartAndEndPoints(HashMap<String, String> dataListMap) throws InvalidFormatException, IOException, InterruptedException {
		List rowCountList = appReaderUtil.rowCount;
		if (startPoint == "" || startPoint.isEmpty()) {

			startPoint = Utils.properties.getProperty("BorrowersPage");
		}
		if (endPoint == "" || endPoint.isEmpty()) {
			endPoint = Utils.properties.getProperty("RatiosPage");
		}
		script_id = "x_"+endPoint;
		getWebdriverMethods(metaDataFile, dataFile);
		int x1=(Integer)rowCountList.get(Integer.parseInt(startPoint)-1);
		int rowCount1 = (Integer)rowCountList.get(Integer.parseInt(endPoint));
		processRequest(x1,rowCount1,dataListMap);
	}

	public void EnterLOSStartAndEndPoints(HashMap<String, String> dataListMap) throws InvalidFormatException, IOException, InterruptedException {
		List rowCountList = appReaderUtil.rowCount;
		if (startPoint == "" || startPoint.isEmpty()) {
			startPoint = Utils.properties.getProperty("LOSAssetsPage");
		}
		if (endPoint == "" || endPoint.isEmpty()) {
			endPoint = Utils.properties.getProperty("LOSLogOutPage");
		}
		script_id = "x_"+endPoint;
		getWebdriverMethods(metaDataFile, dataFile);
		int x1=(Integer)rowCountList.get(Integer.parseInt(startPoint)-1);
		int rowCount1 = (Integer)rowCountList.get(Integer.parseInt(endPoint));
		processRequest(x1,rowCount1,dataListMap);
	}


	public void CRM(HashMap<String, String> dataListMap, String URL,String loanNumber, String userName, String password)
			throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException {
		Login login = new Login();
		login.searchPortalLoan(loanNumber); 

		if (keyValue == true) {
			EnterCRMStartAndEndPoints(dataListMap);
		}else {
			getWebdriverMethods(metaDataFile, dataFile);
			List rowCountList = appReaderUtil.rowCount;
			processRequest(0,(Integer)rowCountList.get(0),dataListMap);
		}
		actionMethods();
	}

	public void lendingPlatform(HashMap<String, String> dataListMap,int rowCount, String URL, String LoanNumber, String userName, String password) throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException{
		Login login = new Login();
		if (PortalApp == false && LPApp == true) {
			login.searchLP(LoanNumber);
		}else {
			String s[][] = getExcelValues();
			try {
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				driver.get(URL);
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
		}

		if (script_id.length()>0) {
			script_id =  "x_2";

		} else {
			script_id = "x_2";
		}

		if (keyValue == true) {
			EnterLOSStartAndEndPoints(dataListMap);
		}else {
			getWebdriverMethods(metaDataFile, dataFile);
			List rowCountList = appReaderUtil.rowCount;
			int x1=(Integer)rowCountList.get(0);
			int rowCount1 = (Integer)rowCountList.get(1);
			processRequest(x1,rowCount1,dataListMap);
		}
		actionMethods();
	}

	public void enterDataIntoApp(String URL1, String URL2, String crmLoanNumber , String lpNumber, String CRMuserName, String CRMpassword, String LPuserName, String LPpassword, boolean CRMflag , boolean LPflag) throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException {
		List rowCountList = appReaderUtil.rowCount;
		if (currentRowNumber < dataList.size()) {
			if (CRMflag) {
				CRM(dataList.get(currentRowNumber),URL1,crmLoanNumber, CRMuserName, CRMpassword);
			}

			if (LPflag) {
				lendingPlatform(dataList.get(currentRowNumber),(Integer)rowCountList.get(0),URL2,lpNumber,LPuserName, LPpassword);
			}
			currentRowNumber++;

		}
	}

	public void getResults(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Passed", ExtentColor.GREEN));
		}
		extentReports.flush();
	}

}
