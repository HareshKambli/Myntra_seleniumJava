package Ecommerce.myntra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPagePOM {
	
	public WebDriver driver;
	
	public CartPagePOM(WebDriver driver) {
		this.driver=driver;
	}
	
	By mrp = By.xpath("//span[@class='priceDetail-base-value ']");
	By coupon = By.xpath("//button[@class='coupons-base-button']");
	By placeOrder = By.xpath("//div[text()='Place Order']");
	By remove = By.xpath("//button[text()='Remove']");
	By quantity = By.xpath("//div[@class='itemBlock-base-itemHeader']//div");
	
	public WebElement mrp() {
		return driver.findElement(mrp);
	}
	
	public WebElement coupon() {
		return driver.findElement(coupon);
	}
	
	public WebElement placeOrder() {
		return driver.findElement(placeOrder);
	}
	
	public WebElement remove() {
		return driver.findElement(remove);
	}
	
	public WebElement quantity() {
		return driver.findElement(quantity);
	}

}
