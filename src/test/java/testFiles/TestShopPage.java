package testFiles;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Ecommerce.myntra.CartPagePOM;
import Ecommerce.myntra.homePagePOM;
import junit.framework.Assert;
import resources.base;

public class TestShopPage extends base{
	public homePagePOM hg;
	public WebDriverWait wait;
	public WebDriver driver;
	public CartPagePOM cp;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	
	@BeforeTest
	public void navigate() throws IOException {
		driver=initializeDriver();
		hg= new homePagePOM(driver);
		wait = new WebDriverWait(driver,10);
		cp=new CartPagePOM(driver);
	}
	
	@Test(priority=1,enabled=false)
	public void menTshirt() throws InterruptedException {
		driver.get("https://www.myntra.com/");
		hg.search().sendKeys("tshirts");
		hg.search().sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Men']")));
		driver.findElement(By.xpath(("//label[text()='Men']"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='product-imageSliderContainer']")).click();
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> it = tabs.iterator();
		String parent_id = it.next();
		String child_id = it.next();
		driver.switchTo().window(child_id);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='M']")));
		driver.findElement(By.xpath("//p[text()='M']")).click();
		driver.findElement(By.xpath("//*[text()='ADD TO BAG']")).click();
		log.info("Added Tshirt in cart");
	}
	
	@Test(priority=2)
	public void Bags() {
		driver.get("https://www.myntra.com/");
		hg.search().sendKeys("bags");
		hg.search().sendKeys(Keys.ARROW_DOWN);
		hg.search().sendKeys(Keys.ARROW_DOWN);
		hg.search().sendKeys(Keys.ARROW_DOWN);
		hg.search().sendKeys(Keys.ARROW_DOWN);
		hg.search().sendKeys(Keys.ENTER);
		
		List<WebElement> bags= driver.findElements(By.xpath("//*[@class='product-imageSliderContainer']"));
		bags.get(3).click();
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> it = tabs.iterator();
		String parent_id = it.next();
		String child_id = it.next();
		driver.switchTo().window(child_id);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.className("size-buttons-unified-size")));
		driver.findElement(By.className("size-buttons-unified-size")).click();
		driver.findElement(By.xpath("//*[text()='ADD TO BAG']")).click();
		log.info("Added bag in cart");
	}
	
	@Test(priority=3)
	public void cart() throws InterruptedException {
		driver.get("https://www.myntra.com/checkout/cart");
		System.out.println(cp.mrp().getText());
		
		cp.coupon().click();
		Thread.sleep(2000);
		driver.findElement(By.id("coupon-input-field")).sendKeys("abcd");
		driver.findElement(By.id("applyCoupon")).click();
		log.info("Coupon Added");
		wait.until(ExpectedConditions.elementToBeClickable(cp.placeOrder()));
		cp.placeOrder().click();
		log.info("Order Placed");
		driver.navigate().back();
		String quantityBefore = cp.quantity().getText();
		System.out.println(quantityBefore);
		cp.remove().click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='cartItemsList']/div/div/div/div/div[4]/div/div/div[2]/div[2]/button")).click();
		Assert.assertEquals("Let's add one items.", driver.findElement(By.className("emptyCart-base-emptyDesc")).getText());
	}
	
	
	@AfterSuite
	public void quit() {
		driver.quit();
	}

}
