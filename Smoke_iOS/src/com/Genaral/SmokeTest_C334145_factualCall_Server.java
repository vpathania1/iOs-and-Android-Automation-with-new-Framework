


package com.Genaral;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

//import atu.testng.reports.ATUReports;


import atu.testng.reports.ATUReports;

import com.google.gson.Gson;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;


public class SmokeTest_C334145_factualCall_Server extends Driver {


	public static List<String> container = new ArrayList<String>();
	public static List<String> fgeoval = new ArrayList<String>();
	public static String ids = null;
	public static String[] Lotameid;
	int FactSteps;
	@SuppressWarnings("deprecation")
	public void verify_facualcal_onfresh_launch() throws InterruptedException, IOException
	{
		//Handling error / failures
		FactSteps=1;

		//Report part invoking
		ExtentReports reporter = Driver.getInstance();
		logger = reporter.startTest("Verify Factual Call test case").assignCategory("Smoke_Test");
		logger.log(LogStatus.PASS, "Started Factual Call Testcase");
		System.out.println("Case Started");

		//reading filr from Propery file
		Driver.property();
		PropertyFile.property();

		//Report logs for Launching the app
		ATUReports.add("Launch the app",false);
		logger.log(LogStatus.PASS, "Launch the app");

		//run command for getting logs in idevicesyslog.log
		String[] str ={"/bin/bash", "-c", "/usr/local/bin/idevicesyslog.log  >>"+ properties.getProperty("LogFilePath") };
		Process p = Runtime.getRuntime().exec(str);

		FactSteps=FactSteps+1;
		BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));

		String line = "";
		String allLine = "";

		while((line=r.readLine()) != null)
		{
			//System.out.println("Sys data is ::"+line);


		}

		String FilePath = properties.getProperty("LogFilePath");
		//Users/aparna/Desktop/syslog1.log";

		Map<String, String> mapkeys = new HashMap<String, String>();

		try {
			FileInputStream fstream = new FileInputStream(FilePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					fstream));
			String strLine;

			// / read log line by line ------ strLine = br.readLines(6, 10); /
			StringBuffer sb = new StringBuffer("");
			while ((strLine = br.readLine()) != null) {

				sb.append(strLine);

			}
			ATUReports.add("Verify the Factual values(FAUD,FGEO) in Feed_1 Call",false);
			logger.log(LogStatus.PASS, "Verify the Factual values(FAUD,FGEO) in Feed_1 Call");

			//Handle wrong data failure for log file
			//			if(!sb.toString().contains("7646/app_iphone_us/display"))
			//			{
			//				FactSteps=4+1;
			//				System.out.println("Log data is not correct");
			//				Assert.fail();
			//			}



			//Get sg values form pub ad call
			if(sb.toString().contains("Requesting ad: /7646/app_iphone_us/display/feed/feed_1")){
				String req = sb.toString().substring( sb.toString().lastIndexOf("Requesting ad: /7646/app_iphone_us/display/feed/feed_1 with parameters: {"));
				req = req.substring(req.indexOf("{")+1,req.indexOf("}"));
				String[] arrays = req.split(";");
				//System.out.println("Verifing the "+req);
				for(String keys : arrays){
					//System.out.println("keys is::"+keys);
					if(keys.contains("=")){
						String[] key = keys.split("=");
						// System.out.println(key[0] + "---"+key[1]);
						mapkeys.put(key[0], key[1]);
					}


				}
				for(Entry<String, String> entryKeys : mapkeys.entrySet()){
					//System.out.println("key : "+entryKeys.getKey() + "----"+"value:"+entryKeys.getValue());
					if(entryKeys.getKey().contains("fgeo"))
					{
						String fgeo = entryKeys.getValue();
						//System.out.println("fgeo values are :" + entryKeys.getValue());
						ATUReports.add("sg values are presented",false);
						logger.log(LogStatus.PASS, "sg values are present");
						//container.add(entryKeys.getValue());
						//sg.add(entryKeys.getValue());
						int size = fgeo.length();
						//System.out.println("Size is  : "+size);
						fgeo = fgeo.substring(2, fgeo.lastIndexOf('"'));
						fgeo=fgeo.replaceAll(",", ", ");


						//System.out.println("sg1::"+sg1);
						fgeoval.add(fgeo);
						//System.out.println("fgeo values and split data is ::"+fgeoval);
					}
				}




				//Get id values for lotame call

				Map<String, String> Factualkeys = new HashMap<String, String>();
				String factualString;
				if(sb.toString().contains("https://location.wfxtriggers.com/geopulse")){
					String factualreq = sb.toString().substring( sb.toString().lastIndexOf("WFX triggers Response {"));
					String factualreq1 = factualreq.substring(factualreq.indexOf("(")+1,factualreq.indexOf(");")-1);
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
							if(FactualValues.contains("index"))
							{
								String Factualval = FactualValues.toString().replace("{", "").trim();
								//System.out.println("Factual values are ::"+Factualval);

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
					//System.out.println("sg values are :" + sg.toString());
					String Actual = fgeoval.toString().replace("[", "").replace("]", "");

					//System.out.println("fgeoval  ::" + Actual.toString());

					System.out.println("Container vales are :"+container.toString());
					String Expected = container.toString().replace("[", "").replace("]", "");
					Expected = Expected.toString().replaceAll("\"", "");
					//System.out.println("Container vales are ::"+Expected.toString());

					//Assert.assertEquals(Actual, Expected);	


					String[] Factualarrays = Actual.split(",");
					System.out.println("Verifing the "+Factualarrays);
					for(String Factualkey : Factualarrays){

						Factualkey=Factualkey.toString().replaceAll("^\"|\"$", "");	
						Factualkey=Factualkey.toString().replace(" ", "");

						//System.out.println(Factualkey);
						if(Expected.toString().contains(Factualkey.toString())){

							System.out.println("Values are matched --"+Expected +"--"+Factualkey);
							ATUReports.add("FGEO value is present",false);
							logger.log(LogStatus.PASS, "FGEO Values are matched");
						}else
						{
							FactSteps=FactSteps+1;
							System.out.println("Values are not matched for :"+ Factualkey);
							Assert.fail();
						}
					}

				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}


		System.out.println("Verification of FactualCall test case done");

		reporter.endTest(logger);
		reporter.flush();

	}



}



