package test.gestione_brani.salva_brano;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_2_2_2 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC222() throws Exception {
		driver.get("http://localhost/");
		driver.findElement(By.id("myBtn")).click();
		driver.findElement(By.name("usr")).sendKeys("pierluigiep");
	    driver.findElement(By.name("pwdLogin")).sendKeys("epifania");
		driver.findElement(By.id("submitLogin")).click();
		driver.findElement(By.id("1")).click();
		Thread.sleep(3000);
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