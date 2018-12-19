package com.ibm.groceriespages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditProductsPage {
	
	//To located product name 
	@FindBy(xpath = "//input[@id='pro_name']")
	WebElement nameEle;

	// To locate Data link
	@FindBy(xpath = "//a[text()='Data']")
	WebElement dataEle;

	// To locate model text box
	@FindBy(xpath = "//input[@id='model']")
	WebElement modelEle;

	// To locate save button
	@FindBy(xpath = "//button[@title='Save']")
	WebElement saveEle;

	WebDriverWait wait;
	WebDriver driver;

	public EditProductsPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	public String editProduct(String productNewname, String modelNewname) {
		// To enter the new name for product
		nameEle.clear();
		nameEle.sendKeys(productNewname);

		// To click on Data link
		dataEle.click();

		// To enter the new name for model
		modelEle.clear();
		modelEle.sendKeys(modelNewname);

		// To click on Save button
		saveEle.click();
		return driver.getPageSource();
	}

}
