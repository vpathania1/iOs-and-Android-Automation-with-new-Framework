package com.Genaral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.bouncycastle.cert.ocsp.Req;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Factual_XML {
	public static String zipCode = null;
	public static String zip = null;
	public static String seg = null;
	public static List<String> container;
//	public static void main(String[] args) throws Exception {
//
//		Read_XML.readXML("cxtg");
//	}

	public static void readXML(String Type) throws Exception{
		//Read the file name from the folder
		File folder = new File("/Users/aparna/Documents/Naresh/Smoke_iOS/CapturedSessionFile/");
		File[] listOfFiles = folder.listFiles();
		String Filename = null;
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	Filename = file.getName();
		        System.out.println("File Name is : "+Filename);
		    }
		}
		
		File file = new File("/Users/aparna/Documents/Naresh/Smoke_iOS/CapturedSessionFile/"+Filename);

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
		if(sb.toString().contains("location.wfxtriggers.com")){
			String wfxtrigger = sb.toString().substring(sb.toString().lastIndexOf("location.wfxtriggers.com"));
			String req1 = wfxtrigger.toString().substring( wfxtrigger.toString().indexOf("<value>keep-alive</value></header></headers><body><![CDATA"));
			req = req1.substring(req1.indexOf("[CDATA")+7,req1.indexOf("}]}")+3);

			System.out.println("file is "+ req.toString());

			String pubadcal = sb.toString().substring(sb.toString().lastIndexOf("<value>https://pubads.g.doubleclick.net/gampad/ads?"));
			pubreq1 = pubadcal.toString().substring(pubadcal.toString().indexOf("cust_params=")+12);
			pubreq1= pubreq1.toString().replaceAll("%3D", "=");
			pubreq1= pubreq1.toString().replaceAll("%26", "&");
			pubreq1= pubreq1.toString().replaceAll("%2C", ",");
			//pubreq = pubreq1.substring(req1.indexOf("zip"),req1.indexOf("%26"));
			System.out.println("feed call zip is "+ pubreq1.toString());


		}

		String Content = "";
		// Json file
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new String(req.toString()));
		JSONObject jsonObject = (JSONObject) obj;

		List<String> segmentList = new ArrayList<String>();
		JSONObject mainTag = (JSONObject) jsonObject.get("wfxtg");
		JSONArray scatterSegs = (JSONArray) mainTag.get("scatterSegs");

		// Loop to read all lines one by one from file and print It.

		// System.out.println("Text lenth is: "+Content.charAt('&'));

		String str[] = pubreq1.split("&");
		for (String ssss : str) {
			String s[] = ssss.split("=");
//			if (s[0].equals("fgeo")) {
//				zip = s[1].toString();
//				System.out.println("zip is :" + zip);
//
//			}
			if (s[0].equals(Type)) {

			    seg = s[1].toString();
			    String[] items = seg.split(",");
			    container = Arrays.asList(items);
			    System.out.println("Container is:" + container);
			}
		}


		// reading json

		System.out.println("Seg " + seg);

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

//						for (Object secondString : container) {
//							System.out.println("secondString : " + secondString);
//							secondList.add(secondString.toString().trim());
//						}
						System.out.println("segments :" + segmentList);
						System.out.println("Containe :" + container);
						System.out.println("File name is :"+Filename);

					}
				}
			}
		}
	}
}