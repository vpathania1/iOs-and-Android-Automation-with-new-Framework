package com.Run_performance;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import Smoke_iOS.openCharles;
import Smoke_iOS.openCharlesControl;

import com.Appium.appiumnew;
import com.Genaral.DeleteFile;
import com.Genaral.Driver;
import com.Genaral.File_Exist;
import com.Genaral.Read_XML;
import com.Genaral.Scroll_Down;
import com.Genaral.SelectAddress;
import com.Genaral.User_Login;



public class Run_iOS_Performance extends Driver{
	
	openCharlesControl openCC = new openCharlesControl();
  @Test
  public void Performance() throws Exception, Exception {

	
	  User_Login userLogin = new User_Login();
	  userLogin.login();
	  
	  SelectAddress.selectAddress();
	  
//	  Scroll_Down scrolldown = new Scroll_Down();
//	  scrolldown.scrolldown();
	  
	 

		openCC.open_Charles_Control_Stop();
		openCC.save_Export_Session_XML_File();
		
		 Read_XML.readXML("cxtg");
		
  }
  @BeforeTest
  public void beforeTest() throws Exception, InterruptedException {
//		Install_the_app iapp = new Install_the_app();
//		iapp.install();
		
//		//Delete the log existed file
//				DeleteFile DF = new DeleteFile();
//				File_Exist FE = new File_Exist();
//				if(FE.fileexist()) {
//					DF.deleteFile();
//
//				} else {
//					System.out.println("File not exist");
//				}
		
	  openCharles OCharles= new openCharles();
	  OCharles.open_Headless_Charles();

	  openCC.open_Charles_Control_From_Browser();

		appiumnew appium = new appiumnew();
		appium.Capabilities();
  }

  @AfterTest
  public void afterTest() {
	 
	  
  }

}
