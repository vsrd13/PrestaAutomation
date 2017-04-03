package com.prestaautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.selenium.commons.CommonCode;
import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class MyAccount {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;

	public HomePage home;
	public Locators LC;

	// ReadExcel read = new ReadExcel();

	@FindBy(id = "search_query_top")
	public WebElement searchtextbox;

	@FindBy(name = "submit_search")
	public WebElement searchBtn;

	@FindBy(xpath = ".//*[@id='center_column']/h3/span")
	public WebElement ResultsDisplayd;

	@FindBy(xpath = ".//*[@id='product_list']/li[1]/div[2]/h3/a")
	public WebElement ipodShuffleDesc;

	@FindBy(xpath = ".//*[@id='product_list']/li[1]/div[3]/a[1]")
	public WebElement add2Cart;

	@FindBy(xpath = ".//*[@id='shopping_cart']/a")
	public WebElement shoppingCartlink;

	@FindBy(xpath = ".//*[@id='cart_title']")
	public WebElement shoppingCartsummary;
	//// a[@title='iPod shuffle']
	@FindBy(xpath = "//td[@class='cart_description']//p[@class='s_title_block']//a")
	public WebElement productDescription;

	@FindBy(xpath = "//td[@class='cart_unit']/span")
	public WebElement unitprice;

	@FindBy(xpath = "//td[@class='cart_delete']/div/a[@class='cart_quantity_delete']")
	public WebElement deleteBtn;

	@FindBy(xpath = ".//*[@id='center_column']/p[3]/a[2]")
	public WebElement continuecartBtn;

	@FindBy(xpath = ".//*[@id='center_column']/p[3]/a[1]")
	public WebElement nextBtn;

	@FindBy(xpath = ".//*[@id='center_column']/form/p/input[3]")
	public WebElement nextprocessBtn;

	@FindBy(xpath = ".//*[@id='center_column']/p")
	public WebElement cartEmptymsg;

	@FindBy(xpath = ".//*[@id='shopping_cart']/a/span[5]")
	public WebElement cartEmpty;

	@FindBy(xpath = ".//*[@id='header_user_info']/a[2]")
	public WebElement logOut;

	@FindBy(xpath = "//img[@class='logo']")
	public WebElement homepageLogo;

	@FindBy(xpath = "//input[@type='checkbox']")
	public WebElement checkBox;

	@FindBy(xpath = ".//*[@id='center_column']/form/div/p[4]/a")
	public WebElement addnewaddressBtn;

	@FindBy(xpath = "//div[@class='delivery_option_title']")
	public WebElement shippingtext;

	// driver.findElement(By.xpath("//div[@class='delivery_option_title']"));

	@FindBy(id = "cgv")
	public WebElement termscheckBox;

	@FindBy(xpath = "//input[@name='processCarrier']")
	public WebElement processCarrierBtn;

	@FindBy(xpath = "//td[contains(.,'$66.05')]")
	public WebElement finalunitPrice;

	@FindBy(xpath = "//a[@title='Pay by cheque']")
	public WebElement payBycheque;

	@FindBy(xpath = "//a[@title='Pay by bank wire']")
	public WebElement paybyBankWire;

	@FindBy(xpath = "//h3[contains(.,'Pay by credit card with our secured payment server')]")
	public WebElement paybyCreditcard;

	@FindBy(xpath = "//a[@title='Pay with cash on delivery (COD)']")
	public WebElement paybyCash;

	@FindBy(className = "stripe-submit-button")
	public WebElement submitPayment;

	@FindBy(xpath = "//a[@class='logout']")
	public WebElement finalLogout;

	public MyAccount() {

		PageFactory.initElements(driver, this);
		read = new ReadExcel();
		LC = new Locators();
	}

	public void searchproduct() {
		Assert.assertTrue(searchtextbox.isDisplayed());
		Assert.assertTrue(searchtextbox.isDisplayed());
		searchtextbox.sendKeys(read.readData("Product_shuffle"));
		searchBtn.click();
	}

	public void add2cart() throws InterruptedException {
		// CommonCode.wait(5000);

		CommonCode.wait(2);

		Assert.assertEquals(ipodShuffleDesc.getText(), read.readData("Product_shuffle"));
		Assert.assertTrue(add2Cart.isDisplayed());
		add2Cart.click();
		CommonCode.wait(2);
		Assert.assertTrue(shoppingCartlink.isDisplayed());
		// shoppingCartlink.click();
		driver.findElement(By.xpath("//a[@title='View my shopping cart']")).click();
		CommonCode.wait(3);
		Assert.assertEquals(shoppingCartsummary.getText(), read.readData("Shoppingcartsummary"));
		Assert.assertEquals(productDescription.getText(), read.readData("Product_shuffle"));
		Assert.assertEquals(unitprice.getText(), read.readData("iPod_Shuffle_Unit_price"));
		Assert.assertTrue(deleteBtn.isDisplayed());
		Assert.assertTrue(continuecartBtn.isDisplayed());
		Assert.assertTrue(nextBtn.isDisplayed());

	}

	public void addndeleteproduct() throws InterruptedException {

		Assert.assertTrue(add2Cart.isDisplayed());
		add2Cart.click();

		CommonCode.wait(5);

		shoppingCartlink.click();
		CommonCode.wait(3);
		Assert.assertEquals(shoppingCartsummary.getText(), read.readData("Shoppingcartsummary"));
		Reporter.log("***************************");

		Assert.assertEquals(productDescription.getText(), read.readData("Product_shuffle"));
		System.out.println(unitprice.getText());
		Assert.assertEquals(unitprice.getText(), read.readData("iPod_Shuffle_Unit_price"));
		Assert.assertTrue(continuecartBtn.isDisplayed());
		Assert.assertTrue(nextBtn.isDisplayed());

		deleteBtn.click();
		CommonCode.wait(5);
		Assert.assertEquals(cartEmptymsg.getText(), read.readData("Empty_shoppingcart_summary"));
		Assert.assertEquals(cartEmpty.getText(), read.readData("CartEmpty"));

		homepageLogo.click();
		CommonCode.wait(5);
		Assert.assertTrue(logOut.isDisplayed());
	}

	public void cartaddress() throws InterruptedException {
		nextBtn.click();
		CommonCode.wait(5);
		if (checkBox.isSelected()) {
			checkBox.click();
			CommonCode.wait(1);
			checkBox.click();
			Reporter.log("error..!!", true);

		}
		Assert.assertTrue(nextprocessBtn.isDisplayed());

	}

	public void cartShipping() throws InterruptedException {

		Assert.assertTrue(addnewaddressBtn.isDisplayed());
		Assert.assertTrue(nextprocessBtn.isDisplayed());
		nextprocessBtn.click();
		CommonCode.wait(5);

		Assert.assertEquals(shippingtext.getText(), read.readData("ShippingText"));

		if (!termscheckBox.isSelected()) {
			CommonCode.wait(1);
			Reporter.log("Checkbox is unchecked..!!", true);
		} else {
			termscheckBox.click();
			Reporter.log("Checkbox is checked..!!", true);
		}
	}

	public void submitCart() throws InterruptedException {
		// Assert.assertTrue(checkBox.isDisplayed());
		if (!termscheckBox.isSelected()) {
			termscheckBox.click();
			CommonCode.wait(1);
			Reporter.log("Checkbox is checked..!!", true);
		} else {
			Reporter.log("Checkbox is uncheked..!!", true);
		}

		processCarrierBtn.click();
		CommonCode.wait(5);
		Assert.assertEquals(productDescription.getText(), read.readData("Product_shuffle"));
		Assert.assertEquals(finalunitPrice.getText(), read.readData("iPod_Shuffle_Unit_price"));
		Assert.assertTrue(payBycheque.isDisplayed());
		Assert.assertTrue(paybyBankWire.isDisplayed());
		Assert.assertEquals(paybyCreditcard.getText(), read.readData("Pay_by_Creditcard"));
		Assert.assertTrue(paybyCash.isDisplayed());
		Assert.assertTrue(submitPayment.isDisplayed());
		CommonCode.wait(2);
		finalLogout.click();

	}
}
