package com.prestaautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.Configuration;

public class Loginpage {

	public WebDriver driver = Configuration.browser();

	@FindBy(id = "email")
	public static WebElement userNametextbox;

	@FindBy(id = "passwd")
	public static WebElement Passwordtextbox;

	@FindBy(id = "SubmitLogin")
	public static WebElement submitBtn;

	@FindBy(xpath = ".//*[@id='center_column']/div[2]/ol/li")
	public static WebElement errormsgValidation;

	public Loginpage() {

		PageFactory.initElements(driver, this);
	}

	public void LogintoApp(String username, String password) {

		userNametextbox.clear();
		userNametextbox.sendKeys(username);

		Passwordtextbox.clear();
		Passwordtextbox.sendKeys(password);

		submitBtn.click();
	}

	public void ValidateErrormsg() {

		Assert.assertEquals(errormsgValidation.getText(), "Invalid e-mail address");
	}

}
