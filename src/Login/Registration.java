package Login;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Registration {
	public static WebDriver driver;
	@BeforeMethod
	public void LoginSetup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
		 driver = new ChromeDriver();	
		 driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("http://addithr.azurewebsites.net/");
	}
	@Test(priority=1)
	public void RegistrationPositive() throws InterruptedException
	{
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[4]/a")).click();
		driver.findElement(By.xpath("//input[@id='registeredCompanyName']")).sendKeys("PV Sollutions");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("pv@gmail.com");
		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("5555555555");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pv@12345");
		driver.findElement(By.xpath("//button[@id='registerButton']")).click();
		Thread.sleep(40000);
		List<String>OTP=getOTP("username","pv@gmail.com");
		String a=OTP.get(0);
		String b=OTP.get(1);
		System.out.println(a);
		System.out.println(b);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[1]/div/div[1]/input")).sendKeys(a);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[2]/div/div[1]/input")).sendKeys(b);
	    driver.findElement(By.xpath("//button[@id='ConfirmButton']")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Rahul");
	    driver.findElement(By.xpath("//input[@id='middleName']")).sendKeys("K");
	    driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("L");
	    driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[4]/div")).click();
	    datePicker("1/01/2000");
	    driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[5]/div/div/div/div")).click();
	    driver.findElement(By.xpath("//span[contains(text(),'Male')]")).click();
	    driver.findElement(By.xpath("//input[@id='employeeId']")).sendKeys("PV0001");
	    driver.findElement(By.xpath("//input[@id='annualCtc']")).sendKeys("240000");
	    driver.findElement(By.xpath("//button[@id='submitButton']")).click();
	    
	}
	@Test(priority=6)
	public void RegistrationIncorrectEmailID()
	{
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[4]/a")).click();
		driver.findElement(By.xpath("//input[@id='registeredCompanyName']")).sendKeys("PV Sollutions");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("pvgmail.com");
		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("5555555555");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pv@12345");
		driver.findElement(By.xpath("//button[@id='registerButton']")).click();
		String Errormsg =driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[2]/small")).getText();
	String ExpectedErroemsg = "Enter a valid email id";
	Assert.assertEquals(Errormsg, ExpectedErroemsg);
	}
	@Test(priority=5)
	public void RegistrationBlankData()
	{
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[4]/a")).click();
		driver.findElement(By.xpath("//button[@id='registerButton']")).click();
		String ErrorMsg = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[1]/small")).getText();
	String ExpectedErroemsg = "This field is required";
	Assert.assertEquals(ErrorMsg, ExpectedErroemsg);
	String ErrorMsg1 = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[2]/small")).getText();
	String ExpectedErroemsg1 = "This field is required";
	Assert.assertEquals(ErrorMsg1, ExpectedErroemsg1);
	String ErrorMsg2 = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[3]/small")).getText();
	String ExpectedErroemsg2 = "This field is required";
	Assert.assertEquals(ErrorMsg2, ExpectedErroemsg2);
	String ErrorMsg3 = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[4]/small")).getText();
	String ExpectedErroemsg3 = "This field is required";
	Assert.assertEquals(ErrorMsg3, ExpectedErroemsg3);
	
	}
	@Test(priority=4)
	public void RegistrationInvalidPhoneNumber()
	{
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[4]/a")).click();
		driver.findElement(By.xpath("//input[@id='registeredCompanyName']")).sendKeys("PV Sollutions");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("pv2gmail.com");
		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("5555");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pv@12345");
		driver.findElement(By.xpath("//button[@id='registerButton']")).click();
		String Errormsg =driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[3]/small")).getText();
	String ExpectedErroemsg = "Please provide valid phone number";
	Assert.assertEquals(Errormsg, ExpectedErroemsg);
	}
	@Test(priority=3)
	public void RegistrationInvalidPassword()
	{
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[4]/a")).click();
		driver.findElement(By.xpath("//input[@id='registeredCompanyName']")).sendKeys("PV Sollutions");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("pv2gmail.com");
		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("5555");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pv@123");
		driver.findElement(By.xpath("//button[@id='registerButton']")).click();
		String Errormsg =driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]/form/div/div[4]/small")).getText();
	String ExpectedErroemsg = "Password must be at least 8 characters";
	Assert.assertEquals(Errormsg, ExpectedErroemsg);
	}
	@Test(priority=2)
	public void RegistrationDuplicateUser() throws InterruptedException
	{
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[4]/a")).click();
		driver.findElement(By.xpath("//input[@id='registeredCompanyName']")).sendKeys("PV Sollutions");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("pv@gmail.com");
		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("5555555555");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pv@12345");
		driver.findElement(By.xpath("//button[@id='registerButton']")).click();
		Thread.sleep(4000);
		String Errormsg =driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div/div[2]")).getText();
	String ExpectedErroemsg = "User Already exists";
	Assert.assertEquals(Errormsg, ExpectedErroemsg);
	}
	public List<String> getOTP(String Type, String Value)
	{
		MongoClientURI uri = new MongoClientURI("mongodb+srv://additlabs:Addit2021@familydb.wzxxe.mongodb.net/test?authSource=admin&replicaSet=atlas-b3hasj-shard-0&readPreference=primary&ssl=true&connecTimeoutMS=30000&socketTimeoutMS=30000");
		MongoClient mongoclient = new MongoClient(uri);
		DB db= mongoclient.getDB("addithrmaster");
		DBCollection coll = db.getCollection("usercollection");
		BasicDBObject searchQuerry = new BasicDBObject("username","pv@gmail.com");
		DBCursor cursor = coll.find(searchQuerry);
		List<DBObject> list = cursor.toArray();
		List<String> SMSOTP = list.stream().map(o -> String.valueOf(o.get("Smsotp"))).collect(Collectors.toList());
		List<String> MAILOTP = list.stream().map(o -> String.valueOf(o.get("Mailotp"))).collect(Collectors.toList());
	//	System.out.println(names);
		List<String> OTPS = new ArrayList<>();
		
		OTPS.addAll(SMSOTP);
		OTPS.addAll(MAILOTP);
		return OTPS;
		
	}
	public void datePicker(String a) throws InterruptedException {
		 Actions actions2 = new Actions(driver);
		 Actions actions1 = new Actions(driver);
		actions1.moveToElement( driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[2]"))).perform();
		Thread.sleep(1000);	
		String ad= a;
		String[] ab= ad.split("/");
		String de = ab[0];
		String nj = ab[1];
		String lm = ab[2];
	String monthname=DateConverter(ad);
		int t=Integer.parseInt(lm);
	String cd=	driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[2]")).getText();
	int m=Integer.parseInt(cd);
	if(t>m)
	{
		while(!driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[2]")).getText().contains(lm))
			{
          driver.findElement(By.xpath("//span[@class='rmdp-arrow-container rmdp-right ']")).click();
			}
	}
	else
	{
		while(!driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[2]")).getText().contains(lm))
			{
      driver.findElement(By.xpath("//span[@class='rmdp-arrow-container rmdp-left ']")).click();
			}
	}
	driver.findElement(By.xpath("//div[@class='rmdp-header-values']/span[1]")).click();
	List<WebElement> Month = driver.findElements(By.xpath("//div[@class='rmdp-ym']/div/span"));
for(int mo=0;mo<Month.size();mo++)
{	 
	   String mn= Month.get(mo).getText();
if(mn.equals(monthname))
	  {
		  Month.get(mo).click();
	  }
}
List<WebElement> Date= driver.findElements(By.xpath("//div[@class='rmdp-day']/span"));
for(int da=0;da<Date.size();da++)
{
	 String dy=  Date.get(da).getText();
	 if(dy.equals(de))
	 {
		 Date.get(da).click();
		break;
		 }
}	
driver.findElement(By.xpath("//button[@class='rmdp-button rmdp-action-button'][1]")).click();
	}

	public String DateConverter(String abc)
	{
		String[] ab = abc.split("/");
	String ce= ab[0];
	String ef = ab[1];
	String gh = ab[2];
	String month= null;
	if(ef.equals("01"))
	{
		month= "January";
	}
	if(ef.equals("02"))
	{
		month= "February";
	}
	if(ef.equals("03"))
	{
		month= "March";
	}
	if(ef.equals("04"))
	{
		month= "April";
	}
	if(ef.equals("05"))
	{
		month= "May";
	}
	if(ef.equals("06"))
	{
		month= "June";
	}
	if(ef.equals("07"))
	{
		month= "July";
	}
	if(ef.equals("08"))
	{
		month= "August";
	}
	if(ef.equals("09"))
	{
		month= "Seprember";
	}
	if(ef.equals("10"))
	{
		month= "October";
	}
	if(ef.equals("11"))
	{
		month= "November";
	}
	
	if(ef.equals("12"))
	{
		month= "December";
	}
		return month;	
	}
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

}
