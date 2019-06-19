package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wtc.globalAccelerators.ActionDriver;


public class Login extends ActionDriver {
	public void loginLendingHive()throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException {
		String location = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", location + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		actions = new  Actions(driver);
		wait = new WebDriverWait(driver, 30);
		driver.get("https://homebridge-dev.bluesageusa.com/lp");
		Thread.sleep(500);
		driver.findElement(By.name("userName")).sendKeys("schint");
		driver.findElement(By.name("password")).sendKeys("RuP901");
		driver.findElement(By.id("btnLogin-btnIconEl")).click();
		Thread.sleep(2000);
		WebElement loans=driver.findElement(By.xpath("//*[contains(text(),'Loans')]"));
		loans.click();
	}

	public void searchLP(String LNum) throws FileNotFoundException, InvalidFormatException, InterruptedException, IOException{
		loginLendingHive();
		String metaDataKey = "LP Page";
		locator = "//input[@name='searchLoans-inputEl']";
		waitForElementWithLocator(locator, metaDataKey);
		Thread.sleep(2000);
		enterText(locator, LNum, metaDataKey);
		clickOnKeyboardEnter(locator, metaDataKey);
		Thread.sleep(3000);
		locator = "//span[text()='Required Loan Actions']";
		waitForElementWithLocator(locator, metaDataKey);
	}

	public void PortalLogin() throws InterruptedException{
		PortalloginRelated(true);
	}

	private void PortalloginRelated(boolean flag) throws InterruptedException{
		String location = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", location + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://homebridge-dev.bluesageusa.com/portal");
		driver.manage().window().maximize();
		actions = new Actions(driver);
		driver.findElement(By.name("userName-inputEl")).sendKeys("schinth");
		driver.findElement(By.name("password-inputEl")).sendKeys("RuP901");
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(5000);
		actions = new  Actions(driver);
		wait = new WebDriverWait(driver, 30);
		locator = "//div[text()='Loans']";

		if(flag){
			element  = driver.findElement(By.xpath("(//span[text()='View Pipeline'])[2]"));
			element.click();
			Thread.sleep(3000);
		}

	}

	public void searchPortalLoan(String LoanNumber) throws InterruptedException{
		if (LoanNumber == ""||LoanNumber.isEmpty()) {
			PortalloginRelated(false);
		}else {
			PortalloginRelated(true);
			String metaDataKey = "EnterLoanNumber";
			locator = "//table[@data-selenium-id='loanNumberSearch']//input";
			enterText(locator, LoanNumber, metaDataKey);
			clickOnKeyboardEnter(locator, metaDataKey);
		}
	}

	public void crmLogout(){
		element = driver.findElement(By.xpath("//a[@data-selenium-id='btnUser']"));
		element.click();

		element = driver.findElement(By.xpath("//div[@data-selenium-id='btnLogout']"));
		element.click();
		locator = "//input[@name='userName-inputEl']";
		waitForElementWithLocator(locator, "logout page");	
	}

}
