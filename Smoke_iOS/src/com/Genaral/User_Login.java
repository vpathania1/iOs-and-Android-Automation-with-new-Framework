package com.Genaral;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User_Login extends Driver{
	
	public void login() throws Exception{
		readExcelValues.excelValues("Login");
		MobileElement Settings =null;
		Settings =(MobileElement) Ad.findElementByXPath(readExcelValues.data[1][1]);
		//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]").click();
		Settings.click();
		
		//if User already logged in navigate to cc page
		if(Ad.findElementByName(readExcelValues.data[2][1]).isDisplayed()){
			Thread.sleep(3000);
			//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]").click();
			Settings.click();
			System.out.println("User on CC page");
		}else
		{
			//If user not logged in enter username and password and try to loggin
		Ad.findElementByName(readExcelValues.data[2][1]).click();
		Thread.sleep(1000);
		//Ad.findElementByName("Email").sendKeys("naresh@monocept.com");
		Ad.findElementByXPath(readExcelValues.data[3][1]).sendKeys(readExcelValues.data[4][1]);
		//Ad.findElementByName("Password (6 or more characters)").sendKeys("12345678");
		Ad.findElementByXPath(readExcelValues.data[5][1]).sendKeys(readExcelValues.data[6][1]);
		Ad.findElementByName(readExcelValues.data[7][1]).click();
		System.out.println("User logged in successfully");
		Thread.sleep(8000);
		
//		WebDriverWait wait = new WebDriverWait(Ad, 10);
//		wait.until(ExpectedConditions.visibilityOf(Settings));
		Settings.click();
		Thread.sleep(3000);
		System.out.println("User on CC page - log in");
		
		}
		
		
		//UIAApplication[1]/UIAWindow[1]/UIAButton[2]
		Thread.sleep(3000);
		
		
	}
	

}
