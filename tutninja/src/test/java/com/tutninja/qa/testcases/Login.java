package com.tutninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutninja.qa.utils.utilities;

public class Login extends Base {
	public Login() {
		super();
	}

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserandopenapp(prop.getProperty("browser"));
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

	}

	@Test(priority = 1)
	public void verifyloginwithvalidcredentials() {
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("validemailid"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h2[normalize-space()='My Account']")).isDisplayed());

	}

	@Test(priority = 2)
	public void verifyloginwithInvalidcredentials() {
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("pavankoorgerr22@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Managecomp00");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualwarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();
		String expectedwarningmsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualwarningmsg, expectedwarningmsg);

	}

	@Test(priority = 3)
	public void verifyloginwithInvalidusernamevalidpassword() {
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("pavankoorgerr22@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualwarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();
		String expectedwarningmsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualwarningmsg, expectedwarningmsg);

	}

	@Test(priority = 4)
	public void verifyloginwithvalidusernameInvalidpassword() throws Exception {
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(utilities.GenerateEmailwithTimeStamp());
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Managecomp00");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualwarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();
		String expectedwarningmsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualwarningmsg, expectedwarningmsg);

	}

	@Test(priority = 5)
	public void verifyloginwithoutcredentials() {
	
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualwarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();
		String expectedwarningmsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualwarningmsg, expectedwarningmsg);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
