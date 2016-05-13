package com.Functions;

import io.appium.java_client.MobileElement;

import com.Genaral.readExcelValues;

public class Main {
	
	public static void main(String[] args) throws Exception
	{
//		//Preconditions
//		Functions.startCharlesSession();
//		
//		
//		//STR
//		Functions.uninstallApp();
//		Functions.installApp();
//		Functions.capabilities();
//		Functions.launchtheApp();
//		Functions.navigatetoSettingPage();
//		Functions.verifyuserloggedIn();
//		Functions.logIn();
//		//Functions.Kill_realaunch();
//		Functions.navigatetoAddressPage();
//		Functions.verifysavedAddresses();
//		Functions.addnewAddress("30339");
//		Functions.addnewAddress("10016");
//		Functions.selectsavedAddresses(2);
//		Functions.charles_Stop();
//		Functions.downloadXMLFile();
		Functions.readXML();
//      Functions.verifyPubadCal(1,"cxtg");
		//Functions.verifyTwoParamsFromPubadCal("zip", "cxtg");
		//WFX TRIGGER
//		Functions.verifyAPICal(2,"cxtg");
//		Functions.readWFXTriggers("cxtg") ;
		//FACTUAL
		Functions.verifyPubadCal(1,"location");
		Functions.verifyAPICal(1,"location");
		Functions.readlocation_wfxTriggers("location") ;
		
	}
}
