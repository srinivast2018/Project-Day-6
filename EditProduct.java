package com.ibm.groceries;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.groceriespages.EditProductsPage;
import com.ibm.groceriespages.GroceriesUserPage;
import com.ibm.groceriespages.PageDashboard;
import com.ibm.groceriespages.PageLogin;
import com.ibm.groceriespages.PageProducts;
import com.ibm.initialization.WebDriverLaunch;

public class EditProduct extends WebDriverLaunch {
	
	
	@Test(priority=1, testName="EditProduct",groups="low")
	public void editProduct()throws IOException,InterruptedException
	{
		String url=data.get("url");
		String userName = data.get("username");
		String password = data.get("password");
		String expMessage=data.get("expectedEditProductMessage");
		String newNameProduct=data.get("prodNameNew");
		String newmodelProduct=data.get("modelNameNew");
		String productNotUpdatedMsg=data.get("productNotUpdated");	
		String userPage=data.get("userPageUrl");
		String proudctFoundMessage=data.get("userPageMsg");
		
		//Launching the web site for atozgroceries
		driver.get(url);
			
		PageLogin login = new PageLogin(driver, wait);
		//To enter email address and password and clickon login button
		login.enterEmailAddress(userName);
		login.enterPassword(password);
		login.clickOnLogin();
		Assert.assertTrue(driver.findElement(By.partialLinkText("Logout")).isDisplayed());
		
		PageDashboard dashboard=new PageDashboard(driver, wait);
		//To click on Catalog
		dashboard.clickOnCatalog();
		//To click on Products
		dashboard.clickOnProducts();
		
		
		PageProducts selProduct=new PageProducts(driver,wait);
		selProduct.selectProduct();
		
		// TO wait for the text box product name to be displayed.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pro_name")));
		
		EditProductsPage editPage=new EditProductsPage(driver,wait);
		String pageSource=editPage.editProduct(newNameProduct,newmodelProduct);
		
	 try {
		
		 if(pageSource.contains(expMessage))
		 	{
			Reporter.log(expMessage);
			Assert.assertTrue(pageSource.contains(expMessage));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='dataTableExample2_length']")));	
		
			//Calling method to select the entry dropdown as 'All'
			String pageSource2=selProduct.selectEntry();
		
		//To check the  new name of the product is displayed 
		Assert.assertTrue(pageSource2.contains(newNameProduct));
		
		//To check the new model of the product is displayed
		Assert.assertTrue(pageSource2.contains(newmodelProduct));
		}
	}
	catch(Exception e)
	{
	
		Assert.fail();
	}
	
	finally {
		//To click on on Logout button
		driver.findElement(By.partialLinkText("Logout")).click();
		
	}
	
	
	//To launch groceries user page
	driver.get(userPage);
	GroceriesUserPage userpage=new GroceriesUserPage(driver,wait);
	String userPageSource=userpage.searchProduct(newNameProduct);
	
	try {
		 if(userPageSource.contains(newNameProduct))
		 	{
			System.out.println(proudctFoundMessage);
			Assert.assertTrue(userPageSource.contains(newNameProduct));
		 	}
		}
		 catch(Exception e)
			{
			 Assert.fail();
			}
	
	}
	
	
}
