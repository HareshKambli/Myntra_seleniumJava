package testFiles;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Ecommerce.myntra.homePagePOM;
import junit.framework.Assert;
import resources.base;

public class TestHomePage extends base{
	public WebDriver driver;
	public homePagePOM hg;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	
	
	@BeforeClass
	public void openSite() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
		hg = new homePagePOM(driver);
	}
	
	
	@Test(priority=1)
	public void search() {
		driver.get("https://www.myntra.com/");
		hg.search().sendKeys("shoes");
		hg.search().sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title-title")));
		System.out.println(driver.findElement(By.className("title-title")).getText());
		Assert.assertEquals("Shoes & Footwear", driver.findElement(By.className("title-title")).getText());
		log.info("Successfully searched shoes");
	}
	
	@Test(priority=2)
	public void men() throws InterruptedException {
		driver.get("https://www.myntra.com/");
		hg.men().click();
		Thread.sleep(2000);
		List<WebElement> categories = driver.findElements(By.xpath("//div[@class='desktop-pSearchlinks']/following-sibling::a"));
		System.out.println(categories.size());
	}
	
	@Test(priority=3)
	public void women() throws InterruptedException {
		driver.get("https://www.myntra.com/");
		hg.women().click();
		Thread.sleep(2000);
		String title = driver.getTitle();
		Assert.assertEquals(title, "Online Shopping for Women - Shop For Women Clothes, Shoes, Bags & More");
	}
	
	@Test(priority=4)
	public void login() throws InterruptedException {
		driver.get("https://www.myntra.com/");
		hg.profile().click();
		driver.findElement(By.xpath("//a[@data-track='login']")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@autocomplete='new-password']")));
		driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("1234567890");
		driver.findElement(By.className("submitBottomOption")).click();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
