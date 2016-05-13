package com.Functions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import atu.testng.reports.ATUReports;

import com.Appium.Start_Stop_AppiumServer;
import com.Genaral.Driver;
import com.Genaral.readExcelValues;
import com.relevantcodes.extentreports.LogStatus;

public class Functions extends Driver{

	static DesiredCapabilities capabilities = new DesiredCapabilities();
	static int Cap =1;
	static String UserStatus = null;
	static List<MobileElement> Addresseslist =null;
	static MobileElement TempEle =null;
	static MobileElement Settings =null;
	static MobileElement SelectAddress =null;
	static StringBuffer sb = new StringBuffer("");
	public static String	req =null;
	static String pubreq=null; 
	static String pubreq1 = null;
	static List<String> container=null;
	static String SecondParamValue =null;
	static String firstParamValue =null;
	static String zipCode = null;
	static String[] splitPubvalues = null;
	public static String seg = null;

	public static ArrayList<String> pubadvalues = new ArrayList<String>();

	//Appium Start
	public void startAppiumServer() throws IOException, InterruptedException { 

		CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
		command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js", false);
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument("4723");
		//command.addArgument("--no-reset", false);
		command.addArgument("--log-level", false);
		command.addArgument("error");
		//command.addArgument("--log");
		//command.addArgument("/Users/aparna/Documents/sys11.log");


		//		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		//		DefaultExecutor executor = new DefaultExecutor();
		//		executor.setExitValue(1);
		//		executor.execute(command, resultHandler);

		String[] str ={"/bin/bash", "-c", "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js --address 127.0.0.1 --chromedriver-port 9517 --bootstrap-port 4721 --no-reset --local-timezone"};
		Process p = Runtime.getRuntime().exec(str);
		Thread.sleep(20000);

	} 

