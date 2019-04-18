package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
/**
 * 
 * @author myralyn
 * To have the Junit view and see status of test ran (pass/fail)
 * On your Eclipse toolbar click Windows-->Show View-->Others. 
 * Type "Junit" without quotes. Select from list and click OK
 *
 */

public class ItemPage {
	
	private static WebDriver driver = null;
	private static String browser = "chrome";
	
	@Before
	public void setupBeforeEachTest() {
		if (browser == "firefox") {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser == "opera") {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if (browser == "chrome") {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
	}
	
	@After
	public void teardownAfterEachTest() {
		driver.close();
	}

	
	@Test
	public void navigateToItemPageConfirmItemDisplayed() {	
		
		/*navigate to item page*/
		driver.get("https://www.walmart.ca/en/ip/intex-metal-frame-pool/6000166640889");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/* confirm item is displayed */
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='https://i5.walmartimages.ca/images/Large/506/897/1506897.jpg']")));
		driver.findElement(By.xpath("//img[@src='https://i5.walmartimages.ca/images/Large/506/897/1506897.jpg']")).click();

	}
	
	@Test
	public void hoverToDepartmentAndSelectFurniture() {
		driver.get("https://www.walmart.ca/en/ip/intex-metal-frame-pool/6000166640889");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement department = driver.findElement(By.xpath("//span[@id='header-dept-btn-tablet'][@class='mobile-visually-hidden']"));
		
		/*hover to Department*/
		Actions action = new Actions(driver);
		action.moveToElement(department).perform();
		
		/*Select Furniture*/
		driver.findElement(By.xpath("//a[@href='/en/furniture/N-119'][@class='global-nav-simplified-catlink']")).click();		
	}
	
	@Test
	public void searchAnItemThenHitEnter() {
		driver.get("https://www.walmart.ca/en/ip/intex-metal-frame-pool/6000166640889");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*search for macbook*/
		driver.findElement(By.xpath("//input[@id='global-search']")).sendKeys("macbook");
		
		/*hit enter from keyboard*/
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		
	}
	
	@Test
	public void selectHamburgerMenuToGetToSubCategories() {
		driver.get("https://www.walmart.ca/en/ip/intex-metal-frame-pool/6000166640889");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*select humburger menu to go the following subcategories
		 * Easter
		 * Patio
		 * Bikes
		 * BBQs
		 * Electronics
		 * Clothing*/
		driver.findElement(By.xpath("//button[@id='header-hamburger-menu']")).click();
		
		/*confirm sub categories ie.Clothing is  accessible after selecting hamburger menu:*/
		driver.findElement(By.xpath("//li[@id='hamburger-clothing']/a[text()='Clothing']")).click();
			
	}
	
	@Test
	public void assertTotalLinks_is_192() {
		driver.get("https://www.walmart.ca/en/ip/intex-metal-frame-pool/6000166640889");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		int expectedTotal = 192;
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int actualTotal = links.size();
		System.out.println(expectedTotal+ " "+actualTotal);
		Assert.assertEquals(actualTotal, expectedTotal);
			
	}
	
}
