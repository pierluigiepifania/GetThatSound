package test.gestione_brani.modifica_brano;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_2_3_3 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testTC233() throws Exception {
		driver.get("http://localhost/");
		driver.findElement(By.id("myBtn")).click();
		driver.findElement(By.name("usr")).sendKeys("pierluigiep");
	    driver.findElement(By.name("pwdLogin")).sendKeys("epifania");
		driver.findElement(By.id("submitLogin")).click();
		driver.get("http://localhost/standard/account.jsp");
		WebElement element = driver.findElement(By.xpath("//button[contains(@id, 'modifica')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.xpath("//button[contains(@id, 'modifica')]")).click();
		driver.findElement(By.xpath("//th[contains(@id, 'titolo')]")).clear();
		driver.findElement(By.xpath("//th[contains(@id, 'titolo')]")).sendKeys("traccia");
		driver.findElement(By.xpath("//button[contains(@id, 'completa')]")).click();
        }

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