	//Stop Appium Server
	public  void stopAppiumServer() throws IOException {  
		String[] command ={"/usr/bin/killall","-KILL","node"};  
		Runtime.getRuntime().exec(command);  
		//System.out.println("Appium server stop");  
	}  
	public static void capabilities() throws Exception {

		//				//Auto start Appium
		//				Start_Stop_AppiumServer appiumStart = new Start_Stop_AppiumServer();
		//				System.out.println("Stopping the appium server");
		//				appiumStart.stopAppiumServer();
		//				System.out.println("Appium server is stopped");
		//				//Thread.sleep(10000);
		//				System.out.println("Starting the appium server");
		//				appiumStart.startAppiumServer();
		//				System.out.println("Appium server is started and running");

		//Read Device Platform
		readExcelValues.excelValues("Device");


		if(readExcelValues.data[1][1].equals("Android")){
			Cap = Cap+1;
		}else
		{
			Cap=Cap;
		}


	}
	//Scroll Down
	public static void scrolldown() throws InterruptedException{
		//Thread.sleep(1000);
		//Scroll JS
		JavascriptExecutor js = (JavascriptExecutor) Ad ;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);


	}
	//App Kill Relaunch
	public static void Kill_realaunch() throws Exception
	{

		//Close the app
		Ad.closeApp();
		Thread.sleep(1000);
		System.out.println("App closed successfully");

		//Relaunch the app
		Ad.launchApp();
		System.out.println("App launched successfully");
	}
	//Assert Equlist
	public static boolean equalLists(List<String> segmentJsonArray, List<String> container2) {

		if (segmentJsonArray == null && container2 == null) {
			return true;
		}

		if ((segmentJsonArray == null && container2 != null) || segmentJsonArray != null && container2 == null
				|| segmentJsonArray.size() != container2.size()) {
			System.out.println("Pubad request cxtg and trigger call cxtg are not matched");
			return false;

		}

		return segmentJsonArray.equals(container2);

	}

	//launch the app
	public static void launchtheApp() throws Exception{	

		readExcelValues.excelValues("Capabilities");
		DesiredCapabilities capabilities = new DesiredCapabilities();

		//Capabilities for IOS and Android Based on Selected on Device Selection
		capabilities.setCapability(readExcelValues.data[1][0], readExcelValues.data[1][Cap]);
		capabilities.setCapability(readExcelValues.data[2][0], readExcelValues.data[2][Cap]);
		capabilities.setCapability(readExcelValues.data[3][0], readExcelValues.data[3][Cap]);
		capabilities.setCapability(readExcelValues.data[5][0], readExcelValues.data[5][Cap]);
		capabilities.setCapability(readExcelValues.data[6][0], readExcelValues.data[6][Cap]);
		capabilities.setCapability(readExcelValues.data[7][0], "="+readExcelValues.data[7][Cap]);
		capabilities.setCapability(readExcelValues.data[8][0], readExcelValues.data[8][Cap]);
		capabilities.setCapability(readExcelValues.data[9][0], readExcelValues.data[9][Cap]);
		capabilities.setCapability(readExcelValues.data[10][0], readExcelValues.data[10][Cap]);
		capabilities.setCapability(readExcelValues.data[12][0], readExcelValues.data[12][Cap]);
		capabilities.setCapability(readExcelValues.data[13][0], readExcelValues.data[13][Cap]);
		capabilities.setCapability(readExcelValues.data[14][0], readExcelValues.data[14][Cap]);

		System.out.println("Reading capabilities done");
		//Wait time for Execution of node.js
		Thread.sleep(20000);

		Ad = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Ad.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


	}

	//Verifi User Loggedin
	public static void verifyuserloggedIn() throws Exception{	
		readExcelValues.excelValues("Login");

		//		Settings =(MobileElement) Ad.findElementByXPath(readExcelValues.data[1][Cap]);
		//		Settings.click();
		UserStatus= Ad.findElementByXPath(readExcelValues.data[2][Cap]).getText();

		if(UserStatus.equals(readExcelValues.data[9][Cap])){
			System.out.println("User Already LoggedIn");
		}else{
			System.out.println("User not LoggedIn");
		}


	}

	//Login
	public static void logIn() throws Exception{	

		readExcelValues.excelValues("Login");

		Settings =(MobileElement) Ad.findElementByXPath(readExcelValues.data[1][Cap]);
		//		Settings.click();
		UserStatus= Ad.findElementByXPath(readExcelValues.data[2][Cap]).getText();
		//if User already logged in navigate to cc page
		if(UserStatus.equals(readExcelValues.data[9][Cap])){
			System.out.println("User Already LoggedIn");
			Thread.sleep(3000);
			Settings.click();
			System.out.println("User on CC page");
		}else{
			System.out.println("User not LoggedIn");
			//If user not logged in enter username and password and try to loggin
			Ad.findElementByName(readExcelValues.data[7][Cap]).click();
			Thread.sleep(1000);
			Ad.findElementByXPath(readExcelValues.data[3][Cap]).sendKeys(readExcelValues.data[4][Cap]);
			Ad.findElementByXPath(readExcelValues.data[5][Cap]).sendKeys(readExcelValues.data[6][Cap]);
			Ad.findElementByName(readExcelValues.data[8][Cap]).click();
			Ad.findElementByName(readExcelValues.data[7][Cap]).click();
			System.out.println("User logged in successfully");
			Thread.sleep(8000);

			Settings.click();
			Thread.sleep(3000);
			System.out.println("User on CC page");
		}
		Thread.sleep(3000);
	}


	//Kill The app
	public void killtheApp() throws Exception
	{
		Ad.closeApp();
		Thread.sleep(1000);
		System.out.println("App closed successfully");
	}

	//Relaunch the app
	public void relaunchtheApp() throws Exception
	{
		Ad.launchApp();
		System.out.println("App launched successfully");
	}

	//Uninstall the app
	public static void uninstallApp() throws Exception{
		readExcelValues.excelValues("Paths");
		Thread.sleep(3000);
		String line = "";
		String allLine = "";
		String[] str1 ={"/bin/bash", "-c", readExcelValues.data[13][Cap]+" " +readExcelValues.data[1][Cap]};
		Process p1 = Runtime.getRuntime().exec(str1);
		Thread.sleep(2000);
		BufferedReader r = new BufferedReader(new FileReader(readExcelValues.data[1][Cap]));
		Thread.sleep(3000);
		while((line=r.readLine()) != null)
		{
			System.out.println("Sys data is ::"+line);
			if(line.contains(readExcelValues.data[10][Cap])){
				String[] str2 ={"/bin/bash", "-c", readExcelValues.data[1][Cap] +readExcelValues.data[10][Cap]};
				Process p2 = Runtime.getRuntime().exec(str2);
				System.out.println("App uninstalled in the device and trying to install the app");
				break;
			}
			System.out.println("App not installed in the device and trying to install the app");

		}
	}

	//install the app
	public static void installApp() throws Exception{
		readExcelValues.excelValues("Paths");
		String[] str ={"/bin/bash", "-c", readExcelValues.data[12][Cap]+" " +readExcelValues.data[14][Cap]};
		Process p = Runtime.getRuntime().exec(str);

		Thread.sleep(30000);
		System.out.println("App was installed in the device successfully");
	}

	//navigatetoSettingPage or manage locations
	public static void navigatetoSettingPage() throws Exception{
		readExcelValues.excelValues("Login");
		MobileElement Settings =null;
		Settings =(MobileElement) Ad.findElementByXPath(readExcelValues.data[1][Cap]);
		Settings.click();
	}

	//navigatetoSettingPage or manage locations
	public static void navigatetoAddressPage() throws Exception{
		readExcelValues.excelValues("AddressPage");
		MobileElement addressPage =null;
		addressPage =(MobileElement) Ad.findElementByXPath(readExcelValues.data[1][Cap]);
		addressPage.click();
		MobileElement Address =(MobileElement) Ad.findElementByXPath(readExcelValues.data[2][Cap]);
		Addresseslist = Address.findElementsByClassName(readExcelValues.data[3][Cap]);


	}

	//Add New addresses
	public static void addnewAddress(String zip) throws Exception{
		readExcelValues.excelValues("AddressPage");
		MobileElement Address =(MobileElement) Ad.findElementByXPath(readExcelValues.data[2][Cap]);
		Addresseslist = Address.findElementsByClassName(readExcelValues.data[3][Cap]);
		if(Addresseslist.size()<=10){
			TempEle=(MobileElement) Ad.findElementByClassName("UIASearchBar");
			TempEle.click();
			TempEle.sendKeys(zip);
			//down Keyboad
			Thread.sleep(2000);
			Ad.findElementByName("Search").click();
			//select first name in the list
			Ad.findElementByXPath(readExcelValues.data[5][Cap]).click();
			Thread.sleep(2000); 

		}else
		{
			System.out.println("Saved address limite excied");
		}

	}
	//Verify saved address list
	public static void verifysavedAddresses() throws Exception{
		readExcelValues.excelValues("AddressPage");
		System.out.println("Saved address list count is :"+Addresseslist.size());
		if(Addresseslist.size()>2){
			for(int addresslist=1;addresslist<=Addresseslist.size()-2;addresslist++){
				int Count =addresslist+2; 
				//int TempEle = addresslist+1;
				String xyz = readExcelValues.data[4][Cap];
				//System.out.println("String xyz is "+xyz);
				String[] AddrestSplit =xyz.split("Count");
				xyz=AddrestSplit[0]+Count+AddrestSplit[1];

				SelectAddress = (MobileElement) Ad.findElementByXPath(xyz);
				//readExcelValues.data[4][Cap]);
				String SavedAddress=SelectAddress.getText();
				System.out.println("Saved Address is  - "+SavedAddress);


			}	
		}


	}

	//Select multiple saved address one by one using addresscount value
	public static void selectsavedAddresses(int addresscount) throws Exception{

		readExcelValues.excelValues("AddressPage");
		if(Addresseslist.size()>2){
			for(int addresslist=1;addresslist<=addresscount;addresslist++){

				int Count =addresslist+2; 
				//int TempEle = addresslist+1;
				String xyz = readExcelValues.data[4][Cap];
				//System.out.println("String xyz is "+xyz);
				String[] AddrestSplit =xyz.split("Count");
				xyz=AddrestSplit[0]+Count+AddrestSplit[1];

				SelectAddress = (MobileElement) Ad.findElementByXPath(xyz);
				//readExcelValues.data[4][Cap]);
				String SavedAddress=SelectAddress.getText();
				System.out.println("Selected saved Address is  - "+SavedAddress);
				SelectAddress.click();
				Count=Count-1;
				//int TempEle = addresslist+1;
				String TempEle = readExcelValues.data[7][Cap];
				//System.out.println("String xyz is "+xyz);
				String[] AddresSplit =TempEle.split("Count");
				TempEle=AddresSplit[0]+Count+AddresSplit[1];
				Ad.findElementByXPath(TempEle).click();

				if(addresscount>1 && addresslist!=addresscount){
					navigatetoAddressPage();
				}else{
					System.out.println("");
				}

			}
		}

	}

	//Open Charless session and control from Browser
	public static void startCharlesSession() throws Exception{

		//Driver.property();
		//Open Charles Using Terminal
		String[] openCharles_str ={"/bin/bash", "-c", "open -a charles"};
		Runtime.getRuntime().exec(openCharles_str);	
		Thread.sleep(10000);

		readExcelValues.excelValues("Paths");


		String downloadPath = readExcelValues.data[4][Cap];

		File index = new File(downloadPath);

		if (!index.exists()) {
			System.out.println("Specified folder is not exist and creating the same folder now");
			index.mkdir();
		} else {
			System.out.println("Specified folder is exist and deleting the same folder");
			FileUtils.cleanDirectory(index);
			System.out.println("Deleted all the files in the specified folder");
		}

		FirefoxProfile profile = new FirefoxProfile();

		profile.setPreference("browser.download.folderList", 2);

		profile.setPreference("browser.download.dir", downloadPath);

		profile.setPreference("browser.helperApps.neverAsk.openFile","text/xml");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/xml");


		driver = new FirefoxDriver(profile);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		//driver.get("http://mohantestengg:123456@control.charles");
		driver.get(readExcelValues.data[9][1]);

		readExcelValues.excelValues("Charlesdeatils");
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[1][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[2][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[3][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[4][0])).click();

		//Start the Charles session
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[5][0])).click();

	}

	//Open Charles controller for Stop
	public static void charles_Stop() throws Exception{
		readExcelValues.excelValues("Charlesdeatils");
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[6][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[3][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[1][0])).click();
	}

	//Download XML file
	public static void downloadXMLFile() throws Exception{
		readExcelValues.excelValues("Charlesdeatils");
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.data[7][0])).click();
		Thread.sleep(2000);
		System.out.println("Export file");
		driver.navigate().to(readExcelValues.data[3][0]);
		Thread.sleep(5000);
		driver.close();
	}

	//Read XML File
	public static void readXML() throws Exception{

		readExcelValues.excelValues("Paths");

		//Read the file name from the folder
		File folder = new File(readExcelValues.data[4][Cap]);
		File[] listOfFiles = folder.listFiles();
		String Filename = null;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				Filename = file.getName();
				System.out.println("File Name is : "+Filename);
			}
		}

		File file = new File(readExcelValues.data[4][Cap]+Filename);

		BufferedReader r = new BufferedReader(new FileReader(file));

		String line = "";
		String allLine = "";


		while((line=r.readLine()) != null)
		{
			sb.append(line.trim());
			//System.out.println(line);
		}
		String	req =null;
		String pubreq=null; 
		String pubreq1 = null;
		//	System.out.println(sb);
		System.out.println("data read finished");

	}	


	//Verify single Param Value from pubad call from XML File
	public static void verifyParamsFromPubadCal(String sheetName) throws Exception{
		//container=null;
		readExcelValues.excelValues(sheetName);
		String VerifypubadValues =readExcelValues.data[16][Cap];
		System.out.println("Pubad values are -"+VerifypubadValues);
		String str[] = pubreq1.split("&");
		if(readExcelValues.data[16][Cap].contains(",")){
			String[] splitPubvalues= VerifypubadValues.split(",");
			for(int i=0;i<=splitPubvalues.length-1;i++){
				System.out.println("Pubad value is -"+splitPubvalues[i].toString()+"----Size is"+splitPubvalues.length);
				for (String ssss : str) {
					String s[] = ssss.split("=");

					if (s[0].equals(splitPubvalues[i].toString())) {
						if(i==0){
							firstParamValue = s[1].toString();
							System.out.println(splitPubvalues[i] +"Param value is :" + firstParamValue);
							pubadvalues.add(firstParamValue);
							System.out.println("all first Param value is :" + pubadvalues);
						}else
						{
							seg = s[1].toString();
							String[] items = seg.split(",");
							container = Arrays.asList(items);
							System.out.println("Container is:" + container);
						}
					}
				}
			}
		}
		else
		{
			for (String ssss : str) {
				String s[] = ssss.split("=");

				if (s[0].equals(VerifypubadValues.toString())) {
					firstParamValue = s[1].toString();
					System.out.println(VerifypubadValues +"Param value is :" + firstParamValue);
				}

			}

		}

	}
	//Verify pubad call from XML File
	public static void verifyPubadCal(int wchichPubad,String sheetName) throws Exception{
		readExcelValues.excelValues(sheetName);
		//Get Pubad call from 
		for(int pubadlist=0;pubadlist<=wchichPubad-1;pubadlist++){

			String pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[17][Cap])-pubadlist);

			pubreq1 = pubadcal.toString().substring(pubadcal.toString().indexOf(readExcelValues.data[7][Cap]));
			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[8][Cap], "=");
			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[9][Cap], "&");
			pubreq1= pubreq1.toString().replaceAll(readExcelValues.data[10][Cap], ",");
			pubreq1 = pubreq1.substring(pubreq1.indexOf(readExcelValues.data[14][Cap])+12,pubreq1.indexOf(readExcelValues.data[15][Cap])+2);

			System.out.println("feed call zip is "+ pubreq1.toString());
			verifyParamsFromPubadCal(sheetName);

		}
	}

	//Verify single Param Value from pubad call from XML File
	public static void verifySingleParamFromPubadCal(String firstParam) throws Exception{

		String str[] = pubreq1.split("&");
		for (String ssss : str) {
			String s[] = ssss.split("=");
			if (s[0].equals(firstParam)) {
				firstParamValue = s[1].toString();
				System.out.println(firstParam +"Param value is :" + firstParamValue);

			}
		}
	}

	//Verify Two Param Values from pubad call from XML File
	public static void verifyTwoParamsFromPubadCal(String firstParam,String SecondParam) throws Exception{
		String str[] = pubreq1.split("&");
		for (String ssss : str) {
			String s[] = ssss.split("=");
			if (s[0].equals(firstParam)) {
				firstParamValue = s[1].toString();
				System.out.println(firstParam +"Param value is :" + firstParamValue);

			}
			if (s[0].equals(SecondParam)) {

				SecondParamValue  = s[1].toString();
				String[] items = SecondParamValue.split(",");
				container = Arrays.asList(items);
				System.out.println("Container is:" + container);
			}
		}
	}

	//Verify API call from XML File
	public static void verifyAPICal(int whichAPI,String SheetName) throws Exception{
		readExcelValues.excelValues(SheetName);
		//Get Pubad call from 
		//for(int APIlist=1;APIlist<=whichAPI;APIlist++){
		String ApiCall = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.data[2][Cap]));
		String req1 = ApiCall.toString().substring(ApiCall.toString().indexOf(readExcelValues.data[3][Cap]));
		//System.out.println("Req1 data is "+req1.toString());
		req = req1.toString().substring(req1.indexOf(readExcelValues.data[4][Cap])+7,req1.indexOf(readExcelValues.data[5][Cap])-2);
		System.out.println("file is "+ req.toString());
		//}
	}

	//Verify API call from XML File
	public static void readWFXTriggers(String sheetName) throws Exception{
		readExcelValues.excelValues(sheetName);

		String Content = "";
		// Json file
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new String(req.toString()));
		JSONObject jsonObject = (JSONObject) obj;

		List<String> segmentList = new ArrayList<String>();
		JSONObject mainTag = (JSONObject) jsonObject.get(readExcelValues.data[11][Cap]);
		JSONArray scatterSegs = (JSONArray) mainTag.get(readExcelValues.data[12][Cap]);
		Iterator<JSONObject> iterator = scatterSegs.iterator();
		while (iterator.hasNext()) {

			JSONObject zipMainJsonObject = (JSONObject) iterator.next();

			JSONArray zcszipJson = (JSONArray) zipMainJsonObject.get("zcs");

			if (zcszipJson != null) {
				Iterator<JSONObject> zcszipIterator = zcszipJson.iterator();
				while (zcszipIterator.hasNext()) {
					JSONObject zcszipObject = (JSONObject) zcszipIterator.next();

					zipCode = zcszipObject.get("zip").toString();
					if (zipCode.contains(zipCode)) {
						System.out.println("Value is :" + firstParamValue);
						JSONArray segmentJsonArray = (JSONArray) zcszipObject.get("cxtg");
						System.out.println(zipCode + " zip code segemtn list : " + segmentJsonArray);

						segmentList.addAll(segmentJsonArray.subList(0, segmentJsonArray.size()));
						List<String> firstList = new ArrayList<String>();
						List<String> secondList = new ArrayList<String>();
						for (Object firstString : segmentList) {
							//System.out.println("firstString: " + firstString);
							firstList.add(firstString.toString().trim());
						}

						for (Object secondString : container) {
							//System.out.println("secondString : " + secondString);
							secondList.add(secondString.toString().trim());
						}
						System.out.println("segments :" + segmentList);
						System.out.println("Containe :" + container);
						//System.out.println("File name is :"+Filename);

						boolean flag = equalLists(firstList, secondList);
						String Res = String.valueOf(flag);
						System.out.println("Trigger call Result is :" + Res.toString());

						//						    if (Type == "zcs") {
						//							//wResult.enterResult("wfx", secondList.toString(), firstList.toString(), 4, 7, 8);
						//						    } else if (Type == "nzcs") {
						//							//wResult.enterResult("wfx", secondList.toString(), firstList.toString(), 5, 7, 8);
						//						    }

						System.out.println(flag);
						Assert.assertTrue(flag);
						break;
					}

				}
			}
		}
	}
	//Read Location.wfxtriggers API
	public static void readlocation_wfxTriggers(String sheetName) throws Exception{
		if(req.toString().contains("journaled")){
			//String factualreq = sb.toString().substring( sb.toString().lastIndexOf("WFX triggers Response {"));
			String factualreq1 = req.substring(req.indexOf("[")+1,req.indexOf("],")-1);
			//	String[] arrays = Lotamereq.split(";");
			//System.out.println("FactualReq1 ::"+factualreq1);

			//	System.out.println("json is :;"+Lotamereq1);
			String[] factualarrays = factualreq1.split("},");
			//	System.out.println("Lotamearray is::"+ Lotamearrays.toString());
			//Gson gson = new Gson();
			String[] factualVal = null;


			for(String factualKeys:factualarrays)
			{
				//System.out.println("Lotkeys ::"+LotKeys.toString());
				factualVal =factualKeys.split(";");

				for(String FactualValues:factualVal)
				{
					if(FactualValues.contains("filter"))
					{
						String Factualval = FactualValues.toString().replace("{", "").trim();
						String[] fgeofilter = Factualval.toString().split(",");
						//System.out.println("Factual values are ::"+fgeofilter[0]);
						Factualval = fgeofilter[0].replaceAll("^\"|\"$","");
						
						//System.out.println("Factual values are ::"+Factualval);
						String[]fgeoval = Factualval.split(":");
						String fgeovalues =fgeoval[1].replaceFirst("^\"|\"$","");
						System.out.println("xyz is :"+fgeovalues);
						
						if(Factualval.contains(""))
						{
							Assert.assertNotNull(Factualval);
						}else
						{
							container.add(Factualval.toString().replaceAll("index =", "").trim().replaceAll(", ", ","));
						}

					}

				}

			}
			System.out.println("sg values are :" + pubadvalues.toString());
			String fgeoActual = pubadvalues.toString().replace("[", "").replace("]", "");
			String FaudActual = container.toString().replace("[", "").replace("]", "");
			//System.out.println("fgeoval  ::" + Actual.toString());

			System.out.println("Container vales are :"+container.toString());
			String Expected = container.toString().replace("[", "").replace("]", "");
			Expected = Expected.toString().replaceAll("\"", "");
			//System.out.println("Container vales are ::"+Expected.toString());
			//Assert.assertEquals(Actual, Expected);	

			String[] Factualarrays = fgeoActual.toString().split(",");
			
			//System.out.println("Verifing the "+Factualarrays.toString());
			for(String Factualkey : Factualarrays){
				Factualkey=Factualkey.toString().replaceAll("^\"|\"$", "");	
				Factualkey=Factualkey.toString().replace(" ", "");
				System.out.println(Factualkey);

				//Fgeo Comparison
				if(firstParamValue.toString().contains(Factualkey.toString())){
					System.out.println("Values are matched --"+firstParamValue +"--"+Factualkey);
					//ATUReports.add("FGEO value is present",false);
					//logger.log(LogStatus.PASS, "FGEO Values are matched");
				}else
				{
					System.out.println("Values are not matched for :"+ Factualkey);
					Assert.fail();
				}
			}
			String[] faudarrays = FaudActual.toString().split(",");
			for(String Faudkey : faudarrays){
				Faudkey=Faudkey.toString().replaceAll("^\"|\"$", "");	
				Faudkey=Faudkey.toString().replace(" ", "");
				System.out.println("FAUD keys :"+Faudkey);
				////Fgeo Comparison
				if(container.toString().contains(Expected.toString())){
					System.out.println("Values are matched --"+container +"--"+Faudkey);
					//ATUReports.add("FGEO value is present",false);
					//logger.log(LogStatus.PASS, "FGEO Values are matched");
				}else
				{
					System.out.println("Values are not matched for :"+ Faudkey);
					Assert.fail();
				}
			}
		}
		}
	}