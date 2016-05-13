package com.Genaral;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;



public class Scroll extends Driver{
	
	public static void scrolldown() throws InterruptedException{
		//Thread.sleep(1000);
		//Scroll JS
		JavascriptExecutor js = (JavascriptExecutor) Ad ;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
		

	}
	
//	public static void scrollup() throws InterruptedException{
//		//Scroll JS
//		Thread.sleep(1000);
//		JavascriptExecutor js = (JavascriptExecutor) Ad ;
//		HashMap<String, String> scrollObject = new HashMap<String, String>();
//		scrollObject.put("direction", "up");
//		js.executeScript("mobile: scroll", scrollObject);
//	}
	
	
//	public static void scrolldown_login() throws InterruptedException{
//		Thread.sleep(1000);
//		//Scroll JS
//		JavascriptExecutor js = (JavascriptExecutor) Ad ;
//		HashMap<String, String> scrollObject = new HashMap<String, String>();
//		scrollObject.put("direction", "down");
//		js.executeScript("mobile: scroll", scrollObject);
//		
//
//	}

}
