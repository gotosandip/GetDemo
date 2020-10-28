import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Driver.DriverContext;

public class Main {



	public static void main(String[] args) {

		DriverContext drivercontext = new DriverContext();
		drivercontext.setUpChromeDriver();
		String url ="http://demo.guru99.com/test/newtours/register.php";
		drivercontext.openApplication(url);
		workWithDropdown(drivercontext.driver);
		/*
		enterText(drivercontext.driver); //Enter text
		workWithCheckbox(drivercontext.driver); //Radio Options
		workWithRadioButton(drivercontext.driver); //Checkboxes
		workWithDropdown(drivercontext.driver); // workwithdropdown
		workWithWebTable(drivercontext.driver); //WebTable
		*/
		
		
		drivercontext.tearDownDriver();

	}
	
	public void workWithWindowHandle(WebDriver driver) {
		
		String currentWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i = s1.iterator();
		
		while(i.hasNext()) {
			String childWindow = i.next();
			if (!(childWindow.equals(currentWindow))) {
				driver.switchTo().window(childWindow);
					//perform Operation;
				driver.switchTo().window(currentWindow)	;
			}
			
		}
		
		
		
	}
	
	
	
	public static void workWithActionClass(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveByOffset(10, 20).build().perform();
		
	}
	
	
	public static boolean workWithExplicitWait(WebDriver driver) {
		
		Duration time = Duration.ofSeconds(20);		
		WebDriverWait wait = new WebDriverWait(driver , time);
		By elemnet = By.xpath("Element");
		wait.until(ExpectedConditions.visibilityOfElementLocated(elemnet));			
		return true;
		
	}
	
public static boolean workWithFluentWait(WebDriver driver) {
	
	Duration time = Duration.ofSeconds(20);	
	Duration pollingTime = Duration.ofSeconds(20);
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
			.withTimeout(time) 			
			.pollingEvery(pollingTime) 			
			.ignoring(NoSuchElementException.class);	
		WebElement clickseleniumlink = wait.until(new Function<WebDriver, WebElement>(){
			
			public WebElement apply(WebDriver driver ) {
				return driver.findElement(By.xpath("/html/body/div[1]/section/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/div/a/i"));
			}
		});
		return true;
		
	}
	
	
	
	public static void workWithDropdown( WebDriver driver) {
		String url ="http://demo.guru99.com/test/newtours/register.php";
		By locListOfCountry =  By.xpath("//select[@name='country1']");
		Duration time = Duration.ofSeconds(2);		
		WebDriverWait wait = new WebDriverWait(driver , time);		
		WebElement listOfCountry= wait.until(ExpectedConditions.visibilityOfElementLocated(locListOfCountry));
		Select select = new Select(listOfCountry);
		System.out.println("Multislect? " +  select.isMultiple());
		List<WebElement> allCOuntries= select.getOptions();
		for (int i = 0; i < allCOuntries.size(); i++) {
			select.selectByIndex(i);		
			System.out.println(allCOuntries.get(i).getText() + " is selected");		
		}
		
	}

	//Work With Checkboxes
	public static void workWithCheckbox(WebDriver driver) {
		//String url ="http://demo.guru99.com/test/radio.html";
		By locRadioOption = By.xpath("//input[@type='checkbox'][@name='webform']");
		List<WebElement> elRadioOptions = driver.findElements(locRadioOption);
		for (int i = 0; i < elRadioOptions.size(); i++) {
			WebElement  radioOption = elRadioOptions.get(i);
			if (radioOption.isSelected()) {
				System.out.println(radioOption.getAttribute("value") + " is already checked");
				radioOption.click();
				if (!(radioOption.isSelected())) {
					System.out.println(radioOption.getAttribute("value") + " is unchecked now => Pass ");					
				} else {
					System.out.println(radioOption.getAttribute("value") + " is still checked now => Fail ");
				}			
			}else {
				System.out.println(radioOption.getAttribute("value") + " is not checked");
				radioOption.click();
				if (radioOption.isSelected()) {
					System.out.println(radioOption.getAttribute("value") + " is checked now => Pass ");					
				} else {
					System.out.println(radioOption.getAttribute("value") + " is still unchecked now => Fail ");
				}	
			}			
		}
	}

	//Work With Radio Buttons
	public static void workWithRadioButton(WebDriver driver) {
		//String url ="http://demo.guru99.com/test/radio.html";
		By locRadioOption = By.xpath("//input[@type='radio'][@name='webform']");
		List<WebElement> elRadioOptions = driver.findElements(locRadioOption);
		for (int i = 0; i < elRadioOptions.size(); i++) {
			WebElement  radioOption = elRadioOptions.get(i);
			if (radioOption.isSelected()) {
				System.out.println(radioOption.getAttribute("value") + " is already seelcted");
			}else {
				radioOption.click();
				System.out.println(radioOption.getAttribute("value") + " is now seelcted");
			}
		}
	}



	public  static void enterText(WebDriver driver) {		
		By locator = By.name("q");
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys("Sandip Nag");
		element.sendKeys(Keys.ENTER);		
	}


	public static void workWithWebTable(WebDriver driver) {
		//String url ="https://www.techlistic.com/p/demo-selenium-practice.html";
		By webTableHeader = By.xpath("//table[@summary='Sample Table']/thead[1]/tr[1]/descendant::th");
		List<WebElement> tableHader = driver.findElements(webTableHeader);
		for (int i = 0; i < tableHader.size(); i++) {
			System.out.println(tableHader.get(i).getText());			
		}
		By webTableRows = By.xpath("//table[@summary='Sample Table']/tbody[1]/child::tr");
		List<WebElement> tablerows = driver.findElements(webTableRows);
		for (int i = 0; i < tablerows.size(); i++) {
			WebElement rows = tablerows.get(i);
			List<WebElement> columns = rows.findElements(By.tagName("td"));
			for (int j = 0; j < columns.size() - 1; j++) {
				System.out.println(columns.get(j).getText());

			}

		}


	}

}
