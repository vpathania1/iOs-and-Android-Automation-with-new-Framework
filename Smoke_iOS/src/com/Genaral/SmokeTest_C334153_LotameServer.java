package com.Genaral;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;



public class SmokeTest_C334153_LotameServer extends Driver{
	
	public static List<String> container = new ArrayList<String>();
	public static List<String> sg = new ArrayList<String>();
	public static String ids = null;
	public static String[] Lotameid;

	@SuppressWarnings("deprecation")

	
	public void Lotame_server() throws IOException{

		System.out.println("Case Started");


		//run command for getting logs in idevicesyslog.log
					String[] str ={"/bin/bash", "-c", "/usr/local/bin/idevicesyslog.log  >>" +properties.getProperty("LogFilePath")};
					Process p = Runtime.getRuntime().exec(str);


		//LotameSteps=//LotameSteps+1;
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

			//Get sg values form pub ad call
			if(sb.toString().contains("Requesting ad: /7646/app_iphone_us/display/feed/feed_1")){
				String req = sb.toString().substring( sb.toString().lastIndexOf("Requesting ad: /7646/app_iphone_us/display/feed/feed_1 with parameters: {"));
				req = req.substring(req.indexOf("{")+1,req.indexOf("}"));
				String[] arrays = req.split(";");
				System.out.println("Verifing the "+req);
				for(String keys : arrays){
					System.out.println(keys);
					if(keys.contains("=")){
						String[] key = keys.split("=");
						// System.out.println(key[0] + "---"+key[1]);
						mapkeys.put(key[0], key[1]);
					}

				}
				for(Entry<String, String> entryKeys : mapkeys.entrySet()){
					//System.out.println("key : "+entryKeys.getKey() + "----"+"value:"+entryKeys.getValue());
					if(entryKeys.getKey().contains("sg"))
					{
						String sg1 = entryKeys.getValue();
						System.out.println("sg values are :" + entryKeys.getValue());
						//ATUReports.add("sg values are presented",false);
						//Loggerlog(LogStatus.PASS, "sg values are presented");
						//container.add(entryKeys.getValue());
						//sg.add(entryKeys.getValue());
						int size = sg1.length();
						//System.out.println("Size is  : "+size);
						sg1 = sg1.substring(2, sg1.lastIndexOf('"'));
						sg1=sg1.replaceAll(",", ", ");
						//System.out.println("sg1::"+sg1);
						sg.add(sg1);
					}
				}

			}


			//Get id values for lotame call

			Map<String, String> Lotamekeys = new HashMap<String, String>();
			String LotameString;
			if(sb.toString().contains("Lotame response")){
				String Lotamereq = sb.toString().substring( sb.toString().lastIndexOf("Lotame response {"));
				String Lotamereq1 = Lotamereq.substring(Lotamereq.indexOf("(")+1,Lotamereq.indexOf(");")-1);
				//	String[] arrays = Lotamereq.split(";");
				System.out.println("LotameReq1 ::"+Lotamereq1);

				//	System.out.println("json is :;"+Lotamereq1);
				String[] Lotamearrays = Lotamereq1.split("},");
				//	System.out.println("Lotamearray is::"+ Lotamearrays.toString());
				//Gson gson = new Gson();
				String[] LotVal = null;


				for(String LotKeys:Lotamearrays)
				{
					//System.out.println("Lotkeys ::"+LotKeys.toString());
					LotVal =LotKeys.split(";");

					for(String LotameValues:LotVal)
					{
						if(LotameValues.contains("id"))
						{
							System.out.println("Lotame values are ::"+LotameValues.toString());

							container.add(LotameValues.toString().replaceAll("id =", "").trim().replaceAll(", ", ","));
						}

					}

				}
				//System.out.println("sg values are :" + sg.toString());
				String Actual = sg.toString().replace("[", "").replace("]", "");

				System.out.println("sg values are :" + Actual.toString());


				//System.out.println("Container vales are :"+container.toString());
				String Expected = container.toString().replace("[", "").replace("]", "");
				System.out.println("Container vales are :"+Expected.toString());

				Assert.assertEquals(Actual, Expected);		
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Verifyings Lotame test case done");
	}

}
