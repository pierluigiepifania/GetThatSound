package test.gestione_brani.visualizza_brani_in_attesa_di_conferma;


import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_2_1_2 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC212() throws Exception {
		driver.get("http://localhost/");
		driver.findElement(By.id("myBtn")).click();
		driver.findElement(By.name("usr")).sendKeys("Manager");
	    driver.findElement(By.name("pwdLogin")).sendKeys("manager");
		driver.findElement(By.id("submitLogin")).click();
		driver.get("http://localhost/manager/manager.jsp");
		WebElement element = driver.findElement(By.id("listaBrani"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
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