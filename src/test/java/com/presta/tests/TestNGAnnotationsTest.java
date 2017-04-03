package com.presta.tests;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.prestaautomation.pages.HomePage;
import com.prestaautomation.pages.Loginpage;
import com.prestaautomation.pages.MyAccount;
import com.selenium.commons.CommonCode;
import com.selenium.commons.Configuration;
import com.selenium.commons.DataSheet;
import com.selenium.commons.ReadExcel;
import com.selenium.commons.Screenshot;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@Listeners(Screenshot.class)
public class TestNGAnnotationsTest {

	// public WebDriver driver = Configuration.browser();
	public WebDriver driver = Configuration.browser();

	public Loginpage login;
	public DataSheet ds;
	static Workbook wbook;
	static WritableWorkbook wwbCopy;
	static String ExecutedTestCasesSheet;
	public WritableWorkbook workbook;
	public HomePage home;
	public MyAccount account;
	public CommonCode common;
	public ReadExcel read;
	// public static ExtentReports extent;
	// public static ExtentTest test;

	public TestNGAnnotationsTest() {
		login = new Loginpage();
		home = new HomePage();
		account = new MyAccount();
		common = new CommonCode();
		read = new ReadExcel();
	}

	@BeforeSuite(alwaysRun = true)
	public void LogintoApp() {

		String filepath = "src/test/resources/Book1.xls";
		filepath = System.getProperty("user.dir") + "/" + filepath;
		File file = new File(filepath);
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("en", "EN"));

		try {
			workbook = Workbook.createWorkbook(file, wbSettings);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		workbook.createSheet("Report", 0);
		try {
			workbook.write();
			workbook.close();
			Reporter.log("Sheet created", true);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ds = new DataSheet();

		driver.get(Configuration.LoginURL());
		String currenturl = driver.getCurrentUrl();
		// test.log(LogStatus.PASS, "The current URL of the web page ::" +
		// currenturl);
		driver.manage().window().maximize();

		home.LoginLink();
		login.LogintoApp(Configuration.loginUserName(), Configuration.Loginpassword());

		// extent = new
		// ExtentReports("C://Users//Kumar//workspace6//PrestaAutomation//Extentreport.html",
		// true);
		// extent.loadConfig(new
		// File("C://Users//Kumar//workspace6//PrestaAutomation//extent-config.xml"));

	}

	@AfterMethod(alwaysRun = true)
	public void HomePage() {

		home.navigate_to_home();

	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		ds.closeFile();

		/*
		 * test.log(LogStatus.PASS, "Browser closed successfully..!");
		 * extent.endTest(test); extent.flush(); extent.close();
		 */
	}

}
