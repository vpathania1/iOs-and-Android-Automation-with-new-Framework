//package practic;
//
//
//
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//
//
//public class Test_One {
//
//	/**
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		ProxyServer server = new ProxyServer(8090);
//		server.start();
//		server.setCaptureHeaders(true);
//
//		server.setCaptureContent(true);
//		server.newHar("test");
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		Proxy proxy = server.seleniumProxy();
//		FirefoxProfile profile = new FirefoxProfile();
//		profile.setAcceptUntrustedCertificates(true);
//		profile.setAssumeUntrustedCertificateIssuer(true);
//		profile.setPreference("network.proxy.http", "localhost");
//		profile.setPreference("network.proxy.http_port", 8090);
//		profile.setPreference("network.proxy.ssl", "localhost");
//		profile.setPreference("network.proxy.ssl_port", 8090);
//		profile.setPreference("network.proxy.type", 1);
//		profile.setPreference("network.proxy.no_proxies_on", "");
//		profile.setProxyPreferences(proxy);
//		capabilities.setCapability(FirefoxDriver.PROFILE,profile);
//		capabilities.setCapability(CapabilityType.PROXY, proxy);
//		WebDriver driver = new FirefoxDriver(capabilities);
//		driver.get("http://www.google.com");
//		Har har1 = server.getHar();
//		server.stop();
//		driver.quit();
//
//	}
//
//}
