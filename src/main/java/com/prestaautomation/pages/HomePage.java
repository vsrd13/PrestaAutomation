package com.prestaautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.CommonCode;
import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class HomePage {

	public WebDriver driver = Configuration.browser();

	public ReadExcel read;

	@FindBy(className = "login")
	public WebElement LoginLink;

	@FindBy(className = "logout")
	public WebElement Logoutlink;

	@FindBy(xpath = "//img[@class='logo']")
	public WebElement homepageLogo;

	public HomePage() {

		PageFactory.initElements(driver, this);
		read = new ReadExcel();

	}

	public void LoginLink() {
		LoginLink.click();

		Assert.assertEquals(driver.getTitle(), read.readData("Login_Title"));
	}

	public void LogOut() throws InterruptedException {
		Logoutlink.click();
		CommonCode.wait(2);
		LoginLink.click();
	}

	public void navigate_to_home() {
		homepageLogo.click();
	}

}
