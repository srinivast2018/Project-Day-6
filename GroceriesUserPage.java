package com.ibm.groceriespages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroceriesUserPage {

	@FindBy(xpath = "//input[@placeholder='Search for products...']")
	WebElement searchEle;

	@FindBy(xpath = "(//div[@class='input-group']/descendant::input[1])[2]")
	WebElement searchEle2;

	@FindBy(xpath = "//div[@id='searchproducts-div']/descendant::a[1]")
	WebElement productNewLink;

	WebDriverWait wait;
	WebDriver driver;

	public GroceriesUserPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	public String searchProduct(String proudctName) throws InterruptedException {

		searchEle.sendKeys(proudctName);
		searchEle.click();

		// To enter product name in search text box of popup
		searchEle2.sendKeys(proudctName);
		Thread.sleep(10000);
		productNewLink.click();

		return driver.getPageSource();

	}
}
