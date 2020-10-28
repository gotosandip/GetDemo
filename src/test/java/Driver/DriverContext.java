package Driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class DriverContext {

	private static String configpath = System.getProperty("user.dir");
	private static  String chromedrivePath = configpath + "\\src\\test\\java\\Driver\\chromedriver.exe";
	public WebDriver driver;


	public void setUpChromeDriver() {
		System.setProperty("webdriver.chrome.driver", chromedrivePath);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("incognito");
		chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//chromeOptions.setCapability(CapabilityType., true);

		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
		driver = new ChromeDriver(chromeOptions);	
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //implicit Wait
	}


	public void openApplication(String url) {		
		driver.manage().deleteAllCookies();
		driver.get(url);	
		//System.out.println(driver.getTitle() + " is the title of the page " + driver.getCurrentUrl());	
		//System.out.println(" Driver Page Source => " + driver.getPageSource() );
	}



	public void tearDownDriver() {
		driver.close();
		driver.quit();
	}

}
