package practic;

import java.util.Date;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChromeConsoleLogging {
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/aparna/Downloads/chromedriver");  
        
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(caps);
    }

    @AfterMethod
    public void tearDown() {
       // driver.quit();
    }

    public void analyzeLog() {
        LogEntries BROWSERLogs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : BROWSERLogs) {
            System.out.println("Logs are:: "+new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
        
        LogEntries clientLogs =driver.manage().logs().get(LogType.CLIENT);
        for (LogEntry Logsentry : clientLogs) {
            System.out.println("Client Logs are:: "+new Date(Logsentry.getTimestamp()) + " " + Logsentry.getLevel() + " " + Logsentry.getMessage());
            //do something useful with the data
        }
        
        
        LogEntries DRIVERLogs =driver.manage().logs().get(LogType.DRIVER);
        for (LogEntry DRIVERLog : DRIVERLogs) {
            System.out.println("Driver Logs are:: "+new Date(DRIVERLog.getTimestamp()) + " " + DRIVERLog.getLevel() + " " + DRIVERLog.getMessage());
            //do something useful with the data
        }
        
//        LogEntries PERFORMANCELogs =driver.manage().logs().get(LogType.PERFORMANCE);
//        for (LogEntry PERFORMANCELog : PERFORMANCELogs) {
//            System.out.println("PERFORMANCE Logs are:: "+new Date(PERFORMANCELog.getTimestamp()) + " " + PERFORMANCELog.getLevel() + " " + PERFORMANCELog.getMessage());
//            //do something useful with the data
//        }
        	
        LogEntries PROFILERLogs =driver.manage().logs().get(LogType.PROFILER);
        for (LogEntry PROFILERLog : PROFILERLogs) {
            System.out.println("Profiler Logs are:: "+new Date(PROFILERLog.getTimestamp()) + " " + PROFILERLog.getLevel() + " " + PROFILERLog.getMessage());
            //do something useful with the data
        }
        LogEntries SERVERLogs =driver.manage().logs().get(LogType.SERVER);
        for (LogEntry PROFILERLog : PROFILERLogs) {
            System.out.println("Server Logs are:: "+new Date(PROFILERLog.getTimestamp()) + " " + PROFILERLog.getLevel() + " " + PROFILERLog.getMessage());
            //do something useful with the data
        }
    }

    @Test
    public void testMethod() {
    	driver.manage().window().maximize();
        driver.get("http://burda-stage-edit.weather.com/fi-FI/weather/today/l/30339");
        //do something on page
        analyzeLog();
    }
}