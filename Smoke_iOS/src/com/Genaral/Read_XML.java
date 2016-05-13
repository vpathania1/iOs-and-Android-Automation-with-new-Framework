package com.Genaral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Read_XML extends Driver{




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


	public static String zipCode = null;
	public static String zip = null;
	public static String seg = null;
	public static List<String> container;
		public static void main(String[] args) throws Exception {
	
			Read_XML.readXML("cxtg");
		}

	public static void readXML(String Type) throws Exception{
		readExcelValues readExcelValues= new readExcelValues();
		readExcelValues.excelValues("Paths");
		readExcelValues.excelValuesnextSheet("cxtg");
		//Read the file name from the folder
		File folder = new File(readExcelValues.data[4][1]);
		File[] listOfFiles = folder.listFiles();
		String Filename = null;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				Filename = file.getName();
				System.out.println("File Name is : "+Filename);
			}
		}

		File file = new File(readExcelValues.data[4][1]+Filename);

		BufferedReader r = new BufferedReader(new FileReader(file));

		String line = "";
		String allLine = "";
		StringBuffer sb = new StringBuffer("");

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

		for(int loopcount=3;loopcount>=1;loopcount--){

			if(sb.toString().contains(readExcelValues.nextdata[1][1])){
				String wfxtrigger = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.nextdata[2][1])-loopcount);
				String req1 = wfxtrigger.toString().substring(wfxtrigger.toString().indexOf(readExcelValues.nextdata[3][1]));
				req = req1.substring(req1.indexOf(readExcelValues.nextdata[4][1])+7,req1.indexOf(readExcelValues.nextdata[5][1])+7);

				System.out.println("file is "+ req.toString());


				//Get Pubad call from 
				String pubadcal = sb.toString().substring(sb.toString().lastIndexOf(readExcelValues.nextdata[6][1])-loopcount);
				pubreq1 = pubadcal.toString().substring(pubadcal.toString().indexOf(readExcelValues.nextdata[7][1]));
				pubreq1= pubreq1.toString().replaceAll(readExcelValues.nextdata[8][1], "=");
				pubreq1= pubreq1.toString().replaceAll(readExcelValues.nextdata[9][1], "&");
				pubreq1= pubreq1.toString().replaceAll(readExcelValues.nextdata[10][1], ",");
				pubreq1 = pubreq1.substring(pubreq1.indexOf(readExcelValues.nextdata[14][1])+12,pubreq1.indexOf(readExcelValues.nextdata[15][1])+2);

				System.out.println("feed call zip is "+ pubreq1.toString());

			}

			

			// Loop to read all lines one by one from file and print It.

			// System.out.println("Text lenth is: "+Content.charAt('&'));

			String str[] = pubreq1.split("&");
			for (String ssss : str) {
				String s[] = ssss.split("=");
				if (s[0].equals("zip")) {
					zip = s[1].toString();
					System.out.println("zip is :" + zip);

				}
				if (s[0].equals(Type)) {

					seg = s[1].toString();
					String[] items = seg.split(",");
					container = Arrays.asList(items);
					System.out.println("Container is:" + container);
				}
			}
			
			System.out.println(Type+"Values are :-" + seg);

			// reading json
			
			String Content = "";
			// Json file
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new String(req.toString()));
			JSONObject jsonObject = (JSONObject) obj;

			List<String> segmentList = new ArrayList<String>();
			JSONObject mainTag = (JSONObject) jsonObject.get(readExcelValues.nextdata[11][1]);
			JSONArray scatterSegs = (JSONArray) mainTag.get(readExcelValues.nextdata[12][1]);
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
							System.out.println("zip is :" + zip);
							JSONArray segmentJsonArray = (JSONArray) zcszipObject.get(Type);
							System.out.println(zipCode + " zip code segemtn list : " + segmentJsonArray);

							segmentList.addAll(segmentJsonArray.subList(0, segmentJsonArray.size()));
							List<String> firstList = new ArrayList<String>();
							List<String> secondList = new ArrayList<String>();
							for (Object firstString : segmentList) {
								//System.out.println("firstString: " + firstString);
								firstList.add(firstString.toString().trim());
							}

							for (Object secondString : container) {
								System.out.println("secondString : " + secondString);
								secondList.add(secondString.toString().trim());
							}
							System.out.println("segments :" + segmentList);
							System.out.println("Containe :" + container);
							System.out.println("File name is :"+Filename);

							boolean flag = equalLists(firstList, secondList);
							String Res = String.valueOf(flag);
							System.out.println("Lotame Result is :" + Res.toString());

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
	}
}