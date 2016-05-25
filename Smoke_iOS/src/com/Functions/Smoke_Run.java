package com.Functions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.Genaral.Driver;
import com.Genaral.PropertyFile;

public class Smoke_Run extends Driver {


	//Pull to refresh Test case
		@Test(priority =0)
		public void pullto_refresh() throws Exception {
			//pull to refresh
			Functions.pulltorefresh();
			Functions.downloadXMLFile();
			Functions.readXML();
			Functions.verifyPubadCal(1, "pulltorefresh");
			
		}
	
	
		//Verify Clean launch the app Test case
		@Test(priority =1)
		public void cleanlaunch() throws Exception {
			Functions.delete_folder();
			Functions.clear_session();
			Functions.close_launchApp();
			Functions.Scroll_end();
			Functions.downloadXMLFile();
			Functions.Verify_feedcals("CLT");
			Functions.close_launchApp();
	
		}
	
		@Test(priority =2)
		public void WFXTriggers_cxtg() throws Exception {
			Functions.navigatetoSettingPage();
			Functions.verifyuserloggedIn();
			Functions.logIn();
			//Functions.Kill_realaunch();
			Functions.navigatetoAddressPage();
			Functions.verifysavedAddresses();
			//		Functions.addnewAddress("30339");
			//		Functions.addnewAddress("10016");
			Functions.selectsavedAddresses(2);
			
			Functions.charles_Stop();
			Functions.readXML();
			//WFX TRIGGER
			Functions.verifyPubadCal(2,"cxtg");
			Functions.verifyAPICal(2,"cxtg");
			Functions.readwfxTriggers("cxtg") ;
		}
	
		//FACTUAL Test cases
		@Test(priority =3)
		public void WFXTriggers_Factuals() throws Exception {
	
	
			Functions.verifyPubadCal(1,"location");
			Functions.verifyAPICal(1,"location");
			Functions.readlocation_wfxTriggers("location") ;
		}
		//Lotame Test case
		@Test(priority =4)
		public void ad_crwd() throws Exception {
			Functions.verifyPubadCal(1,"ad");
			Functions.verifyAPICal(1,"ad");
			Functions.readlocation_wfxTriggers("ad") ;
		}
	
		//Verify extended Hourly page ad presence Test case
		@Test(priority =5)
		public void extenedHourlyPage_ads() throws Exception {
			Functions.close_launchApp();
			Functions.Verify_selectedPages("Hourly");
			Functions.Navigate_extendedPages("Hourly");
			Functions.Verify_Adpresenton_extendedPages("Hourly");
	
		}
		//Verify extended Daily page ad presence Test case
		@Test(priority =6)
		public void extenedDailyPage_ads() throws Exception {
			Functions.Verify_selectedPages("Daily");
			Functions.Navigate_extendedPages("Daily");
			Functions.Verify_Adpresenton_extendedPages("Daily");
	
		}
		//Verify extended Map page ad presence Test case
		@Test(priority =7)
		public void extenedMapPage_ads() throws Exception {
			Functions.Verify_selectedPages("Map");
			Functions.Navigate_extendedPages("Map");
			Functions.Verify_Adpresenton_extendedPages("Map");
	
		}
		//Verify extended News page ad presence Test case
		@Test(priority =8)
		public void extenedNewPage_ads() throws Exception {
			Functions.Verify_selectedPages("News");
			Functions.Navigate_extendedPages("News");
			Functions.Verify_Adpresenton_extendedPages("News");
	
		}


	@Test(priority =9)
	public void setto_TestMode() throws Exception {
		Functions.Setappinto_TestMode();
		Functions.downloadXMLFile();
		Functions.readXML();
		Functions.verifyPubadCal(1, "TestMode");

	}

//	@Test(priority =10)
//	public void ThirdParty_beacons() throws Exception {
//		Functions.thirdPart_Beacon();
//	}


	@BeforeMethod
	public void beforeMethod() {


	}

	@AfterMethod
	public void afterMethod() {
		//System.out.println("After Method");
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		//Preconditions
		//Functions.Appium_Autostart();
		Functions.startCharlesSession();
		Functions.charles_Stop();
		//STR
		Functions.uninstallApp();
		Functions.installApp();
		Functions.capabilities();
		Functions.launchtheApp();



	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Method");
		//driver.close();
	}

}
