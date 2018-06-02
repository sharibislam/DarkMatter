package com.darkmatter.base;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserDriver extends BaseTest {
	static WebDriver driver;

	 
	
	public static void GetDriver(String browserName, String baseurl) {
		Wrapper WA = Wrapper.getInstance();

		CustomLogger.start("browser");
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\BrowserDriver\\chromedriver.exe");

				ChromeOptions options = new ChromeOptions();
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities
						.setCapability(
								CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
								true);
				capabilities.setCapability(
						CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.IGNORE);
				options.addArguments("test-type");

				driver = new ChromeDriver(options);

				driver.manage().window().maximize();
				CustomLogger.Log.info("Initialize the driver " + browserName
						+ " browser iniialized and and open");
			}

			else if (browserName.equalsIgnoreCase("ie")) {

				DesiredCapabilities capabilities = DesiredCapabilities
						.internetExplorer();
				capabilities
						.setCapability(
								InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
								true);
				capabilities.setCapability(
						InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(
						InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,
						true);
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir")
								+ "//BrowserDriver//IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().maximize();
				CustomLogger.Log.info("Initialize the driver " + browserName
						+ " browser iniialized and and open");
			}

			else if (browserName.equalsIgnoreCase("firefox")) {
	
								System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir")
								+ "//BrowserDriver//geckodriver.exe");
				
				driver = new FirefoxDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				CustomLogger.Log.info("Initialize the driver" + browserName
						+ " browser iniialized and and open");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get(baseurl);
		// return driver;
		WA.settDriver(driver);
	}

	public static void StopDriver() {
		Wrapper WA =Wrapper.getInstance();

		driver = WA.getDriver();
		try {
			if (driver != null) {
				driver.close();
				driver.quit();
				driver = null;
				CustomLogger.Log.info("Closing the driver" + "Driver quit");
			}
		} catch (Exception ignore) {
			System.out.println("Getting Exception while closing the driver: "
					+ ignore);
			CustomLogger.Log.info("Closing the driver "
					+ "Getting Exception while closing the driver");
		}

	}
}
