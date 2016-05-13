package practic;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.server.SeleniumServer;

import com.google.gson.Gson;
import com.google.gson.Gson.*;
import com.google.gson.reflect.*;
import com.thoughtworks.selenium.DefaultSelenium;

public class NewTest {
 
 @Test
 public void testProfileburdaTester() throws Exception{
  
  // Start the Selenium Server
  SeleniumServer srvr = new SeleniumServer();
  srvr.start();
   
  // Create a Selenium Session with captureNetworkTraffic ready
  String site = "http://burda-stage-edit.weather.com/fi-FI/weather/today/l/30339";
		  //"http://www.facebook.com";
  
  DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", site);
  selenium.start("captureNetworkTraffic=true");
  
  // open a page to get the traffic
  selenium.open("/");
   
  // dump the traffic into a variable in Json format
  String trafficOutput = selenium.captureNetworkTraffic("json");
  System.out.println(trafficOutput);

  // parse the json using Gson
  Gson gson = new Gson();
  Type collectionOfHTMLRequestsType = 
   new TypeToken<Collection<HTMLRequestFromSelenium>>(){}.getType();
  Collection<HTMLRequestFromSelenium> seleniumRequests = 
   gson.fromJson(trafficOutput, collectionOfHTMLRequestsType);
  
  
  Type collectionOfHTMLResponceType = 
		   new TypeToken<Collection<HTMLRequestFromSelenium>>(){}.getType();
		  Collection<HTMLRequestFromSelenium> seleniumResponces = 
		   gson.fromJson(trafficOutput, collectionOfHTMLResponceType);
  
  // get ready to analyse the traffic
  TrafficAnalyser ta = new TrafficAnalyser(seleniumRequests);
  
  // this is pretty much copied from Corey's python example
  int num_requests = ta.get_num_requests(); 
  int total_size = ta.get_content_size(); 
  HashMap<Integer,Integer> status_map = ta.get_http_status_codes(); 
  HashMap<String, Object[]> file_extension_map = ta.get_file_extension_stats(); 
          
       System.out.println("\n\n--------------------------------"); 
       System.out.println(String.format("results for %s",site)); 
       System.out.println(String.format("content size: %d kb",total_size)); 
       System.out.println(String.format("http requests: %d",num_requests));
       
       Iterator<Integer> statusIterator = status_map.keySet().iterator() ; 
       while ( statusIterator.hasNext (  )  )  
        {  
        int key = statusIterator.next();
        System.out.println(String.format("status %d: %d", key, status_map.get(key)));
        } 
        
       System.out.println("\nfile extensions: (count, size)"); 
       Iterator<String> extensionIterator = file_extension_map.keySet().iterator() ; 
       while ( extensionIterator.hasNext (  )  )  
        {  
        String key = extensionIterator.next();
        System.out.println(String.format("%s: %d, %f", key,
     file_extension_map.get(key)[0],file_extension_map.get(key)[1]));
        } 
                     
      System.out.println("\nhttp timing detail: (status, method, url, size(bytes), time(ms))");
   for (Iterator iterator = seleniumRequests.iterator(); iterator.hasNext();) {
     HTMLRequestFromSelenium hr = (HTMLRequestFromSelenium) iterator.next();
     //totalContentSize += hr.bytes;
     System.out.println(String.format("%d, %s, %s, %d, %d",
      hr.statusCode, hr.method, hr.url, hr.bytes, hr.timeInMillis));
   }
       
  // close everything down
  selenium.close();
  selenium.stop();
  srvr.stop();
 }
 
 @Before
 public void setUp() throws Exception {
     FirefoxProfile firefoxProfile = new FirefoxProfile();
     firefoxProfile.setPreference("New Private Window",false);
  
 }

}

