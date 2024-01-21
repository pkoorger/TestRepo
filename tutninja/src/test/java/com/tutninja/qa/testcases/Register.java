package com.tutninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutninja.qa.utils.utilities;

public class Register extends Base {
	public Register() {
		super();
	}

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserandopenapp(prop.getProperty("browser"));
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(
				By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")).click();

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyregisterusingaccountwithmandatoryfields() {

		driver.findElement(By.id("input-firstname")).sendKeys("Pavan");
		driver.findElement(By.id("input-lastname")).sendKeys("Kumar");
		driver.findElement(By.id("input-email")).sendKeys(utilities.GenerateEmailwithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validpassword"));

		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualResult = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"))
				.getText();

		Assert.assertEquals(actualResult, "Your Account Has Been Created!", "not displayed");
	}

	@Test(priority = 2)
	public void verifyaccountwithallfields() {
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(
				By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Pavan");
		driver.findElement(By.id("input-lastname")).sendKeys("Kumar");
		driver.findElement(By.id("input-email")).sendKeys(utilities.GenerateEmailwithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validpassword"));

		driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualResult = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"))
				.getText();

		Assert.assertEquals(actualResult, "Your Account Has Been Created!", "not displayed");
	}

	@Test(priority = 3)
	public void verifyregisteringaccountwithexistingemailaddress() {

		driver.findElement(By.id("input-firstname")).sendKeys("Pavan");
		driver.findElement(By.id("input-lastname")).sendKeys("Kumar");
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validemailid"));
		driver.findElement(By.id("input-telephone")).sendKeys("123456789");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validpassword"));

		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualWarning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();

		Assert.assertEquals(actualWarning, "Warning: E-Mail Address is already registered!",
				"warning message not displayed");
	}

	@Test(priority = 4)
	public void verifyregisteringaccountwithoutprovidinganydetails() {

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualPrivacypolicyWarning = driver
				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		Assert.assertTrue(actualPrivacypolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),
				"actual privacy policy warning not displayed");

		String actualfirstnamewarning = driver
				.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"))
				.getText();
		Assert.assertTrue(actualfirstnamewarning.contains("First Name must be between 1 and 32 characters!"),
				"firstname is not displayed");

		String actuallastnamewarning = driver
				.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]"))
				.getText();
		Assert.assertTrue(actuallastnamewarning.contains("Last Name must be between 1 and 32 characters!"),
				"lastname is not displayed");

		String emailwarning = driver
				.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]"))
				.getText();
		Assert.assertTrue(emailwarning.contains("E-Mail Address does not appear to be valid!"),
				"email is not displayed");

	}
}
