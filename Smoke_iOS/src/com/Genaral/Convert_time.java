package com.Genaral;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.testng.asserts.SoftAssert;

import com.weather.excel.WriteResultintoExcel;

public class Convert_time {
	public static double AdLoadingTime;
	  private static SoftAssert softAssert = new SoftAssert();
	public static void conver_time(String adreq, String adfinish) {
		//	public static void main(String[] args){
		//		String adreq ="15:24:00";
		String adrequestTime = adreq;

		String[] Req_tokens = adrequestTime.split(":");
		int Req_hours = Integer.parseInt(Req_tokens[0]);
		int Req_minutes = Integer.parseInt(Req_tokens[1]);
		int Req_seconds = Integer.parseInt(Req_tokens[2]);
		int Req_duration= 3600 * Req_hours + 60 * Req_minutes + Req_seconds;
		System.out.println("The reqtime is "+ adreq+"----ReqTime in sec "+  Req_duration);

		//String adfinish="15:24:45";
		String adrefinishTime = adfinish;

		String[] Finish_tokens = adrefinishTime.split(":");
		int Finish_hours = Integer.parseInt(Finish_tokens[0]);
		int Finish_minutes = Integer.parseInt(Finish_tokens[1]);
		int Finish_seconds = Integer.parseInt(Finish_tokens[2]);
		int Finish_duration = 3600 * Finish_hours + 60 * Finish_minutes + Finish_seconds;
		System.out.println("The finish is "+ adfinish+"---finishTime in sec "+ Finish_duration);

		if(Req_duration>Finish_duration){
			 softAssert.assertTrue(false); 
			 
			//Assert.fail("Finish time not correct or ad not loaded properly");
		}else{
			AdLoadingTime = Finish_duration - Req_duration;
			System.out.println("Ad loading time is ::"+ AdLoadingTime +"sec");

		}



		Calendar cal = Calendar.getInstance();
		String Month = new SimpleDateFormat("MMM").format(cal.getTime());
		String Date = new SimpleDateFormat("dd").format(cal.DATE);
		System.out.println(Month);


	}
	
	public static void main(String[] args) {

		   // create a calendar
		   Calendar cal = Calendar.getInstance();

		   // get the time in milliseconds
		   System.out.println("Current time is :" + cal.getTime());

		   // set time to 5000 ms after january 1 1970
		   cal.setTimeInMillis(5000);

		   // print the new time
		   System.out.println("After setting Time:  " + cal.getTime());
		   
		   Date date2 = new Date(); 
		   System.out.println("Date is :;"+System.currentTimeMillis());
		   Long time2 = (long) (((((date2.getHours() * 60) + date2.getMinutes())* 60 ) + date2.getSeconds()) * 1000);
		   System.out.println("Long time is ::"+time2);
		   
		   
		   
		   }

}
