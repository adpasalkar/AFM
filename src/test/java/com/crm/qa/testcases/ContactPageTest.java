package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase{

	private static final String Fname = null;
	LoginPage loginPage;
	HomePage homepage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactPageTest()
	{
		super();
	}
	

	@BeforeMethod
	public void setUp(){
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();	
		contactsPage = new ContactsPage();
		homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homepage.CliconOnContactsLink();
	}
	
	
	@Test(priority =1)
	public void verfyContactspageLabel()
	{
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contact label is missing on page");
		
	}
	
	@Test(priority =2)
	public void selectContactsTest()
	{
		contactsPage.selectContactByName("AB D");
		
	}
	
	@Test(priority =3)
	public void selectMultipleContactsTest()
	{
		contactsPage.selectContactByName("AB D");
		contactsPage.selectContactByName("EE W");
		
	}
	
	@DataProvider
	public Object[][] getCrmTestData()
	{
		Object data [][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	
	@Test(priority=4, dataProvider="getCrmTestData")
	public void validateCreateNewContact(String Title, String firstname, String lastname, String company)
	{
		homepage.clickOnNewContactLink();
	//	contactsPage.createNewContact("Mr.", "Tom", "C", "Google");
		contactsPage.createNewContact(Title, firstname, lastname, company);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
