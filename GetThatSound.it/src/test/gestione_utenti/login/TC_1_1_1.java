package test.gestione_utenti.login;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_1_1_1 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	/*Logger logger = Logger.getLogger("TC_1_1_1");*/

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

}
	
	@Test
	public void testLoginOk() throws Exception {
		driver.get("http://localhost/");
		driver.findElement(By.id("myBtn")).click();
		driver.findElement(By.name("usr")).sendKeys("pierluigiep$");
	    driver.findElement(By.name("pwdLogin")).sendKeys("epifania");
		driver.findElement(By.id("submitLogin")).click();
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
