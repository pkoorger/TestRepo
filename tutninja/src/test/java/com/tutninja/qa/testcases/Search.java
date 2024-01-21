package com.tutninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends Base {
	public Search() {
		super();
	}

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserandopenapp(prop.getProperty("browser"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifysearchwithvalidproduct() {
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("HP");
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());

	}

	@Test(priority = 2)
	public void verifysearchwithinvalidproduct() {
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Honda");
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		String actualSearchmessage = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]"))
				.getText();

		Assert.assertEquals(actualSearchmessage, "There is no product that matches the search criteria.",
				"actual message not displayed");
	}

	@Test(priority = 3)
	public void searchwithoutanyproduct() {
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("");
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		String actualSearchmessage = driver
				.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]"))
				.getText();

		Assert.assertEquals(actualSearchmessage, "There is no product that matches the search criteria.",
				"actual message not displayed");
	}
}
