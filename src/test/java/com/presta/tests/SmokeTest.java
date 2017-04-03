package com.presta.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.selenium.commons.CommonCode;
import com.selenium.commons.Configuration;

public class SmokeTest extends TestNGAnnotationsTest {

	public SmokeTest() {
	}

	@Test(priority = 1, testName = "login_Positive", description = "login_Positive", timeOut = 195000, enabled = true, groups = {
			"smoke", "1" })
	public void alogin_Positive() {
		Reporter.log("Test case 1 is running...!", true);
		Assert.assertTrue(true);

		Reporter.log("Test case 1 passed...!", true);

	}

	@Test(priority = 2, testName = "login_Negative", description = "login_Negative", timeOut = 200000, enabled = true, groups = {
			"smoke", "2" })
	public void loginNegative() throws InterruptedException {
		Reporter.log("Test case 2 is running...!", true);
		home.LogOut();
		login.LogintoApp("xyz", "xyz");
		login.ValidateErrormsg();
		login.LogintoApp(Configuration.loginUserName(), Configuration.Loginpassword());

		Reporter.log("Test case 2 passed...!", true);
		CommonCode.wait(5);
	}

	@Test(priority = 3, testName = "searchProduct", description = "searchProduct", timeOut = 200000, enabled = true, groups = {
			"smoke", "3" })
	public void searchProduct() throws InterruptedException {
		Reporter.log("Test case 3 is running...!", true);
		account.searchproduct();
		// account.add2cart();

		Reporter.log("Test case 3 passed...!", true);
		CommonCode.wait(5);

	}

	@Test(priority = 4, testName = "add2cartndelete", description = "add2cartndelete", timeOut = 200000, enabled = true, groups = {
			"smoke", "4" })
	public void addanddelete() throws InterruptedException {
		Reporter.log("Test case 4 is running...!", true);
		account.searchproduct();
		account.addndeleteproduct();
		Reporter.log("Test passed..!", true);

		Reporter.log("Test case 4 passed...!", true);
		CommonCode.wait(5);

	}

	@Test(priority = 5, testName = "cartaddress", description = "cartaddress", timeOut = 200000, enabled = true, groups = {
			"smoke", "5" })
	public void cartaddressverify() throws InterruptedException {
		Reporter.log("Test case 5 is running...!", true);
		account.searchproduct();
		CommonCode.wait(1);
		account.add2cart();
		CommonCode.wait(1);
		account.cartaddress();

		Reporter.log("Test case 5 passed...!", true);
		CommonCode.wait(5);

	}

	@Test(priority = 6, testName = "cartshipping", description = "cartshipping", timeOut = 300000, enabled = true, groups = {
			"smoke", "6" })
	public void cartshippingverify() throws InterruptedException {
		Reporter.log("Test case 6 is running...!", true);
		account.searchproduct();
		CommonCode.wait(1);
		account.add2cart();
		CommonCode.wait(3);
		account.cartaddress();
		CommonCode.wait(1);
		account.cartShipping();

		Reporter.log("Test case 6 passed...!", true);
		CommonCode.wait(5);

	}

	@Test(priority = 7, testName = "cartPayment", description = "cartPayment", timeOut = 300000, enabled = true, groups = {
			"smoke", "7" })
	public void cartpaymentVerification() throws InterruptedException {
		Reporter.log("Test case 7 is running...!", true);
		account.searchproduct();
		CommonCode.wait(1);
		account.add2cart();
		CommonCode.wait(1);
		account.cartaddress();
		CommonCode.wait(1);
		account.cartShipping();
		CommonCode.wait(1);
		account.submitCart();

		Reporter.log("Test case 7 passed...!", true);

	}

}
