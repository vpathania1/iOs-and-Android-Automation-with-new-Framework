package com.Genaral;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scroll_Down extends Driver {
	public void scrolldown() throws Exception{
	
	
		for(int FeedValue=1;FeedValue<=5;FeedValue++){

			//if(sb.toString().contains("TwcMetrics")){
			//sb.toString().contains("ad.weather.feed"+FeedValue+".overall.request stopped with duration")){

			if(FeedValue==5){

				Scroll.scrolldown();
			//	Scroll.scrolldown();
				Thread.sleep(5000);

			}else if(FeedValue==4)
			{

				Scroll.scrolldown();
				Scroll.scrolldown();
				Thread.sleep(5000);
			}else
			{

				Scroll.scrolldown();
				Scroll.scrolldown();
				Thread.sleep(5000);
			}

		}
		
	}

}
