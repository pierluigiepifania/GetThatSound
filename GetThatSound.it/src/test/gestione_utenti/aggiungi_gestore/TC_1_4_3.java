package test.gestione_utenti.aggiungi_gestore;


import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_1_4_3 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTC143() throws Exception {
		driver.get("http://localhost/");
		driver.findElement(By.id("myBtn")).click();
		driver.findElement(By.name("usr")).sendKeys("Admin");
	    driver.findElement(By.name("pwdLogin")).sendKeys("admin");
		driver.findElement(By.id("submitLogin")).click();
		driver.get("http://localhost//admin//admin.jsp");
		driver.findElement(By.name("nome")).sendKeys("Nuovo");
		driver.findElement(By.name("cognome")).sendKeys("Gestore$");
		driver.findElement(By.name("email")).sendKeys("nuovo.gestore@test.it");
		driver.findElement(By.name("utente")).sendKeys("NuovoGestore");
		driver.findElement(By.name("pwd")).sendKeys("password1");
		driver.findElement(By.name("pwd2")).sendKeys("password1");
		driver.findElement(By.id("confermagestore")).click();
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
