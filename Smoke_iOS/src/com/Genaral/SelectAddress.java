package com.Genaral;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class SelectAddress extends Driver{

	public static void selectAddress() throws InterruptedException{
		MobileElement SelectAddress =null;
		MobileElement NavAddress = (MobileElement) Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]");
		//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAStaticText[1]

		NavAddress.click();
		//Get the saved addresslist
		MobileElement Address =(MobileElement) Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]");

		List<MobileElement> Addresseslist = Address.findElementsByClassName("UIATableCell");
		//String SavedAddress = Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[3]")
		//System.out.println("AddressList Size is : "+Addresseslist.size());
		for(int addresslist=1;addresslist<=3;addresslist++){
			int Count =addresslist+2; 
			int TempEle = addresslist+1;
			SelectAddress = (MobileElement) Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell["+Count+"]/UIAStaticText[1]");
			String SavedAddress=SelectAddress.getText();
					//Ad.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell["+Count+"]/UIAStaticText[1]").getText();
			System.out.println("Saved Address is  - "+SavedAddress);
			//Selecting the address
			SelectAddress.click();
			Thread.sleep(5000);


			NavAddress.click();
			Thread.sleep(5000);
			
		}	
	}
}
