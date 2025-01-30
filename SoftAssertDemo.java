package com.neotech.lesson02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class SoftAssertDemo extends CommonMethods {
	@BeforeMethod
	public void openAndNavigate() {
		setUp();
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}

	@Test
	public void logoAndLoginValidation() {
		WebElement logo = driver.findElement(By.xpath("//div[@class='orangehrm-logo']/img"));
		boolean logoDisplayed = logo.isDisplayed();

		// Let manually make the logoDisplayed = false --> failing the test
		// logoDisplayed = false;

		// Logo displayed validation
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(logoDisplayed, "Logo is NOT displayed!");

		// Login validation
		sendText(driver.findElement(By.id("txtUsername")), ConfigsReader.getProperty("username"));
		sendText(driver.findElement(By.id("txtPassword")), ConfigsReader.getProperty("password"));

		click(driver.findElement(By.xpath("//button")));
		wait(2);

		String expected = "Jacqueline White";
		String actual = driver.findElement(By.id("account-name")).getText();

		soft.assertEquals(actual, expected, "Account name is NOT Jacqueline White!");

		// This will collect all soft test assertions
		// and it will decide whether the test passed or failed
		soft.assertAll();

		// If a test case fails, after assertAll() the execution will stop
		// Otherwise, it will continue executing the next statements

		System.out.println("After Soft.assert...");
	}

}
