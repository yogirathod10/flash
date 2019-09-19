package com.javabykiran;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderEx {
	public static WebDriver driver;
	
	WebElement weUserName;

	WebElement wePassword; 
	
	WebElement weSignInButton;
	
	WebElement weErrorMessage; 
	
	
	
	@BeforeSuite
	public void setupEnvironMent() {
		driver = new FirefoxDriver();
		driver.get("file:///C:/Users/HP/Desktop/java/Selenium%20Software/Offline%20Website/index.html");
	}
	
	@BeforeClass
	public void locateAllWebElements(){
		weUserName=driver.findElement(By.xpath("//*[@id=\"email\"]"));
		wePassword=driver.findElement(By.xpath("//*[@id=\"password\"]"));
		weSignInButton=driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button"));
	}
	
	@BeforeMethod
	public void clearAllTextBoxes(){
		weUserName.clear();
		wePassword.clear();
	}
	
	
	@Test(dataProvider = "login-data",dataProviderClass=DataProviderEx.class,groups="smoke")
	public void LoginTestCases(String tcId, String tcDescr, String uname, String passwd, String expResult) {
		// printing all data we got for each test case.
		
		System.out.println(
				tcId + " tcDescr >>  " + tcDescr + " uname " + uname + " passwd " + passwd + " expResult " + expResult);

		// we start putting data in text boxes.
		weUserName.sendKeys(uname);
		wePassword.sendKeys(passwd);
		
		// click on signIn button for getting error message.
		weSignInButton.click();
		
		// Now we will start testing error messages.
		if(!"validuser".equals(tcDescr)){
			weErrorMessage=driver.findElement(By.xpath("//*[@id=\"email_error\"]"));
			String actualMessage=weErrorMessage.getText();
			Assert.assertEquals(actualMessage, expResult);
		}else{
			String actualTitle=driver.getTitle();
			Assert.assertEquals(actualTitle, expResult);
		}
	}

	@DataProvider(name = "login-data")
	public Object[][] loginData() {
		return new Object[][] { new Object[] { "TC01", "blankUser", "", "", "Please enter email." },
				new Object[] { "TC02", "invaliduser", "hellokiran", "kiranpassword", "Please enter email as kiran@gmail.com" },
				new Object[] { "TC03", "validuser", "kiran@gmail.com", "123456", "AdminLTE 2 | Dashboard 11" }, };
	}
	
	@AfterSuite
	public void closeBrowser(){
		driver.close();
	}

}
