package Smoke_iOS;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.Genaral.Driver;
import com.Genaral.readExcelValues;



public class openCharlesControl extends Driver{
	
	public void open_Charles_Control_From_Browser() throws Exception{
		
		//Driver.property();
		
		readExcelValues.excelValues("Paths");
		readExcelValues.excelValuesnextSheet("Charlesdeatils");
		
		String downloadPath = readExcelValues.data[4][1];
		
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
		
		
//		profile.setPreference("browser.download.manager.showWhenStarting", false);
//		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
//		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
//		profile.setPreference("browser.download.manager.focusWhenStarting", false);
//		profile.setPreference("browser.download.manager.useWindow", false);
//		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
//		profile.setPreference("browser.download.manager.closeWhenDone", false);
//		profile.setPreference("browser.download.useDownloadDir", false); 

		driver = new FirefoxDriver(profile);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		//driver.get("http://mohantestengg:123456@control.charles");
		driver.get(readExcelValues.data[9][1]);
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[1][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[2][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[3][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[4][0])).click();
	}
	
	private WebDriver FirefoxDriver(FirefoxProfile profile) {
		// TODO Auto-generated method stub
		return null;
	}

	public void open_Charles_Control_Start() throws IOException, InterruptedException{
		
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[5][0])).click();	
	}
	public void open_Charles_Control_Stop() throws IOException, InterruptedException{
	
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[6][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[3][0])).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[1][0])).click();
	}
	
	public void save_Export_Session_XML_File() throws IOException, InterruptedException{
		
		Thread.sleep(1000);
		driver.findElement(By.linkText(readExcelValues.nextdata[7][0])).click();
		Thread.sleep(2000);
		System.out.println("Export file");
		driver.navigate().to(readExcelValues.data[4][1]);
		Thread.sleep(5000);
		driver.close();
	}
	
	
	
	
}
