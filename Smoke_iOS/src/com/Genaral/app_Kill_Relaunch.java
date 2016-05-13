package com.Genaral;

public class app_Kill_Relaunch extends Driver{
	
	public void Kill_realaunch() throws Exception
	{
		
		//Close the app
		Ad.closeApp();
		Thread.sleep(1000);
		System.out.println("App closed successfully");
		
		//Relaunch the app
		Ad.launchApp();
		System.out.println("App launched successfully");
		}

}
