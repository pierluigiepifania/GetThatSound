package test.gestione_brani.aggiungi_nuovo_brano;

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

public class TC_2_1_3 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testTC213() throws Exception {
		driver.get("http://localhost/");
		driver.findElement(By.id("myBtn")).click();
		driver.findElement(By.name("usr")).sendKeys("pierluigiep");
	    driver.findElement(By.name("pwdLogin")).sendKeys("epifania");
		driver.findElement(By.id("submitLogin")).click();
		driver.get("http://localhost/standard/account.jsp");
		driver.findElement(By.name("titolo")).sendKeys("Track01$");
	    driver.findElement(By.name("artista")).sendKeys("Artista01");
	    driver.findElement(By.name("album")).sendKeys("Album01");
        WebElement uploadElement = driver.findElement(By.id("file"));
        // inserisci path del file
        uploadElement.sendKeys("C:\\Users\\korar\\Downloads\\Audio.mp3");
        WebElement element = driver.findElement(By.id("addSong"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(10000);
        driver.findElement(By.id("addSong")).click();
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