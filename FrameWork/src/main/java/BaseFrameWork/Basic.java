package BaseFrameWork;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

public class Basic extends Extent {

	public static String OR_Path = System.getProperty("user.dir") + "/src/main/java/FOTA/OR.properties";
	public static String Input_Path = System.getProperty("user.dir") + "/src/main/java/FOTA/InputFile.properties";
	public static WebDriver driver;

	public static void chrome() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/Dependencies/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://qa71.contus.us/services/SVEP4154/code/public/login");
		driver.manage().window().maximize();
	}
	
	public static void FireFox() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "/Dependencies/Driver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://qa71.contus.us/services/SVEP4154/code/public/login");
		driver.manage().window().maximize();
	}
	
	public static void Edge() {
		System.setProperty("webdriver.edge.driver",
				System.getProperty("user.dir") + "/Dependencies/Driver/msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("http://qa71.contus.us/services/SVEP4154/code/public/login");
		driver.manage().window().maximize();
	}
	
	@BeforeSuite
	public static void SetUp() throws Exception {

		chrome();
	}

	public static WebElement WebElementCheck(String objkey) throws Exception {
		InputStream input = new FileInputStream(OR_Path);
		Properties OR = new Properties();
		OR.load(input);
		String obj = OR.getProperty(objkey);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		

		if (objkey.endsWith("_id")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(obj)));
			return driver.findElement(By.id(obj));
		} else if ((objkey.endsWith("_xpath"))) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(obj)));
			return driver.findElement(By.xpath(obj));
		} else if ((objkey.endsWith("_name"))) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(obj)));
			return driver.findElement(By.name(obj));
		} else if ((objkey.endsWith("_class"))) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(obj)));
			return driver.findElement(By.className(obj));
		} else if ((objkey.endsWith("_link"))) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(obj)));
			return driver.findElement(By.linkText(obj));
		} else if ((objkey.endsWith("_link"))) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(obj)));
			return driver.findElement(By.linkText(obj));
		} else if ((objkey.endsWith("_css"))) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(obj)));
			return driver.findElement(By.cssSelector(obj));
		} else {
			return null;
		}
	}
	
	/*Input data*/
	
	public static String InputData(String Key) throws Exception {
		InputStream input = new FileInputStream(Input_Path);
		Properties Inputprob = new Properties();
		Inputprob.load(input);
		String Value = Inputprob.getProperty(Key);
		return Value;
		
	}

	
	/*Screenshot*/
	
	public static String SSAsBase64(String screenshotName) throws IOException {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			String screenshot = ts.getScreenshotAs(OutputType.BASE64);

			return "data:image/gif;base64," + screenshot;
		} catch (Exception e) {
			e.printStackTrace();
			return "Screenshot_Capture_Failure!";
		}

	}

	/* SendKeys */

	public static void sendData(String objkey, String data) {

		try {
			WebElementCheck(objkey).sendKeys(data);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("The data is not send");
		}

	}

	/* Is Displayed */

	public static Boolean Displayed(String objkey) {
		// Boolean verify;
		try {
			Boolean verify = WebElementCheck(objkey).isDisplayed();
			return verify;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/* File Upload */

	public static void fileUpload(String file, String type) {
		try {
			if (type.contains("img")) {
				String fileLocation = System.getProperty("user.dir") + "\\Dependencies\\Images\\" + file;
				System.out.println(fileLocation);
				StringSelection stringSelection = new StringSelection(fileLocation);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, stringSelection);
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_V);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);

			} else if (type.contains("apk")) {
				String fileLocation = System.getProperty("user.dir") + "\\Dependencies\\APK\\" + file;
				StringSelection stringSelection = new StringSelection(fileLocation);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, stringSelection);
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_V);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);

			} else if (type.contains("bin")) {
				String fileLocation = System.getProperty("user.dir") + "\\Dependencies\\Bin\\" + file;
				StringSelection stringSelection = new StringSelection(fileLocation);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, stringSelection);
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_V);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
			} else {
				System.out.println("The file is not defined appropriately");
			}
		} catch (Exception e) {
			System.out.println("The file is not uploaded");
		}
	}

	/* Is Selected */

	public static Boolean Selected(String objkey) {
		Boolean verify;
		try {
			verify = WebElementCheck(objkey).isSelected();
			return verify;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/* Click */

	public static void clickButton(String objkey) {
		try {
			WebElementCheck(objkey).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* get attribute */
	public static String getAttribute(String objkey) {

		try {
			String text = null;
			WebElementCheck(objkey).getAttribute(text);
			return text;
		} catch (Exception e) {

			e.printStackTrace();
			return "Empty field";
		}

	}

	/* GetText *//* GetText */

	public static String getText(String objkey) {
		String text;
		try {
			text = WebElementCheck(objkey).getText();
			return text;
		} catch (Exception e) {

			e.printStackTrace();
			return "Empty field";
		}

	}

	/* clear text */

	public static void clearText(String objkey) {
		try {
			WebElementCheck(objkey).clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * webdriver wait
	 */

	public static void wait(String ele) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ele)));
	}

	/* Scroll */

	public static void scroll(int a, int b) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String ele = "window.scrollBy" + "(" + a + "," + b + ")";
		js.executeScript(ele);
	}

	public static void dropDownByValue(String objkey, String value) throws Exception {
		try {
			WebElement Element = WebElementCheck(objkey);
			Select dropDown = new Select(Element);
			dropDown.selectByValue(value);
		} catch (Exception e) {
			System.out.println("The dropDownByValue method failed");
		}
	}

	public static void dropDownByIndex(String objkey, int index) throws Exception {
		try {
			WebElement Element = WebElementCheck(objkey);
			Select dropDown = new Select(Element);
			dropDown.selectByIndex(index);
		} catch (Exception e) {
			System.out.println("The dropDownByIndex method failed");
		}
	}

	public static void dropDownByVisibleText(String objkey, String text) throws Exception {
		try {
			WebElement Element = WebElementCheck(objkey);
			Select dropDown = new Select(Element);
			dropDown.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("The dropDownByVisibleText method failed");
		}

	}

	public static void enterButton(String objkey) {
		try {
			WebElementCheck(objkey).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("The enter button is not clicked");
		}
	}

	public static String getdropdown(String objkey) throws Exception {
		WebElement Element = WebElementCheck(objkey);
		Select dropDown = new Select(Element);
		String firstSelected = dropDown.getFirstSelectedOption().getText();
		return firstSelected;
	}

	public static void Catch() throws Exception {
		String ss = SSAsBase64("s");
		test.log(LogStatus.FAIL, "The element is not available for the user" + test.addBase64ScreenShot(ss));
		System.out.println("The element is not available for the user");
	}

	@AfterMethod
	public static void EndTest() {
		report.endTest(test);
	}

	@AfterSuite
	public static void teardown() {
		driver.close();
	}
}
