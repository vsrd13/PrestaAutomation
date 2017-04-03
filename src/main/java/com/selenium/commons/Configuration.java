package com.selenium.commons;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Configuration {

	public static Properties properties = new Properties(); // Here we imported
															// Java.util

	public static String filepath;
	public static FileInputStream in;
	public static String URL;

	public static String Browser;
	public static WebDriver driver;
	public static String userName;
	public static String password;

	public static WebDriver browser() {
		Reporter.log("in browser loop", true);
		DesiredCapabilities desiredCapabilities;
		if (Browser.equalsIgnoreCase("firefox")) {
			if (driver == null)
				driver = new FirefoxDriver();
			/*
			 * Gecko driver System.setProperty("webdriver.firefox.marionette",
			 * path + "//BrowserDrivers//geckodriver.exe"); DesiredCapabilities
			 * capabilities = DesiredCapabilities.firefox();
			 * capabilities.setCapability("marionette", true);
			 * driver.manage().window().maximize(); driver = new
			 * FirefoxDriver(capabilities);
			 */
			else
				return driver;

		}

		else if (Browser.equalsIgnoreCase("ie")) {
			if (driver == null) {
				String filepath = "src/test/resources/IEDriverServer.exe";
				filepath = System.getProperty("user.dir") + "/" + filepath;
				File file = new File(filepath);
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
				WebDriverWait mywait = new WebDriverWait(driver, 10);
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(userName)));
			} else
				return driver;

		} else if (Browser.equalsIgnoreCase("chrome")) {
			if (driver == null) {
				String filepath = "src/test/resources/chromedriver.exe";
				filepath = System.getProperty("user.dir") + "/" + filepath;
				File file = new File(filepath);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

				driver = new ChromeDriver();
			} else
				return driver;
		} else if (Browser.equalsIgnoreCase("safari")) {
			if (driver == null)
				driver = new SafariDriver();
			return driver;
		}
		return driver;

	}

	static {
		try {
			if (System.getProperty("EnvType") == null) {
				filepath = "src/test/resources/QA-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			} else if (System.getProperty("EnvType").equalsIgnoreCase("qa")) {
				filepath = "src/test/resources/QA-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			} else if (System.getProperty("EnvType").equalsIgnoreCase("dev")) {
				filepath = "src/test/resources/Dev-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		URL = properties.getProperty("url");

		Browser = properties.getProperty("Browser");
		userName = properties.getProperty("username");
		password = properties.getProperty("password");

	}

	public static String LoginURL() {
		Reporter.log("URL is" + URL, true);
		return URL;

	}

	public static String loginUserName() {
		Reporter.log("username is" + userName, true);
		return userName;

	}

	public static String Loginpassword() {
		Reporter.log("password is" + password, true);
		return password;

	}

}
