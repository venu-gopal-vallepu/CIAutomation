package com.wtc.globalAccelerators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIElementMethods {
	public static WebDriver driver;
	public static Properties properties;
	public static String script_id = "";
	public static String filePath = "";
	public static int errorCount = 0;
	public static String[] LoanNumber;
	public String valueofScriptid = script_id;
	public static Actions actions;
	public static WebDriverWait wait;
	public static WebElement element;
	public static boolean results;
	public static boolean elemFound;
	public static String purposeAndPropertyState;
	public Date dateobj;
	public String key;
	public String loannumber1;
	public SimpleDateFormat df;
	public Set keys;
	public long endTime;
	public long startTime;
	public long timeTaken;
	public boolean PortalApp;
	public boolean LPApp;
	public boolean mortgagePercentage;
	public long loanValue;
	public int seconds;
	public static String number;
	public static String values;
	public int dataListNum;
	public String listString = "";
	public static int rowNumber = 0;
	public Random  rand;
	public String randomValue;
	public String metaDataFile;
	public String dataFile;
	public String browser;
	public String  startPoint;
	public String endPoint;
	public static boolean keyValue;
	public static String resultsValue;
	public List<WebElement> listElems;
	public JavascriptExecutor executor;
	public boolean intialDisclosureFound = true;
	public String locator ; 
	public String checkMaskLoad = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]/div[text()='Loading...']";
	public ArrayList<String> mylist = new ArrayList<String>();
	public ArrayList<String> resultsList = new ArrayList<String>();

	public void elementFound(String xPath){
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		elemFound = true;
		elemFound = driver.findElements(By.xpath(xPath)).size()>0;
	}

	public void clearField(String xPath , String metaDataKey) {
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
				element.clear();
			}
			else{
				System.out.println(metaDataKey + " element is disabled");
			}
		} else {
			System.out.println(metaDataKey + " element is not found");
		}
	}
	public void enterText(String xPath, String text , String metaDataKey) {
		elementFound(xPath);
		if (elemFound == true ) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				try {
					element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
					element.clear();
					actions.moveToElement(element).sendKeys(text).perform();
				} catch (Exception e) {
					results = false;
					e.printStackTrace();
				}
			}
			else{
				System.out.println(metaDataKey + " element is disabled");
			}
		} else {
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public void enterTextWithoutClearing(String xPath, String text , String metaDataKey) {
		elementFound(xPath);
		if (elemFound == true ) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				try {
					element = driver.findElement(By.xpath(xPath));
					element.sendKeys(text);
				} catch (Exception e) {
					results = false;
					e.printStackTrace();
				}
			}
			else{
				System.out.println(metaDataKey + " element is disabled");
			}
		} else {
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public void enterPropertyDetails(String xPath, String metaDataKey)
			throws InterruptedException {
		String[] arrowClickPath = xPath.split("/&/");
		String[] propertyType = { "Investment-Detached", "Investment-Attached","Investment-Detached Condo", "Investment-HighRise Condo","Investment-actured Home",
				"Investment-Manufactured Home Condo", "Investment-MH Select","Investment-Planned Unit Development","Investment-Condominium", "Primary Residence-Detached",
				"Primary Residence-Attached","Primary Residence-Detached Condo","Primary Residence-HighRise Condo","Primary Residence-Manufactured Home",
				"Primary Residence-Manufactured Home Condo","Primary Residence-MH Select","Primary Residence-Planned Unit Development","Primary Residence-Condominium", 
				"Second Home-Detached","Second Home-Attached", "Second Home-Detached Condo","Second Home-HighRise Condo", "Second Home-Manufactured Home",
				"Second Home-Manufactured Home Condo", "Second Home-MH Select","Second Home-Planned Unit Development","Second Home-Condominium"};
		getRandomValueFromArray(propertyType, propertyType.length);
		String[] propertyDetails = randomValue.split("-");
		element = driver.findElement(By.xpath(arrowClickPath[0]));
		element.clear();
		//		element.sendKeys(propertyDetails[0]);
		element.sendKeys("Primary Residence");
		element = driver.findElement(By.xpath(arrowClickPath[1]));
		element.click();
		Thread.sleep(500);
		element = driver.findElement(By.xpath(arrowClickPath[2]));
		element.clear();
		//		element.sendKeys(propertyDetails[1]);
		element.sendKeys("Manufactured Home");
		element = driver.findElement(By.xpath(arrowClickPath[1]));
		element.click();
		Thread.sleep(500);
	}

	public void click(String xPath , String metaDataKey) {
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
				actions.moveToElement(element).click().build().perform();
			}else{
				System.out.println(metaDataKey + " element is disabled");
			}
		}else {
			System.out.println(metaDataKey + " element is not found");
		}

	}

	public void checkElementLoadMask(String xPath ,String text, String metaDataKey) throws InterruptedException{
		elementFound(xPath);
		if (elemFound == true) {
			wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(xPath), text));
			Thread.sleep(500);
			System.out.println(metaDataKey  + " is invisible");
		}else {
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public String todayDate(){
		df = new SimpleDateFormat("MM/dd/yyyy");
		dateobj = new Date();
		System.out.println("Current Date " + df.format(dateobj) );
		return String.valueOf(df.format(dateobj));
	}

	public String getRandomValueFromArray(String[] array, int value){
		rand = new Random();
		int index = rand.nextInt(value);
		randomValue = array[index];
		return randomValue;
	}

	public String getRandomValuefromList(List<String> list , int value){
		rand = new Random();
		int index = rand.nextInt(value);
		randomValue = list.get(index);
		return randomValue;
	}

	public WebElement getElementFormList(List<WebElement> list , int value){
		rand = new Random();
		int index = rand.nextInt(value);
		element =  list.get(index);
		return element;
	}

	public void selectRandomValueFromDropdown(String xPath, String metaDataKey ) throws InterruptedException{
		click(xPath, metaDataKey);
		locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
		mylist.clear();
		listWebElements(locator, locator);
		if (listElems.size()>0) {
			getElementFormList(listElems,listElems.size());
			element.click();
			Thread.sleep(500);
		}
	}

	public void selectDropdownValueByText(String xPath, String metaDataKey, String text ){
		click(xPath, metaDataKey);
		locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li[text()='"+ text+"']";
		click(locator, metaDataKey);
	}

	public 	void selectDropdownFirstValue(String xPath, String metaDataKey ){
		click(xPath, metaDataKey);
		locator = "//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li";
		listWebElements(locator, locator);
		if (listElems.size()>0) {
			listElems.get(0).click();
		}
	
	}
	public void addDate(String xPath , String text , String metaDataKey ){
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				waitForElementWithLocator(xPath, metaDataKey);
				todayDate();
				Calendar c = Calendar.getInstance();
				c.setTime(dateobj);
				c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(text));
				String newDate = df.format(c.getTime());
				System.out.println("Added date " + newDate);

				if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					c.add(Calendar.DAY_OF_MONTH, 1);
					System.out.println("after sunday  date " + newDate);
				}

				if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
					c.add(Calendar.DAY_OF_MONTH, 2);
					System.out.println("after saturday date " + df.format(c.getTime()));
				}

				newDate = df.format(c.getTime());

				String[] holiday = { "11/23/2017", "12/25/2017", "1/1/2018" };

				for (int i = 0; i < holiday.length; i++) {
					if (newDate.equalsIgnoreCase(holiday[i])) {
						String[] holDate = holiday[i].split("/");
						int year = Integer.parseInt(holDate[2]);
						int date = Integer.parseInt(holDate[1]);
						int month = Integer.parseInt(holDate[0]);
						Date date1 = new Date(holiday[i]);
						c.setTime(date1);
						System.out.println("After comparing holiday string " + df.format(c.getTime()));
						c.add(Calendar.DAY_OF_MONTH, 1);
						newDate = df.format(c.getTime());
						System.out.println("Next day of holiday " + newDate);
					}
				}
				enterText(xPath, newDate, metaDataKey);
				System.out.println(newDate);

			}else{
				System.out.println(metaDataKey + " element is disabled");
			}

		}else{
			System.out.println(metaDataKey + " element is not found");
		}

	}

	public void addMonth(String xPath , String text , String metaDataKey ){
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				Calendar now = Calendar.getInstance();
				String date = (now.get(Calendar.MONTH) + 1)
						+ "/"
						+ now.get(Calendar.DATE)
						+ "/"
						+ now.get(Calendar.YEAR);

				System.out.println(date);
				now.add(Calendar.MONTH,1);
				enterText(xPath, text, metaDataKey);
			}else{
				System.out.println(metaDataKey + " element is disabled");
			}
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public void scroll(String xPath, String metaDataKey ){
		elementFound(xPath);
		if (elemFound == true) {
			element = driver.findElement(By.xpath(xPath));
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView()",
					element);
		}else{
			System.out.println(metaDataKey + " element is not found");
		}

	}

	public void doubleClick(String xPath, String metaDataKey ){
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
				actions.moveToElement(element).doubleClick().build().perform();
			}else{
				System.out.println(metaDataKey + " element is disabled");
			}
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public String getElemText(String xPath, String metaDataKey ){
		elementFound(xPath);
		String text = null;
		if (elemFound == true) {
				element = driver.findElement(By.xpath(xPath));
				text = element.getText();
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
		return text;
	}

	public void getNumberFromText(String text){
		LoanNumber = text.split(" ");
		for (int i = 0; i < LoanNumber.length; i++) {
			try {
				loanValue = Long.parseLong(LoanNumber[i]);
				number = String.valueOf(loanValue);
			} catch (NumberFormatException e) {
				System.out.println(loanValue + " Loan text");
			}
		}
	}

	public String getAttributeValue(String xPath, String metaDataKey){
		elementFound(xPath);
		if (elemFound == true) {
				element = driver.findElement(By.xpath(xPath));
				values = element.getAttribute("value");
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
		return values;
	}

	public void writeExcelResults(String fileName ,String xPath,  String metaDataKey){
		elementFound(xPath );
		try {
			int columnCount = 0;
			FileInputStream stream = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(stream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();
			XSSFRow row = sheet.createRow(++rowCount);
			XSSFCell cell = row.createCell(columnCount);
			cell.setCellValue(resultsValue);
			if (elemFound == true) {
				cell.setCellValue("Pass");
			} else {
				cell.setCellValue("Fail");
			}
			stream.close();
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			System.out.println(metaDataKey + " issue at this field");
			e.printStackTrace();
		}
	}

	public static String pluckValue(String storeId, String searchField, String searchValue, String pluckField){
		String script = "Ext.pluck("
				+ "Ext.Array.filter(Ext.pluck(Ext.getStore('"+ storeId +"').data.items,'data'), "
				+ "function(item) {"
				+ "return item."+ searchField +" === '"+ searchValue +"';"
				+ "}), "
				+ "'"+ pluckField +"');";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> result = (ArrayList<String>) js.executeScript("return " + script);
		String output = result.size() > 0 ? result.get(0) : null;
		System.out.println("Plucked Value " + output);
		return output;
	}

	public static void copyLoanNumberToTextFile(String filePath , String data) {
		File file = null;
		FileWriter writer = null;
		BufferedWriter out = null;
		try {
			file = new File(filePath);
			writer = new FileWriter(file, true);
			out = new BufferedWriter(writer);
			if (file.length() > 0) {
				out.newLine();
				out.write(data);
			} else {
				out.newLine();
				out.write(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (writer != null)
					writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public List<WebElement> listWebElements(String xPath,String metaDataKey){
		elementFound(xPath);
		if (elemFound == true) {
			if (listElems != null) {
				listElems.clear();
			}
			listElems = driver.findElements(By.xpath(xPath));
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
		return listElems;

	}

	public void countAndClick(String xPath,String metaDataKey){
		listWebElements(xPath, metaDataKey);
		for(WebElement elem : listElems) {
			wait.until(ExpectedConditions.visibilityOf(elem));
			System.out.println(elem);
			elem.click();
		}
	}

	public void clickOnMaskAndEnterValue(String locator1 ,String locator2, String locator3, String text , String metaDataKey) throws InterruptedException{
		listWebElements(locator1, metaDataKey);
		for (int i = 0; i < listElems.size(); i++) {
			Thread.sleep(300); 
			listElems.get(i).click();
			Thread.sleep(200); 
			click(locator2, metaDataKey);
			enterText(locator2, text, metaDataKey);
			Thread.sleep(200); 
			click(locator3, metaDataKey);
		}
	}

	public void clickOnKeyboardEnter(String xPath,String metaDataKey){
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				element = driver.findElement(By.xpath(xPath));
				element.sendKeys(Keys.ENTER);

			}else{
				System.out.println(metaDataKey + " element is disabled");
			}
		}else{
			System.out.println(metaDataKey + " element is not found");
		}

	}

	public  void enterAdressAndZip(String xPath , String metaDataKey) throws InterruptedException{
		String[] arrowClickPath = xPath.split("/&/");
		waitForElementWithLocator(arrowClickPath[0], metaDataKey);
		String[] completeAdress = { "99508-1715 Birchwood St","99515-320 W 121st Ave", "99712-735 Aspen Heights Dr", "99508-1921 Norene St", "35811-240 Grasslands Rd",
				"35094-8608 Cedar Springs Cir", "36330-74 County Road 270","35206-7205 4th Ave S", "72417-121 Oak Meadow Cir","71922-2504 Hickory Grove Rd", "85234-2472 E Gemini St",
				"85377-12903 W Castlebar Dr", "93535-45704 Palm Ln","94089-1063 Morse Ave", "80113-4125 S Grant St","80024-10190 Wadsworth Blvd", "06370-4 Texas Dr",
				"06825-12 May St", "20032-800 Southern Ave Se",	"20020-2110 T St Se", "19963-377 Bay Ave","19951-30738 Molly B Rd", "32086-362 Rosa Ct",
				"32738-2245 Dumas Dr", "30549-85 Williamson St","30741-718 Cross St", "66441-2619 Paige Ln","67205-11008 W Greenspoint St", "82009-5535 Snowberry Dr",
				"82602-6500 W Riverside Ter", "26554-592 Pricketts Fort Rd","26451-533 Charles St", "53711-5846 Tree Line Dr","54458-586 Church Ave", "99403-2360 Valleyview Dr",
				"98552-950 State Route 109", "05663-23 Cole Ave","23410-1613 Flammarion Ct", "22630-1465 Canterbury Rd","84004-816 E Village Way", "84120-4385 W Sunnyvale Dr",
				"77710-4446 Spring Meadows Dr", "77338-6510 Twain Mark Ln","37087-226 Woolard St", "38556-349 Colditz Rd","57101-6401 W Bonnie Ct", "57325-200 Terrace Rd",
				"29461-238 White St", "29102-509 Skyview Dr","02863-81 Tremont St", "02822-37 West Shore Dr","42302-2340 Bulfinch Ave", "42408-17615 Cord Hamby Rd",
				"71207-957 Angus Rd", "71456-168 Riverside Ln","02780-16 Herbert St", "01247-30 W Shaft Rd Ter","21771-5480 Ross Ct", "20901-506 Stirling Rd",
				"04216-100 N Main St", "04401-124 W Broadway","48242-17527 Garfield", "48168-40593 N Northville Trl","55371-3774 Turner Dr Sw", "55115-1344 Echo Lake Ct",
				"63365-24 Hollywood Dr", "63123-4351 Mohegan Dr","39402-20 Katherine Cir", "38868-154 Shannon Ave","59759-24 Royal Elk Rd", "59803-2300 Larch Camp Rd",
				"27302-4603 Yarboroughs Mill Rd", "27406-6904 Woodhue Dr","58561-240 Main Ave", "58504-27 Custer Dr", "68847-255 M Ave","68803-663 Faidley Ct", "03077-10 Floral Ave",
				"03104-33 Whig Dr", "72223-4615 Old Oak Dr","35212-1336 Mccoy St", "92109-852 Thomas Ave","20019-3947 Burns Pl Se", "96732-275 Mohalu St",
				"60463-12532 S Melvina Ave", "47283-404 W Mulberry St","01056-131 Williams St", "48224-3968 Chatsworth St","55408-3455 Blaisdell Ave", "07042-52 Fairfield St",
				"03768-57 Goose Pond Rd", "88081-443 Ralph St","89432-550 Stanford Way", "58502-1308 Ridgeview Ln","12701-107 Cold Spring Rd", "97016-77285 Woodson Rd",
				"02886-296 Hunt Ave", "84058-1575 Sandhill Rd","05836-18 Foster Ter", "98368-870 Martin Rd","35811-240 Grasslands Rd", "85344-37241 S Buckskin Cir",
				"94536-990 Knollwood Dr", "07670-27 Peter Lynas Ct","27803-109 Robert Ryan Ct", "77069-5207 Court Of York","77401-4804 Palm St", "77328-357 County Road 3997",
				"77023-1721 Idylwood Dr","77075-10419 Clearwood Crossing Blvd","79915-7619 Monterrey Dr", "07042-52 Fairfield St","03768-57 Goose Pond Rd", "88011-443 Ralph St",
				"89432-550 Stanford Way", "58502-1308 Ridgeview Ln","12701-107 Cold Spring Rd", "97011-77285 Woodson Rd","02886-296 Hunt Ave", "84058-1575 Sandhill Rd",
				"05830-18 Foster Ter", "98368-870 Martin Rd" };
		// rand = new Random();
		getRandomValueFromArray(completeAdress , completeAdress.length - 1);
		String[] adressWithZip = randomValue.split("-");
		element = driver.findElement(By.xpath(arrowClickPath[0]));
		element.clear();
		element.sendKeys(adressWithZip[1]);
		element = driver.findElement(By.xpath(arrowClickPath[1]));
		element.clear();
		element.sendKeys(adressWithZip[0]);
		locator = "//div[not(contains(@style,'display: none;'))]/div[contains(@class,'x-mask-loading')]/div[text()='Loading...']";
		elementFound(locator);
		if (elemFound == true) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
		}
		element = driver.findElement(By.xpath(arrowClickPath[2]));
		boolean verifyCity = element.getAttribute("value").length()>0;
		/*wait = new WebDriverWait(driver, 3);
		verifyCity = wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));*/
		System.out.println("value.................." + verifyCity);
		if (verifyCity == false) {
			System.out.println("xxxxxx.................." + verifyCity);
			List<WebElement> elems = driver.findElements(By.xpath(
					"//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li"));
			System.out.println(elems.get(0).getText());
			Thread.sleep(300);
			elems.get(0).click();
			elems.clear();
		}
	}

	public static void enterZipCode(String path , String cityPath , String Value) throws InterruptedException{
		element = driver.findElement(By.xpath(path));
		element.clear();
		element.sendKeys(Value);
		System.out.println(cityPath);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean test= true;
		element  = driver.findElement(By.xpath(cityPath));
		wait = new WebDriverWait(driver, 5);
		test = wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
		if (test == false) {
			List<WebElement> elems = driver.findElements(By.xpath(
					"//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li"));
			System.out.println(elems.get(0).getText());
			elems.get(0).click();
			elems.clear();
		}
		/*	
		element.sendKeys(Keys.CONTROL , "a");
		Thread.sleep(500);*/

	}

	public void clickOnKeyboarTab(String xPath,String metaDataKey){
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				element = driver.findElement(By.xpath(xPath));
				element.sendKeys(Keys.TAB);

			}else{
				System.out.println(metaDataKey + " element is disabled");
			}
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public void copyTestResultsToExcel(String fileName ){
		try {
			int columnCount = 0;
			FileInputStream istream = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(istream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();
			XSSFRow row = sheet.createRow(++rowCount);
			XSSFCell cell ;
			for (int i = 0; i < resultsList.size(); i++) {
				cell = row.createCell(++columnCount);
				cell.setCellValue(resultsList.get(i));
			}
			//			istream.close();
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeAllWindowsExceptMainWindow(String xPath, String metaDataKey) throws InterruptedException {
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String mainWindowhandle = driver.getWindowHandle();
				System.out.println("mainWindowhandle : " + mainWindowhandle);
				click(xPath, metaDataKey);
				Thread.sleep(6000);
				Set<String> allWindowHandles = driver.getWindowHandles();
				allWindowHandles.remove(mainWindowhandle);
				System.out.println(allWindowHandles.size());
				for (String windows: allWindowHandles) {
					driver.switchTo().window(windows);
					driver.close();
				}
				driver.switchTo().window(mainWindowhandle);

			}else{
				System.out.println(metaDataKey + " element is disabled");
			}
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
	}
	
	public void waitForElementWithText(String xPath, String value , String metaDataKey, String text){
		elementFound(xPath);
		if (elemFound == true) {
			Pattern pattern = Pattern.compile("\'([^\']*)\'");
			String newXpath = xPath.replaceAll(String.valueOf(pattern), value);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(newXpath), text));
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public void CompareAndBreak(String xPath, String metaDataKey, String text){
		elementFound(xPath);
		if (elemFound == true) {
			getAttributeValue(xPath, metaDataKey);
			if (values.equals(text)) {
				System.out.println(metaDataKey + "  InitialDisclose value found ");
			}
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public void waitForElementWithLocator(String xPath, String metaDataKey  ){
			wait = new WebDriverWait(driver, 60);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
	}

	public void clickOnElementUsingJavascript(String xPath , String metaDataKey){
		elementFound(xPath);
		if (elemFound == true) {
			if (driver.findElement(By.xpath(xPath)).isEnabled()) {
				element = driver.findElement(By.xpath(xPath));
				executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();",element);
			}else{
				System.out.println(metaDataKey + " element is disabled");
			}
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public void checkIfCityExists(String xPath , String metaDataKey){
		elementFound(xPath);
		if (elemFound == true) {
			element = driver.findElement(By.xpath(locator));
			boolean verifyCity  = true;
			verifyCity = element.getAttribute("value").length()>0;
			if (verifyCity == false) {
				List<WebElement> elems = driver.findElements(By.xpath(
						"//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li"));
				System.out.println(elems.get(0).getText());
				elems.get(0).click();
				elems.clear();
			}
		}else{
			System.out.println(metaDataKey + " element is not found");
		}
	}

	public void getTextAndEnterValue(String xPath , String metaDataKey){
		startTime = System.nanoTime();
		String[] arrowClickPath = xPath.split("/&/");
		element = driver.findElement(By.xpath(arrowClickPath[0]));
		String text = element.getText();
		String exceptionValue;
		/*if (text.contains("-")) {
			exceptionValue = text.substring(1);
		}else{
			exceptionValue = "-"+text;
		}*/

		driver.findElement(By.xpath(arrowClickPath[1]))
		.sendKeys(text);
	}

	public void getAttributeAndEnterValue(String xPath , String metaDataKey){
		startTime = System.nanoTime();
		String[] arrowClickPath = xPath.split("/&/");
		element = driver.findElement(By.xpath(arrowClickPath[0]));
		String text = element.getAttribute("value");
		element = driver.findElement(By.xpath(arrowClickPath[1]));
		element.sendKeys(text);
	}

	public  void conventionalProductDetails(String xPath , String metaDataKey) throws InterruptedException{
		String[] product = {
				//				"Conventional Mortgage-Conforming-Adjustable Rate-Conv Conforming 30 yr 7/1 ARM-Fannie Mae-Purchase-Novalue--410000-210000",
				//				"Conventional Mortgage-Conforming-Adjustable Rate-Conv Conforming 30 yr 3/1 ARM-Fannie Mae-Purchase-Novalue-410000-310000",
				//				"Conventional Mortgage-Conforming-Adjustable Rate-Conv Conforming 30 yr 10/1 ARM-Fannie Mae-Purchase-Novalue-350000-210000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 10 yr Fixed-Fannie Mae-Purchase-Novalue-410000-240000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 15 yr Fixed-Fannie Mae-Purchase-Novalue-410000-230000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 20 yr Fixed-Fannie Mae-Purchase-Novalue-410000-320000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 25 yr Fixed-Fannie Mae-Purchase-Novalue-410000-300000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 30 yr Fixed-Fannie Mae-Purchase-Novalue-410000-290000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 10 yr Fixed -Fannie Mae-Purchase-Novalue-410000-240000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 15 yr Fixed -Fannie Mae-Purchase-Novalue-410000-230000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 20 yr Fixed -Fannie Mae-Purchase-Novalue-410000-320000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 25 yr Fixed -Fannie Mae-Purchase-Novalue-410000-300000",
				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 30 yr Fixed -Fannie Mae-Purchase-Novalue-410000-290000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 10 yr Fixed-Fannie Mae-Purchase-Novalue-410000-240000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 15 yr Fixed-Fannie Mae-Purchase-Novalue-410000-230000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 20 yr Fixed-Fannie Mae-Purchase-Novalue-410000-320000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 25 yr Fixed-Fannie Mae-Purchase-Novalue-410000-300000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Construction Conforming 30 yr Fixed-Fannie Mae-Purchase-Novalue-410000-290000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-HB One Time Close Conforming 10 yr Fixed-Fannie Mae-Purchase-Novalue-410000-240000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-HB One Time Close Conforming 15 yr Fixed-Fannie Mae-Purchase-Novalue-410000-230000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-HB One Time Close Conforming 20 yr Fixed-Fannie Mae-Purchase-Novalue-410000-320000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-HB One Time Close Conforming 25 yr Fixed-Fannie Mae-Purchase-Novalue-410000-300000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-HB One Time Close Conforming 30 yr Fixed-Fannie Mae-Purchase-Novalue-410000-290000",
				//				"Conventional Mortgage-Jumbo-Fixed Rate-Jumbo 10 yr Fixed-Chase Jumbo-Purchase-Novalue-860000-540000",
				//				"Conventional Mortgage-Jumbo-Fixed Rate-Jumbo 10 yr Fixed-Chase Jumbo-Purchase-Novalue-860000-530000",
				//				"Conventional Mortgage-Jumbo-Fixed Rate-Jumbo 20 yr Fixed-Chase Jumbo-Purchase-Novalue-860000-520000",
				//				"Conventional Mortgage-Jumbo-Fixed Rate-Jumbo 25 yr Fixed-Chase Jumbo-Purchase-Novalue-860000-500000",
				//				"Conventional Mortgage-Jumbo-Fixed Rate-Jumbo 30 yr Fixed-Chase Jumbo-Purchase-Novalue-860000-590000",
				//				"Conventional Mortgage-Conforming-Adjustable Rate-Conv Conforming 30 yr 7/1 ARM-Fannie Mae-Refinance-No Cash Out-410000-310000",
				//				"Conventional Mortgage-Conforming-Adjustable Rate-Conv Conforming 30 yr 5/1 ARM-Fannie Mae-Refinance-No Cash Out-410000-310000",
				//				"Conventional Mortgage-Conforming-Adjustable Rate-Conv Conforming 30 yr 3/1 ARM-Fannie Mae-Refinance-No Cash Out-410000-310000",
				//				"Conventional Mortgage-Conforming-Adjustable Rate-Conv Conforming 30 yr 10/1 ARM-Fannie Mae-Refinance-No Cash Out-410000-310000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 10 yr Fixed -Fannie Mae-Refinance-No Cash Out-410000-310000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 15 yr Fixed -Fannie Mae-Refinance-No Cash Out-410000-310000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 20 yr Fixed -Fannie Mae-Refinance-No Cash Out-410000-310000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 25 yr Fixed -Fannie Mae-Refinance-No Cash Out-410000-310000",
				//				"Conventional Mortgage-Conforming-Fixed Rate-Conv Conforming 30 yr Fixed -Fannie Mae-Refinance-No Cash Out-410000-310000"
		};
		////input[@name='loanAmt']
		String[] arrowClickPath = xPath.split("/&/");

		getRandomValueFromArray(product , product.length);
		String[] mortgageDetails = randomValue.split("-");

		//		input[@name='mortgageTypeId']/../following-sibling::td/div/&/
		click(arrowClickPath[0] , metaDataKey);
		locator = "(.//li[text()='" + mortgageDetails[0] + "'])" ;
		click(locator, metaDataKey);

		//input[@name='pricingTierId']/../following-sibling::td/div/&/
		click(arrowClickPath[1] , metaDataKey);
		locator = "(.//li[text()='" + mortgageDetails[1] + "'])" ;
		click(locator, metaDataKey);

		//input[@name='amortizationTypeId']/../following-sibling::td/div/&/
		click(arrowClickPath[2] , metaDataKey);
		locator = "(.//li[text()='" + mortgageDetails[2] + "'])" ;
		click(locator, metaDataKey);
		Thread.sleep(300);

		//input[@name='productPricingTemplateId']/../following-sibling::td/div/&/
		click(arrowClickPath[3] , metaDataKey);
		locator = "(.//li[text()='" + mortgageDetails[3] + "'])" ;
		System.out.println("product pricing " + locator);
		click(locator, metaDataKey);
		Thread.sleep(300);
		//input[@name='investorId']/../following-sibling::td/div/&/
		wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='Processing']")));

		click(arrowClickPath[4] , metaDataKey);
		locator = "(.//li[text()='" + mortgageDetails[4] + "'])" ;
		click(locator, metaDataKey);
		Thread.sleep(300);

		locator = "//input[@name='amortizationTypeId']";
		getAttributeValue(locator, metaDataKey);

		//		String z = "Adjustable Rate";

		if (values.equals("Adjustable Rate")) {
			locator = "//input[@name='armPlanId']/../following-sibling::td/div";
			click(locator, metaDataKey);
			List<WebElement> elems = driver.findElements(By.xpath(
					"//div[contains(@class,'x-boundlist x-boundlist-floating')][not(contains(@style,'display: none'))]/div/ul/li"));
			System.out.println(elems.get(0).getText());
			elems.get(0).click();
			elems.clear();
		}

		//input[@name='loanPurposeTypeId']/../following-sibling::td/div/&/
		click(arrowClickPath[5] , metaDataKey);
		locator = "(.//li[text()='" + mortgageDetails[5] + "'])" ;
		click(locator, metaDataKey);

		locator = "//input[@name='loanPurposeTypeId']";
		getAttributeValue(locator, metaDataKey);
		if (values.equals("Refinance")) {
			locator = "//input[@name='refiPurposeTypeId']/../following-sibling::td/div";
			click(locator , metaDataKey);
			locator = "(.//li[text()='" + mortgageDetails[6] + "'])" ;
			click(locator, metaDataKey);
		}

		enterText(arrowClickPath[6], mortgageDetails[7] , metaDataKey);
		Thread.sleep(500);

		click(arrowClickPath[7], metaDataKey);
		Thread.sleep(2000);

		enterText(arrowClickPath[7], mortgageDetails[8] , metaDataKey);
		Thread.sleep(500);

		click(arrowClickPath[6], metaDataKey);
		Thread.sleep(2000);

	}
}








