package Ecommerce.myntra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class homePagePOM {
	
	public WebDriver driver;
	
	public homePagePOM(WebDriver driver) {
		this.driver=driver;
	}
	
	By search = By.className("desktop-searchBar");
	By men = By.xpath("//a[@href='/shop/men']");
	By women = By.xpath("//a[@data-group='women']");
	By kids = By.xpath("//a[@data-group='kids']");
	By profile = By.className("desktop-userTitle");
	By cart = By.className("desktop-cart");
	By wishlist = By.className("desktop-wishlist");
	
	public WebElement search() {
		return driver.findElement(search);
	}
	
	public WebElement men() {
		return driver.findElement(men);
	}
	
	public WebElement women() {
		return driver.findElement(women);
	}
	
	public WebElement kids() {
		return driver.findElement(kids);
	}
	
	
	public WebElement profile() {
		return driver.findElement(profile);
	}
	
	public WebElement cart() {
		return driver.findElement(cart);
	}
	
	public WebElement wishlist() {
		return driver.findElement(wishlist);
	}

}
